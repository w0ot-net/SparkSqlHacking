package jodd.util.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CompositeIterator implements Iterator {
   protected final List allIterators = new ArrayList();
   protected int currentIterator = -1;

   public CompositeIterator() {
   }

   public CompositeIterator(Iterator... iterators) {
      for(Iterator iterator : iterators) {
         this.add(iterator);
      }

   }

   public void add(Iterator iterator) {
      if (this.allIterators.contains(iterator)) {
         throw new IllegalArgumentException("Duplicate iterator");
      } else {
         this.allIterators.add(iterator);
      }
   }

   public boolean hasNext() {
      if (this.currentIterator == -1) {
         this.currentIterator = 0;
      }

      for(int i = this.currentIterator; i < this.allIterators.size(); ++i) {
         Iterator iterator = (Iterator)this.allIterators.get(i);
         if (iterator.hasNext()) {
            this.currentIterator = i;
            return true;
         }
      }

      return false;
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return ((Iterator)this.allIterators.get(this.currentIterator)).next();
      }
   }

   public void remove() {
      if (this.currentIterator == -1) {
         throw new IllegalStateException("next() has not yet been called");
      } else {
         ((Iterator)this.allIterators.get(this.currentIterator)).remove();
      }
   }
}
