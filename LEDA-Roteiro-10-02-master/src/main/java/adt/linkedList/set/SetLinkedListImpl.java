package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

import java.util.Arrays;


public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {


    public static void main(String[] args) {


            SetLinkedList<Integer> set1 = new SetLinkedListImpl<Integer>();


            System.out.println("Teste de removeDuplicates");
            set1.insert(1);
            set1.insert(2);
            set1.insert(2);
            set1.insert(3);
            set1.insert(3);
            System.out.println(Arrays.toString(set1.toArray()));
            set1.removeDuplicates();
            System.out.println(Arrays.toString(set1.toArray()));
            System.out.println();

            System.out.println("Teste de concatenate");
            SetLinkedList<Integer> set2 = new SetLinkedListImpl<Integer>();
            SetLinkedList<Integer> set3 = new SetLinkedListImpl<Integer>();

            set2.insert(1);
            set2.insert(2);
            set2.insert(2);
            set2.insert(3);
            set2.insert(3);
            System.out.println("set2: " + Arrays.toString(set2.toArray()));

            set3.insert(3);
            set3.insert(4);
            set3.insert(4);
            set3.insert(5);
            set3.insert(5);
            System.out.println("set3: " + Arrays.toString(set3.toArray()));

            set2.concatenate(set3);
            System.out.println("set2 concatenado: " + Arrays.toString(set2.toArray()));
            System.out.println("set3: " + Arrays.toString(set3.toArray()));
            System.out.println();

            System.out.println("Teste de union");
            SetLinkedList<Integer> set4 = new SetLinkedListImpl<Integer>();
            SetLinkedList<Integer> set5 = new SetLinkedListImpl<Integer>();

            set4.insert(1);
            set4.insert(2);
            set4.insert(2);
            set4.insert(3);
            set4.insert(3);

            set5.insert(1);
            set5.insert(2);
            set5.insert(2);
            set5.insert(5);
            set5.insert(4);
            System.out.println("set4: " + Arrays.toString(set4.toArray()));
            System.out.println("set5: " + Arrays.toString(set5.toArray()));

            System.out.println("union: " + Arrays.toString(set4.union(set5).toArray()));
            System.out.println("set4: " + Arrays.toString(set4.toArray()));
            System.out.println("set5: " + Arrays.toString(set5.toArray()));


            System.out.println();


            System.out.println("Teste de intersecao");
            SetLinkedList<Integer> set6 = new SetLinkedListImpl<Integer>();
            SetLinkedList<Integer> set7 = new SetLinkedListImpl<Integer>();

            set6.insert(1);
            set6.insert(2);
            set6.insert(3);
            set6.insert(4);
            set6.insert(4);

            set7.insert(3);
            set7.insert(4);
            set7.insert(5);
            set7.insert(5);
            set7.insert(1);
            System.out.println("set6: " + Arrays.toString(set6.toArray()));
            System.out.println("set7: " + Arrays.toString(set7.toArray()));
            System.out.println("intersecao: " + Arrays.toString(set6.intersection(set7).toArray()));
        System.out.println("set6: " + Arrays.toString(set6.toArray()));
        System.out.println("set7: " + Arrays.toString(set7.toArray()));

            System.out.println();


            System.out.println("Teste de uniao com testes vazios");

            SetLinkedList<Integer> set8 = new SetLinkedListImpl<Integer>();
            System.out.println("set7 e vazio: " + Arrays.toString(set7.toArray()));
            System.out.println("primeiro vazio");

            System.out.println(Arrays.toString(set8.union(set7).toArray()));
            System.out.println("Segundo vazio");

            System.out.println(Arrays.toString(set7.union(set8).toArray()));
            System.out.println("ambos vazios");

            System.out.println(Arrays.toString(set8.union(set8).toArray()));


            System.out.println("uniao consigo msm");
            System.out.println("set7 : " + Arrays.toString(set7.toArray()));
            System.out.println(Arrays.toString(set7.union(set7).toArray()));
            System.out.println();




            System.out.println("Teste de concatenar com testes vazios");


            System.out.println("set7 e vazio: " + Arrays.toString(set7.toArray()));
            System.out.println("primeiro vazio");
            set8.concatenate(set7);
            System.out.println("set vazio: "+Arrays.toString(set8.toArray()));
            System.out.println("set7:" + Arrays.toString(set7.toArray()));


            SetLinkedList<Integer> set9 = new SetLinkedListImpl<Integer>();

            System.out.println("Segundo vazio");
            set7.concatenate(set9);
            System.out.println("set vazio: " + Arrays.toString(set9.toArray()));
            System.out.println("set7:" + Arrays.toString(set7.toArray()));


            System.out.println("ambos vazios");
            set9.concatenate(set9);
            System.out.println(Arrays.toString(set9.toArray()));

            System.out.println("concatenar consigo msm");
            set7.concatenate(set7);
            System.out.println(Arrays.toString(set7.toArray()));
            System.out.println();



            System.out.println("Teste de intersecao com testes vazios");
            SetLinkedList<Integer> set10 = new SetLinkedListImpl<Integer>();

            System.out.println("set7 e vazio: " + Arrays.toString(set7.toArray()));
            System.out.println("primeiro vazio");

            System.out.println("intersecao:" + Arrays.toString(set10.intersection(set7).toArray()));
            System.out.println("set7: " + Arrays.toString(set7.toArray()));
            System.out.println("------------------------------");




            System.out.println("Segundo vazio");

            System.out.println("set vazio: " + Arrays.toString(set10.toArray()));
            System.out.println("intersecao:" + Arrays.toString(set7.intersection(set10).toArray()));

            System.out.println("------------------------------");
            System.out.println("ambos vazios");
            System.out.println("intersecao:" + Arrays.toString(set10.intersection(set10).toArray()));


            System.out.println("------------------------------");
            System.out.println("intersecao consigo msm");

            System.out.println("intersecao:" + Arrays.toString(set7.intersection(set7).toArray()));






    }



    @Override
    public void removeDuplicates() {
        if (!this.isEmpty()) {

            SetLinkedListImpl<T> aux = new SetLinkedListImpl<T>();
            SingleLinkedListNode<T> node = head;

            while (!node.isNIL()) {
                if (aux.search(node.getData()) == null) {
                    aux.insert(node.getData());
                }
                node = node.getNext();
            }

            this.head = aux.head;
        }


    }

    @Override
    public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

        SetLinkedList<T> result = duplicate(this);
        SetLinkedList<T> copy = duplicate((SetLinkedListImpl<T>) otherSet);

        if (this.isEmpty()) {
            copy.removeDuplicates();
            return copy;

        }
        if (copy.isEmpty()) {
            result.removeDuplicates();
            return result;
        }
        result.concatenate(copy);

        return result;
    }

    @Override
    public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {


        SetLinkedList<T> result = new SetLinkedListImpl();
        SingleLinkedListNode<T> node = head;

        if (this.isEmpty() || otherSet.isEmpty()) {
            return result;
        }

        while (!node.isNIL()) {
            if (otherSet.search(node.getData()) != null) {
                result.insert(node.getData());

            }
            node = node.getNext();

        }
        result.removeDuplicates();
        return result;
    }

    @Override
    public void concatenate(SetLinkedList<T> otherSet) {

        SetLinkedList<T> copy = duplicate((SetLinkedListImpl<T>) otherSet);
        SingleLinkedListNode<T> node = head;

        if (!this.equals(otherSet)) {
            if (this.isEmpty()) {
                this.head = ((SingleLinkedListImpl<T>) copy).getHead();

            } else if (!otherSet.isEmpty()) {

                while (!node.getNext().isNIL()) {
                    node = node.getNext();
                }

                node.setNext(((SingleLinkedListImpl<T>) copy).getHead());
                this.removeDuplicates();

            }
        }
    }


    private SetLinkedListImpl<T> duplicate(SetLinkedListImpl<T> set){


        SetLinkedListImpl<T> copy = new  SetLinkedListImpl<T>();

        if(set.isEmpty())
            return copy;

        T[] array = set.toArray();

        for (int i = 0; i < array.length; i++) {
            copy.insert(array[i]);
        }

        return copy;

    }
}