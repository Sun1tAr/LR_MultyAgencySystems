package ru;

import jade.core.Agent;

public class Calculator {

    public static float calculate(Agent a, float x) {
        return switch (a.getLocalName()) {
            case ("Bob1") -> Functions.one(x);
            case ("Bob2") -> Functions.two(x);
            case ("Bob3") -> Functions.three(x);
            default -> throw new IllegalStateException("Unexpected value: " + a.getLocalName());
        };
    }

    public static float getMin(float[] xx, float[] yy0, float[] yy, float[] yy1) {
        float y0, y, y1;
        y0 = getSumY(yy0);
        y = getSumY(yy);
        y1 = getSumY(yy1);

        boolean toLeft = (y0 < y && y < y1) || (y0 < y1);
        boolean toRight = (y0 > y && y > y1 || (y0 > y1));
        boolean toCenter = (y0 > y && y < y1);

        if (toLeft) return xx[0];
        if (toCenter) return xx[1];
        if (toRight) return xx[2];
        else throw new IllegalArgumentException("Расчет некорректен");
    }

    public static float getSumY(float[] yy) {
        float y0 = 0;
        for (float y : yy) {
            y0 += y;
        }
        return y0;
    }

}
