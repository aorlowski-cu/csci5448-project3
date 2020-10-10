package rolls;

import customers.Customer;

import java.util.HashMap;
import java.util.Map;

public class RollStore {
    RollFactory factory;
    private Map<String, Double> totalEarningByCustomerType = new HashMap<>();
    private Map<String, Integer> totalRollsSoldByType = new HashMap<>();
    private int totalRollOutage;

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Double> earningByCustomerType = new HashMap<>();
    public int numRollTypes;

    public void refillStock() {
        factory.refillStock(stock);
    }

    public void open() {

    }

    public void close() {

    }

    public RollStore(RollFactory factory) {
        this.factory = factory;
        this.numRollTypes = factory.numRollTypes;
        refillStock();
        earningByCustomerType.put("casual", 0.0);
        earningByCustomerType.put("business", 0.0);
        earningByCustomerType.put("catering", 0.0);
    }

    private boolean isAvailable(String type) {
        if (stock.get(type) > 0)
            return true;
        return false;
    }

    public Roll orderRoll(Customer customer, String type, int numExtraSauce, int numExtraFillings, int numExtraToppings) {
        Roll roll = null;
        if (isAvailable(type)) {
            roll = factory.createRoll(type);
            for (int i = 0; i < numExtraSauce; i++) {
                roll = new Sauce(roll);
            }
            for (int i = 0; i < numExtraFillings; i++) {
                roll = new Filling(roll);
            }
            for (int i = 0; i < numExtraToppings; i++) {
                roll = new Topping(roll);
            }
            earningByCustomerType.put(customer.type,
                    earningByCustomerType.get(customer.type) + roll.cost());
            totalEarningByCustomerType.put(customer.type,
                    totalEarningByCustomerType.get(customer.type) + roll.cost());
            stock.put(type, stock.get(type) - 1);
        } else {
            // unavailable..
            System.out.println("Requested roll " + type + " is unavailable..");
        }
        return roll;
    }

    public boolean isOutOfStock() {
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        boolean outOfStock = true;
        for (Map.Entry<String, Integer> entry: stock.entrySet()) {
            if (entry.getValue() != 0) {
                outOfStock = false;
            }
        }
        return outOfStock;
    }

    public Double totalEarning() {
        Double total = 0.0;
        for (Map.Entry<String, Double> entry: earningByCustomerType.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    public void incrementRollOutage(int numOfOutage) {
        totalRollOutage += numOfOutage;
    }
}
