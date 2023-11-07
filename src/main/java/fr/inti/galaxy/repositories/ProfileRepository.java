package fr.inti.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.inti.galaxy.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
