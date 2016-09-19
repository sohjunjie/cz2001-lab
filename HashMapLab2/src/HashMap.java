public class HashMap {

	private final static int TABLE_SIZE = 1000;
	private int getProbeCount = 0;
	private int putProbeCount = 0;
	
	LinkedHashEntry[] table;

	HashMap() {    
		table = new LinkedHashEntry[TABLE_SIZE];    
		for (int i = 0; i < TABLE_SIZE; i++)     
			table[i] = null;  
	}
 
	public String get(int key) {
		int hash = (key % TABLE_SIZE);
		if (table[hash] == null){
			getProbeCount++;
			return "";
		} else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.getKey() != key){
				entry = entry.getNext();
				getProbeCount++;
			}
			getProbeCount++;
			if (entry == null)
				return "";
			else
				return entry.getValue();    
		}  
	}
   
	public void put(int key, String value) {
		int hash = (key % TABLE_SIZE);
		if (table[hash] == null){
			putProbeCount++;
			table[hash] = new LinkedHashEntry(key, value);
		} else {                  
			LinkedHashEntry entry = table[hash];                  
			while (entry.getNext() != null && entry.getKey() != key){
				putProbeCount++;
				entry = entry.getNext();
			}
			putProbeCount++;
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

	public int getGetProbeCount(){
		int x = getProbeCount;
		getProbeCount = 0;
		return x;
	}

	public int getPutProbeCount(){
		int x = putProbeCount;
		putProbeCount = 0;
		return x;
	}
	
}