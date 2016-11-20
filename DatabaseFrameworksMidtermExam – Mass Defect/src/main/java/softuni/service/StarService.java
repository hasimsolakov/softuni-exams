package softuni.service;


import softuni.entities.Star;

public interface StarService {

    void save(Star star);
    Star findByName(String name);
}
