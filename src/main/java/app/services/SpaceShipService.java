package app.services;

import app.models.SpaceShip;
import app.repositories.SpaceShipRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@Service
public class SpaceShipService {

    private final SpaceShipRepo spaceShipRepo;

    public SpaceShipService(SpaceShipRepo spaceShipRepo) {
        this.spaceShipRepo = spaceShipRepo;
    }

    public List<SpaceShip> getAll() {
        return new ArrayList<>((Collection) spaceShipRepo.findAll());
    }

    public List<SpaceShip> getActiveShips() {
        return new ArrayList<>((Collection) spaceShipRepo.findByIsActiveTrue());
    }

    public SpaceShip findByName(String name) {
        return spaceShipRepo.findByName(name);
    }

    public SpaceShip saveShip(SpaceShip spaceShip) {
        spaceShipRepo.save(spaceShip);

        return spaceShip;
    }
}
