package fr.univlille.da2i.hubert.etu.tricount.data;

import fr.univlille.da2i.hubert.etu.tricount.data.dto.DebtDto;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.EventsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class EventDebts {

    @NonNull
    private EventsEntity event;
    private List<DebtDto> debts = new ArrayList<>();

    public Double obtainUserDueAmount(final String id) {
        return this.debts.stream().filter(debtDto -> debtDto.getInDebtId().equals(id)).mapToDouble(DebtDto::getAmount).sum();
    }

    public Double obtainUserOwingDueAmount(final String id) {
        return this.debts.stream().filter(debtDto -> debtDto.getDebtOwnerId().equals(id)).mapToDouble(DebtDto::getAmount).sum();
    }

}
