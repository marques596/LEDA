package adt.skipList;

import java.util.Random;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode x = this.root;

		for(int i = x.height()-1; i >= 0; i--){
			while(x.getForward(i).getKey() < key){
				x = x.getForward(i);
			}
			update[i] = x;

		}
		x = x.getForward(0);

		if (x.getKey() == key){
			x.setValue(newValue);
		} else{
			int v = randomLvl();
			// usar v ao inves de height?
            x = new SkipListNode(key,height,newValue);
            for (int i = 0; i < height; i++) {
               x.getForward()[i] = update[i].getForward(i);
               update[i].getForward()[i] = x;
            }

		}

	}


	private int randomLvl(){
	    Random generateRandom = new Random();
		int number = generateRandom.nextInt(2);
        int level = 0;
		while (level < maxHeight && number < PROBABILITY){
		    level++;

        }
        return level;
	}



	@Override
	public void remove(int key) {
        SkipListNode[] update = new SkipListNode[maxHeight];
        SkipListNode x = this.root;

        for(int i = x.height()-1; i >= 0; i--){
            while(x.getForward(i).getKey() < key){
                x = x.getForward(i);
            }
            update[i] = x;

        }
        x = x.getForward(0);

        if (x.getKey() == key){
            for (int i = 0; i < x.height(); i++) {
                if(update[i].getForward()[i] != x)
                    break;
                update[i].getForward()[i] = x.getForward(i);
            }

        }
	}

	@Override
	public int height() {
		//este if ta errado, conceito de root
		if(this.root.getValue() == null){
			return 0;

		}
		SkipListNode atual = this.root;


		int maiorAltura = 0;

		//TODO for que percorre a lista comparando a altura dos nodes
		for (int i = atual.height(); i >= 0  ; i--) {

		}


		return maiorAltura;

	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode aux = this.root;

        for (int i = aux.height()-1; i >= 0 ; i--) {
            while(aux.getForward(i).getKey() < key){
                aux = aux.getForward(i);
            }
        }

        aux = aux.getForward(0);

        if(aux.getKey() == key)
            return aux;
        else
            return null;

	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode aux = this.root.getForward(0);

		while (aux.getKey() != Integer.MAX_VALUE){
			size++;
			aux = aux.getForward(0);

		}

		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode[] array = new SkipListNode[this.size()+2];
		SkipListNode aux = this.root;
		int iterador = 0;

		while (aux.getKey() != Integer.MAX_VALUE){
			array[iterador] = aux;
			iterador++;
			aux = aux.getForward(0);

		}

		array[iterador] = aux;
		return array;
	}

}
