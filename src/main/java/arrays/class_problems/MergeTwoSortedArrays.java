package arrays.class_problems;

import java.util.Arrays;

/**
 * L4. Merge Two Sorted Arrays
 * 
 * Scenario:
 * Two class sections each submit their exam scores already sorted from lowest to highest.
 * The examination office needs one single combined sorted list — without throwing both lists together
 * and re-sorting everything from scratch.
 * 
 * Task:
 * - Accept two sorted integer arrays, arr1 and arr2.
 * - Keep one index pointer for each array, both starting at 0, and create a new empty result array.
 * - Using a while loop, repeatedly compare the current elements pointed to in arr1 and arr2,
 *   copy the smaller one into the result array, and move that array's pointer forward by one.
 * - Once one array is fully copied over, copy all of the remaining elements from the other array
 *   directly onto the end of the result.
 * - Return the fully merged, fully sorted result array.
 */
public class MergeTwoSortedArrays {

    /**
     * Merges two pre-sorted arrays into a single combined sorted array using the two-pointer approach.
     * 
     * @param arr1 First sorted integer array
     * @param arr2 Second sorted integer array
     * @return Merged sorted array containing all elements of arr1 and arr2
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0; // Pointer for arr1
        int j = 0; // Pointer for arr2
        int k = 0; // Pointer for result array

        // Repeatedly compare current elements and copy smaller one
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        // Copy remaining elements of arr1, if any
        while (i < n1) {
            result[k++] = arr1[i++];
        }

        // Copy remaining elements of arr2, if any
        while (j < n2) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1_1 = {1, 3, 5};
        int[] arr2_1 = {2, 4, 6};
        int[] merged1 = mergeSortedArrays(arr1_1, arr2_1);
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: arr1 = " + Arrays.toString(arr1_1) + ", arr2 = " + Arrays.toString(arr2_1));
        System.out.println("Output: " + Arrays.toString(merged1));

        // Test Case 2
        int[] arr1_2 = {};
        int[] arr2_2 = {1, 2, 3};
        int[] merged2 = mergeSortedArrays(arr1_2, arr2_2);
        System.out.println("\n--- Test Case 2 ---");
        System.out.println("Input: arr1 = " + Arrays.toString(arr1_2) + ", arr2 = " + Arrays.toString(arr2_2));
        System.out.println("Output: " + Arrays.toString(merged2));
    }
}

