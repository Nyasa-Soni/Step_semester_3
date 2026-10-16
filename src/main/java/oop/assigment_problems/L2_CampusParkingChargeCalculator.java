package oop.assigment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Assignment Problem 2: The Campus Parking Charge Calculator
 *
 * Demonstrates OOP polymorphism:
 * - Base abstract class Vehicle defines common vehicle attributes and abstract calculation method.
 * - Subclasses BikeVehicle, CarVehicle, and TruckVehicle implement their respective parking rate rules.
 * - VehicleFactory instantiates vehicle types using a registry map, avoiding if-else chains.
 * - ParkingSystem processes vehicle charges polymorphically.
 */
public class L2_CampusParkingChargeCalculator {

    /**
     * Abstract base class representing a parked vehicle.
     */
    public static abstract class Vehicle {
        protected final int hours;

        public Vehicle(int hours) {
            this.hours = hours;
        }

        public int getHours() {
            return hours;
        }

        public abstract String getType();

        public abstract double calculateCharge();
    }

    /**
     * Bike: Rs 10 per hour.
     */
    public static class BikeVehicle extends Vehicle {
        public BikeVehicle(int hours) {
            super(hours);
        }

        @Override
        public String getType() {
            return "BIKE";
        }

        @Override
        public double calculateCharge() {
            return hours * 10.00;
        }
    }

    /**
     * Car: Rs 30 for the first hour, plus Rs 20 for each additional hour.
     */
    public static class CarVehicle extends Vehicle {
        public CarVehicle(int hours) {
            super(hours);
        }

        @Override
        public String getType() {
            return "CAR";
        }

        @Override
        public double calculateCharge() {
            if (hours <= 1) {
                return 30.00;
            }
            return 30.00 + (hours - 1) * 20.00;
        }
    }

    /**
     * Truck: Rs 50 per hour, with a minimum charge of Rs 100.
     */
    public static class TruckVehicle extends Vehicle {
        public TruckVehicle(int hours) {
            super(hours);
        }

        @Override
        public String getType() {
            return "TRUCK";
        }

        @Override
        public double calculateCharge() {
            return Math.max(100.00, hours * 50.00);
        }
    }

    /**
     * Factory class registering vehicle creators to avoid if-else chains.
     */
    public static class VehicleFactory {
        private static final Map<String, Function<Integer, Vehicle>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BIKE", BikeVehicle::new);
            REGISTRY.put("CAR", CarVehicle::new);
            REGISTRY.put("TRUCK", TruckVehicle::new);
        }

        public static Vehicle createVehicle(String type, int hours) {
            Function<Integer, Vehicle> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
            }
            return creator.apply(hours);
        }
    }

    /**
     * Parking service handling parking fee calculations polymorphically.
     */
    public static class ParkingSystem {
        public static void processVehicles(List<Vehicle> vehicles) {
            double grandTotal = 0.0;
            for (Vehicle vehicle : vehicles) {
                double charge = vehicle.calculateCharge();
                System.out.printf(Locale.US, "%s: %.2f%n", vehicle.getType(), charge);
                grandTotal += charge;
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
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int hours = scanner.nextInt();
            vehicles.add(VehicleFactory.createVehicle(type, hours));
        }
        ParkingSystem.processVehicles(vehicles);
    }
}

/**
 * Alias class for direct execution without L2_ prefix.
 */
class CampusParkingChargeCalculator extends L2_CampusParkingChargeCalculator {
    public static void main(String[] args) {
        L2_CampusParkingChargeCalculator.main(args);
    }
}