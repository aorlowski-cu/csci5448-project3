package com.cu.csci5448.foodsimulator;

import rolls.*;
import customers.*;

import java.util.List;

public class Main {

    public static String outputRoll(Roll roll) {
        return "My Roll: " + roll.getDescription() + ", cost: " + roll.cost();
    }

    public static void main(String[] args) {
        final int numOfDays = 30;
        RollFactory factory = new RollFactory();
        RollStore store = new RollStore(factory);

        CustomerFactory customerFactory = new CustomerFactory();

        for (int i = 1; i < numOfDays+1; i++) {
            List<Customer> customers = customerFactory.generateCustomers();
            System.out.println("Day number: " + i);
            System.out.println("Amount of customers: " + customers.size());
            store.open();
            for (Customer customer: customers) {
                customer.addPropertyChangeListener(store);
                if (store.isOutOfStock()) {
                    System.out.println("Store is out of stock today. Please come back tomorrow for fresh rolls!");
                    break;
                } else {
                    customer.purchaseRolls_v2(store);
                }
            }
            store.close();
            if (store.isOutOfStock()) {
                store.refillStock();
            }
        }
        store.printTotalResults();
    }
}
