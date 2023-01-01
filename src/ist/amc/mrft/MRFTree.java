package ist.amc.mrft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ist.amc.dataset.DataSet;
import ist.amc.maxspanningtree.Edge;

public class MRFTree {
	
	private MRFNode root;

	
	public MRFTree(MRFNode root) {
		this.root = root;
		
	}
	
	private static void buildTreeRec(MRFNode current, List<Edge> edges, List<Integer> visited, int rootNode, DataSet fiber) {
		List<Integer> adjacents = EdgeProcessor.getAdjacentNodes(edges, current.getId());
		visited.add(current.getId());
		List<Integer> nextOnes = EdgeProcessor.removeAll(adjacents, visited); 
		if (nextOnes.isEmpty()) return;
		for(int nextOne : nextOnes) {
			Container container = Container.buildContainer(current.getId(), nextOne, fiber, rootNode);
			MRFNode newNode = new MRFNode(nextOne, current.getId(), container);
			current.addChild(newNode);
			buildTreeRec(newNode,edges,visited, rootNode, fiber);
		}		
	}
	
	public static MRFTree buildTree(List<Edge> edges, DataSet fiber) {
		int rootNodeId = EdgeProcessor.getFirstNode(edges);
		MRFNode rootNode = new MRFNode(rootNodeId, MRFNode.NO_PARENT, null);
		MRFTree.buildTreeRec(rootNode, edges, new ArrayList<Integer>(), rootNodeId, fiber);
		MRFTree tree = new MRFTree(rootNode);//criamos uma arvore com um nó
		return tree;
	}
	
	public String toStringRec(MRFNode node, int dept) {
		String acc = String.format(" Node %d, parent %d, level %d \n", node.getId(),node.getParentId(),dept);
		if (node.getMrftProbabilities() != null)
			acc+=node.getMrftProbabilities().toString();
		if (node.hasChildren()) {

			for(MRFNode child : node.getChildren()) {
				acc += toStringRec(child, dept+1);
			}
		}
		return acc;
	}
	
	public String toString() {
		return toStringRec(root, 0);
		
	}
	
	private double calculateFiberProbabilityRec(MRFNode currentNode, int[] individual, double accProbability) {
		if (!currentNode.isRoot()) {
			Container container = currentNode.getMrftProbabilities();
			ContainerKey containerKey = ContainerKey.buildContainerKey(individual[currentNode.getParentId()], individual[currentNode.getId()]);
			System.out.println(container);
			System.out.println(containerKey);
			double x = container.get(containerKey);
			accProbability *= container.get(containerKey);	
			
		}
		
		if (currentNode.hasChildren())
			for(MRFNode child : currentNode.getChildren()) 
				accProbability =calculateFiberProbabilityRec(child, individual, accProbability);
			
		return accProbability;
		
	}
	
	public double calculateFiberProbability(int[] individual) {
		return calculateFiberProbabilityRec(this.root, individual, 1.0);
	}

	 
	  
	
	
	

}