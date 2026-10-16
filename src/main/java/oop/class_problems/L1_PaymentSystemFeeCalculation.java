package oop.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Class Problem 1: Payment System Fee Calculation
 *
 * Demonstrates OOP Polymorphism:
 * - Common abstract base class Payment defines common attributes (amount) and abstract calculateAdjustedAmount().
 * - Specialized subclasses CardPayment, WalletPayment, and BankTransferPayment implement specific fee structures.
 * - PaymentFactory registers payment constructors to instantiate objects uniformly without if-else type checks.
 * - PaymentProcessor uniformly processes payments via dynamic method dispatch.
 */
public class L1_PaymentSystemFeeCalculation {

    /**
     * Abstract base class representing a generic payment transaction.
     */
    public static abstract class Payment {
        protected final double amount;

        public Payment(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        public abstract String getType();

        public abstract double calculateAdjustedAmount();
    }

    /**
     * Card transaction: 2% processing fee.
     */
    public static class CardPayment extends Payment {
        public CardPayment(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "CARD";
        }

        @Override
        public double calculateAdjustedAmount() {
            return amount * 1.02;
        }
    }

    /**
     * Wallet transaction: 1% processing fee.
     */
    public static class WalletPayment extends Payment {
        public WalletPayment(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "WALLET";
        }

        @Override
        public double calculateAdjustedAmount() {
            return amount * 1.01;
        }
    }

    /**
     * Bank transfer transaction: no processing fee.
     */
    public static class BankTransferPayment extends Payment {
        public BankTransferPayment(double amount) {
            super(amount);
        }

        @Override
        public String getType() {
            return "BANKTRANSFER";
        }

        @Override
        public double calculateAdjustedAmount() {
            return amount;
        }
    }

    /**
     * Factory class registering payment constructors to avoid explicit type checking.
     */
    public static class PaymentFactory {
        private static final Map<String, Function<Double, Payment>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("CARD", CardPayment::new);
            REGISTRY.put("WALLET", WalletPayment::new);
            REGISTRY.put("BANKTRANSFER", BankTransferPayment::new);
        }

        public static Payment createPayment(String type, double amount) {
            Function<Double, Payment> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown payment type: " + type);
            }
            return creator.apply(amount);
        }
    }

    /**
     * Payment processor service processing collections of Payment polymorphically.
     */
    public static class PaymentProcessor {
        public static void processPayments(List<Payment> payments) {
            double grandTotal = 0.0;
            for (Payment payment : payments) {
                double adjusted = payment.calculateAdjustedAmount();
                System.out.printf(Locale.US, "%s: %.2f%n", payment.getType(), adjusted);
                grandTotal += adjusted;
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
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            payments.add(PaymentFactory.createPayment(type, amount));
        }
        PaymentProcessor.processPayments(payments);
    }
}

/**
 * Alias class for direct execution without L1_ prefix.
 */
class PaymentSystemFeeCalculation extends L1_PaymentSystemFeeCalculation {
    public static void main(String[] args) {
        L1_PaymentSystemFeeCalculation.main(args);
    }
}