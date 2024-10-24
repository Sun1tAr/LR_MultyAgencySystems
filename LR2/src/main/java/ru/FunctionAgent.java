package ru;

import jade.core.Agent;

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


}




