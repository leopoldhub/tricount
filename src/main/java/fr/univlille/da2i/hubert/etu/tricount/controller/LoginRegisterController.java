package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.AccountDto;
import fr.univlille.da2i.hubert.etu.tricount.data.dto.UserDto;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.AccountRepository;
import fr.univlille.da2i.hubert.etu.tricount.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class LoginRegisterController {

    private final String appName;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public LoginRegisterController(@Value("${appName}") final String appName,
                                   final BCryptPasswordEncoder passwordEncoder,
                                   final UserRepository userRepository,
                                   final AccountRepository accountRepository) {
        this.appName = appName;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
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
    public String registerPost(@Valid final UserDto userdto, @Valid final AccountDto accountDto, @RequestParam("re_password") final String rePassword) {
        if (!accountDto.getPassword().equals(rePassword))
            return "redirect:/register?error=Passwords does not match.";
        if (this.accountRepository.findByUserEmail(userdto.getEmail()) != null)
            return "redirect:/register?error=Email already taken.";

        final AccountEntity account = new AccountEntity();
        account.setEmail(userdto.getEmail());
        account.setPassword(this.passwordEncoder.encode(accountDto.getPassword()));

        this.accountRepository.save(account);

        return "redirect:/login?info=Registered with success!";
    }

}