package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
  
   
    @Override
    public T getOrderStatistics(T[] array, int k) { 
    	
    	
    	T menor = array[0];
    	
    	for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(menor) > 0) {
                menor = array[i];
            }
        }
    	
        if (k > array.length)
            return null;

        if (k == 1) {
           
            for (int i = 0; i < array.length; i++) {
                if (array[i].compareTo(menor) < 0) {
                    menor = array[i];
                }
            }
               
        } else {
           
            T ultimo = getOrderStatistics(array, k - 1);
         
            for (int i = 0; i < array.length; i++) {
               
                if (array[i].compareTo(ultimo) > 0 && array[i].compareTo(menor) < 0) {
                   
                    menor = array[i];
                }
            }
           
        }
        return menor;
    }

}




