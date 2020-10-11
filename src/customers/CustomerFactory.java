package customers;

import java.util.*;

public class CustomerFactory {

    static Random rand = new Random();
    public static List<Customer> generateCustomers() {
        List<Customer> customers = new LinkedList<>();

        // upper bound is exclusive
        int numOfCasualCustomers = rand.nextInt(12) + 1;
        int numOfBusinessCustomers = 0;//rand.nextInt(3) + 1;
        int numOfCateringCustomers = 0;//rand.nextInt(3) + 1;

        for (int i = 0; i < numOfCasualCustomers; i++) {
            customers.add(new CasualCustomer());
        }
        for (int i = 0; i < numOfBusinessCustomers; i++) {
            customers.add(new BusinessCustomer());
        }
        for (int i = 0; i < numOfCateringCustomers; i++) {
            customers.add(new CateringCustomer());
        }

        // https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
        Collections.shuffle(customers);
        return customers;
    }
}
