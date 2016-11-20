package softuni.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Anomaly;
import softuni.repositories.AnomalyRepository;
import softuni.service.AnomalyService;

import java.util.List;

@Service
public class AnomalyServiceImpl implements AnomalyService {

    @Autowired
    private AnomalyRepository anomalyRepository;



    @Override
    public void save(Anomaly anomaly) {
        this.anomalyRepository.save(anomaly);
    }


    @Override
    public Anomaly findById(Long id) {
        Anomaly anomaly = this.anomalyRepository.findOne(id);
        if (anomaly == null) {
            throw new IllegalArgumentException("Anomaly with the given id was not found in the Database!");
        }

        return anomaly;
    }

    @Override
    public List<Anomaly> findAll() {
        return this.anomalyRepository.findAll();
    }
}
