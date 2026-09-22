package fundamentals.class_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 4: First Non-Repeating Character
 *
 * Unique Letter Hunt Mini-Game: finds and highlights the first character
 * that appears only once in the entire input text.
 */
public class L4_FirstNonRepeatingCharacter {

    /**
     * Finds the first non-repeating character in the given text.
     * Scans characters from left to right after computing their frequencies.
     *
     * @param text input string
     * @return the first non-repeating character, or '\0' if none exists
     */
    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        // Frequency counting using standard Java HashMap
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Left-to-right scan to find the first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (frequencyMap.get(ch) == 1) {
                return ch;
            }
        }

        return '\0';
    }

    /**
     * Helper method to run the check and print the result matching the PDF format.
     *
     * @param text input string
     */
    public static void displayFirstNonRepeatingChar(String text) {
        System.out.printf("Input: \"%s\"%n", text);
        char result = findFirstNonRepeatingChar(text);

        if (result != '\0') {
            System.out.printf("First Non-Repeating Character: '%c'%n%n", result);
        } else {
            System.out.println("No Non-Repeating Character Found\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DAY 1: FIRST NON-REPEATING CHARACTER        ");
        System.out.println("==================================================\n");

        // Primary PDF test cases
        displayFirstNonRepeatingChar("swiss");
        displayFirstNonRepeatingChar("aabbcc");

        // Additional test cases for comprehensive demonstration
        displayFirstNonRepeatingChar("java");
        displayFirstNonRepeatingChar("success");
        displayFirstNonRepeatingChar("teeter");
    }
}
