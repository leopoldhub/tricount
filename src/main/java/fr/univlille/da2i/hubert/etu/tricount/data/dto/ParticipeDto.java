package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipeDto {

    @NotNull
    @NotBlank
    private String userId;

    @NotNull
    @NotBlank
    private String eventId;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private boolean owner;

}
