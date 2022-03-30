package fr.univlille.da2i.hubert.etu.tricount.logic;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntity;
import lombok.Data;

@Data
public class Debt {

    private final ParticipesEntity inDebt;

    private final ParticipesEntity receiver;

    private final Double amount;

    public Debt(final ParticipesEntity inDebt, final ParticipesEntity receiver, final Double amount) {
        this.inDebt = inDebt;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Debt{" +
                this.inDebt +
                " => " + this.receiver +
                "[" + this.amount +
                ']';
    }

}
