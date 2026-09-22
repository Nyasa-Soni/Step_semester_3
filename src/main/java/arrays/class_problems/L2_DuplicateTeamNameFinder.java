package arrays.class_problems;

/**
 * Problem 2: Duplicate Team Name Finder
 * Category: Easy
 *
 * Description:
 * Scans a list of registered team names using plain nested loops to find the first
 * duplicate team name. No Collections framework classes are used.
 */
public class L2_DuplicateTeamNameFinder {

    /**
     * Finds the first duplicate team name in scanning order using nested loops.
     *
     * @param teamNames Array of registered team names.
     * @return Formatted message with the first duplicate or "No Duplicates Found".
     */
    static String findDuplicateTeam(String[] teamNames) {
        if (teamNames == null || teamNames.length < 2) {
            return "No Duplicates Found";
        }

        // Compare each team name only with the names that appear after it
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i] != null && teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }

        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        // Sample Test Case 1 (Duplicate present)
        String[] teams1 = {"ByteForce", "CodeCrafters", "ByteForce"};
        System.out.println("Input: {\"ByteForce\", \"CodeCrafters\", \"ByteForce\"}");
        System.out.println("Output: " + findDuplicateTeam(teams1));

        // Sample Test Case 2 (No duplicate)
        String[] teams2 = {"ByteForce", "CodeCrafters", "NullPointers"};
        System.out.println("\nInput: {\"ByteForce\", \"CodeCrafters\", \"NullPointers\"}");
        System.out.println("Output: " + findDuplicateTeam(teams2));
    }
}
