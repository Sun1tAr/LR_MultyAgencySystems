package Behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import parserCfg.AgentsTripCfg;
import parserCfg.MessageRefactor;

import java.util.LinkedHashMap;
import java.util.Optional;

public class Start extends OneShotBehaviour {


    private final String targetAgent;
    private final Optional<AgentsTripCfg> cfg;
    private final int delayTimeToDebug;
    private final int workingTime;
    private final int processingTime;

    public Start(Optional<AgentsTripCfg> cfg, String targetAgent,
                 int delayTimeToDebug, int workingTime, int processingTime) {
        this.cfg = cfg;
        this.targetAgent = targetAgent;
        this.delayTimeToDebug = delayTimeToDebug;
        this.workingTime = workingTime + delayTimeToDebug;
        this.processingTime = processingTime;
    }


    @Override
    public void action() {
        LinkedHashMap<String, Integer> trip = new LinkedHashMap<>();
        trip.put(myAgent.getLocalName(), 0);

        if (targetAgent.equals(myAgent.getLocalName())) {

            LinkedHashMap<String, Integer> localTrip = new LinkedHashMap<>();
            localTrip.put(myAgent.getLocalName(), 0);

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            String content = MessageRefactor.newMsgFromHM(localTrip, this.targetAgent);
            msg.setContent(content);
            String initiator = MessageRefactor.parseMsgToCommonInitName(content);
            msg.addReceiver(new AID(initiator, false));
            myAgent.send(msg);

//            System.out.println("Я нашел себя! - " + myAgent.getLocalName());
        } else {
            myAgent.addBehaviour(new WorkOfInit(trip, cfg, targetAgent, myAgent, delayTimeToDebug));
            myAgent.addBehaviour(new ReceiveTrip(myAgent, this.workingTime, this.processingTime));
        }
    }
}
