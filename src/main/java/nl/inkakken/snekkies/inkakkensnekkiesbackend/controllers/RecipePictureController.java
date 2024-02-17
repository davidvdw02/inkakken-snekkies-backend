package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.RecipePicture;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.RecipePictureService;

@RequestMapping("/recipepicture")
@RestController
public class RecipePictureController {
    
    private final RecipePictureService recipePictureService;

    public RecipePictureController(RecipePictureService recipePictureService) {
        this.recipePictureService = recipePictureService;
    }
    @PostMapping
    public ResponseEntity<RecipePicture> postNewPicture(@RequestBody String picture){
       return ResponseEntity.ok(this.recipePictureService.postNewPicture(picture));
    }
}
