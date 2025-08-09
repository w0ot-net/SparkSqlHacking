package io.netty.util;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import org.jetbrains.annotations.VisibleForTesting;

public abstract class Recycler {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(Recycler.class);
   private static final EnhancedHandle NOOP_HANDLE = new EnhancedHandle() {
      public void recycle(Object object) {
      }

      public void unguardedRecycle(Object object) {
      }

      public String toString() {
         return "NOOP_HANDLE";
      }
   };
   private static final int DEFAULT_INITIAL_MAX_CAPACITY_PER_THREAD = 4096;
   private static final int DEFAULT_MAX_CAPACITY_PER_THREAD;
   private static final int RATIO;
   private static final int DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD;
   private static final boolean BLOCKING_POOL;
   private static final boolean BATCH_FAST_TL_ONLY;
   private final int maxCapacityPerThread;
   private final int interval;
   private final int chunkSize;
   private final FastThreadLocal threadLocal;

   protected Recycler() {
      this(DEFAULT_MAX_CAPACITY_PER_THREAD);
   }

   protected Recycler(int maxCapacityPerThread) {
      this(maxCapacityPerThread, RATIO, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
   }

   /** @deprecated */
   @Deprecated
   protected Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor) {
      this(maxCapacityPerThread, RATIO, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
   }

   /** @deprecated */
   @Deprecated
   protected Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor, int ratio, int maxDelayedQueuesPerThread) {
      this(maxCapacityPerThread, ratio, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
   }

   /** @deprecated */
   @Deprecated
   protected Recycler(int maxCapacityPerThread, int maxSharedCapacityFactor, int ratio, int maxDelayedQueuesPerThread, int delayedQueueRatio) {
      this(maxCapacityPerThread, ratio, DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
   }

   protected Recycler(int maxCapacityPerThread, int ratio, int chunkSize) {
      this.threadLocal = new FastThreadLocal() {
         protected LocalPool initialValue() {
            return new LocalPool(Recycler.this.maxCapacityPerThread, Recycler.this.interval, Recycler.this.chunkSize);
         }

         protected void onRemoval(LocalPool value) throws Exception {
            super.onRemoval(value);
            MessagePassingQueue<DefaultHandle<T>> handles = value.pooledHandles;
            value.pooledHandles = null;
            value.owner = null;
            handles.clear();
         }
      };
      this.interval = Math.max(0, ratio);
      if (maxCapacityPerThread <= 0) {
         this.maxCapacityPerThread = 0;
         this.chunkSize = 0;
      } else {
         this.maxCapacityPerThread = Math.max(4, maxCapacityPerThread);
         this.chunkSize = Math.max(2, Math.min(chunkSize, this.maxCapacityPerThread >> 1));
      }

   }

   public final Object get() {
      if (this.maxCapacityPerThread == 0) {
         return this.newObject(NOOP_HANDLE);
      } else {
         LocalPool<T> localPool = (LocalPool)this.threadLocal.get();
         DefaultHandle<T> handle = localPool.claim();
         T obj;
         if (handle == null) {
            handle = localPool.newHandle();
            if (handle != null) {
               obj = (T)this.newObject(handle);
               handle.set(obj);
            } else {
               obj = (T)this.newObject(NOOP_HANDLE);
            }
         } else {
            obj = (T)handle.get();
         }

         return obj;
      }
   }

   /** @deprecated */
   @Deprecated
   public final boolean recycle(Object o, Handle handle) {
      if (handle == NOOP_HANDLE) {
         return false;
      } else {
         handle.recycle(o);
         return true;
      }
   }

   @VisibleForTesting
   final int threadLocalSize() {
      LocalPool<T> localPool = (LocalPool)this.threadLocal.getIfExists();
      return localPool == null ? 0 : localPool.pooledHandles.size() + localPool.batch.size();
   }

   protected abstract Object newObject(Handle var1);

   static {
      int maxCapacityPerThread = SystemPropertyUtil.getInt("io.netty.recycler.maxCapacityPerThread", SystemPropertyUtil.getInt("io.netty.recycler.maxCapacity", 4096));
      if (maxCapacityPerThread < 0) {
         maxCapacityPerThread = 4096;
      }

      DEFAULT_MAX_CAPACITY_PER_THREAD = maxCapacityPerThread;
      DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD = SystemPropertyUtil.getInt("io.netty.recycler.chunkSize", 32);
      RATIO = Math.max(0, SystemPropertyUtil.getInt("io.netty.recycler.ratio", 8));
      BLOCKING_POOL = SystemPropertyUtil.getBoolean("io.netty.recycler.blocking", false);
      BATCH_FAST_TL_ONLY = SystemPropertyUtil.getBoolean("io.netty.recycler.batchFastThreadLocalOnly", true);
      if (logger.isDebugEnabled()) {
         if (DEFAULT_MAX_CAPACITY_PER_THREAD == 0) {
            logger.debug("-Dio.netty.recycler.maxCapacityPerThread: disabled");
            logger.debug("-Dio.netty.recycler.ratio: disabled");
            logger.debug("-Dio.netty.recycler.chunkSize: disabled");
            logger.debug("-Dio.netty.recycler.blocking: disabled");
            logger.debug("-Dio.netty.recycler.batchFastThreadLocalOnly: disabled");
         } else {
            logger.debug("-Dio.netty.recycler.maxCapacityPerThread: {}", (Object)DEFAULT_MAX_CAPACITY_PER_THREAD);
            logger.debug("-Dio.netty.recycler.ratio: {}", (Object)RATIO);
            logger.debug("-Dio.netty.recycler.chunkSize: {}", (Object)DEFAULT_QUEUE_CHUNK_SIZE_PER_THREAD);
            logger.debug("-Dio.netty.recycler.blocking: {}", (Object)BLOCKING_POOL);
            logger.debug("-Dio.netty.recycler.batchFastThreadLocalOnly: {}", (Object)BATCH_FAST_TL_ONLY);
         }
      }

   }

   public abstract static class EnhancedHandle implements Handle {
      public abstract void unguardedRecycle(Object var1);

      private EnhancedHandle() {
      }
   }

   private static final class DefaultHandle extends EnhancedHandle {
      private static final int STATE_CLAIMED = 0;
      private static final int STATE_AVAILABLE = 1;
      private static final AtomicIntegerFieldUpdater STATE_UPDATER;
      private volatile int state;
      private final LocalPool localPool;
      private Object value;

      DefaultHandle(LocalPool localPool) {
         this.localPool = localPool;
      }

      public void recycle(Object object) {
         if (object != this.value) {
            throw new IllegalArgumentException("object does not belong to handle");
         } else {
            this.localPool.release(this, true);
         }
      }

      public void unguardedRecycle(Object object) {
         if (object != this.value) {
            throw new IllegalArgumentException("object does not belong to handle");
         } else {
            this.localPool.release(this, false);
         }
      }

      Object get() {
         return this.value;
      }

      void set(Object value) {
         this.value = value;
      }

      void toClaimed() {
         assert this.state == 1;

         STATE_UPDATER.lazySet(this, 0);
      }

      void toAvailable() {
         int prev = STATE_UPDATER.getAndSet(this, 1);
         if (prev == 1) {
            throw new IllegalStateException("Object has been recycled already.");
         }
      }

      void unguardedToAvailable() {
         int prev = this.state;
         if (prev == 1) {
            throw new IllegalStateException("Object has been recycled already.");
         } else {
            STATE_UPDATER.lazySet(this, 1);
         }
      }

      static {
         AtomicIntegerFieldUpdater<?> updater = AtomicIntegerFieldUpdater.newUpdater(DefaultHandle.class, "state");
         STATE_UPDATER = updater;
      }
   }

   private static final class LocalPool implements MessagePassingQueue.Consumer {
      private final int ratioInterval;
      private final int chunkSize;
      private final ArrayDeque batch;
      private volatile Thread owner;
      private volatile MessagePassingQueue pooledHandles;
      private int ratioCounter;

      LocalPool(int maxCapacity, int ratioInterval, int chunkSize) {
         this.ratioInterval = ratioInterval;
         this.chunkSize = chunkSize;
         this.batch = new ArrayDeque(chunkSize);
         Thread currentThread = Thread.currentThread();
         this.owner = Recycler.BATCH_FAST_TL_ONLY && !(currentThread instanceof FastThreadLocalThread) ? null : currentThread;
         if (Recycler.BLOCKING_POOL) {
            this.pooledHandles = new BlockingMessageQueue(maxCapacity);
         } else {
            this.pooledHandles = (MessagePassingQueue)PlatformDependent.newMpscQueue(chunkSize, maxCapacity);
         }

         this.ratioCounter = ratioInterval;
      }

      DefaultHandle claim() {
         MessagePassingQueue<DefaultHandle<T>> handles = this.pooledHandles;
         if (handles == null) {
            return null;
         } else {
            if (this.batch.isEmpty()) {
               handles.drain(this, this.chunkSize);
            }

            DefaultHandle<T> handle = (DefaultHandle)this.batch.pollLast();
            if (null != handle) {
               handle.toClaimed();
            }

            return handle;
         }
      }

      void release(DefaultHandle handle, boolean guarded) {
         if (guarded) {
            handle.toAvailable();
         } else {
            handle.unguardedToAvailable();
         }

         Thread owner = this.owner;
         if (owner != null && Thread.currentThread() == owner && this.batch.size() < this.chunkSize) {
            this.accept(handle);
         } else if (owner != null && isTerminated(owner)) {
            this.owner = null;
            this.pooledHandles = null;
         } else {
            MessagePassingQueue<DefaultHandle<T>> handles = this.pooledHandles;
            if (handles != null) {
               handles.relaxedOffer(handle);
            }
         }

      }

      private static boolean isTerminated(Thread owner) {
         return PlatformDependent.isJ9Jvm() ? !owner.isAlive() : owner.getState() == State.TERMINATED;
      }

      DefaultHandle newHandle() {
         if (++this.ratioCounter >= this.ratioInterval) {
            this.ratioCounter = 0;
            return new DefaultHandle(this);
         } else {
            return null;
         }
      }

      public void accept(DefaultHandle e) {
         this.batch.addLast(e);
      }
   }

   private static final class BlockingMessageQueue implements MessagePassingQueue {
      private final Queue deque;
      private final int maxCapacity;

      BlockingMessageQueue(int maxCapacity) {
         this.maxCapacity = maxCapacity;
         this.deque = new ArrayDeque();
      }

      public synchronized boolean offer(Object e) {
         return this.deque.size() == this.maxCapacity ? false : this.deque.offer(e);
      }

      public synchronized Object poll() {
         return this.deque.poll();
      }

      public synchronized Object peek() {
         return this.deque.peek();
      }

      public synchronized int size() {
         return this.deque.size();
      }

      public synchronized void clear() {
         this.deque.clear();
      }

      public synchronized boolean isEmpty() {
         return this.deque.isEmpty();
      }

      public int capacity() {
         return this.maxCapacity;
      }

      public boolean relaxedOffer(Object e) {
         return this.offer(e);
      }

      public Object relaxedPoll() {
         return this.poll();
      }

      public Object relaxedPeek() {
         return this.peek();
      }

      public int drain(MessagePassingQueue.Consumer c, int limit) {
         T obj;
         int i;
         for(i = 0; i < limit && (obj = (T)this.poll()) != null; ++i) {
            c.accept(obj);
         }

         return i;
      }

      public int fill(MessagePassingQueue.Supplier s, int limit) {
         throw new UnsupportedOperationException();
      }

      public int drain(MessagePassingQueue.Consumer c) {
         throw new UnsupportedOperationException();
      }

      public int fill(MessagePassingQueue.Supplier s) {
         throw new UnsupportedOperationException();
      }

      public void drain(MessagePassingQueue.Consumer c, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
         throw new UnsupportedOperationException();
      }

      public void fill(MessagePassingQueue.Supplier s, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
         throw new UnsupportedOperationException();
      }
   }

   public interface Handle extends ObjectPool.Handle {
   }
}
