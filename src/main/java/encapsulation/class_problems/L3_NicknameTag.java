package encapsulation.class_problems;

/**
 * Problem 3: The Nickname Tag
 * 
 * Demonstrates immutability through encapsulation:
 * - The NameTag class is completely immutable.
 * - String splitting occurs exactly once in the constructor.
 * - State is stored in private final fields.
 * - No setters or mutators are provided.
 * - getNickname() returns firstName + " " + lastInitial + "." without re-splitting.
 */
public class L3_NicknameTag {

    /**
     * Immutable NameTag model class.
     */
    static class NameTag {
        // Immutable components stored in private final fields
        private final String firstName;
        private final char lastInitial;

        /**
         * Constructs an immutable NameTag by splitting the full name once.
         * Assumes exactly one first name and one last name separated by a single space.
         *
         * @param fullName the full name, e.g., "Maria Gomez"
         */
        public NameTag(String fullName) {
            String[] parts = fullName.split(" ");
            this.firstName = parts[0];
            this.lastInitial = parts[1].charAt(0);
        }

        /**
         * Returns the nickname built from the stored parts.
         *
         * @return nickname formatted as "firstName L."
         */
        public String getNickname() {
            return this.firstName + " " + this.lastInitial + ".";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: The Nickname Tag ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("tag.getNickname() -> \"" + tag.getNickname() + "\"");

        // Edge Cases & Invariant Verifications
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Multiple calls do not re-split and return consistent results
        System.out.println("Subsequent call 1: " + tag.getNickname());
        System.out.println("Subsequent call 2: " + tag.getNickname());

        // Edge Case 2: Separate instances with same name behave identically but are distinct
        NameTag tag2 = new NameTag("Maria Gomez");
        System.out.println("tag.getNickname().equals(tag2.getNickname()): " + tag.getNickname().equals(tag2.getNickname()));
        System.out.println("tag != tag2 (separate objects in memory): " + (tag != tag2));

        // Edge Case 3: Reassigning caller variable does not mutate created instance
        String originalName = "Alan Turing";
        NameTag tag3 = new NameTag(originalName);
        originalName = "Grace Hopper";
        System.out.println("After mutating local variable, tag3 nickname remains: \"" + tag3.getNickname() + "\"");

        // Edge Case 4: Other names
        NameTag tag4 = new NameTag("Ada Lovelace");
        System.out.println("tag4.getNickname() -> \"" + tag4.getNickname() + "\"");
    }
}
