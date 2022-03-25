package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.EntryDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.EventCreationDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.ParticipantDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.ParticipeDto;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.*;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.*;
import fr.univlille.da2i.hubert.etu.tricount.excpetion.UnauthorizedException;
import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/event")
public class EventsController {

    private final String appName;

    private final EventRepository eventRepository;

    private final ParticipeRepository participeRepository;

    private final UserRetriever userRetriever;

    private final AnonymousUserRepository anonymousUserRepository;

    private final UserRepository userRepository;

    private final EntryRepository entryRepository;

    public EventsController(@Value("${appName}") final String appName,
                            final EventRepository eventRepository,
                            final ParticipeRepository participeRepository,
                            final UserRetriever userRetriever,
                            final AnonymousUserRepository anonymousUserRepository,
                            final UserRepository userRepository,
                            final EntryRepository entryRepository) {
        this.appName = appName;
        this.eventRepository = eventRepository;
        this.participeRepository = participeRepository;
        this.userRetriever = userRetriever;
        this.anonymousUserRepository = anonymousUserRepository;
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
    }

    @GetMapping("")
    public String createEventGet(final Model model, final Principal principal) {
        final UserEntity user = this.userRetriever.getLoggedUserAccountOrThrow(principal);

        model.addAttribute("userInfos", user);
        model.addAttribute("pageName", "Create event");
        model.addAttribute("appName", this.appName);

        return "EventForm";
    }

    @PostMapping("")
    public String createEventPost(@Valid final EventCreationDto eventCreationDto, final Principal principal, final ModelMapper modelMapper) {
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);

        final EventsEntity event;

        try {
            event = this.eventRepository.save(modelMapper.map(eventCreationDto, EventsEntity.class));
            final ParticipesEntity participesEntity = modelMapper.map(new ParticipeDto(connectedUserAccount.getId(), event.getId(), eventCreationDto.getUsername(), true), ParticipesEntity.class);
            this.participeRepository.save(participesEntity);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to create event");
        }

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", event.getId()));
    }

}
