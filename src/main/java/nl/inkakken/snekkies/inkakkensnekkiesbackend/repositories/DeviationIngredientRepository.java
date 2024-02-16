package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.DeviatedIngredient;

public interface DeviationIngredientRepository extends JpaRepository<DeviatedIngredient, UUID>{
    
}
