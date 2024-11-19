package Agents;


import Behaviours.Start;
import jade.core.Agent;
import parserCfg.AgentsTripCfg;
import parserCfg.XmlSerialization;

import java.util.Optional;

public class NodeAgent extends Agent {

    Optional<AgentsTripCfg> cfg;


    @Override
    protected void setup() {
        this.setCfg();
        if (cfg.get().getIsInitiator() == 1){
            this.addBehaviour(new Start(this.cfg, cfg.get().getTargetAgentlocalName()));
        }
//        this.addBehaviour(Receiver(targetAgent));
    }


    protected void setCfg() {
        StringBuilder s = new StringBuilder("LR3/src/main/resources/");
        s.append(this.getLocalName());
        s.append(".xml");
        String path = s.toString();

        this.cfg = XmlSerialization.deserialize(path, AgentsTripCfg.class);

    }
}
