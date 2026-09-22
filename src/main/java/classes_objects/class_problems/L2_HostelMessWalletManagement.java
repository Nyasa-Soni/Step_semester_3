package classes_objects.class_problems;

/**
 * Problem M2: Hostel Mess Wallet Management
 *
 * OOP Concepts Tested:
 * - Data encapsulation using private fields
 * - Constructor initialization with input validation
 * - Business logic validation inside mutator methods
 * - Read-only getter method
 * - Preventing direct balance manipulation (no public setter)
 *
 * Description:
 * A hostel mess-card top-up wallet must never be allowed to go negative,
 * and its balance must never be overwritten directly from outside the class.
 */
class MessWallet {
    private double balance;

    /**
     * Public constructor accepting an opening balance.
     * If a negative value is supplied, starts at 0.0 and prints a warning.
     *
     * @param openingBalance Initial balance to open the mess wallet
     */
    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: Opening balance cannot be negative. Initialized to 0.0");
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    /**
     * Rejects amount <= 0 with a message, otherwise adds it to the balance.
     *
     * @param amount Top-up amount
     */
    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than zero");
            return;
        }
        this.balance += amount;
    }

    /**
     * Rejects any amount greater than current balance or amount <= 0,
     * printing a clear message instead of allowing the balance to go negative.
     *
     * @param amount Amount to deduct
     */
    public void deduct(double amount) {
        if (amount <= 0) {
            System.out.println("Deduct rejected: amount must be greater than zero");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }
        this.balance -= amount;
    }

    /**
     * Read-only getter for the current balance.
     * No public setter exists to preserve encapsulation.
     *
     * @return Current wallet balance
     */
    public double getBalance() {
        return this.balance;
    }
}

public class L2_HostelMessWalletManagement {

    public static void main(String[] args) {
        System.out.println("--- Problem M2: Hostel Mess Wallet Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // PDF Sample sequence:
        // opening = 500, topUp(200), deduct(1000)
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        System.out.println("Balance after top-up: " + wallet.getBalance());
        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Edge Case 1: Negative opening balance
        System.out.println("1. Initializing wallet with negative opening balance (-150.0):");
        MessWallet negWallet = new MessWallet(-150.0);
        System.out.println("Balance after negative init: " + negWallet.getBalance());

        // Edge Case 2: Zero and negative topUp
        System.out.println();
        System.out.println("2. Invalid topUp attempts:");
        wallet.topUp(0);
        wallet.topUp(-50);

        // Edge Case 3: Zero deduct
        System.out.println();
        System.out.println("3. Invalid deduct attempts:");
        wallet.deduct(0);

        // Edge Case 4: Deduct exact balance
        System.out.println();
        System.out.println("4. Deducting exact balance (700.0):");
        wallet.deduct(700.0);
        System.out.println("Balance after exact deduction: " + wallet.getBalance());

        // Edge Case 5: Valid deduction from fresh balance
        System.out.println();
        System.out.println("5. Valid top-up and deduction:");
        wallet.topUp(350.0);
        System.out.println("Balance after top-up: " + wallet.getBalance());
        wallet.deduct(150.0);
        System.out.println("Balance after valid deduction: " + wallet.getBalance());
    }
}
