package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.ModuleFormation;

@Repository
public interface ModuleFormationRepository extends JpaRepository<ModuleFormation, Integer> {

}
