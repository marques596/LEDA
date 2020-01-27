package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(this.isEmpty())
			return null;
		else
			return array[0];
	}

	@Override
	public boolean isEmpty() {
		if (tail ==-1)
			return (true);
		else
			return false;
	}

	@Override
	public boolean isFull() {
		if (tail== array.length-1)
			return true;
		else return false;
	}

	private void shiftLeft() {
		if(!this.isEmpty()) {
			for (int i = 0; i < tail - 1; i++) {
				array[i] = array[i + 1];

			}
		}

	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()) throw new QueueOverflowException();
		tail++;
		array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()) throw new QueueUnderflowException();

		tail--;
		this.shiftLeft();
		return array[0];
	}

}
