package abstraction_interface.assignment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Problem 2: Parcel Shipping Desk
 *
 * Demonstrates Class vs Interface (Abstraction & Capabilities):
 * - Abstract base class Parcel models common parcel attributes (weightKg, declaredValue)
 *   and enforces shipping charge calculation.
 * - Interface Insurable defines the optional insurance capability, separating shipping-charge
 *   behavior from insurance eligibility.
 * - StandardParcel does not implement Insurable, keeping non-insurable parcels decoupled.
 * - ExpressParcel and FragileParcel implement Insurable with standard 2% rate.
 * - FragileParcel extends StandardParcel to reuse standard charge logic while adding the handling fee.
 * - ParcelFactory registers parcel creators in a lookup map, avoiding if-else chains.
 */
public class L2_ParcelShippingDesk {

    /**
     * Interface defining the optional insurance capability for parcels.
     */
    public interface Insurable {
        double INSURANCE_RATE = 0.02;

        double getDeclaredValue();

        default double calculateInsurance() {
            return getDeclaredValue() * INSURANCE_RATE;
        }
    }

    /**
     * Abstract base class representing a generic parcel.
     */
    public static abstract class Parcel {
        protected final double weightKg;
        protected final double declaredValue;

        public Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        public double getWeightKg() {
            return weightKg;
        }

        public double getDeclaredValue() {
            return declaredValue;
        }

        public abstract String getType();

        public abstract double calculateShippingCharge();
    }

    /**
     * Standard parcel: 40 base + 10 per kg.
     * Cannot be insured.
     */
    public static class StandardParcel extends Parcel {
        public static final double BASE_FEE = 40.00;
        public static final double RATE_PER_KG = 10.00;

        public StandardParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        @Override
        public String getType() {
            return "STANDARD";
        }

        @Override
        public double calculateShippingCharge() {
            return BASE_FEE + (RATE_PER_KG * weightKg);
        }
    }

    /**
     * Express parcel: 80 base + 15 per kg.
     * Can be insured.
     */
    public static class ExpressParcel extends Parcel implements Insurable {
        public static final double BASE_FEE = 80.00;
        public static final double RATE_PER_KG = 15.00;

        public ExpressParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        @Override
        public String getType() {
            return "EXPRESS";
        }

        @Override
        public double calculateShippingCharge() {
            return BASE_FEE + (RATE_PER_KG * weightKg);
        }
    }

    /**
     * Fragile parcel: standard charge + 50 handling fee.
     * Extends StandardParcel for behavior reuse and implements Insurable.
     */
    public static class FragileParcel extends StandardParcel implements Insurable {
        public static final double HANDLING_FEE = 50.00;

        public FragileParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        @Override
        public String getType() {
            return "FRAGILE";
        }

        @Override
        public double calculateShippingCharge() {
            return super.calculateShippingCharge() + HANDLING_FEE;
        }
    }

    /**
     * Factory class registering parcel creators to avoid if-else chains.
     */
    public static class ParcelFactory {
        private static final Map<String, BiFunction<Double, Double, Parcel>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("STANDARD", StandardParcel::new);
            REGISTRY.put("EXPRESS", ExpressParcel::new);
            REGISTRY.put("FRAGILE", FragileParcel::new);
        }

        public static Parcel createParcel(String type, double weightKg, double declaredValue) {
            BiFunction<Double, Double, Parcel> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown parcel type: " + type);
            }
            return creator.apply(weightKg, declaredValue);
        }
    }

    /**
     * Shipping desk service processing parcels polymorphically.
     */
    public static class ShippingDeskSystem {
        public static void processParcels(List<Parcel> parcels) {
            double grandTotal = 0.0;
            for (Parcel parcel : parcels) {
                double charge = parcel.calculateShippingCharge();
                double insurance = (parcel instanceof Insurable insurable)
                        ? insurable.calculateInsurance()
                        : 0.00;
                double total = charge + insurance;
                System.out.printf(Locale.US, "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                        parcel.getType(), charge, insurance, total);
                grandTotal += total;
            }
            System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double declaredValue = scanner.nextDouble();
            parcels.add(ParcelFactory.createParcel(type, weight, declaredValue));
        }
        ShippingDeskSystem.processParcels(parcels);
    }
}

/**
 * Alias class for direct execution without L2_ prefix.
 */
class ParcelShippingDesk extends L2_ParcelShippingDesk {
    public static void main(String[] args) {
        L2_ParcelShippingDesk.main(args);
    }
}