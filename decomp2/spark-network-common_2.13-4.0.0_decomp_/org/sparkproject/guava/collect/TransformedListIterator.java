package org.sparkproject.guava.collect;

import java.util.ListIterator;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class TransformedListIterator extends TransformedIterator implements ListIterator {
   TransformedListIterator(ListIterator backingIterator) {
      super(backingIterator);
   }

   private ListIterator backingIterator() {
      return (ListIterator)this.backingIterator;
   }

   public final boolean hasPrevious() {
      return this.backingIterator().hasPrevious();
   }

   @ParametricNullness
   public final Object previous() {
      return this.transform(this.backingIterator().previous());
   }

   public final int nextIndex() {
      return this.backingIterator().nextIndex();
   }

   public final int previousIndex() {
      return this.backingIterator().previousIndex();
   }

   public void set(@ParametricNullness Object element) {
      throw new UnsupportedOperationException();
   }

   public void add(@ParametricNullness Object element) {
      throw new UnsupportedOperationException();
   }
}
