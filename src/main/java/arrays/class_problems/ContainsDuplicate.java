package arrays.class_problems;

import java.util.Arrays;

/**
 * L3. Contains Duplicate
 * 
 * Scenario:
 * Before finalizing an exam seating chart, the office must double-check that no roll number
 * was accidentally entered twice in the list — by comparing every entry against every other entry.
 * 
 * Task:
 * - Accept an integer array nums.
 * - Using two nested loops, compare every element at position i against every element at a different position j.
 * - If any two different positions hold the exact same value, return true right away.
 * - If no matching pair is found after checking every possible pair, return false.
 */
public class ContainsDuplicate {

    /**
     * Checks if the array contains any duplicate values using pairwise comparison.
     * 
     * @param nums Array of integers
     * @return true if any value appears at least twice, false otherwise
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true; // Early exit on finding first duplicate
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 2, 3, 1};
        boolean result1 = containsDuplicate(nums1);
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + result1 + " (the value 1 appears at two different positions)");

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4};
        boolean result2 = containsDuplicate(nums2);
        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + result2 + " (every value is distinct)");
    }
}

