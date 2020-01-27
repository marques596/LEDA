package adt.heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator.
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	public void heapify(int position) {

		int largest = position;

		if ( left(position) <= index && comparator.compare(heap[left(position)],heap[largest] ) > 0)
			largest = left(position);

		if (right(position) <= index && comparator.compare(heap[right(position)],heap[largest] ) > 0)
			largest = right(position);

		if (largest != position){
			Util.swap(heap,largest,position);
			heapify(largest);

		}


	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		index++;
		heap[index] = element;
		int iterator = index;
		while(iterator > 0 && comparator.compare(heap[parent(iterator)], heap[iterator]) < 0 ){
			Util.swap(heap,iterator,parent(iterator));
			iterator = parent(iterator);

		}
	}

	@Override
	public void buildHeap(T[] array) {

		heap = Arrays.copyOf(array,array.length);
		index = array.length -1;
		for (int i = (int) Math.floor(array.length/2); i >= 0; i--) {
			heapify(i);
		}




	}

	@Override
	public T extractRootElement() {
		T result = heap[0];


		heap[0] = heap[index];
		index--;

		heapify(0);

		return result;
	}

	@Override
	public T rootElement() {
		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {

		T[] result = (T[]) new Comparable[array.length];


        Comparator<T> comparatorOriginal = getComparator();

        setComparator((o1, o2) -> o2.compareTo(o1));

		this.buildHeap(array);

		for (int i = 0; i < array.length; i++) {
			result[i] = extractRootElement();
		}

		setComparator(comparatorOriginal);

		return result;
	}

	@Override
	public int size() {
		return index +1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

	public static void main(String[] args) {
		HeapImpl<Integer> reep = new HeapImpl<Integer>((Comparator<Integer>) new ComparatorMaxHeap<>());

		Integer[] array = {15,8,10,7,4,9,6};


		System.out.println(Arrays.toString(reep.heapsort(array)));
	}

}
