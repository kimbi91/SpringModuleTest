package app.repositories;

import app.models.Crew;
import app.models.CrewSearchForm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrewRepo extends CrudRepository<Crew, Long> {

    List<Crew> findCrewByCrewNameContaining(String search);
}
