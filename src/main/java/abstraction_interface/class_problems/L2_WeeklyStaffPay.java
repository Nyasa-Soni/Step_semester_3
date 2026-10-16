package abstraction_interface.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 2: Weekly Staff Pay
 *
 * Demonstrates Abstraction:
 * - Abstract base class StaffMember enforces that a generic staff member without a specific pay rule
 *   can never be instantiated directly.
 * - Subclasses FullTimeStaff, HourlyStaff, and InternStaff implement their respective pay calculation logic.
 * - HourlyStaff correctly computes standard pay up to 40 hours and 1.5x overtime for hours worked above 40.
 * - PayrollSystem calculates and displays each staff member's pay and the overall payroll polymorphically.
 */
public class L2_WeeklyStaffPay {

    /**
     * Abstract base class representing a generic staff member.
     * Prevents direct instantiation while ensuring every concrete staff type defines calculatePay().
     */
    public static abstract class StaffMember {
        protected final String name;

        public StaffMember(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract double calculatePay();
    }

    /**
     * Full-time staff: paid a fixed weekly salary.
     */
    public static class FullTimeStaff extends StaffMember {
        private final double weeklySalary;

        public FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }

        public double getWeeklySalary() {
            return weeklySalary;
        }

        @Override
        public double calculatePay() {
            return weeklySalary;
        }
    }

    /**
     * Hourly staff: paid normal rate up to 40 hours, and 1.5x overtime for hours exceeding 40.
     */
    public static class HourlyStaff extends StaffMember {
        public static final double OVERTIME_THRESHOLD = 40.0;
        public static final double OVERTIME_MULTIPLIER = 1.5;

        private final double hoursWorked;
        private final double hourlyRate;

        public HourlyStaff(String name, double hoursWorked, double hourlyRate) {
            super(name);
            this.hoursWorked = hoursWorked;
            this.hourlyRate = hourlyRate;
        }

        public double getHoursWorked() {
            return hoursWorked;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }

        @Override
        public double calculatePay() {
            if (hoursWorked <= OVERTIME_THRESHOLD) {
                return hoursWorked * hourlyRate;
            }
            double regularPay = OVERTIME_THRESHOLD * hourlyRate;
            double overtimeHours = hoursWorked - OVERTIME_THRESHOLD;
            double overtimePay = overtimeHours * (hourlyRate * OVERTIME_MULTIPLIER);
            return regularPay + overtimePay;
        }
    }

    /**
     * Intern staff: paid a fixed stipend.
     */
    public static class InternStaff extends StaffMember {
        private final double stipend;

        public InternStaff(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        public double getStipend() {
            return stipend;
        }

        @Override
        public double calculatePay() {
            return stipend;
        }
    }

    /**
     * Factory class for staff member instantiation.
     */
    public static class StaffFactory {
        public static StaffMember createStaff(String type, String name, Scanner scanner) {
            switch (type.toUpperCase(Locale.ROOT)) {
                case "FULLTIME":
                    double weeklySalary = scanner.nextDouble();
                    return new FullTimeStaff(name, weeklySalary);
                case "HOURLY":
                    double hours = scanner.nextDouble();
                    double rate = scanner.nextDouble();
                    return new HourlyStaff(name, hours, rate);
                case "INTERN":
                    double stipend = scanner.nextDouble();
                    return new InternStaff(name, stipend);
                default:
                    throw new IllegalArgumentException("Unknown staff type: " + type);
            }
        }
    }

    /**
     * Payroll service processing staff pay polymorphically.
     */
    public static class PayrollSystem {
        public static void processPayroll(List<StaffMember> staffList) {
            double totalPayroll = 0.0;
            for (StaffMember staff : staffList) {
                double pay = staff.calculatePay();
                System.out.printf(Locale.US, "%s: %.2f%n", staff.getName(), pay);
                totalPayroll += pay;
            }
            System.out.printf(Locale.US, "Total Payroll: %.2f%n", totalPayroll);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<StaffMember> staffList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            staffList.add(StaffFactory.createStaff(type, name, scanner));
        }
        PayrollSystem.processPayroll(staffList);
    }
}

/**
 * Alias class for direct execution without L2_ prefix.
 */
class WeeklyStaffPay extends L2_WeeklyStaffPay {
    public static void main(String[] args) {
        L2_WeeklyStaffPay.main(args);
    }
}