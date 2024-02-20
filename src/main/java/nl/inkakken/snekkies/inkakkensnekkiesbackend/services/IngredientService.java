package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Ingredient;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.IngredientRepository;

@Service
public class IngredientService {


    private final Logger logger = LoggerFactory.getLogger(IngredientService.class);
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getIngredientById(UUID id) {
      return this.ingredientRepository.findById(id).orElseThrow(() -> {
            logger.error("Ingredient not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + id);
        });
    }

    public List<Ingredient> getAllIngredients() {
        logger.debug("Getting all ingredients");
        return this.ingredientRepository.findAll();
    }
    
    public Ingredient postNewIngredient(Ingredient newIngredientRequest) {
        logger.debug("Creating ingredient");
        Optional<Ingredient> existingIngredient = this.ingredientRepository.findByName(newIngredientRequest.getName());
       if(existingIngredient.isEmpty()){
        return this.ingredientRepository.save(newIngredientRequest);
       }
       return existingIngredient.get();
    }
    
}
