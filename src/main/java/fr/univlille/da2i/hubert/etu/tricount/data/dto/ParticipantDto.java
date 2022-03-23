package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ParticipantDto {

    @NotNull
    @NotBlank
    private String username;

    @Email
    private String email;

}
