package abstraction_interface.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Problem 5: Travel Booking with a Common Fee
 *
 * Demonstrates Abstraction:
 * - Common abstract base class TravelBooking defines the shared booking fee rule (50.00)
 *   in one place only. If the fee changes, only this one line of code needs to change.
 * - Subclasses BusBooking, TrainBooking, and FlightBooking implement mode-specific base fare formulas:
 *   - Bus: 2 per km.
 *   - Train: 1.5 per km.
 *   - Flight: 2500 base plus 4 per km.
 * - TravelBookingSystem processes bookings polymorphically and displays the final fare.
 */
public class L5_TravelBookingWithCommonFee {

    /**
     * Abstract base class representing a generic travel booking.
     * Centralizes the common booking fee and final fare calculation.
     */
    public static abstract class TravelBooking {
        public static final double BOOKING_FEE = 50.00;

        protected final double distanceKm;

        public TravelBooking(double distanceKm) {
            this.distanceKm = distanceKm;
        }

        public double getDistanceKm() {
            return distanceKm;
        }

        public abstract String getMode();

        public abstract double calculateBaseFare();

        /**
         * Calculates final total fare by adding the shared booking fee.
         * Defined here in one place only.
         */
        public double calculateTotalFare() {
            return calculateBaseFare() + BOOKING_FEE;
        }
    }

    /**
     * Bus booking: 2 per km.
     */
    public static class BusBooking extends TravelBooking {
        public static final double RATE_PER_KM = 2.00;

        public BusBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        public String getMode() {
            return "BUS";
        }

        @Override
        public double calculateBaseFare() {
            return distanceKm * RATE_PER_KM;
        }
    }

    /**
     * Train booking: 1.5 per km.
     */
    public static class TrainBooking extends TravelBooking {
        public static final double RATE_PER_KM = 1.50;

        public TrainBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        public String getMode() {
            return "TRAIN";
        }

        @Override
        public double calculateBaseFare() {
            return distanceKm * RATE_PER_KM;
        }
    }

    /**
     * Flight booking: 2500 fixed base plus 4 per km.
     */
    public static class FlightBooking extends TravelBooking {
        public static final double FIXED_BASE = 2500.00;
        public static final double RATE_PER_KM = 4.00;

        public FlightBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        public String getMode() {
            return "FLIGHT";
        }

        @Override
        public double calculateBaseFare() {
            return FIXED_BASE + (distanceKm * RATE_PER_KM);
        }
    }

    /**
     * Factory class registering booking creators to avoid if-else chains.
     */
    public static class BookingFactory {
        private static final Map<String, Function<Double, TravelBooking>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BUS", BusBooking::new);
            REGISTRY.put("TRAIN", TrainBooking::new);
            REGISTRY.put("FLIGHT", FlightBooking::new);
        }

        public static TravelBooking createBooking(String mode, double distanceKm) {
            Function<Double, TravelBooking> creator = REGISTRY.get(mode.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown travel mode: " + mode);
            }
            return creator.apply(distanceKm);
        }
    }

    /**
     * Booking service processing travel bookings polymorphically.
     */
    public static class TravelBookingSystem {
        public static void processBookings(List<TravelBooking> bookings) {
            for (TravelBooking booking : bookings) {
                double total = booking.calculateTotalFare();
                System.out.printf(Locale.US, "%s: %.2f%n", booking.getMode(), total);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<TravelBooking> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String mode = scanner.next();
            double distanceKm = scanner.nextDouble();
            bookings.add(BookingFactory.createBooking(mode, distanceKm));
        }
        TravelBookingSystem.processBookings(bookings);
    }
}

/**
 * Alias class for direct execution without L5_ prefix.
 */
class TravelBookingWithCommonFee extends L5_TravelBookingWithCommonFee {
    public static void main(String[] args) {
        L5_TravelBookingWithCommonFee.main(args);
    }
}