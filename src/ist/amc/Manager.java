package ist.amc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ist.amc.dataset.DataSet;
import ist.amc.maxspanningtree.Edge;
import ist.amc.maxspanningtree.Graph;
import ist.amc.maxspanningtree.Kruskal;
import ist.amc.mrft.MRFTree;
import ist.amc.tensor.Tensor;

public class Manager {
	
	public static void main(String[] args) {
		
		/*List<Edge> edges = new ArrayList<Edge>();
		Edge edge0 = new Edge(3,4,9);
		edges.add(edge0);
		Edge edge1 = new Edge(3,0,8);
		edges.add(edge1);
		Edge edge2 = new Edge(3,6,7);
		edges.add(edge2);
		Edge edge3 = new Edge(3,1,6);
		edges.add(edge3);
		Edge edge4 = new Edge(0,5,5);
		edges.add(edge4);
		Edge edge5 = new Edge(0,8,4);
		edges.add(edge5);
		Edge edge6 = new Edge(2,0,3);
		edges.add(edge6);
		Edge edge7 = new Edge(7,0,3);
		edges.add(edge7);
		
		List<Edge> results = Kruskal.getMaximumSpanningTree(9,edges);
		
		for(Edge item : results) {
			System.out.println(String.format("%d - %d - weight = %f", item.getU(), item.getV(), item.getWeight()));
		}
		
		MRFTree tree = MRFTree.buildTree(results);
		System.out.println(tree);*/
		
		
		
		
		
		
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
	            DataSet fiber1 = DataSet.buildFiber(data, 1);
	            System.out.println(fiber0);
	            
	            int [] domain = data.getDomain(3);
	            System.out.println(Arrays.toString(domain));
	            
	            System.out.println(data.countDependences(3,4,3,2));
	            
	            System.out.println(data.getLineNumber());
	            
	            System.out.println(data.countVariable(1, 0));
	           
	            
	            
	            Tensor t0 = Tensor.build(fiber0);
	            Tensor t1 = Tensor.build(fiber1);
	            System.out.println(t0);
	            
	            Graph weigthedGraph0 = Graph.buildGraph(t0, fiber0);
	            Graph weigthedGraph1 = Graph.buildGraph(t1, fiber1);
	            System.out.println(weigthedGraph0);
	            
	            List<Edge> results0 = Kruskal.buildMaximumSpanningTree(weigthedGraph0);
	            List<Edge> results1 = Kruskal.buildMaximumSpanningTree(weigthedGraph1);
	            for(Edge item : results0) {
	    			System.out.println(String.format("%d - %d - weight = %f", item.getU(), item.getV(), item.getWeight()));
	            
	            }	
	    		
	            MRFTree tree0 = MRFTree.buildTree(results0, fiber0);
	            MRFTree tree1 = MRFTree.buildTree(results1, fiber1);
	    		System.out.println(tree0);
	    		
	    		int[] individual = {1,0,2,2,1,1,1,2,1,0};
	    		System.out.println(tree0.calculateFiberProbability(individual));
	    		System.out.println(tree1.calculateFiberProbability(individual));
	            
	            
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
