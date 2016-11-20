package softuni.dto.json.export;

import java.io.Serializable;

public class AnomalyExportDto implements Serializable {

    private Long id;

    private OriginPlanetExportDto originPlanet;

    private TeleportPlanetExportDto teleportPlanet;

    private int victimsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OriginPlanetExportDto getOriginPlanetExportDto() {
        return originPlanet;
    }

    public void setOriginPlanet(OriginPlanetExportDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public TeleportPlanetExportDto getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(TeleportPlanetExportDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public int getVictimsCount() {
        return victimsCount;
    }

    public void setVictimsCount(int victimsCount) {
        this.victimsCount = victimsCount;
    }
}
