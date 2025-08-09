package com.fasterxml.jackson.core.util;

public final class JsonRecyclerPools {
   public static RecyclerPool defaultPool() {
      return threadLocalPool();
   }

   public static RecyclerPool threadLocalPool() {
      return JsonRecyclerPools.ThreadLocalPool.GLOBAL;
   }

   public static RecyclerPool nonRecyclingPool() {
      return JsonRecyclerPools.NonRecyclingPool.GLOBAL;
   }

   public static RecyclerPool sharedConcurrentDequePool() {
      return JsonRecyclerPools.ConcurrentDequePool.GLOBAL;
   }

   public static RecyclerPool newConcurrentDequePool() {
      return JsonRecyclerPools.ConcurrentDequePool.construct();
   }

   /** @deprecated */
   @Deprecated
   public static RecyclerPool sharedLockFreePool() {
      return JsonRecyclerPools.LockFreePool.GLOBAL;
   }

   /** @deprecated */
   @Deprecated
   public static RecyclerPool newLockFreePool() {
      return JsonRecyclerPools.LockFreePool.construct();
   }

   public static RecyclerPool sharedBoundedPool() {
      return JsonRecyclerPools.BoundedPool.GLOBAL;
   }

   public static RecyclerPool newBoundedPool(int size) {
      return JsonRecyclerPools.BoundedPool.construct(size);
   }

   public static class ThreadLocalPool extends RecyclerPool.ThreadLocalPoolBase {
      private static final long serialVersionUID = 1L;
      protected static final ThreadLocalPool GLOBAL = new ThreadLocalPool();

      private ThreadLocalPool() {
      }

      public BufferRecycler acquirePooled() {
         return BufferRecyclers.getBufferRecycler();
      }

      protected Object readResolve() {
         return GLOBAL;
      }
   }

   public static class NonRecyclingPool extends RecyclerPool.NonRecyclingPoolBase {
      private static final long serialVersionUID = 1L;
      protected static final NonRecyclingPool GLOBAL = new NonRecyclingPool();

      protected NonRecyclingPool() {
      }

      public BufferRecycler acquirePooled() {
         return new BufferRecycler();
      }

      protected Object readResolve() {
         return GLOBAL;
      }
   }

   public static class ConcurrentDequePool extends RecyclerPool.ConcurrentDequePoolBase {
      private static final long serialVersionUID = 1L;
      protected static final ConcurrentDequePool GLOBAL = new ConcurrentDequePool(-1);

      protected ConcurrentDequePool(int serialization) {
         super(serialization);
      }

      public static ConcurrentDequePool construct() {
         return new ConcurrentDequePool(1);
      }

      public BufferRecycler createPooled() {
         return new BufferRecycler();
      }

      protected Object readResolve() {
         return this._resolveToShared(GLOBAL).orElseGet(() -> construct());
      }
   }

   /** @deprecated */
   @Deprecated
   public static class LockFreePool extends RecyclerPool.LockFreePoolBase {
      private static final long serialVersionUID = 1L;
      protected static final LockFreePool GLOBAL = new LockFreePool(-1);

      protected LockFreePool(int serialization) {
         super(serialization);
      }

      public static LockFreePool construct() {
         return new LockFreePool(1);
      }

      public BufferRecycler createPooled() {
         return new BufferRecycler();
      }

      protected Object readResolve() {
         return this._resolveToShared(GLOBAL).orElseGet(() -> construct());
      }
   }

   public static class BoundedPool extends RecyclerPool.BoundedPoolBase {
      private static final long serialVersionUID = 1L;
      protected static final BoundedPool GLOBAL = new BoundedPool(-1);

      protected BoundedPool(int capacityAsId) {
         super(capacityAsId);
      }

      public static BoundedPool construct(int capacity) {
         if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0, was: " + capacity);
         } else {
            return new BoundedPool(capacity);
         }
      }

      public BufferRecycler createPooled() {
         return new BufferRecycler();
      }

      protected Object readResolve() {
         return this._resolveToShared(GLOBAL).orElseGet(() -> construct(this._serialization));
      }
   }
}
