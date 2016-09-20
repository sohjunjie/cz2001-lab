import java.util.ArrayList;
import java.util.Collections;

public class HashTestApp {

	public static void main(String[] args) {

		int[] numOfData 	= {100,300,500,700,900};
		int dataSize 		= numOfData.length;

		int maxData 		= 100000;

		int numOfRepetition = 1000;

		//step 2:
		closedHashingExample(5, 0);//data in map
		closedHashingExample(5, 6);//data not in map
		//add open
		openHashingExample(5, 4);//data in map
		openHashingExample(5, 5);//data not in map


		//generate a list of values from 1 to 100,000
		ArrayList<Integer> listOfNumbers = generateList(maxData);

		//Loop to go thru each test case from 100 to 900
		for(int i = 0; i < dataSize; i++){

			startTest(listOfNumbers, numOfData[i],numOfRepetition,true);
			startTest(listOfNumbers, numOfData[i],numOfRepetition,false);

		}

	}

	public static void closedHashingExample(int numOfValues, int search){

		HashMap clientHashMap = new HashMap();
		for(int i = 0; i < numOfValues; i ++){
			//dummy string
			clientHashMap.put(i, "Client #" + i);
		}

		String value = clientHashMap.get(search);

		if(value.equals(""))
			System.out.println("Data is not in hash map.");
		else
			System.out.println("Key belongs to " + value);
	}

	public static void openHashingExample(int numOfValues, int search){
		OpenAddressingHashMap clientHashMap = new OpenAddressingHashMap();
		for(int i = 0; i < numOfValues; i ++){
			//dummy string
			clientHashMap.putUsingLinearProbing(i, "Client #" + i);
			//clientHashMap.putUsingDoubleHashing(i, "Client #" + i);
		}

		String value = clientHashMap.getUsingLinearProbing(search);
		//String value = clientHashMap.getUsingDoubleHashing(search);
		
		System.out.println("Key belongs to " + value);
	}


	public static void startTest(ArrayList<Integer> listOfNumbers,int numOfData,int repetition, boolean success){

		long start				= 0;
		long end				= 0;
		double probeCount		= 0;
		double putProbeCount	= 0;
		int target				= 0;
		
		double timeClosed		= 0;
		double probeClosed		= 0;
		double putProbeClosed 	= 0;
		double putTimeClosed 	= 0;
		
		double timeOpen			= 0;
		double probeOpen 		= 0;
		double putProbeOpen 	= 0;
		double putTimeOpen	 	= 0;

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
	        start = System.nanoTime();
	        HashMap clientHashMap = new HashMap();
			for(int i = 0; i < numOfData; i ++){
				//dummy string
				clientHashMap.put(listOfNumbers.get(i), "Client #" + listOfNumbers.get(i));
			}
			end = System.nanoTime();
			
			putTimeClosed 	+= end-start;
			putProbeCount	= clientHashMap.getPutProbeCount();			
			putProbeClosed 	+= putProbeCount;
			
        	//start of closed address testing
        	start 		= System.nanoTime();
        	clientHashMap.get(target);
        	end   		= System.nanoTime();
        	probeCount 	= clientHashMap.getGetProbeCount();

        	//sum for time and probes
        	timeClosed 	+= end-start;
        	probeClosed += probeCount;

        	//create open hashmap here
	        start = System.nanoTime();
	        OpenAddressingHashMap openHashMap = new OpenAddressingHashMap();
			for(int i = 0; i < numOfData; i ++){
				//dummy string
				
				openHashMap.putUsingLinearProbing(listOfNumbers.get(i), "Client #" + listOfNumbers.get(i));
				//openHashMap.putUsingDoubleHashing(listOfNumbers.get(i), "Client #" + listOfNumbers.get(i));
				
			}
			end = System.nanoTime();
			
			putTimeOpen 	+= end-start;
			
			putProbeCount	= openHashMap.getPutProbeCount();	
			//putProbeCount	= openHashMap.getPutProbeCountDHash();	
			
			putProbeOpen 	+= putProbeCount;

        	//start of open address testing
        	start 		= System.nanoTime();
        	
        	openHashMap.getUsingLinearProbing(target);
        	//openHashMap.getUsingDoubleHashing(target);
        	
        	end   		= System.nanoTime();
        	
        	probeCount 	= openHashMap.getGetProbeCount();
        	//probeCount 	= openHashMap.getGetProbeCountDHash();
        	

        	//sum for time and probes
        	timeOpen 	+= end-start;
        	probeOpen 	+= probeCount;
		}
		System.out.println("===========================");
		System.out.println("Averages for " + numOfData + " data test cases for " + repetition+" times");
		System.out.println("Closed addressing average time = " + (timeClosed/repetition));
		System.out.println("Closed addressing average probes = " + (probeClosed/repetition));
		System.out.println("Closed addressing average put time = " + (putTimeClosed/repetition/numOfData));
		System.out.println("Closed addressing average put probes = " + (putProbeClosed/repetition/numOfData));

		System.out.println("Open addressing average time = " + (timeOpen/repetition));
		System.out.println("Open addressing average probes = " + (probeOpen/repetition));
		System.out.println("Open addressing average put time = " + (putTimeOpen/repetition/numOfData));
		System.out.println("Open addressing average put probes = " + (putProbeOpen/repetition/numOfData));
		System.out.println("===========================");
	}
	
	private static ArrayList<Integer> generateList(int n){
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j=1; j<=n; j++) {
            list.add(new Integer(j));
        }
        return list;
	}

}
