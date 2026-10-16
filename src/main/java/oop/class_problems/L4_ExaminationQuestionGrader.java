package oop.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class Problem 4: Examination Question Grader
 *
 * Demonstrates OOP Polymorphism:
 * - Common abstract base class Question defines question attributes (text, correctAnswer, studentAnswer, points)
 *   and abstract evaluateScore().
 * - Specialized subclasses McqQuestion, TrueFalseQuestion, and EssayQuestion encapsulate
 *   their respective grading algorithms.
 * - QuestionFactory uses a registry map with functional parsers to eliminate if-else type checks.
 * - ExamGrader processes questions polymorphically without type inspection.
 */
public class L4_ExaminationQuestionGrader {

    /**
     * Abstract base class representing an examination question.
     */
    public static abstract class Question {
        protected final String questionText;
        protected final String correctAnswer;
        protected final String studentAnswer;
        protected final double points;

        public Question(String questionText, String correctAnswer, String studentAnswer, double points) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getStudentAnswer() {
            return studentAnswer;
        }

        public double getPoints() {
            return points;
        }

        public abstract String getQuestionType();

        public abstract double evaluateScore();
    }

    /**
     * Multiple Choice Question (MCQ):
     * Full points if student answer exactly matches correct answer.
     */
    public static class McqQuestion extends Question {
        public McqQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super(questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public String getQuestionType() {
            return "MCQ";
        }

        @Override
        public double evaluateScore() {
            return studentAnswer.trim().equals(correctAnswer.trim()) ? points : 0.00;
        }
    }

    /**
     * True/False Question (TF):
     * Full points if student answer matches correct answer.
     */
    public static class TrueFalseQuestion extends Question {
        public TrueFalseQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super(questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public String getQuestionType() {
            return "TF";
        }

        @Override
        public double evaluateScore() {
            return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.00;
        }
    }

    /**
     * Essay Question (ESSAY):
     * Graded by case-insensitive keyword occurrences in student answer.
     * - >= 2 keywords: 75% of Points
     * - 1 keyword: 50% of Points
     * - 0 keywords: 0 points
     */
    public static class EssayQuestion extends Question {
        public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super(questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public String getQuestionType() {
            return "ESSAY";
        }

        @Override
        public double evaluateScore() {
            String[] keywords = correctAnswer.split(",");
            int matchCount = 0;

            for (String kw : keywords) {
                String cleanKw = kw.trim();
                if (cleanKw.isEmpty()) {
                    continue;
                }
                // Case-insensitive whole keyword match with word boundaries
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(cleanKw) + "\\b", Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(studentAnswer).find()) {
                    matchCount++;
                }
            }

            if (matchCount >= 2) {
                return points * 0.75;
            } else if (matchCount == 1) {
                return points * 0.50;
            } else {
                return 0.00;
            }
        }
    }

    /**
     * Functional interface for parsing a Question from extracted tokens.
     */
    @FunctionalInterface
    public interface QuestionParser {
        Question parse(List<String> tokens);
    }

    /**
     * Factory class mapping question types to functional parsers to eliminate if-else blocks.
     */
    public static class QuestionFactory {
        private static final Map<String, QuestionParser> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("MCQ", tokens -> new McqQuestion(
                    tokens.get(1),
                    tokens.get(2),
                    tokens.get(3),
                    Double.parseDouble(tokens.get(4))
            ));
            REGISTRY.put("TF", tokens -> new TrueFalseQuestion(
                    tokens.get(1),
                    tokens.get(2),
                    tokens.get(3),
                    Double.parseDouble(tokens.get(4))
            ));
            REGISTRY.put("ESSAY", tokens -> new EssayQuestion(
                    tokens.get(1),
                    tokens.get(2),
                    tokens.get(3),
                    Double.parseDouble(tokens.get(4))
            ));
        }

        public static Question createQuestion(List<String> tokens) {
            if (tokens == null || tokens.size() < 5) {
                throw new IllegalArgumentException("Invalid question input format");
            }
            QuestionParser parser = REGISTRY.get(tokens.get(0).toUpperCase(Locale.ROOT));
            if (parser == null) {
                throw new IllegalArgumentException("Unknown question type: " + tokens.get(0));
            }
            return parser.parse(tokens);
        }
    }

    /**
     * Tokenizer helper that splits a line while preserving text inside double quotes.
     */
    public static List<String> parseTokens(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (Character.isWhitespace(c) && !insideQuotes) {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            tokens.add(sb.toString());
        }
        return tokens;
    }

    /**
     * Exam grader service processing all questions polymorphically.
     */
    public static class ExamGrader {
        public static void gradeExam(List<Question> questions) {
            double totalScore = 0.0;
            for (Question question : questions) {
                double score = question.evaluateScore();
                System.out.printf(Locale.US, "%s: %.2f%n", question.getQuestionType(), score);
                totalScore += score;
            }
            System.out.printf(Locale.US, "Total Score: %.2f%n", totalScore);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Question> questions = new ArrayList<>();

        while (questions.size() < n && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            List<String> tokens = parseTokens(line);
            questions.add(QuestionFactory.createQuestion(tokens));
        }

        ExamGrader.gradeExam(questions);
    }
}

/**
 * Alias class for direct execution without L4_ prefix.
 */
class ExaminationQuestionGrader extends L4_ExaminationQuestionGrader {
    public static void main(String[] args) {
        L4_ExaminationQuestionGrader.main(args);
    }
}