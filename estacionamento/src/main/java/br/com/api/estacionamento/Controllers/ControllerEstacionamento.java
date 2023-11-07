package br.com.api.estacionamento.Controllers;



import br.com.api.estacionamento.Entidades.Cartao;
import br.com.api.estacionamento.Entidades.Estrutura.Estacionamento;
import br.com.api.estacionamento.Entidades.Fabrica.Carro;
import br.com.api.estacionamento.Entidades.Fabrica.ToyotaFabrica;
import br.com.api.estacionamento.Repository.CarroRepository;
import br.com.api.estacionamento.Repository.CartaoRepository;
import br.com.api.estacionamento.Service.Fachada.EstacionamentoFachada;
import br.com.api.estacionamento.Service.Observer.DisplayObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerEstacionamento {
    @Autowired
    CarroRepository carroRepository;
    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    private EstacionamentoFachada estacionamentoFachada;

    private Estacionamento estacionamento = new Estacionamento();


    @GetMapping("/carro/entrar")
    String Entra() {
        ToyotaFabrica toyotaFabrica = new ToyotaFabrica();
        Carro toy = toyotaFabrica.criaCarro("EIS0875", "Pokemon");
        DisplayObserver displayVagas = new DisplayObserver();
        estacionamentoFachada.adicionarObserver(displayVagas);
        Cartao cartao = estacionamentoFachada.entraCarro(estacionamento.getPisos().get(0),8,toy);
        if(cartao != null) return "Carro da placa "+toy.getPlaca()+" entrou na vaga do estacionamento!";
        else return "Erro ao entrar com carro | Carro já no estacionamento";
    }

    @GetMapping("/carro/sair")
    String Sair() {
       Cartao cartao = estacionamentoFachada.localizarCartaoAtivo("EIS0875");
       if(cartao == null)return "Carro não está no estacionamento";
       estacionamentoFachada.saiCarro(estacionamento.getPisos().get(0), 8, cartao, "Pix");
       return "Carro da placa "+cartao.getPlaca()+" teve pagamento concluido no valor de "+cartao.getTotal()+" e saiu do estacionamento ";
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
