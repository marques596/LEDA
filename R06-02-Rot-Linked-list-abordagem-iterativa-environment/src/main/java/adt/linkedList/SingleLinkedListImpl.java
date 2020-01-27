package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (head.getNext() == null)
			return true;
		return false;
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> node = head;
		int size = 0;
		while (!node.isNIL()) {
			size++;
			node = node.next;
		}

		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = head;
		T result = null;
		while (!node.isNIL() && node.getData() != element) {
			node = node.getNext();
		}
		result = (T) node.getData();
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, head);
			head = newHead;
			
		} else {
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, aux.getNext());
			aux.setNext(newNode);

		}
	}

	@Override
	public void remove(T element) {
		//caso onde o node a ser removido eh o primeiro da lista
		if(head.getData() == element) {
			head= head.getNext();
		} else {//pesquisar qual o node a ser removido
			SingleLinkedListNode<T> aux = head;
			SingleLinkedListNode<T> previous = null;
			while(!aux.isNIL() && !(aux.getData() == element)) {
				
				previous = aux;
				aux = aux.getNext();
			}
			//remove o node da lista
			if(!aux.isNIL()) {
				previous.next= aux.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result =(T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = head;
		int indice = 0;
		while (!aux.isNIL()) {
			result[indice++] = (T) aux.getData();
			aux = aux.getNext();
		}

		
		
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
