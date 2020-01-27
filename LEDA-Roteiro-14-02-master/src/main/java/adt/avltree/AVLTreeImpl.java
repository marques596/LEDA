package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
        AVLTree<T> {

    // TODO Do not forget: you must override the methods insert and remove
    // TODO conveniently.

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
            rebalance(node);
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
                rebalanceUp(node);

            } else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) { //caso em que o node tem 2 filhos

                BSTNode<T> sucessor = sucessor(node.getData());
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
                rebalanceUp(node);


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
                rebalanceUp(node);

            }

        }

    }

    // AUXILIARY
    protected int calculateBalance(BSTNode<T> node) {
        if (!node.isEmpty())
            return inclinacao((BSTNode<T>) node.getLeft()) - inclinacao((BSTNode<T>) node.getRight());

        return 0;
    }

    private int inclinacao(BSTNode<T> node) {
        if (node.isEmpty()) {
            return 0;
        }
        if (node.isLeaf()) {
            return 1;
        }
        return 1 + Math.max(inclinacao((BSTNode<T>) node.getRight()), inclinacao((BSTNode<T>) node.getLeft()));

    }

    // AUXILIARY
    protected void rebalance(BSTNode<T> node) {
        int balance = calculateBalance(node);

        //LR
        if (calculateBalance(node) > 1 && calculateBalance((BSTNode<T>) node.getLeft()) == -1) { //LR
            node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));

            if (node.equals(root))
                root = Util.rightRotation(node);
            else
                Util.rightRotation(node);

        } else if (calculateBalance(node) < -1 && calculateBalance((BSTNode<T>) node.getRight()) == 1) { //RL
            node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
            if (node.equals(root))
             root = Util.leftRotation(node);
            else Util.leftRotation(node);

        } else if (calculateBalance(node) > 1) { //LL
            if (node.equals(root))
                root = Util.rightRotation(node);

            else
                Util.rightRotation(node);


        } else if (calculateBalance(node) < -1) { //RR
            if (node.equals(root))
                root = Util.leftRotation(node);
            else
                Util.leftRotation(node);
        }

    }


    // AUXILIARY
    protected void rebalanceUp(BSTNode<T> node) {
        BSTNode<T> parent = (BSTNode<T>) node.getParent();

        while (parent != null) {
            rebalance(parent);
            parent = (BSTNode<T>) parent.getParent();
        }
    }

}


