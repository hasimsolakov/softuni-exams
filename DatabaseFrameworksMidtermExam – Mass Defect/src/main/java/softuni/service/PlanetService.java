package softuni.service;


import softuni.entities.Planet;

import java.util.List;

public interface PlanetService {
    void save(Planet planet);
    Planet findByName(String name);
    List<Planet> findAll();
}
