package app.controllers;

import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.services.SpaceShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpaceShipController {

    private final SpaceShipService spaceShipService;

    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;
    }

    @GetMapping(value = {"/spaceship"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "spaceship_new";
    }

    @PostMapping(value = {"/spaceship"})
    public String saveShip(SpaceShip spaceShip) {
        spaceShipService.saveShip(spaceShip);

        return "redirect:/fleet";
    }

    @GetMapping(value = {"/fleet"})
    public String getAllShips(Model model) {
        List<SpaceShip> spaceShips = spaceShipService.getAll();
        model.addAttribute("spaceships", spaceShips);

        return "fleet";
    }

    @GetMapping(value = {"/fleet/active"})
    public String getActiveShips(Model model) {
        List<SpaceShip> spaceShips = spaceShipService.getActiveShips();
        model.addAttribute("spaceships", spaceShips);

        return "fleet";
    }

    @GetMapping(value = {"/fleet/{name}"})
    public String getShipCrew(@PathVariable String name, Model model) {
        SpaceShip spaceShip = spaceShipService.findByName(name);
        model.addAttribute("spaceship", spaceShip);

        return "crew";
    }

}
