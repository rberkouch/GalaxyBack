package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
