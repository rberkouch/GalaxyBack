package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.FormationDTO;
import fr.inti.galaxy.entities.Formation;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.FormationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class FormationRestApi {
	@Autowired
	FormationService formationService;

	@GetMapping("/formations")
	//@PreAuthorize("hasAuthority('SCOPE_USER')")
	public List<FormationDTO> formations() {
		return formationService.getAll().stream().map(MapperImpl::fromFormation).collect(Collectors.toList());
	}

	@GetMapping("/formations/{id}")
	public FormationDTO getFormation(@PathVariable(name = "id") Integer formationId) {
		return MapperImpl.fromFormation(formationService.getFormationById(formationId));
	}

	@PostMapping("/addFormation")
	public Formation addNewFormation(@RequestBody Formation formation) {
		return formationService.save(formation);
	}

	@DeleteMapping("/deleteFormation/{id}")
	public void deleteFormation(@PathVariable("id") int id) {
		formationService.delete(id);
	}

	@PutMapping("/updateFormation")
	public void updateFormation(@RequestBody Formation formation) {
		formationService.save(formation);
	}
}
