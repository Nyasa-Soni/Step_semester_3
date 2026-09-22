package encapsulation.class_problems;

/**
 * Problem 2: The Quiz Scorecard
 * 
 * Demonstrates hiding internal data structures:
 * - Stores boolean answers privately in an array.
 * - Array reference is NEVER leaked or exposed to the outside world.
 * - Only the computed integer score is revealed.
 * - Enforces fixed question count capacity with recorded answer counter.
 */
public class L2_QuizScorecard {

    /**
     * Scorecard model class.
     */
    static class Scorecard {
        // Private array storing individual question outcomes; never exposed outside
        private final boolean[] results;

        // Tracks how many answers have been recorded so far
        private int count;

        /**
         * Constructs a scorecard with a fixed number of questions.
         *
         * @param totalQuestions total number of questions for this quiz
         */
        public Scorecard(int totalQuestions) {
            int size = Math.max(0, totalQuestions);
            this.results = new boolean[size];
            this.count = 0;
        }

        /**
         * Records the next answer result (true for correct, false for incorrect).
         * If the scorecard is already full, the answer is rejected/ignored.
         *
         * @param correct true if the answer was right, false if wrong
         * @return true if successfully recorded, false if ignored/rejected
         */
        public boolean recordAnswer(boolean correct) {
            if (this.count < this.results.length) {
                this.results[this.count] = correct;
                this.count++;
                return true;
            }
            return false;
        }

        /**
         * Computes and returns the total score (count of correct answers).
         * Does not expose the internal boolean array.
         *
         * @return total number of correct answers recorded
         */
        public int getScore() {
            int score = 0;
            for (int i = 0; i < this.count; i++) {
                if (this.results[i]) {
                    score++;
                }
            }
            return score;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: The Quiz Scorecard ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);

        System.out.println("sc.getScore() -> " + sc.getScore());

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Exceeding capacity (recording a 5th answer)
        boolean extraAnswer = sc.recordAnswer(true);
        System.out.println("Attempting 5th answer on 4-question scorecard: recorded=" + extraAnswer + ", score stays=" + sc.getScore());

        // Edge Case 2: All correct answers
        Scorecard perfect = new Scorecard(3);
        perfect.recordAnswer(true);
        perfect.recordAnswer(true);
        perfect.recordAnswer(true);
        System.out.println("All correct (3/3): score = " + perfect.getScore());

        // Edge Case 3: All incorrect answers
        Scorecard zero = new Scorecard(3);
        zero.recordAnswer(false);
        zero.recordAnswer(false);
        zero.recordAnswer(false);
        System.out.println("All incorrect (0/3): score = " + zero.getScore());

        // Edge Case 4: Zero-question scorecard
        Scorecard emptyCard = new Scorecard(0);
        boolean recordOnEmpty = emptyCard.recordAnswer(true);
        System.out.println("Zero-question scorecard: recordAnswer=" + recordOnEmpty + ", score=" + emptyCard.getScore());
    }
}
