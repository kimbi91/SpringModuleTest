package app.controllers;

import app.models.Officer;
import app.services.OfficerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeAndLoginController {

    private final OfficerService officerService;

    public HomeAndLoginController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @GetMapping(value = {"/", "/home", "/fooldal"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = {"/login", "/bejelentkezes"})
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = {"/login-error"})
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }

    @GetMapping(value = {"/register"})
    public String getRegPage(Model model) {
        model.addAttribute("officer", new Officer());

        return "register";
    }

    @GetMapping(value = {"/regerror"})
    public String getRegErrorPage(Model model) {
        model.addAttribute("regError", true);
        model.addAttribute("officer", new Officer());

        return "register";
    }

    @PostMapping(value = {"/register"})
    public String saveOfficer(Officer officer) {
        if (!(officerService.isNameUsed(officer))) {
            officerService.saveOfficer(officer);

            return "redirect:/login";
        }


        return "redirect:/regerror";
    }
}
