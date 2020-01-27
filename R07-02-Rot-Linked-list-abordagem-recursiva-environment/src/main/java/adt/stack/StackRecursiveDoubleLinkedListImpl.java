package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull())
			throw new StackOverflowException();
		top.insert(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		}
		T result = this.top();
		top.removeLast();
		return result;
	}

	@Override
	public T top() {
		if (this.isEmpty()) {
			return null;
		} else {
			T result = null;
			T[] temp = top.toArray();
			result = temp[size - 1];
			return result;
		}
	}

	@Override
	public boolean isEmpty() {
		if (top.size() == 0)
			return true;

		return false;
	}

	@Override
	public boolean isFull() {
		if (top.size() == this.size)
			return true;
		return false;
	}

}
