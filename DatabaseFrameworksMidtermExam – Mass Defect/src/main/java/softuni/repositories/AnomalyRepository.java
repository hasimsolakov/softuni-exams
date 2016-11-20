package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Anomaly;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {

}
