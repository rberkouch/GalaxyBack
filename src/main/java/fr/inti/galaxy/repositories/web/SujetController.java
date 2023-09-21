package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.SujetDTO;
import fr.inti.galaxy.entities.Sujet;
import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.SujetService;

@RestController
@CrossOrigin("*")
public class SujetController {
	@Autowired
	SujetService sujetService;

	@GetMapping("/sujets")
	public List<SujetDTO> sujets() {
		return sujetService.getAll().stream().map(MapperImpl::fromSujet).collect(Collectors.toList());
	}

	@GetMapping("/sujets/user/{username}")
	List<Sujet> findSujetsByUsername(@PathVariable("username") String username) {
		return sujetService.findSujetsByUsername(username);
	}

	@PostMapping("/sujets/insertsujetuser")
	void affectSujetToUser(@RequestParam("idSujet") Long idSujet, @RequestParam("idUser") String idUser) {
		sujetService.affectSujetToUser(idSujet, idUser);
	}

	@GetMapping("/sujets/{sujetId}")
	public SujetDTO getSujet(@PathVariable(name = "sujetId") Integer sujetId) {
		return MapperImpl.fromSujet(sujetService.getSujetById(sujetId));
	}

	@PostMapping("/sujets")
	public Sujet addNewSujet(@RequestBody Sujet formation) {
		return sujetService.save(formation);
	}

	@DeleteMapping("/sujets/{sujetId}")
	public void deleteSujet(@PathVariable("sujetId") int sujetId) {
		sujetService.delete(sujetId);
	}

	@DeleteMapping("/sujets/{sujetId}/{idUser}")
	public void deleteOneFromDocumentProjetUtilisateurs(@PathVariable("sujetId") Long idSujet,
			@PathVariable("idUser") String idUser) {
		sujetService.deleteOneFromDocumentProjetUtilisateurs(idSujet, idUser);
	}

	@PutMapping("/sujets")
	public void updateSujet(@RequestBody Sujet sujet) {
		sujetService.save(sujet);
	}
}
