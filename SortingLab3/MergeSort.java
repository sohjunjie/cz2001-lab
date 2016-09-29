
public class MergeSort {
	void mergeSort(int []E, int low, int high){
		
		if(low>=high)
			return;
		else{
			int mid = (low+high)/2;
			mergeSort(E,low,mid);
			mergeSort(E,mid+1,high);
			merge(E,low,high);
		}
	}
	
	void merge(int []E,int low, int high){
		if(low>=high)
			return;
		else{
			int l,r,mid,i,j,k;
			mid = (high+low)/2;
			l = mid - low + 1;
			r = high - mid;
			int [] left = new int [l];
			int [] right = new int [r];
			for(i=0;i<l;i++)
				left[i] = E[low+i];
			for(j=0;j<r;j++)
				right[i] = E[mid+i+1];
			i=0;j=0;
			for(k=low;k<=high;k++)
				if(left[i]<=right[j]){
					E[k] = left[i];
					i++;
				}
				else{
					E[k] = right[j];
					j++;
				}
			}
	}
}


