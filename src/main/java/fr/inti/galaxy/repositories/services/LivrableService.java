package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Livrable;

public interface LivrableService {
	public List<Livrable> getAll();

	public Livrable getLivrableById(Integer id);

	public Livrable save(Livrable Livrable);
	
	public void delete(int id);
	
	List<Livrable> searchLivrables(String keyword);
	
	List<Livrable> findLivrablesByUsername(String username);
}
