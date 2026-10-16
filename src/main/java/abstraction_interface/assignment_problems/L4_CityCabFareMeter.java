package abstraction_interface.assignment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Problem 4: City Cab Fare Meter
 *
 * Demonstrates Abstraction & Capability Interfaces:
 * - Abstract base class Cab encapsulates common cab behavior and the shared minimum fare rule (100.00).
 * - Minimum fare logic is written in one place only in the base Cab class.
 * - Capability interface NightServiceCapable models night service as an optional capability,
 *   so cabs that do not support it (MiniCab) do not have to pretend to offer it.
 * - SedanCab and SuvCab implement NightServiceCapable.
 * - CabFactory provides clean lookup-based instantiation without if-else chains.
 * - Fare meter system processes trips polymorphically and rejects night trips for incapable cabs.
 */
public class L4_CityCabFareMeter {

    /**
     * Interface defining the night service capability.
     * Only cabs implementing this interface support night trips with a 20% surcharge.
     */
    public interface NightServiceCapable {
        double NIGHT_SURCHARGE_RATE = 0.20;

        default double applyNightSurcharge(double baseFare) {
            return baseFare * (1.0 + NIGHT_SURCHARGE_RATE);
        }
    }

    /**
     * Abstract base class representing a generic cab.
     * Centralizes the shared minimum fare rule.
     */
    public static abstract class Cab {
        public static final double MINIMUM_FARE = 100.00;

        public abstract String getType();

        public abstract double getRatePerKm();

        /**
         * Calculates base fare ensuring the minimum fare rule is applied.
         */
        public double calculateBaseFare(double km) {
            return Math.max(MINIMUM_FARE, km * getRatePerKm());
        }
    }

    /**
     * Mini cab: 10 per km.
     * Does not support night service.
     */
    public static class MiniCab extends Cab {
        public static final double RATE_PER_KM = 10.00;

        @Override
        public String getType() {
            return "MINI";
        }

        @Override
        public double getRatePerKm() {
            return RATE_PER_KM;
        }
    }

    /**
     * Sedan cab: 14 per km.
     * Supports night service (+20%).
     */
    public static class SedanCab extends Cab implements NightServiceCapable {
        public static final double RATE_PER_KM = 14.00;

        @Override
        public String getType() {
            return "SEDAN";
        }

        @Override
        public double getRatePerKm() {
            return RATE_PER_KM;
        }
    }

    /**
     * SUV cab: 18 per km.
     * Supports night service (+20%).
     */
    public static class SuvCab extends Cab implements NightServiceCapable {
        public static final double RATE_PER_KM = 18.00;

        @Override
        public String getType() {
            return "SUV";
        }

        @Override
        public double getRatePerKm() {
            return RATE_PER_KM;
        }
    }

    /**
     * Class representing an individual trip request.
     */
    public static class TripRequest {
        private final Cab cab;
        private final double km;
        private final boolean night;

        public TripRequest(Cab cab, double km, boolean night) {
            this.cab = cab;
            this.km = km;
            this.night = night;
        }

        public Cab getCab() {
            return cab;
        }

        public double getKm() {
            return km;
        }

        public boolean isNight() {
            return night;
        }
    }

    /**
     * Factory class registering cab creators to avoid if-else chains.
     */
    public static class CabFactory {
        private static final Map<String, Supplier<Cab>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("MINI", MiniCab::new);
            REGISTRY.put("SEDAN", SedanCab::new);
            REGISTRY.put("SUV", SuvCab::new);
        }

        public static Cab createCab(String type) {
            Supplier<Cab> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown cab type: " + type);
            }
            return creator.get();
        }
    }

    /**
     * Meter system handling trip fare calculations polymorphically.
     */
    public static class FareMeterSystem {
        public static void processTrips(List<TripRequest> trips) {
            double grandTotal = 0.0;
            for (TripRequest trip : trips) {
                Cab cab = trip.getCab();
                double km = trip.getKm();
                boolean isNight = trip.isNight();

                if (isNight) {
                    if (cab instanceof NightServiceCapable nightCab) {
                        double baseFare = cab.calculateBaseFare(km);
                        double finalFare = nightCab.applyNightSurcharge(baseFare);
                        System.out.printf(Locale.US, "%s: %.2f%n", cab.getType(), finalFare);
                        grandTotal += finalFare;
                    } else {
                        System.out.printf(Locale.US, "%s: night service not available%n", cab.getType());
                    }
                } else {
                    double finalFare = cab.calculateBaseFare(km);
                    System.out.printf(Locale.US, "%s: %.2f%n", cab.getType(), finalFare);
                    grandTotal += finalFare;
                }
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
        List<TripRequest> trips = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String cabType = scanner.next();
            double km = scanner.nextDouble();
            String time = scanner.next();
            boolean isNight = time.equalsIgnoreCase("NIGHT");
            trips.add(new TripRequest(CabFactory.createCab(cabType), km, isNight));
        }
        FareMeterSystem.processTrips(trips);
    }
}

/**
 * Alias class for direct execution without L4_ prefix.
 */
class CityCabFareMeter extends L4_CityCabFareMeter {
    public static void main(String[] args) {
        L4_CityCabFareMeter.main(args);
    }
}