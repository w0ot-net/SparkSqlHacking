package org.apache.zookeeper.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircularBlockingQueue implements BlockingQueue {
   private static final Logger LOG = LoggerFactory.getLogger(CircularBlockingQueue.class);
   private final ReentrantLock lock;
   private final Condition notEmpty;
   private final ArrayDeque queue;
   private final int maxSize;
   private long droppedCount;

   public CircularBlockingQueue(int queueSize) {
      this.queue = new ArrayDeque(queueSize);
      this.maxSize = queueSize;
      this.lock = new ReentrantLock();
      this.notEmpty = this.lock.newCondition();
      this.droppedCount = 0L;
   }

   public boolean offer(Object e) {
      Objects.requireNonNull(e);
      ReentrantLock lock = this.lock;
      lock.lock();

      try {
         if (this.queue.size() == this.maxSize) {
            E discard = (E)this.queue.remove();
            ++this.droppedCount;
            LOG.debug("Queue is full. Discarding oldest element [count={}]: {}", this.droppedCount, discard);
         }

         this.queue.add(e);
         this.notEmpty.signal();
      } finally {
         lock.unlock();
      }

      return true;
   }

   public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
      long nanos = unit.toNanos(timeout);
      ReentrantLock lock = this.lock;
      lock.lockInterruptibly();

      try {
         while(true) {
            if (this.queue.isEmpty()) {
               if (nanos > 0L) {
                  nanos = this.notEmpty.awaitNanos(nanos);
                  continue;
               }

               Object var11 = null;
               return var11;
            }

            Object var7 = this.queue.poll();
            return var7;
         }
      } finally {
         lock.unlock();
      }
   }

   public Object take() throws InterruptedException {
      ReentrantLock lock = this.lock;
      lock.lockInterruptibly();

      Object var2;
      try {
         while(this.queue.isEmpty()) {
            this.notEmpty.await();
         }

         var2 = this.queue.poll();
      } finally {
         lock.unlock();
      }

      return var2;
   }

   public boolean isEmpty() {
      ReentrantLock lock = this.lock;
      lock.lock();

      boolean var2;
      try {
         var2 = this.queue.isEmpty();
      } finally {
         lock.unlock();
      }

      return var2;
   }

   public int size() {
      ReentrantLock lock = this.lock;
      lock.lock();

      int var2;
      try {
         var2 = this.queue.size();
      } finally {
         lock.unlock();
      }

      return var2;
   }

   public long getDroppedCount() {
      return this.droppedCount;
   }

   boolean isConsumerThreadBlocked() {
      ReentrantLock lock = this.lock;
      lock.lock();

      boolean var2;
      try {
         var2 = lock.getWaitQueueLength(this.notEmpty) > 0;
      } finally {
         lock.unlock();
      }

      return var2;
   }

   public int drainTo(Collection c) {
      throw new UnsupportedOperationException();
   }

   public Object poll() {
      throw new UnsupportedOperationException();
   }

   public Object element() {
      throw new UnsupportedOperationException();
   }

   public Object peek() {
      throw new UnsupportedOperationException();
   }

   public Object remove() {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(Collection arg0) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public boolean containsAll(Collection arg0) {
      throw new UnsupportedOperationException();
   }

   public Iterator iterator() {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection arg0) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection arg0) {
      throw new UnsupportedOperationException();
   }

   public Object[] toArray() {
      throw new UnsupportedOperationException();
   }

   public Object[] toArray(Object[] arg0) {
      throw new UnsupportedOperationException();
   }

   public boolean add(Object e) {
      throw new UnsupportedOperationException();
   }

   public boolean contains(Object o) {
      throw new UnsupportedOperationException();
   }

   public int drainTo(Collection c, int maxElements) {
      throw new UnsupportedOperationException();
   }

   public boolean offer(Object e, long timeout, TimeUnit unit) throws InterruptedException {
      throw new UnsupportedOperationException();
   }

   public void put(Object e) throws InterruptedException {
      throw new UnsupportedOperationException();
   }

   public int remainingCapacity() {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException();
   }
}
