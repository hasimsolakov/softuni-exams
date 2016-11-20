package softuni.dto.json.export;

import java.io.Serializable;

public class PlanetExportDto implements Serializable {

    private String name;

    public PlanetExportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
