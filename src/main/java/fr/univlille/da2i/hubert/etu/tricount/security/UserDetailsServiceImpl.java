package fr.univlille.da2i.hubert.etu.tricount.security;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {


    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
     
    @Override
    public UserDetails loadUserByUsername(final String email) throws AuthenticationCredentialsNotFoundException {
        final AccountEntity account = this.accountRepository.findByUserEmail(email).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Invalid email"));//TODO: extract?

        if (account == null)
            throw new UsernameNotFoundException("Could not find user");

        return new MyAccountDetails(account);
    }
 
}