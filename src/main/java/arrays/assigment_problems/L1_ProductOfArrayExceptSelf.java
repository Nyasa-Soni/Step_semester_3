package arrays.assigment_problems;

import java.util.Arrays;

/**
 * A1. Product of Array Except Self
 * 
 * Scenario:
 * A pricing engine needs, for every product in a bundle, the combined price of every OTHER
 * product in that same bundle — computed for all products at once, without ever dividing by
 * the current product's own price (some prices could legitimately be zero, e.g. a free
 * promotional item, which would make division undefined).
 * 
 * Task:
 * - Accept an integer array nums.
 * - Return an array answer where answer[i] is the product of every element in nums except nums[i]
 *   — without using division anywhere in the solution.
 * - Solve it in O(n) time using two passes:
 *   1. A forward pass accumulating the running product of everything to the left of each index.
 *   2. A backward pass multiplying in the running product of everything to the right.
 * - O(1) extra space beyond the output array.
 */
public class L1_ProductOfArrayExceptSelf {

    /**
     * Computes the product of all elements in the array except nums[i] without division.
     *
     * @param nums Array of integers
     * @return Array where answer[i] is the product of all elements except nums[i]
     */
    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] answer = new int[n];

        // Pass 1: Forward pass to calculate prefix products (products to the left)
        // answer[i] stores the product of all elements to the left of index i.
        // For index 0, there are no elements to the left, so prefix product is 1.
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Pass 2: Backward pass to multiply suffix products (products to the right)
        // Maintain a running product of all elements seen so far to the right of index i.
        // For the last element (n - 1), there are no elements to the right, so running product is 1.
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct *= nums[i]; // Update running right product for the next element to the left
        }

        return answer;
    }

    public static void main(String[] args) {
        // PDF Sample 1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = productExceptSelf(nums1);
        System.out.println("--- Sample 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + Arrays.toString(result1)); // Expected: [24, 12, 8, 6]

        // PDF Sample 2 (contains a single zero)
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = productExceptSelf(nums2);
        System.out.println("\n--- Sample 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + Arrays.toString(result2)); // Expected: [0, 0, 9, 0, 0]

        // Edge Case: Zero at index 0
        int[] nums3 = {0, 2, 3, 4};
        int[] result3 = productExceptSelf(nums3);
        System.out.println("\n--- Edge Case 1 (Zero at index 0) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + Arrays.toString(result3)); // Expected: [24, 0, 0, 0]

        // Edge Case: Multiple zeros
        int[] nums4 = {0, 0, 3, 4};
        int[] result4 = productExceptSelf(nums4);
        System.out.println("\n--- Edge Case 2 (Multiple zeros) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums4));
        System.out.println("Output: " + Arrays.toString(result4)); // Expected: [0, 0, 0, 0]
    }
}
