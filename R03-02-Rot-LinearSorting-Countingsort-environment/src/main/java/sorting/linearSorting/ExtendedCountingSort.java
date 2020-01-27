package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		
		//Somar todos os elementos do array com o oposto do menor numero, para nao existir numeros negativos no array
		int neutral = Math.abs(smallest(array));

		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i]+= neutral;
		}
		
		//Criar array auxiliar e registrar as ocorrencias de cada elemento no array original
		Integer[] aux = new Integer[greatest(array)+1];

		Arrays.fill(aux, 0);

		for (int i = 0; i <= rightIndex; i++) {
			aux[array[i]] += 1;
		}

		
		// Fazer calculo das somas das ocorrencias
		int counter = 0;
		for (int i = 0; i < aux.length; i++) {
			counter = counter + aux[i];
			aux[i] = counter;
		}
		
		
		
		//Criando e populando o array ordenado e restaurando a soma feito anteriormente, deixando o array igual ao original
		Integer[] sorted = new Integer[array.length+1];

		for (int i = 0; i < array.length; i++) {
			sorted[aux[array[i]]] = array[i] - neutral;

			aux[array[i]] -= 1;
		}

		
		// substituindo os elementos ordenados no array original
		for(int i=leftIndex+1; i< sorted.length;i++){
			array[i-1] = sorted[i];
		}


	}

	private int greatest(Integer[] array) {
		Integer greatest = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > greatest)
				greatest = array[i];
		}
		return greatest;
	}

	private int smallest(Integer[] array){
		Integer smallest = 0;
		for (int i = 0; i < array.length; i++ ){
			if (array[i] < smallest)
				smallest = array[i];
		}
		return smallest;
	}



}

