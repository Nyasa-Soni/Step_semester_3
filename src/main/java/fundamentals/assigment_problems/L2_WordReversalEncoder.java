package fundamentals.assigment_problems;

/**
 * Problem 2: Word Reversal Encoder
 * 
 * Scenario:
 * The coding club's "mirror text" mini-game reverses every word in a sentence
 * individually while keeping the word order the same, so "hello club" becomes "olleh bulc".
 * 
 * Task:
 * - Accept a sentence (words separated by single spaces).
 * - Split it into words using split(" ").
 * - For each word, build its reverse using a loop and StringBuilder.
 * - Join the reversed words back together with spaces and return/print the result.
 */
public class L2_WordReversalEncoder {

    /**
     * Reverses each individual word in the given sentence while keeping word order intact.
     *
     * @param sentence input sentence with words separated by single spaces
     * @return encoded sentence with each word reversed
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        // Split into words using single space delimiter
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            // Build the reverse of each word using a loop and StringBuilder
            StringBuilder reversedWord = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }

            result.append(reversedWord);

            // Append space between words
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // PDF Sample demonstration
        System.out.println("--- PDF Sample ---");
        String sample1 = "hello club";
        System.out.println("Input: \"" + sample1 + "\"");
        System.out.println("Output: " + reverseEachWord(sample1));

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample2 = "Java Programming Language";
        System.out.println("Input: \"" + sample2 + "\"");
        System.out.println("Output: " + reverseEachWord(sample2));

        String sample3 = "racecar level";
        System.out.println("Input: \"" + sample3 + "\"");
        System.out.println("Output: " + reverseEachWord(sample3));

        String sample4 = "A";
        System.out.println("Input: \"" + sample4 + "\"");
        System.out.println("Output: " + reverseEachWord(sample4));
    }
}
