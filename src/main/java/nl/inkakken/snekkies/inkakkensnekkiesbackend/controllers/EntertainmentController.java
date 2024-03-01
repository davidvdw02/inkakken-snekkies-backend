package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Entertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.EntertainmentService;

@RestController
@RequestMapping("/entertainment")
public class EntertainmentController {

    private final EntertainmentService entertainmentService;
    
    public EntertainmentController(EntertainmentService entertainmentService) {
        this.entertainmentService = entertainmentService;
    }


    @GetMapping("/movienight/{movieNightId}")
    public ResponseEntity<List<Entertainment>> getEntertainmentByMovieNightId(@PathVariable UUID movieNightId) {
        return ResponseEntity.ok(entertainmentService.getEntertainmentByMovieNightId(movieNightId));
    }

    @PostMapping()
    public ResponseEntity<Entertainment> createEntertainment( @RequestBody Entertainment entertainment) {
        return ResponseEntity.ok(entertainmentService.createEntertainment(entertainment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entertainment> getEntertainmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(entertainmentService.getEntertainmentById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Entertainment> updateEntertainment(@PathVariable UUID id, @RequestBody Entertainment entertainment) {
        return ResponseEntity.ok(entertainmentService.updateEntertainment(id, entertainment));
    }

}
