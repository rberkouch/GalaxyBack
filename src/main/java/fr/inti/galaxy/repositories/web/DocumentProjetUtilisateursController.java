package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.DocumentProjetUtilisateursDTO;
import fr.inti.galaxy.entities.DocumentProjetUtilisateurs;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.DocumentProjetUtilisateursService;

@RestController
@CrossOrigin("*")
public class DocumentProjetUtilisateursController {
	@Autowired
	DocumentProjetUtilisateursService documentProjetUtilisateursService;

	@GetMapping("/documentProjetUtilisateurs")
	public List<DocumentProjetUtilisateursDTO> getAllDocumentProjetUtilisateurs() {
		return documentProjetUtilisateursService.getAll().stream().map(MapperImpl::fromDocumentProjetUtilisateurs)
				.collect(Collectors.toList());
	}

	@GetMapping("/documentProjetUtilisateurs/{idSujet}/{idUser}")
	public List<DocumentProjetUtilisateurs> findAllDocumentProjetUtilisateurs(@PathVariable("idSujet") Long idSujet,
			@PathVariable("idUser") String idUser) {
		return documentProjetUtilisateursService.findAllDocumentProjetUtilisateurs(idSujet, idUser);
	}

	@PostMapping("/documentProjetUtilisateurs/insertsujetuser")
	void affectSujetToUser(@RequestParam("idSujet") Long idSujet, @RequestParam("idUser") String idUser) {
		documentProjetUtilisateursService.affectSujetToUser(idSujet, idUser);
	}

	@DeleteMapping("/documentProjetUtilisateurs/{sujetId}/{idUser}")
	public void deleteOneFromDocumentProjetUtilisateurs(@PathVariable("sujetId") Long idSujet,
			@PathVariable("idUser") String idUser) {
		documentProjetUtilisateursService.deleteOneFromDocumentProjetUtilisateurs(idSujet, idUser);
	}
}
