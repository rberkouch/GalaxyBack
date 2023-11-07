package fr.inti.galaxy.repositories.services;

import java.util.List;

import fr.inti.galaxy.entities.Notification;



public interface NotificationService {
	
	public List<Notification> getAll();

	public Notification getNotificationById(Integer i);

	public Notification save(Notification notification);
	
	public void delete(Integer id);
	

}
