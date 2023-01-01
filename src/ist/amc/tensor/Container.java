package ist.amc.tensor;

import java.util.HashMap;
import java.util.Set;

import ist.amc.dataset.DataSet;

public class Container {

	private HashMap<ContainerKey, Integer> entries;

	public Container() {

		this.entries = new HashMap<ContainerKey, Integer>();
	}
	

	public void set(ContainerKey key, int value) {
		this.entries.put(key, value);
	}

	public int get(ContainerKey key) {
		return this.entries.get(key);
	}
	
	public Set <ContainerKey> getList() {
		return this.entries.keySet();
	}
	
	
	public static Container buildContainer(int variableI, int variableJ, DataSet fiber) {
		int[] domainI = fiber.getDomain(variableI);
		int[] domainJ = fiber.getDomain(variableJ);
		Container container = new Container();
		for (int I : domainI) {
			for (int J : domainJ) {
				
				int result = fiber.countDependences(variableI, variableJ, I, J);
				ContainerKey containerKey = ContainerKey.buildContainerKey(I, J);
				container.set(containerKey, result);
			}
		}
		return container;
	}
	

	
	public String toString() {
		String result = "";
		for (ContainerKey key: this.entries.keySet()) {
			int value = this.entries.get(key);
		    result += String.format("key = %d/%d, value = %d\n",key.getI(), key.getJ(),value);
		}
		return result;
	}

}
