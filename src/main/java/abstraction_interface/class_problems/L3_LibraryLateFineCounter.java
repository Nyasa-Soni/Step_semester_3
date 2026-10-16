package abstraction_interface.class_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Problem 3: Library Late Fine Counter
 *
 * Demonstrates Abstraction:
 * - Common abstract base class LibraryItem defines common item attributes (title, daysLate)
 *   and mandates the calculateFine() method.
 * - Subclasses BookItem, DvdItem, and MagazineItem implement type-specific fine logic.
 * - BookItem charges 2 per day late.
 * - DvdItem charges 5 per day late, capped at a maximum fine of 50.
 * - MagazineItem charges 1 per day late.
 * - LibraryFineSystem processes fines polymorphically across all library item references.
 */
public class L3_LibraryLateFineCounter {

    /**
     * Abstract base class representing a borrowed library item.
     */
    public static abstract class LibraryItem {
        protected final String title;
        protected final int daysLate;

        public LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        public String getTitle() {
            return title;
        }

        public int getDaysLate() {
            return daysLate;
        }

        public abstract String getType();

        public abstract double calculateFine();
    }

    /**
     * Book item: 2 per day late.
     */
    public static class BookItem extends LibraryItem {
        public static final double RATE_PER_DAY = 2.00;

        public BookItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        public String getType() {
            return "BOOK";
        }

        @Override
        public double calculateFine() {
            return daysLate * RATE_PER_DAY;
        }
    }

    /**
     * DVD item: 5 per day late, capped at a maximum of 50.
     */
    public static class DvdItem extends LibraryItem {
        public static final double RATE_PER_DAY = 5.00;
        public static final double MAXIMUM_FINE = 50.00;

        public DvdItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        public String getType() {
            return "DVD";
        }

        @Override
        public double calculateFine() {
            return Math.min(MAXIMUM_FINE, daysLate * RATE_PER_DAY);
        }
    }

    /**
     * Magazine item: 1 per day late.
     */
    public static class MagazineItem extends LibraryItem {
        public static final double RATE_PER_DAY = 1.00;

        public MagazineItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        public String getType() {
            return "MAGAZINE";
        }

        @Override
        public double calculateFine() {
            return daysLate * RATE_PER_DAY;
        }
    }

    /**
     * Factory class for creating library items without if-else branching.
     */
    public static class LibraryItemFactory {
        private static final Map<String, BiFunction<String, Integer, LibraryItem>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BOOK", BookItem::new);
            REGISTRY.put("DVD", DvdItem::new);
            REGISTRY.put("MAGAZINE", MagazineItem::new);
        }

        public static LibraryItem createItem(String type, String title, int daysLate) {
            BiFunction<String, Integer, LibraryItem> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown library item type: " + type);
            }
            return creator.apply(title, daysLate);
        }
    }

    /**
     * Central service for processing late fines polymorphically.
     */
    public static class LibraryFineSystem {
        public static void processFines(List<LibraryItem> items) {
            double totalFines = 0.0;
            for (LibraryItem item : items) {
                double fine = item.calculateFine();
                System.out.printf(Locale.US, "%s: %.2f%n", item.getTitle(), fine);
                totalFines += fine;
            }
            System.out.printf(Locale.US, "Total Fines: %.2f%n", totalFines);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();
            items.add(LibraryItemFactory.createItem(type, title, daysLate));
        }
        LibraryFineSystem.processFines(items);
    }
}

/**
 * Alias class for direct execution without L3_ prefix.
 */
class LibraryLateFineCounter extends L3_LibraryLateFineCounter {
    public static void main(String[] args) {
        L3_LibraryLateFineCounter.main(args);
    }
}