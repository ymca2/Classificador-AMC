package ist.amc.mrft;

public class ContainerKey {
	
	private Integer i;
	
	private Integer j;

	
	public ContainerKey(Integer i, Integer j) {
		this.i = i;
		this.j = j;
	}

	
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}
	
	 public static ContainerKey buildContainerKey (Integer i, Integer j) {
		 ContainerKey containerKey = new ContainerKey (i,j); 
		 return containerKey;
		 
	 }
	

}
