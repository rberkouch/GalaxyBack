package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.FormationDTO;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.impl.FormationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class FormationRestApi {
	private FormationServiceImpl formationService;

	@GetMapping("/formations")
	@PreAuthorize("hasAuthority('SCOPE_USER')")
	public List<FormationDTO> formations() {
		return formationService.getAll().stream().map(MapperImpl::fromFormation).collect(Collectors.toList());
	}

	@GetMapping("/formations/{id}")
	public FormationDTO getFormation(@PathVariable(name = "id") Integer formationId) {
		return MapperImpl.fromFormation(formationService.getFormationById(formationId));
	}
}
