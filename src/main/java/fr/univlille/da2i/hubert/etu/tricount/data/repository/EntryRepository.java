package fr.univlille.da2i.hubert.etu.tricount.data.repository;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.EntriesEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntityId;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<EntriesEntity, ParticipesEntityId> {
}
