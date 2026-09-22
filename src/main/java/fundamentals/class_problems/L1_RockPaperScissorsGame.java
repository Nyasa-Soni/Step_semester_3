package fundamentals.class_problems;

import java.util.Random;

/**
 * Problem 1: Rock-Paper-Scissors Game
 *
 * College Coding Arcade simulator that plays rounds between the player and the
 * computer, records outcomes, and prints a final scoreboard.
 */
public class L1_RockPaperScissorsGame {

    public static final String ROCK = "Rock";
    public static final String PAPER = "Paper";
    public static final String SCISSORS = "Scissors";

    private static final String[] VALID_MOVES = { ROCK, PAPER, SCISSORS };
    private static final Random RANDOM = new Random();

    /**
     * Determines the outcome of a single round of Rock-Paper-Scissors.
     *
     * @param playerMove   the move made by the player ("Rock", "Paper", "Scissors")
     * @param computerMove the move made by the computer ("Rock", "Paper", "Scissors")
     * @return "Player Wins", "Computer Wins", or "Draw"
     */
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove == null || computerMove == null) {
            throw new IllegalArgumentException("Moves cannot be null");
        }

        String p = playerMove.trim();
        String c = computerMove.trim();

        if (p.equalsIgnoreCase(c)) {
            return "Draw";
        }

        if ((p.equalsIgnoreCase(ROCK) && c.equalsIgnoreCase(SCISSORS)) ||
            (p.equalsIgnoreCase(PAPER) && c.equalsIgnoreCase(ROCK)) ||
            (p.equalsIgnoreCase(SCISSORS) && c.equalsIgnoreCase(PAPER))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    /**
     * Generates a random move for the computer.
     *
     * @return "Rock", "Paper", or "Scissors"
     */
    public static String getRandomComputerMove() {
        return VALID_MOVES[RANDOM.nextInt(VALID_MOVES.length)];
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DAY 1: ROCK-PAPER-SCISSORS GAME             ");
        System.out.println("==================================================\n");

        // 1. Verify and display the PDF sample round inputs and outputs
        System.out.println("--- PDF Sample Test Cases ---");
        String[][] sampleCases = {
            { ROCK, SCISSORS },
            { PAPER, PAPER },
            { SCISSORS, ROCK }
        };

        for (int i = 0; i < sampleCases.length; i++) {
            String pMove = sampleCases[i][0];
            String cMove = sampleCases[i][1];
            String result = playRound(pMove, cMove);
            System.out.printf("Round %d - Player: %s, Computer: %s -> %s%n",
                    (i + 1), pMove, cMove, result);
        }
        System.out.println();

        // 2. Simulated 5-Round Match (using predefined player moves for live demo)
        System.out.println("--- Live 5-Round Match Simulation ---");
        String[] predefinedPlayerMoves = { ROCK, PAPER, SCISSORS, ROCK, PAPER };
        int totalRounds = predefinedPlayerMoves.length;

        int[] roundNumbers = new int[totalRounds];
        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] results = new String[totalRounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            roundNumbers[i] = i + 1;
            playerMoves[i] = predefinedPlayerMoves[i];
            computerMoves[i] = getRandomComputerMove();
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if ("Player Wins".equals(results[i])) {
                wins++;
            } else if ("Computer Wins".equals(results[i])) {
                losses++;
            } else {
                draws++;
            }
        }

        // Print formatted summary table
        System.out.printf("%-7s | %-13s | %-13s | %-15s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-7d | %-13s | %-13s | %-15s%n",
                    roundNumbers[i], playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("---------------------------------------------------------");

        double winPercentage = (wins * 100.0) / totalRounds;
        System.out.printf("Final Summary (after %d rounds) | Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n%n",
                totalRounds, wins, losses, draws, winPercentage);
    }
}
