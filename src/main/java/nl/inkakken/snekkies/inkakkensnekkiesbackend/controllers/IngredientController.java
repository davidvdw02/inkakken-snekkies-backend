package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Ingredient;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.IngredientService;
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> postNewIngredient(@RequestBody Ingredient newIngredientRequest){
        return ResponseEntity.ok(this.ingredientService.postNewIngredient(newIngredientRequest));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok(this.ingredientService.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID id){
        return ResponseEntity.ok(this.ingredientService.getIngredientById(id));
    }
    
}
