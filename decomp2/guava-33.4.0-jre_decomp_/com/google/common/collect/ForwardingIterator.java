package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingIterator extends ForwardingObject implements Iterator {
   protected ForwardingIterator() {
   }

   protected abstract Iterator delegate();

   public boolean hasNext() {
      return this.delegate().hasNext();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object next() {
      return this.delegate().next();
   }

   public void remove() {
      this.delegate().remove();
   }
}
