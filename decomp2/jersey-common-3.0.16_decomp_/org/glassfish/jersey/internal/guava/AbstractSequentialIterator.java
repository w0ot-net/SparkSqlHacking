package org.glassfish.jersey.internal.guava;

import java.util.NoSuchElementException;

abstract class AbstractSequentialIterator extends UnmodifiableIterator {
   private Object nextOrNull;

   AbstractSequentialIterator(Object firstOrNull) {
      this.nextOrNull = firstOrNull;
   }

   protected abstract Object computeNext(Object var1);

   public final boolean hasNext() {
      return this.nextOrNull != null;
   }

   public final Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object var1;
         try {
            var1 = this.nextOrNull;
         } finally {
            this.nextOrNull = this.computeNext(this.nextOrNull);
         }

         return var1;
      }
   }
}
