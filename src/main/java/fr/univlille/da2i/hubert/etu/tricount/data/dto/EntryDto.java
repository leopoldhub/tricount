package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class EntryDto {

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    @NotNull
    @Min(0)
    private Double amount;

}
