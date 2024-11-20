package Behaviours;

import jade.core.behaviours.OneShotBehaviour;
import parserCfg.MessageRefactor;

import java.util.LinkedHashMap;

public class Debugger extends OneShotBehaviour {

    LinkedHashMap<String, Integer> trip;
    private final int lengthTrip;

    public Debugger(LinkedHashMap<String, Integer> trip) {
        this.trip = trip;
        String msg = MessageRefactor.newMsgFromHM(this.trip, "plug");
        this.lengthTrip = MessageRefactor.parseMsgToTripLength(msg);
    }

    @Override
    public void action() {
        System.out.println(myAgent.getLocalName() + "----" + trip + "----" + this.lengthTrip);
    }
}
