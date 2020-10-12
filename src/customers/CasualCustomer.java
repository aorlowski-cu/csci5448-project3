package customers;

import rolls.Roll;
import rolls.RollStore;
import rolls.Topping;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

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
        for (int i = 0; i < numRolls; i++) {
            int rollIdx = rand.nextInt(numRollTypes);
            String rollType = pickRoll_v2(rollIdx);
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
        int numRolls = rand.nextInt(numRollUpperBound) + 1;

        for (int i = 0; i < numRolls; i++) {
            rolls.Roll temp = pickRoll();
            for(int j =0; j < rolls.Roll.getNumExtraSauce(); j++){
                temp = new rolls.Sauce(temp);
            }
            for(int j =0; j < rolls.Roll.getNumExtraFillings(); j++){
                temp = new rolls.Filling(temp);
            }
            for(int j =0; j < rolls.Roll.getNumExtraToppings(); j++){
                temp = new rolls.Topping(temp);
            }
            addRoll(temp);
        }
    }
    @Override
    public rolls.Roll pickRoll(){
        int rollType = rand.nextInt(numRollTypes);

        switch (rollType){
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
