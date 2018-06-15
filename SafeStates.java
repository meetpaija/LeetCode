package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SafeStates {

	/*
	 * 
	 * In a directed graph, we start at some node and every turn, walk along a
	 * directed edge of the graph. If we reach a node that is terminal (that is, it
	 * has no outgoing directed edges), we stop.
	 * 
	 * Now, say our starting node is eventually safe if and only if we must
	 * eventually walk to a terminal node. More specifically, there exists a natural
	 * number K so that for any choice of where to walk, we must have stopped at a
	 * terminal node in less than K steps.
	 * 
	 * Which nodes are eventually safe? Return them as an array in sorted order.
	 * 
	 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the
	 * length of graph. The graph is given in the following form: graph[i] is a list
	 * of labels j such that (i, j) is a directed edge of the graph.
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]] Output: [2,4,5,6] Here is a
	 * diagram of the above graph.
	 * 
	 */

	public static Map<Integer, Boolean> data = new HashMap<>();

	public static void main(String[] args) {

		int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
		List<Integer> res = eventualSafeNodes(graph);
		System.out.println(res);
	}

	private static List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> result = new ArrayList<>();
		Graph g = new Graph(graph.length);

		for (int i = 0; i < graph.length; i++)
			for (int j = 0; j < graph[i].length; j++)
				addEdge(g, i, graph[i][j]);

		int V = g.adjListArray.length;

		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++)
			if (!isCyclicUtil(i, visited, recStack, g.adjListArray))
				result.add(i);

		return result;
	}

	private static boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, List<Integer>[] adj) {

		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = adj[i];

		for (Integer c : children)
			if (isCyclicUtil(c, visited, recStack, adj))
				return true;

		recStack[i] = false;

		return false;
	}

	static class Graph {
		int V;
		LinkedList<Integer> adjListArray[];

		Graph(int V) {
			this.V = V;
			adjListArray = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				adjListArray[i] = new LinkedList<>();
			}
		}
	}

	static void addEdge(Graph graph, int src, int dest) {
		graph.adjListArray[src].addFirst(dest);
	}
}
