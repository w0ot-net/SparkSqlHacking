package org.apache.curator.shaded.com.google.common.collect;

import java.util.Iterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
