package ist.amc.mrft;

import java.util.ArrayList;
import java.util.List;

public class MRFNode {
	
	private int id;
	private int parent;
	private Container mrftProbabilities;
	private List<MRFNode> children;
	
	
	public MRFNode(int id, int parent, Container container) {
		this.id = id;
		this.parent = parent;
		this.children = new ArrayList<MRFNode>(); // guardar memória para os children, temos que dizer qual é a classe que vamos trabalkhar com o <>
		this.mrftProbabilities = container;
	}
	
	public void addChild(MRFNode child) {
		this.children.add(child);
	}
	
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getParentId() {
		return this.parent;
	}
	
	public List<MRFNode> getChildren() {
		return this.children; //retoirna a lista de filhos que já existem
	}

	public Container getMrftProbabilities() {
		return mrftProbabilities;
	}
	
	
	
	

}
