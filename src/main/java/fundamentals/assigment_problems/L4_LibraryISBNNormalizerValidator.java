package fundamentals.assigment_problems;

/**
 * Problem 4: Library ISBN Normalizer & Validator
 * 
 * Scenario:
 * A library system's book-intake scanner needs to both normalize and validate ISBN-style codes.
 * A valid code is exactly 13 characters: 3 letters (publisher code) + 4 digits (year) +
 * 6 digits (catalog number). Scanned codes sometimes have stray spaces or a mixed-case publisher code.
 * 
 * Task:
 * - Accept a raw code string that may contain leading/trailing spaces.
 * - Normalize it: trim() the spaces, then uppercase only the first 3 characters using
 *   substring() + concatenation — leave the rest untouched.
 * - Validate: exactly 13 characters after normalization; the first 3 characters are letters;
 *   the remaining 10 are digits (use Character.isLetter() / isDigit() in a loop — no regex).
 * - If valid, build a formatted display line with StringBuilder:
 *   "[PUBCODE] YEAR: 20XX | CATALOG: 123456".
 * - If invalid, print the specific reason: wrong length, non-letter publisher code, or non-digit body.
 */
public class L4_LibraryISBNNormalizerValidator {

    /**
     * Normalizes raw code by trimming whitespace and uppercasing the first 3 letters.
     *
     * @param raw raw input string
     * @return normalized code string
     */
    public static String normalizeCode(String raw) {
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
     * Validates and formats the normalized code into "[PUBCODE] YEAR: <year> | CATALOG: <catalog>".
     *
     * @param code normalized 13-character code
     * @return formatted line or specific error message
     */
    public static String validateAndFormat(String code) {
        // Validate exact length of 13 characters
        if (code == null || code.length() != 13) {
            return "Invalid: wrong length (must be exactly 13 characters)";
        }

        // Validate first 3 characters are letters (publisher code)
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits (4 digits year + 6 digits catalog)
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body (remaining 10 characters must be digits)";
            }
        }

        // Extract components
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        // Build formatted line using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ").append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = " pen2026004251 ";
        String norm1 = normalizeCode(sample1);
        System.out.println("Input: \"" + sample1 + "\"");
        System.out.println("Normalized: \"" + norm1 + "\"");
        System.out.println("Output: " + validateAndFormat(norm1));

        String sample2 = "12N2026004251";
        String norm2 = normalizeCode(sample2);
        System.out.println("\nInput: \"" + sample2 + "\"");
        System.out.println("Output: " + validateAndFormat(norm2));

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "oxf2024123456";
        String norm3 = normalizeCode(sample3);
        System.out.println("Input: \"" + sample3 + "\" -> " + validateAndFormat(norm3));

        String sample4 = "PEN202600425"; // 12 chars
        String norm4 = normalizeCode(sample4);
        System.out.println("Input: \"" + sample4 + "\" -> " + validateAndFormat(norm4));

        String sample5 = "PEN202600425A"; // non-digit in body
        String norm5 = normalizeCode(sample5);
        System.out.println("Input: \"" + sample5 + "\" -> " + validateAndFormat(norm5));

        String sample6 = "";
        String norm6 = normalizeCode(sample6);
        System.out.println("Input: \"\" -> " + validateAndFormat(norm6));
    }
}
