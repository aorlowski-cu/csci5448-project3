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
                        store.incrementRollOutage(1);
                    }
                    else {
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

    @Override
    public void purchaseRolls(){
        ArrayList<Integer> list = new ArrayList<Integer>(numRollTypes);
        //Generating random numbers with no duplicates: https://stackoverflow.com/questions/4040001/creating-random-numbers-with-no-duplicates
        for(int i = 0; i < numRollTypes; i++) {
            list.add(i);
        }
        while(list.size() > 3) {
            int index = rand.nextInt(list.size());
            list.remove(index);
        }
        for(int j =0; j < 3; j++) {
            //int type = rand.nextInt(3) + 1;
            for (int i = 0; i < 5; i++) {
                rolls.Roll temp = pickRoll(list.get(j));
                for(int k =0; k < rolls.Roll.getNumExtraSauce(); k++){
                    temp = new rolls.Sauce(temp);
                }
                for(int k =0; k < rolls.Roll.getNumExtraFillings(); k++){
                    temp = new rolls.Filling(temp);
                }
                for(int k =0; k < rolls.Roll.getNumExtraToppings(); k++){
                    temp = new rolls.Topping(temp);
                }
                addRoll(temp);
            }
        }
    }

    public rolls.Roll pickRoll(int i) {

        switch (i) {
            case 0: //Egg roll
                return new rolls.EggRoll();
            case 1: //Jelly roll
                return new rolls.JellyRoll();
            case 2: //Spring roll
                return new rolls.SpringRoll();
            case 3: //Pastry roll
                return new rolls.PastryRoll();
            case 4: //Sausage roll
                return new rolls.SausageRoll();
            default:
                break;
        }
        return null;
    }

}
