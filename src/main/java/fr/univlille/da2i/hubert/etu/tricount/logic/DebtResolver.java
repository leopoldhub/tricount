package fr.univlille.da2i.hubert.etu.tricount.logic;

import fr.univlille.da2i.hubert.etu.tricount.data.dto.DebtDto;

import java.util.*;
import java.util.stream.Collectors;

public class DebtResolver {

    private final Map<String, Double> payments;

    public DebtResolver(final Map<String, Double> payments) {
        this.payments = payments;
    }

    public Double obtainTotalPayed() {
        return this.payments.values().stream().mapToDouble(aDouble -> aDouble).sum();
    }

    public Double obtainAmountPerParticipant() {
        return this.obtainTotalPayed() / this.payments.size();
    }

    public Map<String, Double> calculatePaymentsDifferences() {
        return this.payments.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), this.obtainAmountPerParticipant() - entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> calculateDidntPayedEnought() {
        return this.calculatePaymentsDifferences().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> calculateHavePayedTooMuch() {
        return this.calculatePaymentsDifferences().entrySet().stream()
                .filter(entry -> entry.getValue() < 0)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Math.abs(entry.getValue())));
    }

    public List<DebtDto> calculateDebts() {
        final List<Map.Entry<String, Double>> didntPayEnought = this.calculateDidntPayedEnought().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toList());
        final List<Map.Entry<String, Double>> havePayTooMuch = this.calculateHavePayedTooMuch().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toList());

        final List<DebtDto> debts = new ArrayList<>();

        for(final Map.Entry<String, Double> inDebt:didntPayEnought) {
            for(final Map.Entry<String, Double> tooMuch:havePayTooMuch) {
                if(inDebt.getValue() == 0) break;
                if(tooMuch.getValue() == 0) continue;
                final Double toPay = Math.min(inDebt.getValue(), Math.abs(tooMuch.getValue()));
                inDebt.setValue(inDebt.getValue()-toPay);
                tooMuch.setValue(tooMuch.getValue()-toPay);
                debts.add(new DebtDto(inDebt.getKey(), tooMuch.getKey(), toPay));
            }
        }

        return debts;
    }

    public List<DebtDto> relatedUserDebts(final String username) {
        return this.calculateDebts().stream().filter(debtDto -> debtDto.getInDebtId().equals(username) || debtDto.getDebtOwnerId().equals(username)).collect(Collectors.toList());
    }

}
