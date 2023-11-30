package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
		Predicate<Sujet> predicate=s-> s.getStatut()==2 || s.getStatut()==3 ; 
		return sujetService.getAll().stream()
				.filter(predicate).map(MapperImpl::fromSujet).collect(Collectors.toList());
	}
	
	@GetMapping("/sujetsSupp")
	public List<SujetDTO> sujetsSupp() {
		Predicate<Sujet> predicate=s-> s.getStatut()==0; 
		return sujetService.getAll().stream()
				.filter(predicate).map(MapperImpl::fromSujet).collect(Collectors.toList());
	}

	@GetMapping("/sujets/user/{username}")
	List<Sujet> findSujetsByUsername(@PathVariable("username") String username) {
		Predicate<Sujet> predicate=s-> s.getStatut()==2 || s.getStatut()==3 ; 
		return sujetService.findSujetsByUsername(username).stream()
				.filter(predicate).collect(Collectors.toList());
	}

	@GetMapping("/documentProjetUtilisateurs/{idSujet}/{idUser}")
	public <T> List<Optional<T>> findAllDocumentProjetUtilisateurs(@PathVariable("idSujet") Long idSujet,
			@PathVariable("idUser") String idUser) {
		return sujetService.findAllDocumentProjetUtilisateurs(idSujet, idUser);
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
		
		sujet=sujetService.save(sujet);
		
	}
	@PatchMapping("/update_status_sujets")
	public void updateSujet2(@RequestBody Sujet sujet) {
		Sujet existingSujet=sujetService.getSujetById(sujet.getId().intValue());
		existingSujet.setStatut(sujet.getStatut());
		sujetService.save(existingSujet);
		if(sujet.getStatut()==0)
		{
		List<String> emails= existingSujet.getUtilisateurs().stream().map(u->u.getEmail()).collect(Collectors.toList());
		for(String mail:emails)
		{
			sujetService.sendMail(mail, existingSujet);
		}
		}
	}
	
}
