import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;


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
		
		// generate data set to csv file
		generateCSVDataSet();
		
		// read data set and run against sorting algorithm
		readCSVDataSet();
		
		// generate performance statistics of sorting algorithm to csv file
		generateCSVResult();
		
		// print performance statistics of sorting algorithm in console
		printStatsResult();

	}
	
	/**
	 * Print statistics results for dummy data run by QuickSort and MergeSort.
	 * Requires data from all the following static global variable in printing
	 * the statistics results:
	 * 
	 * 1) randMergeSortStatistic 2) asceMergeSortStatistic 3) descMergeSortStatistic
	 * 4) randQuickSortStatistic 5) asceQuickSortStatistic 6) descQuickSortStatistic
	 */	
	public static void printStatsResult() {
		
		SortStatistic record = null;
		
		System.out.println("****************************Merge sort****************************");

		
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
		
		System.out.println("+----------------------------------------------------------------+");
		System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "mergesort_random", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
		for(int i=0; i<randMergeSortStatistic.size(); i++) {
			record = randMergeSortStatistic.get(i);
			System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
		}
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
		
		System.out.println();
		System.out.println();



		System.out.println("****************************Quick sort****************************");

		
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

		System.out.println("+----------------------------------------------------------------+");
		System.out.format("|%-20s|%-10s|%-16s|%-15s|%n", "quicksort_random", "array_size", "comparison_count", "nano_time_taken");
		System.out.println("+----------------------------------------------------------------+");
		for(int i=0; i<randQuickSortStatistic.size(); i++) {
        	record = randQuickSortStatistic.get(i);
        	System.out.format("|%-20d|%-10d|%-16d|%-15d|%n", i+1, record.nsize, record.keyCompCount, record.timeTaken);
        }
		System.out.println("+----------------------------------------------------------------+");
		System.out.println();
		
		System.out.println("CSV results was generated in: " + resultPath.toString());
		
	}
	
	/**
	 * Generate csv statistics result for mergesort and quicksort algorithm
	 */
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
	
	/**
	 * Write sorting statistic data into a csv file.
	 * 
	 * @param writePath 	Directory path to write the csv file to
	 * @param statsToWrite 	ArrayList of SortStatistic object recording performance statistics of a sorting algorithm
	 * @throws IOException
	 */
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
	
	/**
	 * Read csv data set from a pre-defined file directory into an array
	 * and then runs it against sorting algorithms. Statistics generated
	 * from the run are recorded into an ArrayList of SortStatistic object.
	 * 
	 * Execution of this method assumes csv data set to read from
	 * exists in the data folder of the project directory.
	 * 
	 * The static method generateCSVDataSet should be executed before this 
	 * method.
	 */
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

			// Activate sorting algorithm before actual test to prevent
			// bias due to memory caching of sorting object
			MergeSort m = new MergeSort();
			m.mergeSort(asceNumberArray, 0, asceNumberArray.length-1);
			
			// START MERGE SORT STATISTICS RECORDING
			recordMergeSortStats(asceNumberArray, asceMergeSortStatistic);
			recordMergeSortStats(descNumberArray, descMergeSortStatistic);
			recordMergeSortStats(randNumberArray, randMergeSortStatistic);

			QuickSort q = new QuickSort();
			q.quickSort(asceNumberArray2, 0, asceNumberArray.length-1);
			// START QUICK SORT STATISTICS RECORDING
			recordQuickSortStats(asceNumberArray2, asceQuickSortStatistic);
			recordQuickSortStats(descNumberArray2, descQuickSortStatistic);
			recordQuickSortStats(randNumberArray2, randQuickSortStatistic);

		}

	}
	
	/**
	 * Record MergeSort performance stats into SortStatistic object
	 * @param arr				array to sort
	 * @param arrSortStatistic	array to add SortStatistic records into
	 */
	public static void recordMergeSortStats(int[] arr, ArrayList<SortStatistic> arrSortStatistic){
		long start, end;
		MergeSort m = new MergeSort();
		
		start 	= System.nanoTime();
		m.mergeSort(arr, 0, arr.length-1);
		end 	= System.nanoTime();
		arrSortStatistic.add(new SortingTestApp().new SortStatistic(arr.length, m.getCompCount(), end-start));
	}
	
	/**
	 * Record QuicSort performance stats into SortStatistic object
	 * @param arr				array to sort
	 * @param arrSortStatistic	array to add SortStatistic records into
	 */
	public static void recordQuickSortStats(int[] arr, ArrayList<SortStatistic> arrSortStatistic){
		long start, end;
		QuickSort q = new QuickSort();
		
		start 	= System.nanoTime();
		q.quickSort(arr, 0, arr.length-1);
		end 	= System.nanoTime();
		arrSortStatistic.add(new SortingTestApp().new SortStatistic(arr.length, q.getCompCount(), end-start));
	}
	
	/**
	 * Generate dummy data set and save it into a csv file
	 * in a pre-defined directory.
	 * 
	 * Generated data set is to be used to test performance
	 * of sorting algorithms.
	 */
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

	/**
	 * Write numbers stored in an ArrayList into a csv file
	 * and save it.
	 * 
	 * @param numberList 	ArrayList containing numbers
	 * @param csvpath		Directory to save csv file
	 * @throws IOException
	 */
	private static void writeArrayListCSV(ArrayList<Integer> numberList, String csvpath) throws IOException{

		CSVWriter writer = new CSVWriter(new FileWriter(csvpath));
		for (int num : numberList) {
			String [] data = new String[]{Integer.toString(num)};			
			writer.writeNext(data);
		}
		writer.close();

	}

	/**
	 * Read data set in csv into an array.
	 * 
	 * @param csvpath	Path of csv data set to read from
	 * @param size		Number of records in csv data set to read and store into the array
	 * @return			Array of integer number as recorded in the csv data set
	 * @throws IOException
	 */
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
	
	
	/**
	 * Object to organise key performance observation of a sorting alogrithm
	 * on a dummy data set
	 */
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
