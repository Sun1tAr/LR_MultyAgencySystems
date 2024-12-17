package Agents;

import Behaviours.Receiver;
import Behaviours.Start;
import jade.core.Agent;
import parserCfg.AgentsTripCfg;
import parserCfg.XmlSerialization;

import java.util.Optional;

public class NodeAgent extends Agent {

    private final static int DELAY_TIME_TO_DEBUG = 0;
    private final static int WORKING_TIME = 10000;
    private final static int PROCESSING_TIME = 1000;
    private Optional<AgentsTripCfg> cfg;

    @Override
    protected void setup() {
        setCfg();
        if (cfg.get().getIsInitiator() == 1) {
            addBehaviour(new Start(cfg, cfg.get().getTargetAgentlocalName(),
                    DELAY_TIME_TO_DEBUG, WORKING_TIME, PROCESSING_TIME));
        }
        addBehaviour(new Receiver(cfg));
    }

    protected void setCfg() {
        String path = "LR3/src/main/resources/" + this.getLocalName() +
                ".xml";
        cfg = XmlSerialization.deserialize(path, AgentsTripCfg.class);

    }
}
