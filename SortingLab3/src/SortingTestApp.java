import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SortingTestApp {
	
	public static final Path 	dataPath 	= Paths.get(System.getProperty("user.dir"), "data");
	public static final int[] 	numOfData 	= {2000,4000,6000,8000,10000};
	
	public static void main(String[] args) {

		generateCSVDataSet();

		readCSVDataSet();

	}

	
	public static void readCSVDataSet(){

		Path randPath, ascePath, descPath;
		ArrayList<Integer> randNumberList = new ArrayList<Integer>();
		ArrayList<Integer> asceNumberList = new ArrayList<Integer>();
		ArrayList<Integer> descNumberList = new ArrayList<Integer>();
		
		for (int maxData  : numOfData) {

			randPath = Paths.get(dataPath.toString(), maxData + "rand.csv");
			ascePath = Paths.get(dataPath.toString(), maxData + "asce.csv");
			descPath = Paths.get(dataPath.toString(), maxData + "desc.csv");

			try {
				randNumberList = readCSVToArrayList(randPath.toString());
				asceNumberList = readCSVToArrayList(ascePath.toString());
				descNumberList = readCSVToArrayList(descPath.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	
	public static void generateCSVDataSet(){

		Path randPath, ascePath, descPath;
		ArrayList<Integer> randNumberList = new ArrayList<Integer>();
		ArrayList<Integer> asceNumberList = new ArrayList<Integer>();
		ArrayList<Integer> descNumberList = new ArrayList<Integer>();		
		
		for (int maxData  : numOfData) {

			randNumberList.clear();
			asceNumberList.clear();
			descNumberList.clear();

			for (int j=1; j<=maxData; j++){
				randNumberList.add(1 + (int) (Math.random() * maxData));
				asceNumberList.add(j);
				descNumberList.add(maxData-j+1);
			}

			randPath = Paths.get(dataPath.toString(), maxData + "rand.csv");
			ascePath = Paths.get(dataPath.toString(), maxData + "asce.csv");
			descPath = Paths.get(dataPath.toString(), maxData + "desc.csv");

			try {
				writeArrayListCSV(randNumberList, randPath.toString());
				writeArrayListCSV(asceNumberList, ascePath.toString());
				writeArrayListCSV(descNumberList, descPath.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}

	
	private static void writeArrayListCSV(ArrayList<Integer> numberList, String csvpath) throws IOException{

		CSVWriter writer = new CSVWriter(new FileWriter(csvpath));
		for (int num : numberList) {
			String [] data = new String[]{Integer.toString(num)};			
			writer.writeNext(data);
		}
		writer.close();

	}

	
	private static ArrayList<Integer> readCSVToArrayList(String csvpath) throws IOException{

		ArrayList<Integer> returnArrayList = new ArrayList<Integer>();
		CSVReader csvReader = new CSVReader(new FileReader(csvpath));
		String[] row = null;
		while((row = csvReader.readNext()) != null) {
			returnArrayList.add(Integer.parseInt(row[0]));
		}
		csvReader.close();
		return returnArrayList;
		
	}

	
	private static int[] readCSVToArray(String csvpath, int size) throws IOException{

		int[] returnArray = new int[size];
		CSVReader csvReader = new CSVReader(new FileReader(csvpath));
		String[] row = null;

		for (int i=0; i<size; i++){
			row = csvReader.readNext();
			if (row == null)
				break;
			returnArray[i] = Integer.parseInt(row[0]);

		}
		
		csvReader.close();
		return returnArray;
		
	}
	
}
