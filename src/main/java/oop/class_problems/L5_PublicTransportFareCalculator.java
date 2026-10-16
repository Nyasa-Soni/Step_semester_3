package oop.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Class Problem 5: Public Transport Fare Calculator
 *
 * Demonstrates OOP Polymorphism:
 * - Common abstract base class Journey defines distance traveled and abstract calculateFare().
 * - Specialized subclasses BusJourney, TrainJourney, and MetroJourney encapsulate their distinct fare structures
 *   and specific parameters (e.g., max fare cap for Bus, peakHourFactor for Metro).
 * - JourneyFactory uses a registry map with functional parsers to eliminate if-else type checks.
 * - PublicTransportSystem processes all journeys polymorphically.
 */
public class L5_PublicTransportFareCalculator {

    /**
     * Abstract base class representing a public transport journey.
     */
    public static abstract class Journey {
        protected final double distance;

        public Journey(double distance) {
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }

        public abstract String getTransportType();

        public abstract double calculateFare();
    }

    /**
     * Bus journey:
     * Base fare $2, plus $0.10 per km. Max fare $10.
     */
    public static class BusJourney extends Journey {
        public BusJourney(double distance) {
            super(distance);
        }

        @Override
        public String getTransportType() {
            return "BUS";
        }

        @Override
        public double calculateFare() {
            return Math.min(10.00, 2.00 + (distance * 0.10));
        }
    }

    /**
     * Train journey:
     * Base fare $3, plus $0.15 per km.
     */
    public static class TrainJourney extends Journey {
        public TrainJourney(double distance) {
            super(distance);
        }

        @Override
        public String getTransportType() {
            return "TRAIN";
        }

        @Override
        public double calculateFare() {
            return 3.00 + (distance * 0.15);
        }
    }

    /**
     * Metro journey:
     * Base fare $1.50, plus $0.20 per km, multiplied by PeakHourFactor (1.0 to 2.0).
     */
    public static class MetroJourney extends Journey {
        private final double peakHourFactor;

        public MetroJourney(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }

        public double getPeakHourFactor() {
            return peakHourFactor;
        }

        @Override
        public String getTransportType() {
            return "METRO";
        }

        @Override
        public double calculateFare() {
            return (1.50 + (distance * 0.20)) * peakHourFactor;
        }
    }

    /**
     * Functional interface for parsing a Journey from string tokens.
     */
    @FunctionalInterface
    public interface JourneyParser {
        Journey parse(String[] tokens);
    }

    /**
     * Factory class mapping transport types to functional parsers to eliminate if-else chains.
     */
    public static class JourneyFactory {
        private static final Map<String, JourneyParser> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BUS", tokens -> new BusJourney(Double.parseDouble(tokens[1])));
            REGISTRY.put("TRAIN", tokens -> new TrainJourney(Double.parseDouble(tokens[1])));
            REGISTRY.put("METRO", tokens -> new MetroJourney(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2])
            ));
        }

        public static Journey createJourney(String[] tokens) {
            if (tokens == null || tokens.length < 2) {
                throw new IllegalArgumentException("Invalid journey input format");
            }
            JourneyParser parser = REGISTRY.get(tokens[0].toUpperCase(Locale.ROOT));
            if (parser == null) {
                throw new IllegalArgumentException("Unknown transport type: " + tokens[0]);
            }
            return parser.parse(tokens);
        }
    }

    /**
     * Public transport system processing all journeys polymorphically.
     */
    public static class PublicTransportSystem {
        public static void processJourneys(List<Journey> journeys) {
            double grandTotal = 0.0;
            for (Journey journey : journeys) {
                double fare = journey.calculateFare();
                System.out.printf(Locale.US, "%s: %.2f%n", journey.getTransportType(), fare);
                grandTotal += fare;
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
        List<Journey> journeys = new ArrayList<>();

        while (journeys.size() < n && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            journeys.add(JourneyFactory.createJourney(tokens));
        }

        PublicTransportSystem.processJourneys(journeys);
    }
}

/**
 * Alias class for direct execution without L5_ prefix.
 */
class PublicTransportFareCalculator extends L5_PublicTransportFareCalculator {
    public static void main(String[] args) {
        L5_PublicTransportFareCalculator.main(args);
    }
}