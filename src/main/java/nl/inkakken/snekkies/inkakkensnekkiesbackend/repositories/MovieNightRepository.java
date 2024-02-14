package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;


import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieNightRepository extends JpaRepository<MovieNight, UUID> {
}
