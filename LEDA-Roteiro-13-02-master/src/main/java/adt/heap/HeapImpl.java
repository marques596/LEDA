package adt.heap;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
        return (T[]) resp.toArray(new Comparable[0]);
    }

    // ///////////// METODOS A IMPLEMENTAR

    /**
     * Valida o invariante de uma heap a partir de determinada posicao, que pode
     * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
     * (comparados usando o comparator) elementos na parte de cima da heap.
     */
    private void heapify(int position) {
        if(position >= 0) {
            int esquerda = left(position);
            int direita = right(position);
            int indiceDoMaior = position;


            if (esquerda <= this.index && comparator.compare(heap[esquerda], heap[indiceDoMaior]) > 0) {
                indiceDoMaior = esquerda;

            }
            if (direita <= this.index && comparator.compare(heap[direita], heap[indiceDoMaior]) > 0) {
                indiceDoMaior = direita;
            }
            if (indiceDoMaior != position) {
                Util.swap(heap, indiceDoMaior, position);
                heapify(indiceDoMaior);
            }
        }
    }

    @Override
    public void insert(T element) {
        // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
        if (index == heap.length - 1) {
            heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
        }
        // /////////////////////////////////////////////////////////////////
        // TODO Implemente a insercao na heap aqui.
        if(element != null) {
            this.index++;
            heap[index] = element;

            int i = index;
            while (i > 0) {
                if (comparator.compare(heap[i], heap[parent(i)]) > 0) {
                    Util.swap(heap, i, parent(i));
                    i = parent(i);
                } else
                    break;
            }
        }

    }

    @Override
    public void buildHeap(T[] array) {
        if( array != null && array.length > 0 ) {
            heap = Arrays.copyOf(array, array.length);
            index = array.length - 1;
            for (int i = (int) Math.floor(array.length / 2) - 1; i >= 0; i--) {
                heapify(i);
            }
        }


    }

    @Override
    public T extractRootElement() {
        if(!this.isEmpty()) {
            T resultado = rootElement();

            T proxRoot = heap[index--];
            heap[0] = proxRoot;
            heapify(0);

            return resultado;
        }
        return null;

    }

    @Override
    public T rootElement() {
        if (!this.isEmpty())
            return heap[0];
        return null;
    }

    @Override
    public T[] heapsort(T[] array) {
        if(array != null && array.length >0 ) {
            T[] resultado = (T[]) new Comparable[array.length];

            Comparator<T> comparatorOriginal = getComparator();

            setComparator((o1, o2) -> o2.compareTo(o1));

            this.buildHeap(array);

            for (int i = 0; i < array.length; i++) {
                resultado[i] = this.extractRootElement();
            }

            setComparator(comparatorOriginal);
            return resultado;
        }
        return null;
    }

    @Override
    public int size() {
        return index + 1;
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

}
