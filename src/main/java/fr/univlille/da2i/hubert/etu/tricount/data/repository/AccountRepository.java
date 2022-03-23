package fr.univlille.da2i.hubert.etu.tricount.data.repository;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, String> {

    Optional<AccountEntity> findByUserEmail(String email);

    Optional<UserEntity> findUserByEmail(String email);

}
