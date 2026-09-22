package encapsulation.assigment_problems;

/**
 * Assignment Problem 3: The Password Checker
 * 
 * Demonstrates immutability and sensitive data shielding:
 * - Password string is stored in a private final field.
 * - Password is never exposed via any getter or external method.
 * - Password cannot be modified once the instance is created.
 * - getStrength() evaluates and exposes only a safe categorical strength label:
 *     - "Weak"   : under 6 characters (< 6)
 *     - "Medium" : 6 to 9 characters (6..9)
 *     - "Strong" : 10 or more characters (>= 10)
 */
public class L3_PasswordChecker {

    /**
     * PasswordChecker model class.
     */
    static class PasswordChecker {
        // Sensitive password stored privately and immutably; never exposed
        private final String password;

        /**
         * Constructs a PasswordChecker with the given password.
         *
         * @param password the password to evaluate
         */
        public PasswordChecker(String password) {
            this.password = (password != null) ? password : "";
        }

        /**
         * Computes and returns the password strength rating based on length.
         * The password text itself is never revealed.
         *
         * @return "Weak", "Medium", or "Strong"
         */
        public String getStrength() {
            int len = this.password.length();
            if (len < 6) {
                return "Weak";
            } else if (len <= 9) {
                return "Medium";
            } else {
                return "Strong";
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: The Password Checker ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        PasswordChecker pc = new PasswordChecker("abcd");
        System.out.println("PasswordChecker(\"abcd\").getStrength() -> \"" + pc.getStrength() + "\"");

        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("PasswordChecker(\"abcdefghij\").getStrength() -> \"" + pc2.getStrength() + "\"");

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // 4 chars (< 6) -> Weak
        System.out.println("Length 4 (\"test\") -> " + new PasswordChecker("test").getStrength());

        // 5 chars (< 6) -> Weak boundary
        System.out.println("Length 5 (\"apple\") -> " + new PasswordChecker("apple").getStrength());

        // 6 chars (6..9) -> Medium boundary
        System.out.println("Length 6 (\"orange\") -> " + new PasswordChecker("orange").getStrength());

        // 8 chars (6..9) -> Medium
        System.out.println("Length 8 (\"password\") -> " + new PasswordChecker("password").getStrength());

        // 9 chars (6..9) -> Medium boundary
        System.out.println("Length 9 (\"pass12345\") -> " + new PasswordChecker("pass12345").getStrength());

        // 10 chars (>= 10) -> Strong boundary
        System.out.println("Length 10 (\"abcdefghij\") -> " + new PasswordChecker("abcdefghij").getStrength());

        // 12 chars (>= 10) -> Strong
        System.out.println("Length 12 (\"securePass12\") -> " + new PasswordChecker("securePass12").getStrength());

        // Null password
        System.out.println("Null password -> " + new PasswordChecker(null).getStrength());
    }
}