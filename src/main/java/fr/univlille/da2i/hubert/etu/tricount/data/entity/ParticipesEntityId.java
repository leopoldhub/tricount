package fr.univlille.da2i.hubert.etu.tricount.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class ParticipesEntityId implements Serializable {

    private static final long serialVersionUID = 8346658315959605269L;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "event_id", nullable = false)
    private String eventId;

}