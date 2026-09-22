package classes_objects.assigment_problems;

/**
 * Problem M4: Exam Hall Ticket Reference Management
 *
 * OOP Concepts Tested:
 * - Object reference variables
 * - Reference assignment & aliasing (two references pointing to the same heap object)
 * - State modification through aliases
 * - Reference identity comparison (==) vs distinct heap instances
 *
 * Description:
 * Demonstrates that assigning copy = priya does not create a new object;
 * changes made through copy affect the object referred to by priya.
 * A separate object with identical values evaluates to false under ==.
 */
public class M4_ExamHallTicketReferenceManagement {

    static class HallTicket {
        String studentName;
        int seatNumber;

        /**
         * Constructor setting both studentName and seatNumber.
         *
         * @param studentName Name of the student
         * @param seatNumber  Assigned seat number
         */
        public HallTicket(String studentName, int seatNumber) {
            this.studentName = studentName;
            this.seatNumber = seatNumber;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem M4: Exam Hall Ticket Reference Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Step 1: Create one HallTicket object for Priya
        HallTicket priya = new HallTicket("Priya", 0);

        // Step 2: Assign second reference to point at the same object (aliasing)
        HallTicket copy = priya;

        // Step 3: Change seatNumber through the second variable
        copy.seatNumber = 45;

        // Step 4: Create a third, separate HallTicket object with identical field values
        HallTicket separate = new HallTicket("Priya", 45);

        // Step 5: Print value via first variable and check reference identity
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));

        System.out.println();
        System.out.println("--- Additional Reference Behavior Demonstrations ---");
        // Modifying through priya also reflects in copy
        priya.seatNumber = 99;
        System.out.println("After priya.seatNumber = 99 -> copy.seatNumber is: " + copy.seatNumber);
        System.out.println("separate.seatNumber remains independent: " + separate.seatNumber);
    }
}
