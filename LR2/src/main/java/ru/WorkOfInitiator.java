package ru;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class WorkOfInitiator extends WakerBehaviour {

    String values;
    Agent agent;

    public WorkOfInitiator(Agent a, long timeout, String values) {
        super(a, timeout);
        this.values = values;
        this.agent = a;
    }

    @Override
    protected void onWake() {
        ACLMessage msgToHelpers = new ACLMessage(ACLMessage.INFORM);
        msgToHelpers.addReceiver(new AID("Bob1", false));
        msgToHelpers.addReceiver(new AID("Bob2", false));
        msgToHelpers.addReceiver(new AID("Bob3", false));
        msgToHelpers.setContent(this.values);
        myAgent.send(msgToHelpers);

        myAgent.addBehaviour(new RecMsgInit());
    }

    @Override
    public void onStart() {
        myAgent.addBehaviour(new RecieveMsg(1));
    }

}
