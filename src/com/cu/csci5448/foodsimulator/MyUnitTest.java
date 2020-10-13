package com.cu.csci5448.foodsimulator;

import customers.*;
//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import rolls.*;


//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test file for all tests. In a bigger project we would have test files for every class to help manage
 * the large number of tests. For 10 tests a single file should be fine.
 */
public class MyUnitTest {

    public MyUnitTest(){

    }
    // Testing setup found here - https://www.jetbrains.com/help/idea/testing.html
    @Test
    public void EggRollCostsNine() {
        EggRoll roll = new EggRoll();
        System.out.println("Egg roll costing nine JUnit test...");
        assertTrue(roll.cost() == 9);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void JellyRollCostsEight() {
        JellyRoll roll = new JellyRoll();
        System.out.println("Jelly roll costing eight JUnit test...");
        assertTrue(roll.cost() == 8);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void SausageRollCosts14Point5() {
        SausageRoll roll = new SausageRoll();
        System.out.println("Sausage roll costing 14.5 JUnit test...");
        assertTrue(roll.cost() == 14.5);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void SpringRollCostsTwelve() {
        SpringRoll roll = new SpringRoll();
        System.out.println("Spring roll costing 12 JUnit test...");
        assertTrue(roll.cost() == 12);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void BusinessCustomer_Buys10Rolls() {
        var factory = new RollFactory();
        var store = new RollStore(factory);
        BusinessCustomer customer = new BusinessCustomer();
        store.isTest = true;
        store.open();
        customer.purchaseRolls_v2(store);
        System.out.println("Business Customer buys 10 rolls JUnit test...");
        assertTrue(customer.rollsPurchased.size() == 10);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void CasualCustomer_Buys1To3Rolls() {
        var factory = new RollFactory();
        var store = new RollStore(factory);
        CasualCustomer customer = new CasualCustomer();
        store.isTest = true;
        store.open();
        customer.purchaseRolls_v2(store);
        int result = customer.rollsPurchased.size();
        System.out.println("Casual customer buys 1 to 3 rolls JUnit test...");
        assertTrue(result <= 3 && result > 0);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void CateringCustomer_Buys15Rolls() {
        var factory = new RollFactory();
        var store = new RollStore(factory);
        CateringCustomer customer = new CateringCustomer();
        store.isTest = true;
        store.open();
        customer.purchaseRolls_v2(store);
        System.out.println("Catering customer buys 15 rolls JUnit test...");
        assertTrue(customer.rollsPurchased.size() == 15);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void CustomerFactory_CreatesLessThan19Customers() {
        var factory = new CustomerFactory();
        var result = factory.generateCustomers();
        System.out.println("Customer factory creates less than 19 customers JUnit test...");
        assertTrue(result.size() < 19);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void RollStore_JellyRollCanBeOrdered(){
        Customer customer = new CasualCustomer();
        var factory = new RollFactory();
        var store = new RollStore(factory);
        store.isTest = true;
        store.open();
        var result = store.orderRoll(customer, "JellyRoll", 0, 0, 0);
        System.out.println("Jelly roll can be ordered from the roll store JUnit test...");
        assertTrue(result.cost() > 0);
        System.out.println("Test completed.  Test passed.");
    }

    @Test
    public void RollStore_SpringRollCanBeOrdered(){
        Customer customer = new CasualCustomer();
        var factory = new RollFactory();
        var store = new RollStore(factory);
        store.isTest = true;
        store.open();
        var result = store.orderRoll(customer, "SpringRoll", 0, 0, 0);
        System.out.println("Spring roll can be ordered from the roll store JUnit test...");
        assertTrue(result.cost() > 0);
        System.out.println("Test completed.  Test passed.");
    }

    public void runTests(){
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Commencing JUnit test methods: ");
        EggRollCostsNine();
        JellyRollCostsEight();
        SausageRollCosts14Point5();
        SpringRollCostsTwelve();
        BusinessCustomer_Buys10Rolls();
        CasualCustomer_Buys1To3Rolls();
        CateringCustomer_Buys15Rolls();
        CustomerFactory_CreatesLessThan19Customers();
        RollStore_JellyRollCanBeOrdered();
        RollStore_SpringRollCanBeOrdered();
        System.out.println("JUnit test methods completed.");
        System.out.println("-------------------------------------------------------------------");
    }
}