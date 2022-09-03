package app.services;

import app.models.Officer;
import app.models.SpaceShip;
import app.repositories.OfficerRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OfficerService implements UserDetailsService {

    private final OfficerRepo officerRepo;

    private final PasswordEncoder encoder;

    public OfficerService(OfficerRepo officerRepo, PasswordEncoder encoder) {
        this.officerRepo = officerRepo;
        this.encoder = encoder;
    }

    public List<Officer> getAll() {
        return new ArrayList<>((Collection) officerRepo.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Officer> officerOptional = officerRepo.findByUsername(username);
        Officer officerData = officerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return (UserDetails) officerData;
    }

    @Transactional
    public void saveOfficer(Officer officer) {
        officer.setPassword(encoder.encode(officer.getPassword()));
        officerRepo.save(officer);
    }

    public boolean isNameUsed(Officer officer) {
        List<Officer> officers = getAll();

        for (Officer dbOfficer : officers) {
            if (officer.getUsername().equals(dbOfficer.getUsername())) {
                return true;
            }
        }

        return false;
    }
}
