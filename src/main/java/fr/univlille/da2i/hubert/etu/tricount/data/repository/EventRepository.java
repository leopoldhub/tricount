package fr.univlille.da2i.hubert.etu.tricount.data.repository;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.EventsEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventsEntity, String> {
}
