package fr.inti.galaxy.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.inti.galaxy.entities.Document;
import fr.inti.galaxy.entities.Utilisateur;

public class TestMapperImpl {

	public static void main(String[] args) {
		Utilisateur user1 = new Utilisateur();
		user1.setFirstName("Réda");
		
		Document document = new Document();
		document.setDocumentName("Avis d'impôts");
		document.setDocumentUrl("C://utilisateurs/document");
		document.setId(45l);
		document.setOperationDate(new Date());
		List<Utilisateur> users = new ArrayList<>();
		users.add(user1);
		document.setUtilisateurs(users);
		System.out.println(document);
		System.out.println("******************************************");
		System.out.println(MapperImpl.fromDocument(document));
	}

}
