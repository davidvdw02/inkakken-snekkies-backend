package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Genre;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(int id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(Genre genre) {
        Genre createdGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(int id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putGenre(int id, Genre genre) {
        this.genreService.putGenre(id, genre);
        return ResponseEntity.noContent().build();
    }
}
