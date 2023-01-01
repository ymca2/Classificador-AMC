package ist.amc.mrft;

import java.util.HashMap;
import java.util.Set;

import ist.amc.dataset.DataSet;
import ist.amc.maxspanningtree.Edge;

public class Container {

	private HashMap<ContainerKey, Double> entries;

	public Container() {

		this.entries = new HashMap<ContainerKey, Double>();
	}
	

	public void set(ContainerKey key, double value) {
		this.entries.put(key, value);
	}

	public double get(ContainerKey key) {
		return this.entries.get(key);
	}
	
	public Set <ContainerKey> getList() {
		return this.entries.keySet();
	}
	
	
	public static Container buildContainer(int variableI, int variableJ, DataSet fiber, int root) {
		int[] domainI = fiber.getDomain(variableI);
		int[] domainJ = fiber.getDomain(variableJ);
		Container container = new Container();
		for (int I : domainI) {
			for (int J : domainJ) {
				double result;
				if (variableI == root|| variableJ == root) 
					result = CountProbability.calculateSpecialEdgeProbability(variableI, variableJ, I, J, fiber);
				else 
					result = CountProbability.calculateOrdinaryEdgeProbability(variableI, variableJ, I, J, fiber);
				
				ContainerKey containerKey = ContainerKey.buildContainerKey(I, J);
				container.set(containerKey, result);
			}
		}
		return container;
	}
	
	
	
	public String toString() {
		String result = "";
		for (ContainerKey key: this.entries.keySet()) {
		    result += String.format("...key = %d/%d, value = %f\n",key.getI(), key.getJ(),this.entries.get(key));
		}
		return result;
	}

}
