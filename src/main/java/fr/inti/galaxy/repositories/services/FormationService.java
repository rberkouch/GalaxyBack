package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Formation;

public interface FormationService {
	public List<Formation> getAll();

	public Formation getFormationById(Integer i);

	public Formation save(Formation formation);
}
