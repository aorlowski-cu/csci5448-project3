package customers;

import rolls.Roll;
import rolls.RollStore;

import java.util.ArrayList;
import java.util.Random;

public abstract class Customer {

    Random rand = new Random();

    public ArrayList<rolls.Roll> rollsPurchased = new ArrayList<>();
    public Customer(){}
    public String type;
    public abstract void purchaseRolls();

    public abstract void purchaseRolls_v2(RollStore store);

    public rolls.Roll pickRoll(){
        return null;
    }

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
