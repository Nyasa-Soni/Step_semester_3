package oop.assigment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Assignment Problem 4: The Festival Bonus Calculator
 *
 * Demonstrates OOP polymorphism:
 * - Base abstract class Employee defines common attributes (name, monthlySalary) and abstract calculateBonus().
 * - Concrete subclasses FullTimeEmployee, PartTimeEmployee, and InternEmployee encapsulate their respective bonus logic.
 * - EmployeeFactory maps employee types to their constructors without if-else type checking.
 * - The PayrollSystem processes all employee bonuses uniformly.
 */
public class L4_FestivalBonusCalculator {

    /**
     * Abstract base class representing an employee.
     */
    public static abstract class Employee {
        protected final String name;
        protected final double monthlySalary;

        public Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        public String getName() {
            return name;
        }

        public double getMonthlySalary() {
            return monthlySalary;
        }

        public abstract double calculateBonus();
    }

    /**
     * Full-time employee: 10% of monthly salary.
     */
    public static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return monthlySalary * 0.10;
        }
    }

    /**
     * Part-time employee: 5% of monthly salary.
     */
    public static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return monthlySalary * 0.05;
        }
    }

    /**
     * Intern: fixed bonus of Rs 2000 regardless of salary.
     */
    public static class InternEmployee extends Employee {
        public InternEmployee(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        public double calculateBonus() {
            return 2000.00;
        }
    }

    /**
     * Functional interface for creating Employee instances.
     */
    @FunctionalInterface
    public interface EmployeeCreator {
        Employee create(String name, double monthlySalary);
    }

    /**
     * Factory class mapping employee types to creators.
     */
    public static class EmployeeFactory {
        private static final Map<String, EmployeeCreator> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("FULLTIME", FullTimeEmployee::new);
            REGISTRY.put("PARTTIME", PartTimeEmployee::new);
            REGISTRY.put("INTERN", InternEmployee::new);
        }

        public static Employee createEmployee(String type, String name, double monthlySalary) {
            EmployeeCreator creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown employee type: " + type);
            }
            return creator.create(name, monthlySalary);
        }
    }

    /**
     * Payroll service handling bonus calculations polymorphically.
     */
    public static class PayrollSystem {
        public static void processEmployees(List<Employee> employees) {
            double grandTotal = 0.0;
            for (Employee employee : employees) {
                double bonus = employee.calculateBonus();
                System.out.printf(Locale.US, "%s: %.2f%n", employee.getName(), bonus);
                grandTotal += bonus;
            }
            System.out.printf(Locale.US, "Total Bonus: %.2f%n", grandTotal);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();
            employees.add(EmployeeFactory.createEmployee(type, name, salary));
        }
        PayrollSystem.processEmployees(employees);
    }
}

/**
 * Alias class for direct execution without L4_ prefix.
 */
class FestivalBonusCalculator extends L4_FestivalBonusCalculator {
    public static void main(String[] args) {
        L4_FestivalBonusCalculator.main(args);
    }
}