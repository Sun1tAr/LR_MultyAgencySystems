package ru;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecieveMsg extends Behaviour {

    MessageTemplate init, info;
    float delta = 1000;
    float x, dx;
    float y0, y, y1;
    int timeWake;

    public RecieveMsg(int timeWake) {
        this.timeWake = timeWake;
    }

    @Override
    public void onStart() {
        this.init = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        this.info = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    }

    @Override
    public void action() {
        ACLMessage infoMsg = myAgent.receive(info);
        ACLMessage initMsg = myAgent.receive(init);

        if (initMsg != null) {
            myAgent.addBehaviour(new WorkOfInitiator(myAgent, timeWake, initMsg.getContent()));
        } else if (infoMsg != null) {
            String values = infoMsg.getContent();
            String[] value = values.split(";");
            x = Float.parseFloat(value[0]);
            dx = Float.parseFloat(value[1]);

            y = Calculator.calculate(myAgent, x);
            y1 = Calculator.calculate(myAgent, x + dx);
            y0 = Calculator.calculate(myAgent, x - dx);

            AID initiator = infoMsg.getSender();

            delta = Math.abs(y1 - y0);

            myAgent.addBehaviour(new SendMsgToInit(initiator, x, dx, y0, y, y1));
        } else block();

    }

    @Override
    public boolean done() {
        return false;
    }
}
