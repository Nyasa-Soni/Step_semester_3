package arrays.class_problems;

import java.util.Arrays;

/**
 * L1. Two Sum
 * 
 * Scenario:
 * A shopkeeper wants to find two items from a list of prices that together add up to exactly a customer's budget.
 * 
 * Task:
 * - Accept an integer array, nums, and an integer target.
 * - Using two nested loops, check every pair of different positions (i, j) in the array.
 * - If nums[i] + nums[j] equals target, return the two indices [i, j] immediately.
 * - Assume input always has exactly one valid pair, and cannot use the same element twice.
 */
public class TwoSum {

    /**
     * Finds two indices such that nums[i] + nums[j] == target using pairwise search.
     * 
     * @param nums   Array of item prices
     * @param target Customer's budget target
     * @return Array containing the two indices [i, j]
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(result1));

        // Test Case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(result2));
    }
}

