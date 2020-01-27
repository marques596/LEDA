package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;
import java.util.Arrays;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {
        return blackHeight((RBNode) root);
    }


    private int blackHeight(RBNode<T> node) {
        if (node.isEmpty())
            return 0;
        else if (node.getColour().compareTo(Colour.BLACK) == 0)
            return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
        else
            return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));


    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour()
                && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        boolean result = true;
        RBNode<T>[] array = this.rbPreOrder();

        for (int i = 0; i < array.length; i++) {
            if (array[i].getColour().equals(Colour.RED)) {

                if (((RBNode<T>) array[i].getRight()).getColour().equals(Colour.RED) || ((RBNode<T>) array[i].getLeft()).getColour().equals(Colour.RED))
                    result = false;

            }

        }

        return result;
    }

    /**
     * Verifies the black-height property from the root.
     */
    private boolean verifyBlackHeight() {
        return blackHeight((RBNode<T>) root.getLeft()) == blackHeight((RBNode<T>) root.getRight());
    }

    @Override
    public void insert(T value) {
        if (value != null)
            insert(value, (RBNode<T>) root);

    }

    private void insert(T value, RBNode<T> node) {
        if (node.isEmpty()) {
            node.setData(value);
            node.setLeft(new RBNode<T>());
            node.getLeft().setParent(node);
            node.setRight(new RBNode<T>());
            node.getRight().setParent(node);

            node.setColour(Colour.RED);

            fixUpCase1(node);

        } else {
            if (value.compareTo(node.getData()) < 0) {

                insert(value, (RBNode<T>) node.getLeft());

            }
            if (value.compareTo(node.getData()) > 0) {

                insert(value, (RBNode<T>) node.getRight());
            }
        }

    }

    @Override
    public RBNode<T>[] rbPreOrder() {

        RBNode<T>[] result = new RBNode[this.size()];
        ArrayList<RBNode<T>> aux = new ArrayList<>();
        RBNode root = (RBNode) this.root;

        rbPreOrder(root, aux);

        return aux.toArray(result);

    }


    private ArrayList<RBNode<T>> rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> list) {
        if (!node.isEmpty()) {
            list.add(node);
            RBNode left = (RBNode) node.getLeft();
            RBNode right = (RBNode) node.getRight();

            rbPreOrder(left, list);
            rbPreOrder(right, list);
        }
        return list;
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (node.equals(root))
            node.setColour(Colour.BLACK);
        else
            fixUpCase2(node);
    }

    protected void fixUpCase2(RBNode<T> node) {
        RBNode parent = (RBNode) node.getParent();

        if (!parent.getColour().equals(Colour.BLACK))
            fixUpCase3(node);
    }

    protected void fixUpCase3(RBNode<T> node) {
        RBNode uncle = node.getUncle();
        RBNode parent = (RBNode) node.getParent();

        if (uncle.getColour().equals(Colour.RED)) {
            parent.setColour(Colour.BLACK);
            uncle.setColour(Colour.BLACK);
            node.getGrandParent().setColour(Colour.RED);
            fixUpCase1(node.getGrandParent());
        } else
            fixUpCase4(node);
    }

    protected void fixUpCase4(RBNode<T> node) {
        RBNode<T> next = node;
        RBNode parent = (RBNode) node.getParent();


        if (node.isRightChild() && parent.isLeftChild()) {
            next.getGrandParent().setLeft(Util.leftRotation(parent));
            next = (RBNode<T>) node.getLeft();
        } else if (node.isLeftChild() && parent.isRightChild()) {
            next.getGrandParent().setRight(Util.rightRotation(parent));
            next = (RBNode<T>) node.getRight();

        }

        fixUpCase5(next);

    }

    protected void fixUpCase5(RBNode<T> node) {
        RBNode parent = (RBNode) node.getParent();
        RBNode grandParent = node.getGrandParent();

        parent.setColour(Colour.BLACK);
        grandParent.setColour(Colour.RED);

        if (node.isLeftChild())
            grandParent.getParent().setLeft(Util.rightRotation(grandParent));
        else
            grandParent.getParent().setRight(Util.leftRotation(grandParent));
    }

}
