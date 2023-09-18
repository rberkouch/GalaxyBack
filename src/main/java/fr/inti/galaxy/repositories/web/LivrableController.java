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

import fr.inti.galaxy.dtos.LivrableDTO;
import fr.inti.galaxy.entities.Livrable;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.LivrableService;

@RestController
@CrossOrigin("*")
public class LivrableController {
	@Autowired
	LivrableService livrableService;

	@GetMapping("/livrables")
	public List<LivrableDTO> livrables() {
		return livrableService.getAll().stream().map(MapperImpl::fromLivrable).collect(Collectors.toList());
	}

	@GetMapping("/livrables/user/{username}")
	List<Livrable> findLivrablesParUsername(@PathVariable("username") String username) {
		return livrableService.findLivrablesParUsername(username);
	}

	@GetMapping("/livrables/{livrableId}")
	public LivrableDTO getLivrable(@PathVariable(name = "livrableId") Integer livrableId) {
		return MapperImpl.fromLivrable(livrableService.getLivrableById(livrableId));
	}

	@PostMapping("/livrables")
	public Livrable addNewLivrable(@RequestBody Livrable formation) {
		return livrableService.save(formation);
	}

	@DeleteMapping("/livrables/{livrableId}")
	public void deleteLivrable(@PathVariable("livrableId") int livrableId) {
		livrableService.delete(livrableId);
	}

	@PutMapping("/livrables")
	public void updateLivrable(@RequestBody Livrable livrable) {
		livrableService.save(livrable);
	}

	@GetMapping("/livrables/search")
	public List<Livrable> searchLivrable(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		return livrableService.searchLivrables("%" + keyword + "%");
	}
}
