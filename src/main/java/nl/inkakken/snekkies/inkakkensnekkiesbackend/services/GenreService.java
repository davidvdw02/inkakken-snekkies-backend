package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Genre;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.GenreRepository;


@Service
public class GenreService {
            private final Logger logger = LoggerFactory.getLogger(GenreService.class);
    
     private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(){
        logger.debug("Getting all genres");
        return this.genreRepository.findAll();
    }
    public Genre getGenreById(UUID id){
        return this.genreRepository.findById(id).orElseThrow(() -> {
            logger.error("Genre not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found with id: " + id);
        });
    }

    public Genre createGenre(Genre genre){
        logger.debug("Creating genre");
        return this.genreRepository.save(genre);
    }

    public void deleteGenre(UUID id){
        logger.debug("Deleting genre with id: " + id);
        this.genreRepository.deleteById(id);
    }
    
    public void putGenre(UUID id, Genre genre){
        logger.debug("getting genre with id: " + id);
        Genre existingGenre = this.getGenreById(id);
        logger.debug("updating genre with id: " + id);
        this.genreRepository.save(existingGenre);
    }                                              
}