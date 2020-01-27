package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
        super(size);
        hashFunction = new HashFunctionLinearProbing<T>(size, method);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {

            int probe = 0;

            while (probe < this.capacity()) {

                int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

                if (table[index] == null || table[index].equals(deletedElement)) {
                    table[index] = element;
                    elements++;
                    break;

                } else {
                    COLLISIONS++;
                    probe++;
                }
            }

        }
    }

    @Override
    public void remove(T element) {

        int index = this.indexOf(element);

        if (index != -1) {
            table[index] = deletedElement;
            elements--;
        }

    }

    @Override
    public T search(T element) {
        if (element != null) {
            int probe = 0;

            while (probe < this.capacity()) {
                int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

                if (table[index] == null) {
                    break;
                }
                if (table[index].equals(element)) {
                    return (T) table[index];
                }

                probe++;

            }
            return null;
        }
        return null;
    }


    @Override
    public int indexOf(T element) {

        if (element != null) {
            int probe = 0;


            while (probe < this.capacity()) {
                int index = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

                if (table[index] == null) {
                    break;
                }
                if (table[index].equals(element)) {
                    return index;
                }

                probe++;

            }
        }
        return -1;
    }

}


