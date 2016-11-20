package softuni.dto.json.input;


import java.io.Serializable;

public class SolarSystemImportDto implements Serializable {

    private String name;

    public SolarSystemImportDto () { }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Solar system can not have null name!");
        }

        this.name = name;
    }
}
