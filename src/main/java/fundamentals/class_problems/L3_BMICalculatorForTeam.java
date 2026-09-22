package fundamentals.class_problems;

import java.util.Locale;

/**
 * Problem 3: BMI Calculator for a Team
 *
 * Corporate Wellness Program calculator that stores heights and weights of
 * employees, calculates BMI, classifies health status, and produces a wellness report.
 */
public class L3_BMICalculatorForTeam {

    /**
     * Determines health status classification based on BMI.
     *
     * Classification thresholds:
     * BMI < 18.5       -> Underweight
     * 18.5 - 24.9      -> Normal
     * 25.0 - 29.9      -> Overweight
     * >= 30.0          -> Obese
     *
     * @param bmi the computed body mass index
     * @return health status string
     */
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Prints a formatted wellness report table for a team of people.
     *
     * @param heights array of heights in meters
     * @param weights array of weights in kilograms
     */
    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights == null || weights == null || heights.length != weights.length) {
            throw new IllegalArgumentException("Heights and weights arrays must be non-null and equal length.");
        }

        System.out.printf(Locale.US, "%-8s | %-10s | %-11s | %-7s | %-12s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double h = heights[i];
            double w = weights[i];
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);

            System.out.printf(Locale.US, "Person %-2d| %-10.2f | %-11.2f | %-7.2f | %-12s%n",
                    (i + 1), h, w, bmi, status);
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DAY 1: BMI CALCULATOR FOR A TEAM            ");
        System.out.println("==================================================\n");

        // 1. PDF Sample Cases Demonstration
        System.out.println("--- PDF Sample Cases ---");
        double[] sampleHeights = { 1.75, 1.60 };
        double[] sampleWeights = { 70.0, 90.0 };

        for (int i = 0; i < sampleHeights.length; i++) {
            double h = sampleHeights[i];
            double w = sampleWeights[i];
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);
            System.out.printf(Locale.US, "Person %d - Height: %.2f m, Weight: %.0f kg -> BMI: %.2f | Status: %s%n",
                    (i + 1), h, w, bmi, status);
        }
        System.out.println();

        // 2. Full Wellness Report for a Team of 10 People
        System.out.println("--- Full Corporate Wellness Report (Team of 10) ---");
        double[] teamHeights = {
            1.75,  // Person 1 (Normal)
            1.60,  // Person 2 (Obese)
            1.82,  // Person 3 (Normal)
            1.68,  // Person 4 (Overweight)
            1.72,  // Person 5 (Underweight)
            1.80,  // Person 6 (Normal)
            1.65,  // Person 7 (Obese)
            1.70,  // Person 8 (Overweight)
            1.58,  // Person 9 (Normal)
            1.85   // Person 10 (Underweight)
        };

        double[] teamWeights = {
            70.0,  // Person 1 -> BMI: 22.86 (Normal)
            90.0,  // Person 2 -> BMI: 35.16 (Obese)
            74.0,  // Person 3 -> BMI: 22.34 (Normal)
            75.0,  // Person 4 -> BMI: 26.57 (Overweight)
            52.0,  // Person 5 -> BMI: 17.58 (Underweight)
            68.0,  // Person 6 -> BMI: 20.99 (Normal)
            85.0,  // Person 7 -> BMI: 31.22 (Obese)
            80.0,  // Person 8 -> BMI: 27.68 (Overweight)
            55.0,  // Person 9 -> BMI: 22.03 (Normal)
            60.0   // Person 10 -> BMI: 17.53 (Underweight)
        };

        printWellnessReport(teamHeights, teamWeights);
    }
}
