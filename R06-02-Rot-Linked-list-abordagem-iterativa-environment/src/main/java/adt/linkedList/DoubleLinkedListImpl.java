package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	
	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertFirst(T element) {
		
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
		newHead.setData(element);
		newHead.setNext(head);
		newHead.setPrevious(new DoubleLinkedListNode<T>());
		
		if(head.isNIL()) {
			last = newHead;
			head = newHead;
		}
		((DoubleLinkedListNode<T>) head).setPrevious(newHead);
		((DoubleLinkedListNode<T>) head).getPrevious().setNext(newHead);
		
		head = newHead;

	}

	@Override
	public void removeFirst() {
		if (!head.isNIL()) {
			head = (DoubleLinkedListNode<T>) head.getNext();
			if (head.isNIL())
				last = (DoubleLinkedListNode<T>) head;
		}
		((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeLast() {
		if(!last.isNIL()) {
			last=last.getPrevious();
			if(last.isNIL())
				head=last;
				
				
		}
		last.setNext(new SingleLinkedListNode<T>());
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
