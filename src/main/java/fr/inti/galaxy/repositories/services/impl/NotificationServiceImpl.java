package fr.inti.galaxy.repositories.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.inti.galaxy.entities.Notification;
import fr.inti.galaxy.repositories.NotificationRepository;
import fr.inti.galaxy.repositories.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public List<Notification> getAll() {
		
		return notificationRepository.findAll();
	}

	@Override
	public Notification getNotificationById(Integer i) {
		
		return notificationRepository.findById(i).get();
	}

	@Override
	public Notification save(Notification notification) {
		// TODO Auto-generated method stub
		return notificationRepository.save(notification);
	}

	@Override
	public void delete(Integer id) {
		
		notificationRepository.deleteById(id);
	}

}
