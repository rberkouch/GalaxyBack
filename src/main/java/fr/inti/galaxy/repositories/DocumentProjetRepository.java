package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.DocumentProjet;

@Repository
public interface DocumentProjetRepository extends JpaRepository<DocumentProjet, Integer>{

}
