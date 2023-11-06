package br.com.api.estacionamento.Entidades.Estrutura;

import br.com.api.estacionamento.Entidades.Fabrica.Carro;

public class Vaga {
    //status vaga (vazia ou cheia)
    private boolean vazia;
    //Carro que ocupa a vaga em caso dela vazia, valor nulo
    private Carro carro;

    public Vaga(boolean vazia, Carro carro) {
        this.vazia = vazia;
        this.carro = carro;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public boolean isVazia() {
        return vazia;
    }

    public void setVazia(boolean vazia) {
        this.vazia = vazia;
    }
}
