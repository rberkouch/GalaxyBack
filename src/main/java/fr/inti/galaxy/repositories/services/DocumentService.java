package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Document;

public interface DocumentService {

	public List<Document> getAll();
	public Document save(Document document);
}
