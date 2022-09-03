package app.controllers;

import app.models.Crew;
import app.models.CrewSearchForm;
import app.services.CrewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping(value = {"/crews"})
    public String getCrews(Model model) {
        List<Crew> crews = crewService.getAll();
        boolean searched = false;
        model.addAttribute("crews", crews);
        model.addAttribute("searched", searched);
        model.addAttribute("search", new CrewSearchForm());

        return "crews";
    }

    @PostMapping(value = {"/crews"})
    public String findCrewContaining(CrewSearchForm search, Model model) {
        List<Crew> crews = crewService.findAllContaining(search);
        boolean searched = true;
        model.addAttribute("crews", crews);
        model.addAttribute("searched", searched);
        model.addAttribute("search", new CrewSearchForm());

        return "crews";
    }
}
