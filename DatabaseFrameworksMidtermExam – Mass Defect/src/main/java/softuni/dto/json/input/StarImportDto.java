package softuni.dto.json.input;

import java.io.Serializable;

public class StarImportDto implements Serializable {

    private String name;

    private String solarSystem;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Star can not have null name!");
        }

        this.name = name;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        if (solarSystem == null) {
            throw new IllegalArgumentException("Star can not have null Solar System!");
        }

        this.solarSystem = solarSystem;
    }
}
