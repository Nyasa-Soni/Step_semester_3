package fundamentals.assigment_problems;

/**
 * Problem 1: ATM PIN Length Validator
 * 
 * Scenario:
 * An ATM app must check that a PIN a customer enters is exactly 4 digits long
 * before allowing them to continue, using only the most basic checks.
 * 
 * Task:
 * - Accept a PIN string.
 * - Get its length using length().
 * - If the length is not exactly 4, print "Invalid PIN — must be exactly 4 digits."
 * - Otherwise, print "PIN length OK."
 * - This one needs no loop at all - just length() and a single if / else.
 */
public class L1_ATMPinLengthValidator {

    /**
     * Checks if the PIN length is exactly 4 digits.
     *
     * @param pin the input PIN string
     */
    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN \u2014 must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = "482";
        System.out.println("Input: \"" + sample1 + "\"");
        checkPinLength(sample1);

        String sample2 = "4820";
        System.out.println("Input: \"" + sample2 + "\"");
        checkPinLength(sample2);

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "12345";
        System.out.println("Input: \"" + sample3 + "\"");
        checkPinLength(sample3);

        String sample4 = "";
        System.out.println("Input: \"" + sample4 + "\"");
        checkPinLength(sample4);
    }
}
