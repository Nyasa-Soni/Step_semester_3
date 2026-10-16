package abstraction_interface.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Problem 4: Electricity Connection Billing
 *
 * Demonstrates Abstraction:
 * - Abstract base class ElectricityConnection defines the common units attribute
 *   and mandates the abstract calculateBill() method.
 * - Subclasses HomeConnection, ShopConnection, and FactoryConnection implement
 *   their specific tariff rules:
 *   - Home: 5 per unit for the first 100 units, 7 per unit thereafter.
 *   - Shop: 8 per unit plus 100 fixed charge.
 *   - Factory: 6 per unit with a minimum bill requirement of 1000.
 * - ElectricityBillingSystem processes all connections polymorphically without if-else type checks.
 */
public class L4_ElectricityConnectionBilling {

    /**
     * Abstract base class representing an electricity connection.
     */
    public static abstract class ElectricityConnection {
        protected final double units;

        public ElectricityConnection(double units) {
            this.units = units;
        }

        public double getUnits() {
            return units;
        }

        public abstract String getType();

        public abstract double calculateBill();
    }

    /**
     * Home connection: 5 per unit for first 100 units, 7 per unit for remaining units.
     */
    public static class HomeConnection extends ElectricityConnection {
        public static final double TIER1_RATE = 5.00;
        public static final double TIER2_RATE = 7.00;
        public static final double TIER1_THRESHOLD = 100.0;

        public HomeConnection(double units) {
            super(units);
        }

        @Override
        public String getType() {
            return "HOME";
        }

        @Override
        public double calculateBill() {
            if (units <= TIER1_THRESHOLD) {
                return units * TIER1_RATE;
            }
            double tier1Cost = TIER1_THRESHOLD * TIER1_RATE;
            double tier2Cost = (units - TIER1_THRESHOLD) * TIER2_RATE;
            return tier1Cost + tier2Cost;
        }
    }

    /**
     * Shop connection: 8 per unit plus a fixed charge of 100.
     */
    public static class ShopConnection extends ElectricityConnection {
        public static final double RATE_PER_UNIT = 8.00;
        public static final double FIXED_CHARGE = 100.00;

        public ShopConnection(double units) {
            super(units);
        }

        @Override
        public String getType() {
            return "SHOP";
        }

        @Override
        public double calculateBill() {
            return (units * RATE_PER_UNIT) + FIXED_CHARGE;
        }
    }

    /**
     * Factory connection: 6 per unit, subject to a minimum bill of 1000.
     */
    public static class FactoryConnection extends ElectricityConnection {
        public static final double RATE_PER_UNIT = 6.00;
        public static final double MINIMUM_BILL = 1000.00;

        public FactoryConnection(double units) {
            super(units);
        }

        @Override
        public String getType() {
            return "FACTORY";
        }

        @Override
        public double calculateBill() {
            return Math.max(MINIMUM_BILL, units * RATE_PER_UNIT);
        }
    }

    /**
     * Factory class registering connection creators to avoid if-else chains.
     */
    public static class ConnectionFactory {
        private static final Map<String, Function<Double, ElectricityConnection>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("HOME", HomeConnection::new);
            REGISTRY.put("SHOP", ShopConnection::new);
            REGISTRY.put("FACTORY", FactoryConnection::new);
        }

        public static ElectricityConnection createConnection(String type, double units) {
            Function<Double, ElectricityConnection> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown connection type: " + type);
            }
            return creator.apply(units);
        }
    }

    /**
     * Central service processing electricity bills polymorphically.
     */
    public static class ElectricityBillingSystem {
        public static void processBills(List<ElectricityConnection> connections) {
            double totalBilled = 0.0;
            for (ElectricityConnection connection : connections) {
                double bill = connection.calculateBill();
                System.out.printf(Locale.US, "%s: %.2f%n", connection.getType(), bill);
                totalBilled += bill;
            }
            System.out.printf(Locale.US, "Total: %.2f%n", totalBilled);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<ElectricityConnection> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double units = scanner.nextDouble();
            connections.add(ConnectionFactory.createConnection(type, units));
        }
        ElectricityBillingSystem.processBills(connections);
    }
}

/**
 * Alias class for direct execution without L4_ prefix.
 */
class ElectricityConnectionBilling extends L4_ElectricityConnectionBilling {
    public static void main(String[] args) {
        L4_ElectricityConnectionBilling.main(args);
    }
}