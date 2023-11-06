package br.com.api.estacionamento.Entidades.Fabrica;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Data
@Document(collection  = "carros")
public class Carro {

    @Id
    private String placa;
    private String modelo;
    private String marca;


    public Carro(String placa, String modelo, String marca) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return "Carro (" +
                "placa= " + placa +
                ", modelo= " + modelo +
                ", marca= " + marca +
                ')';
    }
}
