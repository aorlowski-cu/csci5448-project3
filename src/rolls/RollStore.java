package rolls;

import customers.Customer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RollStore implements PropertyChangeListener {
    RollFactory factory;
    private Map<String, Double> totalEarningByCustomerType = new HashMap<>();
    private Map<String, Integer> totalRollsSoldByType = new HashMap<>();
    private int totalRollOutage;
    private String didClose = "";

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Integer> rollsSoldByType = new HashMap<>();
    private Map<String, Double> earningByCustomerType = new HashMap<>();
    private ArrayList<String> orderForTheDay = new ArrayList<>();
    public int numRollTypes;


    public void refillStock() {
        if (!isAvailable("SpringRoll")) {
            stock.put("SpringRoll", 30);
        }
        if (!isAvailable("EggRoll")) {
            stock.put("EggRoll", 30);
        }
        if (!isAvailable("JellyRoll")) {
            stock.put("JellyRoll", 30);
        }
        if (!isAvailable("SausageRoll")) {
            stock.put("SausageRoll", 30);
        }
        if (!isAvailable("PastryRoll")) {
            stock.put("PastryRoll", 30);
        }
    }

    public void open() {
        refillStock();
        System.out.println("Inventory at the beginning of the day: ");
        printInventory();
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
        System.out.println("Inventory at the end of the day: ");
        printInventory();
        for (String s : orderForTheDay) {
            System.out.println(s);
        }
        orderForTheDay.clear();
        System.out.println("Total payment for order by customer type up to the end of today: " + totalEarningByCustomerType.toString());
        System.out.println("Total payment for order by customer type for today only: " + earningByCustomerType.toString());
        System.out.println("Total rolls sold by type up to the end of today: " + totalRollsSoldByType.toString());
        System.out.println("Total rolls sold by type up for today only: " + rollsSoldByType.toString());
        //System.out.println("Number of roll orders impacted by outages: " + ) //to be done
        //System.out.println("Number of times inventory had to be filled for each roll up to this point: " + ); //to be done
        System.out.println("The store " + didClose + " close today for no inventory.");
        System.out.println("-------------------------------------------------------------------");
        // Observer pattern to report to logging class
    }

    public void printInventory() {
        System.out.println(stock.toString());
    }

    public void printTotalResults() {
        System.out.println("End of simulation statistics:");
        System.out.println("Total rolls sold: " + totalRollsSoldByType.toString());
        System.out.println("Total earnings: " + totalEarningByCustomerType.toString());
        double sum = 0.0;
        for (double i : totalEarningByCustomerType.values()) {
            sum += i;
        }
        System.out.println("Total earnings summed " + sum);
        //System.out.println("Total number of roll outage impacts: " + ); //To be done
        System.out.println("-------------------------------------------------------------------");
    }

    public RollStore(RollFactory factory) {
        this.factory = factory;
        this.numRollTypes = factory.numRollTypes;
        initStock();
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
        didClose = "did";
        boolean outOfStock = true;
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() != 0) {
                didClose = "didn't";
                outOfStock = false;
            }
        }
        return outOfStock;
    }

    public Double totalEarning() {
        Double total = 0.0;
        for (Map.Entry<String, Double> entry : earningByCustomerType.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    public void incrementRollOutage(int numOfOutage) {
        totalRollOutage += numOfOutage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setOutput(evt.getPropertyName(), (String) evt.getNewValue());
    }

    private void setOutput(String name, String value) {

        if(value.equals("Total cost: 0.0" + "\n")){
            return;
        }
        switch (name) {
            case "casualCustomer":
                orderForTheDay.add("Casual Customer's order: " + "\n" + value);
                break;
            case "businessCustomer":
                orderForTheDay.add("Business Customer's order: " + "\n" + value);
                break;
            case "cateringCustomer":
                orderForTheDay.add("Catering Customer's order: " + "\n" + value);
                break;
            default:
                break;
        }
    }

    private void initStock() {
        stock.put("SpringRoll", 30);
        stock.put("EggRoll", 30);
        stock.put("JellyRoll", 30);
        stock.put("SausageRoll", 30);
        stock.put("PastryRoll", 30);
    }

}
