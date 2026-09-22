package fundamentals.assigment_problems;

import java.util.Locale;

/**
 * Assignment Problem 2: The Typing Speed Test Accuracy Checker
 *
 * Typing verification tool that compares original and typed strings character
 * by character, computing accuracy and locating the first mismatch.
 */
public class L2_TypingSpeedTestAccuracyChecker {

    /**
     * Compares original and typed passages character by character, calculates
     * accuracy, and reports the first mismatch if one exists.
     *
     * @param original the original benchmark passage
     * @param typed    the text typed by the user
     */
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input: Strings cannot be null.");
            return;
        }

        if (original.length() != typed.length()) {
            System.out.println("Invalid input: Strings must be of equal length for comparison.");
            return;
        }

        int total = original.length();
        if (total == 0) {
            System.out.println("Matched: 0/0 | Accuracy: 100.00% | No Mismatches");
            return;
        }

        int matched = 0;
        int firstMismatchPos = -1;
        char origMismatchChar = '\0';
        char typedMismatchChar = '\0';

        for (int i = 0; i < total; i++) {
            char origChar = original.charAt(i);
            char typedChar = typed.charAt(i);

            if (origChar == typedChar) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based position
                origMismatchChar = origChar;
                typedMismatchChar = typedChar;
            }
        }

        double accuracy = ((double) matched / total) * 100.0;

        if (firstMismatchPos != -1) {
            System.out.printf(Locale.US,
                    "Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matched, total, accuracy, firstMismatchPos, origMismatchChar, typedMismatchChar);
        } else {
            System.out.printf(Locale.US,
                    "Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n",
                    matched, total, accuracy);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ASSIGNMENT 2: TYPING SPEED TEST ACCURACY CHECKER");
        System.out.println("==================================================\n");

        // PDF Sample Case 1: "hello world" vs "hello worlt"
        System.out.println("Test Case 1: original=\"hello world\", typed=\"hello worlt\"");
        checkTypingAccuracy("hello world", "hello worlt");
        System.out.println();

        // PDF Sample Case 2: "coding" vs "coding"
        System.out.println("Test Case 2: original=\"coding\", typed=\"coding\"");
        checkTypingAccuracy("coding", "coding");
        System.out.println();

        // Additional Case: early mismatch
        System.out.println("Test Case 3: original=\"algorithm\", typed=\"algorythm\"");
        checkTypingAccuracy("algorithm", "algorythm");
    }
}
