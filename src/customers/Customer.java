package customers;

import java.util.ArrayList;

public abstract class Customer {

    private ArrayList<rolls.Roll> rollsPurchased = new ArrayList<>();
    public Customer(){
    }
    public void purchaseRolls(){}

    public rolls.Roll pickRoll(){
        return null;
    }

    public void addRoll(rolls.Roll roll){
        rollsPurchased.add(roll);
    }

    public void printPurchasedRolls(){
        for(int i =0; i < rollsPurchased.size(); i++){
            System.out.println(rollsPurchased.get(i).getDescription());
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
