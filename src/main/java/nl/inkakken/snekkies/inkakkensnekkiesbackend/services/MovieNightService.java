package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.MovieNightRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieNightService {

    private final Logger logger = LoggerFactory.getLogger(OnlineRecipeService.class);

    private final MovieNightRepository movieNightRepository;

    @Autowired
    public MovieNightService(MovieNightRepository movieNightRepository) {
        this.movieNightRepository = movieNightRepository;
    }

    public List<MovieNight> getAllMovieNights() {
        logger.debug("Getting all movie nights");
        return movieNightRepository.findAll();
    }

    public MovieNight getMovieNightById(UUID id) {
        logger.debug("Getting movie night with id: " + id);
        MovieNight movieNight = movieNightRepository.findById(id).orElseThrow(() -> {
            logger.error("MovieNight not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MovieNight not found with id: " + id);
        });
        return movieNight;
    }

    public MovieNight createMovieNight(MovieNight movieNight) {
        logger.debug("Creating movie night");
        return movieNightRepository.save(movieNight);
    }

    public void deleteMovieNight(UUID id) {
        logger.debug("Deleting movie night with id: " + id);
        movieNightRepository.deleteById(id);
    }

}
