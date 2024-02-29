package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.inkakken.snekkies.inkakkensnekkiesbackend.enums.EntertainmentType;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "entertainment")
public class Entertainment {

    @Id
    @GeneratedValue()
    private UUID id;

    private float rating;

    private EntertainmentType type;

    private UUID onlineEntertainmentId;

    private UUID movieNightId;

    @ManyToMany()
    @JoinTable(name = "entertainment_snekkie", joinColumns = {
            @JoinColumn(name = "entertainment_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "snekkie_id")
    })
    private List<Snekkie> snekkies;
}
