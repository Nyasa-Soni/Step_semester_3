package encapsulation.class_problems;

/**
 * Problem 5: The Attendance Sheet
 * 
 * Demonstrates internal collection encapsulation with deduplication:
 * - Stores present student names in a private fixed-size array.
 * - The internal array is NEVER returned or exposed in any form.
 * - External interaction is strictly restricted to markPresent(), getPresentCount(), and isPresent().
 * - Duplicate submissions are rejected internally and do not inflate the attendance count.
 */
public class L5_AttendanceSheet {

    /**
     * AttendanceSheet model class.
     */
    static class AttendanceSheet {
        // Private array holding names of present students; never exposed
        private final String[] names;

        // Tracks current count of distinct present students
        private int count;

        /**
         * Constructs an attendance sheet with a fixed maximum class size.
         *
         * @param capacity maximum number of attendees
         */
        public AttendanceSheet(int capacity) {
            int size = Math.max(0, capacity);
            this.names = new String[size];
            this.count = 0;
        }

        /**
         * Marks a student as present if they are not already recorded and capacity allows.
         * Duplicate names are prevented internally and do not increment the count.
         *
         * @param name the student name to mark present
         * @return true if added as newly present, false if duplicate or sheet full
         */
        public boolean markPresent(String name) {
            if (name == null || isPresent(name)) {
                return false;
            }
            if (this.count < this.names.length) {
                this.names[this.count] = name;
                this.count++;
                return true;
            }
            return false;
        }

        /**
         * Returns the count of distinct students marked present.
         *
         * @return number of present students
         */
        public int getPresentCount() {
            return this.count;
        }

        /**
         * Checks whether a specific student has been marked present.
         * Loops through the internal private array without exposing it.
         *
         * @param name student name to look up
         * @return true if the student is present, false otherwise
         */
        public boolean isPresent(String name) {
            if (name == null) {
                return false;
            }
            for (int i = 0; i < this.count; i++) {
                if (name.equals(this.names[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: The Attendance Sheet ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana"); // Duplicate

        System.out.println("sheet.getPresentCount() -> " + sheet.getPresentCount());
        System.out.println("sheet.isPresent(\"Ben\") -> " + sheet.isPresent("Ben"));
        System.out.println("sheet.isPresent(\"Chen\") -> " + sheet.isPresent("Chen"));

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Repeated duplicates
        boolean dup1 = sheet.markPresent("Ben");
        boolean dup2 = sheet.markPresent("Ana");
        System.out.println("Adding existing 'Ben' again: added=" + dup1 + ", 'Ana' again: added=" + dup2);
        System.out.println("Count after duplicate attempts remains: " + sheet.getPresentCount());

        // Edge Case 2: Full capacity sheet
        AttendanceSheet smallSheet = new AttendanceSheet(2);
        smallSheet.markPresent("Alice");
        smallSheet.markPresent("Bob");
        boolean overflow = smallSheet.markPresent("Charlie");
        System.out.println("Capacity=2, mark 3rd student Charlie: added=" + overflow + ", count=" + smallSheet.getPresentCount());
        System.out.println("smallSheet.isPresent(\"Charlie\") -> " + smallSheet.isPresent("Charlie"));

        // Edge Case 3: Absent name check and null safety
        System.out.println("sheet.isPresent(\"UnknownStudent\") -> " + sheet.isPresent("UnknownStudent"));
        System.out.println("sheet.isPresent(null) -> " + sheet.isPresent(null));
        System.out.println("sheet.markPresent(null) -> " + sheet.markPresent(null));
    }
}
