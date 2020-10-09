package customers;

import java.util.*;

public class CustomerFactory {
    Random rand = new Random();
    public List<Customer> generateCustomers() {
        List<Customer> customers = new LinkedList<>();

        // upper bound is exclusive
        int numOfCasualCustomers = rand.nextInt(12) + 1;
        int numOfBusinessCustomers = rand.nextInt(3) + 1;
        int numOfCateringCustomers = rand.nextInt(3) + 1;

        for (int i = 0; i < numOfCasualCustomers; i++) {
            customers.add(new casualCustomer());
        }
        for (int i = 0; i < numOfBusinessCustomers; i++) {
            customers.add(new businessCustomer());
        }
        for (int i = 0; i < numOfCateringCustomers; i++) {
            customers.add(new cateringCustomer());
        }

        // https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
        Collections.shuffle(customers);
        return customers;
    }
}
