package softuni.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Person;
import softuni.repositories.PersonRepository;
import softuni.service.PersonService;

import java.util.List;

@Service
class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void save(Person person) {
        this.personRepository.save(person);
    }

    @Override
    public Person findByName(String name) {
       Person person = this.personRepository.findTopByName(name);
        if (person == null) {
            throw new IllegalArgumentException("Person with the given name was not found in the database!");
        }

        return person;
    }

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }
}
