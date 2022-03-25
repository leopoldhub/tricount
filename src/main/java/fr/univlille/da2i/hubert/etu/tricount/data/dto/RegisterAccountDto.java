package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterAccountDto {

    @NotNull
    @NotBlank
    @NotEmpty
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 64, message = "The size must be between 6 and 64")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 64, message = "The size must be between 6 and 64")
    private String confirmpassword;

}
