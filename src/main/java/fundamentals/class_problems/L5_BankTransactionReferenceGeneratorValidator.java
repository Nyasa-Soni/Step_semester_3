package fundamentals.class_problems;

/**
 * Problem 5: Bank Transaction Reference Generator & Validator
 * 
 * Scenario:
 * A fintech onboarding module for a placement-prep hackathon needs to both normalize
 * and validate transaction reference codes. A valid reference is exactly 14 characters:
 * 3 letters (bank code) + 6 digits (date, ddMMyy) + 5 digits (sequence number).
 * Users sometimes paste codes with stray spaces or a mixed-case bank code.
 * 
 * Task:
 * - Accept a raw reference string that may contain leading/trailing spaces.
 * - Normalize it: trim() the spaces, then uppercase only the first 3 characters using
 *   substring() + concatenation - leave the rest untouched.
 * - Validate: exactly 14 characters after normalization; the first 3 characters are letters;
 *   the remaining 11 are digits (use Character.isLetter() / isDigit() in a loop - no regex).
 * - If valid, build a formatted display line with StringBuilder:
 *   "[BANKCODE] DATE: dd/MM/yy | SEQ: 12345".
 * - If invalid, return/print the specific reason: wrong length, non-letter bank code, or non-digit body.
 */
public class L5_BankTransactionReferenceGeneratorValidator {

    /**
     * Normalizes a raw transaction reference by trimming spaces and uppercasing
     * only the first 3 characters (bank code).
     *
     * @param raw raw reference string
     * @return normalized reference string
     */
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }

        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }

        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates and formats a normalized transaction reference code.
     *
     * @param reference normalized reference string
     * @return formatted string "[BANKCODE] DATE: dd/MM/yy | SEQ: <seq>" or specific invalid reason
     */
    public static String validateAndFormat(String reference) {
        // Validate exact length of 14 characters
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length (must be exactly 14 characters)";
        }

        // Validate first 3 characters are letters (bank code)
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Validate remaining 11 characters are numeric digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body (remaining 11 characters must be digits)";
            }
        }

        // Extract components using substring
        String bankCode = reference.substring(0, 3);
        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        // Build formatted display line using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(seq);

        return sb.toString();
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = " hdf03022600042 ";
        String norm1 = normalizeReference(sample1);
        System.out.println("Input: \"" + sample1 + "\"");
        System.out.println("Normalized: \"" + norm1 + "\"");
        System.out.println("Output: " + validateAndFormat(norm1));

        String sample2 = "12F03022600042";
        String norm2 = normalizeReference(sample2);
        System.out.println("\nInput: \"" + sample2 + "\"");
        System.out.println("Output: " + validateAndFormat(norm2));

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "sbi15082400199"; // valid lowercase code
        String norm3 = normalizeReference(sample3);
        System.out.println("Input: \"" + sample3 + "\" -> " + validateAndFormat(norm3));

        String sample4 = "HDF0302260042"; // 13 chars - wrong length
        String norm4 = normalizeReference(sample4);
        System.out.println("Input: \"" + sample4 + "\" -> " + validateAndFormat(norm4));

        String sample5 = "HDF0302260004X"; // non-digit in body
        String norm5 = normalizeReference(sample5);
        System.out.println("Input: \"" + sample5 + "\" -> " + validateAndFormat(norm5));

        String sample6 = ""; // empty string
        String norm6 = normalizeReference(sample6);
        System.out.println("Input: \"\" -> " + validateAndFormat(norm6));
    }
}
