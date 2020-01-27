package adt.heap.extended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import adt.heap.HeapImpl;
import util.Util;

public class HeapMergeImpl extends HeapImpl<Integer> implements HeapMerge {

    public HeapMergeImpl(Comparator<Integer> comparator) {
        super(comparator);
    }

    @Override
    public Integer[] mergeArraysAndOrder(List<Integer[]> arrays) {

        int tamanhoDoArray = 0;
        Comparator<Integer> comparatorOriginal = getComparator();

        setComparator(new ComparatorMinHeap<Integer>());

        for (Integer[] array : arrays
        ) {
            tamanhoDoArray += array.length;
            for (Integer numero : array
            ) {
                insert(numero);
            }


        }


        Integer[] resultado = new Integer[tamanhoDoArray];


        for (int i = 0; i < tamanhoDoArray; i++) {
            resultado[i] = extractRootElement();
        }

        setComparator(comparatorOriginal);

        return resultado;


    }

    @Override
    public int sumRange(int k1, int k2) {

        int result = 0;

        int tamanhoHeapInicial = size();


        if (this.ehMinHeap()) {
            //converter posicoes em indices
            k1--;
            k2--;
            for (int i = 0; i < tamanhoHeapInicial; i++) {
                if (i > k1 && i < k2) {
                    System.out.println("i: " + i);
                    result += extractRootElement();
                } else {
                    extractRootElement();
                }

            }

        } else {
            System.out.println("size: " + size());
			k1 = size() -k1-1;
			k2 = size() - k2+1;
            System.out.println(k1);
            System.out.println(k2);


            for (int i = 0; i < tamanhoHeapInicial; i++) {
                if (i <= k1 && i >= k2) {
                    System.out.println("i: " + i);
                    result += extractRootElement();
                } else {
                    extractRootElement();
                }

            }

        }





        return result;
    }

    // para uma heapMIn, ordena em ordem crescente
    // para uma heapMAx, ordena em ordem descrescescente
//	@Override
//	public Integer[] heapsort(Integer[] array) {
//
//		Integer[] result = new Integer[array.length];
//
//
//
//
//		this.buildHeap(array);
//
//		for (int i = 0; i < array.length; i++) {
//			result[i] = extractRootElement();
//		}
//
//
//		return result;
//	}

    private boolean ehMinHeap() {
        if (rootElement() > heap[2] && rootElement() > heap[1])
            return false;
        return true;


    }

    public static void main(String[] args) {
        List<Integer[]> arrays = new ArrayList<>();

        Integer[] a1 = {3, 1, 2};

        Integer[] a2 = {5, 4, 7};

        Integer[] a3 = {8, 9, 6};


        arrays.add(a1);
        arrays.add(a2);
        arrays.add(a3);


        HeapMergeImpl reep = new HeapMergeImpl((Comparator<Integer>) new ComparatorMaxHeap<>());


        System.out.println(Arrays.toString(reep.mergeArraysAndOrder(arrays)));


        System.out.println("sum testes");
        Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17};

        HeapMergeImpl minRip = new HeapMergeImpl((Comparator<Integer>) new ComparatorMinHeap<>());


        minRip.buildHeap(array);
        System.out.println(minRip.sumRange(3, 8));


        System.out.println("sum testes max heap");
        Integer[] array2 = {17, 15, 13, 11, 9, 7, 5, 3, 1};

        HeapMergeImpl maxRip = new HeapMergeImpl((Comparator<Integer>) new ComparatorMaxHeap<>());

        maxRip.buildHeap(array2);
        System.out.println(maxRip.sumRange(3, 8));

    }
}
