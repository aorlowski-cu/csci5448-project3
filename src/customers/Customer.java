package customers;

import java.util.ArrayList;
import java.util.Random;

public abstract class Customer {

    Random rand = new Random();

    private ArrayList<rolls.Roll> rollsPurchased = new ArrayList<>();
    public Customer(){
    }
    public abstract void purchaseRolls();

    public rolls.Roll pickRoll(){
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
}
