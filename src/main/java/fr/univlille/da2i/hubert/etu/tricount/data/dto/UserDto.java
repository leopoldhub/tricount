package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @NotNull
    @NotBlank
    @Email
    private String email;

}
