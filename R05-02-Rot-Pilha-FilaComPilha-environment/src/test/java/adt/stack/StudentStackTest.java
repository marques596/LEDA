package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StudentStackTest {

	public Stack<Integer> pilhaQuaseCheia;
	public Stack<Integer> pilhaCheia;
	public Stack<Integer> pilhaVazia;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		pilhaQuaseCheia.push(1);
		pilhaQuaseCheia.push(2);
		pilhaQuaseCheia.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		pilhaCheia.push(1);
		pilhaCheia.push(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		pilhaQuaseCheia = new StackImpl(4);
		pilhaCheia = new StackImpl(2);
		pilhaVazia = new StackImpl(3);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(new Integer(3), pilhaQuaseCheia.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(pilhaQuaseCheia.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(pilhaQuaseCheia.isFull()); // vai depender do tamanho que a pilha foi
										// iniciada!!!!
	}

	@Test
	public void testPush() {
		try {
			pilhaQuaseCheia.push(new Integer(5));
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		pilhaCheia.push(new Integer(5)); // levanta excecao apenas se o tamanho nao
										// permitir outra insercao
	}

	@Test
	public void testPop() {
		try {
			assertEquals(new Integer(3), pilhaQuaseCheia.pop());
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals(new Integer(3), pilhaVazia.pop()); // levanta excecao apenas se
													// stack3 for vazia
	}
}