package Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import parserCfg.AgentTripCfg;
import parserCfg.AgentsTripCfg;
import parserCfg.MessageRefactor;

import java.util.LinkedHashMap;
import java.util.Optional;


public class WorkOfInit extends WakerBehaviour {

    private final LinkedHashMap<String, Integer> trip;
    private final Optional<AgentsTripCfg> cfg;
    private final String targetAgent;

    public WorkOfInit(LinkedHashMap<String, Integer> trip, Optional<AgentsTripCfg> cfg, String targetAgent, Agent a, long timeout) {
        super(a, timeout);
        this.cfg = cfg;
        this.targetAgent = targetAgent;
        this.trip = trip;
    }

    @Override
    protected void onWake() {
        super.onWake();

        if (trip.containsKey(myAgent.getLocalName()) && targetAgent.equals(myAgent.getLocalName())) {

            LinkedHashMap<String, Integer> localTrip = new LinkedHashMap<>();
            localTrip.putAll(this.trip);

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            String content = MessageRefactor.newMsgFromHM(localTrip, this.targetAgent);
            msg.setContent(content);
            String initiator = MessageRefactor.parseMsgToCommonInitName(content);
            msg.addReceiver(new AID(initiator, false));
            myAgent.send(msg);

            //TODO debugger
//            myAgent.addBehaviour(new Debugger(this.trip));
        } else {

            for (AgentTripCfg agent : cfg.get().getAgents()) {
                if (trip.containsKey(agent.getName())) {
                    continue;
                }
                LinkedHashMap<String, Integer> localTrip = new LinkedHashMap<>();
                localTrip.putAll(trip);
                localTrip.put(agent.getName(), agent.getLength());
                ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                String content = MessageRefactor.newMsgFromHM(localTrip, this.targetAgent);
                msg.setContent(content);
                msg.addReceiver(new AID(agent.getName(), false));
                myAgent.send(msg);
            }
        }
    }
}
