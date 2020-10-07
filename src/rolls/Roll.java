package rolls;

import java.util.Random;

public abstract class Roll {

    private static int numExtraSauce;
    private static int numExtraFillings;
    private static int numExtraToppings;
    private static Random rand;
    public Roll(){
        rand = new Random();
        numExtraSauce = rand.nextInt(4);
        numExtraFillings = rand.nextInt(2);
        numExtraToppings = rand.nextInt(3);
    }

    public static int getNumExtraSauce(){
        return numExtraSauce;
    }
    public static int getNumExtraFillings(){
        return numExtraFillings;
    }
    public static int getNumExtraToppings(){
        return numExtraToppings;
    }
    String description = "Roll";

    public String getDescription() {
        return description;
    }
    public abstract double cost();

    public static String outputRoll(Roll roll) {
        return "My Roll: " + roll.getDescription() + ", cost: " + roll.cost();
    }
}
