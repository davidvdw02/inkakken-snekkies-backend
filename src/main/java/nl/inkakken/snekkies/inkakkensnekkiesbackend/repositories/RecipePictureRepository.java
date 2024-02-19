package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.RecipePicture;

public interface RecipePictureRepository extends JpaRepository<RecipePicture, UUID> {
    
}
