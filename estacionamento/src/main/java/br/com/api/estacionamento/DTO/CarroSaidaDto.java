package br.com.api.estacionamento.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CarroSaidaDto {
    private String placa;
    private Integer piso;
    private Integer vaga;

    @JsonCreator
    public CarroSaidaDto(String placa, Integer piso, Integer vaga) {
        this.placa = placa;
        this.piso = piso;
        this.vaga = vaga;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getPiso() {
        return piso;
    }

    public Integer getVaga() {
        return vaga;
    }
}
