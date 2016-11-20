package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>{
    Planet findTopByName(String name);
}
