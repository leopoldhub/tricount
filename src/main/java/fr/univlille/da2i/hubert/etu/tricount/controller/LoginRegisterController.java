package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.RegisterAccountDto;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.UserRepository;
import fr.univlille.da2i.hubert.etu.tricount.utils.MailUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class LoginRegisterController {

    private final String appName;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private JavaMailSender javaMailSender;

    public LoginRegisterController(@Value("${appName}") final String appName,
                                   final BCryptPasswordEncoder passwordEncoder,
                                   final UserRepository userRepository,
                                   final AccountRepository accountRepository,
                                   JavaMailSender javaMailSender) {
        this.appName = appName;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/login")
    public String login(final Model model, @RequestParam(value = "error", required = false) final String error) {
        model.addAttribute("appName", this.appName);
        model.addAttribute("pageName", "login");
        model.addAttribute("errorMessage", error);
        return "LoginRegisterForm";
    }

    @GetMapping("/register")
    public String registerGet(final Model model, @RequestParam(value = "error", required = false) final String error) {
        model.addAttribute("appName", this.appName);
        model.addAttribute("pageName", "register");
        model.addAttribute("error", error);
        return "LoginRegisterForm";
    }

    @PostMapping("/register")
    public String registerPost(@Valid final RegisterAccountDto accountDto, ModelMapper modelMapper) throws MessagingException {
        if (!accountDto.getPassword().equals(accountDto.getConfirmpassword()))
            return "redirect:/register?error=Passwords does not match.";
        if (!this.accountRepository.findByUserEmail(accountDto.getEmail()).isEmpty())
            return "redirect:/register?error=Email already taken.";

        accountDto.setPassword(this.passwordEncoder.encode(accountDto.getPassword()));

        AccountEntity account = modelMapper.map(accountDto, AccountEntity.class);
        account.setConfirmationCode(UUID.randomUUID().toString());

        account = this.accountRepository.save(account);

        MailUtils.sendMail(this.javaMailSender, "leopold.hubert.etu@univ-lille.fr", account.getEmail(), "Please confirm your account creation", "confirm your account by clicking here: http://localhost:8080/confirmation/" + account.getEmail() + "/" + account.getConfirmationCode());

        return "redirect:/login?info=Please validate your email to end the registration!";
    }

}
