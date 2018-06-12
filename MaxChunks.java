package LeetCode;

import java.util.Arrays;

/*

*	Problem:769
*
*Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], 
*we split the array into some number of "chunks" (partitions), and individually sort each chunk. 
* After concatenating them, the result equals the sorted array.
* What is the most number of chunks we could have made?
*
*
*Input: arr = [4,3,2,1,0]
*Output: 1
*Explanation:
*Splitting into two or more chunks will not return the required result.
*For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
*
*/

public class MaxChunks {

	public static void main(String[] args) {

		int arr[] = { 0, 3, 1, 2 };

		int result = maxChunksToSorted(arr);
		System.out.println(result);
	}

	private static int maxChunksToSorted(int[] arr) {
		int len = arr.length;

		if (arr[0] == len - 1)
			return 1;

		int count = len;
		int j = 0;

		while (j < arr.length) {

			if (arr[j] == j) {
				j++;
				continue;
			}

			int k = j + 1;

			while (k < len) {
				if (k - 1 == j)
					swapNumbers(arr, j, k);
				else
					Arrays.sort(arr, j, k + 1);

				if (isEqual(arr, j, k)) {
					count -= k - j;
					j = k + 1;
					break;
				} else
					k++;
			}

		}
		return count;

	}

	private static void swapNumbers(int[] arr, int j, int k) {

		int temp = arr[j];
		arr[j] = arr[k];
		arr[k] = temp;

	}

	private static boolean isEqual(int[] arr, int i, int j) {

		for (int j2 = i; j2 <= j; j2++) {
			if (arr[j2] != j2)
				return false;
		}

		return true;
	}

}
