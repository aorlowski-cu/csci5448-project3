package rolls;

import java.util.Map;

public class RollFactory {
    public int numRollTypes = 5;

    public Roll createRoll(String type) {
        Roll roll = null;

        if (type.equals("EggRoll")) {
            roll = new EggRoll();
        } else if (type.equals("JellyRoll")) {
            roll = new JellyRoll();
        } else if (type.equals("PastryRoll")) {
            roll = new PastryRoll();
        } else if (type.equals("SausageRoll")) {
            roll = new SausageRoll();
        } else if (type.equals("SpringRoll")) {
            roll = new SpringRoll();
        }
        return roll;
    }
}
