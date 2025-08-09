package org.sparkproject.jetty.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;

@ManagedObject
public class ArrayRetainableByteBufferPool implements RetainableByteBufferPool, Dumpable {
   private static final Logger LOG = LoggerFactory.getLogger(ArrayRetainableByteBufferPool.class);
   private final RetainedBucket[] _direct;
   private final RetainedBucket[] _indirect;
   private final int _minCapacity;
   private final int _maxCapacity;
   private final long _maxHeapMemory;
   private final long _maxDirectMemory;
   private final AtomicLong _currentHeapMemory;
   private final AtomicLong _currentDirectMemory;
   private final IntUnaryOperator _bucketIndexFor;

   public ArrayRetainableByteBufferPool() {
      this(0, -1, -1, Integer.MAX_VALUE);
   }

   public ArrayRetainableByteBufferPool(int minCapacity, int factor, int maxCapacity, int maxBucketSize) {
      this(minCapacity, factor, maxCapacity, maxBucketSize, 0L, 0L);
   }

   public ArrayRetainableByteBufferPool(int minCapacity, int factor, int maxCapacity, int maxBucketSize, long maxHeapMemory, long maxDirectMemory) {
      this(minCapacity, factor, maxCapacity, maxBucketSize, (IntUnaryOperator)null, (IntUnaryOperator)null, maxHeapMemory, maxDirectMemory);
   }

   /** @deprecated */
   @Deprecated
   protected ArrayRetainableByteBufferPool(int minCapacity, int factor, int maxCapacity, int maxBucketSize, long maxHeapMemory, long maxDirectMemory, Function bucketIndexFor, Function bucketCapacity) {
      Objects.requireNonNull(bucketIndexFor);
      IntUnaryOperator var10005 = bucketIndexFor::apply;
      Objects.requireNonNull(bucketCapacity);
      this(minCapacity, factor, maxCapacity, maxBucketSize, var10005, bucketCapacity::apply, maxHeapMemory, maxDirectMemory);
   }

   protected ArrayRetainableByteBufferPool(int minCapacity, int factor, int maxCapacity, int maxBucketSize, IntUnaryOperator bucketIndexFor, IntUnaryOperator bucketCapacity, long maxHeapMemory, long maxDirectMemory) {
      this._currentHeapMemory = new AtomicLong();
      this._currentDirectMemory = new AtomicLong();
      if (minCapacity <= 0) {
         minCapacity = 0;
      }

      factor = factor <= 0 ? 4096 : factor;
      if (maxCapacity <= 0) {
         maxCapacity = 16 * factor;
      }

      if (maxCapacity % factor == 0 && factor < maxCapacity) {
         if (bucketIndexFor == null) {
            bucketIndexFor = (c) -> (c - 1) / factor;
         }

         if (bucketCapacity == null) {
            bucketCapacity = (ix) -> (ix + 1) * factor;
         }

         int length = bucketIndexFor.applyAsInt(maxCapacity) + 1;
         RetainedBucket[] directArray = new RetainedBucket[length];
         RetainedBucket[] indirectArray = new RetainedBucket[length];

         for(int i = 0; i < directArray.length; ++i) {
            int capacity = Math.min(bucketCapacity.applyAsInt(i), maxCapacity);
            directArray[i] = new RetainedBucket(capacity, maxBucketSize);
            indirectArray[i] = new RetainedBucket(capacity, maxBucketSize);
         }

         this._minCapacity = minCapacity;
         this._maxCapacity = maxCapacity;
         this._direct = directArray;
         this._indirect = indirectArray;
         this._maxHeapMemory = AbstractByteBufferPool.retainedSize(maxHeapMemory);
         this._maxDirectMemory = AbstractByteBufferPool.retainedSize(maxDirectMemory);
         this._bucketIndexFor = bucketIndexFor;
      } else {
         throw new IllegalArgumentException(String.format("The capacity factor(%d) must be a divisor of maxCapacity(%d)", factor, maxCapacity));
      }
   }

   @ManagedAttribute("The minimum pooled buffer capacity")
   public int getMinCapacity() {
      return this._minCapacity;
   }

   @ManagedAttribute("The maximum pooled buffer capacity")
   public int getMaxCapacity() {
      return this._maxCapacity;
   }

   public RetainableByteBuffer acquire(int size, boolean direct) {
      RetainedBucket bucket = this.bucketFor(size, direct);
      if (bucket == null) {
         return this.newRetainableByteBuffer(size, direct, this::removed);
      } else {
         Pool<RetainableByteBuffer>.Entry entry = bucket.acquire();
         RetainableByteBuffer buffer;
         if (entry == null) {
            Pool<RetainableByteBuffer>.Entry reservedEntry = bucket.reserve();
            if (reservedEntry != null) {
               buffer = this.newRetainableByteBuffer(bucket._capacity, direct, (retainedBuffer) -> {
                  BufferUtil.reset(retainedBuffer.getBuffer());
                  reservedEntry.release();
               });
               reservedEntry.enable(buffer, true);
               if (direct) {
                  this._currentDirectMemory.addAndGet((long)buffer.capacity());
               } else {
                  this._currentHeapMemory.addAndGet((long)buffer.capacity());
               }

               this.releaseExcessMemory(direct);
            } else {
               buffer = this.newRetainableByteBuffer(size, direct, this::removed);
            }
         } else {
            buffer = (RetainableByteBuffer)entry.getPooled();
            buffer.acquire();
         }

         return buffer;
      }
   }

   protected ByteBuffer allocate(int capacity) {
      return ByteBuffer.allocate(capacity);
   }

   protected ByteBuffer allocateDirect(int capacity) {
      return ByteBuffer.allocateDirect(capacity);
   }

   protected void removed(RetainableByteBuffer retainedBuffer) {
   }

   private RetainableByteBuffer newRetainableByteBuffer(int capacity, boolean direct, Consumer releaser) {
      ByteBuffer buffer = direct ? this.allocateDirect(capacity) : this.allocate(capacity);
      BufferUtil.clear(buffer);
      RetainableByteBuffer retainableByteBuffer = new RetainableByteBuffer(buffer, releaser);
      retainableByteBuffer.acquire();
      return retainableByteBuffer;
   }

   protected Pool poolFor(int capacity, boolean direct) {
      return this.bucketFor(capacity, direct);
   }

   private RetainedBucket bucketFor(int capacity, boolean direct) {
      if (capacity < this._minCapacity) {
         return null;
      } else {
         int idx = this._bucketIndexFor.applyAsInt(capacity);
         RetainedBucket[] buckets = direct ? this._direct : this._indirect;
         return idx >= buckets.length ? null : buckets[idx];
      }
   }

   @ManagedAttribute("The number of pooled direct ByteBuffers")
   public long getDirectByteBufferCount() {
      return this.getByteBufferCount(true);
   }

   @ManagedAttribute("The number of pooled heap ByteBuffers")
   public long getHeapByteBufferCount() {
      return this.getByteBufferCount(false);
   }

   private long getByteBufferCount(boolean direct) {
      RetainedBucket[] buckets = direct ? this._direct : this._indirect;
      return Arrays.stream(buckets).mapToLong(Pool::size).sum();
   }

   @ManagedAttribute("The number of pooled direct ByteBuffers that are available")
   public long getAvailableDirectByteBufferCount() {
      return this.getAvailableByteBufferCount(true);
   }

   @ManagedAttribute("The number of pooled heap ByteBuffers that are available")
   public long getAvailableHeapByteBufferCount() {
      return this.getAvailableByteBufferCount(false);
   }

   private long getAvailableByteBufferCount(boolean direct) {
      RetainedBucket[] buckets = direct ? this._direct : this._indirect;
      return Arrays.stream(buckets).mapToLong((bucket) -> bucket.values().stream().filter(Pool.Entry::isIdle).count()).sum();
   }

   @ManagedAttribute("The bytes retained by direct ByteBuffers")
   public long getDirectMemory() {
      return this.getMemory(true);
   }

   @ManagedAttribute("The bytes retained by heap ByteBuffers")
   public long getHeapMemory() {
      return this.getMemory(false);
   }

   private long getMemory(boolean direct) {
      return direct ? this._currentDirectMemory.get() : this._currentHeapMemory.get();
   }

   @ManagedAttribute("The available bytes retained by direct ByteBuffers")
   public long getAvailableDirectMemory() {
      return this.getAvailableMemory(true);
   }

   @ManagedAttribute("The available bytes retained by heap ByteBuffers")
   public long getAvailableHeapMemory() {
      return this.getAvailableMemory(false);
   }

   private long getAvailableMemory(boolean direct) {
      RetainedBucket[] buckets = direct ? this._direct : this._indirect;
      long total = 0L;

      for(RetainedBucket bucket : buckets) {
         int capacity = bucket._capacity;
         total += bucket.values().stream().filter(Pool.Entry::isIdle).count() * (long)capacity;
      }

      return total;
   }

   @ManagedOperation(
      value = "Clears this RetainableByteBufferPool",
      impact = "ACTION"
   )
   public void clear() {
      this.clearArray(this._direct, this._currentDirectMemory);
      this.clearArray(this._indirect, this._currentHeapMemory);
   }

   private void clearArray(RetainedBucket[] poolArray, AtomicLong memoryCounter) {
      for(RetainedBucket pool : poolArray) {
         for(Pool.Entry entry : pool.values()) {
            if (entry.remove()) {
               memoryCounter.addAndGet((long)(-((RetainableByteBuffer)entry.getPooled()).capacity()));
               this.removed((RetainableByteBuffer)entry.getPooled());
            }
         }
      }

   }

   private void releaseExcessMemory(boolean direct) {
      long maxMemory = direct ? this._maxDirectMemory : this._maxHeapMemory;
      if (maxMemory > 0L) {
         long excess = this.getMemory(direct) - maxMemory;
         if (excess > 0L) {
            this.evict(direct, excess);
         }
      }

   }

   private void evict(boolean direct, long excess) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("evicting {} bytes from {} pools", excess, direct ? "direct" : "heap");
      }

      long now = NanoTime.now();
      long totalClearedCapacity = 0L;
      RetainedBucket[] buckets = direct ? this._direct : this._indirect;

      while(totalClearedCapacity < excess) {
         for(RetainedBucket bucket : buckets) {
            Pool<RetainableByteBuffer>.Entry oldestEntry = this.findOldestEntry(now, bucket);
            if (oldestEntry != null && oldestEntry.remove()) {
               RetainableByteBuffer buffer = (RetainableByteBuffer)oldestEntry.getPooled();
               int clearedCapacity = buffer.capacity();
               if (direct) {
                  this._currentDirectMemory.addAndGet((long)(-clearedCapacity));
               } else {
                  this._currentHeapMemory.addAndGet((long)(-clearedCapacity));
               }

               totalClearedCapacity += (long)clearedCapacity;
               this.removed(buffer);
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("eviction done, cleared {} bytes from {} pools", totalClearedCapacity, direct ? "direct" : "heap");
      }

   }

   public String toString() {
      return String.format("%s{min=%d,max=%d,buckets=%d,heap=%d/%d,direct=%d/%d}", super.toString(), this._minCapacity, this._maxCapacity, this._direct.length, this._currentHeapMemory.get(), this._maxHeapMemory, this._currentDirectMemory.get(), this._maxDirectMemory);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this, DumpableCollection.fromArray("direct", this._direct), DumpableCollection.fromArray("indirect", this._indirect));
   }

   private Pool.Entry findOldestEntry(long now, Pool bucket) {
      Pool<RetainableByteBuffer>.Entry oldestEntry = null;
      RetainableByteBuffer oldestBuffer = null;
      long oldestAge = 0L;

      for(Pool.Entry entry : bucket.values()) {
         RetainableByteBuffer buffer = (RetainableByteBuffer)entry.getPooled();
         if (buffer != null) {
            long age = NanoTime.elapsed(buffer.getLastUpdate(), now);
            if (oldestBuffer == null || age > oldestAge) {
               oldestEntry = entry;
               oldestBuffer = buffer;
               oldestAge = age;
            }
         }
      }

      return oldestEntry;
   }

   private static class RetainedBucket extends Pool {
      private final int _capacity;

      RetainedBucket(int capacity, int size) {
         super(Pool.StrategyType.THREAD_ID, size, true);
         this._capacity = capacity;
      }

      public String toString() {
         int entries = 0;
         int inUse = 0;

         for(Pool.Entry entry : this.values()) {
            ++entries;
            if (entry.isInUse()) {
               ++inUse;
            }
         }

         return String.format("%s{capacity=%d,inuse=%d(%d%%)}", super.toString(), this._capacity, inUse, entries > 0 ? inUse * 100 / entries : 0);
      }
   }

   public static class Tracking extends ArrayRetainableByteBufferPool {
      private static final Logger LOG = LoggerFactory.getLogger(Tracking.class);
      private final Set buffers = ConcurrentHashMap.newKeySet();

      public Tracking() {
      }

      public Tracking(int minCapacity, int factor, int maxCapacity, int maxBucketSize) {
         super(minCapacity, factor, maxCapacity, maxBucketSize);
      }

      public Tracking(int minCapacity, int factor, int maxCapacity, int maxBucketSize, long maxHeapMemory, long maxDirectMemory) {
         super(minCapacity, factor, maxCapacity, maxBucketSize, maxHeapMemory, maxDirectMemory);
      }

      public Tracking(int minCapacity, int factor, int maxCapacity, int maxBucketSize, IntUnaryOperator bucketIndexFor, IntUnaryOperator bucketCapacity, long maxHeapMemory, long maxDirectMemory) {
         super(minCapacity, factor, maxCapacity, maxBucketSize, bucketIndexFor, bucketCapacity, maxHeapMemory, maxDirectMemory);
      }

      public RetainableByteBuffer acquire(int size, boolean direct) {
         RetainableByteBuffer buffer = super.acquire(size, direct);
         Buffer wrapper = new Buffer(buffer, size);
         if (LOG.isDebugEnabled()) {
            LOG.debug("acquired {}", wrapper);
         }

         this.buffers.add(wrapper);
         return wrapper;
      }

      public Set getLeaks() {
         return this.buffers;
      }

      public String dumpLeaks() {
         return (String)this.getLeaks().stream().map(Buffer::dump).collect(Collectors.joining(System.lineSeparator()));
      }

      public class Buffer extends RetainableByteBuffer {
         private final RetainableByteBuffer wrapped;
         private final int size;
         private final Instant acquireInstant;
         private final Throwable acquireStack;
         private final List retainStacks = new CopyOnWriteArrayList();
         private final List releaseStacks = new CopyOnWriteArrayList();
         private final List overReleaseStacks = new CopyOnWriteArrayList();

         private Buffer(RetainableByteBuffer wrapped, int size) {
            super(wrapped.getBuffer(), (x) -> {
            });
            this.wrapped = wrapped;
            this.size = size;
            this.acquireInstant = Instant.now();
            this.acquireStack = new Throwable();
         }

         public int getSize() {
            return this.size;
         }

         public Instant getAcquireInstant() {
            return this.acquireInstant;
         }

         public Throwable getAcquireStack() {
            return this.acquireStack;
         }

         protected void acquire() {
            this.wrapped.acquire();
         }

         public boolean isRetained() {
            return this.wrapped.isRetained();
         }

         public void retain() {
            this.wrapped.retain();
            this.retainStacks.add(new Throwable());
         }

         public boolean release() {
            try {
               boolean released = this.wrapped.release();
               if (released) {
                  Tracking.this.buffers.remove(this);
                  if (ArrayRetainableByteBufferPool.Tracking.LOG.isDebugEnabled()) {
                     ArrayRetainableByteBufferPool.Tracking.LOG.debug("released {}", this);
                  }
               }

               this.releaseStacks.add(new Throwable());
               return released;
            } catch (IllegalStateException e) {
               Tracking.this.buffers.add(this);
               this.overReleaseStacks.add(new Throwable());
               throw e;
            }
         }

         public String dump() {
            StringWriter w = new StringWriter();
            PrintWriter pw = new PrintWriter(w);
            this.getAcquireStack().printStackTrace(pw);
            pw.println("\n" + this.retainStacks.size() + " retain(s)");

            for(Throwable retainStack : this.retainStacks) {
               retainStack.printStackTrace(pw);
            }

            pw.println("\n" + this.releaseStacks.size() + " release(s)");

            for(Throwable releaseStack : this.releaseStacks) {
               releaseStack.printStackTrace(pw);
            }

            pw.println("\n" + this.overReleaseStacks.size() + " over-release(s)");

            for(Throwable overReleaseStack : this.overReleaseStacks) {
               overReleaseStack.printStackTrace(pw);
            }

            return String.format("%s@%x of %d bytes on %s wrapping %s acquired at %s", this.getClass().getSimpleName(), this.hashCode(), this.getSize(), this.getAcquireInstant(), this.wrapped, w);
         }
      }
   }
}
