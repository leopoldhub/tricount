package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.UserEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.ParticipeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

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
    @ResponseBody //temp
    public /*String*/Object account(final Model model, final Principal principal) {
        final UserEntity user = this.userRetriever.getLoggedUserAccount(principal);

        model.addAttribute("userInfos", user);
        model.addAttribute("pageName", "Account");
        model.addAttribute("appName", this.appName);

        /*List<ParticipeEntity> relatedParticipe = participeRepository.findByUser(user);

        UserDebts userDebts = new UserDebts(user);

        for (ParticipeEntity participe : relatedParticipe) {
            EventEntity event = participe.getEvent();
            List<ParticipeEntity> participants = participeRepository.findByEvent(event);
            System.out.println(participants);
        }*/

        //System.out.println(relatedParticipe);

        /*List<ParticipeEntity> relatedParticipe = participeRepository.findByIdUsername(user.getUsername());
        System.out.println(relatedParticipe);
        UserDebts userDebts = new UserDebts(userRepository.findById(user.getUsername()).get());
        for (ParticipeEntity participe : relatedParticipe) {
            EventEntity event = eventRepository.findById(participe.getId().getId()).get();
            List<ParticipeEntity> participants = participeRepository.findByIdId(event.getId());
            userDebts.getEventDebts().add(new EventDebts(event, new DebtResolver(participants.stream().map(part -> new AbstractMap.SimpleEntry<String, Double>(participe.getId().getUsername(), part.getPayed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))).relatedUserDebts(user.getUsername())));
        }

        model.addAttribute("userDebts", userDebts);*/

        //return "Account";
        //return userDebts;
        return null;
    }

}
