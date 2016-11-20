package softuni.dto.json.input;

import java.io.Serializable;

public class PersonImportDto implements Serializable {

    private String name;

    private String homePlanet;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Person name can not be null!");
        }

        this.name = name;
    }

    public String getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(String homePlanet) {
        if (homePlanet == null) {
            throw new IllegalArgumentException("Home planet of the Person can not be null!");
        }

        this.homePlanet = homePlanet;
    }
}
