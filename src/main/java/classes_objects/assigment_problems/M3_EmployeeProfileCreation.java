package classes_objects.assigment_problems;

/**
 * Problem M3: Employee Profile Creation
 *
 * OOP Concepts Tested:
 * - Constructor overloading
 * - Constructor chaining using this(...)
 * - DRY principle (eliminating duplicate initialization logic)
 * - Instance methods
 *
 * Description:
 * Permanent employees join with an agreed salary (isIntern = false).
 * Interns join without a fixed salary (salary = 0, isIntern = true).
 * The intern constructor chains to the 3-argument constructor via this(...).
 */
public class M3_EmployeeProfileCreation {

    static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        /**
         * Constructor for permanent employees.
         *
         * @param empId   Employee identifier
         * @param empName Employee name
         * @param salary  Monthly salary
         */
        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        /**
         * Second constructor for interns.
         * Chains to the 3-argument constructor with salary set to 0, then sets isIntern = true.
         *
         * @param empId   Employee identifier
         * @param empName Employee name
         */
        public Employee(String empId, String empName) {
            this(empId, empName, 0);
            this.isIntern = true;
        }

        /**
         * Prints the employee profile in the standard format:
         * <empId> | <empName> | Rs <salary> | Intern: <isIntern>
         */
        public void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem M3: Employee Profile Creation ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Permanent Employee: E-101, Divya, 65000
        Employee permanentEmp = new Employee("E-101", "Divya", 65000);

        // Intern: E-102, Arjun
        Employee internEmp = new Employee("E-102", "Arjun");

        // Print profiles
        permanentEmp.printProfile();
        internEmp.printProfile();

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Verify intern salary is strictly 0.0 and isIntern is true
        System.out.println("Verification: internEmp.salary == " + internEmp.salary + " (expected 0.0)");
        System.out.println("Verification: internEmp.isIntern == " + internEmp.isIntern + " (expected true)");
        System.out.println("Verification: permanentEmp.isIntern == " + permanentEmp.isIntern + " (expected false)");
    }
}
