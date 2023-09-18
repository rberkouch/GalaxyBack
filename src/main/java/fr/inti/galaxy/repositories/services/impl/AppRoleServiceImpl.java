package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.repositories.services.AppRoleService;
import fr.inti.galaxy.security.AppRole;
import fr.inti.galaxy.security.AppRoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppRoleServiceImpl implements AppRoleService {
	@Autowired
	private AppRoleRepository appRoleRepository;

	@Override
	public List<AppRole> getAll() {
		return appRoleRepository.findAll();
	}

	@Override
	public AppRole save(AppRole appRole) {
		return appRoleRepository.save(appRole);
	}

	@Override
	public AppRole findOne(String role) {
		return appRoleRepository.findById(role).get();
	}

	@Override
	public void delete(String role) {
		AppRole appRole=findOne(role);
		appRoleRepository.delete(appRole);
		
	}

	

}
