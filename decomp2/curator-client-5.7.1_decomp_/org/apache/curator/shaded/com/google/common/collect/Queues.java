package org.apache.curator.shaded.com.google.common.collect;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Queues {
   private Queues() {
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ArrayBlockingQueue newArrayBlockingQueue(int capacity) {
      return new ArrayBlockingQueue(capacity);
   }

   public static ArrayDeque newArrayDeque() {
      return new ArrayDeque();
   }

   public static ArrayDeque newArrayDeque(Iterable elements) {
      if (elements instanceof Collection) {
         return new ArrayDeque((Collection)elements);
      } else {
         ArrayDeque<E> deque = new ArrayDeque();
         Iterables.addAll(deque, elements);
         return deque;
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ConcurrentLinkedQueue newConcurrentLinkedQueue() {
      return new ConcurrentLinkedQueue();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static ConcurrentLinkedQueue newConcurrentLinkedQueue(Iterable elements) {
      if (elements instanceof Collection) {
         return new ConcurrentLinkedQueue((Collection)elements);
      } else {
         ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue();
         Iterables.addAll(queue, elements);
         return queue;
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingDeque newLinkedBlockingDeque() {
      return new LinkedBlockingDeque();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingDeque newLinkedBlockingDeque(int capacity) {
      return new LinkedBlockingDeque(capacity);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingDeque newLinkedBlockingDeque(Iterable elements) {
      if (elements instanceof Collection) {
         return new LinkedBlockingDeque((Collection)elements);
      } else {
         LinkedBlockingDeque<E> deque = new LinkedBlockingDeque();
         Iterables.addAll(deque, elements);
         return deque;
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingQueue newLinkedBlockingQueue() {
      return new LinkedBlockingQueue();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingQueue newLinkedBlockingQueue(int capacity) {
      return new LinkedBlockingQueue(capacity);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static LinkedBlockingQueue newLinkedBlockingQueue(Iterable elements) {
      if (elements instanceof Collection) {
         return new LinkedBlockingQueue((Collection)elements);
      } else {
         LinkedBlockingQueue<E> queue = new LinkedBlockingQueue();
         Iterables.addAll(queue, elements);
         return queue;
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static PriorityBlockingQueue newPriorityBlockingQueue() {
      return new PriorityBlockingQueue();
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static PriorityBlockingQueue newPriorityBlockingQueue(Iterable elements) {
      if (elements instanceof Collection) {
         return new PriorityBlockingQueue((Collection)elements);
      } else {
         PriorityBlockingQueue<E> queue = new PriorityBlockingQueue();
         Iterables.addAll(queue, elements);
         return queue;
      }
   }

   public static PriorityQueue newPriorityQueue() {
      return new PriorityQueue();
   }

   public static PriorityQueue newPriorityQueue(Iterable elements) {
      if (elements instanceof Collection) {
         return new PriorityQueue((Collection)elements);
      } else {
         PriorityQueue<E> queue = new PriorityQueue();
         Iterables.addAll(queue, elements);
         return queue;
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static SynchronousQueue newSynchronousQueue() {
      return new SynchronousQueue();
   }

   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static int drain(BlockingQueue q, Collection buffer, int numElements, Duration timeout) throws InterruptedException {
      return drain(q, buffer, numElements, timeout.toNanos(), TimeUnit.NANOSECONDS);
   }

   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static int drain(BlockingQueue q, Collection buffer, int numElements, long timeout, TimeUnit unit) throws InterruptedException {
      Preconditions.checkNotNull(buffer);
      long deadline = System.nanoTime() + unit.toNanos(timeout);
      int added = 0;

      while(added < numElements) {
         added += q.drainTo(buffer, numElements - added);
         if (added < numElements) {
            E e = (E)q.poll(deadline - System.nanoTime(), TimeUnit.NANOSECONDS);
            if (e == null) {
               break;
            }

            buffer.add(e);
            ++added;
         }
      }

      return added;
   }

   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static int drainUninterruptibly(BlockingQueue q, Collection buffer, int numElements, Duration timeout) {
      return drainUninterruptibly(q, buffer, numElements, timeout.toNanos(), TimeUnit.NANOSECONDS);
   }

   @CanIgnoreReturnValue
   @J2ktIncompatible
   @GwtIncompatible
   public static int drainUninterruptibly(BlockingQueue q, Collection buffer, int numElements, long timeout, TimeUnit unit) {
      Preconditions.checkNotNull(buffer);
      long deadline = System.nanoTime() + unit.toNanos(timeout);
      int added = 0;
      boolean interrupted = false;

      try {
         while(added < numElements) {
            added += q.drainTo(buffer, numElements - added);
            if (added < numElements) {
               E e;
               while(true) {
                  try {
                     e = (E)q.poll(deadline - System.nanoTime(), TimeUnit.NANOSECONDS);
                     break;
                  } catch (InterruptedException var15) {
                     interrupted = true;
                  }
               }

               if (e == null) {
                  break;
               }

               buffer.add(e);
               ++added;
            }
         }
      } finally {
         if (interrupted) {
            Thread.currentThread().interrupt();
         }

      }

      return added;
   }

   public static Queue synchronizedQueue(Queue queue) {
      return Synchronized.queue(queue, (Object)null);
   }

   public static Deque synchronizedDeque(Deque deque) {
      return Synchronized.deque(deque, (Object)null);
   }
}
