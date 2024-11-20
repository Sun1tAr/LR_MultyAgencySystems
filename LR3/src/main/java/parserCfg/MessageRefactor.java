package parserCfg;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageRefactor {

    public static String newMsgFromHM(HashMap<String, Integer> hashMap, String targetAgent) {
        StringBuilder msg = new StringBuilder();
        int count = 0;
        StringBuilder trip = new StringBuilder();
        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
            trip.append(stringIntegerEntry.toString());
            trip.append("/");
            count++;
        }
        msg.append(count);
        msg.append(",");
        msg.append(targetAgent);
        msg.append(",");
        msg.append(trip);
        return msg.toString();
    }

    public static LinkedHashMap<String, Integer> parseMsgToHM(String msg) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        String[] blocks = msg.split(",");
        int count = Integer.parseInt(blocks[0]);
        String agents = blocks[2];
        for (String s : agents.split("/")) {
            String[] agent = s.split("=");
            map.put(agent[0], Integer.valueOf(agent[1]));
        }
        return map;
    }

    public static String parseMsgToTarget(String msg) {
        String[] blocks = msg.split(",");
        return blocks[1];
    }

    public static String parseMsgToStringTrip(String msg) {
        StringBuilder stringTrip = new StringBuilder();
        String[] trips = msg.split(",");
        String[] nodes = trips[2].split("/");
        for (int i = 0; i < nodes.length; i++) {
            String[] splitNode = nodes[i].split("=");
            stringTrip.append(splitNode[0]);
            if (i != nodes.length - 1) {
                stringTrip.append(" -> ");
            }
        }

        return stringTrip.toString();
    }

    public static int parseMsgToTripLength(String msg) {
        int tripLength = 0;
        String[] trips = msg.split(",");
        String[] nodes = trips[2].split("/");
        for (int i = 0; i < nodes.length; i++) {
            String[] splitNode = nodes[i].split("=");
            tripLength += Integer.parseInt(splitNode[1]);

        }

        return tripLength;
    }

    public static String parseMsgToCommonInitName(String msg) {
        StringBuilder commonInitName = new StringBuilder();
        String[] trips = msg.split(",");
        String[] nodes = trips[2].split("/");
        for (int i = 0; i < 1; i++) {
            String[] splitNode = nodes[i].split("=");
            commonInitName.append(splitNode[0]);
        }
        return commonInitName.toString();
    }

}
