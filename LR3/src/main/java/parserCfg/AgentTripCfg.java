package parserCfg;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentTripCfg {
    @XmlElement
    private String name;
    @XmlElement
    private int length;
}
