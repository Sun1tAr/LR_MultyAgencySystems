package AgentDetector;

import com.sun.jna.NativeLibrary;
import jade.core.AID;
import lombok.SneakyThrows;
import org.pcap4j.core.*;

import java.util.*;

public class Subscriber {

    private static final int DELAY_TIME_TO_CLEARING = 5000;

    static {
        NativeLibrary.addSearchPath("wpcap", "C:\\Windows\\System32\\Npcap");
    }

    private long startTime = System.currentTimeMillis();
    private Set<AID> uniqueActiveAgents;
    private PcapHandle pcapHandle;

    @SneakyThrows
    public void start(int port) {
        uniqueActiveAgents = new HashSet<>();
        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        PcapNetworkInterface pcapNetworkInterface = allDevs.stream()
                .filter(e -> e.getName().equals("\\Device\\NPF_Loopback"))
                .findAny()
                .orElseThrow();

        pcapHandle = pcapNetworkInterface.openLive(1500, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 50);

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    pcapHandle.setFilter("ip proto \\udp && dst port " + port, BpfProgram.BpfCompileMode.NONOPTIMIZE);
                    pcapHandle.loop(-1, (PacketListener) packet -> {
                        byte[] rawData = packet.getRawData();
                        String aid = new String(rawData, 32, rawData.length - 32);
                        Optional<Agents> deserialize = JSerializator.deserialize(aid, Agents.class);
                        uniqueActiveAgents.add(deserialize.get().getName());
                        if (System.currentTimeMillis() - startTime >= DELAY_TIME_TO_CLEARING) {
                            uniqueActiveAgents = new HashSet<>();
                            startTime = System.currentTimeMillis();
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();


    }

    public synchronized List<AID> getActiveAgents() {
        List<AID> activeAgents = new ArrayList<>();
        activeAgents.addAll(uniqueActiveAgents);
        return activeAgents;
    }

}
