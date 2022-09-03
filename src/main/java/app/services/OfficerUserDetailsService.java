package app.services;

import app.models.Officer;
import app.repositories.OfficerRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfficerUserDetailsService implements UserDetailsService {

    private final OfficerRepo officerRepo;

    public OfficerUserDetailsService(OfficerRepo officerRepo) {
        this.officerRepo = officerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Officer> officerOptional = officerRepo.findByUsername(username);
        Officer officerData = officerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return (UserDetails) officerData;
    }
}