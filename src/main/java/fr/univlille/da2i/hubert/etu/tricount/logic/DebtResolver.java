package fr.univlille.da2i.hubert.etu.tricount.logic;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.EntriesEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntity;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class DebtResolver {

    private final List<ParticipesEntity> participants;

    public DebtResolver(final List<ParticipesEntity> participants) {
        this.participants = participants;
    }

    public List<EntriesEntity> obtainAllEntries() {
        return this.participants.stream().map(ParticipesEntity::getEntriesEntities).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public Double calculateTotalExpenses() {
        return this.obtainAllEntries().stream().mapToDouble(EntriesEntity::getAmount).sum();
    }

    public Double calculateAmountPerParticipant() {
        return this.calculateTotalExpenses() / this.getParticipants().size();
    }

    public Double calculateParticipantAmount(final ParticipesEntity participant) {
        return participant.getEntriesEntities().stream()
                .mapToDouble(EntriesEntity::getAmount).sum();
    }

    public Double calculateAmountOwedByParticipant(final ParticipesEntity participant) {
        return this.calculateParticipantAmount(participant) - this.calculateAmountPerParticipant();
    }

    public Double calculateParticipantAmountById(final String participantId) {
        return this.getParticipants().stream()
                .filter(participesEntity -> participesEntity.getId().equals(participantId))
                .map(ParticipesEntity::getEntriesEntities)
                .flatMap(Collection::stream)
                .mapToDouble(EntriesEntity::getAmount).sum();
    }

    public Double calculateAmountOwedByParticipantById(final String participantId) {
        return this.calculateParticipantAmountById(participantId) - this.calculateAmountPerParticipant();
    }

    public List<Debt> calculateDebts() {
        List<Map.Entry<ParticipesEntity, Double>> owedAmounts = this.getParticipants().stream()
                .map(participant -> new AbstractMap.SimpleEntry<>(participant, this.calculateAmountOwedByParticipant(participant)))
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toList());

        owedAmounts.sort(Comparator.comparingDouble(Map.Entry::getValue));

        final List<Debt> debts = new ArrayList<>();

        while (owedAmounts.size() > 1) {
            final Map.Entry<ParticipesEntity, Double> mostInDebt = owedAmounts.get(0);
            final Map.Entry<ParticipesEntity, Double> mostOverpay = owedAmounts.get(owedAmounts.size() - 1);

            final Double amountOfTheDebt = Math.abs(mostInDebt.getValue()) < mostOverpay.getValue() ? Math.abs(mostInDebt.getValue()) : mostOverpay.getValue();
            final Double roundedAmountOfTheDebt = Math.round(amountOfTheDebt * 100) / 100D;

            debts.add(new Debt(mostInDebt.getKey(), mostOverpay.getKey(), roundedAmountOfTheDebt));

            mostInDebt.setValue(mostInDebt.getValue() + amountOfTheDebt);
            mostOverpay.setValue(mostOverpay.getValue() - amountOfTheDebt);

            owedAmounts.sort(Comparator.comparingDouble(Map.Entry::getValue));
            owedAmounts = owedAmounts.stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toList());
        }

        return debts;
    }

}
