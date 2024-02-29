package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.tv.TvEpisode;
import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.model.tv.TvSeries;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.Genre;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.OnlineEntertainment;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TMDBService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TMDBService() {
    }

    public MovieResultsPage searchMovies(String query) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchMovie(query, null, null, true, null);
    }

    public MovieResultsPage searchMoviesWithPage(String query, int page) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchMovie(query, null, null, true, page);
    }

    public OnlineEntertainment getMovieById(int id) {
        MovieDb movie = new TmdbApi(apiKey).getMovies().getMovie(id, "en");
        return refactorMovieToOnlineENtertainment(movie);
    }

    public List<PersonCast> getMovieCastById(int id) {
        return new TmdbApi(apiKey).getMovies().getCredits(id).getCast();
    }

    public TvResultsPage SearchSeries(String query) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchTv(query, null, null, true, null);
    }

    public TvResultsPage SearchSeriesWithPage(String query, int page) {
        TmdbSearch search = new TmdbApi(apiKey).getSearch();
        return search.searchTv(query, null, null, true, page);
    }

    public TvSeries getSerieById(int id) {
        return new TmdbApi(apiKey).getTvSeries().getSeries(id, "en");
    }

    public List<PersonCast> getSerieCastById(int id) {
        return new TmdbApi(apiKey).getTvSeries().getSeries(id, "en").getCredits().getCast();
    }

    public TvSeason getSeason(int serieId, int seasonNumber) {
        {
            return new TmdbApi(apiKey).getTvSeasons().getSeason(serieId, seasonNumber, "en");
        }
    }
    private TvEpisode getEpisode(int serieId, int seasonNumber, int episodeNumber) {
        return new TmdbApi(apiKey).getTvEpisodes().getEpisode(serieId, seasonNumber, episodeNumber, "en");
        // from episode // episodeTitle, releaseDate, season, episode, stillImagePath, rating
        //from season, episode + poster path
        //from serie, title 
    }

    public OnlineEntertainment getFormattedTvShow(int serieId, int seasonnumber, int episodeNumber){
        TvEpisode episode = getEpisode(serieId, seasonnumber, episodeNumber);
        TvSeries serie = getSerieById(serieId);
        return new OnlineEntertainment(episode.getId(), serie.getName(), episode.getVoteAverage(), formatStringToDate(episode.getAirDate()), serie.getPosterPath(), seasonnumber, episodeNumber, episode.getName(), episode.getStillPath(), refactorGenres(serie.getGenres()));
    }

    private OnlineEntertainment refactorMovieToOnlineENtertainment(MovieDb movie) {
        return new OnlineEntertainment(movie.getId(), movie.getTitle(),
                movie.getVoteAverage(), formatStringToDate(movie.getReleaseDate()), movie.getPosterPath(),
                movie.getRuntime(), refactorGenres(movie.getGenres()));
    }

    private Date formatStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = null;
        try {
            releaseDate = formatter.parse(dateString);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing date");
        }
        return releaseDate;
    }

    private ArrayList<Genre> refactorGenres(List<info.movito.themoviedbapi.model.Genre> genres) {
        ArrayList<Genre> genresList = new ArrayList<>();
        for (info.movito.themoviedbapi.model.Genre genre : genres) {
            genresList.add(new Genre(genre.getId(), genre.getName()));
        }
        return genresList;
    }
}
