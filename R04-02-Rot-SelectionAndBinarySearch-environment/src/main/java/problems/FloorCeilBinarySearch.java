package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

   @Override
   public Integer floor(Integer[] array, Integer x) {
      int inicio = 0;
      int fim = array.length - 1;
      int meio;

      if (array[0] == x) {
         return null;
      }

      while (inicio <= fim) {
         meio = (inicio + fim) / 2;

         if (array[meio] == x) {
            return array[meio - 1];
         } else if (x < array[meio]) {
            fim = meio - 1;
         } else if (x > array[meio]) {
            inicio = meio + 1;
         }

      }

      return null;
   }

   @Override
   public Integer ceil(Integer[] array, Integer x) {
      int inicio = 0;
      int fim = array.length - 1;
      int meio;

      if (array[array.length - 1] == x) {
         return null;
      }

      while (inicio <= fim) {
         meio = (inicio + fim) / 2;

         if (array[meio] == x) {
            return array[meio + 1];
         } else if (x < array[meio]) {
            fim = meio - 1;
         } else if (x > array[meio]) {
            inicio = meio + 1;
         }

      }

      return null;
   }

}
