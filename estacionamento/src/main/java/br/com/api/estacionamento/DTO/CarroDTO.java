package br.com.api.estacionamento.DTO;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CarroDTO {
    private String placa;
    private String modelo;
    private String marca;
    private Integer piso;
    private Integer vaga;

    @JsonCreator
    public CarroDTO(String placa, String modelo, String marca, Integer piso, Integer vaga) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.piso = piso;
        this.vaga = vaga;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getPiso() {
        return piso;
    }

    public Integer getVaga() {
        return vaga;
    }
}
