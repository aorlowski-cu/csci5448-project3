package rolls;

import java.util.HashMap;
import java.util.Map;

public class RollStore {
    RollFactory factory;
    private Map<String, Integer> stock = new HashMap<>();

    public void refillStock() {
        factory.refillStock(stock);
    }

    public RollStore(RollFactory factory) {
        this.factory = factory;
        refillStock();
    }

    private boolean isAvailable(String type) {
        if (stock.get(type) > 0)
            return true;
        return false;
    }

    public Roll orderRoll(String type) {
        Roll roll = null;
        if (isAvailable(type)) {
            roll = factory.createRoll(type);
            stock.put(type, stock.get(type) - 1);
        } else {
            System.out.println("Requested roll " + type + " is unavailable..");
        }
        return roll;
    }

    public boolean isOutOfStock() {
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        boolean outOfStock = true;
        for (Map.Entry<String, Integer> entry: stock.entrySet()) {
            if (entry.getValue() != 0) {
                outOfStock = false;
            }
        }
        return outOfStock;
    }
}
