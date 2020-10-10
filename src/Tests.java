import org.junit.Assert;
import org.junit.Test;
import rolls.EggRoll;

/**
 * Test file for all tests. In a bigger project we would have test files for every class to help manage
 * the large number of tests. For 10 tests a single file should be fine.
 */
public class Tests{
    // Testing setup found here - https://www.jetbrains.com/help/idea/testing.html
    @Test
    public void EggRollCostsNine(){
        EggRoll roll = new EggRoll();
        Assert.assertTrue(roll.cost() == 9);
    }
}