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
    private Map<String, Integer> rollsSoldByType = new HashMap<>();
    private Map<String, Double> earningByCustomerType = new HashMap<>();
    public int numRollTypes;

    public void refillStock() {
        factory.refillStock(stock);
    }

    public void open() {
        earningByCustomerType.put("casual", 0.0);
        earningByCustomerType.put("business", 0.0);
        earningByCustomerType.put("catering", 0.0);
        rollsSoldByType.put("SpringRoll", 0);
        rollsSoldByType.put("EggRoll", 0);
        rollsSoldByType.put("JellyRoll", 0);
        rollsSoldByType.put("SausageRoll", 0);
        rollsSoldByType.put("PastryRoll", 0);
    }

    public void close() {
        // Observer pattern to report to logging class
    }

    public RollStore(RollFactory factory) {
        this.factory = factory;
        this.numRollTypes = factory.numRollTypes;
        refillStock();
        totalEarningByCustomerType.put("casual", 0.0);
        totalEarningByCustomerType.put("business", 0.0);
        totalEarningByCustomerType.put("catering", 0.0);
        totalRollsSoldByType.put("SpringRoll", 0);
        totalRollsSoldByType.put("EggRoll", 0);
        totalRollsSoldByType.put("JellyRoll", 0);
        totalRollsSoldByType.put("SausageRoll", 0);
        totalRollsSoldByType.put("PastryRoll", 0);
    }

    private boolean isAvailable(String type) {
        if (stock.get(type) > 0)
            return true;
        return false;
    }

    public Roll orderRoll(Customer customer, String rollType, int numExtraSauce, int numExtraFillings, int numExtraToppings) {
        Roll roll = null;
        if (isAvailable(rollType)) {
            roll = factory.createRoll(rollType);
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
            totalRollsSoldByType.put(rollType,
                    totalRollsSoldByType.get(rollType) + 1);
            rollsSoldByType.put(rollType,
                    rollsSoldByType.get(rollType) + 1);
            stock.put(rollType, stock.get(rollType) - 1);
        } else {
            // unavailable..
            System.out.println("Requested roll " + rollType + " is unavailable..");
        }
        return roll;
    }

    public Map<String, Integer> getStoreInventory() {
        return stock;
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
