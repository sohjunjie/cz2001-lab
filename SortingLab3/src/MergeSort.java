public class MergeSort {
	
	private int comparisonCount = 0;
	
	public int getCompCount() {
		int x;
		x = comparisonCount;
		comparisonCount = 0;
		return x;
	}
	
	public void resetCompCount(){
		comparisonCount = 0;
	}
	
	/**
	 * Mergesort algorithm sorting in ascending order
	 * 
	 * @param E		array to be sorted
	 * @param low	first index of array
	 * @param high	last index of array
	 */
	public void mergeSort(int[] E, int low, int high){

		if(low >= high)
			return;
		else{
			int mid = (low + high)/2;
			mergeSort(E, low, mid);
			mergeSort(E, mid+1, high);
			merge(E, low, high);
		}
	}

	/**
	 * Merge algorithm to merge 2 sorted sub array into 1
	 * 
	 * @param E		array assumed to contain 2 sorted sub array
	 * @param low	first index of 1st sorted sub array
	 * @param high	last index of 2nd sorted sub array
	 */
	public void merge(int[] E, int low, int high){

		if(low >= high) return;

		int mid 		= (high + low)/2;
		int leftSize 	= mid - low + 1;
		int rightSize 	= high - mid;

		int lPtr		= 0;
		int rPtr		= 0;
		int ePtr		= low;

		int[] left = new int [leftSize];
		int[] right = new int [rightSize];

		// copy main array to left and right sub array
		for(int i=0; i<leftSize; i++)
			left[i] = E[low+i];
		for(int j=0; j<rightSize; j++)
			right[j] = E[mid+j+1];

		// move smaller of the left array and right array item to result array
		while((lPtr < leftSize) && (rPtr < rightSize)){
			comparisonCount++;
			if(left[lPtr]<=right[rPtr])
				E[ePtr++] = left[lPtr++];
			else
				E[ePtr++] = right[rPtr++];
		}
		
		// merge remaining right array if left array completely merged
		if(lPtr == leftSize)
			while(rPtr < rightSize)
				E[ePtr++] = right[rPtr++];
		
		// merge remaining left array if right array completely merged
		if(rPtr == rightSize)
			while(lPtr < leftSize)
				E[ePtr++] = left[lPtr++];


	}
	
	
}