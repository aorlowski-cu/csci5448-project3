package com.cu.csci5448.foodsimulator;

import rolls.*;
import customers.*;

import java.io.*;
import java.util.List;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws IOException {

        // https://www.tutorialspoint.com/redirecting-system-out-println-output-to-a-file-in-java#:~:text=Instantiate%20a%20PrintStream%20class%20by,created%20in%20the%20first%20step.
        // Direct output stream to file 'out.txt'
        File file = new File("60RollsMaxOutput.txt");
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);
        MyUnitTest mUT = new MyUnitTest();
        mUT.runTests();

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
