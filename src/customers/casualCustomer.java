package customers;

import rolls.Topping;

import java.util.Random;

public class casualCustomer extends Customer{

    private int numRollUpperBound = 3;
    private int numRollTypes = 5;
    private Random rand = new Random();


    public casualCustomer() {
        super();

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
