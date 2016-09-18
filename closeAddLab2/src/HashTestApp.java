import java.util.ArrayList;
import java.util.Collections;

public class HashTestApp {

	public static void main(String[] args) {

		int[] numOfData 	= {100,300,500,700,900};
		int dataSize 		= numOfData.length;

		int maxData 		= 100000;

		int numOfRepetition = 1000;

		//generate a list of values from 1 to 100,000
		ArrayList<Integer> listOfNumbers = generateList(maxData);

		//Loop to go thru each test case from 100 to 900
		for(int i = 0; i < dataSize; i++){

			startTest(listOfNumbers, numOfData[i],numOfRepetition,true);
			startTest(listOfNumbers, numOfData[i],numOfRepetition,false);

		}

	}

	public static void startTest(ArrayList<Integer> listOfNumbers,int numOfData,int repetition, boolean success){

		long start			= 0;
		long end			= 0;
		double probeCount	= 0;
		double timeClosed	= 0;
		double probeClosed	= 0;
		double timeOpen		= 0;
		double probeOpen 	= 0;
		int target			= 0;

		if(success)
			System.out.println("****************Success cases****************");
		else
			System.out.println("****************Failure cases****************");

    		System.out.println("Test for " + numOfData + " data");

		for(int rep = 0; rep < repetition; rep++){
			//randomize list
	        Collections.shuffle(listOfNumbers);

        	//randomly choose target from list(depends if you want a success search or failure)
	        if(success)
	        	target = listOfNumbers.get((int)(Math.random()*numOfData));
	        else
	        	target = listOfNumbers.get(numOfData+1); //this value will not be in the list

        	//create closed hashmap
	        HashMap clientHashMap = new HashMap();
			for(int i = 0; i < numOfData; i ++){
				//can actually put any string into value (not impt for this case)
				clientHashMap.put(listOfNumbers.get(i), "Client #" + listOfNumbers.get(i));
			}

        	//start of closed address testing
        	start 		= System.nanoTime();
        	clientHashMap.get(target);
        	end   		= System.nanoTime();
        	probeCount 	= clientHashMap.getTimesSearch();

        	//sum for time and probes
        	timeClosed 	+= end-start;
        	probeClosed += probeCount;

        	//create open hashmap here

        	//start of open address testing
        	start 		= System.nanoTime();
        	probeCount 	= testOpenAddr(listOfNumbers,target,numOfData);
        	end   		= System.nanoTime();

        	//sum for time and probes
        	timeOpen 	+= end-start;
        	probeOpen 	+= probeCount;
		}
		System.out.println("===========================");
		System.out.println("Averages for " + numOfData + " data test cases for " + repetition+" times");
		System.out.println("Closed addressing average time = " + (timeClosed/repetition));
		System.out.println("Closed addressing average probes = " + (probeClosed/repetition));
		System.out.println("Open addressing average time = " + (timeOpen/repetition));
		System.out.println("Open addressing average probes = " + (probeOpen/repetition));
		System.out.println("===========================");
	}

	private static ArrayList<Integer> generateList(int n){
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j=1; j<=n; j++) {
            list.add(new Integer(j));
        }
        return list;
	}

	// if needed
	private static int testOpenAddr(ArrayList<Integer> testList, int target, int size){

		return 0;
	}

}
