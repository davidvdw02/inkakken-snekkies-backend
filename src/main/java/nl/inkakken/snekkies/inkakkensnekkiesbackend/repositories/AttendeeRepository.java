package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, UUID>{
    Optional<Attendee> findByName(String name);

}
