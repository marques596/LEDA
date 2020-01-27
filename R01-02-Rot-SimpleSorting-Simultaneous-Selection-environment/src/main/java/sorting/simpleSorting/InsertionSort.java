package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      for (int i = 1; i < array.length; i++) {
         T temporary = array[i];
         while (i > 0 && array[i - 1].compareTo(temporary) > 0) {
            array[i] = array[i - 1];
            i = i - 1;
            array[i] = temporary;

         }
      }

   }
}
