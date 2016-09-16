public class application {
	
	public static void main(String[] args){
		
		int dataSize = 900; //adjust to 100, 300, 500, 700, 900 where necessary
		int recordCount = 0, i, x;
		int clientKey;
		String client;
		int[] clientIDs = new int[dataSize];
		HashMap clientHashMap = new HashMap();
		
		while (recordCount < dataSize) {

			//hash randomly generated key, value to hashmap
			clientKey = 1 + (int) (Math.random() * 100000);	//client id
			client = "Client #" + (++recordCount);				//client name
			clientIDs[recordCount-1] = clientKey;
			clientHashMap.put(clientKey, client);
			
		}
		
		//try to perform 1000 times random searches
		for(i=0;i<1000;i++){
	        x = 0 + (int) (Math.random() * dataSize-1);
	        //R: HashMap Result, TS: TimesSearch, TTS: TotalTimesSearch
	        System.out.print("R:" + clientHashMap.get(clientIDs[x]) + " TS:" + clientHashMap.getTimesSearch() + " TTS:" + clientHashMap.getTotalTimesSearch());
	        System.out.println();
	        // performing hashmap search on clientUUIDs[x]
		}
		
	}

}