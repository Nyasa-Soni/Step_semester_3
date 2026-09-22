package classes_objects.class_problems;

/**
 * Problem M5: Student and College Information Management
 *
 * OOP Concepts Tested:
 * - Instance fields vs static (class-level) fields
 * - Static method definition
 * - Static methods cannot access instance variables
 * - Static counter incremented inside constructor
 * - Class-level access (invoking static method via ClassName.method())
 *
 * Description:
 * Instead of duplicating common college information across every student instance,
 * we store collegeName and studentCount as static members shared by the entire class.
 * studentCount increments once per constructor call.
 */
class Student {
    // Instance fields: unique per student object
    String name;
    double attendance;

    // Static fields: shared across all instances of the Student class
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    /**
     * Constructor initializing student instance fields and incrementing
     * the static student counter once per object creation.
     *
     * @param name       Student's name
     * @param attendance Student's attendance percentage
     */
    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    /**
     * Static method to print shared college details and total student count.
     * Does NOT reference any instance field (name or attendance).
     */
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class L5_StudentCollegeInformationManagement {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("--- Problem M5: Student and College Information Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Create two Student objects
        Student student1 = new Student("Alice", 85.0);
        Student student2 = new Student("Bob", 92.5);

        // Call printCollegeInfo() through the CLASS NAME, not through an object
        Student.printCollegeInfo();

        System.out.println();
        System.out.println("--- Additional Static Behavior Verification ---");
        // Creating a third student object
        Student student3 = new Student("Charlie", 78.0);
        System.out.println("After creating 3rd student (Charlie):");
        Student.printCollegeInfo();

        // Verify individual instance fields are accessible
        System.out.println();
        System.out.println("Verified student instances: " + student1.name + ", " + student2.name + ", " + student3.name);

        // Verifying instance access vs class access for collegeName
        System.out.println("Student.collegeName: " + Student.collegeName);
    }
}
