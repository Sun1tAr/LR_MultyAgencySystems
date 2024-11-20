package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class ReceiveTrip extends WakerBehaviour {

    private final int processingTime;

    public ReceiveTrip(Agent a, long timeout, int processingTime) {
        super(a, timeout);
        this.processingTime = processingTime;
    }

    @Override
    protected void onWake() {
        super.onWake();
        myAgent.addBehaviour(new Ender(this.processingTime));
    }
}
