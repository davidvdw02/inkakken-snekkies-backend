package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.DeviatedIngredient;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.DeviatedIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deviatedingredient")
public class DeviatedIngredientController {

    private final DeviatedIngredientService deviatedIngredientService;

    @Autowired
    public DeviatedIngredientController(DeviatedIngredientService deviatedIngredientService) {
        this.deviatedIngredientService = deviatedIngredientService;
    }

    @GetMapping
    public ResponseEntity<List<DeviatedIngredient>> getAllDeviatedIngredients() {
        List<DeviatedIngredient> deviatedIngredients = deviatedIngredientService.getAllDeviatedIngredients();
        return ResponseEntity.ok(deviatedIngredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviatedIngredient> getDeviatedIngredientById(@PathVariable UUID id) {
        return ResponseEntity.ok(deviatedIngredientService.getDeviatedIngredientById(id));
    }

    @PostMapping
    public ResponseEntity<DeviatedIngredient> createDeviatedIngredient(@RequestBody DeviatedIngredient deviatedIngredient) {
        DeviatedIngredient createdDeviatedIngredient = deviatedIngredientService.createDeviatedIngredient(deviatedIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDeviatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviatedIngredient(@PathVariable UUID id) {
        deviatedIngredientService.deleteDeviatedIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
