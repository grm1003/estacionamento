package br.com.api.estacionamento.Entidades;

import br.com.api.estacionamento.Service.Pagamentos.PagamentosStrategy;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
@Document(collection  = "cartoes")
public class Cartao {


    @Id
    String id;
    //preço de cada minuto do carro dentro do estacionamento
    private final double preco_min;

    //preço de cada hora do carro dentro do estacionamento
    private final double preco_horas;

    //Carro vinculado a esse cartão
    String placa;

    //Data de entrada no estacionamento
    private LocalDateTime entrada;
    //Data de saída no estacionamento
    private LocalDateTime saida;

    //Valor total do cartão que deve ser pago
    private double total;

    //Strategy que define estratégia de pagamento do total
    private PagamentosStrategy pagamentoStrategy;
    private String tipoPagamento;

    //verifica se cartão foi pago ou não
    private boolean pago;

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getId() {
        return id;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public double getPreco_min() {
        return preco_min;
    }

    public double getPreco_horas() {
        return preco_horas;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public double getTotal() {
        return total;
    }

    public PagamentosStrategy getPagamentoStrategy() {
        return pagamentoStrategy;
    }

    //retorna se foi pago ou não
    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void setPagamentoStrategy(PagamentosStrategy pagamentoStrategy) {
        this.pagamentoStrategy = pagamentoStrategy;
    }

    //seta valores imutáveis de preço de minuto e hora, além de vicular a um carro
    //e registrar horário de entrada
    public Cartao(double preco_min, double preco_horas, String placa) {
        this.preco_min = preco_min;
        this.preco_horas = preco_horas;
        this.placa = placa;
        registrarEntrada();
        this.saida = null;
        this.total = 2;
        this.pago = false;
        this.id = placa+entrada.toString();
    }

    //seta tempo de entrada do carro no estacionamento e soma o total a uma taxa base
    public void registrarEntrada() {
        entrada = LocalDateTime.now();
    }

    //seta tempo de saida do carro no estacionamento e calcula duração e total do cartao a ser pago
    public double registrarSaida() {
        saida = LocalDateTime.now();
        Duration duration = calcularDuracao();
        if(duration != null){
            this.total += calcularValor(duration.toHoursPart(), duration.toMinutesPart());
        }
        return total;
    }

    //realiza o pagamento de acordo com a estratégia escolhida
    public void realizaPagamento(double valor) {
        pagamentoStrategy.realizarPagamento(valor);
        setPago(true);
    }

    //calcula duração de tempo do carro no estacionamento antes de saida
    private Duration calcularDuracao() {
        if (entrada != null && saida != null) {
            return Duration.between(entrada, saida);
        }
        return null;
    }
    //Calcula total de valor a ser pago de acordo com tempo e taxa de cada tempo
    private double calcularValor(int horas, int minutos){
        return (horas*preco_horas)+(minutos*preco_min);
    }

    //para teste de calculo
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

}

