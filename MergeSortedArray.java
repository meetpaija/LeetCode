package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedArray {

	/*
	 * 
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
	 * one sorted array.
	 * 
	 * Note:
	 * 
	 * The number of elements initialized in nums1 and nums2 are m and n
	 * respectively. You may assume that nums1 has enough space (size that is
	 * greater or equal to m + n) to hold additional elements from nums2.
	 * 
	 * 
	 * 
	 * Input: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
	 * 
	 * Output: [1,2,2,3,5,6]
	 * 
	 * 
	 */

	public static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) {

		int num2[] = { 2, 5, 6 };
		int num1[] = { 1, 2, 3, 0, 0, 0 };
		merge(num1, 0, num2, 0);
	}

	private static void merge(int[] num1, int a, int[] num2, int b) {

		int num1Orig[] = new int[num1.length - num2.length];
		for (int i = 0; i < num1.length - num2.length; i++)
			num1Orig[i] = num1[i];

		sortedMerge(num1Orig, 0, num2, 0);
	}

	private static void sortedMerge(int[] num1Orig, int a, int[] num2, int b) {
		if (a == num1Orig.length) {
			while (b != num2.length) {
				result.add(num2[b++]);
			}
		}
		if (b == num2.length) {
			while (a != num1Orig.length) {
				result.add(num1Orig[a++]);
			}
		}

		if (a == num1Orig.length && b == num2.length) {
			System.out.println(result);
			int num1[] = new int[num1Orig.length + num2.length];
			for (int i = 0; i < result.size(); i++)
				num1[i] = result.get(i);

			return;
		}

		if (num1Orig[a] <= num2[b]) {
			result.add(num1Orig[a]);
			sortedMerge(num1Orig, a + 1, num2, b);
		} else {
			result.add(num2[b]);
			sortedMerge(num1Orig, a, num2, b + 1);
		}

	}

}
