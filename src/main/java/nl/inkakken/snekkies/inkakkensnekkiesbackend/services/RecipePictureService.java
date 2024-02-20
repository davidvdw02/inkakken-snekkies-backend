package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.dto.NewPictureDTO;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.RecipePicture;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.RecipePictureRepository;

@Service
public class RecipePictureService {
    
      private final Logger logger = LoggerFactory.getLogger(RecipePictureService.class);
      private final RecipePictureRepository recipePictureRepository;
      private final RecipeService recipeService;

      @Autowired
      public RecipePictureService(RecipePictureRepository recipePictureRepository, RecipeService recipeService) {
          this.recipePictureRepository = recipePictureRepository;
            this.recipeService = recipeService;

      }
    public RecipePicture postNewPicture(NewPictureDTO newPictureDTO){
        logger.debug("Posting new picture");
        String filepath = savePicture(newPictureDTO.getPicture());
        RecipePicture recipePicture = new RecipePicture();
        recipePicture.setPictureReference(filepath);
        recipePicture.setRecipe(recipeService.getRecipeById(newPictureDTO.getRecipeId()));
        return recipePictureRepository.save(recipePicture);
    }


    private String savePicture(String picture) {
        try {
            String extension = picture.substring(picture.indexOf('/') + 1, picture.indexOf(';'));
            String fileName = UUID.randomUUID().toString() + "." + extension;
    
            String base64Data = picture.replaceAll("^data:[^;]+;base64,", "");
            byte[] imageBytes = Base64.getDecoder().decode(base64Data);
            String filePath = "src/main/resources/static/" + fileName;
            
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(imageBytes);
            outputStream.close();
            
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save picture");
        }
    }
    
}
