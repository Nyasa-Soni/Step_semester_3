package abstraction_interface.assignment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Problem 3: College Fee Counter
 *
 * Demonstrates Abstraction & Capability Interfaces:
 * - Abstract base class Student captures student identity (name) and declares
 *   the abstract calculateBaseFee() contract.
 * - Capability interface TransportUser encapsulates the college bus capability
 *   and holds the shared transport fee rule (12000.00) in one place only.
 * - DayScholarStudent and ScholarshipStudent implement TransportUser, indicating
 *   bus transport eligibility through type hierarchy rather than name checking.
 * - HostellerStudent lives on campus and does not implement TransportUser.
 * - StudentFactory provides clean instantiation via a registry map.
 * - CollegeFeeSystem processes students polymorphically without inspecting student type names.
 */
public class L3_CollegeFeeCounter {

    /**
     * Interface defining the transport capability for students using the college bus.
     * The transport fee rule (12000) is defined here in one place only.
     */
    public interface TransportUser {
        double TRANSPORT_FEE = 12000.00;

        default double getTransportFee() {
            return TRANSPORT_FEE;
        }
    }

    /**
     * Abstract base class representing a college student.
     */
    public static abstract class Student {
        protected final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * Calculates base student fees (tuition and accommodation where applicable).
         */
        public abstract double calculateBaseFee();
    }

    /**
     * Day scholar student: tuition 40000 + uses college bus.
     */
    public static class DayScholarStudent extends Student implements TransportUser {
        public static final double TUITION_FEE = 40000.00;

        public DayScholarStudent(String name) {
            super(name);
        }

        @Override
        public double calculateBaseFee() {
            return TUITION_FEE;
        }
    }

    /**
     * Hosteller student: tuition 40000 + hostel fee 60000.
     * Lives on campus, does not use college bus.
     */
    public static class HostellerStudent extends Student {
        public static final double TUITION_FEE = 40000.00;
        public static final double HOSTEL_FEE = 60000.00;

        public HostellerStudent(String name) {
            super(name);
        }

        @Override
        public double calculateBaseFee() {
            return TUITION_FEE + HOSTEL_FEE;
        }
    }

    /**
     * Scholarship student: half tuition (20000) + uses college bus.
     */
    public static class ScholarshipStudent extends Student implements TransportUser {
        public static final double NORMAL_TUITION = 40000.00;

        public ScholarshipStudent(String name) {
            super(name);
        }

        @Override
        public double calculateBaseFee() {
            return NORMAL_TUITION / 2.0;
        }
    }

    /**
     * Factory class registering student constructors to avoid if-else chains.
     */
    public static class StudentFactory {
        private static final Map<String, Function<String, Student>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("DAY_SCHOLAR", DayScholarStudent::new);
            REGISTRY.put("HOSTELLER", HostellerStudent::new);
            REGISTRY.put("SCHOLAR", ScholarshipStudent::new);
        }

        public static Student createStudent(String type, String name) {
            Function<String, Student> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown student type: " + type);
            }
            return creator.apply(name);
        }
    }

    /**
     * Central fee processing service.
     */
    public static class CollegeFeeSystem {
        public static void processStudents(List<Student> students) {
            double totalCollected = 0.0;
            for (Student student : students) {
                double fee = student.calculateBaseFee();
                if (student instanceof TransportUser transportUser) {
                    fee += transportUser.getTransportFee();
                }
                System.out.printf(Locale.US, "%s: %.2f%n", student.getName(), fee);
                totalCollected += fee;
            }
            System.out.printf(Locale.US, "Total Collected: %.2f%n", totalCollected);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            students.add(StudentFactory.createStudent(type, name));
        }
        CollegeFeeSystem.processStudents(students);
    }
}

/**
 * Alias class for direct execution without L3_ prefix.
 */
class CollegeFeeCounter extends L3_CollegeFeeCounter {
    public static void main(String[] args) {
        L3_CollegeFeeCounter.main(args);
    }
}