package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineRecipe;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.OnlineRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/onlinerecipe")
public class OnlineRecipeController {

    private final OnlineRecipeService onlineRecipeService;

    @Autowired
    public OnlineRecipeController(OnlineRecipeService recipeService) {
        this.onlineRecipeService = recipeService;
    }
    @GetMapping
    public ResponseEntity<?> getAllOnlineRecipes() {
        return ResponseEntity.ok(onlineRecipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnlineRecipe> getOnlineRecipeById(@PathVariable UUID id) {
        return ResponseEntity.ok(onlineRecipeService.getRecipeById(id));
    }

    @PostMapping
    public ResponseEntity<OnlineRecipe> addOnlineRecipe(@RequestBody OnlineRecipe recipe) {
        return ResponseEntity.ok(onlineRecipeService.addRecipe(recipe));
    }

    @PostMapping("/recipe/{id}")
    public ResponseEntity<OnlineRecipe> addOnlineRecipeWithRecipe(@RequestBody OnlineRecipe onlineRecipe, @PathVariable UUID id) {
        return ResponseEntity.ok(onlineRecipeService.addOnlineRecipeWithRecipe(onlineRecipe, id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable UUID id) {
        onlineRecipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
