package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
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

    private int duration;

    @ManyToMany()
    @JoinTable(name = "ingredient_online_recipe", joinColumns = {
            @JoinColumn(name = "recipe_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ingredient_id")
    })
    private List<Ingredient> ingredients;
}
