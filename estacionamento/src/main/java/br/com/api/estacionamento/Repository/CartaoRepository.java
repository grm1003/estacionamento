package br.com.api.estacionamento.Repository;

import br.com.api.estacionamento.Entidades.Cartao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, String> {
    List<Cartao> findByPlaca(String placa);
}
