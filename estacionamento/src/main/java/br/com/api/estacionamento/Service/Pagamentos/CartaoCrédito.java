package br.com.api.estacionamento.Service.Pagamentos;

import br.com.api.estacionamento.Service.Logger.Logger;

public class CartaoCrédito implements PagamentosStrategy{
    @Override
    public void realizarPagamento(double valor) {
        Logger logger = Logger.getInstance();
        logger.println("Pagamento via cartão de crédito de R$" + valor);
    }
}
