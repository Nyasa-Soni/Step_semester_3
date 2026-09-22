package classes_objects.assigment_problems;

/**
 * Problem M2: Payroll Salary Management
 *
 * OOP Concepts Tested:
 * - Data encapsulation with private fields
 * - Constructor validation for negative values
 * - Validation inside mutator methods (creditBonus, deductTax)
 * - Computing net salary with read-only access (no public setters)
 *
 * Description:
 * A company's payroll account preserves encapsulation by keeping basicSalary
 * and bonus private. The net salary is calculated dynamically and tax reduction
 * applies strictly to basicSalary while bonus remains included.
 */
public class M2_PayrollSalaryManagement {

    static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        /**
         * Public constructor accepting opening basic salary.
         * If negative, sets basicSalary to 0.0 and prints a warning.
         *
         * @param openingBasicSalary Opening basic salary
         */
        public PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) {
                System.out.println("Warning: Opening basic salary cannot be negative. Initialized to 0.0");
                this.basicSalary = 0.0;
            } else {
                this.basicSalary = openingBasicSalary;
            }
            this.bonus = 0.0;
        }

        /**
         * Credits bonus to the account. Rejects amount <= 0.
         *
         * @param amount Bonus amount to credit
         */
        public void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Credit bonus rejected: amount must be greater than zero");
                return;
            }
            this.bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }

        /**
         * Reduces basicSalary by the given percentage.
         * Rejects any percent outside the 0 to 100 range.
         *
         * @param percent Tax deduction percentage (0 - 100)
         */
        public void deductTax(double percent) {
            if (percent < 0 || percent > 100) {
                System.out.println("Tax deduction rejected: percent must be between 0 and 100");
                return;
            }
            this.basicSalary -= (this.basicSalary * (percent / 100.0));
            String formattedPercent = (percent == (long) percent) ? String.format("%d", (long) percent) : String.valueOf(percent);
            System.out.println("Tax deducted: " + formattedPercent + "%");
        }

        /**
         * Returns net salary: basicSalary + bonus.
         *
         * @return Current net salary
         */
        public double getNetSalary() {
            return this.basicSalary + this.bonus;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem M2: Payroll Salary Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // PDF Sample sequence:
        // basicSalary = 50000, creditBonus(5000), deductTax(10)
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Edge Case 1: Negative opening basic salary
        System.out.println("1. Negative opening basic salary (-20000):");
        PayrollAccount negAccount = new PayrollAccount(-20000);
        System.out.println("Net salary: Rs " + negAccount.getNetSalary());

        // Edge Case 2: Invalid creditBonus (0 and negative)
        System.out.println();
        System.out.println("2. Invalid creditBonus attempts:");
        account.creditBonus(0);
        account.creditBonus(-100);

        // Edge Case 3: Invalid deductTax (-1 and 101)
        System.out.println();
        System.out.println("3. Invalid deductTax attempts:");
        account.deductTax(-1);
        account.deductTax(101);

        // Edge Case 4: deductTax(100) on a fresh account
        System.out.println();
        System.out.println("4. Full 100% tax deduction on basic salary with bonus:");
        PayrollAccount fullTaxAccount = new PayrollAccount(40000);
        fullTaxAccount.creditBonus(8000);
        fullTaxAccount.deductTax(100);
        System.out.println("Net salary after 100% tax: Rs " + fullTaxAccount.getNetSalary());
    }
}
