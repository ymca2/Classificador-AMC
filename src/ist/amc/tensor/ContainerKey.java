package ist.amc.tensor;

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

	public static ContainerKey buildContainerKey(Integer i, Integer j) {
		ContainerKey containerKey = new ContainerKey(i, j);
		return containerKey;

	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContainerKey containerKey = (ContainerKey) o;
		return (this.i == containerKey.i && this.j == containerKey.j);
	}

	@Override
	public int hashCode() {
		return this.i * 10000 + this.j;
	}

}
