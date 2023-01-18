package ist.amc.classifier;

import java.util.ArrayList;
import java.util.List;
import ist.amc.dataset.DataSet;
import ist.amc.maxspanningtree.Edge;
import ist.amc.maxspanningtree.Graph;
import ist.amc.maxspanningtree.Kruskal;
import ist.amc.mrft.MRFTree;
import ist.amc.tensor.Tensor;

public class Classifier {

	private List<MRFTree> mrfts;
	private List<Integer> classifierDomain;

	public Classifier() {
		this.mrfts = new ArrayList<MRFTree>();
		this.classifierDomain = new ArrayList<Integer>();
	}

	private MRFTree getTree(int classType) {
		for (MRFTree index : this.mrfts)
			if (index.getId() == classType)
				return index;
		return null;
	}

	private void addTree(MRFTree mrftree) {
		this.mrfts.add(mrftree);
		this.classifierDomain.add(mrftree.getId());
	}

	public static Classifier buildClassifier(DataSet raw) {
		Classifier classifier = new Classifier();
		int[] domainClass = raw.getDomain(raw.getColumnNumber() - 1);
		for (int index : domainClass) {
			DataSet fiber = DataSet.buildFiber(raw, index);
			Tensor tensor = Tensor.build(fiber);
			Graph weigthedGraph = Graph.buildGraph(tensor, fiber);
			List<Edge> edges = Kruskal.buildMaximumSpanningTree(weigthedGraph);
			MRFTree tree = MRFTree.buildTree(edges, fiber);
			classifier.addTree(tree);
		}
		return classifier;
	}

	public int getClassifier(int[] individual) {
		double maxProbability = 0.0;
		int bestClass = -1;
		for (Integer index : this.classifierDomain) {
			MRFTree mrftree = getTree(index);
			double currentProbability = mrftree.calculateFiberProbability(individual, index);
			if (currentProbability > maxProbability) {
				maxProbability = currentProbability;
				bestClass = index;
			}
		}
		return bestClass;
	}

}
