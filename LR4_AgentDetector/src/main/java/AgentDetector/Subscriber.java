package AgentDetector;

import com.sun.jna.NativeLibrary;
import jade.core.AID;
import lombok.Getter;
import lombok.SneakyThrows;
import org.pcap4j.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Subscriber {

    static {
        NativeLibrary.addSearchPath("wpcap", "C:\\Windows\\System32\\Npcap");
    }
    @Getter
    List<AID> activeAgents = new ArrayList<>();
    private PcapHandle pcapHandle;

    @SneakyThrows
    public void start(int port){

        List<PcapNetworkInterface> allDevs = Pcaps.findAllDevs();
        PcapNetworkInterface pcapNetworkInterface = allDevs.stream()
                .filter(e -> e.getName().equals("\\Device\\NPF_Loopback"))
                .findAny()
                .orElseThrow();

        pcapHandle = pcapNetworkInterface.openLive(1500, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 50);

        Thread t = new Thread(() -> {
            try {
                pcapHandle.setFilter("ip proto \\udp && dst port "+port, BpfProgram.BpfCompileMode.NONOPTIMIZE);
                pcapHandle.loop(-1, (PacketListener) packet -> {
                    byte[] rawData = packet.getRawData();


                    String aid = new String(rawData, 32, rawData.length - 32);
                    Optional<Agents> deserialize = JSerializator.deserialize(aid, Agents.class);
                    activeAgents.clear();
                    activeAgents.add(deserialize.get().getName());

//                    System.out.println(aid);

                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        t.start();

    }


}
