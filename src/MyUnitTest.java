import customers.*;
import org.junit.Assert;
import org.junit.Test;
import rolls.*;

import static org.junit.Assert.assertTrue;

/**
 * Test file for all tests. In a bigger project we would have test files for every class to help manage
 * the large number of tests. For 10 tests a single file should be fine.
 */
public class MyUnitTest {
    // Testing setup found here - https://www.jetbrains.com/help/idea/testing.html
    @Test
    public void EggRollCostsNine() {
        EggRoll roll = new EggRoll();
        assertTrue(roll.cost() == 9);
    }

    @Test
    public void JellyRollCostsEight() {
        JellyRoll roll = new JellyRoll();
        assertTrue(roll.cost() == 8);
    }

    @Test
    public void SausageRollCosts14Point5() {
        SausageRoll roll = new SausageRoll();
        assertTrue(roll.cost() == 14.5);
    }

    @Test
    public void SpringRollCostsTwelve() {
        SpringRoll roll = new SpringRoll();
        assertTrue(roll.cost() == 12);
    }

    @Test
    public void BusinessCustomer_Buys10Rolls() {
        BusinessCustomer customer = new BusinessCustomer();
        customer.purchaseRolls();
        assertTrue(customer.rollsPurchased.size() == 10);
    }

    @Test
    public void CasualCustomer_Buys1To3Rolls() {
        CasualCustomer customer = new CasualCustomer();
        customer.purchaseRolls();
        int result = customer.rollsPurchased.size();
        assertTrue(result <= 3 && result > 0);
    }

    @Test
    public void CateringCustomer_Buys15Rolls() {
        CateringCustomer customer = new CateringCustomer();
        customer.purchaseRolls();
        assertTrue(customer.rollsPurchased.size() == 15);
    }

    @Test
    public void CustomerFactory_CreatesLessThan19Customers() {
        var factory = new CustomerFactory();
        var result = factory.generateCustomers();
        assertTrue(result.size() < 19);
    }

    @Test
    public void RollStore_JellyRollCanBeOrdered(){
        var factory = new RollFactory();
        var store = new RollStore(factory);
        var result = store.orderRoll("JellyRoll");
        assertTrue(result.cost() > 0);
    }

    @Test
    public void RollStore_SpringRollCanBeOrdered(){
        var factory = new RollFactory();
        var store = new RollStore(factory);
        var result = store.orderRoll("SpringRoll");
        assertTrue(result.cost() > 0);
    }
}