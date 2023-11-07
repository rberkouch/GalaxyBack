package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.function.Predicate;
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

import fr.inti.galaxy.dtos.SujetDTO;
import fr.inti.galaxy.entities.Avis;
import fr.inti.galaxy.entities.Notification;
import fr.inti.galaxy.entities.Sujet;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.services.AvisService;
import fr.inti.galaxy.repositories.services.NotificationService;

@RestController
@CrossOrigin("*")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/notifications")
	public Notification addNotification(@RequestBody Notification notification) {
		return notificationService.save(notification);
	}
	
	@DeleteMapping("/notifications/{idNotification}")
	public void deteleNotification(@PathVariable("idNotification") Integer idNotification)
	{
		notificationService.delete(idNotification);
	}
	
	@GetMapping("/notifications")
	public List<Notification> notifications() {
		
		return notificationService.getAll();
	}
	
	@GetMapping("/notifications/non-traitées")
	public List<Notification> notificationsNonTraitées() {
		
		Predicate<Notification> predicate=n-> n.getStatut()==0;
	    
	    return notificationService.getAll().stream().filter(predicate).toList();
	}
	
	@PutMapping("/notifications")
	public void updateNotification(@RequestBody Notification notifications) {
		notificationService.save(notifications);
	}
	
	

}
