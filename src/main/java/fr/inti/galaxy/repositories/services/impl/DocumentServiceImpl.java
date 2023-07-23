package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Document;
import fr.inti.galaxy.repositories.DocumentRepository;
import fr.inti.galaxy.repositories.services.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	public List<Document> getAll(){
		return documentRepository.findAll();
	}
	
	@Override
	public Document save(Document document) {
		return documentRepository.save(document);
	}
}


