package org.apache.curator.shaded.com.google.common.collect;

import java.util.ListIterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
