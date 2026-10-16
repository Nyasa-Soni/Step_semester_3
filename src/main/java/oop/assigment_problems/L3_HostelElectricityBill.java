package oop.assigment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Assignment Problem 3: The Hostel Electricity Bill
 *
 * Demonstrates OOP polymorphism and clean encapsulation:
 * - Base abstract class Room defines common attributes (units) and abstract methods.
 * - Subclasses SingleRoom, SharedRoom, and AcRoom implement specific billing formulas.
 * - SharedRoom encapsulates its extra attribute (occupants) cleanly inside its own class.
 * - RoomFactory uses a parser registry to create rooms based on line tokens without if-else blocks.
 * - The ElectricityBillingSystem processes all rooms uniformly without type-checking.
 */
public class L3_HostelElectricityBill {

    /**
     * Abstract base class representing a hostel room.
     */
    public static abstract class Room {
        protected final int units;

        public Room(int units) {
            this.units = units;
        }

        public int getUnits() {
            return units;
        }

        public abstract String getRoomType();

        public abstract double calculateBill();
    }

    /**
     * Single room: Rs 8 per unit.
     */
    public static class SingleRoom extends Room {
        public SingleRoom(int units) {
            super(units);
        }

        @Override
        public String getRoomType() {
            return "SINGLE";
        }

        @Override
        public double calculateBill() {
            return units * 8.00;
        }
    }

    /**
     * Shared room: Rs 6 per unit, divided equally by number of occupants.
     * The extra parameter 'occupants' is encapsulated within this subclass.
     */
    public static class SharedRoom extends Room {
        private final int occupants;

        public SharedRoom(int units, int occupants) {
            super(units);
            this.occupants = occupants;
        }

        public int getOccupants() {
            return occupants;
        }

        @Override
        public String getRoomType() {
            return "SHARED";
        }

        @Override
        public double calculateBill() {
            return (units * 6.00) / occupants;
        }
    }

    /**
     * AC room: Rs 10 per unit, plus a fixed charge of Rs 200.
     */
    public static class AcRoom extends Room {
        public AcRoom(int units) {
            super(units);
        }

        @Override
        public String getRoomType() {
            return "AC";
        }

        @Override
        public double calculateBill() {
            return (units * 10.00) + 200.00;
        }
    }

    /**
     * Functional interface for room creation from tokens.
     */
    @FunctionalInterface
    public interface RoomParser {
        Room parse(String[] tokens);
    }

    /**
     * Factory class mapping room types to their specialized parsers.
     */
    public static class RoomFactory {
        private static final Map<String, RoomParser> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("SINGLE", tokens -> new SingleRoom(Integer.parseInt(tokens[1])));
            REGISTRY.put("SHARED", tokens -> new SharedRoom(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            REGISTRY.put("AC", tokens -> new AcRoom(Integer.parseInt(tokens[1])));
        }

        public static Room createRoom(String[] tokens) {
            if (tokens == null || tokens.length < 2) {
                throw new IllegalArgumentException("Invalid input tokens");
            }
            RoomParser parser = REGISTRY.get(tokens[0].toUpperCase(Locale.ROOT));
            if (parser == null) {
                throw new IllegalArgumentException("Unknown room type: " + tokens[0]);
            }
            return parser.parse(tokens);
        }
    }

    /**
     * Billing system processing rooms polymorphically.
     */
    public static class ElectricityBillingSystem {
        public static void processRooms(List<Room> rooms) {
            double grandTotal = 0.0;
            for (Room room : rooms) {
                double bill = room.calculateBill();
                System.out.printf(Locale.US, "%s: %.2f%n", room.getRoomType(), bill);
                grandTotal += bill;
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
        List<Room> rooms = new ArrayList<>();
        while (rooms.size() < n && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tokens = line.split("\\s+");
            rooms.add(RoomFactory.createRoom(tokens));
        }
        ElectricityBillingSystem.processRooms(rooms);
    }
}

/**
 * Alias class for direct execution without L3_ prefix.
 */
class HostelElectricityBill extends L3_HostelElectricityBill {
    public static void main(String[] args) {
        L3_HostelElectricityBill.main(args);
    }
}