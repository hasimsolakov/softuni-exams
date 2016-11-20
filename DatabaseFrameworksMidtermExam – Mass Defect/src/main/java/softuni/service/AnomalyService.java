package softuni.service;

import softuni.entities.Anomaly;

import java.util.List;

public interface AnomalyService {
    void save(Anomaly anomaly);

    Anomaly findById(Long id);

    List<Anomaly> findAll();
}
