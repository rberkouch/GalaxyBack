package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inti.galaxy.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
