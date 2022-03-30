package fr.univlille.da2i.hubert.etu.tricount.data.repository;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.ParticipesEntityId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipeRepository extends CrudRepository<ParticipesEntity, ParticipesEntityId> {

    List<ParticipesEntity> findByIdUserId(String userId);

    List<ParticipesEntity> findByIdEventId(String eventId);

    Optional<ParticipesEntity> findByIdEventIdAndIdUserId(String eventId, String userId);

    Optional<ParticipesEntity> findByIdEventIdAndIdUserIdAndOwnerTrue(String eventId, String userId);

}
