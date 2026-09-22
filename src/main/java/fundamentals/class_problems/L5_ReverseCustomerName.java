package fundamentals.class_problems;

/**
 * Problem 5: Reverse Customer Name
 *
 * Customer Identity Verification System: safely reverses a customer's name
 * for identity verification without modifying the original string data.
 */
public class L5_ReverseCustomerName {

    /**
     * Reverses the given customer name using character array manipulation.
     * Leaves the original string immutable and unmodified.
     *
     * @param customerName the input name to reverse
     * @return the reversed name as a new String, or null if customerName is null
     */
    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }

        char[] chars = customerName.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    /**
     * Helper method to display original and reversed customer names.
     *
     * @param customerName name to display and reverse
     */
    public static void displayReversedName(String customerName) {
        String reversed = reverseCustomerName(customerName);
        System.out.printf("Original Name: %s%n", customerName);
        System.out.printf("Reversed Name: %s%n%n", reversed);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      DAY 1: REVERSE CUSTOMER NAME                ");
        System.out.println("==================================================\n");

        // Primary PDF test case
        displayReversedName("Sunil");

        // Additional test cases for comprehensive demonstration
        displayReversedName("Alexander");
        displayReversedName("Grace Hopper");
        displayReversedName("Ada Lovelace");
    }
}
