package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Recipe;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.RecipeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {

    private final Logger logger = LoggerFactory.getLogger(OnlineRecipeService.class);

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipeById(UUID id) {
        logger.debug("Getting recipe with id: " + id);
        return recipeRepository.findById(id).orElseThrow(() -> {
            logger.error("Recipe not found with ID: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found with ID: " + id);
        });
    }

    public List<Recipe> getAllRecipes() {
        logger.debug("Getting all recipes");
        return recipeRepository.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        logger.debug("Saving recipe");
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(UUID id) {
        logger.debug("Deleting recipe with id: " + id);
        recipeRepository.deleteById(id);
    }

}
