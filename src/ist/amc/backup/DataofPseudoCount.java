package ist.amc.backup;

import java.util.Arrays;

import ist.amc.dataset.DataSet;

public class DataofPseudoCount {
	
	private int line;
	private int column;
	private double [][] dataofPseudoCount;
	
	public DataofPseudoCount (int line, int column) { 
		this.line = line;
		this.column = column;
		this.dataofPseudoCount = new double [line][column]; 
	}

	public void set(int line, int column, int value) {
		this.dataofPseudoCount [line][column] = value;
	}
	
	public double get(int line, int column) {
		return this.dataofPseudoCount[line][column];
	}
	
	public static DataofPseudoCount buildDataofPseudoCount(int variableI, int variableJ, DataSet fiber) {
		int [] domainI = fiber.getDomain(variableI);
		int [] domainJ = fiber.getDomain(variableJ);
		DataofPseudoCount dataofPseudoCount = new DataofPseudoCount(domainI.length,domainJ.length);
		double delta = 0.2;
		for(int I : domainI) {
			for(int J : domainJ) {
				
				
			}
		}
		return  dataofPseudoCount;
	}

}
