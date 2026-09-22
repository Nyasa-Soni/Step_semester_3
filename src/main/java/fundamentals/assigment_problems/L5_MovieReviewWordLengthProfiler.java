package fundamentals.assigment_problems;

/**
 * Assignment Problem 5: The Movie Review Word Length Profiler
 *
 * Content moderation profiling tool that categorizes review words by letter length
 * into Short (1-4 letters), Medium (5-8 letters), and Long (9+ letters).
 */
public class L5_MovieReviewWordLengthProfiler {

    /**
     * Splits the given movie review into words, calculates the letter length of each
     * word (excluding punctuation), and prints the category counts.
     *
     * Classification categories:
     * - Short:  1 - 4 letters
     * - Medium: 5 - 8 letters
     * - Long:   9+ letters
     *
     * @param review string containing the movie review text
     */
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] rawTokens = review.trim().split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String token : rawTokens) {
            // Count only alphabetic letter characters to ensure punctuation doesn't inflate length
            int letterCount = 0;
            for (int i = 0; i < token.length(); i++) {
                if (Character.isLetter(token.charAt(i))) {
                    letterCount++;
                }
            }

            if (letterCount >= 1 && letterCount <= 4) {
                shortCount++;
            } else if (letterCount >= 5 && letterCount <= 8) {
                mediumCount++;
            } else if (letterCount >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n",
                shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ASSIGNMENT 5: MOVIE REVIEW WORD LENGTH PROFILER");
        System.out.println("==================================================\n");

        // PDF Sample Case
        String sampleReview = "This movie was absolutely fantastic and thrilling";
        System.out.printf("Review: \"%s\"%n", sampleReview);
        classifyWordLengths(sampleReview);
        System.out.println();

        // Additional Case with punctuation and varied word lengths
        String punctuatedReview = "An outstanding, masterfully directed drama with great performances!";
        System.out.printf("Review: \"%s\"%n", punctuatedReview);
        classifyWordLengths(punctuatedReview);
    }
}
