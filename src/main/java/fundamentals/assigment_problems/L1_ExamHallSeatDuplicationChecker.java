package fundamentals.assigment_problems;

/**
 * Assignment Problem 1: The Exam Hall Seat Duplication Checker
 *
 * Examination Cell seating verification tool that checks for duplicate seat numbers
 * using only arrays and nested loops (without any Collections classes).
 */
public class L1_ExamHallSeatDuplicationChecker {

    /**
     * Scans an array of seat numbers for duplicate assignments and reports the findings.
     * Uses only arrays and loops, ensuring any duplicate value is reported cleanly.
     *
     * @param seatNumbers array of integer seat numbers assigned to students
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length <= 1) {
            System.out.println("No Duplicate Seats Found");
            return;
        }

        boolean hasDuplicates = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            // Check if this seat number was already checked and printed in an earlier position
            boolean alreadyProcessed = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyProcessed = true;
                    break;
                }
            }

            if (alreadyProcessed) {
                continue;
            }

            // Compare seatNumbers[i] against subsequent elements
            boolean isDuplicate = false;
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                hasDuplicates = true;
                System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
            }
        }

        if (!hasDuplicates) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ASSIGNMENT 1: EXAM HALL SEAT DUPLICATION CHECKER");
        System.out.println("==================================================\n");

        // PDF Sample Case 1 (Duplicates present)
        int[] hallA = { 101, 102, 103, 102, 105 };
        System.out.println("Test Case 1: {101, 102, 103, 102, 105}");
        checkDuplicateSeats(hallA);
        System.out.println();

        // PDF Sample Case 2 (No duplicates)
        int[] hallB = { 101, 102, 103, 104, 105 };
        System.out.println("Test Case 2: {101, 102, 103, 104, 105}");
        checkDuplicateSeats(hallB);
        System.out.println();

        // Additional Case: Multiple distinct duplicates and multiple occurrences
        int[] hallC = { 201, 202, 203, 201, 204, 202, 201 };
        System.out.println("Test Case 3: {201, 202, 203, 201, 204, 202, 201}");
        checkDuplicateSeats(hallC);
    }
}
