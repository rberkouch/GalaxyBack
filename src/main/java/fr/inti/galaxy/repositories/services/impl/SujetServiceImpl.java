package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Sujet;
import fr.inti.galaxy.repositories.SujetRepository;
import fr.inti.galaxy.repositories.services.SujetService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SujetServiceImpl implements SujetService {
	@Autowired
	private SujetRepository sujetRepository;

	public List<Sujet> getAll() {
		return sujetRepository.findAll();
	}

	@Override
	public Sujet getSujetById(Integer i) {
		return sujetRepository.findById(i).get();
	}

	@Override
	public Sujet save(Sujet sujet) {

		return sujetRepository.save(sujet);
	}

	@Override
	public void delete(int id) {
		sujetRepository.delete(sujetRepository.getReferenceById(id));
	}

	@Override
	public List<Sujet> findSujetsByUsername(String username) {
		return sujetRepository.findSujetsByUsername(username);
	}

}
