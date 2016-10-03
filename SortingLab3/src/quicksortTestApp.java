import java.util.Arrays;

public class quicksortTestApp {

	public static void main(String[] args) {
		quicksort q = new quicksort();
		int[] x = {1,12,5,38,66,23,21,87,99,0};			//test example
		q.quickSort(x,0,x.length-1);
		System.out.println(Arrays.toString(x));
	}
}
