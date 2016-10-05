public class QuickSort {

	private int comparisonCount = 0;

	public int getCompCount() {
		int x;
		x = comparisonCount;
		comparisonCount = 0;
		return x;
	}

	/**
	 * Quicksort algorithm sorting in ascending
	 * 
	 * @param arr 	array to be sorted
	 * @param n		first index of array
	 * @param m		last index of array
	 */
	public void quickSort(int[] arr, int n, int m) {
		int pivot_pos;
		if(arr== null || arr.length==0)	return;
		if(n>=m)	return;
		pivot_pos=partition(arr,n,m);
		quickSort(arr, n, pivot_pos-1);					//recursive call for left side
		quickSort(arr, pivot_pos+1, m);					//recursive call for right side
	}


	/**
	 * Partition array into 2 parts base on a pivot
	 * 
	 * @param arr	array to be partition
	 * @param low	first index of array
	 * @param high	last index of array
	 * @return		position of partition pivot
	 */
	public int partition(int[] arr, int low, int high) {

		int last_small, pivot, temp;
		int mid = (low+high)/2;
		
		temp = arr[low];
		arr[low] = arr[mid];
		arr[mid] = temp;
		pivot = last_small = low;
		
		for(int i=low+1; i<=high; i++) {
			comparisonCount++;
			if(arr[i] < arr[pivot])
			{	//swap last_small+1 with i
				temp = arr[++last_small];
				arr[last_small] = arr[i];
				arr[i] = temp;
			}
		}
		temp = arr[low];
		arr[low] = arr[last_small];
		arr[last_small] = temp;

		return last_small;
	}


}