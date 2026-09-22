package encapsulation.class_problems;

/**
 * Problem 4: The Locker Code
 * 
 * Demonstrates write-only / restricted-mutation encapsulation:
 * - Locker combination is strictly private with NO getter.
 * - Changing the code requires providing the valid existing code first.
 * - If verification fails, the request is rejected and state remains untouched.
 * - Locker number is final and permanently fixed at creation.
 */
public class L4_LockerCode {

    /**
     * Locker model class.
     */
    static class Locker {
        // Locker number is immutable and fixed at creation
        private final int lockerNumber;

        // Private combination code; write-only with guarded verification
        private String code;

        /**
         * Constructs a locker with a fixed number and an initial combination code.
         *
         * @param lockerNumber the unique identifier/number of the locker
         * @param initialCode  the initial combination code
         */
        public Locker(int lockerNumber, String initialCode) {
            this.lockerNumber = lockerNumber;
            this.code = initialCode;
        }

        /**
         * Attempts to change the combination code.
         * Requires the caller to supply the correct current code.
         * If the current code does not match, the change is rejected and the code remains unchanged.
         *
         * @param currentCode the existing combination to authenticate
         * @param newCode     the new combination to set
         * @return true if code was successfully updated, false if rejected
         */
        public boolean changeCode(String currentCode, String newCode) {
            if (this.code != null && this.code.equals(currentCode)) {
                this.code = newCode;
                return true;
            }
            return false;
        }

        /**
         * Read-only getter for the locker number.
         *
         * @return the locker number
         */
        public int getLockerNumber() {
            return this.lockerNumber;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: The Locker Code ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        Locker l = new Locker(101, "1234");
        System.out.println("Created Locker #" + l.getLockerNumber() + " (initial code protected)");

        boolean c1 = l.changeCode("1234", "5678");
        System.out.println("l.changeCode(\"1234\", \"5678\") -> " + (c1 ? "success" : "rejected"));

        boolean c2 = l.changeCode("0000", "9999");
        System.out.println("l.changeCode(\"0000\", \"9999\") -> " + (c2 ? "success" : "rejected, code is still \"5678\""));

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Prove code is still "5678" by attempting change with "5678"
        boolean c3 = l.changeCode("5678", "2468");
        System.out.println("Verification that previous code stayed \"5678\": change with \"5678\" -> " + (c3 ? "success" : "failed"));

        // Edge Case 2: Attempting change with null or empty current code
        boolean cNull = l.changeCode(null, "1111");
        System.out.println("l.changeCode(null, \"1111\") -> rejected=" + (!cNull));

        boolean cWrong = l.changeCode("5678", "3333");
        System.out.println("l.changeCode(\"5678\", \"3333\") [using obsolete old code] -> rejected=" + (!cWrong));

        // Edge Case 3: Final field immutability check
        System.out.println("Locker number remains fixed: " + l.getLockerNumber());
    }
}
