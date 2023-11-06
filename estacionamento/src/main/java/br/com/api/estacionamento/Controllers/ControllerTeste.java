package br.com.api.estacionamento.Controllers;



import br.com.api.estacionamento.Entidades.Fabrica.Carro;
import br.com.api.estacionamento.Entidades.Fabrica.HondaFabrica;
import br.com.api.estacionamento.Repository.CarroRepository;
import br.com.api.estacionamento.Repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerTeste {
    @Autowired
    CarroRepository carroRepository;
    @Autowired
    CartaoRepository cartaoRepository;
    @GetMapping()
    String Work() {
        return "Funfando";
    }

    @GetMapping("/test")
    List<Carro> all() {
        return carroRepository.findAll();
    }

}
