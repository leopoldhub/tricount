package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.dto.ResetPasswordDto;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.utils.MailUtils;
import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.InvalidParameterException;
import java.util.UUID;

@Controller
@RequestMapping("/password-reset")
public class PasswordResetController {

    private final AccountRepository accountRepository;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;

    public PasswordResetController(final AccountRepository accountRepository,
                                   final JavaMailSender javaMailSender,
                                   final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String askPasswordReset() {
        return "AskResetPassword";
    }

    @PostMapping("")
    public String sendPasswordReset(@RequestParam("email") final String email) throws MessagingException {
        AccountEntity accountEntity = this.accountRepository.findByUserEmail(email).orElseThrow(() -> new InvalidParameterException("invalid email"));
        accountEntity.setConfirmationCode(UUID.randomUUID().toString());
        accountEntity = this.accountRepository.save(accountEntity);

        MailUtils.sendMail(this.javaMailSender, "leopold.hubert.etu@univ-lille.fr", email, "Reset your password", "reset your password by clicking here: http://localhost:8080/password-reset/" + email + "/" + accountEntity.getConfirmationCode());

        return UrlUtils.buildRedirectUrlWithInfo("/login", "reset link sent by mail");
    }

    @GetMapping("{email}/{confirmation-id}")
    public String resetPasword(@PathVariable("email") final String email, @PathVariable("confirmation-id") final String confirmationId) {
        final AccountEntity accountEntity = this.accountRepository.findByUserEmail(email).orElseThrow(() -> new InvalidParameterException("invalid email"));

        if (accountEntity.getConfirmationCode().equals(confirmationId)) {
            return "ResetPassword";
        } else
            return UrlUtils.buildRedirectUrlWithError("/login", "unexpected error while confirming your account!");
    }

    @PostMapping("{email}/{confirmation-id}")
    public String resetPasword(@PathVariable("email") final String email, @PathVariable("confirmation-id") final String confirmationId, final ResetPasswordDto resetPasswordDto) {
        final AccountEntity accountEntity = this.accountRepository.findByUserEmail(email).orElseThrow(() -> new InvalidParameterException("invalid email"));

        if (!resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmpassword()))
            return UrlUtils.buildRedirectUrlWithError("./"+confirmationId, "Passwords does not match.");

        if (accountEntity.getConfirmationCode().equals(confirmationId)) {
            accountEntity.setPassword(this.passwordEncoder.encode(resetPasswordDto.getPassword()));
            this.accountRepository.save(accountEntity);
            return UrlUtils.buildRedirectUrlWithInfo("/login", "password changed! please login");
        } else
            return UrlUtils.buildRedirectUrlWithError("/login", "unexpected error while changing password!");
    }

}
