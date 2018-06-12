package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. 

For example if routes[0] = [1, 5, 7], 
this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T.

Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.


Example:

Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6

Output: 2

Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.


*/

public class BusRoutes {

	public static void main(String[] args) {
		int routes[][] = { { 1, 2, 7 }, { 6, 7, 8 } };
		int S = 2;
		int T = 8;
		System.out.println(numBusesToDestination(routes, S, T));
	}

	public static int numBusesToDestination(int[][] routes, int S, int T) {
		if (S == T)
			return 0;

		Map<Integer, Integer> indexValueMap = new HashMap<>();
		int k = 0;
		for (int i = 0; i < routes.length; i++) {

			boolean isSourceExist = false;
			boolean isDestinationExist = false;
			for (int j = 0; j < routes[i].length; j++) {

				if (routes[i][j] == S)
					isSourceExist = true;
				if (routes[i][j] == T)
					isDestinationExist = true;
				if (!indexValueMap.containsKey(routes[i][j]))
					indexValueMap.put(routes[i][j], k++);

			}
			if (isDestinationExist && isSourceExist)
				return 1;
		}

		System.out.println(indexValueMap);

		int routeTable[][] = new int[indexValueMap.size()][indexValueMap.size()];

		int indexOfSource = indexValueMap.get(S);
		int indexOfDestination = indexValueMap.get(T);

		for (int i = 0; i < routeTable.length; i++) {
			for (int j = 0; j <= i; j++) {
				routeTable[i][j] = -1;
				routeTable[j][i] = -1;
				if (i == j)
					routeTable[i][i] = 0;
			}
		}

		// entry of 1
		for (int l = 0; l < routes.length; l++) {
			for (int i = 0; i < routes[l].length; i++) {
				int j = i + 1;
				int len = routes[l].length;
				while (j < len) {
					routeTable[indexValueMap.get(routes[l][i])][indexValueMap.get(routes[l][j])] = 1;
					routeTable[indexValueMap.get(routes[l][j])][indexValueMap.get(routes[l][i])] = 1;
					j++;
				}
			}
		}

		// entry of 1+
		while (true) {
			int routeTableCopy[][] = routeTable;
			for (int i = 0; i < routeTable.length; i++) {
				int index = 0;
				List<Integer> list = new ArrayList<>();

				while (index != routeTable.length) {
					if (routeTable[i][index] == 1)
						list.add(index);
					index++;
				}
				for (int j = 0; j <= i; j++) {

					if (routeTable[i][j] == -1) {

						for (Integer integer : list) {
							if (routeTable[integer][j] != -1) {
								if (routeTable[i][j] == -1)
									routeTable[i][j] = routeTable[integer][j] + 1;
								else
									routeTable[i][j] = Math.min(routeTable[i][j], routeTable[integer][j] + 1);
								routeTable[j][i] = routeTable[i][j];
							}
						}
						if ((i == indexOfSource && j == indexOfDestination)
								|| (i == indexOfDestination && j == indexOfSource))
							return routeTable[indexOfSource][indexOfDestination];
					}
				}
			}

			boolean isEquals = true;
			boolean isEntryOfMinusOne = false;

			for (int i = 0; i < routeTable.length; i++) {
				for (int j = 0; j < routeTable.length; j++) {
					if (routeTable[i][j] == -1)
						isEntryOfMinusOne = true;
					if (routeTable[i][j] != routeTableCopy[i][j])
						isEquals = false;
				}
			}

			if (!isEntryOfMinusOne)
				break;

			if (isEquals)
				break;

		}

		// for (int i = 0; i < routeTable.length; i++) {
		//
		// for (int j = 0; j < routeTable[i].length; j++) {
		// System.out.print(routeTable[i][j]);
		// }
		// System.out.println();
		// }

		return routeTable[indexOfSource][indexOfDestination];
	}
}
