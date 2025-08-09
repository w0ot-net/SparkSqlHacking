package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import java.util.Queue;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
