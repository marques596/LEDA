package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int j = 0; j< array.length-1;j++){
			int lowest = j;
			for(int i = j+1; i< array.length; i++){
				if(array[i].compareTo(array[lowest]) < 0){
					lowest = i;

				}

			}
			Util.swap(array,j,lowest);
		}
	}
}
