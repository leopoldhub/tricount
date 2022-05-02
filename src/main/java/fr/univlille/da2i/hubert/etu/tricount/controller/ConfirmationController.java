package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.InvalidParameterException;

@Controller
@RequestMapping("/confirmation")
public class ConfirmationController {

    private final AccountRepository accountRepository;


    public ConfirmationController(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping("{email}/{confirmation-id}")
    public String confirmAccount(@PathVariable("email") final String email, @PathVariable("confirmation-id") final String confirmationId) {
        final AccountEntity accountEntity = this.accountRepository.findByUserEmail(email).orElseThrow(() -> new InvalidParameterException("invalid email"));

        if (accountEntity.getConfirmationCode().equals(confirmationId)) {
            accountEntity.setEnabled(true);
            this.accountRepository.save(accountEntity);
            return UrlUtils.buildRedirectUrlWithInfo("/login", "account successfully confirmed! please login");
        } else
            return UrlUtils.buildRedirectUrlWithError("/login", "unexpected error while confirming your account!");
    }

}
