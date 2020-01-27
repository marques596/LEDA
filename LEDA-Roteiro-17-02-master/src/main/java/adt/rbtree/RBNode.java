package adt.rbtree;

import adt.bst.BSTNode;
import adt.bt.BTNode;

import java.lang.instrument.UnmodifiableClassException;

public class RBNode<T extends Comparable<T>> extends BSTNode<T> {
	enum Colour {
		BLACK, RED
	};

	private Colour colour;

	public RBNode() {
		this.colour = Colour.BLACK;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		if (isEmpty() && colour == Colour.RED) {
			throw new UnsupportedOperationException(
					"NIL node colour cannot be RED");
		}
		this.colour = colour;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& this.colour == ((RBNode<T>) obj).getColour();
	}

	@Override
	public String toString() {
		String resp = "NIL";
		if (!isEmpty()) {
			resp = "(" + data.toString();
			if (colour == Colour.RED) {
				resp = resp + ",R)";
			} else {
				resp = resp + ",B)";
			}
		}
		return resp;
	}


	public boolean isLeftChild(){
		RBNode parent = (RBNode) this.getParent();
		boolean result = false;

		if(this.equals(parent.getLeft()))
			result = true;

		return result;

	}

	public boolean isRightChild(){
		RBNode parent = (RBNode) this.getParent();
		boolean result = false;

		if(this.equals(parent.getRight()))
			result = true;

		return result;

	}

	public RBNode getGrandParent(){
		return (RBNode) this.getParent().getParent();
	}

	public RBNode getUncle(){
		RBNode parent = (RBNode) this.getParent();
		RBNode result = null;

		if(parent.isLeftChild())
			result = (RBNode) this.getGrandParent().getRight();
		else
			result = (RBNode) this.getGrandParent().getLeft();

		return result;
	}

}
