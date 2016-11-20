package softuni.dto.xml.export;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomaliesExportXMLDto {

    @XmlElement(name = "anomaly")
    private List<AnomalyExportXMLDto> anomalyExportDtos;

    public AnomaliesExportXMLDto() {
        this.setAnomalyExportDtos(new ArrayList<>());
    }

    public List<AnomalyExportXMLDto> getAnomalyExportDtos() {
        return anomalyExportDtos;
    }

    public void setAnomalyExportDtos(List<AnomalyExportXMLDto> anomalyExportDtos) {
        this.anomalyExportDtos = anomalyExportDtos;
    }
}
