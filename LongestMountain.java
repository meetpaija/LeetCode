package LeetCode;

public class LongestMountain {
	
	/*

Problem: 845

Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

    B.length >= 3
    There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]

(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.



Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.


*/
	
	
	public static void main(String[] args) {

		int arr[] = {0,1,0,0,1,0,0,1,1,0,0,0,1,1,0,1,0,1,0,1,0,0,0,1,0,0,1,1,0,1,1,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,0,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,0,0,1,0,1,1};
		int result = LongestMountain(arr);
		System.out.println(result);
	}

	private static int LongestMountain(int[] arr) {
		
		if (arr.length < 3)
			return 0;
		int max = 0;
		boolean decrPhase=false;
		int count=1;
		for (int i = 0; i < arr.length; i++) {
			if (i+1 < arr.length) {
				if(arr[i]==arr[i+1]) {
					count=1;
					continue;
				}
				if(!decrPhase && arr[i]<arr[i+1])
				{
					count++;
					if(i+2==arr.length)
						break;
					if(i+2<arr.length && arr[i+2]<arr[i+1]) 
						decrPhase=true;
				}
				else if(decrPhase && arr[i]>arr[i+1]) {
					count++;
					if(i+2==arr.length) 
						if(max<count)max=count;
					
					if(i+2<arr.length && arr[i+2]>=arr[i+1]) {
						decrPhase=false;
						if(max<count)max=count;
						count=1;
					}
				}
				
			}
		}
		return max;
	}

}
