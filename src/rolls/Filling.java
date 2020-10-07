package rolls;

public class Filling extends CondimentDecorator{
    Roll roll;

    public Filling(Roll roll) {
        this.roll = roll;
    }

    public String getDescription() {
        return roll.getDescription() + ", extra filling";
    }

    public double cost() {
        return roll.cost() + 2.0;
    }
}
