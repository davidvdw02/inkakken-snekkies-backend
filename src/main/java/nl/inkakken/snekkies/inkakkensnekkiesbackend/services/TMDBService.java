package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

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

}
