package nl.inkakken.snekkies.inkakkensnekkiesbackend.models;

import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue()
    private UUID id;

    private String name;
}

