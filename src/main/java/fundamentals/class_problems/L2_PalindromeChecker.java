package fundamentals.class_problems;

import java.util.Arrays;

/**
 * Problem 2: Palindrome Checker (3 Approaches)
 *
 * QA Text Verification Toolkit that validates palindromes using three
 * independent approaches: iterative comparison, recursion, and array reversal.
 */
public class L2_PalindromeChecker {

    /**
     * Approach 1: Iterative check comparing characters from both ends moving inward.
     *
     * @param text input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeIterative(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Approach 2: Recursive check comparing first and last characters,
     * shrinking the substring on each recursive step.
     *
     * @param text input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeRecursive(String text) {
        if (text == null) {
            return false;
        }
        // Base cases: empty string or single character is always a palindrome
        if (text.length() <= 1) {
            return true;
        }
        // Check outer characters
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        // Shrink substring by removing first and last characters
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    /**
     * Approach 3: Array-reversal check converting string to character array,
     * reversing it, and comparing with the original.
     *
     * @param text input string
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) {
            return false;
        }
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return Arrays.equals(original, reversed);
    }

    /**
     * Helper method to evaluate and format verification across all three approaches.
     *
     * @param text input string to check
     */
    public static void verifyPalindrome(String text) {
        boolean resIter = isPalindromeIterative(text);
        boolean resRec = isPalindromeRecursive(text);
        boolean resArr = isPalindromeArrayReversal(text);

        String strIter = resIter ? "Palindrome" : "Not Palindrome";
        String strRec = resRec ? "Palindrome" : "Not Palindrome";
        String strArr = resArr ? "Palindrome" : "Not Palindrome";

        System.out.printf("Input: \"%s\"%n", text);
        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s%n%n",
                strIter, strRec, strArr);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DAY 1: PALINDROME CHECKER (3 APPROACHES)    ");
        System.out.println("==================================================\n");

        // Primary PDF test cases
        verifyPalindrome("madam");
        verifyPalindrome("hello");

        // Additional test cases for thorough demonstration
        verifyPalindrome("racecar");
        verifyPalindrome("step");
        verifyPalindrome("a");
    }
}
