public class HashMap {

	private final static int TABLE_SIZE = 1000;
	private int timesSearch = 0;
	private long totalTimesSearch = 0;
	
	LinkedHashEntry[] table;

	HashMap() {    
		table = new LinkedHashEntry[TABLE_SIZE];    
		for (int i = 0; i < TABLE_SIZE; i++)     
			table[i] = null;  
	}
 
	public String get(int key) {
		int hash = (key % TABLE_SIZE);
		if (table[hash] == null){
			timesSearch++;
			totalTimesSearch++;
			return "";
		} else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.getKey() != key){
				entry = entry.getNext();
				timesSearch++;
				totalTimesSearch++;
			}

			timesSearch++;
			totalTimesSearch++;
			if (entry == null)
				return "";
			else
				return entry.getValue();    
		}  
	}
   
	public void put(int key, String value) {
		int hash = (key % TABLE_SIZE);    
		if (table[hash] == null)      
			table[hash] = new LinkedHashEntry(key, value);            
		else {                  
			LinkedHashEntry entry = table[hash];                  
			while (entry.getNext() != null && entry.getKey() != key)                        
				entry = entry.getNext();                  
			if (entry.getKey() == key)
				entry.setValue(value);
			else
				entry.setNext(new LinkedHashEntry(key, value));
		}      
	}

	public void remove(int key) {
		int hash = (key % TABLE_SIZE);
		if (table[hash] != null) {
			LinkedHashEntry prevEntry = null;
			LinkedHashEntry entry = table[hash];
			while (entry.getNext() != null && entry.getKey() != key) {
				prevEntry = entry;
				entry = entry.getNext();
			}
			if (entry.getKey() == key) {                        
				if (prevEntry == null)
					table[hash] = entry.getNext();
				else
					prevEntry.setNext(entry.getNext());
			}
		}
	}

	public int getTimesSearch(){
		int x = timesSearch;
		timesSearch = 0;
		return x;
	}
	
	public long getTotalTimesSearch(){
		return totalTimesSearch;
	}

}