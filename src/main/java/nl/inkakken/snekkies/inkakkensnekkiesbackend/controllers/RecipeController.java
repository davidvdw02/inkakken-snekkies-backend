package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineRecipe;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Recipe;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable UUID id){
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
        Recipe newRecipe = recipeService.saveRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
    }
    @PutMapping("path/{id}")
    public String putRecipe(@PathVariable String id, @RequestBody Recipe recipe) {
        return recipeService.putRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable UUID id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/onlinerecipe/{movieNightId}")
    public OnlineRecipe getOnlineRecipe(@PathVariable UUID movieNightId) {
        return recipeService.getOnlineRecipe(movieNightId);
    }
    

    @GetMapping("/recipe/{onlineRecipeId}")
    public Recipe getMethodName(@PathVariable UUID onlineRecipeId) {
        return recipeService.getRecipyByOnlineRecipeId(onlineRecipeId);
    }
    




}
