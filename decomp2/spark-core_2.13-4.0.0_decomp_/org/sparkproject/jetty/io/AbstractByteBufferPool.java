package org.sparkproject.jetty.io;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;

@ManagedObject
abstract class AbstractByteBufferPool implements ByteBufferPool {
   public static final int DEFAULT_FACTOR = 4096;
   public static final int DEFAULT_MAX_CAPACITY_BY_FACTOR = 16;
   private final int _factor;
   private final int _maxCapacity;
   private final int _maxBucketSize;
   private final long _maxHeapMemory;
   private final long _maxDirectMemory;
   private final AtomicLong _heapMemory = new AtomicLong();
   private final AtomicLong _directMemory = new AtomicLong();
   private final RetainableByteBufferPool _retainableByteBufferPool;

   protected AbstractByteBufferPool(int factor, int maxCapacity, int maxBucketSize, long maxHeapMemory, long maxDirectMemory, long retainedHeapMemory, long retainedDirectMemory) {
      this._factor = factor <= 0 ? 4096 : factor;
      this._maxCapacity = maxCapacity > 0 ? maxCapacity : 16 * this._factor;
      this._maxBucketSize = maxBucketSize;
      this._maxHeapMemory = memorySize(maxHeapMemory);
      this._maxDirectMemory = memorySize(maxDirectMemory);
      this._retainableByteBufferPool = retainedHeapMemory == -2L && retainedDirectMemory == -2L ? RetainableByteBufferPool.from(this) : this.newRetainableByteBufferPool(factor, maxCapacity, maxBucketSize, retainedSize(retainedHeapMemory), retainedSize(retainedDirectMemory));
   }

   static long retainedSize(long size) {
      return size == -2L ? 0L : memorySize(size);
   }

   static long memorySize(long size) {
      if (size < 0L) {
         return -1L;
      } else {
         return size == 0L ? Runtime.getRuntime().maxMemory() / 4L : size;
      }
   }

   protected RetainableByteBufferPool newRetainableByteBufferPool(int factor, int maxCapacity, int maxBucketSize, long retainedHeapMemory, long retainedDirectMemory) {
      return RetainableByteBufferPool.from(this);
   }

   public RetainableByteBufferPool asRetainableByteBufferPool() {
      return this._retainableByteBufferPool;
   }

   protected int getCapacityFactor() {
      return this._factor;
   }

   protected int getMaxCapacity() {
      return this._maxCapacity;
   }

   protected int getMaxBucketSize() {
      return this._maxBucketSize;
   }

   /** @deprecated */
   @Deprecated
   protected void decrementMemory(ByteBuffer buffer) {
      this.updateMemory(buffer, false);
   }

   /** @deprecated */
   @Deprecated
   protected void incrementMemory(ByteBuffer buffer) {
      this.updateMemory(buffer, true);
   }

   private void updateMemory(ByteBuffer buffer, boolean addOrSub) {
      AtomicLong memory = buffer.isDirect() ? this._directMemory : this._heapMemory;
      int capacity = buffer.capacity();
      memory.addAndGet(addOrSub ? (long)capacity : (long)(-capacity));
   }

   protected void releaseExcessMemory(boolean direct, Consumer clearFn) {
      long maxMemory = direct ? this._maxDirectMemory : this._maxHeapMemory;
      if (maxMemory > 0L) {
         while(this.getMemory(direct) > maxMemory) {
            clearFn.accept(direct);
         }
      }

   }

   @ManagedAttribute("The bytes retained by direct ByteBuffers")
   public long getDirectMemory() {
      return this.getMemory(true);
   }

   @ManagedAttribute("The bytes retained by heap ByteBuffers")
   public long getHeapMemory() {
      return this.getMemory(false);
   }

   @ManagedAttribute("The max num of bytes that can be retained from direct ByteBuffers")
   public long getMaxDirectMemory() {
      return this._maxDirectMemory;
   }

   @ManagedAttribute("The max num of bytes that can be retained from heap ByteBuffers")
   public long getMaxHeapMemory() {
      return this._maxHeapMemory;
   }

   public long getMemory(boolean direct) {
      AtomicLong memory = direct ? this._directMemory : this._heapMemory;
      return memory.get();
   }

   IntConsumer updateMemory(boolean direct) {
      IntConsumer var2;
      if (direct) {
         AtomicLong var10000 = this._directMemory;
         Objects.requireNonNull(var10000);
         var2 = var10000::addAndGet;
      } else {
         AtomicLong var3 = this._heapMemory;
         Objects.requireNonNull(var3);
         var2 = var3::addAndGet;
      }

      return var2;
   }

   @ManagedOperation(
      value = "Clears this ByteBufferPool",
      impact = "ACTION"
   )
   public void clear() {
      this._heapMemory.set(0L);
      this._directMemory.set(0L);
   }

   protected static class Bucket {
      private final Queue _queue;
      private final int _capacity;
      private final int _maxSize;
      private final AtomicInteger _size;
      private final AtomicLong _lastUpdate;
      private final IntConsumer _memoryFunction;

      /** @deprecated */
      @Deprecated
      public Bucket(int capacity, int maxSize) {
         this(capacity, maxSize, (i) -> {
         });
      }

      public Bucket(int capacity, int maxSize, IntConsumer memoryFunction) {
         this._queue = new ConcurrentLinkedQueue();
         this._lastUpdate = new AtomicLong(NanoTime.now());
         this._capacity = capacity;
         this._maxSize = maxSize;
         this._size = maxSize > 0 ? new AtomicInteger() : null;
         this._memoryFunction = (IntConsumer)Objects.requireNonNull(memoryFunction);
      }

      public ByteBuffer acquire() {
         ByteBuffer buffer = (ByteBuffer)this._queue.poll();
         if (buffer != null) {
            if (this._size != null) {
               this._size.decrementAndGet();
            }

            this._memoryFunction.accept(-buffer.capacity());
         }

         return buffer;
      }

      public void release(ByteBuffer buffer) {
         this.resetUpdateTime();
         BufferUtil.reset(buffer);
         if (this._size != null && this._size.incrementAndGet() > this._maxSize) {
            this._size.decrementAndGet();
         } else {
            this._queue.offer(buffer);
            this._memoryFunction.accept(buffer.capacity());
         }

      }

      void resetUpdateTime() {
         this._lastUpdate.lazySet(NanoTime.now());
      }

      public void clear() {
         int size = this._size == null ? 0 : this._size.get() - 1;

         while(size >= 0) {
            ByteBuffer buffer = this.acquire();
            if (buffer == null) {
               break;
            }

            if (this._size != null) {
               --size;
            }
         }

      }

      boolean isEmpty() {
         return this._queue.isEmpty();
      }

      int size() {
         return this._queue.size();
      }

      long getLastUpdate() {
         return this._lastUpdate.getOpaque();
      }

      public String toString() {
         return String.format("%s@%x{capacity=%d, size=%d, maxSize=%d}", this.getClass().getSimpleName(), this.hashCode(), this._capacity, this.size(), this._maxSize);
      }
   }
}
