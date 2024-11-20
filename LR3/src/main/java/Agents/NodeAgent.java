package Agents;

import Behaviours.Receiver;
import Behaviours.Start;
import jade.core.Agent;
import parserCfg.AgentsTripCfg;
import parserCfg.XmlSerialization;

import java.util.Optional;

public class NodeAgent extends Agent {

    private final int delayTimeToDebug = 0;
    private final int workingTime = 10000;
    private final int processingTime = 1000;

    Optional<AgentsTripCfg> cfg;

    @Override
    protected void setup() {
        this.setCfg();
        if (cfg.get().getIsInitiator() == 1) {
            this.addBehaviour(new Start(this.cfg, cfg.get().getTargetAgentlocalName(),
                    this.delayTimeToDebug, this.workingTime, this.processingTime));
        }
        this.addBehaviour(new Receiver(this.cfg));
    }

    protected void setCfg() {
        String path = "LR3/src/main/resources/" + this.getLocalName() +
                ".xml";

        this.cfg = XmlSerialization.deserialize(path, AgentsTripCfg.class);

    }
}
