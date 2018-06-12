package LeetCode;

import java.util.Arrays;
import java.util.List;

public class UglyNumber {
	public static List<Integer> factors = Arrays.asList(2, 3, 5);

	public static void main(String[] args) {

		int n = 10;
		int result = nthUglyNumber(n);
		System.out.println(result);
	}

	private static int nthUglyNumber(int n) {
		   int i = 0;
			int num = 1;
			while (i != n) {
				if(num==1)
					i++;
	            if(num%2==0 || num%3==0 || num%5==0){
				
	            	if (isUglyNumber(num))
					i++;
			
	            }
	            num++;
			}
			return num - 1;
	}

	private static boolean isUglyNumber(int n) {
		while (n % 2 == 0)
			n /= 2;

		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			while (n % i == 0) {
				if (!factors.contains(i))
					return false;

				n /= i;
			}
		}

		if (n > 2)
			if (!factors.contains(n))
				return false;

		return true;
	}

}
