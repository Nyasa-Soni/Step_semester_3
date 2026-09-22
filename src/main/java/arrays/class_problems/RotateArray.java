package arrays.class_problems;

import java.util.Arrays;

/**
 * L5. Rotate Array
 * 
 * Scenario:
 * A playlist needs to be shifted so the last few songs move to the front of the queue,
 * rotating the whole list to the right by a given number of positions.
 * 
 * Task:
 * - Accept an integer array nums and an integer k, the number of positions to rotate to the right.
 * - First reduce k using k = k % nums.length — rotating by the array's own length has no visible effect.
 * - Create a new array of the same size. For every index i in the original array,
 *   work out its new position after rotation: newArray[(i + k) % nums.length] = nums[i].
 * - Copy the values from the new array back into nums (or return the new array).
 */
public class RotateArray {

    /**
     * Rotates an array to the right by k steps.
     * 
     * @param nums Array of integers to rotate
     * @param k    Number of steps to rotate right
     * @return Rotated array
     */
    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        k = k % n; // Handle k larger than array length

        if (k == 0) {
            return nums;
        }

        int[] newArray = new int[n];
        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }

        // Copy values from newArray back into original nums array
        System.arraycopy(newArray, 0, nums, 0, n);

        return nums;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        int[] result1 = rotateArray(nums1, k1);
        System.out.println("Output: " + Arrays.toString(result1));

        // Test Case 2
        int[] nums2 = {1, 2};
        int k2 = 3;
        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        int[] result2 = rotateArray(nums2, k2);
        System.out.println("Output: " + Arrays.toString(result2) + " (k % length = 3 % 2 = 1, single rotation)");
    }
}

