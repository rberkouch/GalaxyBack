package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Sujet;

@Repository
public interface SujetRepository extends JpaRepository<Sujet, Integer>{

}
