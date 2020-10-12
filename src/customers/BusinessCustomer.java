package customers;

import rolls.Roll;
import rolls.RollStore;
import java.util.Random;
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
        for (int i = 0; i < numRollsToBuy; i++) {
            String rollType = pickRoll_v2(i%5);
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
        for (int i = 0; i < numRollsToBuy; i++) {
            rolls.Roll temp = pickRoll(i);
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

    public rolls.Roll pickRoll(int i){

        switch (i%5){
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
