package com.fasterxml.jackson.core.util;

import java.io.Serializable;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicReference;

public interface RecyclerPool extends Serializable {
   default WithPool acquireAndLinkPooled() {
      return this.acquirePooled().withPool(this);
   }

   WithPool acquirePooled();

   void releasePooled(WithPool var1);

   default boolean clear() {
      return false;
   }

   default int pooledCount() {
      return -1;
   }

   public abstract static class ThreadLocalPoolBase implements RecyclerPool {
      private static final long serialVersionUID = 1L;

      protected ThreadLocalPoolBase() {
      }

      public WithPool acquireAndLinkPooled() {
         return this.acquirePooled();
      }

      public abstract WithPool acquirePooled();

      public void releasePooled(WithPool pooled) {
      }

      public int pooledCount() {
         return -1;
      }

      public boolean clear() {
         return false;
      }
   }

   public abstract static class NonRecyclingPoolBase implements RecyclerPool {
      private static final long serialVersionUID = 1L;

      public WithPool acquireAndLinkPooled() {
         return this.acquirePooled();
      }

      public abstract WithPool acquirePooled();

      public void releasePooled(WithPool pooled) {
      }

      public int pooledCount() {
         return 0;
      }

      public boolean clear() {
         return true;
      }
   }

   public abstract static class StatefulImplBase implements RecyclerPool {
      private static final long serialVersionUID = 1L;
      public static final int SERIALIZATION_SHARED = -1;
      public static final int SERIALIZATION_NON_SHARED = 1;
      protected final int _serialization;

      protected StatefulImplBase(int serialization) {
         this._serialization = serialization;
      }

      protected Optional _resolveToShared(StatefulImplBase shared) {
         return this._serialization == -1 ? Optional.of(shared) : Optional.empty();
      }

      public abstract WithPool createPooled();
   }

   public abstract static class ConcurrentDequePoolBase extends StatefulImplBase {
      private static final long serialVersionUID = 1L;
      protected final transient Deque pool = new ConcurrentLinkedDeque();

      protected ConcurrentDequePoolBase(int serialization) {
         super(serialization);
      }

      public WithPool acquirePooled() {
         P pooled = (P)((WithPool)this.pool.pollFirst());
         if (pooled == null) {
            pooled = (P)this.createPooled();
         }

         return pooled;
      }

      public void releasePooled(WithPool pooled) {
         this.pool.offerLast(pooled);
      }

      public int pooledCount() {
         return this.pool.size();
      }

      public boolean clear() {
         this.pool.clear();
         return true;
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class LockFreePoolBase extends StatefulImplBase {
      private static final long serialVersionUID = 1L;
      private final transient AtomicReference head = new AtomicReference();

      protected LockFreePoolBase(int serialization) {
         super(serialization);
      }

      public WithPool acquirePooled() {
         for(int i = 0; i < 3; ++i) {
            Node<P> currentHead = (Node)this.head.get();
            if (currentHead == null) {
               return this.createPooled();
            }

            if (this.head.compareAndSet(currentHead, currentHead.next)) {
               currentHead.next = null;
               return (WithPool)currentHead.value;
            }
         }

         return this.createPooled();
      }

      public void releasePooled(WithPool pooled) {
         Node<P> newHead = new Node(pooled);

         for(int i = 0; i < 3; ++i) {
            newHead.next = (Node)this.head.get();
            if (this.head.compareAndSet(newHead.next, newHead)) {
               return;
            }
         }

      }

      public int pooledCount() {
         int count = 0;

         for(Node<P> curr = (Node)this.head.get(); curr != null; curr = curr.next) {
            ++count;
         }

         return count;
      }

      public boolean clear() {
         this.head.set((Object)null);
         return true;
      }

      protected static class Node {
         final Object value;
         Node next;

         Node(Object value) {
            this.value = value;
         }
      }
   }

   public abstract static class BoundedPoolBase extends StatefulImplBase {
      private static final long serialVersionUID = 1L;
      public static final int DEFAULT_CAPACITY = 100;
      private final transient ArrayBlockingQueue pool;
      private final transient int capacity;

      protected BoundedPoolBase(int capacityAsId) {
         super(capacityAsId);
         this.capacity = capacityAsId <= 0 ? 100 : capacityAsId;
         this.pool = new ArrayBlockingQueue(this.capacity);
      }

      public WithPool acquirePooled() {
         P pooled = (P)((WithPool)this.pool.poll());
         if (pooled == null) {
            pooled = (P)this.createPooled();
         }

         return pooled;
      }

      public void releasePooled(WithPool pooled) {
         this.pool.offer(pooled);
      }

      public int pooledCount() {
         return this.pool.size();
      }

      public boolean clear() {
         this.pool.clear();
         return true;
      }

      public int capacity() {
         return this.capacity;
      }
   }

   public interface WithPool {
      WithPool withPool(RecyclerPool var1);

      void releaseToPool();
   }
}
