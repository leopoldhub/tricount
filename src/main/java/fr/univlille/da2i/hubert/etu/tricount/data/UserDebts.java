package fr.univlille.da2i.hubert.etu.tricount.data;

import fr.univlille.da2i.hubert.etu.tricount.data.dto.DebtDto;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDebts {

    @NonNull
    private UserEntity user;
    private List<EventDebts> eventDebts = new ArrayList<>();

    public Double obtainUserDueAmount() {
        return this.eventDebts.stream().mapToDouble(eventDebt -> eventDebt.obtainUserDueAmount(this.getUser().getId())).sum();
    }

    public Double obtainUserOwingDueAmount() {
        return this.eventDebts.stream().mapToDouble(eventDebt -> eventDebt.obtainUserOwingDueAmount(this.getUser().getId())).sum();
    }

}
