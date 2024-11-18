package parserCfg;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "cfg")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentsTripCfg {
    @XmlElementWrapper(name = "agents")
    @XmlElement(name = "agent")
    private List<AgentTripCfg> agents;
    @XmlElement
    private int isInitiator;
    @XmlElement
    private String findedAgentlocalName;

    public List<AgentTripCfg> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentTripCfg> agents) {
        this.agents = agents;
    }

    public int getIsInitiator() {
        return isInitiator;
    }

    public void setIsInitiator(int isInitiator) {
        this.isInitiator = isInitiator;
    }

    public String getFindedAgentlocalName() {
        return findedAgentlocalName;
    }

    public void setFindedAgentlocalName(String findedAgentlocalName) {
        this.findedAgentlocalName = findedAgentlocalName;
    }
}
