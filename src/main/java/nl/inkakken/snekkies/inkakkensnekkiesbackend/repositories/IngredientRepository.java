package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Optional<Ingredient> findByName(String name);
}
