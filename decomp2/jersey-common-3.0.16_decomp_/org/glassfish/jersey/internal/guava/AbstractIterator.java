package org.glassfish.jersey.internal.guava;

import java.util.NoSuchElementException;

public abstract class AbstractIterator extends UnmodifiableIterator {
   private State state;
   private Object next;

   AbstractIterator() {
      this.state = AbstractIterator.State.NOT_READY;
   }

   protected abstract Object computeNext();

   final Object endOfData() {
      this.state = AbstractIterator.State.DONE;
      return null;
   }

   public final boolean hasNext() {
      Preconditions.checkState(this.state != AbstractIterator.State.FAILED);
      switch (this.state) {
         case DONE:
            return false;
         case READY:
            return true;
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

   public final Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.state = AbstractIterator.State.NOT_READY;
         T result = (T)this.next;
         this.next = null;
         return result;
      }
   }

   private static enum State {
      READY,
      NOT_READY,
      DONE,
      FAILED;
   }
}
