package app.controllers;

import app.models.Crew;
import app.services.CrewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("crews", crews);

        return "crews";
    }
}
