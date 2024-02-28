package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;

@Service
public class TMDBService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TMDBService() {
    }


    public MovieResultsPage searchMovies(String query) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return  search.searchMovie(query, null, null, true, null);
    }

    public MovieResultsPage searchMoviesWithPage(String query, int page) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return  search.searchMovie(query, null, null, true, page);
    }

    public MovieDb getMovieById(int id) {
        return new TmdbApi(apiKey).getMovies().getMovie(id, "en");
    }
    public List<PersonCast> getMovieCastById(int id){
        return new TmdbApi(apiKey).getMovies().getCredits(id).getCast();
    }

    public TvResultsPage SearchSeries(String query){
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchTv(query, null, null, true, null);
    }
    public TvResultsPage SearchSeriesWithPage(String query, int page){
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchTv(query, null, null, true, page);
    }
    public TvSeries getSerieById(int id){
        return new TmdbApi(apiKey).getTvSeries().getSeries(id, "en");
    }
    public List<PersonCast> getSerieCastById(int id){
        return new TmdbApi(apiKey).getTvSeries().getSeries(id, "en").getCredits().getCast();
    }
    public TvSeason getSeason(int serieId, int seasonNumber){ {
        return new TmdbApi(apiKey).getTvSeasons().getSeason(serieId, seasonNumber, "en");
    }
}
}
