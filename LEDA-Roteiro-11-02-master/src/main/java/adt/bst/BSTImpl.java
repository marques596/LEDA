package adt.bst;

import adt.bt.BTNode;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

    protected BSTNode<T> root;

    public BSTImpl() {
        root = new BSTNode<T>();
    }

    public BSTNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int height() {
        if (root.isEmpty()) {
            return -1;
        } else return height(root) - 1;
    }

    private int height(BSTNode<T> node) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (!node.getLeft().isEmpty() && node != null)
            leftHeight = height((BSTNode<T>) node.getLeft());

        if (!node.getRight().isEmpty() && node != null)
            rightHeight = height((BSTNode<T>) node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public BSTNode<T> search(T element) {
        return search(root, element);
    }

    private BSTNode<T> search(BSTNode<T> node, T element) {
        if (node.isEmpty() || node.getData().compareTo(element) == 0) {
            return node;
        }

        if (node.getData().compareTo(element) > 0) {
            return search((BSTNode<T>) node.getLeft(), element);
        } else
            return search((BSTNode<T>) node.getRight(), element);
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
            if (element.compareTo(node.getData()) < 0) {

                insert(element, (BSTNode<T>) node.getLeft());

            }
            if (element.compareTo(node.getData()) > 0) {

                insert(element, (BSTNode<T>) node.getRight());
            }
        }

    }

    @Override
    public BSTNode<T> maximum() {
        if (maximum(root).isEmpty())
            return null;
        return maximum(root);
    }

    private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> atual = node;

        while (atual.getRight() != null && !atual.getRight().isEmpty()) {
            atual = (BSTNode<T>) atual.getRight();
        }
        return atual;
    }

    @Override
    public BSTNode<T> minimum() {
        if (minimum(root).isEmpty())
            return null;
        return minimum(root);
    }

    private BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> atual = node;

        while (atual.getLeft() != null && !atual.getLeft().isEmpty()) {
            atual = (BSTNode<T>) atual.getLeft();

        }
        return atual;
    }

    @Override
    public BSTNode<T> sucessor(T element) {
        return sucessor(search(element));
    }

    private BSTNode<T> sucessor(BSTNode<T> node) {

        if (node.getData() != null && !this.isEmpty() && !node.isEmpty()) {
            if (!node.getRight().isEmpty()) {
                return minimum((BSTNode<T>) node.getRight());
            }
            BSTNode<T> sucessor = (BSTNode<T>) node.getParent();
            BSTNode<T> atual = node;
            while (sucessor != null && !sucessor.isEmpty() && atual.equals(sucessor.getRight())) {
                atual = sucessor;
                sucessor = (BSTNode<T>) sucessor.getParent();
            }
            return sucessor;
        } else {
            return null;
        }
    }

    @Override
    public BSTNode<T> predecessor(T element) {


        return predecessor(search(element));
    }

    private BSTNode<T> predecessor(BSTNode<T> node) {
        if (node.getData() != null && !this.isEmpty() && !node.isEmpty()) {
            if (!node.getLeft().isEmpty()) {
                return maximum((BSTNode<T>) node.getLeft());
            }
            BSTNode<T> predecessor = (BSTNode<T>) node.getParent();
            BSTNode<T> atual = node;
            while (predecessor != null && !predecessor.isEmpty() && atual.equals(predecessor.getLeft())) {
                atual = predecessor;
                predecessor = (BSTNode<T>) predecessor.getParent();
            }
            return predecessor;
        } else {
            return null;
        }

    }

    @Override
    public void remove(T element) {

        if (element != null && !search(element).isEmpty()) {
            remove(search(element));
        }

    }

    private void remove(BSTNode<T> node) {

        if (!node.isEmpty()) {

            // caso em que o node a ser removido nao tem filhos
            if (node.isLeaf()) {
                node.setData(null);
                node.setRight(null);
                node.setLeft(null);

            } else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) { //caso em que o node tem 2 filhos

                BSTNode<T> sucessor = sucessor(node);
                node.setData(sucessor.getData());
                remove(sucessor);

            } else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) { //caso em que so tem o filho direito

                node.getRight().setParent(node.getParent());

                if (node.getParent() != null) {

                    if (node.getParent().getLeft().equals(node)) {
                        node.getParent().setLeft(node.getRight());

                    } else {
                        node.getParent().setRight(node.getRight());
                    }

                } else {
                    this.root = (BSTNode<T>) root.getRight();
                    node.setRight(null);
                }

            } else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) { //caso em que so tem o filho esquerdo
                node.getLeft().setParent(node.getParent());
                if (node.getParent() != null) {
                    if (node.getParent().getRight().equals(node)) {
                        node.getParent().setRight(node.getLeft());

                    } else {
                        node.getParent().setLeft(node.getLeft());
                    }
                } else {
                    this.root = (BSTNode<T>) root.getLeft();
                    node.setLeft(null);

                }

            }

        }

    }


    @Override
    public T[] preOrder() {
        T[] result = (T[]) new Comparable[this.size()];
        ArrayList<T> aux = new ArrayList<T>();

        preOrder(this.root, aux);

        return (T[]) aux.toArray(result);

    }

    private ArrayList<T> preOrder(BTNode<T> node, ArrayList<T> lista) {
        if (!node.isEmpty()) {
            lista.add(node.getData());
            preOrder(node.getLeft(), lista);
            preOrder(node.getRight(), lista);
        }
        return lista;
    }

    @Override
    public T[] order() {
        T[] result = (T[]) new Comparable[this.size()];
        ArrayList<T> aux = new ArrayList<T>();

        order(this.root, aux);

        return (T[]) aux.toArray(result);

    }

    private ArrayList<T> order(BTNode<T> node, ArrayList<T> lista) {
        if (!node.isEmpty()) {
            order(node.getLeft(), lista);
            lista.add(node.getData());
            order(node.getRight(), lista);
        }
        return lista;
    }

    @Override
    public T[] postOrder() {
        T[] result = (T[]) new Comparable[this.size()];
        ArrayList<T> aux = new ArrayList<T>();

        postOrder(this.root, aux);

        return (T[]) aux.toArray(result);

    }

    private ArrayList<T> postOrder(BTNode<T> node, ArrayList<T> lista) {
        if (!node.isEmpty()) {
            postOrder(node.getLeft(), lista);
            postOrder(node.getRight(), lista);
            lista.add(node.getData());

        }
        return lista;
    }

    /**
     * This method is already implemented using recursion. You must understand
     * how it work and use similar idea with the other methods.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode<T> node) {
        int result = 0;
        // base case means doing nothing (return 0)
        if (!node.isEmpty()) { // indusctive case
            result = 1 + size((BSTNode<T>) node.getLeft())
                    + size((BSTNode<T>) node.getRight());
        }
        return result;
    }

    public static void main(String[] args) {
        BSTImpl<Integer> arvore = new BSTImpl<Integer>();

        arvore.insert(12);
        arvore.insert(5);
        arvore.insert(15);
        arvore.insert(3);
        arvore.insert(7);
        arvore.insert(1);
        arvore.insert(9);
        arvore.insert(13);
        arvore.insert(17);
        arvore.insert(19);


        System.out.println(arvore.search(17).getRight());

        arvore.remove(19);
        System.out.println(arvore.search(17).getRight());


        System.out.println(arvore.search(5).getRight());
        arvore.remove(7);
        System.out.println(arvore.search(5).getRight());


    }
}
