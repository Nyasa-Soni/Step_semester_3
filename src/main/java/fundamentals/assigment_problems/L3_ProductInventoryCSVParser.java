package fundamentals.assigment_problems;

/**
 * Problem 3: Product Inventory CSV Parser
 * 
 * Scenario:
 * The warehouse team receives inventory updates as CSV lines and needs a quick
 * parser to split each line into fields and print a formatted record.
 * 
 * Task:
 * - Accept a CSV line in the form "ProductName,SKU,Quantity".
 * - Use split(",") to break it into fields.
 * - Validate that exactly 3 fields are present; if not, print "Invalid Record".
 * - Print a formatted record: "Product: ... | SKU: ... | Qty: ...".
 */
public class L3_ProductInventoryCSVParser {

    /**
     * Parses a single CSV inventory record and prints the formatted output.
     *
     * @param csvLine CSV line containing "ProductName,SKU,Quantity"
     */
    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Split CSV line by comma
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        if (productName.isEmpty() || sku.isEmpty() || quantity.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Print formatted record
        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = "Wireless Mouse,WM-2201,150";
        System.out.println("Input: \"" + sample1 + "\"");
        parseInventoryRecord(sample1);

        String sample2 = "Wireless Mouse,150";
        System.out.println("Input: \"" + sample2 + "\"");
        parseInventoryRecord(sample2);

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "Mechanical Keyboard,KB-9900,45,Warehouse-A";
        System.out.println("Input: \"" + sample3 + "\"");
        parseInventoryRecord(sample3);

        String sample4 = "";
        System.out.println("Input: \"" + sample4 + "\"");
        parseInventoryRecord(sample4);
    }
}
