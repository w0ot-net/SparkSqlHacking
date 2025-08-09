package com.clearspring.analytics.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractIterator implements Iterator {
   private Object next;
   private State currentState;

   public AbstractIterator() {
      this.currentState = AbstractIterator.State.NOT_STARTED;
   }

   public boolean hasNext() {
      switch (this.currentState) {
         case DONE:
            return false;
         case NOT_STARTED:
            this.currentState = AbstractIterator.State.HAS_DATA;
            this.next = this.computeNext();
            break;
         case HAS_DATA:
            return true;
         case EMPTY:
            this.currentState = AbstractIterator.State.HAS_DATA;
            this.next = this.computeNext();
      }

      return this.currentState != AbstractIterator.State.DONE;
   }

   public Object next() {
      if (this.hasNext()) {
         T r = (T)this.next;
         this.currentState = AbstractIterator.State.EMPTY;
         return r;
      } else {
         throw new NoSuchElementException();
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("Can't remove from an abstract iterator");
   }

   protected abstract Object computeNext();

   public Object endOfData() {
      this.currentState = AbstractIterator.State.DONE;
      return null;
   }

   private static enum State {
      NOT_STARTED,
      DONE,
      HAS_DATA,
      EMPTY;
   }
}
