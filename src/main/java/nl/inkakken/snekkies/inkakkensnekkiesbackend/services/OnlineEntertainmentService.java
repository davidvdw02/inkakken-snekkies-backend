package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.OnlineEntertainmentRepository;

@Service
public class OnlineEntertainmentService {

      private final Logger logger = LoggerFactory.getLogger(OnlineEntertainmentService.class);
    
    private final OnlineEntertainmentRepository onlineEntertainmentRepository;

    public OnlineEntertainmentService(OnlineEntertainmentRepository onlineEntertainmentRepository) {
        this.onlineEntertainmentRepository = onlineEntertainmentRepository;
    }


   public List<OnlineEntertainment> getAllOnlineEntertainment(){
        logger.debug("Getting all online entertainment");
         return this.onlineEntertainmentRepository.findAll();
    }

    public OnlineEntertainment getOnlineEntertainmentById(UUID id){
        return this.onlineEntertainmentRepository.findById(id).orElseThrow(() -> {
            logger.error("OnlineEntertainment not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OnlineEntertainment not found with id: " + id);
        });
    }

    public OnlineEntertainment createOnlineEntertainment(OnlineEntertainment onlineEntertainment){
        logger.debug("Creating online entertainment");
        return this.onlineEntertainmentRepository.save(onlineEntertainment);
    }

    public void deleteOnlineEntertainment(UUID id){
        logger.debug("Deleting online entertainment with id: " + id);
        this.onlineEntertainmentRepository.deleteById(id);
    }

    public void putOnlineEntertainment(UUID id, OnlineEntertainment onlineEntertainment){
       logger.debug("getting online entertainment with id: " + id);
       OnlineEntertainment existingOnlineEntertainment = this.getOnlineEntertainmentById(id);
       logger.debug("updating online entertainment with id: " + id);
        this.onlineEntertainmentRepository.save(existingOnlineEntertainment);
    }

}
