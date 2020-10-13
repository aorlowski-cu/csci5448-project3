package customers;

import rolls.Roll;
import rolls.RollStore;
import rolls.Topping;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class CasualCustomer extends Customer{

    private int numRollUpperBound = 3;
    private int numRollTypes = 5;
    private Random rand = new Random();


    private PropertyChangeSupport casualCustomerObservable;
    private String toBeObserved;

    //Adding an observable to each customer, this code and next three methods from:
    // https://www.baeldung.com/java-observer-pattern

    //This method is part of the Observer pattern, allows the customer to alert the store of purchases
    public void showPurchase(String value) {
        casualCustomerObservable.firePropertyChange("casualCustomer", this.toBeObserved, value);
        this.toBeObserved = value;
    }

    public CasualCustomer() {
        super();
        casualCustomerObservable = new PropertyChangeSupport(this);
        type = "casual";
    }

    //Part of the observer pattern
    public void addPropertyChangeListener(PropertyChangeListener pcl) {casualCustomerObservable.addPropertyChangeListener(pcl); }
    public void removePropertyChangeListener(PropertyChangeListener pcl) { casualCustomerObservable.removePropertyChangeListener(pcl); }

    public void purchaseRolls_v2(RollStore store) {
        int numRolls = rand.nextInt(numRollUpperBound) + 1;
        String out = "";

        Map<String, Integer> storeStock = new HashMap<>(store.getStoreInventory());
        List<String> rollsToBuy = new ArrayList<>();

        for (int i = 0; i < numRolls; i++) { //Run for each roll
            int rollIdx = rand.nextInt(numRollTypes);
            String rollType = pickRoll_v2(rollIdx);
            if (storeStock.getOrDefault(rollType, 0) > 0) { //If the roll is in stock
                storeStock.put(rollType, storeStock.get(rollType) - 1);
                rollsToBuy.add(rollType);
            }
            else { //if the roll isn't in stock
                store.incrementRollOutage("casual", numRolls - i);
                // randomly pick one of the other rolls
                String backup = null;
                for (String type: storeStock.keySet()) {
                    if (storeStock.get(type) > 0) {
                        backup = type;
                        break;
                    }
                }
                if (backup == null) {
                    // out of stock
                    break;
                }
                else {
                    storeStock.put(backup, storeStock.get(backup) - 1);
                    rollsToBuy.add(backup);
                }
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
