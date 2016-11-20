package softuni.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.SolarSystem;
import softuni.repositories.SolarSystemRepository;
import softuni.service.SolarSystemService;

@Service
public class SolarSystemServiceImpl implements SolarSystemService {

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Override
    public void save(SolarSystem solarSystem) {
        this.solarSystemRepository.save(solarSystem);
    }

    @Override
    public SolarSystem findByName(String name) {
        SolarSystem solarSystem = this.solarSystemRepository.findTopByName(name);
        if (solarSystem  == null) {
            throw new IllegalArgumentException("Solar System with the given name was not found in the DataBase!");
        }

        return solarSystem;
    }
}
