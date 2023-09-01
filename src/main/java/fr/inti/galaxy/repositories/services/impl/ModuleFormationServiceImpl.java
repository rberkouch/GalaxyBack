package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.inti.galaxy.entities.ModuleFormation;
import fr.inti.galaxy.repositories.ModuleFormationRepository;
import fr.inti.galaxy.repositories.services.ModuleFormationService;

@Service
@Transactional
public class ModuleFormationServiceImpl implements ModuleFormationService{

	@Autowired
	private ModuleFormationRepository moduleFormationRepository;
	
	@Override
	public List<ModuleFormation> getAll(){
		return moduleFormationRepository.findAll();
	}
	@Override
	public ModuleFormation save(ModuleFormation moduleFormation) {
		
		return moduleFormationRepository.save(moduleFormation);
	}
}
