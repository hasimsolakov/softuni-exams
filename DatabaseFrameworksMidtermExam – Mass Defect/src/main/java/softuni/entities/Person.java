package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "home_planet_id", nullable = false)
    private Planet homePlanet;

    @ManyToMany(mappedBy = "victims", fetch = FetchType.EAGER)
    private Set<Anomaly> anomalies;

    public Person(String name, Planet homePlanet) {
        this.setName(name);
        this.setHomePlanet(homePlanet);
    }

    public Person(String name, Planet homePlanet, Set<Anomaly> anomalies) {
        this(name, homePlanet);
        this.setAnomalies(anomalies);
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name of the Person can not be null or empty string!");
        }

        this.name = name;
    }

    public Planet getHomePlanet() {
        return homePlanet;
    }

    private void setHomePlanet(Planet homePlanet) {
        if (homePlanet == null) {
            throw new IllegalArgumentException("Home planet of the person can not be null!");
        }

        this.homePlanet = homePlanet;
    }

    public Set<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(Set<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
