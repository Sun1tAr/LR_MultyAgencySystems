//package Behaviours;
//
//import jade.core.behaviours.OneShotBehaviour;
//import parserCfg.AgentsTripCfg;
//import parserCfg.XmlSerialization;
//
//import java.util.Optional;
//
//public class Parsing extends OneShotBehaviour {
//
//    Optional<AgentsTripCfg> cfg;
//
//    @Override
//    public void action() {
//        StringBuilder s = new StringBuilder("LR3/src/main/resources/");
//        s.append(myAgent.getLocalName());
//        s.append(".xml");
//        String path = s.toString();
//
//        Optional<AgentsTripCfg> cfg = XmlSerialization.deserialize(path, AgentsTripCfg.class);
//
//        int isInitiator = cfg.get().getIsInitiator();
//        String targetAgent = cfg.get().getTargetAgentlocalName();
//
//        myAgent.addBehaviour(cfg));
//
//
//    }
//
//}
