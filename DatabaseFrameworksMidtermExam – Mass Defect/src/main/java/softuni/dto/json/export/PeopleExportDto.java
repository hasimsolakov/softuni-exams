package softuni.dto.json.export;

import java.io.Serializable;

public class PeopleExportDto implements Serializable {

    private String name;

    private HomePlanetExportDto homePlanet;

    public PeopleExportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomePlanetExportDto getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(HomePlanetExportDto homePlanet) {
        this.homePlanet = homePlanet;
    }
}
