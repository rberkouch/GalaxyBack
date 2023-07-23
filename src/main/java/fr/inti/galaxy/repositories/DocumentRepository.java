package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inti.galaxy.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
