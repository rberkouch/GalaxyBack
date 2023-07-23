package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.ModuleFormation;

public interface ModuleFormationService {

	public List<ModuleFormation> getAll();
	
	public ModuleFormation save(ModuleFormation moduleFormation);
}
