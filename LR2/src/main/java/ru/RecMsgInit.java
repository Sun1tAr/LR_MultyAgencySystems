package ru;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecMsgInit extends Behaviour {

    double delta = 0.01;

    int numberOfMsg = 0;

    float x, dx, y0, y, y1;
    float[] xx = new float[3];
    float[] yy0 = new float[3];
    float[] yy = new float[3];
    float[] yy1 = new float[3];


    String initiator;
    Boolean isNewInitiator = false;

    @Override
    public void action() {

        MessageTemplate agree = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        ACLMessage reply = myAgent.receive(agree);
        if (reply != null) {
            if (numberOfMsg < 3) {
                setNewInitiator(myAgent, reply.getSender().getLocalName());

                String rep = reply.getContent();
                String[] values = rep.split(";");

                x = Float.parseFloat(values[0]);
                dx = Float.parseFloat(values[1]);
                y0 = Float.parseFloat(values[2]);
                y = Float.parseFloat(values[3]);
                y1 = Float.parseFloat(values[4]);

                xx[0] = x - dx;
                xx[1] = x;
                xx[2] = x + dx;

                yy0[numberOfMsg] = y0;
                yy[numberOfMsg] = y;
                yy1[numberOfMsg] = y1;

                numberOfMsg++;
            }
            if (numberOfMsg == 3 && !tooSmallErr()) {
                refactorOfPoint();
                myAgent.addBehaviour(new Start(initiator, x, dx));
            }
        } else block();


    }

    @Override
    public boolean done() {
        return numberOfMsg == 3;
    }

    private void refactorOfPoint() {
        float smallX;
        smallX = Calculator.getMin(xx, yy0, yy, yy1);
        if (smallX == x) {
            this.dx = dx / 2;
        } else this.x = smallX;
    }

    private void setNewInitiator(Agent that, String sender) {
        if (isNewInitiator) return;
        if (!that.getLocalName().equals(sender)) {
            this.initiator = sender;
            isNewInitiator = true;
        }

    }

    private boolean tooSmallErr() {
        if (Math.abs(yy0[0] - yy1[0]) <= delta
                && Math.abs(yy0[1] - yy1[1]) <= delta
                && Math.abs(yy0[2] - yy1[2]) <= delta) {
            String out = "f(x) = " + Calculator.getSumY(yy) + ", x = " + this.x;
            System.err.println(out);
            return true;
        }
        return false;
    }

}
