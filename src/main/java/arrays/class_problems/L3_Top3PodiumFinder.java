package arrays.class_problems;

import java.util.Arrays;

/**
 * Problem 3: Top-3 Podium Finder
 * Category: Intermediate
 *
 * Description:
 * Finds the top 3 scores in a single pass without sorting the array.
 * Duplicate scores count towards podium spots (ties are preserved).
 */
public class L3_Top3PodiumFinder {

    /**
     * Finds the top 3 podium scores in a single pass.
     *
     * @param scores The array of scores (length >= 3).
     * @return An array of the top three scores in descending order [first, second, third].
     */
    static int[] findTopThreeScores(int[] scores) {
        if (scores == null || scores.length < 3) {
            throw new IllegalArgumentException("Array must contain at least 3 scores.");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        // Single pass to update the top 3 scores
        for (int score : scores) {
            if (score >= first) {
                // New score beats or ties first place: ripple down
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                // New score beats or ties second place: ripple down to third
                third = second;
                second = score;
            } else if (score >= third) {
                // New score beats or ties third place
                third = score;
            }
        }

        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        // Sample Input
        int[] scores = {45, 82, 79, 90, 33, 90, 61};

        System.out.println("Scores: " + Arrays.toString(scores));
        int[] podium = findTopThreeScores(scores);

        // Expected: [90, 90, 82]
        System.out.println("Top 3 Podium Scores: " + Arrays.toString(podium));
    }
}
