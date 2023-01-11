package ist.amc.dataset;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataSet implements Serializable {

	private static final long serialVersionUID = -2955258415103571166L;
	public ArrayList<int[]> dataList; // static - so ha um elemento para todo o programa
	private int id;
	public String message;

	public DataSet() {
		this.dataList = new ArrayList<int[]>();
	}

	
	
	
	public static DataSet buildDataSetold(String csvFile) {
		String line;
		BufferedReader br;
		DataSet dataset = new DataSet();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				dataset.dataList.add(convert(line)); // add no array list - os arrays sao tipos basicos. o arraylist é uma
												// coleção que simula um array mas é dinamico. Crio um arraylist vazio
												// mas faço add add add
			}
			br.close();
		} catch (Exception e) {		
			e.printStackTrace();
			//return e.getMessage();
			dataset.message= e.getLocalizedMessage();
		}
		
		return dataset;
	}
	
	
	public static DataSet buildDataSet(String filename) {
		String line;
		DataSet dataset = new DataSet();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream, StandardCharsets.UTF_8));
			while ((line = bufferedReader.readLine()) != null) {
			    dataset.dataList.add(DataSet.convert(line));
			}
			bufferedReader.close();
			dataInputStream.close();
			fileInputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			dataset.message= e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dataset.message= e.getMessage();
			
		}
		
		return dataset;
	}


	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public List<int[]> getEntries() {
		return this.dataList;
	}

	public static int[] convert(String line) {
		String cvsSplitBy = ",";
		String[] strings = line.split(cvsSplitBy);
		int[] stringToIntVec = new int[strings.length];
		for (int i = 0; i < strings.length; i++)
			stringToIntVec[i] = Integer.parseInt(strings[i]); // converte o valor de uma string num inteiro (Integer
																// objeto, int tipo primitiovo)
		return stringToIntVec;
	}

	public void add(int[] element) {
		dataList.add(element);

	}

	public ArrayList<int[]> get() { // retorna o conteudo do objeto datalist para podemos usar nio static fiber
		return this.dataList;
	}

	public static DataSet buildFiber(DataSet dataSet, int classType) { // deve ser static porquye nao aktera o objeto so
																		// usa poara criar um novo
		DataSet dsFiber = new DataSet();
		dsFiber.setId(classType);
		for (int[] index : dataSet.get()) { // usamos o dataset como parametro, recebe o dataset e a fibra que se
											// pretende usar, isto é necessário para a fiber estar static
			if (index[index.length - 1] == classType) {
				dsFiber.add(Arrays.copyOf(index, index.length - 1));
			}
		}
		return dsFiber;

	}

	public int[] getDomain(int position) {
		Set<Integer> domain = new HashSet<Integer>(); // SET - arraylist sem repetidos
		for (int[] index : dataList) {
			domain.add(index[position]);

		}
		return domain.stream().mapToInt(Number::intValue).toArray(); // number to int value transforma num valor inteiro

	}

	public int getDomainLength(int position) {
		return getDomain(position).length;
	}

	public int countDependences(int positionI, int positionJ, int valorI, int valorJ) {
		int r = 0;
		for (int[] arrayIndex : dataList) {
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
		return this.dataList.get(0).length; // get vai biscar a 1a posição do arraylist
	}

	public int countVariable(int positionI, int valorI) {
		int r = 0;
		for (int[] index : dataList) {
			if (index[positionI] == valorI) {
				r++;
			}
		}
		return r;
	}

	public double getVariableFrequency(int positionI, int valueI) {
		return (double) countVariable(positionI, valueI) / getLineNumber();
	}

	@Override
	public String toString() {
		String s = "[";
		if (dataList.size() > 0)
			s += Arrays.toString(dataList.get(0));
		for (int i = 1; i < dataList.size(); i++)
			s += "," + Arrays.toString(dataList.get(i));
		s += "]";

		return "Size=" + dataList.size() + " Dataset = " + s;
	}
}
