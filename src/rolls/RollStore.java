package rolls;

import customers.Customer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RollStore implements PropertyChangeListener {
    //Instance variables and datastructures
    RollFactory factory;
    private Map<String, Double> totalEarningByCustomerType = new HashMap<>();
    private Map<String, Integer> totalRollsSoldByType = new HashMap<>();
    private Map<String, Integer> totalRollOutageByType = new HashMap<>();
    private String didClose = "";
    private final int amountOfRolls = 60;
    public boolean isTest = false; //Used to eliminate print statements during JUnit tests, so the results appear cleaner

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Integer> rollsSoldByType = new HashMap<>();
    private Map<String, Double> earningByCustomerType = new HashMap<>();
    private Map<String, Integer> dailyRollOutageByType = new HashMap<>();
    private ArrayList<String> orderForTheDay = new ArrayList<>();
    private Map<String, Integer> numInventoryOrders = new HashMap<>();
    public int numRollTypes;


    //Public method to refill stock based on if any of the rolls are unavailable
    public void refillStock() {
        if (!isAvailable("SpringRoll")) {
            numInventoryOrders.put("SpringRoll", numInventoryOrders.get("SpringRoll") + 1);
            stock.put("SpringRoll", amountOfRolls);
        }
        if (!isAvailable("EggRoll")) {
            numInventoryOrders.put("EggRoll", numInventoryOrders.get("EggRoll") + 1);
            stock.put("EggRoll", amountOfRolls);
        }
        if (!isAvailable("JellyRoll")) {
            numInventoryOrders.put("JellyRoll", numInventoryOrders.get("JellyRoll") + 1);
            stock.put("JellyRoll", amountOfRolls);
        }
        if (!isAvailable("SausageRoll")) {
            numInventoryOrders.put("SausageRoll", numInventoryOrders.get("SausageRoll") + 1);
            stock.put("SausageRoll", amountOfRolls);
        }
        if (!isAvailable("PastryRoll")) {
            numInventoryOrders.put("PastryRoll", numInventoryOrders.get("PastryRoll") + 1);
            stock.put("PastryRoll", amountOfRolls);
        }
    }

    //Method to run every day when the store opens
    public void open() {
        refillStock();
        if(isTest == false) {
            System.out.println("Inventory at the beginning of the day: ");
            printInventory();
        }
        earningByCustomerType.put("casual", 0.0);
        earningByCustomerType.put("business", 0.0);
        earningByCustomerType.put("catering", 0.0);
        rollsSoldByType.put("SpringRoll", 0);
        rollsSoldByType.put("EggRoll", 0);
        rollsSoldByType.put("JellyRoll", 0);
        rollsSoldByType.put("SausageRoll", 0);
        rollsSoldByType.put("PastryRoll", 0);
        dailyRollOutageByType.put("casual", 0);
        dailyRollOutageByType.put("business", 0);
        dailyRollOutageByType.put("catering",0);
    }

    //Method to run everyday when the store closes
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
        System.out.println("Number of roll orders impacted by outages today: " + dailyRollOutageByType.toString());
        System.out.println("Number of times inventory had to be filled for each roll up to this point: " + numInventoryOrders.toString()); //to be done
        System.out.println("The store " + didClose + " close today for no inventory.");
        System.out.println("-------------------------------------------------------------------");
    }

    //Method to print the inventory
    public void printInventory() {
        System.out.println(stock.toString());
    }

    //Method to print all the necessary information at the end of the simulation
    public void printTotalResults() {
        System.out.println("End of simulation statistics:");
        System.out.println("Total rolls sold: " + totalRollsSoldByType.toString());
        System.out.println("Total earnings: " + totalEarningByCustomerType.toString());
        System.out.println("Total earnings summed:" + totalEarning());
        System.out.println("Total number of roll outage impacts by type: " + totalRollOutageByType.toString());
        System.out.println("-------------------------------------------------------------------");
    }

    //Constructor, initializing values
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
        totalRollOutageByType.put("casual", 0);
        totalRollOutageByType.put("business", 0);
        totalRollOutageByType.put("catering", 0);
        numInventoryOrders.put("SpringRoll", 0);
        numInventoryOrders.put("EggRoll", 0);
        numInventoryOrders.put("JellyRoll", 0);
        numInventoryOrders.put("SausageRoll", 0);
        numInventoryOrders.put("PastryRoll", 0);
    }

    //Method to check if the rolls are in stock
    private boolean isAvailable(String type) {
        if (stock.get(type) > 0)
            return true;
        return false;
    }

    //Method to order a roll
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

    //Method to get the inventory
    public Map<String, Integer> getStoreInventory() {
        return stock;
    }

    //Method to determine if the store is out of stock, and if it did to alert the results
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

    //Method to calculate the total Earnings for the whole simulation
    public Double totalEarning() {
        Double total = 0.0;
        for (Map.Entry<String, Double> entry : totalEarningByCustomerType.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }


    //Method to handle keeping track of roll outage scenarios
    public void incrementRollOutage(String type, int numOfOutage) {

        switch (type) {
            case "casual":
                totalRollOutageByType.put(type, totalRollOutageByType.get(type) + numOfOutage);
                dailyRollOutageByType.put(type, dailyRollOutageByType.get(type) + numOfOutage);
                break;
            case "business":
                totalRollOutageByType.put(type, totalRollOutageByType.get(type) + numOfOutage);
                dailyRollOutageByType.put(type, dailyRollOutageByType.get(type) + numOfOutage);
                break;
            case "catering":
                totalRollOutageByType.put(type, totalRollOutageByType.get(type) + numOfOutage);
                dailyRollOutageByType.put(type, dailyRollOutageByType.get(type) + numOfOutage);
                break;
            default:
                break;
        }
    }


    //Observer pattern methods
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setOutput(evt.getPropertyName(), (String) evt.getNewValue());
    }

    //Adding to the arraylist each customers order for the day,
    // so that the simulation can print them nicely when required
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

    //Private method to start the stock
    private void initStock() {
        stock.put("SpringRoll", amountOfRolls);
        stock.put("EggRoll", amountOfRolls);
        stock.put("JellyRoll", amountOfRolls);
        stock.put("SausageRoll", amountOfRolls);
        stock.put("PastryRoll", amountOfRolls);
    }

}
