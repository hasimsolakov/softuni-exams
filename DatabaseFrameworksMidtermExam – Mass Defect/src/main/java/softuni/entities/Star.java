package softuni.entities;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.persistence.*;

@Entity
@Table(name = "stars")
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "solar_system_id", nullable = false)
    private SolarSystem solarSystem;

    public Star(String name, SolarSystem solarSystem) {
        this.setName(name);
        this.setSolarSystem(solarSystem);
    }

    public Star() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name can not be null or empty string!");
        }

        this.name = name;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    private void setSolarSystem(SolarSystem solarSystem) {
        if (solarSystem == null) {
            throw new IllegalArgumentException("Solar System must not be null!");
        }

        this.solarSystem = solarSystem;
    }
}
