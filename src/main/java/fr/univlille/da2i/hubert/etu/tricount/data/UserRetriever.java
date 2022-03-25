package fr.univlille.da2i.hubert.etu.tricount.data;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.excpetion.UnauthorizedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Component
public class UserRetriever {

    private final AccountRepository accountRepository;

    public UserRetriever(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity getLoggedUserAccount(final Principal principal) throws AuthenticationCredentialsNotFoundException {
        Optional<AccountEntity> loggedUserAccount = Optional.empty();
        try {
            loggedUserAccount = this.accountRepository.findByUserEmail(principal.getName());
        } catch (Exception e) {
        }
        return loggedUserAccount.orElseThrow(() -> new UnauthorizedException("You need to be logged in to do this."));
    }

}
