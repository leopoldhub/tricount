package fr.univlille.da2i.hubert.etu.tricount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.EventCreationDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.ParticipantDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.ParticipeDto;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.*;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AnonymousUserRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.EventRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.ParticipeRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/event")
public class EventController {

    private final String appName;

    private final EventRepository eventRepository;

    private final ParticipeRepository participeRepository;

    private final UserRetriever userRetriever;

    private final AnonymousUserRepository anonymousUserRepository;

    private final UserRepository userRepository;

    public EventController(@Value("${appName}") final String appName,
                           final EventRepository eventRepository,
                           final ParticipeRepository participeRepository,
                           final UserRetriever userRetriever,
                           final AnonymousUserRepository anonymousUserRepository,
                           final UserRepository userRepository) {
        this.appName = appName;
        this.eventRepository = eventRepository;
        this.participeRepository = participeRepository;
        this.userRetriever = userRetriever;
        this.anonymousUserRepository = anonymousUserRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String createEventGet(final Model model, final Principal principal) {
        final UserEntity user = this.userRetriever.getLoggedUserAccount(principal);

        model.addAttribute("userInfos", user);
        model.addAttribute("pageName", "Create event");
        model.addAttribute("appName", this.appName);

        return "EventForm";
    }

    @PostMapping("")
    public String createEventPost(@Valid final EventCreationDto eventCreationDto, final Principal principal, final ModelMapper modelMapper) {
        AccountEntity connectedUserAccount = userRetriever.getLoggedUserAccount(principal);

        EventsEntity event = modelMapper.map(eventCreationDto, EventsEntity.class);

        event = this.eventRepository.save(event);

        //FIXME: add username in event creation
        ParticipesEntity participesEntity = modelMapper.map(new ParticipeDto(connectedUserAccount.getId(), event.getId(), "", true), ParticipesEntity.class);

        this.participeRepository.save(participesEntity);

        return String.format("redirect:/event/%s/", event.getId());
    }

    @RequestMapping("/{id}")
    public String getEvent(@PathVariable("id") final String id) {
        return String.format("redirect:/event/%s/", id);
    }

    @GetMapping("/{id}/")
    public String getEvent(@PathVariable("id") final String id, final Model model) {

        EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        model.addAttribute("event", event);

        /*List<ParticipeEntity> participants = participeRepository.findByIdEventId(id);

        Map<Integer, Double> payements = participants.stream()
                .map(participe -> new AbstractMap.SimpleEntry<String, Double>(participe.getId().getUserId(), participe.getPayed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new DebtResolver(payements).calculateDebts();*/
        return "Event";
    }

    @PostMapping("/{id}/addUser")
    public String getEvent(@PathVariable("id") final String id, @Valid ParticipantDto participantDto, final Model model, ModelMapper modelMapper) {
        EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        System.out.println(participantDto);

//        if(participantDto.getEmail() == null) {
        AnonymousUserEntity anonymousUserEntity = this.anonymousUserRepository.save(modelMapper.map(participantDto, AnonymousUserEntity.class));
        ParticipesEntity participesEntity = this.participeRepository.save(modelMapper.map(new ParticipeDto(anonymousUserEntity.getId(), event.getId(), participantDto.getUsername(), false), ParticipesEntity.class));
//        }

        model.addAttribute("event", event);

        return String.format("redirect:/event/%s/", id);
    }

}
