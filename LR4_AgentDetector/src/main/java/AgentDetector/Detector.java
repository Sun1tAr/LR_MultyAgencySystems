package AgentDetector;

import jade.core.AID;

import java.util.List;
import java.util.Optional;

public class Detector implements AgentDetector{

    Subscriber sub;
    List<AID> activeAgents;

    @Override
    public void startPublishing(AID aid, int port) {
        String agentData;
        Optional<String> serialize = JSerializator.serialize(aid);
        if (serialize.isPresent()){
            agentData = serialize.get();
        } else {
            agentData = "";
            serialize.orElseThrow();
        }

        Publisher publisher = new Publisher();
        publisher.create("127.0.0.1", 9000);
        Thread senderThread = new Thread(() -> {
            while(true) {

                publisher.send(agentData);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        senderThread.start();
    }

    @Override
    public void startDiscovering(int port) {
        sub = new Subscriber();
        sub.start(port);
    }

    @Override
    public List<AID> getActiveAgents() {
        activeAgents = sub.getActiveAgents();
        return activeAgents;

    }
}
