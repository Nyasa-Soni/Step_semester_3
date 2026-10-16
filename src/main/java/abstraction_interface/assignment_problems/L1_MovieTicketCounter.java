package abstraction_interface.assignment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Problem 1: Movie Ticket Counter
 *
 * Demonstrates Abstraction:
 * - Abstract base class Ticket captures common attributes (count, convenience fee rule)
 *   and enforces that a plain ticket without a concrete seat type can never be instantiated.
 * - The convenience fee rule (20 per ticket) and total calculation formula are written
 *   in one place only within the abstract Ticket class.
 * - Derived classes RegularTicket, PremiumTicket, and ReclinerTicket provide their
 *   seat-specific base pricing.
 * - TicketFactory provides a clean registry-based object creation without branching logic.
 */
public class L1_MovieTicketCounter {

    /**
     * Abstract base class representing a movie booking.
     * Cannot be instantiated directly, ensuring plain tickets cannot be sold.
     */
    public static abstract class Ticket {
        public static final double CONVENIENCE_FEE = 20.00;

        protected final int count;

        public Ticket(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        /**
         * Returns the seat type name.
         */
        public abstract String getSeatType();

        /**
         * Returns the seat-specific base price per ticket.
         */
        public abstract double getBasePricePerTicket();

        /**
         * Calculates total amount for this booking.
         * The shared convenience fee rule is written here in one place only.
         */
        public double calculateTotalAmount() {
            return count * (getBasePricePerTicket() + CONVENIENCE_FEE);
        }
    }

    /**
     * Regular seat ticket: 150 per ticket.
     */
    public static class RegularTicket extends Ticket {
        public static final double BASE_PRICE = 150.00;

        public RegularTicket(int count) {
            super(count);
        }

        @Override
        public String getSeatType() {
            return "REGULAR";
        }

        @Override
        public double getBasePricePerTicket() {
            return BASE_PRICE;
        }
    }

    /**
     * Premium seat ticket: 250 per ticket.
     */
    public static class PremiumTicket extends Ticket {
        public static final double BASE_PRICE = 250.00;

        public PremiumTicket(int count) {
            super(count);
        }

        @Override
        public String getSeatType() {
            return "PREMIUM";
        }

        @Override
        public double getBasePricePerTicket() {
            return BASE_PRICE;
        }
    }

    /**
     * Recliner seat ticket: 400 per ticket.
     */
    public static class ReclinerTicket extends Ticket {
        public static final double BASE_PRICE = 400.00;

        public ReclinerTicket(int count) {
            super(count);
        }

        @Override
        public String getSeatType() {
            return "RECLINER";
        }

        @Override
        public double getBasePricePerTicket() {
            return BASE_PRICE;
        }
    }

    /**
     * Factory class registering ticket constructors to avoid if-else chains.
     */
    public static class TicketFactory {
        private static final Map<String, Function<Integer, Ticket>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("REGULAR", RegularTicket::new);
            REGISTRY.put("PREMIUM", PremiumTicket::new);
            REGISTRY.put("RECLINER", ReclinerTicket::new);
        }

        public static Ticket createTicket(String seatType, int count) {
            Function<Integer, Ticket> creator = REGISTRY.get(seatType.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown seat type: " + seatType);
            }
            return creator.apply(count);
        }
    }

    /**
     * Counter system processing bookings polymorphically.
     */
    public static class TicketCounterSystem {
        public static void processBookings(List<Ticket> bookings) {
            double totalCollected = 0.0;
            for (Ticket ticket : bookings) {
                double amount = ticket.calculateTotalAmount();
                System.out.printf(Locale.US, "%s: %.2f%n", ticket.getSeatType(), amount);
                totalCollected += amount;
            }
            System.out.printf(Locale.US, "Total: %.2f%n", totalCollected);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Ticket> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String seatType = scanner.next();
            int count = scanner.nextInt();
            bookings.add(TicketFactory.createTicket(seatType, count));
        }
        TicketCounterSystem.processBookings(bookings);
    }
}

/**
 * Alias class for direct execution without L1_ prefix.
 */
class MovieTicketCounter extends L1_MovieTicketCounter {
    public static void main(String[] args) {
        L1_MovieTicketCounter.main(args);
    }
}
