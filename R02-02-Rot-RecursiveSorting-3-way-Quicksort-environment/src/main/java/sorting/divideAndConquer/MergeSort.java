package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex >= 0) {

         int m = (leftIndex + rightIndex) / 2;

         sort(array, leftIndex, m);
         sort(array, m + 1, rightIndex);
         merge(array, leftIndex, m, rightIndex);
      }

   }

   public void merge(T[] array, int start, int mid, int end) {

      T[] aux = Arrays.copyOf(array, array.length);

      int i = start;
      int j = mid + 1;

      for (int k = start; k <= end; k++) {
         if (i > mid)
            array[k] = aux[j++];
         else if (j > end)
            array[k] = aux[i++];
         else if (aux[i].compareTo(aux[j]) < 0)
            array[k] = aux[i++];
         else
            array[k] = aux[j++];

      }
   }

}
