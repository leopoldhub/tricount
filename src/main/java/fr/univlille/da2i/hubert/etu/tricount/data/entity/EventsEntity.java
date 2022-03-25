package fr.univlille.da2i.hubert.etu.tricount.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class EventsEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, columnDefinition = "CHAR(32)")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "public", nullable = false)
    @ColumnDefault("'false'")
    private boolean publicEntries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    @JsonIgnore
    private List<ParticipesEntity> participes;

    @Override
    public String toString() {
        return "EventsEntity{" +
                "id='" + this.id + '\'' +
                ", title='" + this.title + '\'' +
                ", description='" + this.description + '\'' +
                ", publicEntries=" + this.publicEntries +
                '}';
    }

}