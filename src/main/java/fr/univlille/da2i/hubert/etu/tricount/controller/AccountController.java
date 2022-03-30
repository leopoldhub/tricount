package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.EventsEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.ParticipeRepository;
import fr.univlille.da2i.hubert.etu.tricount.logic.DebtResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    private final String appName;

    private final ParticipeRepository participeRepository;

    private final UserRetriever userRetriever;

    public AccountController(@Value("${appName}") final String appName,
                             final ParticipeRepository participeRepository,
                             final UserRetriever userRetriever) {
        this.appName = appName;
        this.participeRepository = participeRepository;
        this.userRetriever = userRetriever;
    }

    @RequestMapping("/account")
    public String account(final Model model, final Principal principal) {
        final AccountEntity accountEntity = this.userRetriever.getLoggedUserAccountOrThrow(principal);

        final List<EventsEntity> events = this.participeRepository.findByIdUserId(accountEntity.getId()).stream()
                .map(ParticipesEntity::getEvent)
                .collect(Collectors.toList());

        final LinkedHashMap<EventsEntity, Double> eventsAmoutsDifferences = events.stream()
                .map(eventsEntity ->
                        new AbstractMap.SimpleEntry<>(
                                eventsEntity,
                                new DebtResolver(eventsEntity.getParticipes()).calculateAmountOwedByParticipantById(accountEntity.getId())
                        )
                ).
                sorted(Comparator.comparing(t -> t.getKey().getId()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, o2) -> o2, LinkedHashMap::new));

        model.addAttribute("eventDifferences", eventsAmoutsDifferences);
        model.addAttribute("userInfos", accountEntity);
        model.addAttribute("events", events);
        model.addAttribute("pageName", "Account");
        model.addAttribute("appName", this.appName);

        return "Account";
    }

}
