package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SmallestRange {
	/*
	 * 632
	 * 
	 * You have k lists of sorted integers in ascending order. Find the smallest
	 * range that includes at least one number from each of the k lists. We define
	 * the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a ==
	 * d-c.
	 * 
	 * 
	 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]] Output: [20,24]
	 * Explanation:
	 * 
	 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. List 2: [0, 9, 12, 20],
	 * 20 is in range [20,24]. List 3: [5, 18, 22, 30], 22 is in range [20,24].
	 * 
	 * 
	 *
	 */
	public static void main(String[] args) {

		List<List<Integer>> input = Arrays.asList(Arrays.asList(4, 10, 15, 24, 26), Arrays.asList(0, 9, 12, 20),
				Arrays.asList(5, 18, 22, 30));
		int[] result = smallestRange(input);
		System.out.println(result[0] + " " + result[1]);
	}

	private static int[] smallestRange(List<List<Integer>> input) {

		Map<Integer, List<Integer>> elements = new TreeMap<>();
		Map<Integer, Integer[]> indices = new HashMap<>();

		for (int i = 0; i < input.size(); i++) {

			Integer firstValue = input.get(i).get(0);
			if (firstValue != null) {
				if (elements.containsKey(firstValue)) {
					List<Integer> values = elements.get(firstValue);
					values.add(i);
					elements.put(firstValue, values);
				} else
					elements.put(firstValue, new ArrayList<>(Arrays.asList(i)));
			}
			indices.put(i, new Integer[] {0,input.get(i).size()});
		}

		Map<Integer, Integer[]> result = new TreeMap<>();

		if (!elements.isEmpty() && elements.size() <= input.size()) {
			int firstElement = (int) elements.keySet().toArray()[0];
			int lastElement = (int) elements.keySet().toArray()[elements.size() - 1];

			result.put(lastElement - firstElement, new Integer[] { firstElement, lastElement });
			List<Integer> connectedLists = elements.get(firstElement);
			int listNumber = connectedLists.get(0);

			if (connectedLists.size() == 1) {
				elements.remove(firstElement);
			} else {
				connectedLists.remove(0);
				elements.put(firstElement, connectedLists);
			}

			Integer[] indexWithSize = indices.get(listNumber);

			while (indexWithSize[0] + 1 < indexWithSize[1]) {
				indexWithSize[0]++;
				int newElement = input.get(listNumber).get(indexWithSize[0]);
				indices.put(listNumber, indexWithSize);

				if (elements.containsKey(newElement)) {
					List<Integer> values = elements.get(newElement);
					values.add(listNumber);
					elements.put(newElement, values);
				} else {
					elements.put(newElement, new ArrayList<>(Arrays.asList(listNumber)));
				}

				if (!elements.isEmpty() && elements.size() <= input.size()) {
					firstElement = (int) elements.keySet().toArray()[0];
					lastElement = (int) elements.keySet().toArray()[elements.size() - 1];
					int diff = lastElement - firstElement;

					if (result.containsKey(diff)) {
						if (firstElement - result.get(diff)[0] < lastElement - result.get(diff)[1])
							result.put(lastElement - firstElement, new Integer[] { firstElement, lastElement });
						else if (firstElement - result.get(diff)[0] == lastElement - result.get(diff)[1])
							if (result.get(lastElement - firstElement)[0] > firstElement)
								result.put(lastElement - firstElement, new Integer[] { firstElement, lastElement });
					} else
						result.put(diff, new Integer[] { firstElement, lastElement });

					connectedLists = elements.get(firstElement);
					listNumber = connectedLists.get(0);

					if (connectedLists.size() == 1)
						elements.remove(firstElement);
					else {
						connectedLists.remove(0);
						elements.put(firstElement, connectedLists);
					}

					indexWithSize = indices.get(listNumber);
				}
			}

		}

		// result.keySet().forEach(x -> {
		// System.out.println(x + " -> " + result.get(x)[0] + " " + result.get(x)[1]);
		// });

		Integer[] res = result.get(result.keySet().toArray()[0]);
		int[] finalResult = { res[0], res[1] };

		return finalResult;
	}

}
