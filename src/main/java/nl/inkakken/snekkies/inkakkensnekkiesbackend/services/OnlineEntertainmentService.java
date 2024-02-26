package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Entertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import info.movito.themoviedbapi.model.core.MovieResultsPage;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.OnlineEntertainmentRepository;

@Service
public class OnlineEntertainmentService {

      private final Logger logger = LoggerFactory.getLogger(OnlineEntertainmentService.class);
    
    private final OnlineEntertainmentRepository onlineEntertainmentRepository;
    private final TMDBService tmdbService;

    @Autowired
    private EntertainmentService entertainmentService;

    public OnlineEntertainmentService(OnlineEntertainmentRepository onlineEntertainmentRepository, TMDBService tmdbService) {
        this.onlineEntertainmentRepository = onlineEntertainmentRepository;
        this.tmdbService = tmdbService;
    }


   public List<OnlineEntertainment> getAllOnlineEntertainment(){
        logger.debug("Getting all online entertainment");
         return this.onlineEntertainmentRepository.findAll();

    }
    public MovieResultsPage getMovieResultsPage(String query, int page) {
        return this.tmdbService.searchMoviesWithPage(query, page);
    }
    public MovieResultsPage getMovieResults(String query) {
        return this.tmdbService.searchMovies(query);
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

    public OnlineEntertainment getOnlineEntertainmentIdByMovieNightId(UUID id) {
        Entertainment entertainment = this.entertainmentService.getEntertainmentByMovieNightId(id);


        return this.onlineEntertainmentRepository.findById(entertainment.getOnlineEntertainmentId())
                .orElseThrow(() -> {
                    logger.error("Online entertainment not found with entertainment id: " + entertainment.getOnlineEntertainmentId());
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Online entertainment not found with entertainment id: " + entertainment.getOnlineEntertainmentId());
                });
    }
}
