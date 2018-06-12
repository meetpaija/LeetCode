package LeetCode;


/*
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.
 * 
 * 
*/
public class IntegerBreak {

	public static void main(String[] args) {
		
		System.out.println(run(10));

	}

	private static int run(int i) {
		
		if(i==2)return 1;
		if(i==3)return 2;
		if(i==4)return 4;
		return helper(i);

	}
	
	public static int helper (int n) {
		
		if(n==2)return 2;
		if(n==3)return 3;
		if(n==4)return 4;
		return helper(n-3)*3;
		
	}

}
