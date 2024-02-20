package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Ingredient;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.IngredientRepository;

@Service
public class IngredientService {


    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    public void createIngredient(Ingredient ingredient) {
        // Implementation goes here
    }

    public Ingredient getIngredientById(UUID id) {
        // Implementation goes here
        return null;
    }

    public List<Ingredient> getAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    public void updateIngredient(Ingredient ingredient) {
        // Implementation goes here
    }

    public void deleteIngredient(int id) {
        // Implementation goes here
    }
    public Ingredient postNewIngredient(Ingredient newIngredientRequest) {
        Optional<Ingredient> existingIngredient = this.ingredientRepository.findByName(newIngredientRequest.getName());
       if(existingIngredient.isEmpty()){
        return this.ingredientRepository.save(newIngredientRequest);
       }
       return existingIngredient.get();
    }
    
}
