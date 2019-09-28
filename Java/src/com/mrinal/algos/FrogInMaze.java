package com.mrinal.algos;

import java.util.*;

public class FrogInMaze {
	static float totalPropability = 0;
	static List<Integer> path = new ArrayList<>();
	static class Graph {
		List<List<Integer>> adj = new ArrayList<>();

		public Graph(int size) {
			for (int i = 0; i < size; i++) {
				adj.add(new ArrayList<>());
			}
		}

		public void addEdge(Integer v1, Integer v2) {
			adj.get(v1).add(v2);
		}

	}

	private static final Scanner scanner = new Scanner(System.in);
	Graph graph = null;

	public static void main(String[] args) {
		String[] nmk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nmk[0]);

		int m = Integer.parseInt(nmk[1]);

		int k = Integer.parseInt(nmk[2]);

		List<List<Integer>> paths = new ArrayList<>();

		List<Integer> mines = new ArrayList<>();


		int exit = -1;
		int A = -1;
		List<Boolean> visited = new ArrayList<>(m * n);
		for (int i = 0; i < m * n; i++) {
			visited.add(false);
		}
		List<String> rows = new ArrayList<>();
		Graph graph = new Graph(n * m);
		for (int nItr = 0; nItr < n; nItr++) {
			String row = scanner.nextLine();
			rows.add(row);
		}
		for (int nItr = 0; nItr < n; nItr++) {
			String row = rows.get(nItr);
			for (int mItr = 0; mItr < m; mItr++) {
				if (row.charAt(mItr) == 'O' || row.charAt(mItr) == '%' || row.charAt(mItr) == '*'
						|| row.charAt(mItr) == 'A') {
					// add edges
					int v1 = nItr * m + mItr;
					if (row.charAt(mItr) == '%') {
						exit = v1;
					}
					if (row.charAt(mItr) == 'A') {
						A = v1;
					}
					if (row.charAt(mItr) == '*') {
						mines.add(v1);
					}
					// find right edge
					if ((mItr + 1 < m) && (row.charAt(mItr + 1) == 'O' || row.charAt(mItr + 1) == '%'
							|| row.charAt(mItr + 1) == '*' || row.charAt(mItr + 1) == 'A')) {
						graph.addEdge(v1, v1 + 1);
						//if (row.charAt(mItr) == '%') {
							//graph.addEdge(v1 + 1, v1);
						//}
					}

					/*
					 * graph.addEdge(Character.getNumericValue(row.charAt(mItr)),
					 * Character.getNumericValue(row.charAt(mItr + 1)));
					 */

					// find left edge
					if ((mItr - 1 >= 0) && ((row.charAt(mItr - 1) == 'O') || row.charAt(mItr - 1) == '%'
							|| row.charAt(mItr - 1) == '*' || row.charAt(mItr - 1) == 'A')) {
						graph.addEdge(v1, v1 - 1);
						//if (row.charAt(mItr) == '%') {
							//graph.addEdge(v1 - 1, v1);
						//}
					}
					/*
					 * graph.addEdge(Character.getNumericValue(row.charAt(mItr)),
					 * Character.getNumericValue(row.charAt(mItr - 1)));
					 */

					// get next row edge
					if ((nItr + 1 < n) && (rows.get(nItr + 1).charAt(mItr) == 'O'
							|| rows.get(nItr + 1).charAt(mItr) == '%' || rows.get(nItr + 1).charAt(mItr) == '*'
							|| rows.get(nItr + 1).charAt(mItr) == 'A')) {
						int v2 = (nItr + 1) * m + mItr;
						if (v1 != v2) {
							graph.addEdge(v1, v2);
							//if (row.charAt(mItr) == '%') {
								//graph.addEdge(v2, v1);
							//}
						}
					}
					/*
					 * graph.addEdge(Character.getNumericValue(row.charAt(mItr)),
					 * Character.getNumericValue(rows.get(nItr + 1).charAt(mItr)));
					 */

					// get previous row edge
					if ((nItr - 1 >= 0) && (rows.get(nItr - 1).charAt(mItr) == 'O'
							|| rows.get(nItr - 1).charAt(mItr) == '%' || rows.get(nItr - 1).charAt(mItr) == '*'
							|| rows.get(nItr - 1).charAt(mItr) == 'A')) {
						int v2 = (nItr - 1) * m + mItr;
						if (v1 != v2) {
							graph.addEdge(v1, v2);
							//if (row.charAt(mItr) == '%') {
								//graph.addEdge(v2, v1);
							//}
						}
						/*
						 * graph.addEdge(Character.getNumericValue(row.charAt(mItr)),
						 * Character.getNumericValue(rows.get(nItr - 1).charAt(mItr)));
						 */
					}

				}
			}
		}
		for (int kItr = 0; kItr < k; kItr++) {
			String[] i1J1I2J2 = scanner.nextLine().split(" ");

			int i1 = Integer.parseInt(i1J1I2J2[0]);

			int j1 = Integer.parseInt(i1J1I2J2[1]);

			int i2 = Integer.parseInt(i1J1I2J2[2]);

			int j2 = Integer.parseInt(i1J1I2J2[3]);
			if (i1 >= 1 && i1 <= n && i2 >= 1 && i2 <= n && j1 >= 1 && j1 <= m && j2 >= 1 && j2 <= m) {

				int v1 = (i1 - 1) * m + j1 - 1;
				int v2 = (i2 - 1) * m + j2 - 1;
				graph.addEdge(v1, v2);
				graph.addEdge(v2, v1);
			}
		}
		// Add edges to Exit %
		dfs(A, exit, visited, graph, 1, mines);

		System.out.println(FrogInMaze.totalPropability);

		// Write Your Code Here
		scanner.close();
	}

	static void dfs(int v, int exit, List<Boolean> visited, Graph graph, float preProbability, List<Integer> mines) {
		path.add(v);
		if (v == exit) {
			FrogInMaze.totalPropability = FrogInMaze.totalPropability + preProbability;
			System.out.println("Path: "+ path + "Probability: "+ FrogInMaze.totalPropability + " Path probability: "+ preProbability);
		} else {
			visited.set(v, true);
			List<Integer> adjecent = graph.adj.get(v);
			int unvisitedAdjSize = adjecent.size();
			for (int k = 0; k < visited.size(); k++) {
				if (visited.get(k) == true) {
					if (adjecent.contains(k)) {
						unvisitedAdjSize--;
					}
				}

			}
			float newProbability = unvisitedAdjSize > 0 ? (float) (1.0 / (unvisitedAdjSize * 1.0)) : 0;
			for (int i = 0; i < adjecent.size(); i++) {
				// for (int vertex : graph.adj.get(v)) {

				if (visited.get(graph.adj.get(v).get(i)) == false && !mines.contains(graph.adj.get(v).get(i))) {
					visited.set(graph.adj.get(v).get(i), true);
					dfs(graph.adj.get(v).get(i), exit, visited, graph, preProbability * newProbability, mines);
				}
			}
		}
		visited.set(v, false);
		path.remove(path.size()-1);
	}
}
