package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.inti.galaxy.entities.Livrable;

@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Integer>{

}
