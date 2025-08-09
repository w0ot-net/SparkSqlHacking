package shaded.parquet.com.fasterxml.jackson.databind.util.internal;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class LinkedDeque extends AbstractCollection implements Deque {
   Linked first;
   Linked last;

   void linkFirst(Linked e) {
      E f = (E)this.first;
      this.first = e;
      if (f == null) {
         this.last = e;
      } else {
         f.setPrevious(e);
         e.setNext(f);
      }

   }

   void linkLast(Linked e) {
      E l = (E)this.last;
      this.last = e;
      if (l == null) {
         this.first = e;
      } else {
         l.setNext(e);
         e.setPrevious(l);
      }

   }

   Linked unlinkFirst() {
      E f = (E)this.first;
      E next = (E)f.getNext();
      f.setNext((Linked)null);
      this.first = next;
      if (next == null) {
         this.last = null;
      } else {
         next.setPrevious((Linked)null);
      }

      return f;
   }

   Linked unlinkLast() {
      E l = (E)this.last;
      E prev = (E)l.getPrevious();
      l.setPrevious((Linked)null);
      this.last = prev;
      if (prev == null) {
         this.first = null;
      } else {
         prev.setNext((Linked)null);
      }

      return l;
   }

   void unlink(Linked e) {
      E prev = (E)e.getPrevious();
      E next = (E)e.getNext();
      if (prev == null) {
         this.first = next;
      } else {
         prev.setNext(next);
         e.setPrevious((Linked)null);
      }

      if (next == null) {
         this.last = prev;
      } else {
         next.setPrevious(prev);
         e.setNext((Linked)null);
      }

   }

   public boolean isEmpty() {
      return this.first == null;
   }

   void checkNotEmpty() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      }
   }

   public int size() {
      int size = 0;

      for(E e = (E)this.first; e != null; e = (E)e.getNext()) {
         ++size;
      }

      return size;
   }

   public void clear() {
      E next;
      for(E e = (E)this.first; e != null; e = next) {
         next = (E)e.getNext();
         e.setPrevious((Linked)null);
         e.setNext((Linked)null);
      }

      this.first = this.last = null;
   }

   public boolean contains(Object o) {
      return o instanceof Linked && this.contains((Linked)o);
   }

   boolean contains(Linked e) {
      return e.getPrevious() != null || e.getNext() != null || e == this.first;
   }

   public void moveToFront(Linked e) {
      if (e != this.first) {
         this.unlink(e);
         this.linkFirst(e);
      }

   }

   public void moveToBack(Linked e) {
      if (e != this.last) {
         this.unlink(e);
         this.linkLast(e);
      }

   }

   public Linked peek() {
      return this.peekFirst();
   }

   public Linked peekFirst() {
      return this.first;
   }

   public Linked peekLast() {
      return this.last;
   }

   public Linked getFirst() {
      this.checkNotEmpty();
      return this.peekFirst();
   }

   public Linked getLast() {
      this.checkNotEmpty();
      return this.peekLast();
   }

   public Linked element() {
      return this.getFirst();
   }

   public boolean offer(Linked e) {
      return this.offerLast(e);
   }

   public boolean offerFirst(Linked e) {
      if (this.contains(e)) {
         return false;
      } else {
         this.linkFirst(e);
         return true;
      }
   }

   public boolean offerLast(Linked e) {
      if (this.contains(e)) {
         return false;
      } else {
         this.linkLast(e);
         return true;
      }
   }

   public boolean add(Linked e) {
      return this.offerLast(e);
   }

   public void addFirst(Linked e) {
      if (!this.offerFirst(e)) {
         throw new IllegalArgumentException();
      }
   }

   public void addLast(Linked e) {
      if (!this.offerLast(e)) {
         throw new IllegalArgumentException();
      }
   }

   public Linked poll() {
      return this.pollFirst();
   }

   public Linked pollFirst() {
      return this.isEmpty() ? null : this.unlinkFirst();
   }

   public Linked pollLast() {
      return this.isEmpty() ? null : this.unlinkLast();
   }

   public Linked remove() {
      return this.removeFirst();
   }

   public boolean remove(Object o) {
      return o instanceof Linked && this.remove((Linked)o);
   }

   boolean remove(Linked e) {
      if (this.contains(e)) {
         this.unlink(e);
         return true;
      } else {
         return false;
      }
   }

   public Linked removeFirst() {
      this.checkNotEmpty();
      return this.pollFirst();
   }

   public boolean removeFirstOccurrence(Object o) {
      return this.remove(o);
   }

   public Linked removeLast() {
      this.checkNotEmpty();
      return this.pollLast();
   }

   public boolean removeLastOccurrence(Object o) {
      return this.remove(o);
   }

   public boolean removeAll(Collection c) {
      boolean modified = false;

      for(Object o : c) {
         modified |= this.remove(o);
      }

      return modified;
   }

   public void push(Linked e) {
      this.addFirst(e);
   }

   public Linked pop() {
      return this.removeFirst();
   }

   public Iterator iterator() {
      return new AbstractLinkedIterator(this.first) {
         Linked computeNext() {
            return this.cursor.getNext();
         }
      };
   }

   public Iterator descendingIterator() {
      return new AbstractLinkedIterator(this.last) {
         Linked computeNext() {
            return this.cursor.getPrevious();
         }
      };
   }

   abstract class AbstractLinkedIterator implements Iterator {
      Linked cursor;

      AbstractLinkedIterator(Linked start) {
         this.cursor = start;
      }

      public boolean hasNext() {
         return this.cursor != null;
      }

      public Linked next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            E e = (E)this.cursor;
            this.cursor = this.computeNext();
            return e;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      abstract Linked computeNext();
   }
}
