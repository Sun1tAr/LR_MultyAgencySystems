package Behaviours;

import jade.core.behaviours.OneShotBehaviour;

import java.util.HashMap;

public class PreEnder extends OneShotBehaviour {

    HashMap<String, Integer> trip;

    public PreEnder(HashMap<String, Integer> trip) {
        this.trip = trip;
    }

    @Override
    public void action() {
        System.out.println(trip);
    }
}
