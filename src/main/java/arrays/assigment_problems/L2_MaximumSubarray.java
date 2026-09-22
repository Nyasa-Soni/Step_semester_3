package arrays.assigment_problems;

import java.util.Arrays;

/**
 * A2. Maximum Subarray
 * 
 * Scenario:
 * A trader has a full year of daily profit-or-loss figures, some positive, some negative,
 * and wants to know the single best contiguous stretch of days to have been actively trading
 * — the run of consecutive days whose combined total is the highest possible.
 * 
 * Task:
 * - Accept an integer array nums, which may contain negative numbers.
 * - Find the contiguous subarray (containing at least one number) with the largest possible sum,
 *   and return that sum.
 * - Solve it using Kadane's algorithm: at each element, decide whether to extend the current running
 *   subarray or abandon it and start fresh from the current element, based on whichever gives a larger sum.
 * - O(n) time complexity and O(1) extra space.
 * - Correctly handle arrays where every value is negative.
 */
public class L2_MaximumSubarray {

    /**
     * Finds the maximum sum of a contiguous subarray using Kadane's algorithm.
     *
     * @param nums Array of integers (may contain negative numbers)
     * @return The maximum subarray sum
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must contain at least one element");
        }

        // Initialize currentSum and maxSum with the first element
        // This ensures the algorithm handles all-negative arrays correctly without defaulting to 0
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Decision: Extend existing subarray or start a new subarray from nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update overall maximum sum found so far
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        // PDF Sample 1
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result1 = maxSubArray(nums1);
        System.out.println("--- Sample 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + result1); // Expected: 6 (subarray [4, -1, 2, 1])

        // PDF Sample 2 (All negative numbers)
        int[] nums2 = {-3, -1, -2};
        int result2 = maxSubArray(nums2);
        System.out.println("\n--- Sample 2 (All Negative) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + result2); // Expected: -1

        // Edge Case 1: Single positive element
        int[] nums3 = {5};
        int result3 = maxSubArray(nums3);
        System.out.println("\n--- Edge Case 1 (Single Positive) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + result3); // Expected: 5

        // Edge Case 2: Single negative element
        int[] nums4 = {-5};
        int result4 = maxSubArray(nums4);
        System.out.println("\n--- Edge Case 2 (Single Negative) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums4));
        System.out.println("Output: " + result4); // Expected: -5

        // Edge Case 3: Mixed elements
        int[] nums5 = {1, -2, 3, 4, -1};
        int result5 = maxSubArray(nums5);
        System.out.println("\n--- Edge Case 3 (Mixed Elements) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums5));
        System.out.println("Output: " + result5); // Expected: 7 (subarray [3, 4])
    }
}
