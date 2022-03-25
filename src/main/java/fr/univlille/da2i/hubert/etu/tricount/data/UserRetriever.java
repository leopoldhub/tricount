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

    public Optional<AccountEntity> getLoggedUserAccount(final Principal principal) throws AuthenticationCredentialsNotFoundException {
        Optional<AccountEntity> loggedUserAccount = Optional.empty();
        try {
            loggedUserAccount = this.accountRepository.findByUserEmail(principal.getName());
        } catch (final Exception e) {
        }
        return loggedUserAccount;
    }

    public AccountEntity getLoggedUserAccountOrThrow(final Principal principal) throws AuthenticationCredentialsNotFoundException {
        return this.getLoggedUserAccount(principal).orElseThrow(() -> new UnauthorizedException("You need to be logged in to do this."));
    }

}
