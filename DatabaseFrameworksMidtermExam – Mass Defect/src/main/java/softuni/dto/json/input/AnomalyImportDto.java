package softuni.dto.json.input;

import java.io.Serializable;

public class AnomalyImportDto implements Serializable {

    private String originPlanet;

    private String teleportPlanet;

    public String getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        if (originPlanet == null) {
            throw new IllegalArgumentException("Anomaly can not have null origin planet!");
        }

        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Anomaly can not have null teleport planet!");
        }

        this.teleportPlanet = teleportPlanet;
    }
}
