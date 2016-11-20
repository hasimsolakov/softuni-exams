package softuni.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Planet;
import softuni.repositories.PlanetRepository;
import softuni.service.PlanetService;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public void save(Planet planet) {
        this.planetRepository.save(planet);
    }

    @Override
    public Planet findByName(String name) {
        Planet planet = this.planetRepository.findTopByName(name);
        if (planet == null) {
            throw new IllegalArgumentException("Planet with the given name was not found in the Database!");
        }

        return planet;
    }

    @Override
    public List<Planet> findAll() {
        return this.planetRepository.findAll();
    }
}
