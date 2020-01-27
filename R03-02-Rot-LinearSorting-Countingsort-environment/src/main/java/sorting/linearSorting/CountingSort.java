package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		//Criar array auxiliar e registrar as ocorrencias de cada elemento no array original
		Integer[] aux = new Integer[greatest(array)+1];

		Arrays.fill(aux, 0);

		for (int i = leftIndex; i <= rightIndex; i++) {
			aux[array[i]] += 1;
		}
		
		// Fazer calculo das somas das ocorrencias
		int counter = 0;
		
		for (int i = 0; i < aux.length; i++) {
			counter = counter + aux[i];
			aux[i] = counter;
		}
		
		//Criando e populando o array ordenado
		Integer[] sorted = new Integer[array.length+1];
		
		for (int i = 0; i < array.length; i++) {
			sorted[aux[array[i]]] = array[i];
			aux[array[i]] -= 1;
		}

		// substituindo os elementos ordenados no array original
		for(int i = leftIndex+1; i < sorted.length;i++){
			array[i-1] = sorted[i];
		}

			
		

	}

	private int greatest(Integer[] array){
		Integer greatest = 0;
		for (int i = 0; i < array.length; i++ ){
			if (array[i] > greatest)
				greatest = array[i];
		}


		return greatest;
	}


}