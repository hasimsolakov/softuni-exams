package softuni.entities;

import javax.persistence.*;

@Entity
@Table(name = "solar_systems")
public class SolarSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public SolarSystem() {
    }

    public SolarSystem(String name) {
        this.setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name can not be null!");
        }

        this.name = name;
    }
}
