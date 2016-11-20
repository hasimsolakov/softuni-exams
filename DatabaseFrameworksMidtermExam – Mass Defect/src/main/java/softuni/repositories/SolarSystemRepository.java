package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.SolarSystem;

@Repository
public interface SolarSystemRepository extends JpaRepository<SolarSystem, Long> {

    SolarSystem findTopByName(String name);
}
