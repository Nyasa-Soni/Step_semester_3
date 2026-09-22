package arrays.assigment_problems;

import java.util.Arrays;

/**
 * Problem 5: Fantasy League Auto-Draft Ranking Engine
 * Category: Advanced
 *
 * Topics Integrated:
 * Arrays, Method Overloading, Static Methods, Standard Library (Arrays.sort),
 * Constructors & Encapsulation, Comparable interface.
 *
 * Threshold & Scoring Formulation:
 * As stated in the specification, the exact thresholds and fantasy-points formula are
 * intentionally left to the student while matching the PDF's sample input/output:
 *
 * 1. Overloaded Draft Eligibility Filters:
 *    - isDraftable(int matchesPlayed):
 *      Experience-only qualification cutoff: matchesPlayed >= 10.
 *      Directly qualifies established players Virat (15 matches) and Dev (12 matches, injured).
 *    - isDraftable(int matchesPlayed, boolean injured):
 *      Combined fitness-and-matches check: matchesPlayed >= 5 && !injured (or matchesPlayed >= 10).
 *      Qualifies Rahul (7 matches >= 5 and healthy !injured).
 *      Excludes Sameer (3 matches < 5, failing both rules despite having the highest batting average 60.0).
 *
 * 2. Fantasy Points & Ranking Metric:
 *    - The PDF specifies ranking draftable players by fantasy points descending, noting that Sameer
 *      is excluded "despite having the best batting average of anyone."
 *    - Among the draftable players (Rahul: 55.0, Virat: 48.0, Dev: 20.0), ranking by battingAverage
 *      descending directly produces the exact expected draft order:
 *      "1. Rahul | 2. Virat | 3. Dev".
 *    - Therefore, getFantasyPoints() returns the battingAverage, which is used in compareTo()
 *      for natural descending ranking via Arrays.sort().
 */
public class L5_FantasyLeagueAutoDraftRankingEngine {

    /**
     * Encapsulated Player model implementing Comparable<Player> for natural descending ranking.
     */
    public static class Player implements Comparable<Player> {
        private final String name;
        private final int matchesPlayed;
        private final double battingAverage;
        private final boolean injured;

        /**
         * Constructs an encapsulated Player instance.
         *
         * @param name           Player name.
         * @param matchesPlayed  Total matches played (non-negative integer).
         * @param battingAverage Player's career/season batting average.
         * @param injured        Current injury status (true if injured).
         */
        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        public String getName() {
            return name;
        }

        public int getMatchesPlayed() {
            return matchesPlayed;
        }

        public double getBattingAverage() {
            return battingAverage;
        }

        public boolean isInjured() {
            return injured;
        }

        /**
         * Returns the fantasy points metric used for auto-draft ranking.
         * Uses battingAverage as the core performance rating.
         */
        public double getFantasyPoints() {
            return battingAverage;
        }

        /**
         * Compares players in descending order of fantasy points for Arrays.sort().
         * Includes deterministic tie-breakers on matches played (descending) and name (ascending).
         */
        @Override
        public int compareTo(Player other) {
            if (other == null) {
                return -1;
            }
            int scoreComparison = Double.compare(other.getFantasyPoints(), this.getFantasyPoints());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            int matchComparison = Integer.compare(other.matchesPlayed, this.matchesPlayed);
            if (matchComparison != 0) {
                return matchComparison;
            }
            return this.name.compareTo(other.name);
        }

        // Forwarding methods inside Player class for convenience and test runner compatibility
        public static boolean isDraftable(int matchesPlayed) {
            return L5_FantasyLeagueAutoDraftRankingEngine.isDraftable(matchesPlayed);
        }

        public static boolean isDraftable(int matchesPlayed, boolean injured) {
            return L5_FantasyLeagueAutoDraftRankingEngine.isDraftable(matchesPlayed, injured);
        }
    }

    /**
     * Experience-only draft eligibility rule for established players.
     * Established players with matches >= 10 qualify regardless of fitness.
     *
     * @param matchesPlayed Non-negative count of matches played.
     * @return true if player meets the experience cutoff (>= 10).
     */
    public static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    /**
     * Combined matches-and-fitness draft eligibility rule.
     * Newer players qualify if they have played at least 5 matches and are not injured,
     * or if they meet the primary experience cutoff (>= 10).
     *
     * @param matchesPlayed Non-negative count of matches played.
     * @param injured       true if currently injured, false if fit.
     * @return true if player qualifies under either criterion.
     */
    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        return isDraftable(matchesPlayed) || (matchesPlayed >= 5 && !injured);
    }

    /**
     * Filters draftable players using overloaded isDraftable rules, ranks them
     * using standard Arrays.sort(), and returns a formatted ranking string.
     *
     * @param players Array of player candidates.
     * @return Formatted ranking string: "1. Name | 2. Name | ...".
     */
    public static String draftAndRank(Player[] players) {
        if (players == null || players.length == 0) {
            return "";
        }

        // Step 1: Count draftable players using overloaded eligibility rules
        int draftableCount = 0;
        for (Player p : players) {
            if (p != null && (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured()))) {
                draftableCount++;
            }
        }

        if (draftableCount == 0) {
            return "";
        }

        // Step 2: Populate the draftable array
        Player[] draftable = new Player[draftableCount];
        int index = 0;
        for (Player p : players) {
            if (p != null && (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured()))) {
                draftable[index++] = p;
            }
        }

        // Step 3: Sort using standard Arrays.sort (relies on Player's compareTo)
        Arrays.sort(draftable);

        // Step 4: Build formatted ranking output: "1. Name | 2. Name | ..."
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            if (i > 0) {
                result.append(" | ");
            }
            result.append(i + 1).append(". ").append(draftable[i].getName());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 5: Fantasy League Auto-Draft Ranking Engine ---");

        // Sample PDF Input
        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        String result = draftAndRank(players);

        // Expected Output: "1. Rahul | 2. Virat | 3. Dev"
        System.out.println("Ranked Draft Output: " + result);

        // Edge Case 1: Borderline experience (matches = 10, injured) -> qualifies
        Player borderlineExperienced = new Player("Rishabh", 10, 35.0, true);
        System.out.println("\nBorderline Experienced (10 matches, injured) isDraftable: "
                + isDraftable(borderlineExperienced.getMatchesPlayed(), borderlineExperienced.isInjured())); // true

        // Edge Case 2: Borderline combined (matches = 5, healthy) -> qualifies
        Player borderlineNew = new Player("Shubman", 5, 40.0, false);
        System.out.println("Borderline Combined (5 matches, fit) isDraftable: "
                + isDraftable(borderlineNew.getMatchesPlayed(), borderlineNew.isInjured())); // true

        // Edge Case 3: Borderline combined but injured (matches = 5, injured) -> fails
        Player borderlineInjured = new Player("Hardik", 5, 40.0, true);
        System.out.println("Borderline Combined (5 matches, injured) isDraftable: "
                + isDraftable(borderlineInjured.getMatchesPlayed(), borderlineInjured.isInjured())); // false

        // Edge Case 4: Equal fantasy points (tie-breaking test)
        Player[] tiedPlayers = {
            new Player("PlayerA", 12, 50.0, false),
            new Player("PlayerB", 15, 50.0, false)
        };
        System.out.println("\nTied Scores Draft Result (PlayerB has more matches): " + draftAndRank(tiedPlayers));
        // Expected: "1. PlayerB | 2. PlayerA"
    }
}
