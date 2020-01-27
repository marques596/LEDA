package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
    protected LinkedList<T> elements; //PODERIA TRABALHAR COM ARRAY TAMBEM
    protected LinkedList<BNode<T>> children; //PODERIA TRABALHAR COM ARRAY TAMBEM
    protected BNode<T> parent;
    protected int order;

    public BNode(int order) {
        this.order = order;
        this.elements = new LinkedList<T>();
        this.children = new LinkedList<BNode<T>>();
    }

    @Override
    public String toString() {
        return this.elements.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean resp = false;
        if (obj != null) {
            if (obj instanceof BNode) {
                if (this.size() == ((BNode<T>) obj).size()) {
                    resp = true;
                    int i = 0;
                    while (i < this.size() && resp) {
                        resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
                        i++;
                    }
                }
            }
        }
        return resp;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.elements.size();
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public boolean isFull() {
        return this.size() == order - 1;
    }

    public void addElement(T element) {
        this.elements.add(element);
        Collections.sort(elements);
    }

    public void removeElement(T element) {
        this.elements.remove(element);
    }

    public void removeElement(int position) {
        this.elements.remove(position);
    }

    public void addChild(int position, BNode<T> child) {
        this.children.add(position, child);
        child.parent = this;
    }

    public void removeChild(BNode<T> child) {
        this.children.remove(child);
    }

    public int indexOfChild(BNode<T> child) {
        return this.children.indexOf(child);
    }

    public T getElementAt(int index) {
        return this.elements.get(index);
    }

    protected void split() {
        int indexNextChild = parent.indexOfChild(this) + 1;
        int mid = this.elements.size() / 2;

        BNode<T> nextChild = copySemChildren();
        nextChild.setParent(parent);

        // remover a segunda metade do node original
        for (int i = mid; i < elements.size(); i++) {
            this.removeElement(elements.size() - 1);
        }

        // remover a primeira metade do novo node
        // caso o node tenha um numero par de elementos
        if (nextChild.elements.size() % 2 == 0) {
            for (int i = 0; i < mid; i++) {
                nextChild.removeElement(0);

            }
        } else {// caso o ndoe tenha um numero impar de elementos

            for (int i = 0; i <= mid; i++) {
                nextChild.removeElement(0);
            }
        }
        if (this.getChildren().size() > order) {
            int metade = this.getChildren().size()/2;
            //copiar para o new child
            int iterador = 0;
            for (int i = metade; i < this.getChildren().size(); i++) {
                nextChild.addChild(iterador,this.getChildren().get(i));
                iterador++;
            }

            int aux = this.getChildren().size();
            //remover do node atual
            for (int i = aux-1; i >= metade; i--) {
                this.removeChild(this.getChildren().get(i));

            }

        }


        parent.addChild(indexNextChild, nextChild);

    }

    private BNode<T> copySemChildren() {
        BNode<T> result = new BNode<T>(order);
        result.parent = parent;
        for (int i = 0; i < this.elements.size(); i++) {
            result.addElement(this.elements.get(i));
        }
        return result;
    }

    protected void promote() {
        int mid = this.elements.size() / 2;

        T promoted = elements.get(mid);
        parent.addElement(promoted);
        this.removeElement(promoted);

    }

    public LinkedList<T> getElements() {
        return elements;
    }

    public void setElements(LinkedList<T> elements) {
        this.elements = elements;
    }

    public LinkedList<BNode<T>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<BNode<T>> children) {
        this.children = children;
    }

    public BNode<T> copy() {
        BNode<T> result = new BNode<T>(order);
        result.parent = parent;
        for (int i = 0; i < this.elements.size(); i++) {
            result.addElement(this.elements.get(i));
        }
        for (int i = 0; i < this.children.size(); i++) {
            result.addChild(i, ((BNode<T>) this.children.get(i)).copy());
        }

        return result;
    }

    public BNode<T> getParent() {
        return parent;
    }

    public void setParent(BNode<T> parent) {
        this.parent = parent;
    }

    public int getMaxKeys() {
        return order - 1;
    }

    public int getMaxChildren() {
        return order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


}
