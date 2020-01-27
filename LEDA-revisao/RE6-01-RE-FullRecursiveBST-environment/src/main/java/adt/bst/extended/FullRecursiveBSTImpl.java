package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum(){
		BSTNode<T> result = null;
		if (!this.isEmpty())
			result =  maximum(root);

		return result;
	}

	private BSTNode<T> maximum(BSTNode<T> node){
		if(node.isLeaf()){
			return node;
		}
		else{
			return maximum((BSTNode<T>) node.getRight());

		}
	}

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum(){
		BSTNode<T> result = null;
		if (!this.isEmpty())
			result =  minimum(root);

		return result;
	}

	private BSTNode<T> minimum(BSTNode<T> node){
		if(node.isLeaf()){
			return node;
		}
		else{
			return minimum((BSTNode<T>) node.getLeft());

		}
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario
	 * o sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode node = search(element);

		if (!node.getRight().isEmpty())
			return buscaEsquerda((BSTNode<T>) node.getRight());

		return sucessor(node,element);
	}
	private BSTNode<T> sucessor( BSTNode<T> node, T element) {
		if (node.getData().compareTo(element) > 0){
			return  node;
		}else{
			return sucessor((BSTNode<T>) node.getParent(),element);

		}
	}
	private BSTNode<T> buscaEsquerda(BSTNode<T> node){
		if (node.getLeft().isEmpty())
			return node;
		else
			return buscaEsquerda((BSTNode<T>) node.getLeft());


	}

		/**
         * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
         * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
         * o predecessor sera o primeiro ascendente a ter um valor menor.
         */
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode node = search(element);

		if (!node.getLeft().isEmpty())
			return buscaDireita((BSTNode<T>) node.getLeft());

		return predecessor(node,element);
	}

	private BSTNode<T> predecessor(BSTNode<T> node , T element) {
		if (node.getData().compareTo(element) < 0){
			return node;
		}else{
			return predecessor((BSTNode<T>) node.getParent(), element);

		}
	}

	private BSTNode<T> buscaDireita(BSTNode<T> node){
		if (node.getRight().isEmpty())
			return node;
		else
			return buscaDireita((BSTNode<T>) node.getRight());

	}
	@Override
	public T[] elementsAtDistance(int k) {
		ArrayList result = new ArrayList<T>();



		elementsAtDistance(0,root, k, result);
		T[] aux = (T[]) new Comparable[result.size()];

		return (T[]) result.toArray(aux);
	}

	private void elementsAtDistance(int k, BSTNode<T> node, int original, ArrayList<T> list) {
		if(k == original){
			 list.add(node.getData());
		}else{
			if (!node.getLeft().isEmpty())
				elementsAtDistance(k + 1, (BSTNode<T>) node.getLeft(), original, list);

			if (!node.getRight().isEmpty())
				elementsAtDistance(k+1, (BSTNode<T>) node.getRight(), original, list);


		}

	}

	//Funcoes da lista

//	Considere o código de uma BST abaixo. Utilizando recursão (no próprio método ou em algum
//			método auxiliar), complete a implementação do método descendingOrder() , que retorna um
//	array contendo as chaves da árvore ordenadas em valor decrescente. Faça a análise de seu
//	algoritmo. Obs: sua implementação NÃO pode originar um array ordenado e depois inverter!

	public T[] descendingOrder(){
		if (!this.isEmpty()){
			ArrayList<T> aux = new ArrayList<T>();

			T[] result = (T[]) new Comparable[this.size()];

			descendingOrder(root,aux);


			return aux.toArray(result);

		}
		return null;

	}
	private void descendingOrder(BSTNode<T> node, ArrayList<T> list){

			if(!node.getRight().isEmpty())
			descendingOrder((BSTNode<T>) node.getRight(), list);

			list.add(node.getData());

			if(!node.getLeft().isEmpty())
				descendingOrder((BSTNode<T>) node.getLeft(),list);




	}


//	Implemente um metodo (usando recursão) que retorna a quantidade de folhas em uma árvore
//	binaria. Faça a análise de seu algoritmo.
	public int quantasFolhasTem(){

		if (!this.isEmpty())
			return quantasFolhasTem(root);
		return 0;
	}

	private int quantasFolhasTem(BSTNode<T> node){
		if (node.isLeaf())
			return 1;

		else{
			int result = 0;
			if (!node.getRight().isEmpty() && !node.getLeft().isEmpty())
				result +=quantasFolhasTem((BSTNode<T>) node.getRight()) + quantasFolhasTem((BSTNode<T>) node.getLeft());

			else if(!node.getRight().isEmpty())
				result +=quantasFolhasTem((BSTNode<T>) node.getRight());
			else if (!node.getLeft().isEmpty())
				result += quantasFolhasTem((BSTNode<T>) node.getLeft());

			return result;
		}

	}

//	Considerando a representação acima e usando recursão (no próprio método ou em um método
//			auxiliar) escreva um método que diz se uma BST é igual a outra recebida como parâmetro. Faça a
//	análise de seu algoritmo

	// pega as duas arvores e faz um order ou preorder ou postorder
	// compara os 2 arrays
	// complexidade 3n = O(n) , n pra pegar o primeiro array, n pra pegar o segundo e outro n pra comparar.


		public static void main(String[] args) {
		FullRecursiveBSTImpl<Integer> tree =  new FullRecursiveBSTImpl<>();

		Integer[] array = {5,2,7,1,3,6,8,9,-1};
		for (Integer numero: array
			 ) {
			tree.insert(numero);
		}

		System.out.println(Arrays.toString(tree.preOrder()));

		System.out.println(Arrays.toString(tree.elementsAtDistance(3)));


		System.out.println("maximo");
		System.out.println(tree.maximum().toString());

		System.out.println("minimo");
		System.out.println(tree.minimum().toString());

		System.out.println("predecessor");
		System.out.println(tree.predecessor(5));


		System.out.println("sucessor");
		System.out.println(tree.sucessor(3));


		System.out.println("descending order");

		FullRecursiveBSTImpl<Integer> arvore =  new FullRecursiveBSTImpl<>();


		Integer[] arr = {7,3,11,1,4,10,12};
		for (Integer numero: arr
		) {
			arvore.insert(numero);
		}

		System.out.println(Arrays.toString(arvore.descendingOrder()));

		System.out.println("Quantas folhas");

		System.out.println(arvore.quantasFolhasTem());


	}

}
