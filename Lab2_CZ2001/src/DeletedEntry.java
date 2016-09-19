
public class DeletedEntry extends HashEntry {
	
	public static DeletedEntry entry = null;
	
	public DeletedEntry(){
		super(-1,"\0");
	}
	
	public static DeletedEntry getUniqueDeletedEntry(){
		if(entry == null)
			entry = new DeletedEntry();
		return entry;			
	}

}
