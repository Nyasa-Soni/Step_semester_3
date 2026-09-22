package arrays.class_problems;

import java.util.Arrays;
import java.util.Locale;

/**
 * Problem 5: Placement Drive Shortlisting & Ranking Engine
 * Category: Advanced
 *
 * Topics Integrated:
 * Arrays, Method Overloading, Static Methods, Standard Library (Arrays.sort),
 * Constructors & Encapsulation, Comparable interface.
 *
 * Threshold & Scoring Formulation:
 * As stated in the specification, the exact thresholds and composite formula are
 * intentionally left to the student. The chosen values match the provided sample
 * input/output:
 *
 * 1. Composite Score Formula:
 *    compositeScore = (cgpa * 10.0) + (codingScore * 0.5)
 *    - Aisha: (8.2 * 10) + (40 * 0.5) = 82.0 + 20.0 = 102.0
 *    - Rohit: (6.8 * 10) + (65 * 0.5) = 68.0 + 32.5 = 100.5
 *    - Karan: (7.5 * 10) + (20 * 0.5) = 75.0 + 10.0 = 85.0
 *
 * 2. Overloaded Eligibility Filters:
 *    - isEligible(double cgpa):
 *      CGPA-only bar: cgpa >= 7.0.
 *      Directly qualifies Aisha (8.2) and Karan (7.5).
 *    - isEligible(double cgpa, int codingScore):
 *      Combined filter for borderline cases: cgpa >= 6.5 && codingScore >= 60.
 *      Qualifies Rohit (cgpa 6.8 is borderline >= 6.5, codingScore 65 >= 60).
 *      Disqualifies Meena (cgpa 6.0 is below borderline 6.5, regardless of high coding score 90).
 */
public class L5_PlacementDriveShortlistingRankingEngine {

    /**
     * Encapsulated Candidate model implementing Comparable for natural descending ranking.
     */
    public static class Candidate implements Comparable<Candidate> {
        private final String name;
        private final double cgpa;
        private final int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }

        public int getCodingScore() {
            return codingScore;
        }

        /**
         * Calculates composite score: (cgpa * 10.0) + (codingScore * 0.5)
         */
        public double getCompositeScore() {
            return (cgpa * 10.0) + (codingScore * 0.5);
        }

        /**
         * Compares candidates in descending order based on composite score.
         */
        @Override
        public int compareTo(Candidate other) {
            return Double.compare(other.getCompositeScore(), this.getCompositeScore());
        }

        // Forwarding methods in Candidate class for convenience and test compatibility
        public static boolean isEligible(double cgpa) {
            return L5_PlacementDriveShortlistingRankingEngine.isEligible(cgpa);
        }

        public static boolean isEligible(double cgpa, int codingScore) {
            return L5_PlacementDriveShortlistingRankingEngine.isEligible(cgpa, codingScore);
        }
    }

    /**
     * CGPA-only filter: Direct qualification for strong academic records (CGPA >= 7.0).
     *
     * @param cgpa Candidate's CGPA (0.0 to 10.0).
     * @return true if CGPA meets or exceeds 7.0.
     */
    public static boolean isEligible(double cgpa) {
        return cgpa >= 7.0;
    }

    /**
     * Combined filter: Allows borderline CGPA candidates (>= 6.5) to qualify with a
     * strong coding score (>= 60), or anyone meeting the primary CGPA cutoff.
     *
     * @param cgpa        Candidate's CGPA (0.0 to 10.0).
     * @param codingScore Candidate's coding round score (0 to 100).
     * @return true if candidate qualifies under either criterion.
     */
    public static boolean isEligible(double cgpa, int codingScore) {
        return isEligible(cgpa) || (cgpa >= 6.5 && codingScore >= 60);
    }

    /**
     * Filters eligible candidates, sorts them using Arrays.sort, and returns
     * a formatted ranking string.
     *
     * @param candidates Array of applicants.
     * @return Formatted ranking string: "1. Name (Score) | 2. Name (Score) ... "
     */
    public static String shortlistAndRank(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) {
            return "";
        }

        // Step 1: Count eligible candidates using the overloaded filters
        int eligibleCount = 0;
        for (Candidate c : candidates) {
            if (c != null && (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore()))) {
                eligibleCount++;
            }
        }

        if (eligibleCount == 0) {
            return "";
        }

        // Step 2: Populate the shortlisted array
        Candidate[] shortlisted = new Candidate[eligibleCount];
        int index = 0;
        for (Candidate c : candidates) {
            if (c != null && (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore()))) {
                shortlisted[index++] = c;
            }
        }

        // Step 3: Sort shortlisted candidates using standard Arrays.sort
        // Relies on Candidate's compareTo() for descending composite score order
        Arrays.sort(shortlisted);

        // Step 4: Build the formatted ranking string
        StringBuilder ranking = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            if (i > 0) {
                ranking.append(" | ");
            }
            ranking.append(i + 1)
                   .append(". ")
                   .append(shortlisted[i].getName())
                   .append(" (")
                   .append(String.format(Locale.US, "%.1f", shortlisted[i].getCompositeScore()))
                   .append(")");
        }

        return ranking.toString();
    }

    public static void main(String[] args) {
        // Sample Input
        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        String result = shortlistAndRank(candidates);

        // Expected: "1. Aisha (102.0) | 2. Rohit (100.5) | 3. Karan (85.0)"
        System.out.println("Output:");
        System.out.println(result);
    }
}
