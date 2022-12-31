package Classes;

import java.util.HashMap;

public class Tensor {
	
	private HashMap<String, Container> mapear;
	
	 //hashmaps entre 2 objetos, temos que ter um objeto que nos da a relação engre as duas col

	public Tensor() {
		
		this.mapear = new HashMap<String, Container>();

	}
	
	private static String buildId(int line, int column) { // pode ser static porque nao faz nada ao tensor, o comportamento vai ser igual para todas as instancias da classe tensor
		return String.format("%d-%d", line, column);
	}
	
	//recebe uma matriz e a posição e vai escrever
	
	public void set(int line, int column, Container matrix) { //maiuscula def da classe min instancia
		String matrixId = Tensor.buildId(line, column);
		this.mapear.put(matrixId, matrix);
	}
	
	 public Container get(int line, int column) {
		 String matrixId = Tensor.buildId(line, column);
		 return this.mapear.get(matrixId);
	 }
	 
	 //tensor ja tem a forma de escrever matrizes e ler matrizes
	 
	 public static Tensor build (DataSet fiber) {
		 Tensor tensor = new Tensor (); // crias um novo la dentro porque ´static
		 int columnSize = fiber.getColumnNumber();
		 for(int outerIndex=0; outerIndex<columnSize; outerIndex ++) {
			 for(int innerIndex=0; innerIndex<columnSize; innerIndex ++) {
				 if (outerIndex<innerIndex) {
					 Container container = Container.buildContainer(outerIndex, innerIndex, fiber);
					 tensor.set(outerIndex, innerIndex, container);
				 }
			 }
		 }
		 return tensor;
		 
	 }
	 
	 public String toString() {
			String result = "";
			for (String key: this.mapear.keySet()) {
				Container value = this.mapear.get(key);
			    result += String.format("xi->xj = %s\n", key);
			    result += value.toString();
			}
			return result;
		}
	 
	 
	
}
