package softuni.service;


import softuni.entities.SolarSystem;

public interface SolarSystemService {

    void save(SolarSystem solarSystem);
    SolarSystem findByName(String name);
}
