package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends
		AbstractSorting<T> {


	public void sort(T[] array, int leftIndex, int rightIndex){


		for(int j = 0; j< array.length;j++){
			int smallest = j;
			int greatest = array.length-1-j;

			for(int i=j+1; i<array.length;i++){
				if(array[i].compareTo(array[smallest]) < 0){
					smallest = i;

				}
				Util.swap(array,smallest,j);
			}

			for(int i=0; i<array.length-j;i++){
				if(array[i].compareTo(array[greatest]) > 0){
					greatest = i;

				}
				Util.swap(array,greatest,array.length-1-j);
			}


		}

			}




}
