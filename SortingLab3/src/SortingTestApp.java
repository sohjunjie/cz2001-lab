import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SortingTestApp {
	
	public static final Path 	dataPath 	= Paths.get(System.getProperty("user.dir"), "data");
	public static final Path 	resultPath 	= Paths.get(System.getProperty("user.dir"), "result");
	public static final int[] 	numOfData 	= {2000,4000,6000,8000,10000};
	
	public static ArrayList<SortStatistic> randMergeSortStatistic = new ArrayList<SortStatistic>();
	public static ArrayList<SortStatistic> asceMergeSortStatistic = new ArrayList<SortStatistic>();
	public static ArrayList<SortStatistic> descMergeSortStatistic = new ArrayList<SortStatistic>();

	public static ArrayList<SortStatistic> randQuickSortStatistic = new ArrayList<SortStatistic>();
	public static ArrayList<SortStatistic> asceQuickSortStatistic = new ArrayList<SortStatistic>();
	public static ArrayList<SortStatistic> descQuickSortStatistic = new ArrayList<SortStatistic>();
	
	public static void main(String[] args) {
		
		generateCSVDataSet();
		
		readCSVDataSet();
		
		generateCSVResult();
		
		printStatsResult();		

	}
	
	public static void printStatsResult() {
		
		SortStatistic record = null;
		
		System.out.println("****************************Merge sort****************************");

		
		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "mergesort_random", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<randMergeSortStatistic.size(); i++) {
        	record = randMergeSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();

		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "mergesort_ascending", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<asceMergeSortStatistic.size(); i++) {
        	record = asceMergeSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
        
		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "mergesort_descending", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<descMergeSortStatistic.size(); i++) {
        	record = descMergeSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
		
		System.out.println();
		System.out.println();
		
		System.out.println("****************************Quick sort****************************");
		
		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "quicksort_random", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<randQuickSortStatistic.size(); i++) {
        	record = randQuickSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
		
		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "quicksort_ascending", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<asceQuickSortStatistic.size(); i++) {
        	record = asceQuickSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();

		System.out.println("+----------------------------------------------------------------+");
        System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "quicksort_descending", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
        for(int i=0; i<descQuickSortStatistic.size(); i++) {
        	record = descQuickSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
		
	}
	
	public static void generateCSVResult(){

		Path randMergePath = Paths.get(resultPath.toString(), "mergesort_rand.csv");
		Path asceMergePath = Paths.get(resultPath.toString(), "mergesort_asce.csv");
		Path descMergePath = Paths.get(resultPath.toString(), "mergesort_desc.csv");
		
		Path randQuickPath = Paths.get(resultPath.toString(), "quicksort_rand.csv");
		Path asceQuickPath = Paths.get(resultPath.toString(), "quicksort_asce.csv");
		Path descQuickPath = Paths.get(resultPath.toString(), "quicksort_desc.csv");
		
		try {
			
			writeSortStatsCSV(randMergePath.toString(), randMergeSortStatistic);
			writeSortStatsCSV(asceMergePath.toString(), asceMergeSortStatistic);
			writeSortStatsCSV(descMergePath.toString(), descMergeSortStatistic);

			writeSortStatsCSV(randQuickPath.toString(), randQuickSortStatistic);
			writeSortStatsCSV(asceQuickPath.toString(), asceQuickSortStatistic);
			writeSortStatsCSV(descQuickPath.toString(), descQuickSortStatistic);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void writeSortStatsCSV(String writePath, ArrayList<SortStatistic> statsToWrite) throws IOException{

		CSVWriter writer = new CSVWriter(new FileWriter(writePath));
		
		// CSV header row
		writer.writeNext(new String[] {"array_size", "comparison_count", "nano_time_taken"});
		
		// CSV result row
		for (SortStatistic record : statsToWrite) {
			String[] data = {	Integer.toString(record.nsize), 
								Integer.toString(record.keyCompCount), 
								Long.toString(record.timeTaken)
							};
			writer.writeNext(data);
		}

		writer.close();

	}
	
	public static void readCSVDataSet(){

		long start, end;
		Path randPath, ascePath, descPath;
		
		//array for merge sort
		int[] randNumberArray = null;
		int[] asceNumberArray = null;
		int[] descNumberArray = null;

		//array for quick sort
		int[] randNumberArray2 = null;
		int[] asceNumberArray2 = null;
		int[] descNumberArray2 = null;
		
		for (int maxData  : numOfData) {

			randPath = Paths.get(dataPath.toString(), maxData + "rand.csv");
			ascePath = Paths.get(dataPath.toString(), maxData + "asce.csv");
			descPath = Paths.get(dataPath.toString(), maxData + "desc.csv");

			try {
				randNumberArray = readCSVToArray(randPath.toString(), maxData);
				asceNumberArray = readCSVToArray(ascePath.toString(), maxData);
				descNumberArray = readCSVToArray(descPath.toString(), maxData);
				randNumberArray2 = randNumberArray.clone();
				asceNumberArray2 = asceNumberArray.clone();
				descNumberArray2 = descNumberArray.clone();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// START MERGE SORT STATISTICS RECORDING
			start 	= System.nanoTime();
			MergeSort.mergeSort(randNumberArray, 0, maxData-1);
			end 	= System.nanoTime();
			randMergeSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, MergeSort.getCompCount(), end-start));

			start 	= System.nanoTime();
			MergeSort.mergeSort(asceNumberArray, 0, maxData-1);
			end 	= System.nanoTime();
			asceMergeSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, MergeSort.getCompCount(), end-start));
			
			start 	= System.nanoTime();
			MergeSort.mergeSort(descNumberArray, 0, maxData-1);
			end 	= System.nanoTime();
			descMergeSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, MergeSort.getCompCount(), end-start));
			
			
			// START QUICK SORT STATISTICS RECORDING
			start 	= System.nanoTime();
			QuickSort.quickSort(randNumberArray2, 0, maxData-1);
			end 	= System.nanoTime();
			randQuickSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, QuickSort.getCompCount(), end-start));

			start 	= System.nanoTime();
			QuickSort.quickSort(asceNumberArray2, 0, maxData-1);
			end 	= System.nanoTime();
			asceQuickSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, QuickSort.getCompCount(), end-start));
			
			start 	= System.nanoTime();
			QuickSort.quickSort(descNumberArray2, 0, maxData-1);
			end 	= System.nanoTime();
			descQuickSortStatistic.add(new SortingTestApp().new SortStatistic(maxData, QuickSort.getCompCount(), end-start));

		}

	}

	
	public static void generateCSVDataSet(){

		Path randPath, ascePath, descPath;
		ArrayList<Integer> randNumberArray = new ArrayList<Integer>();
		ArrayList<Integer> asceNumberArray = new ArrayList<Integer>();
		ArrayList<Integer> descNumberArray = new ArrayList<Integer>();		
		
		for (int maxData  : numOfData) {

			randNumberArray.clear();
			asceNumberArray.clear();
			descNumberArray.clear();

			for (int j=1; j<=maxData; j++){
				randNumberArray.add(1 + (int) (Math.random() * maxData));
				asceNumberArray.add(j);
				descNumberArray.add(maxData-j+1);
			}

			randPath = Paths.get(dataPath.toString(), maxData + "rand.csv");
			ascePath = Paths.get(dataPath.toString(), maxData + "asce.csv");
			descPath = Paths.get(dataPath.toString(), maxData + "desc.csv");

			try {
				writeArrayListCSV(randNumberArray, randPath.toString());
				writeArrayListCSV(asceNumberArray, ascePath.toString());
				writeArrayListCSV(descNumberArray, descPath.toString());
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
	
	
	public class SortStatistic {
		public int nsize;
		public long timeTaken;
		public int keyCompCount;
		
		public SortStatistic(int nsize, int keyCompCount, long timeTaken) {
			this.nsize = nsize;
			this.timeTaken = timeTaken;
			this.keyCompCount = keyCompCount;
		}
		
	}
	
}
