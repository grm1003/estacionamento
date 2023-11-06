package br.com.api.estacionamento.Service.Pagamentos;

import br.com.api.estacionamento.Service.Logger.Logger;
import br.com.api.estacionamento.Service.Pagamentos.PagamentosStrategy;

public class Pix implements PagamentosStrategy {
    @Override
    public void realizarPagamento(double valor) {
        Logger logger = Logger.getInstance();
        logger.println("Pagamento via pix de R$" + valor);
    }
}
