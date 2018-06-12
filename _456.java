package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class _456 {

	public static void main(String[] args) {
  
		int nums[] = {1};
		System.out.println(find132pattern(nums)); 

	}

	public static boolean find132pattern(int[] nums) {
		// boolean flag=false;
		if (nums.length < 3)
			return false;
		
		HashSet<Integer> hs=new HashSet<Integer>();
		for(long i=0;i<nums.length;i++)
			hs.add(nums[(int) i]);

		if(hs.size()<=2)
			return false;
		
		for (long i = 0; i < nums.length - 2; i++) {

			long j = i + 1;

			while (j != nums.length - 1) {
				if (nums[(int) j] > nums[(int) i]) {

					long k = j + 1;
					while (k != nums.length) {
						if (nums[(int) j] > nums[(int) k] && nums[(int) k] > nums[(int) i])
							return true;
						k++;
					}
				}
				j++;
			}

		}

		return false;
	}

}
