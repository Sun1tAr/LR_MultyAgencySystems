package LR4;

import AgentDetector.Detector;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;

import java.util.List;

public class myAgent extends Agent {

    private AID aid;


    @Override
    protected void setup() {
        this.aid = new AID(this.getLocalName(), true);

        Behaviour publisher = new TickerBehaviour(this, 1000) {
            @Override
            protected void onTick() {
                Detector det = new Detector();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                det.startPublishing(aid, 9000);
                System.out.println(getLocalName() + " - опубликовался");
            }
        };
        this.addBehaviour(publisher);

        Behaviour finder = new TickerBehaviour(this, 5000) {

            Detector det;

            @Override
            public void onStart() {
                super.onStart();
                det = new Detector();
                det.startDiscovering(9000);
            }

            @Override
            protected void onTick() {

                List<AID> activeAgents = det.getActiveAgents();
                System.out.println(getLocalName() + " --- нашел таких агентов --- " + activeAgents);
            }
        };

        this.addBehaviour(finder);

    }

}
