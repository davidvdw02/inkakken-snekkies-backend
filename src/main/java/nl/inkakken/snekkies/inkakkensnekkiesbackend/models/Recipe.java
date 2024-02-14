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
@Table(name = "recipe")
public class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name")
    private String name;

    private Date startTime;

    private int duration;

    private float grade;

    private String pictureReference;

    private UUID onlineRecipeId;

    private UUID movieNightId;

    @ManyToMany()
    @JoinTable(name = "recipe_deviated_ingredient", joinColumns = {
            @JoinColumn(name = "recipe_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "deviated_ingredient_id")
    })
    private List<DeviatedIngredient> deviatedIngredient;


}
