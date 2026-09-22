package arrays.assigment_problems;

/**
 * Problem 4: Match Day Grid Analyzer
 * Category: Intermediate
 *
 * Topics Integrated:
 * 2D Arrays, User-Defined Methods (reused), Loops
 *
 * Description:
 * Analyzes cricket runs scored in every over of every match represented as a 2D grid.
 * Reuses a private helper rowAverage(int[] row) called exactly once per match to classify
 * matches as "Power Surge" (average >= threshold) or "Normal" (average < threshold).
 * Correctly handles jagged arrays where matches have different numbers of overs.
 */
public class L4_MatchDayGridAnalyzer {

    /**
     * Private helper method to compute the average runs scored in a single match (row).
     * Called exactly once per match. Does not classify.
     *
     * @param row Array representing runs scored in each over for a match.
     * @return The average runs per over, or 0.0 if the row is null or empty.
     */
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) {
            return 0.0;
        }

        int sum = 0;
        for (int runs : row) {
            sum += runs;
        }

        return (double) sum / row.length;
    }

    /**
     * Classifies each match in the grid as "Power Surge" or "Normal" based on whether
     * its average runs per over meets or exceeds the given threshold.
     *
     * @param runsPerOver 2D array representing overs per match (supports jagged rows).
     * @param threshold   Scoring threshold separating Normal and Power Surge matches.
     * @return Formatted classification string: "Match 0: Normal | Match 1: Power Surge ...".
     */
    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        if (runsPerOver == null || runsPerOver.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < runsPerOver.length; i++) {
            // Call rowAverage exactly once per match
            double avg = rowAverage(runsPerOver[i]);
            String status = (avg >= threshold) ? "Power Surge" : "Normal";

            if (i > 0) {
                result.append(" | ");
            }
            result.append("Match ").append(i).append(": ").append(status);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 4: Match Day Grid Analyzer ---");

        // Sample PDF Test Case
        int[][] runsPerOver = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        int threshold = 8;

        System.out.println("Threshold: " + threshold);
        String sampleResult = classifyMatches(runsPerOver, threshold);
        // Expected: Match 0: Normal | Match 1: Power Surge | Match 2: Normal
        System.out.println("Result   : " + sampleResult);

        // Edge Case 1: Jagged arrays with differing row lengths
        int[][] jaggedRuns = {
            {6, 10},               // avg = 8.0 (Power Surge)
            {12, 8, 14, 6, 10},     // avg = 10.0 (Power Surge)
            {3}                    // avg = 3.0 (Normal)
        };
        System.out.println("\nJagged Runs Result (Threshold 8):");
        System.out.println("Result: " + classifyMatches(jaggedRuns, 8));
        // Expected: Match 0: Power Surge | Match 1: Power Surge | Match 2: Normal

        // Edge Case 2: Average exactly on threshold boundary
        int[][] boundaryRuns = {
            {8, 8, 8},             // avg = 8.0 (Power Surge)
            {7, 8, 8}              // avg = 7.67 (Normal)
        };
        System.out.println("\nBoundary Runs Result (Threshold 8):");
        System.out.println("Result: " + classifyMatches(boundaryRuns, 8));
        // Expected: Match 0: Power Surge | Match 1: Normal
    }
}
