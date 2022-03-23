package fr.univlille.da2i.hubert.etu.tricount.data.repository;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AnonymousUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface AnonymousUserRepository extends CrudRepository<AnonymousUserEntity, String> {
}
