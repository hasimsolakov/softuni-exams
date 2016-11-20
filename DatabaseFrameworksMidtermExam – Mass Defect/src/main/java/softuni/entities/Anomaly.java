package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "anomalies")
public class Anomaly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_planet_id", nullable = false)
    private Planet originPlanet;

    @ManyToOne
    @JoinColumn(name = "teleport_planet_id", nullable = false)
    private Planet teleportPlanet;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "anomaly_victims",
            joinColumns = @JoinColumn(name = "anomaly_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> victims;

    public Anomaly() {
    }

    public Anomaly(Planet originPlanet, Planet teleportPlanet, Set<Person> victims) {
        this.setOriginPlanet(originPlanet);
        this.setTeleportPlanet(teleportPlanet);
        this.setVictims(victims);
    }

    public Long getId() {
        return this.id;
    }

    public Planet getOriginPlanet() {
        return this.originPlanet;
    }

    private void setOriginPlanet(Planet originPlanet) {
        if (originPlanet == null) {
            throw new IllegalArgumentException("Origin planet of the Anomaly can not be null!");
        }

        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return this.teleportPlanet;
    }

    private void setTeleportPlanet(Planet teleportPlanet) {
        if (teleportPlanet == null) {
            throw new IllegalArgumentException("Teleport planet of the Anomaly can not be null!");
        }

        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        return this.victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }
}
