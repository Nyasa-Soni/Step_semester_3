package fundamentals.class_problems;

/**
 * Problem 1: Vowel & Consonant Counter
 * 
 * Scenario:
 * A library orientation kiosk counts vowels and consonants in a submitted book
 * title for a simple text-stats display.
 * 
 * Task:
 * - Accept a string (assume only letters and spaces).
 * - Loop through each character using charAt().
 * - Count vowels (a, e, i, o, u - case-insensitive) and consonants separately; ignore spaces.
 * - Print the total vowels and total consonants.
 */
public class L1_VowelAndConsonantCounter {

    /**
     * Counts and prints vowels and consonants in the given text.
     *
     * @param text input string containing letters and spaces
     */
    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            System.out.println("Vowels: 0 | Consonants: 0");
            return;
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Ignore whitespace characters
            if (ch == ' ') {
                continue;
            }

            // Convert to lowercase for case-insensitive comparison
            char lower = Character.toLowerCase(ch);

            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                vowels++;
            } else if (lower >= 'a' && lower <= 'z') {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        // PDF Sample demonstration
        System.out.println("--- PDF Sample ---");
        String sample1 = "Java Programming";
        System.out.println("Input: \"" + sample1 + "\"");
        countVowelsAndConsonants(sample1);

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample2 = "AEIOU";
        System.out.println("Input: \"" + sample2 + "\"");
        countVowelsAndConsonants(sample2);

        String sample3 = "rhythm fly";
        System.out.println("Input: \"" + sample3 + "\"");
        countVowelsAndConsonants(sample3);

        String sample4 = "   ";
        System.out.println("Input: \"" + sample4 + "\"");
        countVowelsAndConsonants(sample4);
    }
}
