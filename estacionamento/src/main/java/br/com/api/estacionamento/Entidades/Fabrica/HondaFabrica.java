package br.com.api.estacionamento.Entidades.Fabrica;

public class HondaFabrica implements CarroFabrica {
    @Override
    public Carro criaCarro(String placa, String modelo) {
        return new Carro(placa,modelo,"Honda");
    }
}
