package arrays.class_problems;

import java.util.Arrays;

/**
 * Problem 1: Hackathon Score Curve Booster
 * Category: Easy
 *
 * Description:
 * Boosts every score in-place by adding a flat bonus to the caller's array directly.
 * No new array is created and no value is returned.
 */
public class L1_HackathonScoreCurveBooster {

    /**
     * Curves hackathon scores in-place by adding the given bonus to each element.
     *
     * @param scores The original array of scores to be modified directly.
     * @param bonus  The non-negative bonus score to add.
     */
    static void curveScores(int[] scores, int bonus) {
        if (scores == null) {
            return;
        }
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    public static void main(String[] args) {
        // Sample Input
        int[] scores = {70, 85, 60};
        int bonus = 10;

        System.out.println("Original scores: " + Arrays.toString(scores));
        curveScores(scores, bonus);

        // Printing using Arrays.toString(...) as required by the specification
        System.out.println("Curved scores: " + Arrays.toString(scores));
    }
}
