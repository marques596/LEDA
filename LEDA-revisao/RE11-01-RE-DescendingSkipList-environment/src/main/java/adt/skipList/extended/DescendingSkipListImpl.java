package adt.skipList.extended;

import java.util.LinkedList;

import adt.skipList.SkipListImpl;

/**
 * Esta skip list é uma skip list (com um método adicional) que armazena os
 * numeros em ordem descendente (do maior para o menor). Voce deve herdar da sua
 * implementação de skip list.
 *
 * Esta skip list guarda os elementos em ordem decrescente das chaves (Key) e
 * que possui um método adicional. Este método retorna uma LinkedList (de java
 * collections) contendo os elementos da skip list (Values) em ordem decrescente
 * da altura de seus nós (SkipNode). Os nós com mesma altura são colocados na
 * LinkedList em ordem decrescente das chaves de seus nós (Key).
 *
 * Os metodos insert, remove, height, search, size, toArray e skipListToList
 * serão testados na correção.
 * 
 * @author Adalberto Cajueiro
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class DescendingSkipListImpl<T> extends SkipListImpl<T> {

	public DescendingSkipListImpl(int maxLevel) {
		super(maxLevel);
	}

	/**
	 * Este método retorna uma linked list (de java collections) com os
	 * elementos da skip list em uma ordem especifica: a quantidade de operações
	 * para buscar o elemento na skip list é igual a quantidade de operações
	 * para buscar o mesmo elemento na lista. Isso significa que a ordem dos
	 * elementos na lista é diferente da ordem dos elementos na skip list. Voce
	 * precisará pensar sobre a logica do metodo procurar na skip list de forma
	 * a deduzir como eles devem ser colocados na linked list que sera
	 * retornada.
	 * 
	 * @return
	 */
	public LinkedList<T> skipListToList() {
		throw new UnsupportedOperationException("Method not implemented!!!");
	}

}
