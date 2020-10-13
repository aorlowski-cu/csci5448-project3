package customers;

import rolls.Roll;
import rolls.RollStore;

import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BusinessCustomer extends Customer{

    private int numRollsToBuy = 10;
    private Random rand = new Random();
    private PropertyChangeSupport businessCustomerObservable;
    private String toBeObserved;

    //Adding an observable to each customer, this code and next three methods from:
    // https://www.baeldung.com/java-observer-pattern
    //This method is part of the Observer pattern, allows the customer to alert the store of purchases
    public void showPurchase(String value) {
        businessCustomerObservable.firePropertyChange("businessCustomer", this.toBeObserved, value);
        this.toBeObserved = value;
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {businessCustomerObservable.addPropertyChangeListener(pcl); }
    public void removePropertyChangeListener(PropertyChangeListener pcl) {businessCustomerObservable.removePropertyChangeListener(pcl); }

    public BusinessCustomer() {
        super();
        businessCustomerObservable = new PropertyChangeSupport(this);
        type = "business";
    }

    public void purchaseRolls_v2(RollStore store) {
        String out = "";

        Map<String, Integer> storeStock = new HashMap<>(store.getStoreInventory());
        List<String> rollsToBuy = new ArrayList<>();

        for (int i = 0; i < numRollsToBuy; i++) {
            String rollType = pickRoll_v2(i%5);
            if (storeStock.getOrDefault(rollType, 0) > 0) {
                storeStock.put(rollType, storeStock.get(rollType) - 1);
                rollsToBuy.add(rollType);
            }
            else {
                // business customer will not buy if any request is not met
                store.incrementRollOutage("business",numRollsToBuy);
                // https://www.geeksforgeeks.org/arraylist-clear-java-examples/
                rollsToBuy.clear();
                break;
            }
        }

        for (int i = 0; i < rollsToBuy.size(); i++) {
            String rollType = rollsToBuy.get(i);
            int numExtraSauce = getNumExtraSauce();
            int numExtraFillings = getNumExtraFillings();
            int numExtraToppings = getNumExtraToppings();
            Roll roll = store.orderRoll(this, rollType, numExtraSauce, numExtraFillings, numExtraToppings);
            addRoll(roll);
            out = out + "Roll number: " + (i+1) + " " + roll.getDescription() + "\n";
        }

        out = out + "Total cost: " + costOfOrder() + "\n";
        this.showPurchase(out);
    }
}
