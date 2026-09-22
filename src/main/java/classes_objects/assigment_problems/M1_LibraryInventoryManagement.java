package classes_objects.assigment_problems;

/**
 * Problem M1: Library Inventory Management
 *
 * OOP Concepts Tested:
 * - Class design with fields
 * - Constructor initialization
 * - Instance methods
 * - Replacing parallel arrays with an array of objects (BookInventory[])
 *
 * Description:
 * The library tracks its book inventory using BookInventory objects instead of
 * separate parallel arrays for titles, authors, and copiesAvailable.
 */
public class M1_LibraryInventoryManagement {

    static class BookInventory {
        String title;
        String author;
        int copiesAvailable;

        /**
         * Constructor to initialize all three fields.
         *
         * @param title           Title of the book
         * @param author          Author of the book
         * @param copiesAvailable Number of copies currently available
         */
        public BookInventory(String title, String author, int copiesAvailable) {
            this.title = title;
            this.author = author;
            this.copiesAvailable = copiesAvailable;
        }

        /**
         * Instance method printing one formatted entry line:
         * <title> by <author> - <copiesAvailable> copies available
         */
        public void printEntry() {
            System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem M1: Library Inventory Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Create four BookInventory objects and store them in an array
        BookInventory[] inventory = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };

        // Print each book record using a loop
        for (BookInventory book : inventory) {
            book.printEntry();
        }

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Edge Case: Single book with large inventory
        BookInventory single = new BookInventory("Introduction to Algorithms", "CLRS", 50);
        single.printEntry();
    }
}
