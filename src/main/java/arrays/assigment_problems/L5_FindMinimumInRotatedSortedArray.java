package arrays.assigment_problems;

import java.util.Arrays;

/**
 * A5. Find Minimum in Rotated Sorted Array
 * 
 * Scenario:
 * A circular duty roster was originally sorted by join date, then "rotated" at some unknown
 * point when the office started the printed list from a different staff member instead of
 * the very first one. Given only the resulting list, find the original earliest join date
 * — without scanning every entry one by one.
 * 
 * Task:
 * - Accept an integer array nums of unique elements, originally sorted in ascending order
 *   and then rotated at some unknown pivot.
 * - Return the minimum element in the array.
 * - Solve it using a modified binary search rather than a linear scan: at each step, compare
 *   the middle element to the rightmost element to decide which half of the array the minimum
 *   must be hiding in.
 * - Time complexity must be O(log n).
 * - Correctly handle an already-sorted array with no rotation.
 */
public class L5_FindMinimumInRotatedSortedArray {

    /**
     * Finds the minimum element in a rotated sorted array of distinct values using binary search.
     *
     * @param nums Array of unique integers originally sorted ascending and then rotated
     * @return The minimum element in the array
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost element, the inflection (pivot) point
            // and the minimum element MUST be strictly to the right of mid
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // If mid element is less than or equal to rightmost element,
                // the minimum is either at mid or to the left of mid
                right = mid;
            }
        }

        // When left == right, both pointers have converged on the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        // PDF Sample 1
        int[] nums1 = {3, 4, 5, 1, 2};
        int result1 = findMin(nums1);
        System.out.println("--- Sample 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + result1); // Expected: 1

        // PDF Sample 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int result2 = findMin(nums2);
        System.out.println("\n--- Sample 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + result2); // Expected: 0

        // PDF Sample 3 (No rotation)
        int[] nums3 = {11, 13, 15, 17};
        int result3 = findMin(nums3);
        System.out.println("\n--- Sample 3 (No Rotation) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + result3); // Expected: 11

        // Edge Case 1: Two elements rotated
        int[] nums4 = {2, 1};
        int result4 = findMin(nums4);
        System.out.println("\n--- Edge Case 1 (Two Elements Rotated) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums4));
        System.out.println("Output: " + result4); // Expected: 1

        // Edge Case 2: Single element
        int[] nums5 = {1};
        int result5 = findMin(nums5);
        System.out.println("\n--- Edge Case 2 (Single Element) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums5));
        System.out.println("Output: " + result5); // Expected: 1

        // Edge Case 3: Rotated near the middle
        int[] nums6 = {5, 6, 7, 1, 2, 3, 4};
        int result6 = findMin(nums6);
        System.out.println("\n--- Edge Case 3 (Rotated Near Middle) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums6));
        System.out.println("Output: " + result6); // Expected: 1
    }
}
