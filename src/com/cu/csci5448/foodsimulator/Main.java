package com.cu.csci5448.foodsimulator;

import rolls.*;

public class Main {

    public static String outputRoll(Roll roll) {
        return "My Roll: " + roll.getDescription() + ", cost: " + roll.cost();
    }

    public static void main(String[] args) {
        Roll roll1 = new SpringRoll();
        roll1 = new Filling(roll1);
        System.out.println(outputRoll(roll1));

        Roll roll2 = new EggRoll();
        roll2 = new Topping(roll2);
        roll2 = new Sauce(roll2);
        System.out.println(outputRoll(roll2));

        Roll roll3 = new SausageRoll();
        roll3 = new Topping(roll3);
        roll3 = new Topping(roll3);
        System.out.println(outputRoll(roll3));

    }
}
