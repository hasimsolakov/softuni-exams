package softuni.dto.json.export;

import java.io.Serializable;

public class TeleportPlanetExportDto implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
