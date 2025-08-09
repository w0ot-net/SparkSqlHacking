package org.sparkproject.guava.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class AbstractIterator implements Iterator {
   private State state;
   @CheckForNull
   private Object next;

   protected AbstractIterator() {
      this.state = AbstractIterator.State.NOT_READY;
   }

   @CheckForNull
   protected abstract Object computeNext();

   @CheckForNull
   @CanIgnoreReturnValue
   protected final Object endOfData() {
      this.state = AbstractIterator.State.DONE;
      return null;
   }

   public final boolean hasNext() {
      Preconditions.checkState(this.state != AbstractIterator.State.FAILED);
      switch (this.state.ordinal()) {
         case 0:
            return true;
         case 2:
            return false;
         default:
            return this.tryToComputeNext();
      }
   }

   private boolean tryToComputeNext() {
      this.state = AbstractIterator.State.FAILED;
      this.next = this.computeNext();
      if (this.state != AbstractIterator.State.DONE) {
         this.state = AbstractIterator.State.READY;
         return true;
      } else {
         return false;
      }
   }

   @ParametricNullness
   public final Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.state = AbstractIterator.State.NOT_READY;
         T result = (T)NullnessCasts.uncheckedCastNullableTToT(this.next);
         this.next = null;
         return result;
      }
   }

   public final void remove() {
      throw new UnsupportedOperationException();
   }

   private static enum State {
      READY,
      NOT_READY,
      DONE,
      FAILED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{READY, NOT_READY, DONE, FAILED};
      }
   }
}
