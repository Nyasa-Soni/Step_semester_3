package arrays.assigment_problems;

/**
 * Problem 2: Duplicate Player Pick Checker
 * Category: Easy
 *
 * Topics Integrated:
 * Arrays, Strings, Nested Loops
 *
 * Description:
 * Checks a submitted lineup for a repeated player name using plain nested loops.
 * Strictly avoids the Collections framework. Compares each name only against subsequent
 * names in the lineup, reporting the first duplicate found in scanning order.
 * String comparison is case-sensitive.
 */
public class L2_DuplicatePlayerPickChecker {

    /**
     * Finds the first duplicate player pick using nested-loop pairwise comparison.
     *
     * @param playerNames Array of player names submitted in the lineup.
     * @return Formatted message: "Duplicate Found: <Name>" or "No Duplicates Found".
     */
    public static String findDuplicatePick(String[] playerNames) {
        if (playerNames == null || playerNames.length < 2) {
            return "No Duplicates Found";
        }

        // Nested loops: compare each name only with names after it in the lineup
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i] != null && playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }

        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 2: Duplicate Player Pick Checker ---");

        // Sample Test Case 1 (Duplicate present)
        String[] lineup1 = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println("Lineup 1: {\"Kohli\", \"Bumrah\", \"Kohli\", \"Rohit\"}");
        System.out.println("Result  : " + findDuplicatePick(lineup1)); // Expected: Duplicate Found: Kohli

        // Sample Test Case 2 (No duplicates)
        String[] lineup2 = {"Kohli", "Bumrah", "Rohit"};
        System.out.println("\nLineup 2: {\"Kohli\", \"Bumrah\", \"Rohit\"}");
        System.out.println("Result  : " + findDuplicatePick(lineup2)); // Expected: No Duplicates Found

        // Edge Case 1: First possible pair duplicate
        String[] lineup3 = {"Dhoni", "Dhoni", "Jadeja"};
        System.out.println("\nLineup 3: {\"Dhoni\", \"Dhoni\", \"Jadeja\"}");
        System.out.println("Result  : " + findDuplicatePick(lineup3)); // Expected: Duplicate Found: Dhoni

        // Edge Case 2: Duplicate later in array
        String[] lineup4 = {"Rohit", "Gill", "Bumrah", "Bumrah"};
        System.out.println("\nLineup 4: {\"Rohit\", \"Gill\", \"Bumrah\", \"Bumrah\"}");
        System.out.println("Result  : " + findDuplicatePick(lineup4)); // Expected: Duplicate Found: Bumrah

        // Edge Case 3: Case-sensitive check
        String[] lineup5 = {"Kohli", "kohli"};
        System.out.println("\nLineup 5: {\"Kohli\", \"kohli\"}");
        System.out.println("Result  : " + findDuplicatePick(lineup5)); // Expected: No Duplicates Found
    }
}
