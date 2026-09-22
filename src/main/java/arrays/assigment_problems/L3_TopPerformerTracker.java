package arrays.assigment_problems;

import java.util.Arrays;

/**
 * Problem 3: Top Performer Tracker
 * Category: Intermediate
 *
 * Topics Integrated:
 * Arrays, Loops, Logical Thinking
 *
 * Description:
 * Tracks the minimum and maximum scores in a single pass without sorting the array,
 * and computes the spread (max - min) between the standout performer and the lowest score.
 */
public class L3_TopPerformerTracker {

    /**
     * Finds the minimum, maximum, and spread in a single pass through the array.
     *
     * @param scores Array of scores (length >= 2).
     * @return Formatted string: "Min: <min> | Max: <max> | Spread: <spread>".
     */
    public static String findMinMaxSpread(int[] scores) {
        if (scores == null || scores.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 scores.");
        }

        // Initialize both running variables from the first element
        int min = scores[0];
        int max = scores[0];

        // Single pass: update min and max from the same loop
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
            if (scores[i] > max) {
                max = scores[i];
            }
        }

        int spread = max - min;
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 3: Top Performer Tracker ---");

        // Sample PDF Test Case
        int[] scores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println("Scores : " + Arrays.toString(scores));
        // Expected: Min: 33 | Max: 90 | Spread: 57
        System.out.println("Result : " + findMinMaxSpread(scores));

        // Edge Case 1: Two elements only
        int[] twoElements = {42, 10};
        System.out.println("\nTwo Elements: " + Arrays.toString(twoElements));
        System.out.println("Result      : " + findMinMaxSpread(twoElements)); // Min: 10 | Max: 42 | Spread: 32

        // Edge Case 2: All negative values
        int[] negatives = {-15, -3, -40, -8};
        System.out.println("\nAll Negatives: " + Arrays.toString(negatives));
        System.out.println("Result       : " + findMinMaxSpread(negatives)); // Min: -40 | Max: -3 | Spread: 37

        // Edge Case 3: Duplicate minimums and maximums
        int[] duplicates = {50, 10, 50, 10, 30};
        System.out.println("\nDuplicates: " + Arrays.toString(duplicates));
        System.out.println("Result    : " + findMinMaxSpread(duplicates)); // Min: 10 | Max: 50 | Spread: 40

        // Edge Case 4: Identical elements (spread = 0)
        int[] identical = {25, 25, 25};
        System.out.println("\nIdentical Elements: " + Arrays.toString(identical));
        System.out.println("Result            : " + findMinMaxSpread(identical)); // Min: 25 | Max: 25 | Spread: 0
    }
}
