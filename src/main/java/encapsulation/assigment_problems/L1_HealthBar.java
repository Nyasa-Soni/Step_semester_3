package encapsulation.assigment_problems;

/**
 * Assignment Problem 1: The Health Bar
 * 
 * Demonstrates value-boundary encapsulation:
 * - health is private and starts at maxHealth.
 * - maxHealth is final and immutable once assigned.
 * - health can only change via takeDamage(int amount) and heal(int amount).
 * - health is clamped: extra damage below 0 is wasted, extra healing above max is wasted.
 * - No setHealth() setter exists.
 * - Read-only access provided via getHealth() and getMaxHealth().
 */
public class L1_HealthBar {

    /**
     * Character model class.
     */
    static class Character {
        private final int maxHealth;
        private int health;

        /**
         * Constructs a new Character with fixed maximum health and full initial health.
         *
         * @param maxHealth the maximum health capacity
         */
        public Character(int maxHealth) {
            this.maxHealth = Math.max(0, maxHealth);
            this.health = this.maxHealth;
        }

        /**
         * Applies damage to the character, decreasing health down to a minimum of 0.
         * Extra damage beyond 0 is wasted.
         *
         * @param amount the damage to apply
         */
        public void takeDamage(int amount) {
            if (amount > 0) {
                this.health = Math.max(0, this.health - amount);
            }
        }

        /**
         * Restores health up to the maximum health capacity.
         * Extra healing beyond maximum is wasted.
         *
         * @param amount the health amount to restore
         */
        public void heal(int amount) {
            if (amount > 0) {
                this.health = Math.min(this.maxHealth, this.health + amount);
            }
        }

        /**
         * Read-only getter for current health.
         *
         * @return current health value
         */
        public int getHealth() {
            return this.health;
        }

        /**
         * Read-only getter for maximum health.
         *
         * @return maximum health value
         */
        public int getMaxHealth() {
            return this.maxHealth;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: The Health Bar ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        Character c = new Character(100);
        System.out.println("Initial health = " + c.getHealth() + " / " + c.getMaxHealth());

        c.takeDamage(30);
        System.out.println("c.takeDamage(30) -> health = " + c.getHealth());

        c.heal(50);
        System.out.println("c.heal(50) -> health = " + c.getHealth() + " (capped)");

        c.takeDamage(150);
        System.out.println("c.takeDamage(150) -> health = " + c.getHealth() + " (floored)");

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Damage when already at 0 health
        c.takeDamage(50);
        System.out.println("Damage at 0 health: health stays = " + c.getHealth());

        // Edge Case 2: Heal when already at full health
        c.heal(100);
        System.out.println("Healed back to full: health = " + c.getHealth());
        c.heal(25);
        System.out.println("Heal when already at maximum: health stays = " + c.getHealth());

        // Edge Case 3: Exact damage equal to current health
        c.takeDamage(100);
        System.out.println("Damage exactly equal to current health (100): health = " + c.getHealth());

        // Edge Case 4: Moderate healing from zero
        c.heal(40);
        System.out.println("Moderate healing (+40): health = " + c.getHealth());

        // Edge Case 5: Negative amounts rejected
        c.takeDamage(-20);
        c.heal(-30);
        System.out.println("Negative damage/healing ignored: health remains = " + c.getHealth());
    }
}