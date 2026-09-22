package arrays.class_problems;

/**
 * Problem 4: Hackathon Seating Grid Optimizer
 * Category: Intermediate
 *
 * Description:
 * Classifies seating grid rows as "Quiet Zone" or "Buzzing Zone" based on whether
 * each row's average score meets or exceeds a given threshold.
 * Uses a private helper method to compute the average for each row, handling jagged arrays.
 */
public class L4_HackathonSeatingGridOptimizer {

    /**
     * Private helper to compute the average score for a single seating row.
     * Handles jagged arrays and empty rows safely.
     *
     * @param row The row of scores.
     * @return The average of the row, or 0.0 if empty or null.
     */
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) {
            return 0.0;
        }

        int sum = 0;
        for (int score : row) {
            sum += score;
        }

        return (double) sum / row.length;
    }

    /**
     * Classifies each row in the seating grid by calling rowAverage once per row.
     *
     * @param seatingScores 2D array representing seating grid scores (can be jagged).
     * @param threshold     Score threshold separating Quiet and Buzzing zones.
     * @return Formatted classification string for all rows separated by " | ".
     */
    static String classifyRows(int[][] seatingScores, int threshold) {
        if (seatingScores == null || seatingScores.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seatingScores.length; i++) {
            // Call rowAverage exactly once per row
            double avg = rowAverage(seatingScores[i]);
            String zone = (avg >= threshold) ? "Buzzing Zone" : "Quiet Zone";

            if (i > 0) {
                result.append(" | ");
            }
            result.append("Row ").append(i).append(": ").append(zone);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Sample Input
        int[][] seatingScores = {
            {40, 50, 45},
            {85, 90, 95},
            {30, 20, 25}
        };
        int threshold = 60;

        String classification = classifyRows(seatingScores, threshold);

        // Expected: "Row 0: Quiet Zone | Row 1: Buzzing Zone | Row 2: Quiet Zone"
        System.out.println("Output:");
        System.out.println(classification);
    }
}
