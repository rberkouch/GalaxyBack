package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inti.galaxy.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Integer> {

}
