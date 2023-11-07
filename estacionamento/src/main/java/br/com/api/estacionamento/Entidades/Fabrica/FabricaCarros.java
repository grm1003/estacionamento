package br.com.api.estacionamento.Entidades.Fabrica;

public class FabricaCarros {
    private HondaFabrica honda = new HondaFabrica();
    private ToyotaFabrica toyota = new ToyotaFabrica();

    private HyundaiFabrica hyundai = new HyundaiFabrica();

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



