package softuni.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "victim")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimImportDto implements Serializable {

    @XmlAttribute(name = "name", required = true)
    private String name;

    public VictimImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of the victim can not be null!");
        }

        this.name = name;
    }
}
