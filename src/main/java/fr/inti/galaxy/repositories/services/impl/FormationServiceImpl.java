package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Formation;
import fr.inti.galaxy.repositories.FormationRepository;
import fr.inti.galaxy.repositories.services.FormationService;

@Service
public class FormationServiceImpl implements FormationService{
	
	@Autowired
	private FormationRepository formationRepository;
	
	public List<Formation> getAll(){
		return formationRepository.findAll();
	}
	
	@Override
	public Formation getFormationById(Integer i) {
		return formationRepository.findById(i).get();
	}
	
	@Override
	public Formation save(Formation formation) {
		
		return formationRepository.save(formation);
	}

	@Override
	public void delete(int id) {
		formationRepository.delete(formationRepository.getReferenceById(id));		
	}
	
	

}
