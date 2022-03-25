package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.EntryDto;
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
@RequestMapping("/event/{id}/")
public class EventController {

    private final String appName;

    private final EventRepository eventRepository;

    private final ParticipeRepository participeRepository;

    private final UserRetriever userRetriever;

    private final AnonymousUserRepository anonymousUserRepository;

    private final UserRepository userRepository;

    private final EntryRepository entryRepository;

    public EventController(@Value("${appName}") final String appName,
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

    @GetMapping("")//TODO: redirect /xxx => /xxx/
    public String getEvent(@PathVariable("id") final String id, final Model model) {

        final EventsEntity event = this.eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Asked event not found"));

        model.addAttribute("event", event);

        return "Event";
    }

    @PostMapping("config")
    public String getEvent(@PathVariable("id") final String id, @RequestParam final boolean publicEntries, final Principal principal) {
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);
        this.participeRepository.findByIdEventIdAndIdUserIdAndOwnerTrue(id, connectedUserAccount.getId())
                .orElseThrow(()->new UnauthorizedException("you must be the owner of the event to do this"));

        final EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        event.setPublicEntries(publicEntries);

        this.eventRepository.save(event);

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", id));
    }

    @DeleteMapping("")
    public String deleteEvent(@PathVariable("id") final String id, final Principal principal) {
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);
        this.participeRepository.findByIdEventIdAndIdUserIdAndOwnerTrue(id, connectedUserAccount.getId())
                .orElseThrow(()->new UnauthorizedException("you must be the owner of the event to do this"));

        if (this.participeRepository.existsById(new ParticipesEntityId()))
            this.eventRepository.deleteById(id);

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", id));
    }

    @PostMapping("addUser")
    public String getEvent(@PathVariable("id") final String id, @Valid final ParticipantDto participantDto, final ModelMapper modelMapper, final Principal principal) {
        //TODO: send mail...
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);
        this.participeRepository.findByIdEventIdAndIdUserIdAndOwnerTrue(id, connectedUserAccount.getId())
                .orElseThrow(()->new UnauthorizedException("you must be the owner of the event to do this"));

        final EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        final UserEntity userEntity = modelMapper.map(participantDto, UserEntity.class);

        final AnonymousUserEntity savedAnonymousUserEntity;

        if (StringUtils.isEmpty(userEntity.getEmail())) {
            savedAnonymousUserEntity = this.anonymousUserRepository.save(modelMapper.map(participantDto, AnonymousUserEntity.class));
        } else {
            savedAnonymousUserEntity = this.userRepository.findByEmail(userEntity.getEmail()).orElseGet(() -> this.userRepository.save(userEntity));
        }

        if (!this.participeRepository.existsById(new ParticipesEntityId(userEntity.getId(), event.getId())))
            this.participeRepository.save(modelMapper.map(
                    new ParticipeDto(savedAnonymousUserEntity.getId(), event.getId(), participantDto.getUsername(), false)
                    , ParticipesEntity.class));

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", id));
    }

    @PostMapping("addEntry")
    public String getEvent(@PathVariable("id") final String id, @Valid final EntryDto entryDto, final ModelMapper modelMapper, final Principal principal) {
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);

        final EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        if(!event.isPublicEntries())
            this.participeRepository.findByIdEventIdAndIdUserIdAndOwnerTrue(id, connectedUserAccount.getId())
                    .orElseThrow(()->new UnauthorizedException("you must be the owner of the event to do this"));
        else
            this.participeRepository.findById(new ParticipesEntityId(connectedUserAccount.getId(), id))
                    .orElseThrow(()->new UnauthorizedException("you are not authorized to do this"));

        final EntriesEntity entity = modelMapper.map(entryDto, EntriesEntity.class);
        entity.setEventId(id);

        this.entryRepository.save(entity);

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", id));
    }

    @DeleteMapping("deleteEntry/{entryId}")
    public String deleteEntry(@PathVariable("id") final String id, @PathVariable("entryId") final String entryId, final Principal principal) {
        final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);

        final EventsEntity event = this.eventRepository.findById(id).orElseThrow();

        if(!event.isPublicEntries())
            this.participeRepository.findByIdEventIdAndIdUserIdAndOwnerTrue(id, connectedUserAccount.getId())
                    .orElseThrow(()->new UnauthorizedException("you must be the owner of the event to do this"));
        else
            this.participeRepository.findById(new ParticipesEntityId(connectedUserAccount.getId(), id))
                    .orElseThrow(()->new UnauthorizedException("you are not authorized to do this"));

        this.entryRepository.deleteById(entryId);

        return UrlUtils.buildRedirectUrl(String.format("/event/%s/", id));
    }

}