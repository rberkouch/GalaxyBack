package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Profile;
import fr.inti.galaxy.repositories.ProfileRepository;
import fr.inti.galaxy.repositories.services.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public List<Profile> getAll(){
		return profileRepository.findAll();
	}

}
