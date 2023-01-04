package ist.amc.mrft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ist.amc.maxspanningtree.Edge;

public class EdgeProcessor {

	private static boolean IsInEdge(int node, Edge edge) {
		if (edge.getU() == node || edge.getV() == node)
			return true;
		return false;
	}

	private static Integer getPairNode(int node, Edge edge) {
		if (edge.getU() == node)
			return edge.getV();
		return edge.getU();
	}

	public static List<Integer> getAdjacentNodes(List<Edge> edges, int node) {

		List<Integer> adjacents = new ArrayList<Integer>();

		for (Edge edge : edges)
			if (EdgeProcessor.IsInEdge(node, edge))
				adjacents.add(EdgeProcessor.getPairNode(node, edge));

		return adjacents;
	}

	public static List<Integer> removeAll(List<Integer> list1, List<Integer> list2) {
		return (list1.stream().filter(e -> !list2.contains(e)).collect(Collectors.toList()));
	}

	private static int getNodeDeptRec(List<Edge> edges, int node, List<Integer> visited, int dept) {

		List<Integer> adjacents = EdgeProcessor.getAdjacentNodes(edges, node);
		visited.add(node);

		// remove from adjacents, the ones already visited
		List<Integer> nextOnes = EdgeProcessor.removeAll(adjacents, visited);

		if (nextOnes.size() == 0)
			return dept;

		List<Integer> depts = new ArrayList<Integer>();
		for (int nextOne : nextOnes)
			depts.add(EdgeProcessor.getNodeDeptRec(edges, nextOne, visited, dept + 1));
		return Collections.max(depts);
	}

	public static int getNodeDept(List<Edge> edges, int node) {
		return EdgeProcessor.getNodeDeptRec(edges, node, new ArrayList<Integer>(), 1);
	}

	public static int getFirstNode(List<Edge> edges) {
		Edge mostExpensiveEdge = edges.get(0);
		int uDept = EdgeProcessor.getNodeDept(edges, mostExpensiveEdge.getU());
		int vDept = EdgeProcessor.getNodeDept(edges, mostExpensiveEdge.getV());
		return (uDept > vDept) ? uDept : vDept;
	}

}