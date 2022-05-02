package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.RegisterAccountDto;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.UserRepository;
import fr.univlille.da2i.hubert.etu.tricount.utils.MailUtils;
import fr.univlille.da2i.hubert.etu.tricount.utils.UrlUtils;
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
import java.security.Principal;
import java.util.UUID;

@Controller
public class LoginRegisterController {

    private final String appName;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final JavaMailSender javaMailSender;

    private final UserRetriever userRetriever;

    private final String contextPath;

    public LoginRegisterController(@Value("${appName}") final String appName,
                                   @Value("${server.servlet.context-path}") final String contextPath,
                                   final BCryptPasswordEncoder passwordEncoder,
                                   final UserRepository userRepository,
                                   final AccountRepository accountRepository,
                                   final JavaMailSender javaMailSender,
                                   final UserRetriever userRetriever) {
        this.appName = appName;
        this.contextPath = contextPath;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.javaMailSender = javaMailSender;
        this.userRetriever = userRetriever;
    }

    @GetMapping("/login")
    public String login(final Model model, @RequestParam(value = "error", required = false) final String error, final Principal principal) {
        try {
            final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);
            model.addAttribute("userInfos", connectedUserAccount);
        }catch (final Exception e){}

        model.addAttribute("appName", this.appName);
        model.addAttribute("pageName", "login");
        model.addAttribute("errorMessage", error);
        return "LoginForm";
    }

    @GetMapping("/register")
    public String registerGet(final Model model, @RequestParam(value = "error", required = false) final String error, final Principal principal) {
        try {
            final AccountEntity connectedUserAccount = this.userRetriever.getLoggedUserAccountOrThrow(principal);
            model.addAttribute("userInfos", connectedUserAccount);
        }catch (final Exception e){}

        model.addAttribute("appName", this.appName);
        model.addAttribute("pageName", "register");
        model.addAttribute("error", error);
        return "RegisterForm";
    }

    @PostMapping("/register")
    public String registerPost(@Valid final RegisterAccountDto accountDto, final ModelMapper modelMapper) throws MessagingException {
        if (!accountDto.getPassword().equals(accountDto.getConfirmpassword()))
            return UrlUtils.buildRedirectUrlWithError("/register", "Passwords does not match.");
        if (!this.accountRepository.findByUserEmail(accountDto.getEmail()).isEmpty())
            return UrlUtils.buildRedirectUrlWithError("/register", "Email already taken.");

        accountDto.setPassword(this.passwordEncoder.encode(accountDto.getPassword()));

        AccountEntity account = modelMapper.map(accountDto, AccountEntity.class);
        account.setConfirmationCode(UUID.randomUUID().toString());

        account = this.accountRepository.save(account);

        MailUtils.sendMail(this.javaMailSender, "leopold.hubert.etu@univ-lille.fr", account.getEmail(), "Please confirm your account creation", "confirm your account by clicking here: http://localhost:8443"+contextPath+"/confirmation/" + account.getEmail() + "/" + account.getConfirmationCode());

        return UrlUtils.buildRedirectUrlWithInfo("/login", "Please validate your email to end the registration!");
    }

}
