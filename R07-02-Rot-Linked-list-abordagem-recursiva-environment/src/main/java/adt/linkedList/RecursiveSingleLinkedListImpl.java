package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

   protected T data;
   protected RecursiveSingleLinkedListImpl<T> next;

   public RecursiveSingleLinkedListImpl() {
	   this.next = null;
   }

   @Override
   public boolean isEmpty() {
      if (data == null)
         return true;
      return false;
   }

   @Override
   public int size() {
      if (this.isEmpty())
         return 0;
      else
         return 1 + next.size();
   }

   @Override
   public T search(T element) {
      if (this.isEmpty())
         return null;
      else {
         if (this.data.equals(element)) {
            return data;
         } else {
            return next.search(element);
         }

      }
   }

   @Override
   public void insert(T element) {
      if (this.isEmpty()) {
         this.data = element;
         next = new RecursiveSingleLinkedListImpl<T>();
      } else {
         next.insert(element);

      }
   }

   @Override
   public void remove(T element) {
      if (this.isEmpty()) {
      } else {
         if (this.getNext().getData() == element) {
            this.next = this.getNext().getNext();

         } else {
            this.next.remove(element);
         }

      }
   }

   @Override
   public T[] toArray() {
      T[] result = ((T[]) new Object[this.size()]);

      if (this.isEmpty())
         return result;

      adicionaNoArray(result, this, 0);
      return result;
   }

   private void adicionaNoArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int indice) {
      if (!node.isEmpty()) {
         array[indice] = node.data;
         adicionaNoArray(array, node.getNext(), indice + 1);
      }
   }

   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }

   public RecursiveSingleLinkedListImpl<T> getNext() {
      return next;
   }

   public void setNext(RecursiveSingleLinkedListImpl<T> next) {
      this.next = next;
   }

}

