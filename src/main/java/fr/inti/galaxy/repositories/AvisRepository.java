package fr.inti.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inti.galaxy.entities.Avis;

public interface AvisRepository extends JpaRepository<Avis, Long> {
	
	
	public List<Avis> findByLivrable_id(long id);

}
