package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> filaQuaseCheia;
	public Queue<Integer> filaCheia;
	public Queue<Integer> filaVazia;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		filaQuaseCheia.enqueue(1);
		filaQuaseCheia.enqueue(2);
		filaQuaseCheia.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		filaCheia.enqueue(1);
		filaCheia.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		filaQuaseCheia = new QueueUsingStack(4);
		filaCheia = new QueueUsingStack(2);
		filaVazia = new QueueUsingStack(3);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), filaQuaseCheia.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(filaQuaseCheia.isEmpty());
		assertTrue(filaVazia.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(filaQuaseCheia.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			filaQuaseCheia.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		filaCheia.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), filaQuaseCheia.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		assertEquals(new Integer(1), filaVazia.dequeue()); // vai depender do
														// tamanho que a fial
														// foi iniciada!!!
	}
}