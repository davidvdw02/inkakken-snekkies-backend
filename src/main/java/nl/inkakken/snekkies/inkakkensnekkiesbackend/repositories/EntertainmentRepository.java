package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntertainmentRepository extends JpaRepository<Entertainment, UUID> {

    Optional<Entertainment> findByMovieNightId(UUID id);
}
