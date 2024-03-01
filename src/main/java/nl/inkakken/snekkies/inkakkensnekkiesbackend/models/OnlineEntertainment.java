package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "online_entertainment")
public class OnlineEntertainment {

    @Id
    @GeneratedValue()
    private UUID id;

    private int tmdbId;

    @Nullable
    private int tmdbEpisodeId;

    private String title;

    private float rating;

    private Date releaseDate;

    private String posterPath;

    @Nullable
    private Integer season;

    @Nullable
    private Integer episode;
    
    @Nullable
    private String episodeTitle;

    @Nullable
    private String stillImagePath;

    @Nullable
    private int duration;

    @ManyToMany()
    @JoinTable(name = "genre_online_entertainment", joinColumns = {
            @JoinColumn(name = "online_entertainment_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "genre_id")
    })
    private List<Genre> genres;

    public OnlineEntertainment(int tmdbId, int tmdbEpisodeId, String title, float rating, Date releaseDate, String posterPath, Integer season, Integer episode,String episodeTitle , String stillImagePath, List<Genre> genres) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.season = season;
        this.episode = episode;
        this.stillImagePath = stillImagePath;
        this.genres = genres;
        this.tmdbEpisodeId = tmdbEpisodeId;
        this.episodeTitle = episodeTitle;
    }

    public OnlineEntertainment(int tmdbId, String title, float rating, Date releaseDate, String posterPath, int duration, List<Genre> genres) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.duration = duration;
        this.genres = genres;
    }
}
