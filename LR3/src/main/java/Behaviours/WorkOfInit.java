package Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.SneakyThrows;
import parserCfg.AgentTripCfg;
import parserCfg.AgentsTripCfg;

import java.util.HashMap;
import java.util.Optional;


public class WorkOfInit extends WakerBehaviour {

    HashMap<String, Integer> trip;
    Optional<AgentsTripCfg> cfg;
    String targetAgent;

    public WorkOfInit(HashMap<String, Integer> trip, Optional<AgentsTripCfg> cfg,String targetAgent ,Agent a, long timeout) {
        super(a, timeout);
        this.cfg = cfg;
        this.targetAgent = targetAgent;
        this.trip = trip;
    }

    @SneakyThrows
    @Override
    protected void onWake() {
        super.onWake();

        if (trip.containsKey(myAgent.getLocalName()) && targetAgent.equals(myAgent.getLocalName())){
            myAgent.addBehaviour(new PreEnder(this.trip));
        } else {
            //TODO я не цель и у меня есть свзяи - разослать сообщения по связям
            for (AgentTripCfg agent : cfg.get().getAgents()) {
                if (trip.containsKey(agent.getName())){
                    continue;
                }
                HashMap <String,Integer> localTrip= new HashMap<>();
                localTrip.putAll(trip);
                localTrip.put(agent.getName(), agent.getLength());
                ACLMessage msg = new ACLMessage(0);
                msg.setContentObject(localTrip);
                msg.setContent(this.targetAgent);
                msg.addReceiver(new AID(agent.getName(),false));
                myAgent.send(msg);


            }

        }



        //TODO я - не цель и у меня нет связей - 0
    }
}
