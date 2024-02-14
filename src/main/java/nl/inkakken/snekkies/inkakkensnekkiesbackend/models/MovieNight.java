package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie_night")
public class MovieNight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Date date;

    private String location;

    @ManyToMany()
    @JoinTable(name = "movie_night_attendee", joinColumns = {
            @JoinColumn(name = "movie_night_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "attendee_id")
    })
    private List<Attendee> attendees;

}
