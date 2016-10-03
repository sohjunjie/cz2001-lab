public class QuickSort {

	public static int comparisonCount = 0;

	public static int getCompCount() {
		int x;
		x = comparisonCount;
		comparisonCount = 0;
		return x;
	}

	//n is position 0, m is position of last element
	public static void quickSort(int[] arr, int n, int m) {
		int pivot_pos;
		if(arr== null || arr.length==0)	{return;}
		if(n>=m)	{return;}
		pivot_pos=partition(arr,n,m);
		quickSort(arr, n, pivot_pos-1);					//recursive call for left side
		quickSort(arr, pivot_pos+1, m);					//recursive call for right side
	}

	
	public static int partition(int[] arr, int low, int high) {
		
		int i, last_small, pivot;
		int mid =(low+high)/2;
		int temp = arr[low];
		arr[low] = arr[mid];
		arr[mid] = temp;
		pivot = low;
		last_small = low;
		for(i=low+1;i<=high;i++) {			
			comparisonCount++;
			if(arr[i]<arr[pivot])
			{
				temp=arr[last_small+1];
				arr[last_small+1]=arr[i];
				arr[i]=temp;
				last_small++;
			}
		}
		temp=arr[low];
		arr[low]=arr[last_small];
		arr[last_small]=temp;
		return last_small;								//return position of pivot
	}

}
