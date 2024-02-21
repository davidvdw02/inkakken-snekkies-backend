package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "online_entertainment")
public class OnlineEntertainment {

    @Id
    @GeneratedValue()
    private UUID id;

    private String name;

    private int duration;

    private float rating;

    @Nullable
    private Integer episode;

    private String link;

    @ManyToMany()
    @JoinTable(name = "genre_online_entertainment", joinColumns = {
            @JoinColumn(name = "online_entertainment_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "genre_id")
    })
    private List<Genre> genres;

}
