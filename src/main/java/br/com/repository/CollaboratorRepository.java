package br.com.repository;

import br.com.document.Collaborator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaboratorRepository extends MongoRepository<Collaborator, Long> {

    List<Collaborator> findBySectorIdIs(Long id);
}