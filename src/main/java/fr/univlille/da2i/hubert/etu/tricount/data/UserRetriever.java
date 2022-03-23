package fr.univlille.da2i.hubert.etu.tricount.data;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserRetriever {

    private final AccountRepository accountRepository;

    public UserRetriever(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity getLoggedUserAccount(final Principal principal) throws AuthenticationCredentialsNotFoundException {
        return this.accountRepository.findByUserEmail(principal.getName()).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Invalid email"));
    }

}
