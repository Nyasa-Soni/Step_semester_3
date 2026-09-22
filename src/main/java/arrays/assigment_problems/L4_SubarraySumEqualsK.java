package arrays.assigment_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A4. Subarray Sum Equals K
 * 
 * Scenario:
 * A hostel warden reviewing a semester's daily attendance-change log (some days net positive,
 * some negative, as students check in and out) wants to know how many different contiguous
 * stretches of days had a net change of exactly k — across the whole log, not just one answer.
 * 
 * Task:
 * - Accept an integer array nums (which may contain negative numbers) and an integer k.
 * - Return the total number of contiguous subarrays whose sum equals exactly k.
 * - Solve it using running prefix sums combined with a hash map of prefix-sum frequencies:
 *   The sum of any subarray [i, j] equals prefixSum[j] - prefixSum[i - 1].
 *   So at each position j, we need to know how many earlier prefix sums equal (currentSum - k).
 * - A sliding window cannot be used because negative numbers break the monotonic property of window sums.
 * - Initialize the "empty prefix" base case with prefixMap.put(0, 1).
 * - O(n) time complexity and O(n) space complexity.
 */
public class L4_SubarraySumEqualsK {

    /**
     * Counts the number of contiguous subarrays whose sum equals k.
     *
     * @param nums Array of integers (may contain positive, negative, or zero)
     * @param k    Target subarray sum
     * @return Total number of subarrays with sum equal to k
     */
    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Map to store frequency of prefix sums encountered so far
        // Key: prefixSum, Value: count of times this prefixSum has occurred
        Map<Integer, Integer> prefixFrequencyMap = new HashMap<>();

        // Base case: A prefix sum of 0 has occurred once before reading any elements (empty prefix)
        // This handles subarrays starting from index 0 that directly sum to k
        prefixFrequencyMap.put(0, 1);

        int currentSum = 0;
        int totalSubarrays = 0;

        for (int num : nums) {
            currentSum += num;

            // If (currentSum - k) exists in the map, there exist earlier prefixes
            // such that (currentSum - earlierPrefix) == k
            int targetPrefix = currentSum - k;
            if (prefixFrequencyMap.containsKey(targetPrefix)) {
                totalSubarrays += prefixFrequencyMap.get(targetPrefix);
            }

            // Record the current prefix sum in the frequency map
            prefixFrequencyMap.put(currentSum, prefixFrequencyMap.getOrDefault(currentSum, 0) + 1);
        }

        return totalSubarrays;
    }

    public static void main(String[] args) {
        // PDF Sample 1
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int result1 = subarraySum(nums1, k1);
        System.out.println("--- Sample 1 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + result1); // Expected: 2 (subarrays [1, 1] at 0-1 and 1-2)

        // PDF Sample 2
        int[] nums2 = {1, -1, 0};
        int k2 = 0;
        int result2 = subarraySum(nums2, k2);
        System.out.println("\n--- Sample 2 ---");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + result2); // Expected: 3 ([1, -1], [0], [1, -1, 0])

        // Edge Case 1: Single element equals k
        int[] nums3 = {1};
        int k3 = 1;
        int result3 = subarraySum(nums3, k3);
        System.out.println("\n--- Edge Case 1 (Single element equals k) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + result3); // Expected: 1

        // Edge Case 2: Single element does not equal k
        int[] nums4 = {1};
        int k4 = 0;
        int result4 = subarraySum(nums4, k4);
        System.out.println("\n--- Edge Case 2 (Single element != k) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Output: " + result4); // Expected: 0

        // Edge Case 3: All zeros with k = 0
        int[] nums5 = {0, 0, 0};
        int k5 = 0;
        int result5 = subarraySum(nums5, k5);
        System.out.println("\n--- Edge Case 3 (All Zeros) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums5) + ", k = " + k5);
        System.out.println("Output: " + result5); // Expected: 6 (3 of len 1, 2 of len 2, 1 of len 3)

        // Edge Case 4: Negative values and negative k
        int[] nums6 = {-1, -1, 1};
        int k6 = -1;
        int result6 = subarraySum(nums6, k6);
        System.out.println("\n--- Edge Case 4 (Negative numbers and negative k) ---");
        System.out.println("Input: nums = " + Arrays.toString(nums6) + ", k = " + k6);
        System.out.println("Output: " + result6); // Expected: 3 (nums[0..0], nums[1..1], nums[0..2])
    }
}
