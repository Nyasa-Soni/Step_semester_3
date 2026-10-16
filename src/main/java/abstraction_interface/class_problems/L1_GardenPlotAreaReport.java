package abstraction_interface.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 1: Garden Plot Area Report
 *
 * Demonstrates Abstraction and Polymorphism:
 * - Common abstract base class GardenPlot defines shared properties (owner) and abstract calculateArea().
 * - Subclasses CircularPlot, RectangularPlot, and TriangularPlot implement their specific area formulas.
 * - The reporting system iterates through GardenPlot references uniformly, so adding a new plot shape
 *   requires no modifications to the reporting logic.
 */
public class L1_GardenPlotAreaReport {

    /**
     * Abstract base class representing a generic garden plot.
     */
    public static abstract class GardenPlot {
        protected final String owner;

        public GardenPlot(String owner) {
            this.owner = owner;
        }

        public String getOwner() {
            return owner;
        }

        public abstract String getShape();

        public abstract double calculateArea();
    }

    /**
     * Circular plot: area = pi * radius * radius.
     */
    public static class CircularPlot extends GardenPlot {
        private final double radius;

        public CircularPlot(String owner, double radius) {
            super(owner);
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        @Override
        public String getShape() {
            return "CIRCLE";
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }

    /**
     * Rectangular plot: area = length * width.
     */
    public static class RectangularPlot extends GardenPlot {
        private final double length;
        private final double width;

        public RectangularPlot(String owner, double length, double width) {
            super(owner);
            this.length = length;
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public double getWidth() {
            return width;
        }

        @Override
        public String getShape() {
            return "RECTANGLE";
        }

        @Override
        public double calculateArea() {
            return length * width;
        }
    }

    /**
     * Triangular plot: area = 0.5 * base * height.
     */
    public static class TriangularPlot extends GardenPlot {
        private final double base;
        private final double height;

        public TriangularPlot(String owner, double base, double height) {
            super(owner);
            this.base = base;
            this.height = height;
        }

        public double getBase() {
            return base;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public String getShape() {
            return "TRIANGLE";
        }

        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }
    }

    /**
     * Factory class for creating plot instances.
     */
    public static class GardenPlotFactory {
        public static GardenPlot createPlot(String shape, String owner, Scanner scanner) {
            switch (shape.toUpperCase(Locale.ROOT)) {
                case "CIRCLE":
                    double radius = scanner.nextDouble();
                    return new CircularPlot(owner, radius);
                case "RECTANGLE":
                    double length = scanner.nextDouble();
                    double width = scanner.nextDouble();
                    return new RectangularPlot(owner, length, width);
                case "TRIANGLE":
                    double base = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    return new TriangularPlot(owner, base, height);
                default:
                    throw new IllegalArgumentException("Unknown shape: " + shape);
            }
        }
    }

    /**
     * Reporting system processing plots polymorphically.
     */
    public static class GardenPlotReportSystem {
        public static void printReport(List<GardenPlot> plots) {
            double totalArea = 0.0;
            for (GardenPlot plot : plots) {
                double area = plot.calculateArea();
                System.out.printf(Locale.US, "%s (%s): %.2f%n", plot.getOwner(), plot.getShape(), area);
                totalArea += area;
            }
            System.out.printf(Locale.US, "Total Area: %.2f%n", totalArea);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<GardenPlot> plots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String shape = scanner.next();
            String owner = scanner.next();
            plots.add(GardenPlotFactory.createPlot(shape, owner, scanner));
        }
        GardenPlotReportSystem.printReport(plots);
    }
}

/**
 * Alias class for direct execution without L1_ prefix.
 */
class GardenPlotAreaReport extends L1_GardenPlotAreaReport {
    public static void main(String[] args) {
        L1_GardenPlotAreaReport.main(args);
    }
}