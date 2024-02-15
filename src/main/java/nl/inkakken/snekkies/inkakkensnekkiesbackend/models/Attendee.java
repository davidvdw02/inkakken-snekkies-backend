package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "attendee")
public class Attendee {

    @Id
    @GeneratedValue()
    private UUID id;

    private String name;
}
