package softuni.dto.json.input;

import java.io.Serializable;

public class PlanetImportDto implements Serializable {

    private String name;

    private String solarSystem;

    private String sun;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Planet name can not be null!");
        }

        this.name = name;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        if (solarSystem == null) {
            throw new IllegalArgumentException("Planet can not have null solar system!");
        }

        this.solarSystem = solarSystem;
    }

    public String getSun() {
        return this.sun;
    }

    public void setSun(String sun) {
        if (sun == null) {
            throw new IllegalArgumentException("Planet can not have null sun!");
        }

        this.sun = sun;
    }
}
