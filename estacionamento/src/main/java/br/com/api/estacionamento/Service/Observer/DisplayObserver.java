package br.com.api.estacionamento.Service.Observer;

import br.com.api.estacionamento.Service.Observer.Observer;

public class DisplayObserver implements Observer {
    @Override
    public void update(String nome, int vagas) {
        System.out.println( nome + " Vagas Disponíveis : " + vagas );
    }
}
