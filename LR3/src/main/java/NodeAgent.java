import parserCfg.AgentTripCfg;
import parserCfg.AgentsTripCfg;
import parserCfg.XmlSerialization;

import java.io.File;
import java.util.Optional;

//Вариант 17
public class NodeAgent {
    public static void main(String[] args) {
        
        Optional<AgentsTripCfg> cfg = XmlSerialization.deserialize("LR3/src/main/resources/Bob1.xml", AgentsTripCfg.class);
        System.out.println(cfg);
        int isInitiator = cfg.get().getIsInitiator();
        String endAgent = cfg.get().getFindedAgentlocalName();
        for (AgentTripCfg agent : cfg.get().getAgents()) {
            
        }

    }
}
