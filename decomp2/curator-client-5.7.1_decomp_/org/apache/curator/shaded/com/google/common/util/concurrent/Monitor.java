package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.primitives.Longs;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.GuardedBy;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Monitor {
   private final boolean fair;
   private final ReentrantLock lock;
   @CheckForNull
   @GuardedBy("lock")
   private Guard activeGuards;

   public Monitor() {
      this(false);
   }

   public Monitor(boolean fair) {
      this.activeGuards = null;
      this.fair = fair;
      this.lock = new ReentrantLock(fair);
   }

   public Guard newGuard(final BooleanSupplier isSatisfied) {
      Preconditions.checkNotNull(isSatisfied, "isSatisfied");
      return new Guard(this) {
         public boolean isSatisfied() {
            return isSatisfied.getAsBoolean();
         }
      };
   }

   public void enter() {
      this.lock.lock();
   }

   public boolean enter(Duration time) {
      return this.enter(Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enter(long time, TimeUnit unit) {
      long timeoutNanos = toSafeNanos(time, unit);
      ReentrantLock lock = this.lock;
      if (!this.fair && lock.tryLock()) {
         return true;
      } else {
         boolean interrupted = Thread.interrupted();

         try {
            long startTime = System.nanoTime();
            long remainingNanos = timeoutNanos;

            while(true) {
               try {
                  boolean var12 = lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS);
                  return var12;
               } catch (InterruptedException var16) {
                  interrupted = true;
                  remainingNanos = remainingNanos(startTime, timeoutNanos);
               }
            }
         } finally {
            if (interrupted) {
               Thread.currentThread().interrupt();
            }

         }
      }
   }

   public void enterInterruptibly() throws InterruptedException {
      this.lock.lockInterruptibly();
   }

   public boolean enterInterruptibly(Duration time) throws InterruptedException {
      return this.enterInterruptibly(Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
      return this.lock.tryLock(time, unit);
   }

   public boolean tryEnter() {
      return this.lock.tryLock();
   }

   public void enterWhen(Guard guard) throws InterruptedException {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
         lock.lockInterruptibly();
         boolean satisfied = false;

         try {
            if (!guard.isSatisfied()) {
               this.await(guard, signalBeforeWaiting);
            }

            satisfied = true;
         } finally {
            if (!satisfied) {
               this.leave();
            }

         }

      }
   }

   public boolean enterWhen(Guard guard, Duration time) throws InterruptedException {
      return this.enterWhen(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
      long timeoutNanos = toSafeNanos(time, unit);
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock;
         boolean reentrant;
         long startTime;
         label269: {
            lock = this.lock;
            reentrant = lock.isHeldByCurrentThread();
            startTime = 0L;
            if (!this.fair) {
               if (Thread.interrupted()) {
                  throw new InterruptedException();
               }

               if (lock.tryLock()) {
                  break label269;
               }
            }

            startTime = initNanoTime(timeoutNanos);
            if (!lock.tryLock(time, unit)) {
               return false;
            }
         }

         boolean satisfied = false;
         boolean threw = true;

         boolean var13;
         try {
            satisfied = guard.isSatisfied() || this.awaitNanos(guard, startTime == 0L ? timeoutNanos : remainingNanos(startTime, timeoutNanos), reentrant);
            threw = false;
            var13 = satisfied;
         } finally {
            if (!satisfied) {
               try {
                  if (threw && !reentrant) {
                     this.signalNextWaiter();
                  }
               } finally {
                  lock.unlock();
               }
            }

         }

         return var13;
      }
   }

   public void enterWhenUninterruptibly(Guard guard) {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
         lock.lock();
         boolean satisfied = false;

         try {
            if (!guard.isSatisfied()) {
               this.awaitUninterruptibly(guard, signalBeforeWaiting);
            }

            satisfied = true;
         } finally {
            if (!satisfied) {
               this.leave();
            }

         }

      }
   }

   public boolean enterWhenUninterruptibly(Guard guard, Duration time) {
      return this.enterWhenUninterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
      long timeoutNanos = toSafeNanos(time, unit);
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         long startTime = 0L;
         boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
         boolean interrupted = Thread.interrupted();

         try {
            if (this.fair || !lock.tryLock()) {
               startTime = initNanoTime(timeoutNanos);
               long remainingNanos = timeoutNanos;

               while(true) {
                  try {
                     if (!lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS)) {
                        boolean var32 = false;
                        return var32;
                     }
                     break;
                  } catch (InterruptedException var27) {
                     InterruptedException interrupt = var27;
                     interrupted = true;
                     remainingNanos = remainingNanos(startTime, timeoutNanos);
                  }
               }
            }

            boolean satisfied = false;

            try {
               while(true) {
                  try {
                     if (guard.isSatisfied()) {
                        satisfied = true;
                     } else {
                        long remainingNanos;
                        if (startTime == 0L) {
                           startTime = initNanoTime(timeoutNanos);
                           remainingNanos = timeoutNanos;
                        } else {
                           remainingNanos = remainingNanos(startTime, timeoutNanos);
                        }

                        satisfied = this.awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                     }

                     boolean var31 = satisfied;
                     return var31;
                  } catch (InterruptedException var25) {
                     interrupted = true;
                     signalBeforeWaiting = false;
                  }
               }
            } finally {
               if (!satisfied) {
                  lock.unlock();
               }

            }
         } finally {
            if (interrupted) {
               Thread.currentThread().interrupt();
            }

         }
      }
   }

   public boolean enterIf(Guard guard) {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         lock.lock();
         boolean satisfied = false;

         boolean var4;
         try {
            var4 = satisfied = guard.isSatisfied();
         } finally {
            if (!satisfied) {
               lock.unlock();
            }

         }

         return var4;
      }
   }

   public boolean enterIf(Guard guard, Duration time) {
      return this.enterIf(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enterIf(Guard guard, long time, TimeUnit unit) {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else if (!this.enter(time, unit)) {
         return false;
      } else {
         boolean satisfied = false;

         boolean var6;
         try {
            var6 = satisfied = guard.isSatisfied();
         } finally {
            if (!satisfied) {
               this.lock.unlock();
            }

         }

         return var6;
      }
   }

   public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         lock.lockInterruptibly();
         boolean satisfied = false;

         boolean var4;
         try {
            var4 = satisfied = guard.isSatisfied();
         } finally {
            if (!satisfied) {
               lock.unlock();
            }

         }

         return var4;
      }
   }

   public boolean enterIfInterruptibly(Guard guard, Duration time) throws InterruptedException {
      return this.enterIfInterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         if (!lock.tryLock(time, unit)) {
            return false;
         } else {
            boolean satisfied = false;

            boolean var7;
            try {
               var7 = satisfied = guard.isSatisfied();
            } finally {
               if (!satisfied) {
                  lock.unlock();
               }

            }

            return var7;
         }
      }
   }

   public boolean tryEnterIf(Guard guard) {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         ReentrantLock lock = this.lock;
         if (!lock.tryLock()) {
            return false;
         } else {
            boolean satisfied = false;

            boolean var4;
            try {
               var4 = satisfied = guard.isSatisfied();
            } finally {
               if (!satisfied) {
                  lock.unlock();
               }

            }

            return var4;
         }
      }
   }

   public void waitFor(Guard guard) throws InterruptedException {
      if (guard.monitor == this && this.lock.isHeldByCurrentThread()) {
         if (!guard.isSatisfied()) {
            this.await(guard, true);
         }

      } else {
         throw new IllegalMonitorStateException();
      }
   }

   public boolean waitFor(Guard guard, Duration time) throws InterruptedException {
      return this.waitFor(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
      long timeoutNanos = toSafeNanos(time, unit);
      if (guard.monitor == this && this.lock.isHeldByCurrentThread()) {
         if (guard.isSatisfied()) {
            return true;
         } else if (Thread.interrupted()) {
            throw new InterruptedException();
         } else {
            return this.awaitNanos(guard, timeoutNanos, true);
         }
      } else {
         throw new IllegalMonitorStateException();
      }
   }

   public void waitForUninterruptibly(Guard guard) {
      if (guard.monitor == this && this.lock.isHeldByCurrentThread()) {
         if (!guard.isSatisfied()) {
            this.awaitUninterruptibly(guard, true);
         }

      } else {
         throw new IllegalMonitorStateException();
      }
   }

   public boolean waitForUninterruptibly(Guard guard, Duration time) {
      return this.waitForUninterruptibly(guard, Internal.toNanosSaturated(time), TimeUnit.NANOSECONDS);
   }

   public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
      long timeoutNanos = toSafeNanos(time, unit);
      if (guard.monitor == this && this.lock.isHeldByCurrentThread()) {
         if (guard.isSatisfied()) {
            return true;
         } else {
            boolean signalBeforeWaiting = true;
            long startTime = initNanoTime(timeoutNanos);
            boolean interrupted = Thread.interrupted();

            try {
               long remainingNanos = timeoutNanos;

               while(true) {
                  try {
                     boolean var13 = this.awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                     return var13;
                  } catch (InterruptedException var18) {
                     interrupted = true;
                     if (guard.isSatisfied()) {
                        boolean var14 = true;
                        return var14;
                     }

                     signalBeforeWaiting = false;
                     remainingNanos = remainingNanos(startTime, timeoutNanos);
                  }
               }
            } finally {
               if (interrupted) {
                  Thread.currentThread().interrupt();
               }

            }
         }
      } else {
         throw new IllegalMonitorStateException();
      }
   }

   public void leave() {
      ReentrantLock lock = this.lock;

      try {
         if (lock.getHoldCount() == 1) {
            this.signalNextWaiter();
         }
      } finally {
         lock.unlock();
      }

   }

   public boolean isFair() {
      return this.fair;
   }

   public boolean isOccupied() {
      return this.lock.isLocked();
   }

   public boolean isOccupiedByCurrentThread() {
      return this.lock.isHeldByCurrentThread();
   }

   public int getOccupiedDepth() {
      return this.lock.getHoldCount();
   }

   public int getQueueLength() {
      return this.lock.getQueueLength();
   }

   public boolean hasQueuedThreads() {
      return this.lock.hasQueuedThreads();
   }

   public boolean hasQueuedThread(Thread thread) {
      return this.lock.hasQueuedThread(thread);
   }

   public boolean hasWaiters(Guard guard) {
      return this.getWaitQueueLength(guard) > 0;
   }

   public int getWaitQueueLength(Guard guard) {
      if (guard.monitor != this) {
         throw new IllegalMonitorStateException();
      } else {
         this.lock.lock();

         int var2;
         try {
            var2 = guard.waiterCount;
         } finally {
            this.lock.unlock();
         }

         return var2;
      }
   }

   private static long toSafeNanos(long time, TimeUnit unit) {
      long timeoutNanos = unit.toNanos(time);
      return Longs.constrainToRange(timeoutNanos, 0L, 6917529027641081853L);
   }

   private static long initNanoTime(long timeoutNanos) {
      if (timeoutNanos <= 0L) {
         return 0L;
      } else {
         long startTime = System.nanoTime();
         return startTime == 0L ? 1L : startTime;
      }
   }

   private static long remainingNanos(long startTime, long timeoutNanos) {
      return timeoutNanos <= 0L ? 0L : timeoutNanos - (System.nanoTime() - startTime);
   }

   @GuardedBy("lock")
   private void signalNextWaiter() {
      for(Guard guard = this.activeGuards; guard != null; guard = guard.next) {
         if (this.isSatisfied(guard)) {
            guard.condition.signal();
            break;
         }
      }

   }

   @GuardedBy("lock")
   private boolean isSatisfied(Guard guard) {
      try {
         return guard.isSatisfied();
      } catch (Error | RuntimeException throwable) {
         this.signalAllWaiters();
         throw throwable;
      }
   }

   @GuardedBy("lock")
   private void signalAllWaiters() {
      for(Guard guard = this.activeGuards; guard != null; guard = guard.next) {
         guard.condition.signalAll();
      }

   }

   @GuardedBy("lock")
   private void beginWaitingFor(Guard guard) {
      int waiters = guard.waiterCount++;
      if (waiters == 0) {
         guard.next = this.activeGuards;
         this.activeGuards = guard;
      }

   }

   @GuardedBy("lock")
   private void endWaitingFor(Guard guard) {
      int waiters = --guard.waiterCount;
      if (waiters == 0) {
         Guard p = this.activeGuards;

         Guard pred;
         for(pred = null; p != guard; p = p.next) {
            pred = p;
         }

         if (pred == null) {
            this.activeGuards = p.next;
         } else {
            pred.next = p.next;
         }

         p.next = null;
      }

   }

   @GuardedBy("lock")
   private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
      if (signalBeforeWaiting) {
         this.signalNextWaiter();
      }

      this.beginWaitingFor(guard);

      try {
         do {
            guard.condition.await();
         } while(!guard.isSatisfied());
      } finally {
         this.endWaitingFor(guard);
      }

   }

   @GuardedBy("lock")
   private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
      if (signalBeforeWaiting) {
         this.signalNextWaiter();
      }

      this.beginWaitingFor(guard);

      try {
         do {
            guard.condition.awaitUninterruptibly();
         } while(!guard.isSatisfied());
      } finally {
         this.endWaitingFor(guard);
      }

   }

   @GuardedBy("lock")
   private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
      boolean firstTime = true;

      boolean var10;
      try {
         while(nanos > 0L) {
            if (firstTime) {
               if (signalBeforeWaiting) {
                  this.signalNextWaiter();
               }

               this.beginWaitingFor(guard);
               firstTime = false;
            }

            nanos = guard.condition.awaitNanos(nanos);
            if (guard.isSatisfied()) {
               var10 = true;
               return var10;
            }
         }

         var10 = false;
      } finally {
         if (!firstTime) {
            this.endWaitingFor(guard);
         }

      }

      return var10;
   }

   public abstract static class Guard {
      @Weak
      final Monitor monitor;
      final Condition condition;
      @GuardedBy("monitor.lock")
      int waiterCount = 0;
      @CheckForNull
      @GuardedBy("monitor.lock")
      Guard next;

      protected Guard(Monitor monitor) {
         this.monitor = (Monitor)Preconditions.checkNotNull(monitor, "monitor");
         this.condition = monitor.lock.newCondition();
      }

      public abstract boolean isSatisfied();
   }
}
