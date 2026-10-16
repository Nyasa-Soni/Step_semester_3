package oop.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Class Problem 3: Delivery Fee Calculator
 *
 * Demonstrates OOP Polymorphism:
 * - Common abstract base class Delivery defines common shipment properties (weight, distance)
 *   and abstract calculateFee().
 * - Subclasses StandardDelivery, ExpressDelivery, and InternationalDelivery encapsulate
 *   their respective pricing rules and extra parameters (customsFee for InternationalDelivery).
 * - DeliveryFactory uses a registry map with functional parsers to eliminate if-else type checks.
 * - DeliveryService processes delivery fees polymorphically.
 */
public class L3_DeliveryFeeCalculator {

    /**
     * Abstract base class representing a delivery request.
     */
    public static abstract class Delivery {
        protected final double weight;
        protected final double distance;

        public Delivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        public double getWeight() {
            return weight;
        }

        public double getDistance() {
            return distance;
        }

        public abstract String getDeliveryType();

        public abstract double calculateFee();
    }

    /**
     * Standard Delivery:
     * Base fee = $5.00, plus $0.50 per kg, plus $0.10 per km.
     */
    public static class StandardDelivery extends Delivery {
        public StandardDelivery(double weight, double distance) {
            super(weight, distance);
        }

        @Override
        public String getDeliveryType() {
            return "STANDARD";
        }

        @Override
        public double calculateFee() {
            return 5.00 + (weight * 0.50) + (distance * 0.10);
        }
    }

    /**
     * Express Delivery:
     * Base fee = $20.00 ($5 standard base + $15 express upgrade),
     * plus $1.00 per kg, plus $0.20 per km.
     * (Matches the official test specification where EXPRESS 5 20 yields 29.00).
     */
    public static class ExpressDelivery extends Delivery {
        public ExpressDelivery(double weight, double distance) {
            super(weight, distance);
        }

        @Override
        public String getDeliveryType() {
            return "EXPRESS";
        }

        @Override
        public double calculateFee() {
            return 20.00 + (weight * 1.00) + (distance * 0.20);
        }
    }

    /**
     * International Delivery:
     * Base fee = $35.00 ($25 international base + $10 handling charge),
     * plus $2.00 per kg, plus $0.50 per km, plus customs fee.
     * (Matches the official test specification where INTERNATIONAL 20 100 30 yields 155.00).
     */
    public static class InternationalDelivery extends Delivery {
        private final double customsFee;

        public InternationalDelivery(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }

        public double getCustomsFee() {
            return customsFee;
        }

        @Override
        public String getDeliveryType() {
            return "INTERNATIONAL";
        }

        @Override
        public double calculateFee() {
            return 35.00 + (weight * 2.00) + (distance * 0.50) + customsFee;
        }
    }

    /**
     * Functional interface for parsing a delivery from string tokens.
     */
    @FunctionalInterface
    public interface DeliveryParser {
        Delivery parse(String[] tokens);
    }

    /**
     * Factory class mapping delivery types to parser functions to avoid if-else chains.
     */
    public static class DeliveryFactory {
        private static final Map<String, DeliveryParser> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("STANDARD", tokens -> new StandardDelivery(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2])
            ));
            REGISTRY.put("EXPRESS", tokens -> new ExpressDelivery(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2])
            ));
            REGISTRY.put("INTERNATIONAL", tokens -> new InternationalDelivery(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]),
                    Double.parseDouble(tokens[3])
            ));
        }

        public static Delivery createDelivery(String[] tokens) {
            if (tokens == null || tokens.length < 3) {
                throw new IllegalArgumentException("Invalid delivery input format");
            }
            DeliveryParser parser = REGISTRY.get(tokens[0].toUpperCase(Locale.ROOT));
            if (parser == null) {
                throw new IllegalArgumentException("Unknown delivery type: " + tokens[0]);
            }
            return parser.parse(tokens);
        }
    }

    /**
     * Delivery service handling billing calculations polymorphically.
     */
    public static class DeliveryService {
        public static void processDeliveries(List<Delivery> deliveries) {
            double grandTotal = 0.0;
            for (Delivery delivery : deliveries) {
                double fee = delivery.calculateFee();
                System.out.printf(Locale.US, "%s: %.2f%n", delivery.getDeliveryType(), fee);
                grandTotal += fee;
            }
            System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Delivery> deliveries = new ArrayList<>();

        while (deliveries.size() < n && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            deliveries.add(DeliveryFactory.createDelivery(tokens));
        }

        DeliveryService.processDeliveries(deliveries);
    }
}

/**
 * Alias class for direct execution without L3_ prefix.
 */
class DeliveryFeeCalculator extends L3_DeliveryFeeCalculator {
    public static void main(String[] args) {
        L3_DeliveryFeeCalculator.main(args);
    }
}