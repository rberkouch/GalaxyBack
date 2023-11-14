package fr.inti.galaxy.repositories.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.entities.Avis;
import fr.inti.galaxy.repositories.services.AvisService;

@RestController
@CrossOrigin("*")
public class AvisController {
	
	@Autowired
	AvisService aviService;
	
	@PostMapping("/avis")
	public Avis addAvis(@RequestBody Avis avis) {
		return aviService.save(avis);
	}
	
	@GetMapping("/avis/{idLivrable}")
	public List<Avis> getAllAvis(@PathVariable("idLivrable") int idLivrable)
	{
		return aviService.findByLivrable_id(idLivrable);
	}
	
	@DeleteMapping("/avis/{idAvis}")
	public void deteleAvis(@PathVariable("idAvis") Long idAvis)
	{
		aviService.delete(idAvis);
	}
	
	
	
	
}
