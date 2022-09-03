package app.controllers;

import app.models.*;
import app.services.CrewService;
import app.services.SpaceShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CrewController {

    private final CrewService crewService;
    private final SpaceShipService spaceShipService;

    public CrewController(CrewService crewService, SpaceShipService spaceShipService) {
        this.crewService = crewService;
        this.spaceShipService = spaceShipService;
    }

    @GetMapping(value = {"/crews"})
    public String getCrews(Model model) {
        List<Crew> crews = crewService.getAll();
        model.addAttribute("crews", crews);
        model.addAttribute("search", new CrewSearchForm());

        return "crews";
    }

    @PostMapping(value = {"/crews"})
    public String findCrewContaining(CrewSearchForm search, Model model) {
        List<Crew> crews = crewService.findAllContaining(search);
        model.addAttribute("crews", crews);
        model.addAttribute("search", new CrewSearchForm());

        return "crews";
    }

    @GetMapping(value = {"/crews/new"})
    public String getNewCrew(Model model) {
        List<SpaceShip> spaceShips = spaceShipService.getAll();
        model.addAttribute("newCrew", new Crew());
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("ships", spaceShips);

        return "newcrew";
    }

    @PostMapping(value = {"/crews/new"})
    public String saveNewCrew(Crew crew) {
        crew.setDutyShip(spaceShipService.findByName(crew.getDutyShip().getName()));
        crewService.saveCrew(crew);

        return "redirect:/";
    }
}
