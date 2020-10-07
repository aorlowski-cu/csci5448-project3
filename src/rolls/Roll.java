package rolls;

public abstract class Roll {

    String description = "Roll";

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}
