package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.security.AppRole;

public interface AppRoleService {
	public List<AppRole> getAll();
}
