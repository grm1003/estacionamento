package br.com.api.estacionamento.Entidades.Estrutura;

import br.com.api.estacionamento.Service.Fachada.EstacionamentoFachada;

import java.util.ArrayList;

public class Estacionamento{
    ArrayList<PisoEstacionamento> pisos;

    public Estacionamento() {
        EstacionamentoFachada estacionamentoFachada = new EstacionamentoFachada();
        this.pisos = estacionamentoFachada.estacionamentoAbre(new ArrayList<>());
    }

    public ArrayList<PisoEstacionamento> getPisos() {
        return pisos;
    }

}
