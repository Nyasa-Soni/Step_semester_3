package arrays.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A3. 3Sum
 * 
 * Scenario:
 * A budgeting tool needs to find every distinct combination of exactly three transactions
 * in a student's account history that cancel each other out exactly — summing to zero —
 * without ever reporting the same combination of amounts twice, even if it could be picked
 * out of the list in more than one way.
 * 
 * Task:
 * - Accept an integer array nums.
 * - Return all unique triplets [nums[i], nums[j], nums[k]] (i, j, k all different positions)
 *   such that the three values sum to exactly 0.
 * - Sort the array first, then for each element, use two pointers moving inward from both ends
 *   of the remaining subarray to find pairs that complete the sum to zero.
 * - Carefully skip over duplicate values at every level to avoid reporting the same triplet more than once.
 * - Target overall time complexity: O(n^2).
 */
public class L3_ThreeSum {

    /**
     * Finds all unique triplets in the array that sum to zero.
     *
     * @param nums Array of integers
     * @return 2D array of unique triplets summing to 0
     */
    public static int[][] threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new int[0][0];
        }

        // Step 1: Sort the array to enable two-pointer traversal and easy duplicate skipping
        Arrays.sort(nums);

        List<int[]> result = new ArrayList<>();
        int n = nums.length;

        // Step 2: Fix the first element nums[i] and use two pointers for the remaining two elements
        for (int i = 0; i < n - 2; i++) {
            // Early exit optimization: If the current number is greater than 0,
            // no three positive numbers can sum to 0 in a sorted array
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Valid triplet found
                    result.add(new int[]{nums[i], nums[left], nums[right]});

                    // Skip duplicate values for the second element (left pointer)
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicate values for the third element (right pointer)
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move both pointers inward to look for the next distinct pair
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum is too small; move left pointer rightward to increase sum
                    left++;
                } else {
                    // Sum is too large; move right pointer leftward to decrease sum
                    right--;
                }
            }
        }

        // Convert the list of triplets to a 2D integer array
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // PDF Sample 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("--- Sample 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        int[][] result1 = threeSum(nums1.clone());
        System.out.println("Output: " + Arrays.deepToString(result1)); // Expected: [[-1, -1, 2], [-1, 0, 1]]

        // PDF Sample 2
        int[] nums2 = {0, 0, 0};
        System.out.println("\n--- Sample 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        int[][] result2 = threeSum(nums2.clone());
        System.out.println("Output: " + Arrays.deepToString(result2)); // Expected: [[0, 0, 0]]

        // Edge Case 1: Four zeros
        int[] nums3 = {0, 0, 0, 0};
        System.out.println("\n--- Edge Case 1 (Four Zeros) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        int[][] result3 = threeSum(nums3.clone());
        System.out.println("Output: " + Arrays.deepToString(result3)); // Expected: [[0, 0, 0]]

        // Edge Case 2: Duplicate negative and positive numbers
        int[] nums4 = {-2, 0, 0, 2, 2};
        System.out.println("\n--- Edge Case 2 (Repeated Values) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums4));
        int[][] result4 = threeSum(nums4.clone());
        System.out.println("Output: " + Arrays.deepToString(result4)); // Expected: [[-2, 0, 2]]

        // Edge Case 3: Multiple identical candidates
        int[] nums5 = {-1, -1, -1, 2, 2};
        System.out.println("\n--- Edge Case 3 (Multiple Identical Values) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums5));
        int[][] result5 = threeSum(nums5.clone());
        System.out.println("Output: " + Arrays.deepToString(result5)); // Expected: [[-1, -1, 2]]
    }
}
