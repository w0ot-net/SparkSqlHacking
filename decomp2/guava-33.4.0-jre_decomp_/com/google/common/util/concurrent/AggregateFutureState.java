package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@ReflectionSupport(Level.FULL)
abstract class AggregateFutureState extends AbstractFuture.TrustedFuture {
   @CheckForNull
   private volatile Set seenExceptions = null;
   private volatile int remaining;
   private static final AtomicHelper ATOMIC_HELPER;
   private static final LazyLogger log = new LazyLogger(AggregateFutureState.class);

   AggregateFutureState(int remainingFutures) {
      this.remaining = remainingFutures;
   }

   final Set getOrInitSeenExceptions() {
      Set<Throwable> seenExceptionsLocal = this.seenExceptions;
      if (seenExceptionsLocal == null) {
         seenExceptionsLocal = Sets.newConcurrentHashSet();
         this.addInitialException(seenExceptionsLocal);
         ATOMIC_HELPER.compareAndSetSeenExceptions(this, (Set)null, seenExceptionsLocal);
         seenExceptionsLocal = (Set)Objects.requireNonNull(this.seenExceptions);
      }

      return seenExceptionsLocal;
   }

   abstract void addInitialException(Set seen);

   final int decrementRemainingAndGet() {
      return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
   }

   final void clearSeenExceptions() {
      this.seenExceptions = null;
   }

   static {
      Throwable thrownReflectionFailure = null;

      AtomicHelper helper;
      try {
         helper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
      } catch (Throwable reflectionFailure) {
         thrownReflectionFailure = reflectionFailure;
         helper = new SynchronizedAtomicHelper();
      }

      ATOMIC_HELPER = helper;
      if (thrownReflectionFailure != null) {
         log.get().log(java.util.logging.Level.SEVERE, "SafeAtomicHelper is broken!", thrownReflectionFailure);
      }

   }

   private abstract static class AtomicHelper {
      private AtomicHelper() {
      }

      abstract void compareAndSetSeenExceptions(AggregateFutureState state, @CheckForNull Set expect, Set update);

      abstract int decrementAndGetRemainingCount(AggregateFutureState state);
   }

   private static final class SafeAtomicHelper extends AtomicHelper {
      final AtomicReferenceFieldUpdater seenExceptionsUpdater;
      final AtomicIntegerFieldUpdater remainingCountUpdater;

      SafeAtomicHelper(AtomicReferenceFieldUpdater seenExceptionsUpdater, AtomicIntegerFieldUpdater remainingCountUpdater) {
         this.seenExceptionsUpdater = seenExceptionsUpdater;
         this.remainingCountUpdater = remainingCountUpdater;
      }

      void compareAndSetSeenExceptions(AggregateFutureState state, @CheckForNull Set expect, Set update) {
         this.seenExceptionsUpdater.compareAndSet(state, expect, update);
      }

      int decrementAndGetRemainingCount(AggregateFutureState state) {
         return this.remainingCountUpdater.decrementAndGet(state);
      }
   }

   private static final class SynchronizedAtomicHelper extends AtomicHelper {
      private SynchronizedAtomicHelper() {
      }

      void compareAndSetSeenExceptions(AggregateFutureState state, @CheckForNull Set expect, Set update) {
         synchronized(state) {
            if (state.seenExceptions == expect) {
               state.seenExceptions = update;
            }

         }
      }

      int decrementAndGetRemainingCount(AggregateFutureState state) {
         synchronized(state) {
            return --state.remaining;
         }
      }
   }
}
