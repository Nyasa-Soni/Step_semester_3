package encapsulation.class_problems;

/**
 * Problem 1: The Piggy Bank
 * 
 * Demonstrates basic encapsulation by protecting mutable balance state:
 * - The savings field is private and can only change via deposit() and withdraw().
 * - No setSavings() setter exists.
 * - The bank ID is final and immutable once assigned at creation.
 * - Over-withdrawal is rejected, preserving the existing balance.
 */
public class L1_PiggyBank {

    /**
     * PiggyBank model class.
     */
    static class PiggyBank {
        // Unique identifier locked in place upon creation
        private final String id;

        // Private savings amount; mutable only through controlled business methods
        private double savings;

        /**
         * Constructs a new PiggyBank with a fixed ID and 0 savings.
         *
         * @param id the unique identifier for this piggy bank
         */
        public PiggyBank(String id) {
            this.id = id;
            this.savings = 0.0;
        }

        /**
         * Deposits money into the piggy bank.
         *
         * @param amount the positive amount to add
         */
        public void deposit(double amount) {
            if (amount > 0) {
                this.savings += amount;
            }
        }

        /**
         * Withdraws money from the piggy bank if sufficient savings exist.
         * If the requested amount exceeds current savings, the withdrawal is rejected
         * and the savings balance remains untouched.
         *
         * @param amount the amount to withdraw
         * @return true if withdrawal succeeded, false if rejected
         */
        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= this.savings) {
                this.savings -= amount;
                return true;
            }
            return false;
        }

        /**
         * Read-only getter for current savings.
         *
         * @return current savings amount
         */
        public double getSavings() {
            return this.savings;
        }

        /**
         * Read-only getter for the piggy bank ID.
         *
         * @return the piggy bank ID
         */
        public String getId() {
            return this.id;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: The Piggy Bank ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        PiggyBank pb = new PiggyBank("PB-1");
        System.out.println("Created PiggyBank with ID: " + pb.getId() + ", initial savings = " + pb.getSavings());

        pb.deposit(100);
        System.out.println("pb.deposit(100) -> savings = " + pb.getSavings());

        boolean w1 = pb.withdraw(30);
        System.out.println("pb.withdraw(30) -> success: " + w1 + ", savings = " + pb.getSavings());

        boolean w2 = pb.withdraw(500);
        System.out.println("pb.withdraw(500) -> success: " + w2 + " (rejected), savings stays " + pb.getSavings());

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Fresh bank with 0 balance withdrawal
        PiggyBank emptyBank = new PiggyBank("PB-EMPTY");
        boolean wEmpty = emptyBank.withdraw(10);
        System.out.println("Withdraw from empty bank: rejected=" + (!wEmpty) + ", savings=" + emptyBank.getSavings());

        // Edge Case 2: Exact balance withdrawal
        pb.withdraw(70);
        System.out.println("pb.withdraw(70) [exact balance] -> savings = " + pb.getSavings());

        // Edge Case 3: Invalid / negative deposit & withdrawal
        pb.deposit(-50);
        System.out.println("pb.deposit(-50) -> savings remains = " + pb.getSavings());
        boolean wNeg = pb.withdraw(-20);
        System.out.println("pb.withdraw(-20) -> rejected=" + (!wNeg) + ", savings = " + pb.getSavings());
    }
}
