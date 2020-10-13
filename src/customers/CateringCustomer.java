package customers;

import rolls.Roll;
import rolls.RollStore;

import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CateringCustomer extends Customer{

    private int numRollsToBuy = 15;
    private int numRollTypes = 5;
    private Random rand = new Random();

    private PropertyChangeSupport cateringCustomerObservable;
    private String toBeObserved;

    //Adding an observable to each customer, this code and next three methods from:
    // https://www.baeldung.com/java-observer-pattern

    //This method is part of the Observer pattern, allows the customer to alert the store of purchases
    public void showPurchase(String value) {
        cateringCustomerObservable.firePropertyChange("cateringCustomer", this.toBeObserved, value);
        this.toBeObserved = value;
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {cateringCustomerObservable.addPropertyChangeListener(pcl); }
    public void removePropertyChangeListener(PropertyChangeListener pcl) { cateringCustomerObservable.removePropertyChangeListener(pcl); }

    public CateringCustomer() {
        super();
        cateringCustomerObservable = new PropertyChangeSupport(this);
        type = "catering";
    }

    public void purchaseRolls_v2(RollStore store) {
        String out = "";

        ArrayList<Integer> rollIdxList = new ArrayList<Integer>(numRollTypes);
        //Generating random numbers with no duplicates: https://stackoverflow.com/questions/4040001/creating-random-numbers-with-no-duplicates
        for(int i = 0; i < numRollTypes; i++) {
            rollIdxList.add(i);
        }
        while(rollIdxList.size() > 3) {
            int index = rand.nextInt(rollIdxList.size());
            rollIdxList.remove(index);
        }

        Map<String, Integer> storeStock = new HashMap<>(store.getStoreInventory());
        List<String> rollsToBuy = new ArrayList<>();

        for (int i = 0; i < rollIdxList.size(); i++) {
            for (int j = 0; j < 5; j++) {
                String rollType = pickRoll_v2(rollIdxList.get(i));
                if (storeStock.getOrDefault(rollType, 0) > 0) {
                    storeStock.put(rollType, storeStock.get(rollType) - 1);
                    rollsToBuy.add(rollType);
                } else {
                    // select alternative type of roll
                    String backup = null;
                    for (String type: storeStock.keySet()) {
                        if (storeStock.get(type) > 0) {
                            backup = type;
                            break;
                        }
                    }
                    if (backup == null) {
                        store.incrementRollOutage("catering",1);
                    }
                    else {
                        store.incrementRollOutage("catering",1);
                        storeStock.put(backup, storeStock.get(backup) - 1);
                        rollsToBuy.add(backup);
                    }
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
