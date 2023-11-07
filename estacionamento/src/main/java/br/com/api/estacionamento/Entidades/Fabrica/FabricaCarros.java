package br.com.api.estacionamento.Entidades.Fabrica;

public class FabricaCarros {


    private HondaFabrica honda;
    private ToyotaFabrica toyota;

    private HyundaiFabrica hyundai;

    public Carro controiCarro(String placa, String modelo, String marca) {

        if (marca.equals("honda") || marca.equals("Honda")) {
            return honda.criaCarro(placa, modelo);
        } else {
            if (marca.equals("toyota") || marca.equals("Toyota")) {
                return toyota.criaCarro(placa, modelo);
            } else {
                if (marca.equals("Hyundai") || marca.equals("hyundai")) {
                    return hyundai.criaCarro(placa, modelo);
                } else return new Carro(placa, modelo, marca);
            }
        }
    }
}



