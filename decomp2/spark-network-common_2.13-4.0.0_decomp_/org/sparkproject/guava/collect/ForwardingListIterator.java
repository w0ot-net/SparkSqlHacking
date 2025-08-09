package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingListIterator extends ForwardingIterator implements ListIterator {
   protected ForwardingListIterator() {
   }

   protected abstract ListIterator delegate();

   public void add(@ParametricNullness Object element) {
      this.delegate().add(element);
   }

   public boolean hasPrevious() {
      return this.delegate().hasPrevious();
   }

   public int nextIndex() {
      return this.delegate().nextIndex();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object previous() {
      return this.delegate().previous();
   }

   public int previousIndex() {
      return this.delegate().previousIndex();
   }

   public void set(@ParametricNullness Object element) {
      this.delegate().set(element);
   }
}
