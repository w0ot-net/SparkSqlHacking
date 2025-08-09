package org.apache.logging.log4j.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectArrayIterator implements Iterator {
   final Object[] array;
   final int startIndex;
   final int endIndex;
   int index;

   @SafeVarargs
   public ObjectArrayIterator(final Object... array) {
      this(array, 0, array.length);
   }

   public ObjectArrayIterator(final Object[] array, final int start) {
      this(array, start, array.length);
   }

   public ObjectArrayIterator(final Object[] array, final int start, final int end) {
      this.index = 0;
      if (start < 0) {
         throw new ArrayIndexOutOfBoundsException("Start index must not be less than zero");
      } else if (end > array.length) {
         throw new ArrayIndexOutOfBoundsException("End index must not be greater than the array length");
      } else if (start > array.length) {
         throw new ArrayIndexOutOfBoundsException("Start index must not be greater than the array length");
      } else if (end < start) {
         throw new IllegalArgumentException("End index must not be less than start index");
      } else {
         this.array = array;
         this.startIndex = start;
         this.endIndex = end;
         this.index = start;
      }
   }

   public boolean hasNext() {
      return this.index < this.endIndex;
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.array[this.index++];
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("remove() method is not supported for an ObjectArrayIterator");
   }

   public Object[] getArray() {
      return this.array;
   }

   public int getStartIndex() {
      return this.startIndex;
   }

   public int getEndIndex() {
      return this.endIndex;
   }

   public void reset() {
      this.index = this.startIndex;
   }
}
