package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Integer> {

}
