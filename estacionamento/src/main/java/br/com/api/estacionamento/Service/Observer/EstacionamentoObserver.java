package br.com.api.estacionamento.Service.Observer;

import br.com.api.estacionamento.Entidades.Estrutura.PisoEstacionamento;

public interface EstacionamentoObserver {
    public void adicionarObserver(Observer observer);
    public void excluirObserver(Observer observer);
    public void notificarObserver(PisoEstacionamento pisos);
}
