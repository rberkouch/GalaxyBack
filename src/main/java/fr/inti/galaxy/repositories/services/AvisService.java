package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Avis;



public interface AvisService {
	
	public List<Avis> getAll();

	public Avis getAvisById(Long i);

	public Avis save(Avis avis);
	
	public void delete(Long id);
	
	public List<Avis> findByLivrable_id(long id);

}