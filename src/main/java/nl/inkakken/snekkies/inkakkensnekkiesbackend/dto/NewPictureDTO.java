package nl.inkakken.snekkies.inkakkensnekkiesbackend.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPictureDTO {
    private String picture;
    private UUID recipeId;
}
