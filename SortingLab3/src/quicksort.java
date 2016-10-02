package cz2001_lab3_quicksort;

public class quicksort {
public quicksort(){}
	
public void quickSort(int[] arr, int n, int m)		//n is position 0, m is position of last element
{
	int pivot_pos;
	if(arr== null || arr.length==0)	{return;}		
	if(n>=m)	{return;}							
	pivot_pos=partition(arr,n,m);
	quickSort(arr,n,pivot_pos-1);					//recursive call for left side
	quickSort(arr,pivot_pos+1,m);					//recursive call for right side
}

public int partition(int[] arr, int low, int high)	//from lecture notes
{
	int i, last_small,pivot;
	int mid =(low+high)/2;
	int temp = arr[low];							
	arr[low] = arr[mid];
	arr[mid] = temp;
	pivot = low;
	last_small = low;
	for(i=low+1;i<=high;i++)
	{
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
