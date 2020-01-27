package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                    HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {

            int probe = 0;

            while (probe < this.capacity()) {

                int index = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

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
                int index = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

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
                int index = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

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
