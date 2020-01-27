package adt.btree;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

   protected BNode<T> root;
   protected int order;

   public BTreeImpl(int order) {
      this.order = order;
      this.root = new BNode<T>(order);
   }

   @Override
   public BNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return this.root.isEmpty();
   }

   @Override
   public int height() {
      return height(this.root) - 1;
   }

   private int height(BNode<T> node) {

      if (node != null && !node.isEmpty()) {
         if (node.isLeaf()) {
            return 1;
         } else {
            return 1 + height(node.getChildren().get(0));
         }
      }
      return 0;
   }

   private int maiorDoArray(int[] array) {
      int maior = -1;
      for (int i = 0; i < array.length; i++) {
         if (array[i] > maior)
            maior = array[i];
      }
      return maior;

   }

   @Override
   public BNode<T>[] depthLeftOrder() {

      BNode<T>[] array;
      List<BNode<T>> lista = new ArrayList<BNode<T>>();

      depthLeftOrder(lista, this.root);

      array = new BNode[lista.size()];

      return lista.toArray(array);
   }

   private void depthLeftOrder(List<BNode<T>> lista, BNode<T> node) {

      if (!node.isEmpty()) {

         lista.add(node);
         for (BNode<T> aux : node.getChildren()) {
            depthLeftOrder(lista, aux);
         }

      }

   }

   @Override
   public int size() {
      return size(root);
   }

   private int size(BNode<T> node) {
      int size = 0;

      if (!node.isEmpty()) {
         size += node.size();

         for (int i = 0; i < node.getChildren().size(); i++) {
            size += size(node.getChildren().get(i));
         }

      }

      return size;

   }

   @Override
   public BNodePosition<T> search(T element) {

      BNodePosition result = null;
      if (element != null)
         result = search(element, root);

      return result;
   }

   private BNodePosition<T> search(T element, BNode<T> node) {
      int indice = 0;
      while (indice < node.size() && node.getElementAt(indice).compareTo(element) < 0) {
         indice++;
      }
      if (indice < node.size() && node.getElementAt(indice).compareTo(element) == 0) {
         return new BNodePosition<T>(node, indice);
      }
      if (node.isLeaf()) {
         return null;
      }
      return search(element, node.getChildren().get(indice));
   }

   @Override
   public void insert(T element) {
      if (element != null)
         insert(element, root);

   }

   private void insert(T element, BNode<T> node) {
      if (!node.isFull() && node.isLeaf()) {
         node.addElement(element);
      } else if (node.isFull() && node.isLeaf()) {
         node.addElement(element);
         promote(node);
         split(node);
         BNode aux = node.getParent();

         while (aux.size() == aux.getOrder()) {
            promote(aux);
            split(aux);

            aux = aux.getParent();
         }

      } else if (!node.isFull() && !node.isLeaf() || node.isFull() && !node.isLeaf()) {
         int indice = 0;
         while (indice < node.size() && node.getElementAt(indice).compareTo(element) < 0) {
            indice++;
         }

         insert(element, node.getChildren().get(indice));

      }

   }

   private void split(BNode<T> node) {

      node.split();
   }

   private void promote(BNode<T> node) {
      if (!node.isEmpty()) {

         if (node.equals(root)) {
            BNode newRoot = new BNode(this.order);
            newRoot.setParent(root.getParent());
            newRoot.addChild(0, root);

            root.setParent(newRoot);

            this.root = newRoot;
         }
         node.promote();

      }
   }

   // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
   @Override
   public BNode<T> maximum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public BNode<T> minimum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public void remove(T element) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

    public static void main(String[] args) {
        BTreeImpl arvore = new BTreeImpl<Integer>(4);

        Integer[] array = {15,16,17,18,14,13,19,12,20,11,10,21,9};
        for (Integer a:array
             ) {
            arvore.insert(a);
        }

        System.out.println(Arrays.toString(arvore.depthLeftOrder()));
    }
}
