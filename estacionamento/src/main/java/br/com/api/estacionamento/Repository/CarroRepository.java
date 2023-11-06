package br.com.api.estacionamento.Repository;

import br.com.api.estacionamento.Entidades.Fabrica.Carro;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends MongoRepository<Carro, String> {
    Carro findByPlaca(String placa);
}
