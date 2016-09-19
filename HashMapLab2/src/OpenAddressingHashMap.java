public class OpenAddressingHashMap {
	
	private static final int TABLE_SIZE = 1000;
	private static final int HASH_INCR = 11;
	private int getProbeCount = 0;
	private int putProbeCount = 0;
	private int getProbeCountDHash = 0;
	private int putProbeCountDHash = 0;
	
	HashEntry [] table;
	
	public OpenAddressingHashMap(){
		table = new HashEntry[TABLE_SIZE];
		for(int i=0;i<TABLE_SIZE;i++){
			table[i] = null;
		}
	}
	
	public int getGetProbeCount() {
		int x = getProbeCount;
		getProbeCount = 0;
		return x;
	}

	public int getPutProbeCount() {
		int x = putProbeCount;
		putProbeCount = 0;
		return x;
	}

	public int getGetProbeCountDHash() {
		int x = getProbeCountDHash;
		getProbeCountDHash = 0;
		return x;
	}

	public int getPutProbeCountDHash() {
		int x = putProbeCountDHash;
		putProbeCountDHash = 0;
		return x;
	}

	public String getUsingLinearProbing(int key){
		
		int hashCode = (key % TABLE_SIZE);
		int loc = hashCode;
		
		getProbeCount++;
		while( table[loc] != null){
			if((table[loc]).getKey() == key){
				return table[loc].getvalue();
			}
			loc = (loc + 1) % TABLE_SIZE;
			getProbeCount++;
			if(loc == hashCode)
				break;
		}		
		return "Not found.";	
	}
	
	public void putUsingLinearProbing(int key, String value){
		int hashCode = (key % TABLE_SIZE);
		int loc = hashCode;
		putProbeCount++;
		while(table[loc] != null && 
				table[loc] != DeletedEntry.getUniqueDeletedEntry()){
			loc = (loc + 1) % TABLE_SIZE;
			putProbeCount++;
			
			if(loc == hashCode){
				System.out.println("Hash Table is full");
				return;
			}
		}
		
		table[loc] = new HashEntry(key,value);
	}
	
	public String getUsingDoubleHashing(int key){
		int hashCode = (key % TABLE_SIZE);
		int loc = hashCode;
		int inc = key % HASH_INCR + 1;
		
		getProbeCountDHash++;
		while(table[loc] != null){
			if((table[loc]).getKey() == key)
				return (table[loc]).getvalue();
			
			getProbeCountDHash++;
			loc = (loc + inc) % TABLE_SIZE;
			if(loc == hashCode)
				break;
		}
		
		return "Not found.";
	}
	
	public void putUsingDoubleHashing(int key, String value){
		int hashCode = (key % TABLE_SIZE);
		int loc = hashCode;
		int inc = key % HASH_INCR + 1;
		
		putProbeCountDHash++;
		while(table[loc] != null && table[loc] != DeletedEntry.getUniqueDeletedEntry()){
			loc = (loc + inc) % TABLE_SIZE;
			putProbeCountDHash++;
			
			if(loc == hashCode){
				System.out.println("Hash table is full");
				return;
			}
		}
		
		table[loc] = new HashEntry(key,value);
	}
	
	public void remove(int key){
		int hashCode = key % TABLE_SIZE;
		int loc = hashCode;
		
		while(table[loc] != null){
			if((table[loc]).getKey() == key){
				table[loc] = DeletedEntry.getUniqueDeletedEntry();
				return;
			}
			
			loc = (loc + 1) % TABLE_SIZE;
			if(loc == hashCode){
				break;
			}
			
		}
		
		System.out.println("Key not found in table");
	}
	
}
