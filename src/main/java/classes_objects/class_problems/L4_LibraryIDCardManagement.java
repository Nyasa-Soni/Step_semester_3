package classes_objects.class_problems;

/**
 * Problem M4: Library ID Card Management
 *
 * OOP Concepts Tested:
 * - Object reference variables
 * - Reference assignment & aliasing (two variables referencing the same object)
 * - State modification through aliases
 * - Reference equality comparison (==) vs distinct object instances on the heap
 *
 * Description:
 * Demonstrates the fundamental difference between reference assignment
 * (IdCard duplicate = ravi;) and creating a new independent object
 * (IdCard separate = new IdCard("Ravi", 3);).
 */
class IdCard {
    String name;
    int booksIssued;

    /**
     * Constructor setting both name and booksIssued.
     *
     * @param name        Student name on ID card
     * @param booksIssued Number of books issued
     */
    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class L4_LibraryIDCardManagement {

    public static void main(String[] args) {
        System.out.println("--- Problem M4: Library ID Card Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Step 1: Create one IdCard object for Ravi
        IdCard ravi = new IdCard("Ravi", 0);

        // Step 2: Assign second variable to point to the same object (aliasing)
        IdCard duplicate = ravi;

        // Step 3: Change booksIssued through the duplicate reference
        duplicate.booksIssued = 3;

        // Step 4: Create a third, separate IdCard object with identical values
        IdCard separate = new IdCard("Ravi", 3);

        // Step 5: Print value through first variable and check reference equality
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        System.out.println("separate == ravi: " + (separate == ravi));

        System.out.println();
        System.out.println("--- Additional Reference Behavior Demonstrations ---");
        // Modifying through ravi also affects duplicate
        ravi.booksIssued = 5;
        System.out.println("After ravi.booksIssued = 5 -> duplicate.booksIssued is: " + duplicate.booksIssued);
        System.out.println("separate.booksIssued remains unchanged: " + separate.booksIssued);
    }
}
