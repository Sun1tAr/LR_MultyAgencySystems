package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import parserCfg.AgentsTripCfg;
import parserCfg.MessageRefactor;

import java.util.LinkedHashMap;
import java.util.Optional;

public class Receiver extends Behaviour {

    private String targetAgent;
    private final MessageTemplate initMt;
    private LinkedHashMap<String, Integer> trip;
    private final Optional<AgentsTripCfg> cfg;
    private final MessageTemplate closeRecieve;
    private Boolean isClosing = false;

    public Receiver(Optional<AgentsTripCfg> cfg) {
        this.cfg = cfg;
        this.initMt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        this.closeRecieve = MessageTemplate.MatchPerformative(ACLMessage.CANCEL);
    }

    @Override
    public void action() {
        ACLMessage initMsg = myAgent.receive(this.initMt);
        ACLMessage closeRecMsg = myAgent.receive(this.closeRecieve);
        if (initMsg != null) {
            String content = initMsg.getContent();
            this.trip = MessageRefactor.parseMsgToHM(content);
            this.targetAgent = MessageRefactor.parseMsgToTarget(content);
            myAgent.addBehaviour(new WorkOfInit(this.trip, this.cfg, this.targetAgent, myAgent, 0));
        } else if (closeRecMsg != null) {
            this.isClosing = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return this.isClosing;
    }
}
