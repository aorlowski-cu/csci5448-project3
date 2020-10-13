package customers;

import rolls.Roll;
import rolls.RollStore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public abstract class Customer {

    private PropertyChangeSupport customerObservable;
    private String toBeObserved;

    //Adding an observable to each customer, this code and next three methods from:
    // https://www.baeldung.com/java-observer-pattern

    //Part of the observer pattern
    public void addPropertyChangeListener(PropertyChangeListener pcl) {customerObservable.addPropertyChangeListener(pcl); }
    public void removePropertyChangeListener(PropertyChangeListener pcl) { customerObservable.removePropertyChangeListener(pcl); }

    //This method is part of the Observer pattern, allows the customer to alert the store of purchases
    public void showPurchase(String value) {
        customerObservable.firePropertyChange("customer", this.toBeObserved, value);
        this.toBeObserved = value;
    }

    Random rand = new Random();

    public ArrayList<rolls.Roll> rollsPurchased = new ArrayList<>();
    public Customer(){
        //customerObservable = new PropertyChangeSupport(this);
    }
    public String type;

    public abstract void purchaseRolls_v2(RollStore store);

    public String pickRoll_v2(int rollIdx) {
        switch (rollIdx) {
            case 0: //Egg roll
                return "EggRoll";
            case 1: //Jelly roll
                return "JellyRoll";
            case 2: //Spring roll
                return "SpringRoll";
            case 3: //Pastry roll
                return "PastryRoll";
            case 4: //Sausage roll
                return "SausageRoll";
            default:
                break;
        }
        return null;
    }

    public void addRoll(rolls.Roll roll){
        rollsPurchased.add(roll);
    }

    public void printPurchasedRolls(){
        for(int i =0; i < rollsPurchased.size(); i++){
            System.out.println(rolls.Roll.outputRoll(rollsPurchased.get(i)));
        }
    }

    public double costOfOrder(){
        double sum = 0.0;
        for(int i =0; i < rollsPurchased.size(); i++){
            sum += rollsPurchased.get(i).cost();
        }
        return sum;
    }

    public int getNumExtraSauce(){ return rand.nextInt(4); }
    public int getNumExtraFillings(){ return rand.nextInt(2); }
    public int getNumExtraToppings(){ return rand.nextInt(3); }

}
