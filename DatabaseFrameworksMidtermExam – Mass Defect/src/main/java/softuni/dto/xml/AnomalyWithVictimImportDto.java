package softuni.dto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anomaly")
public class AnomalyWithVictimImportDto implements Serializable {

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimImportDto> victims;

    public AnomalyWithVictimImportDto () {

    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        if (originPlanet == null) {
            throw new IllegalArgumentException("Origin planet of an anomaly can not be null!");
        }

        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Teleport planet of an anomaly can not be null!");
        }

        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimImportDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimImportDto> victims) {
        this.victims = victims;
    }
}
