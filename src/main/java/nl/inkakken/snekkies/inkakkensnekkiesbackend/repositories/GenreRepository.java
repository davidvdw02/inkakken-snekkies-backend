package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, UUID>{
    
}
