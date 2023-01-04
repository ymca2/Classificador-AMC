package ist.amc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ist.amc.analysis.Matrix;
import ist.amc.classifier.Classifier;
import ist.amc.dataset.DataSet;

public class Manager {

	public static void main(String[] args) {

		/*
		 * List<Edge> edges = new ArrayList<Edge>(); Edge edge0 = new Edge(3,4,9);
		 * edges.add(edge0); Edge edge1 = new Edge(3,0,8); edges.add(edge1); Edge edge2
		 * = new Edge(3,6,7); edges.add(edge2); Edge edge3 = new Edge(3,1,6);
		 * edges.add(edge3); Edge edge4 = new Edge(0,5,5); edges.add(edge4); Edge edge5
		 * = new Edge(0,8,4); edges.add(edge5); Edge edge6 = new Edge(2,0,3);
		 * edges.add(edge6); Edge edge7 = new Edge(7,0,3); edges.add(edge7);
		 * 
		 * List<Edge> results = Kruskal.getMaximumSpanningTree(9,edges);
		 * 
		 * for(Edge item : results) {
		 * System.out.println(String.format("%d - %d - weight = %f", item.getU(),
		 * item.getV(), item.getWeight())); }
		 * 
		 * MRFTree tree = MRFTree.buildTree(results); System.out.println(tree);
		 */

			DataSet data = new DataSet("data/thyroid.csv");
			/*
			 * System.out.println(data);
			 * 
			 * DataSet fiber0 = DataSet.buildFiber(data, 0); DataSet fiber1 =
			 * DataSet.buildFiber(data, 1); System.out.println(fiber0);
			 * 
			 * int [] domain = data.getDomain(3);
			 * System.out.println(Arrays.toString(domain));
			 * 
			 * System.out.println(data.countDependences(3,4,3,2));
			 * 
			 * System.out.println(data.getLineNumber());
			 * 
			 * System.out.println(data.countVariable(1, 0));
			 * 
			 * 
			 * 
			 * Tensor t0 = Tensor.build(fiber0); Tensor t1 = Tensor.build(fiber1);
			 * System.out.println(t0);
			 * 
			 * Graph weigthedGraph0 = Graph.buildGraph(t0, fiber0); Graph weigthedGraph1 =
			 * Graph.buildGraph(t1, fiber1); System.out.println(weigthedGraph0);
			 * 
			 * List<Edge> results0 = Kruskal.buildMaximumSpanningTree(weigthedGraph0);
			 * List<Edge> results1 = Kruskal.buildMaximumSpanningTree(weigthedGraph1);
			 * for(Edge item : results0) {
			 * System.out.println(String.format("%d - %d - weight = %f", item.getU(),
			 * item.getV(), item.getWeight()));
			 * 
			 * }
			 * 
			 * MRFTree tree0 = MRFTree.buildTree(results0, fiber0); MRFTree tree1 =
			 * MRFTree.buildTree(results1, fiber1); System.out.println(tree0); int[]
			 * individual = {1,0,2,2,2,0,1,2,1,1};
			 */

			Classifier classifier = Classifier.buildClassifier(data);
			int match = 0, nonMatch = 0;
			for (int[] ArrayIndex : data.dataList) {
				int b = ArrayIndex [data.getColumnNumber()-1];
				if ( b == classifier.getClassifier(ArrayIndex)){
					match++;
				}
				else nonMatch++;
			}
			double matchesNormalize = (double) ((double)match/(double)(match+nonMatch))*100;
			double nonMatchesNormalize = (double)((double)nonMatch/(double)(match+nonMatch))*100;
			
			System.out.println((String.format("Matches %f \nNonMatches  %f",matchesNormalize,  nonMatchesNormalize)));
			
			/*Matrix confusionMatrix = new Matrix(dimension, dimension);
			for (int[] index : data.getEntries()) {
				// System.out.println(String.format("Best fit class %d", ));
				int result = classifier.getClassifier(index);
				confusionMatrix.inc(index[index.length-1],result);
			}
			System.out.println(confusionMatrix);*/
			
			
			

		
	}
}
