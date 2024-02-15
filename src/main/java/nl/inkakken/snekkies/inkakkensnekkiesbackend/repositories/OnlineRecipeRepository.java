package nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories;


import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OnlineRecipeRepository extends JpaRepository<OnlineRecipe, UUID> {
}
