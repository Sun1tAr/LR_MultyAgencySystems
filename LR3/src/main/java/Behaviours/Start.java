package Behaviours;

import jade.core.behaviours.OneShotBehaviour;
import parserCfg.AgentsTripCfg;

import java.util.HashMap;
import java.util.Optional;

public class Start extends OneShotBehaviour {


    String targetAgent;
    Optional<AgentsTripCfg> cfg;

//    public Start(String nextInit, String targetAgent) {
//        this.nextInit = nextInit;
//        this.targetAgent = targetAgent;
//    }

    public Start(Optional<AgentsTripCfg> cfg, String targetAgent) {
        this.cfg = cfg;
        this.targetAgent = targetAgent;
    }


    @Override
    public void action() {
        HashMap<String, Integer> trip = new HashMap<>();
        trip.put(myAgent.getLocalName(), 0);

        if (targetAgent.equals(myAgent.getLocalName())) {
            System.out.println("Я нашел себя! - " + myAgent.getLocalName());
        } else {
            myAgent.addBehaviour(new WorkOfInit(trip, cfg, targetAgent, myAgent, 10000));
        }

    }
}
