package org.glassfish.jersey.internal.guava;

import java.util.Iterator;

abstract class TransformedIterator implements Iterator {
   private final Iterator backingIterator;

   TransformedIterator(Iterator backingIterator) {
      this.backingIterator = (Iterator)Preconditions.checkNotNull(backingIterator);
   }

   abstract Object transform(Object var1);

   public final boolean hasNext() {
      return this.backingIterator.hasNext();
   }

   public final Object next() {
      return this.transform(this.backingIterator.next());
   }

   public final void remove() {
      this.backingIterator.remove();
   }
}
