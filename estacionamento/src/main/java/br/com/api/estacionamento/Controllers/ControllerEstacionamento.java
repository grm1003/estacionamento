package br.com.api.estacionamento.Controllers;



import br.com.api.estacionamento.DTO.CarroDTO;
import br.com.api.estacionamento.Entidades.Cartao;
import br.com.api.estacionamento.Entidades.Estrutura.Estacionamento;
import br.com.api.estacionamento.Entidades.Fabrica.Carro;
import br.com.api.estacionamento.Entidades.Fabrica.FabricaCarros;
import br.com.api.estacionamento.Service.Fachada.EstacionamentoFachada;
import br.com.api.estacionamento.Service.Observer.DisplayObserver;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEstacionamento {

    private Estacionamento estacionamento = new Estacionamento();
    DisplayObserver displayVagas = new DisplayObserver();
    FabricaCarros fabrica = new FabricaCarros();

    @Autowired
    private EstacionamentoFachada estacionamentoFachada;

    @GetMapping("teste/carro/entrar")
    String EntrarTeste() {
        Carro toy = fabrica.controiCarro("EIS0875", "Pokemon","Toyota");
        estacionamentoFachada.adicionarObserver(displayVagas);
        Cartao cartao = estacionamentoFachada.entraCarro(estacionamento.getPisos().get(0),8,toy);
        if(cartao != null) return "Carro da placa "+toy.getPlaca()+" entrou na vaga do estacionamento!";
        else return "Erro ao entrar com carro | Carro já no estacionamento";
    }

    @GetMapping("teste/carro/sair")
    String SairTeste() {
       Cartao cartao = estacionamentoFachada.localizarCartaoAtivo("EIS0875");
       if(cartao == null)return "Carro não está no estacionamento";
       Boolean certo = estacionamentoFachada.saiCarro(estacionamento.getPisos().get(0), 8, cartao, "Pix");
       if(certo)return "Carro da placa " + cartao.getPlaca() + " teve pagamento concluído no valor de " + cartao.getTotal() + " e saiu do estacionamento";
       return "Erro ao realizar pagamento";
    }

    @GetMapping("/estacionamento")
    Estacionamento Estrutura() {
        return estacionamento;
    }

    @PostMapping("carro/entrar")
    @Transactional
    public String Entrar(@RequestBody @Valid CarroDTO carro) {
        Carro carro_entrada = fabrica.controiCarro(carro.getPlaca(), carro.getModelo(), carro.getMarca());
        estacionamentoFachada.adicionarObserver(displayVagas);
        Cartao cartao = estacionamentoFachada.entraCarro(estacionamento.getPisos().get(carro.getPiso()), carro.getVaga(), carro_entrada);
        if (cartao != null) {
            return "Carro da placa " + carro_entrada.getPlaca() + " entrou na vaga do estacionamento!";
        } else {
            return "Erro ao entrar com carro | Carro já no estacionamento";
        }
    }

    @PostMapping("carro/sair")
    public String Sair(@PathParam("placa")String placa,@PathParam("pagamento") String pagamento, @PathParam("vaga")Integer vaga, @PathParam("piso")Integer piso ) {
        Cartao cartao = estacionamentoFachada.localizarCartaoAtivo(placa);
        if (cartao == null) {
            return "Carro não está no estacionamento";
        }
        Boolean certo = estacionamentoFachada.saiCarro(estacionamento.getPisos().get(piso),vaga, cartao, pagamento);
        if(certo)return "Carro da placa " + cartao.getPlaca() + " teve pagamento concluído no valor de " + cartao.getTotal() + " e saiu do estacionamento";
        return "Erro ao realizar pagamento";
    }

    @GetMapping("/cartoes")
    List<Cartao> Cartoes() {
       return estacionamentoFachada.listAllCartoes();
    }

    @GetMapping("/carros")
    List<Carro> Carros() {
        return estacionamentoFachada.listAllCarros();
    }

}
