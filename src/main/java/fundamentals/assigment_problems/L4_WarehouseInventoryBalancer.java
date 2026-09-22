package fundamentals.assigment_problems;

/**
 * Assignment Problem 4: The Warehouse Inventory Balancer
 *
 * Warehouse stock audit tool that compares inventory totals between Section A
 * and Section B, detects balance status, and tracks the peak quantity across sections.
 */
public class L4_WarehouseInventoryBalancer {

    /**
     * Computes totals for Section A and Section B, checks if they are balanced,
     * and reports the highest quantity item with its section and human-readable item number.
     *
     * @param sectionA array of item quantities in Section A
     * @param sectionB array of item quantities in Section B
     */
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null) {
            System.out.println("Invalid input: Inventory sections cannot be null.");
            return;
        }

        if (sectionA.length != sectionB.length) {
            System.out.println("Invalid input: Sections must have equal number of items.");
            return;
        }

        if (sectionA.length == 0) {
            System.out.println("Section A Total: 0 | Section B Total: 0 | Status: Balanced | Highest Quantity: 0 (None)");
            return;
        }

        int totalA = 0;
        int totalB = 0;
        int maxQuantity = Integer.MIN_VALUE;
        String maxSection = "Section A";
        int maxItemNumber = 1;

        // Scan Section A
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > maxQuantity) {
                maxQuantity = sectionA[i];
                maxSection = "Section A";
                maxItemNumber = i + 1; // 1-based human-readable item number
            }
        }

        // Scan Section B (strictly greater than preserves first occurrence if tied)
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > maxQuantity) {
                maxQuantity = sectionB[i];
                maxSection = "Section B";
                maxItemNumber = i + 1; // 1-based human-readable item number
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.printf(
                "Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, status, maxQuantity, maxSection, maxItemNumber);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ASSIGNMENT 4: WAREHOUSE INVENTORY BALANCER     ");
        System.out.println("==================================================\n");

        // PDF Sample Case: Balanced inventory with tie on max quantity
        int[] sampleA = { 20, 15, 30 };
        int[] sampleB = { 25, 10, 30 };
        System.out.println("Test Case 1: sectionA={20,15,30}, sectionB={25,10,30}");
        analyzeInventory(sampleA, sampleB);
        System.out.println();

        // Additional Case: Not Balanced inventory where Section B has highest item
        int[] warehouseA = { 45, 30, 25, 10 };
        int[] warehouseB = { 40, 60, 20, 15 };
        System.out.println("Test Case 2: sectionA={45,30,25,10}, sectionB={40,60,20,15}");
        analyzeInventory(warehouseA, warehouseB);
    }
}
