package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.MovieNightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movienight")
public class MovieNightController {
    private final MovieNightService movieNightService;

    @Autowired
    public MovieNightController(MovieNightService movieNightService) {
        this.movieNightService = movieNightService;
    }

    @GetMapping
    public ResponseEntity<List<MovieNight>> getAllMovieNights() {
        List<MovieNight> movieNights = movieNightService.getAllMovieNights();
        return ResponseEntity.ok(movieNights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieNight> getMovieNightById(@PathVariable UUID id) {
        return ResponseEntity.ok(movieNightService.getMovieNightById(id));
    }

    @PostMapping
    public ResponseEntity<MovieNight> createMovieNight(@RequestBody MovieNight movieNight) {
        MovieNight createdMovieNight = movieNightService.createMovieNight(movieNight);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovieNight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieNight(@PathVariable UUID id) {
        movieNightService.deleteMovieNight(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putMovieNight(@PathVariable UUID id, @RequestBody MovieNight movieNight) {
        this.movieNightService.putMovieNight(id, movieNight);
        return ResponseEntity.noContent().build();
    }
}
