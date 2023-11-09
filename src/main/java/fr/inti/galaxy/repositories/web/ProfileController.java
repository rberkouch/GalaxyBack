package fr.inti.galaxy.repositories.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.entities.Profile;
import fr.inti.galaxy.repositories.services.ProfileService;

@RestController
@CrossOrigin("*")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@GetMapping("/Profiles")
	public List<Profile> getAll() {
		return profileService.getAll();
	}

}
