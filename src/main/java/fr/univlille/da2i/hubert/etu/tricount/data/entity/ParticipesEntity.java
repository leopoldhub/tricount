package fr.univlille.da2i.hubert.etu.tricount.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "participes")
@Data
public class ParticipesEntity {

    @EmbeddedId
    private ParticipesEntityId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private AnonymousUserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private EventsEntity event;

    @Column(name = "owner")
    @ColumnDefault("'false'")
    private boolean owner;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false, insertable = false, updatable = false)
    })
    private List<EntriesEntity> entriesEntities;

    @Override
    public String toString() {
        return "ParticipesEntity{" +
                "id=" + this.id +
                ", owner=" + this.owner +
                ", username='" + this.username + '\'' +
                '}';
    }

}