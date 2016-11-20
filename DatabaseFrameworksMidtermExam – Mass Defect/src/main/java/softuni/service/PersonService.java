package softuni.service;

import softuni.entities.Person;

import java.util.List;

public interface PersonService {
    void save(Person person);
    Person findByName(String name);
    List<Person> findAll();
}
