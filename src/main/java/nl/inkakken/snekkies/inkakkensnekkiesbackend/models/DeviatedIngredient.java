package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deviated_ingredient")
public class DeviatedIngredient{

    @Id
    @GeneratedValue()
    private UUID id;

    private int amount;

    private boolean addedOrSubstracted;

    private boolean accident;

    @ManyToOne
    private Ingredient ingredient; 


}
