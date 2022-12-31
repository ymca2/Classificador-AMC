package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Edge {
	int u;
	int v;
	double weight;

	public Edge(int u, int v, double weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
}

class UnionFind {
	int[] parent;
	int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		if (x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		if (xRoot == yRoot) {
			return;
		}
		if (rank[xRoot] < rank[yRoot]) {
			parent[xRoot] = yRoot;
		} else if (rank[xRoot] > rank[yRoot]) {
			parent[yRoot] = xRoot;
		} else {
			parent[yRoot] = xRoot;
			rank[xRoot]++;
		}
	}
}

class Kruskal {
	
	public static List<Edge> buildMaximumSpanningTree(Graph weigthedGraph) {
		List<Edge> edges = new ArrayList<Edge>();
		int dimension = weigthedGraph.getDimension();
		for(int outerIndex=0; outerIndex<dimension; outerIndex ++) {
			 for(int innerIndex=0; innerIndex<dimension; innerIndex ++) {
				 if (outerIndex<innerIndex) {
					double value = weigthedGraph.get(outerIndex, innerIndex);
					if (value!=0) {
						Edge edge= new Edge(outerIndex,innerIndex,value);
						edges.add(edge);
					}
					 
				 }
			 }
		}
		return Kruskal.getMaximumSpanningTree(dimension, edges);
		
	}
	
	public static List<Edge> getMaximumSpanningTree(int n, List<Edge> edges) {
		
		// Sort the edges in non-decreasing order of their weight
		Collections.sort(edges, (e1, e2) -> -Double.compare(e1.weight, e2.weight));

		// Initialize the maximum spanning tree as an empty set of edges
		List<Edge> mst = new ArrayList<>();

		// Initialize the disjoint-set data structure
		UnionFind uf = new UnionFind(n);

		// Add the edges to the maximum spanning tree one at a time, in non-decreasing
		// order of their weight
		for (Edge edge : edges) {
			int u = edge.u;
			int v = edge.v;
			if (uf.find(u) != uf.find(v)) {
				mst.add(edge);
				uf.union(u, v);
			}
		}

		return mst;
	}
}
