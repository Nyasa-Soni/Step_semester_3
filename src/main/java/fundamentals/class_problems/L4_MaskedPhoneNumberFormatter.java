package fundamentals.class_problems;

/**
 * Problem 4: Masked Phone Number Formatter
 * 
 * Scenario:
 * A student-support call center displays a partially masked version of a registered
 * phone number on-screen for privacy, while agents confirm identity using the last 4 digits.
 * 
 * Task:
 * - Accept a phone number as a string.
 * - Validate that it is exactly 10 digits (all numeric).
 * - Build a masked version showing "XXXXXX" followed by the last 4 digits, using StringBuilder.
 * - Insert a "-" between the mask and the last 4 digits for readability.
 * - Print the final masked number, or an error message if validation fails.
 */
public class L4_MaskedPhoneNumberFormatter {

    /**
     * Validates and masks a 10-digit phone number.
     *
     * @param phone input phone number string
     * @return masked phone number "XXXXXX-XXXX" or "Invalid phone number"
     */
    public static String maskPhoneNumber(String phone) {
        // Validate null or incorrect length
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validate that all characters are numeric digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Build masked version using StringBuilder and substring
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append(phone.substring(6)); // extract last 4 digits

        // Insert '-' between the mask and the last 4 digits
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = "9876543210";
        System.out.println("Input: \"" + sample1 + "\" -> " + maskPhoneNumber(sample1));

        String sample2 = "98765";
        System.out.println("Input: \"" + sample2 + "\" -> " + maskPhoneNumber(sample2));

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "98765abcde";
        System.out.println("Input: \"" + sample3 + "\" -> " + maskPhoneNumber(sample3));

        String sample4 = "987654321012";
        System.out.println("Input: \"" + sample4 + "\" -> " + maskPhoneNumber(sample4));

        String sample5 = "987-654321";
        System.out.println("Input: \"" + sample5 + "\" -> " + maskPhoneNumber(sample5));

        String sample6 = "";
        System.out.println("Input: \"" + sample6 + "\" -> " + maskPhoneNumber(sample6));
    }
}
