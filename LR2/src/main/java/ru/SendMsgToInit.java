package ru;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendMsgToInit extends OneShotBehaviour {

    float x, dx, y0, y, y1;
    AID initiator;

    public SendMsgToInit(AID initiator, float x, float dx, float y0, float y, float y1) {
        this.y0 = y0;
        this.y = y;
        this.y1 = y1;
        this.initiator = initiator;
        this.x = x;
        this.dx = dx;
    }


    @Override
    public void action() {
        ACLMessage reply = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        String rep = x + ";" + dx + ";" + y0 + ";" + y + ";" + y1;
        reply.setContent(rep);
        reply.addReceiver(initiator);
        myAgent.send(reply);
    }
}
