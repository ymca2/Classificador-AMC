package ist.amc.maxspanningtree;

import java.util.Arrays;

import ist.amc.dataset.DataSet;
import ist.amc.tensor.Container;
import ist.amc.tensor.ContainerKey;
import ist.amc.tensor.Tensor;

public class Graph {

	private int dimension;
	private double graph[][];

	public Graph(int dim) { // escolher a dimensão do grafo no construtor
		super();
		this.dimension = dim;
		this.graph = new double[dim][dim]; // aloca a memória necessária
	}

	@Override
	public String toString() {
		return "Graph [dim=" + dimension + ", graph=" + Arrays.deepToString(graph) + "]";
	}

	public void set(int line, int column, double value) {
		this.graph[line][column] = value;
	}

	public double get(int line, int column) {
		return this.graph[line][column];
	}

	public int getDimension() {
		return this.dimension;
	}

	public static double calculateMutualInf(int variableI, int variableJ, Tensor tensor, int m, DataSet fiber) {
		double result = 0.0;
		Container container = tensor.get(variableI, variableJ);

		for (ContainerKey index : container.getList()) {
			int count = container.get(index);
			double pr_xi_xj = (double) count / m;
			double pr_xi = (double) fiber.countVariable(variableI, index.getI()) / m;
			double pr_xj = (double) fiber.countVariable(variableJ, index.getJ()) / m;
			double I = pr_xi_xj < 10E-10 ? 0.0 : pr_xi_xj * Math.log(pr_xi_xj / (pr_xi * pr_xj));
			result += I;
		}
		return result;
	}

	public static Graph buildGraph(Tensor tensor, DataSet fiber) {
		int graphDimension = fiber.getColumnNumber();
		int m = fiber.getLineNumber();
		Graph weigthedGraph = new Graph(graphDimension);

		for (int outerIndex = 0; outerIndex < graphDimension; outerIndex++) {
			for (int innerIndex = 0; innerIndex < graphDimension; innerIndex++) {
				if (outerIndex < innerIndex) {
					double result = Graph.calculateMutualInf(outerIndex, innerIndex, tensor, m, fiber);
					weigthedGraph.set(outerIndex, innerIndex, result);

				}
			}
		}

		return weigthedGraph;
	}

}
