package fr.univlille.da2i.hubert.etu.tricount.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtDto {

    @NotNull
    @NotBlank
    private String inDebtId;

    @NotNull
    @NotBlank
    private String debtOwnerId;

    @NotNull
    @NotBlank
    private Double amount;

}
