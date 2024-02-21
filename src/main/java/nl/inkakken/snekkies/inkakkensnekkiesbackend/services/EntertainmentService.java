package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Entertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.EntertainmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class EntertainmentService {

    private final Logger logger = LoggerFactory.getLogger(EntertainmentService.class);

    @Autowired
    private EntertainmentRepository entertainmentRepository;


    public Entertainment getEntertainmentByMovieNightId(UUID id) {
        return this.entertainmentRepository.findByMovieNightId(id).orElseThrow( () -> {
            logger.error("Entertainment not found with movie night id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entertainment not found with movie night id: " + id);
        });
    }

}
