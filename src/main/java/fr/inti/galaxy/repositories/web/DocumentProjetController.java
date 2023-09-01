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
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.DocumentProjetDTO;
import fr.inti.galaxy.entities.DocumentProjet;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.DocumentProjetService;

@RestController
@CrossOrigin("*")
public class DocumentProjetController {
	@Autowired
	DocumentProjetService documentProjetService;

	@GetMapping("/documentProjets")
	public List<DocumentProjetDTO> documentProjets() {
		return documentProjetService.getAll().stream().map(MapperImpl::fromDocumentProjet).collect(Collectors.toList());
	}

	@GetMapping("/documentProjets/{documentProjetId}")
	public DocumentProjetDTO getDocumentProjet(@PathVariable(name = "documentProjetId") Integer documentProjetId) {
		return MapperImpl.fromDocumentProjet(documentProjetService.getDocumentProjetById(documentProjetId));
	}

	@PostMapping("/documentProjets")
	public DocumentProjet addNewDocumentProjet(@RequestBody DocumentProjet formation) {
		return documentProjetService.save(formation);
	}

	@DeleteMapping("/documentProjets/{documentProjetId}")
	public void deleteDocumentProjet(@PathVariable("documentProjetId") int documentProjetId) {
		documentProjetService.delete(documentProjetId);
	}

	@PutMapping("/documentProjets")
	public void updateDocumentProjet(@RequestBody DocumentProjet documentProjet) {
		documentProjetService.save(documentProjet);
	}
}
