package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {
	
	public static void main(String[] args) {
		
		/*List<Edge> edges = new ArrayList<Edge>();
		Edge edge0 = new Edge(0,3,1);
		edges.add(edge0);
		Edge edge1 = new Edge(0,1,4);
		edges.add(edge1);
		Edge edge2 = new Edge(0,2,2);
		edges.add(edge2);
		Edge edge3 = new Edge(3,1,1);
		edges.add(edge3);
		Edge edge4 = new Edge(1,2,2);
		edges.add(edge4);
		Edge edge5 = new Edge(2,3,10);
		edges.add(edge5);
		
		List<Edge> results = Kruskal.getMaximumSpanningTree(4,edges);
		
		for(Edge item : results) {
			System.out.println(String.format("%d - %d - weight = %d", item.u, item.v, item.weight));
		}*/
		
		
		
		
		
		
		DataSet d = new DataSet("bcancer.csv");
		System.out.println(d);
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("d.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(d);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		 	FileInputStream fileIn;
			try {
				fileIn = new FileInputStream("d.ser");
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	   		 
	            DataSet data =  (DataSet) objectIn.readObject();
	 
	            System.out.println("The Object has been read from the file");
	            System.out.println(data);
	            objectIn.close();
				
	            System.out.println(data);
	            
	            DataSet fiber0 = DataSet.buildFiber(data, 0);	
	            System.out.println(fiber0);
	            
	            int [] domain = data.getDomain(3);
	            System.out.println(Arrays.toString(domain));
	            
	            System.out.println(data.countDependences(3,4,3,2));
	            
	            System.out.println(data.getLineNumber());
	            
	            System.out.println(data.countVariable(1, 0));
	           
	            
	            
	            Tensor t = Tensor.build(fiber0);
	            System.out.println(t);
	            
	            Graph weigthedGraph = Graph.buildGraph(t, fiber0);
	            System.out.println(weigthedGraph);
	            
	            List<Edge> results = Kruskal.buildMaximumSpanningTree(weigthedGraph);
	            for(Edge item : results) {
	    			System.out.println(String.format("%d - %d - weight = %f", item.u, item.v, item.weight));
	            
	            }
	            
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
