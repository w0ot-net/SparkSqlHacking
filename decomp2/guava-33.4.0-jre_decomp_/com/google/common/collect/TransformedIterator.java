package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class TransformedIterator implements Iterator {
   final Iterator backingIterator;

   TransformedIterator(Iterator backingIterator) {
      this.backingIterator = (Iterator)Preconditions.checkNotNull(backingIterator);
   }

   @ParametricNullness
   abstract Object transform(@ParametricNullness Object from);

   public final boolean hasNext() {
      return this.backingIterator.hasNext();
   }

   @ParametricNullness
   public final Object next() {
      return this.transform(this.backingIterator.next());
   }

   public final void remove() {
      this.backingIterator.remove();
   }
}
