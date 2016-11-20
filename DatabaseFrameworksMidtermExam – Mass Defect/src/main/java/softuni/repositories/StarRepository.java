package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Star;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
    Star findTopByName(String name);
}
