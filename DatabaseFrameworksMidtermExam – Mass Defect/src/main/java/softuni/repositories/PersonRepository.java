package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findTopByName(String name);
}
