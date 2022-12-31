package Classes;

import java.util.Arrays;

public class Matrix {
	
	private int lines;
	private int columns;
	private int [][] matrix;
		
	public Matrix (int lines, int columns) { 
		this.lines = lines;
		this.columns = columns;
		this.matrix = new int [lines][columns]; 
	}
	
	@Override
	public String toString() {
		String message = Arrays.deepToString(matrix);
		return String.format("Matrix with dimension %d x %d, %s", lines, columns, message);
	}

	public void set(int line, int column, int value) {
		this.matrix [line][column] = value;
	}
	
	public int get(int line, int column) {
		return this.matrix[line][column];
	}
	
	public static Matrix buildMatrix(int variableI, int variableJ, DataSet fiber) {
		int [] domainI = fiber.getDomain(variableI);
		int [] domainJ = fiber.getDomain(variableJ);
		Matrix matrix = new Matrix(domainI.length,domainJ.length);
		int M = fiber.getLineNumber();
		for(int I : domainI) {
			for(int J : domainJ) {
				int result = fiber.countDependences(variableI, variableJ, I, J);
				matrix.set(I, J, result);
			}
		}
		return matrix;
	}

}