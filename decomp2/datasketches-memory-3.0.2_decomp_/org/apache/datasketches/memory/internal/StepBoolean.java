package org.apache.datasketches.memory.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class StepBoolean {
   private static final int FALSE = 0;
   private static final int TRUE = 1;
   private static final AtomicIntegerFieldUpdater STATE_FIELD_UPDATER = AtomicIntegerFieldUpdater.newUpdater(StepBoolean.class, "state");
   private final int initialState;
   private volatile int state;

   public StepBoolean(boolean initialState) {
      this.initialState = initialState ? 1 : 0;
      this.state = this.initialState;
   }

   public boolean get() {
      return this.state == 1;
   }

   public boolean change() {
      int notInitialState = this.initialState == 1 ? 0 : 1;
      return STATE_FIELD_UPDATER.compareAndSet(this, this.initialState, notInitialState);
   }

   public boolean hasChanged() {
      return this.state != this.initialState;
   }
}
