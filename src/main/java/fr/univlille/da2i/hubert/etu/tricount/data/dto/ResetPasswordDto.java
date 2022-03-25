package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordDto {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 64, message = "The size must be between 6 and 64")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 64, message = "The size must be between 6 and 64")
    private String confirmpassword;

}
