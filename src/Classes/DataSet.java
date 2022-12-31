package Classes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DataSet implements Serializable {
	    private final long serialVersionUID = 1L;
	    
		private ArrayList<int []> dataList; //static - so ha um elemento para todo o programa
		
		DataSet() {
			this.dataList = new ArrayList<int []>();
		}
		
		DataSet(String csvFile)  {
			this.dataList = new ArrayList<int []>();
			String line;
			BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(csvFile));
						while((line = br.readLine()) != null) {
							dataList.add(convert(line)); // add no array list - os arrays sao tipos basicos. o arraylist é uma coleção que simula um array mas é dinamico. Crio um arraylist vazio mas faço add add add
						}
							br.close();	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
		}
		
		
		
		
		//temos um objeto que é  um arraylist que tem vários arrays de inteiros lá dentro
		

		// pega em line (em string) e com split pega nos separadores e faz um array dos valores divididos em virgulas
		//converto em arrays de string e depois faço arrys de Integers
		// a função recebe uma string, faz o slit faz com que fiqye array de strings, depois varro o array de strings e torno o em array de inteiros e guardo isso no array inteiro
		
		public int [] convert (String line) {
			String cvsSplitBy = ",";
			String[] strings     = line.split(cvsSplitBy);
			int[] stringToIntVec = new int[strings.length];
			for (int i = 0; i < strings.length; i++)
				stringToIntVec[i] = Integer.parseInt(strings[i]); //converte o valor de uma string num inteiro (Integer objeto, int tipo primitiovo)
			return stringToIntVec; 
		}
		
		public void add (int[] element) {
			dataList.add(element);
			
		}
		
		public ArrayList<int []> get () { //retorna o conteudo do objeto datalist para podemos usar nio static fiber
			return this.dataList;
		}
		
		public static DataSet buildFiber (DataSet dataSet, int classType) { //deve ser static porquye nao aktera o objeto so usa poara criar um novo
			DataSet dsFiber = new DataSet ();
			for(int[] index : dataSet.get()) { //usamos o dataset como parametro, recebe o dataset e a fibra que se pretende usar, isto é necessário para a fiber estar static
				if (index[index.length-1] == classType) {
					dsFiber.add(Arrays.copyOf(index,index.length-1));	
				}
			}
			return dsFiber;
			
		}
		
		public int [] getDomain(int position) {
			Set<Integer> domain = new HashSet<Integer>();  //SET - arraylist sem repetidos
			for(int[] index : dataList) {
				domain.add(index[position]);
			
			}
			return domain.stream().mapToInt(Number::intValue).toArray(); //number to int value transforma  num valor inteiro
			
		}
		
		public int countDependences(int positionI, int positionJ, int valorI, int valorJ) {
			int r = 0;
			for(int [] arrayIndex : dataList) {
				if (arrayIndex[positionI] == valorI && arrayIndex[positionJ] == valorJ) {
					r++;	
				}
			}
			return r;
		}
		
		public int getLineNumber() {
			return this.dataList.size();
		}
		
		public int getColumnNumber() {
			return this.dataList.get(0).length; //get vai biscar a 1a posição do arraylist
		}
			
		public int countVariable(int positionI, int valorI) {
			int r=0;
			for(int [] index : dataList) {
				if(index[positionI]==valorI) {
					r++;
				}
			}
			return r;
		}
		
	
		
		@Override
		public String toString() {
			String s="[";
			if (dataList.size()>0) s+=Arrays.toString(dataList.get(0));
			for (int i=1; i<dataList.size();i++)
				s+=","+Arrays.toString(dataList.get(i));
			s+="]";
				
			return "Size=" + dataList.size() + " Dataset = " + s;
		}
}

