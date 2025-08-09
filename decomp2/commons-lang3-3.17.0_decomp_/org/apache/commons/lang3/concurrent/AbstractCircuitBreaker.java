package org.apache.commons.lang3.concurrent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractCircuitBreaker implements CircuitBreaker {
   public static final String PROPERTY_NAME = "open";
   protected final AtomicReference state;
   private final PropertyChangeSupport changeSupport;

   protected static boolean isOpen(State state) {
      return state == AbstractCircuitBreaker.State.OPEN;
   }

   public AbstractCircuitBreaker() {
      this.state = new AtomicReference(AbstractCircuitBreaker.State.CLOSED);
      this.changeSupport = new PropertyChangeSupport(this);
   }

   public void addChangeListener(PropertyChangeListener listener) {
      this.changeSupport.addPropertyChangeListener(listener);
   }

   protected void changeState(State newState) {
      if (this.state.compareAndSet(newState.oppositeState(), newState)) {
         this.changeSupport.firePropertyChange("open", !isOpen(newState), isOpen(newState));
      }

   }

   public abstract boolean checkState();

   public void close() {
      this.changeState(AbstractCircuitBreaker.State.CLOSED);
   }

   public abstract boolean incrementAndCheckState(Object var1);

   public boolean isClosed() {
      return !this.isOpen();
   }

   public boolean isOpen() {
      return isOpen((State)this.state.get());
   }

   public void open() {
      this.changeState(AbstractCircuitBreaker.State.OPEN);
   }

   public void removeChangeListener(PropertyChangeListener listener) {
      this.changeSupport.removePropertyChangeListener(listener);
   }

   protected static enum State {
      CLOSED {
         public State oppositeState() {
            return OPEN;
         }
      },
      OPEN {
         public State oppositeState() {
            return CLOSED;
         }
      };

      private State() {
      }

      public abstract State oppositeState();

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{CLOSED, OPEN};
      }
   }
}
