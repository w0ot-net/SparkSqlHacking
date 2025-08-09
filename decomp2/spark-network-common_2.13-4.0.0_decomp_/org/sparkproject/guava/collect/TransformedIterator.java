package org.sparkproject.guava.collect;

import java.util.Iterator;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

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
