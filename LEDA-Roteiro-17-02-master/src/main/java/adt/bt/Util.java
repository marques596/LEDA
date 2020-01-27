package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> right = node.getRight();

		node.setRight(right.getLeft());
		right.getLeft().setParent(node);

		right.setParent(node.getParent());

		right.setLeft(node);
		node.setParent(right);

		return (BSTNode<T>) right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {

		BTNode<T> left = node.getLeft();

		node.setLeft(left.getRight());
		left.getRight().setParent(node);

		left.setParent(node.getParent());

		left.setRight(node);
		node.setParent(left);

		return (BSTNode<T>) left;


	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}