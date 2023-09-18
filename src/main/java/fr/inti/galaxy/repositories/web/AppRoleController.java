package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.AppRoleDTO;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.AppRoleService;
import fr.inti.galaxy.security.AppRole;

@RestController
@CrossOrigin("*")
public class AppRoleController {
	@Autowired
	AppRoleService appRoleService;

	@GetMapping("/appRoles")
	public List<AppRoleDTO> appRoles() {
		return appRoleService.getAll().stream().map(MapperImpl::fromAppRole).collect(Collectors.toList());
	}

	@PostMapping("/appRoles")
	public AppRole addNewAppRole(@RequestBody AppRole appRole) {
		return appRoleService.save(appRole);
	}

	@DeleteMapping("/appRoles/{role}")
	public void deleteAppRole(@PathVariable("role") String role) {
		appRoleService.delete(role);
	}
}
