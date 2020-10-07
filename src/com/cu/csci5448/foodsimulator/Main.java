package com.cu.csci5448.foodsimulator;

import rolls.*;
import customers.*;

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

        //Casual customer
        Customer cust1 = new casualCustomer();
        cust1.purchaseRolls();
        cust1.printPurchasedRolls();
        System.out.println(cust1.costOfOrder());

        //Business customer
        System.out.println();
        Customer cust2 = new businessCustomer();
        cust2.purchaseRolls();
        cust2.printPurchasedRolls();
        System.out.println(cust2.costOfOrder());

        //Catering customer
        System.out.println();
        Customer cust3 = new cateringCustomer();
        cust3.purchaseRolls();
        cust3.printPurchasedRolls();
        System.out.println(cust3.costOfOrder());


    }
}
