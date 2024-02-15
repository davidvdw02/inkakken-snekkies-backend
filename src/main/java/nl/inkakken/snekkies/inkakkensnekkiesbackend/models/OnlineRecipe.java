package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "online_recipe")
public class OnlineRecipe {

    @Id
    @GeneratedValue()
    private UUID id;

    private String name;

    private String link;

    private String ingredients;

    private int duration;
}
