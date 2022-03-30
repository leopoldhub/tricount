package fr.univlille.da2i.hubert.etu.tricount.controller;

import fr.univlille.da2i.hubert.etu.tricount.data.UserRetriever;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.AccountEntity;
import fr.univlille.da2i.hubert.etu.tricount.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class IndexController {

    private final String appName;

    private final UserRetriever userRetriever;

    public IndexController(@Value("${appName}") final String appName,
                           final UserRetriever userRetriever) {
        this.appName = appName;
        this.userRetriever = userRetriever;
    }

    @RequestMapping({"/", "/index"})
    public String index(final Model model, final Principal principal) {
        final AccountEntity user = this.userRetriever.getLoggedUserAccount(principal).orElse(null);

        model.addAttribute("userInfos", user);
        model.addAttribute("pageName", "Index");
        model.addAttribute("appName", this.appName);

        return "Index";
    }

}
