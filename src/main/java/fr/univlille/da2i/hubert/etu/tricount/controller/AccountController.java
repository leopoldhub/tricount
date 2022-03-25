package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.EventsEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.ParticipeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
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
        AccountEntity accountEntity = this.userRetriever.getLoggedUserAccount(principal);

        List<EventsEntity> events = this.participeRepository.findByIdUserId(accountEntity.getId()).stream()
                .map(ParticipesEntity::getEvent)
                .collect(Collectors.toList());

        model.addAttribute("userInfos", accountEntity);
        model.addAttribute("events", events);
        model.addAttribute("pageName", "Account");
        model.addAttribute("appName", this.appName);

        return "Account";
    }

}
