package br.com.api.estacionamento.Service.Fachada;

import br.com.api.estacionamento.Entidades.Cartao;
import br.com.api.estacionamento.Entidades.Estrutura.PisoEstacionamento;
import br.com.api.estacionamento.Entidades.Estrutura.Vaga;
import br.com.api.estacionamento.Entidades.Fabrica.Carro;
import br.com.api.estacionamento.Repository.CarroRepository;
import br.com.api.estacionamento.Repository.CartaoRepository;
import br.com.api.estacionamento.Service.Logger.Logger;
import br.com.api.estacionamento.Service.Observer.EstacionamentoObserver;
import br.com.api.estacionamento.Service.Observer.Observer;
import br.com.api.estacionamento.Service.Pagamentos.CartaoCrédito;
import br.com.api.estacionamento.Service.Pagamentos.CartaoDédito;
import br.com.api.estacionamento.Service.Pagamentos.Pix;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class EstacionamentoFachada implements EstacionamentoObserver {

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    private  double minuto = 0.08;
    private  double hora = 2;
    ArrayList<Observer> observers = new ArrayList<>();


    //seta por padrão quantidade de vagas e nome dos pisos
    public  ArrayList<PisoEstacionamento> estacionamentoAbre(ArrayList<PisoEstacionamento> pisos){
        PisoEstacionamento piso1 = new PisoEstacionamento("Piso 1", 50);
        PisoEstacionamento piso2 = new PisoEstacionamento("Piso 2", 30);
        pisos.add(piso1);
        pisos.add(piso2);
        return pisos;
    }

    //processo de entrada de um carro no estacionamento
    @Transactional
    public Cartao entraCarro(PisoEstacionamento piso, int vaga, Carro carro){
        try {
            //verifica se estacionamento não está cheio
            if(!piso.EstaCheio()){
                //preenche vaga com o carro
                piso.PreencheVaga(vaga, carro);
                Logger logger = Logger.getInstance();
                logger.println("Carro entrou no estacionamento: " + carro.toString());
                notificarObserver(piso);
                //salva carro
                if(!verificaPlacaSalva(carro.getPlaca()))carroRepository.save(carro);

                //Cria um novo cartão para o carro que acabou de entraCarro
                if(!CartaonaoPago(carro.getPlaca())){
                    Cartao novo = criaCartaoRegistro(carro.getPlaca());
                    cartaoRepository.save(novo);
                    return novo;
                }

                //teriamos que salvar esse cartão em algum banco de dados para salvar as informações
            }else System.out.println("Estacionameto ocupado");
        }catch (Exception e){
            System.out.println("Erro: "+ e);
        }

        return null;
    }

    //processo de saida de um carro no estacionamento
    @Transactional
    public void saiCarro(PisoEstacionamento piso, int vaga, Cartao cartao, String tipoPagamento){
        try {
            //verifica se tem vagas preenchidas
            if(piso.ContaVagasDisponiveis() < piso.tamanhoEstacionamento()) {
                Vaga[] verif = piso.getVagas();
                if(verif[vaga].isVazia()) throw new IllegalStateException("Vaga está vazia");

                //retorna valor a ser pago e realiza pagamento
                RealizaPagamento(cartao.registrarSaida(),cartao,tipoPagamento);
                atualizaCartao(cartao);
                //verifica se foi pago se sim ele libera a vaga do carro
                if(cartao.isPago())piso.LiberaVaga(vaga);
                //salvar registro de banco de pagamento e utilização da vaga
                Logger logger = Logger.getInstance();
                logger.println("Carro saiu do estacionamento");
                notificarObserver(piso);


            }
        }catch (Exception e){
            System.out.println("Erro: "+ e);
        }
    }

    @Override
    public void adicionarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void excluirObserver(Observer observer) {
        observers.remove(observer);
    }



    @Override
    public void notificarObserver( PisoEstacionamento pisos) {
        for (Observer a: observers){
            a.update(pisos.getNome(), pisos.ContaVagasDisponiveis());

        }
    }

    public Cartao criaCartaoRegistro(String placa){
        Cartao fiscal = new Cartao(minuto,hora, placa);
        return fiscal;
    }

    public Boolean verificaPlacaSalva(String placa){
        Carro encontrado = carroRepository.findByPlaca(placa);
        if(encontrado != null) return true;
        else return false;
    }

    public Boolean CartaonaoPago(String placa){
        List<Cartao> encontrados = cartaoRepository.findByPlaca(placa);
        for(Cartao encontrado: encontrados){
            if(!encontrado.isPago()) return true;
        }
        return false;
    }

    public void atualizaCartao(Cartao cartao){
        Cartao cartaoAtualizar = null;
        List<Cartao> encontrados = cartaoRepository.findByPlaca(cartao.getPlaca());
        for(Cartao encontrado: encontrados){
            if(!encontrado.isPago()) cartaoAtualizar = encontrado;
        }
        try {
            cartao.setPago(true);
            cartao.setPagamentoStrategy(null);
            cartaoRepository.deleteById(cartaoAtualizar.getId());
            cartaoRepository.save(cartao);
        }catch (NullPointerException e){
            System.out.println("Registro não encontrado");
        }

    }

    public void definePagamento(Cartao cartao, String tipoPagamento){
        cartao.setTipoPagamento(tipoPagamento);
        if(tipoPagamento.equals("Pix"))cartao.setPagamentoStrategy(new Pix());
        if(tipoPagamento.equals("Crédito"))cartao.setPagamentoStrategy(new CartaoCrédito());
        if(tipoPagamento.equals("Débito")) cartao.setPagamentoStrategy(new CartaoDédito());

    }

    public void RealizaPagamento(double valor,Cartao cartao, String tipoPagamento){
        //setar metodo de pagamento a preferencia do usuário
        definePagamento(cartao, tipoPagamento);
        //realiza o pagamento do cartao e seta cartao como pago
        cartao.realizaPagamento(valor);
    }

    //-------------------------funções básicas api-------------------------------------

    public List<Carro> listAllCarros() {
        return carroRepository.findAll();
    }

    public List<Cartao> listAllCartoes() {
        return cartaoRepository.findAll();
    }

    public Cartao localizarCartaoAtivo(String placa){
        List<Cartao> cartoes = cartaoRepository.findByPlaca(placa);
        for (Cartao cartao : cartoes)
            if(!cartao.isPago())return cartao;
        return null;
    }
}
