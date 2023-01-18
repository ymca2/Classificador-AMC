package ist.amc;

import ist.amc.analysis.Matrix;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;*/


import ist.amc.classifier.Classifier;
import ist.amc.dataset.DataSet;

public class Manager {

	public static void main(String[] args) {


	     	DataSet data = DataSet.buildDataSet("data/satimage.csv");
			
			
			 int[]individual = {6,11,9,6,8,7,10,7,5,9,9,5,7,9,9,6,8,10,9,5,7,10,6,6,5,9,10,4,6,8,8,4,6,9,6,3};			

			Classifier classifier = Classifier.buildClassifier(data);
			
			System.out.println((String.format("Classifier Result: %d",classifier.getClassifier(individual))));
			System.out.println("");
			int dimension = data.getDomainLength(data.getColumnNumber()-1);
			Matrix confusionMatrix = new Matrix(dimension, dimension);
			for (int[] index : data.getEntries()) {
				int result = classifier.getClassifier(index);
				confusionMatrix.inc(index[index.length-1],result);
			}
			System.out.println(confusionMatrix);
			
			int truePositive = 0;
			for(int i = 0; i<dimension; i++) {
				truePositive += confusionMatrix.get(i, i);
			}
			System.out.println((String.format("True Positives: %d",truePositive)));
			
			
			double accuracy = ((double)(truePositive)/data.getLineNumber())*100;
			
			System.out.println((String.format("Accuracy %f",accuracy)));

		
	}
}
