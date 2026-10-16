package oop.assigment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Assignment Problem 1: The Canteen Billing Counter
 *
 * Demonstrates OOP polymorphism:
 * - Base abstract class Customer defines common customer attributes and abstract methods.
 * - Subclasses StudentCustomer, StaffCustomer, and GuestCustomer implement specific discount / surcharge rules.
 * - CustomerFactory uses a registry map to instantiate objects without explicit if-else type checking.
 * - The CanteenBillingSystem processes bills polymorphically.
 */
public class L1_CanteenBillingCounter {

    /**
     * Abstract base class representing a customer bill.
     */
    public static abstract class Customer {
        protected final double amount;

        public Customer(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        public abstract String getType();

        public abstract double calculateFinalAmount();
    }

    /**
     * Student customer: 10% discount.
     */
    public static class StudentCustomer extends Customer {
        public StudentCustomer(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "STUDENT";
        }

        @Override
        public double calculateFinalAmount() {
            return amount * 0.90;
        }
    }

    /**
     * Staff customer: 5% discount.
     */
    public static class StaffCustomer extends Customer {
        public StaffCustomer(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "STAFF";
        }

        @Override
        public double calculateFinalAmount() {
            return amount * 0.95;
        }
    }

    /**
     * Guest customer: full amount + Rs 10 service charge.
     */
    public static class GuestCustomer extends Customer {
        public GuestCustomer(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "GUEST";
        }

        @Override
        public double calculateFinalAmount() {
            return amount + 10.00;
        }
    }

    /**
     * Factory class registering customer creators to avoid if-else chains.
     */
    public static class CustomerFactory {
        private static final Map<String, Function<Double, Customer>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("STUDENT", StudentCustomer::new);
            REGISTRY.put("STAFF", StaffCustomer::new);
            REGISTRY.put("GUEST", GuestCustomer::new);
        }

        public static Customer createCustomer(String type, double amount) {
            Function<Double, Customer> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown customer type: " + type);
            }
            return creator.apply(amount);
        }
    }

    /**
     * Billing service handling billing calculations polymorphically.
     */
    public static class CanteenBillingSystem {
        public static void processBills(List<Customer> bills) {
            double grandTotal = 0.0;
            for (Customer customer : bills) {
                double finalAmount = customer.calculateFinalAmount();
                System.out.printf(Locale.US, "%s: %.2f%n", customer.getType(), finalAmount);
                grandTotal += finalAmount;
            }
            System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Customer> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            bills.add(CustomerFactory.createCustomer(type, amount));
        }
        CanteenBillingSystem.processBills(bills);
    }
}

/**
 * Alias class for direct execution without L1_ prefix.
 */
class CanteenBillingCounter extends L1_CanteenBillingCounter {
    public static void main(String[] args) {
        L1_CanteenBillingCounter.main(args);
    }
}