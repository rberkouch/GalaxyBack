package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Livrable;
import fr.inti.galaxy.repositories.LivrableRepository;
import fr.inti.galaxy.repositories.services.LivrableService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LivrableServiceImpl implements LivrableService {
	@Autowired
	private LivrableRepository livrableRepository;

	public List<Livrable> getAll() {
		return livrableRepository.findAll();
	}

	@Override
	public Livrable getLivrableById(Integer i) {
		return livrableRepository.findById(i).get();
	}

	@Override
	public Livrable save(Livrable livrable) {

		return livrableRepository.save(livrable);
	}

	@Override
	public void delete(int id) {
		livrableRepository.delete(livrableRepository.getReferenceById(id));
	}

	@Override
	public List<Livrable> searchLivrables(String keyword) {
		return livrableRepository.searchLivrable(keyword);
	}

	@Override
	public List<Livrable> findLivrablesByUsername(String username) {
		return livrableRepository.findLivrablesByUsername(username);
	}
}
