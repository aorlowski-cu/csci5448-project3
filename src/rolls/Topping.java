package rolls;

public class Topping extends CondimentDecorator{
    Roll roll;
    public Topping(Roll roll) {
        this.roll = roll;
    }

    public String getDescription() {
        return roll.getDescription() + ", extra topping";
    }

    public double cost() {
        return roll.cost() + 1.75;
    }
}
