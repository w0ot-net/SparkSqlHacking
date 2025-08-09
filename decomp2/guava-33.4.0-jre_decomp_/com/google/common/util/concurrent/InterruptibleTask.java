package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@ReflectionSupport(Level.FULL)
abstract class InterruptibleTask extends AtomicReference implements Runnable {
   private static final Runnable DONE;
   private static final Runnable PARKED;
   private static final int MAX_BUSY_WAIT_SPINS = 1000;

   public final void run() {
      Thread currentThread = Thread.currentThread();
      if (this.compareAndSet((Object)null, currentThread)) {
         boolean run = !this.isDone();
         T result = (T)null;
         Throwable error = null;

         try {
            if (run) {
               result = (T)this.runInterruptibly();
            }
         } catch (Throwable t) {
            Platform.restoreInterruptIfIsInterruptedException(t);
            error = t;
         } finally {
            if (!this.compareAndSet(currentThread, DONE)) {
               this.waitForInterrupt(currentThread);
            }

            if (run) {
               if (error == null) {
                  this.afterRanInterruptiblySuccess(NullnessCasts.uncheckedCastNullableTToT(result));
               } else {
                  this.afterRanInterruptiblyFailure(error);
               }
            }

         }

      }
   }

   private void waitForInterrupt(Thread currentThread) {
      boolean restoreInterruptedBit = false;
      int spinCount = 0;
      Runnable state = (Runnable)this.get();

      for(Blocker blocker = null; state instanceof Blocker || state == PARKED; state = (Runnable)this.get()) {
         if (state instanceof Blocker) {
            blocker = (Blocker)state;
         }

         ++spinCount;
         if (spinCount > 1000) {
            if (state == PARKED || this.compareAndSet(state, PARKED)) {
               restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
               LockSupport.park(blocker);
            }
         } else {
            Thread.yield();
         }
      }

      if (restoreInterruptedBit) {
         currentThread.interrupt();
      }

   }

   abstract boolean isDone();

   @ParametricNullness
   abstract Object runInterruptibly() throws Exception;

   abstract void afterRanInterruptiblySuccess(@ParametricNullness Object result);

   abstract void afterRanInterruptiblyFailure(Throwable error);

   final void interruptTask() {
      Runnable currentRunner = (Runnable)this.get();
      if (currentRunner instanceof Thread) {
         Blocker blocker = new Blocker(this);
         blocker.setOwner(Thread.currentThread());
         if (this.compareAndSet(currentRunner, blocker)) {
            try {
               ((Thread)currentRunner).interrupt();
            } finally {
               Runnable prev = (Runnable)this.getAndSet(DONE);
               if (prev == PARKED) {
                  LockSupport.unpark((Thread)currentRunner);
               }

            }
         }
      }

   }

   public final String toString() {
      Runnable state = (Runnable)this.get();
      String result;
      if (state == DONE) {
         result = "running=[DONE]";
      } else if (state instanceof Blocker) {
         result = "running=[INTERRUPTED]";
      } else if (state instanceof Thread) {
         result = "running=[RUNNING ON " + ((Thread)state).getName() + "]";
      } else {
         result = "running=[NOT STARTED YET]";
      }

      return result + ", " + this.toPendingString();
   }

   abstract String toPendingString();

   static {
      Class var0 = LockSupport.class;
      DONE = new DoNothingRunnable();
      PARKED = new DoNothingRunnable();
   }

   private static final class DoNothingRunnable implements Runnable {
      private DoNothingRunnable() {
      }

      public void run() {
      }
   }

   @VisibleForTesting
   static final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
      private final InterruptibleTask task;

      private Blocker(InterruptibleTask task) {
         this.task = task;
      }

      public void run() {
      }

      private void setOwner(Thread thread) {
         super.setExclusiveOwnerThread(thread);
      }

      @CheckForNull
      @VisibleForTesting
      Thread getOwner() {
         return super.getExclusiveOwnerThread();
      }

      public String toString() {
         return this.task.toString();
      }
   }
}
