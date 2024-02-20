package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.DeviatedIngredient;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.DeviatedIngredientRepository;

@Service
public class DeviatedIngredientService {

    private final Logger logger = LoggerFactory.getLogger(DeviatedIngredientService.class);
    private final DeviatedIngredientRepository deviatedIngredientRepository;
    
    @Autowired
    public DeviatedIngredientService(DeviatedIngredientRepository deviatedIngredientRepository) {
         this.deviatedIngredientRepository = deviatedIngredientRepository;
    }
    

   
    public List<DeviatedIngredient> getAllDeviatedIngredients() {
        logger.debug("Getting all deviated ingredients");
        return deviatedIngredientRepository.findAll();
    }

    public DeviatedIngredient getDeviatedIngredientById(UUID id) {
        logger.debug("Getting deviated ingredient with id: " + id);
        DeviatedIngredient deviatedIngredient = deviatedIngredientRepository.findById(id).orElseThrow(() -> {
            logger.error("DeviatedIngredient not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DeviatedIngredient not found with id: " + id);
        });
        return deviatedIngredient;
    }

    public DeviatedIngredient createDeviatedIngredient(DeviatedIngredient deviatedIngredient) {
        logger.debug("Creating deviated ingredient");
        return deviatedIngredientRepository.save(deviatedIngredient);
    }

    public void deleteDeviatedIngredient(UUID id) {
        logger.debug("Deleting deviated ingredient with id: " + id);
        deviatedIngredientRepository.deleteById(id);
    }


}
