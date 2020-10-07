package customers;

import java.util.ArrayList;
import java.util.Random;

public class cateringCustomer extends Customer{

    private int numRollsToBuy = 15;
    private int numRollTypes = 5;
    private Random rand = new Random();
    public cateringCustomer() {
        super();

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
            System.out.println("Selected: "+list.remove(index));
        }

        for(int j =0; j < 3; j++) {
            //int type = rand.nextInt(3) + 1;
            for (int i = 0; i < 5; i++) {
                addRoll(pickRoll(list.get(j)));
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
