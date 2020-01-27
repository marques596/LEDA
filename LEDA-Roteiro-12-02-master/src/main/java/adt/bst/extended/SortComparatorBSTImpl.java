package adt.bst.extended;

import java.util.*;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
   }

   @Override
   public void insert(T element) {
      insert(element, root);
   }

   private void insert(T element, BSTNode<T> node) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new BSTNode<T>());
         node.getLeft().setParent(node);
         node.setRight(new BSTNode<T>());
         node.getRight().setParent(node);

      } else {
         if (getComparator().compare(element, node.getData()) < 0) {

            insert(element, (BSTNode<T>) node.getLeft());

         }
         if (getComparator().compare(element, node.getData()) > 0) {

            insert(element, (BSTNode<T>) node.getRight());
         }
      }

   }

   @Override
   public BSTNode<T> search(T element) {
      return search(root, element);
   }

   private BSTNode<T> search(BSTNode<T> node, T element) {
      if (node.isEmpty() || getComparator().compare(node.getData(), element) == 0) {
         return node;
      }

      if (getComparator().compare(node.getData(), element) > 0) {
         return search((BSTNode<T>) node.getLeft(), element);
      } else
         return search((BSTNode<T>) node.getRight(), element);
   }

   @Override
   public T[] sort(T[] array) {
      List<T> lista = Arrays.asList(array);
      BSTImpl<T> arvore = new BSTImpl<T>();

      for (T elemento : array) {
         arvore.insert(elemento);
      }

      return arvore.order();
   }

   @Override
   public T[] reverseOrder() {
      T[] result = (T[]) new Comparable[this.size()];
      ArrayList<T> aux = new ArrayList<T>();

      reverseOrder(this.root, aux);

      return (T[]) aux.toArray(result);

   }

   private ArrayList<T> reverseOrder(BTNode<T> node, ArrayList<T> lista) {
      if (!node.isEmpty()) {
         reverseOrder(node.getRight(), lista);
         lista.add(node.getData());
         reverseOrder(node.getLeft(), lista);

      }
      return lista;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public static void main(String[] args) {
      Integer[] array = {5,4,3,2,1};

      Comparator<Integer> comparador = new Comparator<Integer>() {
         @Override
         public int compare(Integer int1, Integer int2) {
            if (int1<int2)
               return -1;

            if(int1>int2)
               return 1;

            return 0;
         }
      };

      SortComparatorBSTImpl<Integer> arvore = new SortComparatorBSTImpl<Integer>(comparador);

      System.out.println(Arrays.toString(arvore.sort(array)));
      arvore.insert(4);
      arvore.insert(5);

      arvore.insert(3);

      arvore.insert(2);

      arvore.insert(1);

      System.out.println(Arrays.toString(arvore.order()) );
      System.out.println(Arrays.toString(arvore.reverseOrder()) );
   }
}
