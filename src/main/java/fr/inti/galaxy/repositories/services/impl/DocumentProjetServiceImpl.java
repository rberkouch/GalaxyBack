package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.inti.galaxy.entities.DocumentProjet;
import fr.inti.galaxy.repositories.DocumentProjetRepository;
import fr.inti.galaxy.repositories.services.DocumentProjetService;

@Service
@Transactional
public class DocumentProjetServiceImpl implements DocumentProjetService {
	@Autowired
	private DocumentProjetRepository documentProjetRepository;
	
	public List<DocumentProjet> getAll(){
		return documentProjetRepository.findAll();
	}
	
	@Override
	public DocumentProjet getDocumentProjetById(Integer id) {
		return documentProjetRepository.findById(id).get();
	}
	
	@Override
	public DocumentProjet save(DocumentProjet formation) {
		
		return documentProjetRepository.save(formation);
	}

	@Override
	public void delete(int id) {
		documentProjetRepository.delete(documentProjetRepository.getReferenceById(id));		
	}
}
