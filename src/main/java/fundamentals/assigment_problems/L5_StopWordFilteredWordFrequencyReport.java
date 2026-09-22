package fundamentals.assigment_problems;

import java.util.*;

/**
 * Problem 5: Stop-Word-Filtered Word Frequency Report
 * 
 * Scenario:
 * The T&P team wants word-frequency analysis of feedback paragraphs, but common
 * filler words ("the", "was", "and", "a", "is", "of", "in") should be excluded so
 * the report highlights meaningful themes instead of function words.
 * 
 * Task:
 * - Accept a paragraph of feedback text, and treat a small fixed list of stop words as filler:
 *   {"the", "was", "and", "a", "is", "of", "in"}.
 * - Normalize it: convert to lowercase, and strip punctuation such as periods and commas using replace().
 * - Split the cleaned text into words using split("\\s+").
 * - Skip any word found in the stop-word list.
 * - Count the frequency of every remaining unique word (a HashMap is fine).
 * - Print each unique word with its count, sorted by count in descending order. Ties may appear in any order.
 */
public class L5_StopWordFilteredWordFrequencyReport {

    // Fixed stop words list
    private static final Set<String> STOP_WORDS = new HashSet<>(
            Arrays.asList("the", "was", "and", "a", "is", "of", "in")
    );

    /**
     * Normalizes input feedback, filters stop words, and prints descending word frequency report.
     *
     * @param feedback feedback paragraph to analyze
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        // Convert to lowercase and strip punctuation using replace()
        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "");

        // Split into words by whitespace
        String[] words = cleaned.trim().split("\\s+");

        // Count frequency using LinkedHashMap to preserve first-appearance order for ties
        Map<String, Integer> freqMap = new LinkedHashMap<>();
        for (String word : words) {
            word = word.trim();
            if (word.isEmpty() || STOP_WORDS.contains(word)) {
                continue;
            }
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        // Sort entries in descending order of frequency
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Print each word with its count
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        // PDF Sample demonstration
        System.out.println("--- PDF Sample ---");
        String sample1 = "The mentor was great, the session was great and clear.";
        System.out.println("Input: \"" + sample1 + "\"");
        System.out.println("Output:");
        printFilteredWordFrequency(sample1);

        // Edge case demonstrations
        System.out.println("\n--- Edge Case: Only Stop Words ---");
        String sample2 = "The was and a is of in";
        System.out.println("Input: \"" + sample2 + "\"");
        printFilteredWordFrequency(sample2);

        System.out.println("\n--- Edge Case: Mixed Case and Multiple Occurrences ---");
        String sample3 = "Java java JAVA code practice makes code perfect.";
        System.out.println("Input: \"" + sample3 + "\"");
        System.out.println("Output:");
        printFilteredWordFrequency(sample3);
    }
}
