package ru;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Start extends OneShotBehaviour {

    String currentInitiator;
    float x, dx;


    public Start(String nextInitiator, float x, float dx) {
        this.currentInitiator = nextInitiator;
        this.x = x;
        this.dx = dx;
    }

    @Override
    public void action() {
        System.out.println("Circle--------" + myAgent.getLocalName());
        String values = x + ";" + dx;
        ACLMessage st = new ACLMessage(ACLMessage.REQUEST);
        AID reciever = new AID(currentInitiator, false);
        st.addReceiver(reciever);
        st.setContent(values);
        myAgent.send(st);

    }

    @Override
    public void onStart() {

        myAgent.addBehaviour(new RecieveMsg(1));
    }



}
