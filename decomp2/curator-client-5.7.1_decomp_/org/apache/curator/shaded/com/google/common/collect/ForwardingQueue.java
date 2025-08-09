package org.apache.curator.shaded.com.google.common.collect;

import java.util.NoSuchElementException;
import java.util.Queue;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingQueue extends ForwardingCollection implements Queue {
   protected ForwardingQueue() {
   }

   protected abstract Queue delegate();

   @CanIgnoreReturnValue
   public boolean offer(@ParametricNullness Object o) {
      return this.delegate().offer(o);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object poll() {
      return this.delegate().poll();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object remove() {
      return this.delegate().remove();
   }

   @CheckForNull
   public Object peek() {
      return this.delegate().peek();
   }

   @ParametricNullness
   public Object element() {
      return this.delegate().element();
   }

   protected boolean standardOffer(@ParametricNullness Object e) {
      try {
         return this.add(e);
      } catch (IllegalStateException var3) {
         return false;
      }
   }

   @CheckForNull
   protected Object standardPeek() {
      try {
         return this.element();
      } catch (NoSuchElementException var2) {
         return null;
      }
   }

   @CheckForNull
   protected Object standardPoll() {
      try {
         return this.remove();
      } catch (NoSuchElementException var2) {
         return null;
      }
   }
}
