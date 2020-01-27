package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = this.head();
		list.removeFirst();
		return result;
	}

	@Override
	public T head() {
		return ((SingleLinkedListImpl<T>) list).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		if(list.size() == 0) 
			return true;
		return false;
	}

	@Override
	public boolean isFull() {
		if(list.size() == this.size)
			return true;
			
		return false;
	}

}
