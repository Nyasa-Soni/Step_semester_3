package arrays.assigment_problems;

import java.util.Arrays;

/**
 * Problem 1: Fantasy Team Score Multiplier
 * Category: Easy
 *
 * Topics Integrated:
 * Creating/Modifying Arrays, Arrays Passed by Reference
 *
 * Description:
 * In a fantasy sports app, every user picks a Captain (2x points) and a Vice-Captain
 * (1.5x points) from their lineup. This method applies both multipliers directly to
 * the lineup's score array in-place, so the app's scoreboard reflects the boosted totals
 * without returning anything or iterating unnecessarily over the array.
 */
public class L1_FantasyTeamScoreMultiplier {

    /**
     * Applies captain (2.0x) and vice-captain (1.5x) multipliers directly to the caller's array.
     * Modifies the array in-place and returns nothing.
     *
     * @param playerScores     Array of player fantasy scores to be modified directly.
     * @param captainIndex     Zero-based index of the captain (receives 2.0x multiplier).
     * @param viceCaptainIndex Zero-based index of the vice-captain (receives 1.5x multiplier).
     */
    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        if (playerScores == null) {
            return;
        }

        // Direct index assignments: no unnecessary loop over the array
        if (captainIndex >= 0 && captainIndex < playerScores.length) {
            playerScores[captainIndex] *= 2.0;
        }
        if (viceCaptainIndex >= 0 && viceCaptainIndex < playerScores.length) {
            playerScores[viceCaptainIndex] *= 1.5;
        }
    }

    public static void main(String[] args) {
        // Sample PDF Test Case
        double[] scores = {40, 55, 30, 62};
        System.out.println("--- Problem 1: Fantasy Team Score Multiplier ---");
        System.out.println("Original Scores: " + Arrays.toString(scores));
        applyMultipliers(scores, 1, 3);
        // Expected: [40.0, 110.0, 30.0, 93.0]
        System.out.println("Boosted Scores : " + Arrays.toString(scores));

        // Additional Edge Case: Decimal scores and different positions
        double[] decimalScores = {12.5, 20.0, 45.5, 80.0};
        System.out.println("\nOriginal Decimal Scores: " + Arrays.toString(decimalScores));
        applyMultipliers(decimalScores, 0, 2);
        // Expected: [25.0, 20.0, 68.25, 80.0]
        System.out.println("Boosted Decimal Scores : " + Arrays.toString(decimalScores));
    }
}
