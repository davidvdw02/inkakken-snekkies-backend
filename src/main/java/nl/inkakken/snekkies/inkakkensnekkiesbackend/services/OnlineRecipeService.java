package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineRecipe;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.OnlineRecipeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class OnlineRecipeService {

    private final Logger logger = LoggerFactory.getLogger(OnlineRecipeService.class);

    private final OnlineRecipeRepository onlineRecipeRepository;

    @Autowired
    public OnlineRecipeService(OnlineRecipeRepository onlineRecipeRepository) {
        this.onlineRecipeRepository = onlineRecipeRepository;
    }

    public List<OnlineRecipe> getAllRecipes() {
        logger.debug("Getting all online recipes");
        return onlineRecipeRepository.findAll();
    }

    public OnlineRecipe getOnlineRecipeById(UUID id) {
        logger.debug("Getting online recipe with id: " + id);
        OnlineRecipe onlineRecipe = onlineRecipeRepository.findById(id).orElseThrow(() -> {
            logger.error("OnlineRecipe not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OnlineRecipe not found with id: " + id);
        });
        return onlineRecipe;
    }

    public OnlineRecipe addRecipe(OnlineRecipe recipe) {
        logger.debug("Adding online recipe");
        return onlineRecipeRepository.save(recipe);
    }

    public void deleteRecipe(UUID id) {
        logger.debug("Deleting online recipe with id: " + id);
        onlineRecipeRepository.deleteById(id);
    }

}
