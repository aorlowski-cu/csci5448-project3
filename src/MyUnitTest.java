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
        RollFactory factory = new RollFactory();
        RollStore store = new RollStore(factory);
        BusinessCustomer customer = new BusinessCustomer();
        customer.purchaseRolls_v2(store);
        assertTrue(customer.rollsPurchased.size() == 10);
    }

    @Test
    public void CasualCustomer_Buys1To3Rolls() {
        RollFactory factory = new RollFactory();
        RollStore store = new RollStore(factory);
        CasualCustomer customer = new CasualCustomer();
        customer.purchaseRolls_v2(store);
        int result = customer.rollsPurchased.size();
        assertTrue(result <= 3 && result > 0);
    }

    @Test
    public void CateringCustomer_Buys15Rolls() {
        RollFactory factory = new RollFactory();
        RollStore store = new RollStore(factory);
        CateringCustomer customer = new CateringCustomer();
        customer.purchaseRolls_v2(store);
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
        Customer customer = new CasualCustomer();
        var factory = new RollFactory();
        var store = new RollStore(factory);
        store.open();
        var result = store.orderRoll(customer, "JellyRoll", 0, 0, 0);
        assertTrue(result.cost() > 0);
    }

    @Test
    public void RollStore_SpringRollCanBeOrdered(){
        Customer customer = new CasualCustomer();
        var factory = new RollFactory();
        var store = new RollStore(factory);
        store.open();
        var result = store.orderRoll(customer, "SpringRoll", 0, 0, 0);
        assertTrue(result.cost() > 0);
    }
}