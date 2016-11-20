package softuni.dto.json.input;

import java.io.Serializable;

public class AnomalyVictimsImportDto implements Serializable {

    private Long id;

    private String person;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of the anomaly to registe can not be null");
        }

        this.id = id;
    }

    public String getPerson() {
        return this.person;
    }

    public void setPerson(String person) {
        if (person == null) {
            throw new IllegalArgumentException("Person name can not be null!");
        }

        this.person = person;
    }
}
