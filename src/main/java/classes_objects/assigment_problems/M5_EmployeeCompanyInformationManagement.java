package classes_objects.assigment_problems;

/**
 * Problem M5: Employee and Company Information Management
 *
 * OOP Concepts Tested:
 * - Instance fields (empName, salary) unique to each object
 * - Static fields (companyName, employeeCount) shared by the entire class
 * - Incrementing static counter once per constructor call
 * - Static method definition accessing only static members
 * - Class-level invocation (Employee.printCompanyInfo()) without using object instances
 *
 * Description:
 * Instead of duplicating the company name in every employee instance,
 * companyName and employeeCount are stored as static members.
 * printCompanyInfo() prints these shared details and does not touch instance fields.
 */
public class M5_EmployeeCompanyInformationManagement {

    static class Employee {
        // Instance fields: unique per employee
        String empName;
        double salary;

        // Static fields: shared across all Employee objects
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        /**
         * Constructor initializing instance fields and incrementing
         * the static employeeCount exactly once.
         *
         * @param empName Employee name
         * @param salary  Monthly salary
         */
        public Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        /**
         * Static method printing companyName and employeeCount.
         * Does NOT reference empName or salary.
         */
        public static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("--- Problem M5: Employee and Company Information Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Create three Employee objects
        Employee emp1 = new Employee("Aditi", 55000);
        Employee emp2 = new Employee("Rajesh", 62000);
        Employee emp3 = new Employee("Simran", 48000);

        // Call printCompanyInfo() through the CLASS NAME, not through an object
        Employee.printCompanyInfo();

        System.out.println();
        System.out.println("--- Additional Static Verification ---");
        // Creating a fourth employee
        Employee emp4 = new Employee("Vikram", 70000);
        System.out.println("After creating 4th employee (Vikram):");
        Employee.printCompanyInfo();

        // Verify individual instance fields are accessible
        System.out.println();
        System.out.println("Verified employee instances: " + emp1.empName + ", " + emp2.empName + ", " + emp3.empName + ", " + emp4.empName);

        // Verifying class-level access to companyName
        System.out.println("Employee.companyName: " + Employee.companyName);
    }
}
