package Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import parserCfg.MessageRefactor;

public class Ender extends Behaviour {

    MessageTemplate mt;
    private String veryShortTrip;
    private int lengthOfVST = Integer.MAX_VALUE;
    private final int processingTime;
    private final long startTime;


    public Ender(int processingTime) {
        this.processingTime = processingTime;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onStart() {
        this.mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            ACLMessage blocker = new ACLMessage(ACLMessage.CANCEL);
            blocker.addReceiver(msg.getSender());
            myAgent.send(blocker);

            //TODO debugger
            myAgent.addBehaviour(new Debugger(MessageRefactor.parseMsgToHM(msg.getContent())));

            int localLengthTrip = MessageRefactor.parseMsgToTripLength(msg.getContent());
            if (localLengthTrip < this.lengthOfVST) {
                this.lengthOfVST = localLengthTrip;
                this.veryShortTrip = MessageRefactor.parseMsgToStringTrip(msg.getContent());
            }
        } else {
            block(100);
        }
    }

    @Override
    public boolean done() {
        long localTime = System.currentTimeMillis() - this.startTime;
        boolean isReady = localTime > this.processingTime;

        //TODO debugger
//        System.out.println(localTime);
        return isReady;
    }

    @Override
    public int onEnd() {
        if (this.lengthOfVST == Integer.MAX_VALUE) {
            System.out.println("Я не успел найти путь(((");
        } else {
            System.out.println("Самый короткий путь, найденный мной:\n" + this.veryShortTrip +
                    "\nС длиной: " + this.lengthOfVST);
        }
        return super.onEnd();
    }
}
