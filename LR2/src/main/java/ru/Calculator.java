package ru;

import jade.core.Agent;

public class Calculator {

    public static float calculate(Agent a, float x){
        return switch (a.getLocalName()) {
            case ("Bob1") -> Functions.one(x);
            case ("Bob2") -> Functions.two(x);
            case ("Bob3") -> Functions.three(x);
            default -> throw new IllegalStateException("Unexpected value: " + a.getLocalName());
        };
    }

    public static float getMin(float[] xx, float[] yy0, float[] yy, float[] yy1){
        float min = Float.MAX_VALUE;
        float y0, y, y1;
        y0 = getSumY(yy0);
        y = getSumY(yy);
        y1 = getSumY(yy1);
        if (y0 < y && y < y1) return xx[0];
        if (y0 > y && y < y1) return xx[1];
        if (y0 > y && y > y1) return xx[2];
        if (y0 < y && y > y1) {
            if (y0 < y1) return xx[0];
            if (y0 > y1) return xx[2];

        }
        throw new IllegalArgumentException("Расчет некорректен");

    }

    public static float getSumY(float[] yy){
        float y0 = 0;
        for (float y: yy){
            y0 += y;
        }
        return y0;
    }



}
