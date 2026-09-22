package encapsulation.assigment_problems;

/**
 * Assignment Problem 4: The Traffic Light
 * 
 * Demonstrates state-machine encapsulation with restricted transitions:
 * - Current color is private and starts at "RED".
 * - No method allows arbitrary colors to be set directly.
 * - The only mechanism to transition state is next(), which strictly follows:
 *     RED -> GREEN -> YELLOW -> RED -> GREEN ...
 * - Traffic light ID is final and locked upon creation.
 * - Read-only inspection is provided via getColor() and getId().
 */
public class L4_TrafficLight {

    /**
     * TrafficLight model class.
     */
    static class TrafficLight {
        // Immutable identifier fixed at construction
        private final String id;

        // Current light state; mutated exclusively through next()
        private String color;

        /**
         * Constructs a TrafficLight with a fixed ID, starting at "RED".
         *
         * @param id the unique traffic light ID
         */
        public TrafficLight(String id) {
            this.id = id;
            this.color = "RED";
        }

        /**
         * Advances the traffic light to the next valid state in the cycle:
         * RED -> GREEN -> YELLOW -> RED.
         *
         * @return the new color state after advancing
         */
        public String next() {
            if ("RED".equals(this.color)) {
                this.color = "GREEN";
            } else if ("GREEN".equals(this.color)) {
                this.color = "YELLOW";
            } else {
                this.color = "RED";
            }
            return this.color;
        }

        /**
         * Read-only getter for the current light color.
         *
         * @return current color ("RED", "GREEN", or "YELLOW")
         */
        public String getColor() {
            return this.color;
        }

        /**
         * Read-only getter for the traffic light ID.
         *
         * @return the traffic light ID
         */
        public String getId() {
            return this.id;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: The Traffic Light ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("TrafficLight ID: " + t.getId());
        System.out.println("t.getColor() -> \"" + t.getColor() + "\"");

        System.out.println("t.next() -> \"" + t.next() + "\"");
        System.out.println("t.next() -> \"" + t.next() + "\"");
        System.out.println("t.next() -> \"" + t.next() + "\"");

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Multiple complete cycles
        System.out.println("Cycle continuing from RED:");
        System.out.println("Step 1 -> " + t.next() + " (expected GREEN)");
        System.out.println("Step 2 -> " + t.next() + " (expected YELLOW)");
        System.out.println("Step 3 -> " + t.next() + " (expected RED)");
        System.out.println("Step 4 -> " + t.next() + " (expected GREEN)");

        // Edge Case 2: Invariant inspection
        System.out.println("Current color verified via getColor(): " + t.getColor());
        System.out.println("Traffic light ID remains immutable: " + t.getId());
    }
}