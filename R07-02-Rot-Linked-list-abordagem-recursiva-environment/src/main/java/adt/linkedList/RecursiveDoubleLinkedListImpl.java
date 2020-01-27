package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected RecursiveDoubleLinkedListImpl<T> previous;

   public RecursiveDoubleLinkedListImpl() {

   }

   public RecursiveDoubleLinkedListImpl(RecursiveDoubleLinkedListImpl<T> previous,
         RecursiveDoubleLinkedListImpl<T> next, T data) {
      this.previous = previous;
      this.next = next;
      this.data = data;
   }

   @Override
   public void remove(T element) {
	   if(this.isEmpty()) {}
	   else {
		   if(this.data == element) {
			   if(previous.isEmpty() && next.isEmpty()) {
				   data = null;
				   next = null;
				   previous = null;
			   }
			   else {
				   data = next.data;
				   next = next.getNext();
					if (next != null) {
						((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
					}
			   }
		   }
		   else {
			   next.remove(element);
		   }
	   }
   }
   
   
   @Override
   public void insert(T element) {
      if (this.isEmpty()) {
         this.data = element;
         next = new RecursiveDoubleLinkedListImpl<T>();

         if (previous == null) {
            previous = new RecursiveDoubleLinkedListImpl<T>();
         }
      } else {
         next.insert(element);

      }
   }

   @Override
   public void insertFirst(T element) {
      if (this.isEmpty()) {
         if (this.previous == null) {
            this.previous = new RecursiveDoubleLinkedListImpl<T>(null, this, element);
         }

      } else {
         if (this.previous.isEmpty()) {

            next = new RecursiveDoubleLinkedListImpl<T>(this, (RecursiveDoubleLinkedListImpl<T>) this.next, this.data);
            this.data = element;
         }
         previous.insertFirst(element);
      }

   }

   @Override
   public void removeFirst() {
      if (!this.isEmpty()) {
         if (previous.isEmpty()) { 
            this.data = next.getData();
            this.next = next.getNext();
         } else {
            previous.removeFirst();
         }
      }
   }

   @Override
   public void removeLast() {
      if (!this.isEmpty()) {
         if (this.next.isEmpty()) {
            this.next = null;
            this.data = null;
         } else {
            ((DoubleLinkedList<T>) this.next).removeLast();
         }

      }
   }

   public RecursiveDoubleLinkedListImpl<T> getPrevious() {
      return previous;
   }

   public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
      this.previous = previous;
   }

}
