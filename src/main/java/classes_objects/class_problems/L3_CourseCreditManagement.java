package classes_objects.class_problems;

/**
 * Problem M3: Course Credit Management
 *
 * OOP Concepts Tested:
 * - Constructor overloading
 * - Constructor chaining using this(...)
 * - Code reuse and DRY (Don't Repeat Yourself) principle
 * - Instance methods
 *
 * Description:
 * Some courses come with a separate lab component and lab credit count; most don't.
 * We support both without writing the same setup logic twice by chaining the
 * theory-only constructor to the comprehensive constructor using this(...).
 */
class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    /**
     * Four-argument constructor that sets all four fields directly.
     *
     * @param code       Course code
     * @param title      Course title
     * @param credits    Theory credit count
     * @param labCredits Lab credit count
     */
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    /**
     * Second constructor for theory-only courses.
     * Uses this(...) to chain to the 4-argument constructor with labCredits set to 0.
     *
     * @param code    Course code
     * @param title   Course title
     * @param credits Theory credit count
     */
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    /**
     * Calculates the total credits of the course.
     *
     * @return Sum of theory credits and lab credits
     */
    public int totalCredits() {
        return this.credits + this.labCredits;
    }
}

public class L3_CourseCreditManagement {

    public static void main(String[] args) {
        System.out.println("--- Problem M3: Course Credit Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Theory-only course (using 3-arg constructor chaining via this(...))
        Course theoryCourse = new Course("21CSC201J", "Data Structures", 4);

        // Course with lab component (using 4-arg constructor)
        Course labCourse = new Course("21CSC205L", "DSA Lab", 3, 1);

        // Print total credits for both
        System.out.println(theoryCourse.code + " total credits: " + theoryCourse.totalCredits());
        System.out.println(labCourse.code + " total credits: " + labCourse.totalCredits());

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Edge Case: Lab-only course (0 theory credits, 2 lab credits)
        Course labOnly = new Course("21CSC206P", "Web Dev Practicum", 0, 2);
        System.out.println(labOnly.code + " (" + labOnly.title + ") total credits: " + labOnly.totalCredits());

        // Verify that 3-arg constructor correctly initialized labCredits to 0
        System.out.println("Verification: theoryCourse.labCredits == " + theoryCourse.labCredits + " (expected 0)");
    }
}
