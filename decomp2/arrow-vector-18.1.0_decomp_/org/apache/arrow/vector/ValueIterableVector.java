package org.apache.arrow.vector;

import java.util.Iterator;

public interface ValueIterableVector extends ValueVector {
   default Iterator getValueIterator() {
      return new Iterator() {
         private int index = 0;

         public boolean hasNext() {
            return this.index < ValueIterableVector.this.getValueCount();
         }

         public Object next() {
            return ValueIterableVector.this.getObject(this.index++);
         }
      };
   }

   default Iterable getValueIterable() {
      return this::getValueIterator;
   }
}
