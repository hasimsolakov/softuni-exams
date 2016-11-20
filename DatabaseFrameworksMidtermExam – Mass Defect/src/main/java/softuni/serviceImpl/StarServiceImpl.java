package softuni.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.constants.Messages;
import softuni.entities.Star;
import softuni.io.Writer;
import softuni.repositories.StarRepository;
import softuni.service.StarService;

@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private StarRepository starRepository;

    @Override
    public void save(Star star) {
        this.starRepository.save(star);
    }

    @Override
    public Star findByName(String name) {
        Star star = this.starRepository.findTopByName(name);
        if (star == null) {
            throw new IllegalArgumentException("Star with the given name was not found!");
        }

        return star;
    }
}
