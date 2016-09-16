public class LinkedHashEntry {

	private int key;  
	private String value;
	private LinkedHashEntry next;
   
	LinkedHashEntry(int key, String value) {            
		this.key = key;
		this.value = value;    
		this.next = null;
      }
 
      public String getValue() {  
    	  return value;
      }
 
      public void setValue(String value) {  
    	  this.value = value;
      }
 
      public int getKey() {  
    	  return key;
      }
 
      public LinkedHashEntry getNext() {  
    	  return next;
      }
 
      public void setNext(LinkedHashEntry next) {  
    	  this.next = next;
      }
}