package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Attendee;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.MovieNightRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class MovieNightService {

    private final Logger logger = LoggerFactory.getLogger(MovieNightService.class);

    private final MovieNightRepository movieNightRepository;

    private final AttendeeService attendeeService;

    @Autowired
    public MovieNightService(MovieNightRepository movieNightRepository, AttendeeService attendeeService) {
        this.movieNightRepository = movieNightRepository;
        this.attendeeService = attendeeService;
    }

    public List<MovieNight> getAllMovieNights() {
        logger.debug("Getting all movie nights");
        return movieNightRepository.findAll();
    }

    public MovieNight getMovieNightById(UUID id) {
        logger.debug("Getting movie night with id: " + id);
        MovieNight movieNight = movieNightRepository.findById(id).orElseThrow(() -> {
            logger.error("Movie night not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie night not found with id: " + id);
        });
        return movieNight;
    }

    public MovieNight createMovieNight(MovieNight movieNight) {
        logger.debug("Creating movie night");

        movieNight = this.addStandardAttendees(movieNight);

        movieNight.setDate(new Date());

        return movieNightRepository.save(movieNight);
    }

    public MovieNight addStandardAttendees(MovieNight movieNight) {
        List<Attendee> standardAttendees = new ArrayList<>();

        standardAttendees.add(this.attendeeService.getAttendeeByName("David"));
        standardAttendees.add(this.attendeeService.getAttendeeByName("Niels"));
        standardAttendees.add(this.attendeeService.getAttendeeByName("Justin"));

        movieNight.setAttendees(standardAttendees);

        return movieNight;
    }

    public void deleteMovieNight(UUID id) {
        logger.debug("Deleting movie night with id: " + id);
        movieNightRepository.deleteById(id);
    }

    public Optional<MovieNight> checkIfMovieNightExists(UUID id) {
        Optional<MovieNight> movieNight = this.movieNightRepository.findById(id);
        return movieNight;
    }

    public void putMovieNight(UUID id, MovieNight movieNight) {
        logger.debug("Updating movie night with id: " + id);
        if (checkIfMovieNightExists(id).isEmpty()) {
            logger.error("Movie night not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie night not found with id: " + id);
        }
        this.movieNightRepository.save(movieNight);
    }
}
