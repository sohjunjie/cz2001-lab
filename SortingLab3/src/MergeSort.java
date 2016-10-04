public class MergeSort {
	
	public static int comparisonCount = 0;
	
	public static int getCompCount() {
		int x;
		x = comparisonCount;
		comparisonCount = 0;
		return x;
	}

	public static void mergeSort(int[] E, int low, int high){

		if(low >= high)
			return;
		else{
			int mid = (low + high)/2;
			mergeSort(E, low, mid);
			mergeSort(E, mid+1, high);
			merge(E, low, high);
		}
	}
	
	public static void merge(int[] slot, int low, int high){

		int mid = (low+high)/2;
		int a = low, b = mid+1, i, tmp;

		if (high <= low) return;
		
		while (a <= mid && b <= high){
			comparisonCount++;
			if (slot[a] > slot[b]) {
				tmp = slot[b++];
				for (i = ++mid; i > a; i--)
					slot[i] = slot[i-1];
				slot[a++] = tmp;
			} else if (slot[a] < slot[b])
				a++;
			else { //slot[a]== slot[b]
				if (a == mid && b == high)	//last item in left sub list same as last item in right sublist
					break;
				tmp = slot[b++];
				a++;
				for (i = ++mid; i > a; i--)
					slot[i] = slot[i-1];
				slot[a++] = tmp;
			}
		}
		
	}

//	public static void merge(int[] E, int low, int high){
//		
//		if(low >= high)
//			return;
//		else{
//			int l, r, mid, i, j, k;
//			mid = (high + low)/2;
//			l = mid - low + 1;
//			r = high - mid;
//			
//			int[] left = new int [l];
//			int[] right = new int [r];
//			
//			for(i=0;i<l;i++)
//				left[i] = E[low+i];
//			for(j=0;j<r;j++)
//				right[j] = E[mid+j+1];
//			
//			i=0;
//			j=0;
//			k=low;
//			
//			while((i<l)&&(j<r)){
//				comparisonCount++;
//				if(left[i]<=right[j]){
//					E[k] = left[i];
//					i++;
//				}
//				else{
//					E[k] = right[j];
//					j++;
//				}
//				k++;
//			}
//			
//			if(i==l){
//				while(j<r){
//					E[k] = right[j];
//					j++;
//					k++;
//				}
//			}
//			
//			if(j==r){
//				while(i<l){
//					E[k] = left[i];
//					i++;
//					k++;
//				}
//			}
//
//		}
//	}
	
	
}



