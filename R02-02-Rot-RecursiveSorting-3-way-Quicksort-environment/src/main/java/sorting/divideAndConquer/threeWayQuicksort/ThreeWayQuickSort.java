package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   /**
    * No algoritmo de quicksort, selecionamos um elemento como pivot,
    * particionamos o array colocando os menores a esquerda do pivot 
    * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
    * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
    * 
    * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
    * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
    * eh conhecida como quicksort tree way e consiste da seguinte ideia:
    * - selecione o pivot e particione o array de forma que
    *   * arr[l..i] contem elementos menores que o pivot
    *   * arr[i+1..j-1] contem elementos iguais ao pivot.
    *   * arr[j..r] contem elementos maiores do que o pivot. 
    *   
    * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
    * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
    * recursivamente. 
    **/
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex >= 0) {
         T pivot = array[leftIndex];
         int smallerThenPivot = leftIndex;
         int greaterThanPivot = rightIndex;

         for (int i = leftIndex; i <= greaterThanPivot; i++) {
            if (array[i].compareTo(pivot) < 0) {
               Util.swap(array, smallerThenPivot++, i);

            } else if (array[i].compareTo(pivot) > 0) {
               Util.swap(array, greaterThanPivot--, i--);

            }
         }
         sort(array, leftIndex, smallerThenPivot - 1);
         sort(array, greaterThanPivot + 1, rightIndex);
      }

   }

}
