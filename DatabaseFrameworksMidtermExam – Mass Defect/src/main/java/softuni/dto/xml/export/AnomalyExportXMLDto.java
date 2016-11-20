package softuni.dto.xml.export;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "anomaly")
public class AnomalyExportXMLDto {

    @XmlAttribute(name = "id", required = true)
    private Long id;

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimExportXMLDto> victims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimExportXMLDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimExportXMLDto> victims) {
        this.victims = victims;
    }
}
