package javolution.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javolution.context.ObjectFactory;

final class FastIterator implements Iterator {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      protected Object create() {
         return new FastIterator();
      }

      protected void cleanup(Object obj) {
         FastIterator iterator = (FastIterator)obj;
         iterator._collection = null;
         iterator._current = null;
         iterator._next = null;
         iterator._tail = null;
      }
   };
   private FastCollection _collection;
   private FastCollection.Record _current;
   private FastCollection.Record _next;
   private FastCollection.Record _tail;

   public static FastIterator valueOf(FastCollection collection) {
      FastIterator iterator = (FastIterator)FACTORY.object();
      iterator._collection = collection;
      iterator._next = collection.head().getNext();
      iterator._tail = collection.tail();
      return iterator;
   }

   private FastIterator() {
   }

   public boolean hasNext() {
      return this._next != this._tail;
   }

   public Object next() {
      if (this._next == this._tail) {
         throw new NoSuchElementException();
      } else {
         this._current = this._next;
         this._next = this._next.getNext();
         return this._collection.valueOf(this._current);
      }
   }

   public void remove() {
      if (this._current != null) {
         FastCollection.Record previous = this._current.getPrevious();
         this._collection.delete(this._current);
         this._current = null;
         this._next = previous.getNext();
      } else {
         throw new IllegalStateException();
      }
   }
}
