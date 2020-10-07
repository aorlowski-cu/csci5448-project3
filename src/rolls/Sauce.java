package rolls;

public class Sauce extends CondimentDecorator{
    Roll roll;

    public Sauce(Roll roll) {
        this.roll = roll;
    }

    public String getDescription() {
        return roll.getDescription() + ", extra sauce";
    }

    public double cost() {
        return roll.cost() + 1.5;
    }
}
