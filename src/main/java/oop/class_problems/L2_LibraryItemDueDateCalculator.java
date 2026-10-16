package oop.class_problems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Class Problem 2: Library Item Due Date Calculator
 *
 * Demonstrates OOP Polymorphism:
 * - Common abstract base class LibraryItem defines item attributes (title, borrowDate)
 *   and abstract getBorrowDurationDays().
 * - Specialized subclasses BookItem (14 days), DvdItem (7 days), and MagazineItem (3 days)
 *   encapsulate their respective borrowing duration rules.
 * - LibraryItemFactory registers item creators to instantiate objects without if-else type checks.
 * - LibrarySystem uniformly processes items via dynamic method dispatch.
 */
public class L2_LibraryItemDueDateCalculator {

    /**
     * Fixed base date as required by the specification: 2023-10-26.
     */
    public static final LocalDate FIXED_CURRENT_DATE = LocalDate.of(2023, 10, 26);

    /**
     * Abstract base class representing a borrowed library item.
     */
    public static abstract class LibraryItem {
        protected final String title;
        protected final LocalDate borrowDate;

        public LibraryItem(String title, LocalDate borrowDate) {
            this.title = title;
            this.borrowDate = borrowDate;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getBorrowDate() {
            return borrowDate;
        }

        public abstract String getItemType();

        public abstract int getBorrowDurationDays();

        public LocalDate calculateDueDate() {
            return borrowDate.plusDays(getBorrowDurationDays());
        }
    }

    /**
     * Book item: standard borrowing period of 14 days.
     */
    public static class BookItem extends LibraryItem {
        public BookItem(String title, LocalDate borrowDate) {
            super(title, borrowDate);
        }

        @Override
        public String getItemType() {
            return "BOOK";
        }

        @Override
        public int getBorrowDurationDays() {
            return 14;
        }
    }

    /**
     * DVD item: standard borrowing period of 7 days.
     */
    public static class DvdItem extends LibraryItem {
        public DvdItem(String title, LocalDate borrowDate) {
            super(title, borrowDate);
        }

        @Override
        public String getItemType() {
            return "DVD";
        }

        @Override
        public int getBorrowDurationDays() {
            return 7;
        }
    }

    /**
     * Magazine item: standard borrowing period of 3 days.
     */
    public static class MagazineItem extends LibraryItem {
        public MagazineItem(String title, LocalDate borrowDate) {
            super(title, borrowDate);
        }

        @Override
        public String getItemType() {
            return "MAGAZINE";
        }

        @Override
        public int getBorrowDurationDays() {
            return 3;
        }
    }

    /**
     * Factory class mapping item types to constructor references to eliminate if-else chains.
     */
    public static class LibraryItemFactory {
        private static final Map<String, BiFunction<String, LocalDate, LibraryItem>> REGISTRY = new HashMap<>();

        static {
            REGISTRY.put("BOOK", BookItem::new);
            REGISTRY.put("DVD", DvdItem::new);
            REGISTRY.put("MAGAZINE", MagazineItem::new);
        }

        public static LibraryItem createItem(String type, String title, LocalDate borrowDate) {
            BiFunction<String, LocalDate, LibraryItem> creator = REGISTRY.get(type.toUpperCase(Locale.ROOT));
            if (creator == null) {
                throw new IllegalArgumentException("Unknown library item type: " + type);
            }
            return creator.apply(title, borrowDate);
        }
    }

    /**
     * Library processing service handling borrowed items polymorphically.
     */
    public static class LibrarySystem {
        public static void processItems(List<LibraryItem> items) {
            for (LibraryItem item : items) {
                LocalDate dueDate = item.calculateDueDate();
                System.out.println(item.getTitle() + ": " + dueDate);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<LibraryItem> items = new ArrayList<>();

        while (items.size() < n && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            int spaceIdx = line.indexOf(' ');
            if (spaceIdx == -1) {
                continue;
            }

            String type = line.substring(0, spaceIdx).trim();
            String title = line.substring(spaceIdx + 1).trim();

            // Strip enclosing double quotes if present
            if (title.startsWith("\"") && title.endsWith("\"") && title.length() >= 2) {
                title = title.substring(1, title.length() - 1);
            }

            items.add(LibraryItemFactory.createItem(type, title, FIXED_CURRENT_DATE));
        }

        LibrarySystem.processItems(items);
    }
}

/**
 * Alias class for direct execution without L2_ prefix.
 */
class LibraryItemDueDateCalculator extends L2_LibraryItemDueDateCalculator {
    public static void main(String[] args) {
        L2_LibraryItemDueDateCalculator.main(args);
    }
}