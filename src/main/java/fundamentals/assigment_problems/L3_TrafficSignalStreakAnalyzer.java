package fundamentals.assigment_problems;

/**
 * Assignment Problem 3: The Traffic Signal Streak Analyzer
 *
 * Traffic control diagnostic tool that scans signal color logs and identifies
 * the longest continuous streak of identical readings.
 */
public class L3_TrafficSignalStreakAnalyzer {

    /**
     * Finds and prints the color and length of the longest continuous streak
     * in the given sequence of signal readings.
     *
     * @param signalLog string representing signal colors (e.g. 'R', 'G', 'Y')
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("No signal data available");
            return;
        }

        char maxColor = signalLog.charAt(0);
        int maxStreak = 1;

        char currentColor = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char ch = signalLog.charAt(i);

            if (ch == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    maxColor = currentColor;
                }
                currentColor = ch;
                currentStreak = 1;
            }
        }

        // Final check for trailing streak ending at the last character
        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            maxColor = currentColor;
        }

        System.out.printf("Longest Streak: '%c' repeated %d times%n", maxColor, maxStreak);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ASSIGNMENT 3: TRAFFIC SIGNAL STREAK ANALYZER   ");
        System.out.println("==================================================\n");

        // PDF Sample Case 1: "RRGGGYRR"
        System.out.println("Test Case 1: \"RRGGGYRR\"");
        findLongestStreak("RRGGGYRR");
        System.out.println();

        // PDF Sample Case 2: "RRRRYYGG"
        System.out.println("Test Case 2: \"RRRRYYGG\"");
        findLongestStreak("RRRRYYGG");
        System.out.println();

        // Additional Cases
        System.out.println("Test Case 3: \"GGYYYRRR\"");
        findLongestStreak("GGYYYRRR");
        System.out.println();

        System.out.println("Test Case 4: \"R\"");
        findLongestStreak("R");
    }
}
