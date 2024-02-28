package nl.inkakken.snekkies.inkakkensnekkiesbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.services.OnlineEntertainmentService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/onlineentertainment")
public class OnlineEntertainmentController {
    
    private final OnlineEntertainmentService onlineEntertainmentService;

    @Autowired
    public OnlineEntertainmentController(OnlineEntertainmentService onlineEntertainmentService) {
        this.onlineEntertainmentService = onlineEntertainmentService;
    }


    @GetMapping
    public ResponseEntity<List<OnlineEntertainment>> getAllOnlineEntertainment() {
        List<OnlineEntertainment> onlineEntertainment = onlineEntertainmentService.getAllOnlineEntertainment();
        return ResponseEntity.ok(onlineEntertainment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OnlineEntertainment> getOnlineEntertainmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(onlineEntertainmentService.getOnlineEntertainmentById(id));
    }

    @PostMapping
    public ResponseEntity<OnlineEntertainment> createOnlineEntertainment(@PathVariable OnlineEntertainment onlineEntertainment) {
        OnlineEntertainment createdOnlineEntertainment = onlineEntertainmentService.createOnlineEntertainment(onlineEntertainment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOnlineEntertainment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnlineEntertainment(@PathVariable UUID id) {
        onlineEntertainmentService.deleteOnlineEntertainment(id);
        return ResponseEntity.noContent().build();
    }

   @PutMapping("/{id}")
    public ResponseEntity<Void> putOnlineEntertainment(@PathVariable UUID id, @PathVariable OnlineEntertainment onlineEntertainment) {
        this.onlineEntertainmentService.putOnlineEntertainment(id, onlineEntertainment);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movienight/{id}")
    public ResponseEntity<OnlineEntertainment> getOnlineEntertainmentIdByMovieNightId(@PathVariable UUID id) {
        return ResponseEntity.ok(onlineEntertainmentService.getOnlineEntertainmentIdByMovieNightId(id));
    }
    @GetMapping("/movie/query/{query}")
    public ResponseEntity<MovieResultsPage> queryTMDB(@PathVariable String query) {
        return ResponseEntity.ok(onlineEntertainmentService.getMovieResults(query));
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDb> getMovieById(@PathVariable int id) {
        return ResponseEntity.ok(onlineEntertainmentService.getMovieById(id));
    }

    @GetMapping("/movie/{id}/cast")
    public ResponseEntity<List<PersonCast>> getCastById(@PathVariable int id) {
        return ResponseEntity.ok(onlineEntertainmentService.getMovieCastById(id));
    }

    @GetMapping("/serie/query/{query}")
    public ResponseEntity<TvResultsPage> querySeriesTMDB(@PathVariable String query) {
        return ResponseEntity.ok(onlineEntertainmentService.getSeriesResults(query));
    }

    @GetMapping("/serie/{id}")
    public ResponseEntity<TvSeries> getSeriesById(@PathVariable int id) {
        return ResponseEntity.ok(onlineEntertainmentService.getSerieById(id));
    }
    @GetMapping("/serie/{id}/cast")
    public ResponseEntity<List<PersonCast>> getSeriesCastById(@PathVariable int id) {
        return ResponseEntity.ok(onlineEntertainmentService.getSerieCastById(id));
    }
    @GetMapping("/serie/{id}/season/{seasonNumber}")
    public ResponseEntity<TvSeason> getSeason(@PathVariable int id, @PathVariable int seasonNumber) {
        return ResponseEntity.ok(onlineEntertainmentService.getSeason(id, seasonNumber));
    }


    

}
