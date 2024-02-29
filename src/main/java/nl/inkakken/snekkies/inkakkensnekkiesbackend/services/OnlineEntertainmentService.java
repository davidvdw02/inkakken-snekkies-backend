package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;
import java.util.UUID;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Entertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Genre;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.MovieNight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.repositories.OnlineEntertainmentRepository;

@Service
public class OnlineEntertainmentService {

      private final Logger logger = LoggerFactory.getLogger(OnlineEntertainmentService.class);
    
    private final OnlineEntertainmentRepository onlineEntertainmentRepository;
    private final TMDBService tmdbService;
    private final GenreService genreService;

    public OnlineEntertainmentService(OnlineEntertainmentRepository onlineEntertainmentRepository, TMDBService tmdbService, GenreService genreService) {
        this.onlineEntertainmentRepository = onlineEntertainmentRepository;
        this.genreService = genreService;
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

    public OnlineEntertainment getMovieById(int id) {
        return this.tmdbService.getMovieById(id);
    }

    public List<PersonCast> getMovieCastById(int id) {
        return this.tmdbService.getMovieCastById(id);
    }

    public TvResultsPage getSeriesResultsPage(String query, int page) {
        return this.tmdbService.SearchSeriesWithPage(query, page);
    }

    public TvResultsPage getSeriesResults(String query) {
        return this.tmdbService.SearchSeries(query);
    }

    public TvSeries getSerieById(int id) {
        return this.tmdbService.getSerieById(id);
    }

    public List<PersonCast> getSerieCastById(int id) {
        return this.tmdbService.getSerieCastById(id);
    }

    public TvSeason getSeason(int serieId, int seasonNumber) {
        return this.tmdbService.getSeason(serieId, seasonNumber);
    }

   public OnlineEntertainment getFormattedTvShow(int serieId, int seasonnumber, int episodeNumber){
        return this.tmdbService.getFormattedTvShow(serieId, seasonnumber, episodeNumber);
    }

    public OnlineEntertainment getOnlineEntertainmentById(UUID id){
        return this.onlineEntertainmentRepository.findById(id).orElseThrow(() -> {
            logger.error("OnlineEntertainment not found with id: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OnlineEntertainment not found with id: " + id);
        });
    }

    public OnlineEntertainment createOnlineEntertainment(OnlineEntertainment onlineEntertainment){
       
        for(Genre genre : onlineEntertainment.getGenres()){
            if(this.genreService.getGenreByIdWithoutException(genre.getId()) == null){
                logger.debug("Creating genre");
                genre = this.genreService.createGenre(genre);
            }
        }
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
