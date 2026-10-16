package abstraction_interface.assignment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Problem 5: Home Appliance Energy Report
 *
 * Demonstrates Abstraction & Capability Interfaces:
 * - Abstract base class Appliance defines power rating, the standard kWh units calculation
 *   (power * hours / 1000), and cost conversion (units * 8), centralizing shared formulas.
 * - Capability interface SaverModeCapable represents the optional energy-saver capability
 *   that reduces energy units by 25%.
 * - AcAppliance and WasherAppliance implement SaverModeCapable.
 * - FridgeAppliance and TvAppliance do not implement SaverModeCapable.
 * - ApplianceFactory registers appliance creators in a map without branching logic.
 * - EnergyReportSystem processes reports uniformly via capability checking without
 *   chaining appliance type names.
 */
public class L5_HomeApplianceEnergyReport {

    /**
     * Interface representing the optional saver-mode capability.
     */
    public interface SaverModeCapable {
        double SAVER_REDUCTION = 0.25;

        default double applySaverReduction(double standardUnits) {
            return standardUnits * (1.0 - SAVER_REDUCTION);
        }
    }

    /**
     * Abstract base class representing a home appliance.
     * Shared formulas for standard units and cost are defined here.
     */
    public static abstract class Appliance {
        public static final double COST_PER_UNIT = 8.00;

        protected final double powerWatts;

        public Appliance(double powerWatts) {
            this.powerWatts = powerWatts;
        }

        public double getPowerWatts() {
            return powerWatts;
        }

        public abstract String getName();

        /**
         * Calculates standard energy units consumed in kWh.
         */
        public double calculateStandardUnits(double hours) {
            return (powerWatts * hours) / 1000.0;
        }

        /**
         * Converts energy units to cost.
         */
        public double calculateCost(double units) {
            return units * COST_PER_UNIT;
        }
    }

    /**
     * Refrigerator: 150 W. Does not support saver mode.
     */
    public static class FridgeAppliance extends Appliance {
        public static final double POWER_WATTS = 150.0;

        public FridgeAppliance() {
            super(POWER_WATTS);
        }

        @Override
        public String getName() {
            return "FRIDGE";
        }
    }

    /**
     * Air Conditioner: 1500 W. Supports saver mode.
     */
    public static class AcAppliance extends Appliance implements SaverModeCapable {
        public static final double POWER_WATTS = 1500.0;

        public AcAppliance() {
            super(POWER_WATTS);
        }

        @Override
        public String getName() {
            return "AC";
        }
    }

    /**
     * Television: 100 W. Does not support saver mode.
     */
    public static class TvAppliance extends Appliance {
        public static final double POWER_WATTS = 100.0;

        public TvAppliance() {
            super(POWER_WATTS);
        }

        @Override
        public String getName() {
            return "TV";
        }
    }

    /**
     * Washing Machine: 500 W. Supports saver mode.
     */
    public static class WasherAppliance extends Appliance implements SaverModeCapable {
        public static final double POWER_WATTS = 500.0;

        public WasherAppliance() {
            super(POWER_WATTS);
        }

        @Override
        public String getName() {
            return "WASHER";
        }
    }

    /**
     * Class representing a report entry request.
     */
    public static class UsageRequest {
        private final Appliance appliance;
        private final double hours;
        private final boolean saverModeRequested;

        public UsageRequest(Appliance appliance, double hours, boolean saverModeRequested) {
            this.appliance = appliance;
            this.hours = hours;
            this.saverModeRequested = saverModeRequested;
        }

        public Appliance getAppliance() {
            return appliance;
        }

        public double getHours() {
            return hours;
        }

        public boolean isSaverModeRequested() {
            return saverModeRequested;
        }
    }

    /**
     * Factory class registering appliance creators to avoid if-else chains.
     */
    public static class ApplianceFactory {
        private static final Map<String, Supplier<Appliance>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("FRIDGE", FridgeAppliance::new);
            REGISTRY.put("AC", AcAppliance::new);
            REGISTRY.put("TV", TvAppliance::new);
            REGISTRY.put("WASHER", WasherAppliance::new);
        }

        public static Appliance createAppliance(String name) {
            Supplier<Appliance> creator = REGISTRY.get(name.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown appliance: " + name);
            }
            return creator.get();
        }
    }

    /**
     * Energy report service processing usage requests polymorphically.
     */
    public static class EnergyReportSystem {
        public static void processReports(List<UsageRequest> requests) {
            double totalCost = 0.0;
            for (UsageRequest req : requests) {
                Appliance appliance = req.getAppliance();
                double hours = req.getHours();

                if (req.isSaverModeRequested()) {
                    if (appliance instanceof SaverModeCapable saverAppliance) {
                        double standardUnits = appliance.calculateStandardUnits(hours);
                        double units = saverAppliance.applySaverReduction(standardUnits);
                        double cost = appliance.calculateCost(units);
                        System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n",
                                appliance.getName(), units, cost);
                        totalCost += cost;
                    } else {
                        System.out.printf(Locale.US, "%s: saver mode not supported%n",
                                appliance.getName());
                    }
                } else {
                    double units = appliance.calculateStandardUnits(hours);
                    double cost = appliance.calculateCost(units);
                    System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n",
                            appliance.getName(), units, cost);
                    totalCost += cost;
                }
            }
            System.out.printf(Locale.US, "Total Cost: %.2f%n", totalCost);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        List<UsageRequest> requests = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            while (line.isEmpty() && scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
            }
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+");
            String applianceName = parts[0];
            double hours = Double.parseDouble(parts[1]);
            boolean saverRequested = parts.length > 2 && parts[2].equalsIgnoreCase("SAVER");
            requests.add(new UsageRequest(
                    ApplianceFactory.createAppliance(applianceName),
                    hours,
                    saverRequested
            ));
        }
        EnergyReportSystem.processReports(requests);
    }
}

/**
 * Alias class for direct execution without L5_ prefix.
 */
class HomeApplianceEnergyReport extends L5_HomeApplianceEnergyReport {
    public static void main(String[] args) {
        L5_HomeApplianceEnergyReport.main(args);
    }
}