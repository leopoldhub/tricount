package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EventCreationDto {

    @NotNull
    @NotBlank
    @NotEmpty
    private String username;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 64, message = "The size must be under 64")
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 128, message = "The size must be under 128")
    private String description;

}
