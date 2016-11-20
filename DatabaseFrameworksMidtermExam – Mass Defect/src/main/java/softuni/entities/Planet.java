package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sun_id", nullable = false)
    private Star sun;

    @ManyToOne
    @JoinColumn(name = "solar_system_id", nullable = false)
    private SolarSystem solarSystem;

    @OneToMany(mappedBy = "originPlanet", fetch = FetchType.EAGER)
    private Set<Anomaly> originatedAnomalies;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teleportPlanet")
    private Set<Anomaly> teleportedAnomalies;

    public Planet(String name, Star sun, SolarSystem solarSystem) {
        this.setName(name);
        this.setSun(sun);
        this.setSolarSystem(solarSystem);
    }

    public Planet() { }


    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name can not be null or empty string!");
        }

        this.name = name;
    }

    public Star getSun() {
        return sun;
    }

    private void setSun(Star sun) {
        if (sun == null) {
            throw new IllegalArgumentException("Sun of the planet must not be null!");
        }

        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    private void setSolarSystem(SolarSystem solarSystem) {
        if (solarSystem == null) {
            throw new IllegalArgumentException("Solar system of the planet can not be null!");
        }

        this.solarSystem = solarSystem;
    }

    public Set<Anomaly> getOriginatedAnomalies() {
        return originatedAnomalies;
    }

    public void setOriginatedAnomalies(Set<Anomaly> originatedAnomalies) {
        this.originatedAnomalies = originatedAnomalies;
    }

    public Set<Anomaly> getTeleportedAnomalies() {
        return teleportedAnomalies;
    }

    public void setTeleportedAnomalies(Set<Anomaly> teleportedAnomalies) {
        this.teleportedAnomalies = teleportedAnomalies;
    }
}
