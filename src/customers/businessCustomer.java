package customers;

import java.util.Random;

public class businessCustomer extends Customer{

    private int numRollsToBuy = 10;
    private int numRollTypes = 5;
    private Random rand = new Random();
    public businessCustomer() {
        super();
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
