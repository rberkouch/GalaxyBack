package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Avis;
import fr.inti.galaxy.entities.Formation;
import fr.inti.galaxy.repositories.AvisRepository;
import fr.inti.galaxy.repositories.FormationRepository;
import fr.inti.galaxy.repositories.services.AvisService;

@Service
public class AvisServiceImpl implements AvisService {
	
	@Autowired
	private AvisRepository avisRepository;
	
	public List<Avis> getAll(){
		return avisRepository.findAll();
	}
	
	@Override
	public Avis getAvisById(Long id) {
		return avisRepository.findById(id).get();
	}
	
	@Override
	public Avis save(Avis avis) {
		
		return avisRepository.save(avis);
	}

	@Override
	public void delete(Long id) {
		avisRepository.deleteById(id);	
	}
	
	public List<Avis> findByLivrable_id(long id)
	{
		return avisRepository.findByLivrable_id(id);
	}
	

}
