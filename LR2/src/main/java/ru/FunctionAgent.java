package ru;

import jade.core.AID;
import jade.core.Agent;

import java.util.Objects;
// Взял вариант 3 тк 18 по списку без смещения
//Ищу минимум

public class FunctionAgent extends Agent {

    float x, dx;
    @Override
    protected void setup() {
        System.out.println("Hi - " + this.getLocalName());

        if (this.getLocalName().equals("Bob1")) {
            this.x = (float) (Math.random() * 100);
            this.dx = (float) Math.abs(Math.random() * 100);
            this.addBehaviour(new Start(this.getLocalName(), x, dx));
        } else {
            this.addBehaviour(new RecieveMsg(1000));

        }
    }
    AID two, three;
    private void setRecievers(Agent a){
        switch(a.getLocalName()){
            case("Bob1"): {
                two = new AID("Bob2", false);
                three = new AID("Bob3", false);
            }
            case("Bob2"): {
                two = new AID("Bob3", false);
                three = new AID("Bob1", false);
            }
            case("Bob3"): {
                two = new AID("Bob1", false);
                three = new AID("Bob2", false);
            }
        }
    }
}




