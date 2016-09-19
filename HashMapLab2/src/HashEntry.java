public class HashEntry {
	private int key;
	private String value;
	
	public HashEntry(int key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String getvalue(){
		return this.value;
	}
	
	public int getKey(){
		return this.key;
	}
	
	public void setKey(int key){
		this.key = key;
	}
	
	public void setvalue(String value){
		this.value = value;
	}

}
