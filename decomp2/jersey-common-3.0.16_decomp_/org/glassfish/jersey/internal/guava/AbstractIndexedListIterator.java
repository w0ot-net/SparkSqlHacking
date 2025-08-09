package org.glassfish.jersey.internal.guava;

import java.util.NoSuchElementException;

abstract class AbstractIndexedListIterator extends UnmodifiableListIterator {
   private final int size;
   private int position;

   AbstractIndexedListIterator(int size, int position) {
      Preconditions.checkPositionIndex(position, size);
      this.size = size;
      this.position = position;
   }

   protected abstract Object get(int var1);

   public final boolean hasNext() {
      return this.position < this.size;
   }

   public final Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.get(this.position++);
      }
   }

   public final int nextIndex() {
      return this.position;
   }

   public final boolean hasPrevious() {
      return this.position > 0;
   }

   public final Object previous() {
      if (!this.hasPrevious()) {
         throw new NoSuchElementException();
      } else {
         return this.get(--this.position);
      }
   }

   public final int previousIndex() {
      return this.position - 1;
   }
}
