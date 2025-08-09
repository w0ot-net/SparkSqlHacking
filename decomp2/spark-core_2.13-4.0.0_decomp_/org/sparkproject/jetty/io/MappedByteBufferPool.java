package org.sparkproject.jetty.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;

@ManagedObject
public class MappedByteBufferPool extends AbstractByteBufferPool implements Dumpable {
   private static final Logger LOG = LoggerFactory.getLogger(MappedByteBufferPool.class);
   private final ConcurrentMap _directBuffers;
   private final ConcurrentMap _heapBuffers;
   private final Function _newBucket;
   private boolean _detailedDump;

   public MappedByteBufferPool() {
      this(-1);
   }

   public MappedByteBufferPool(int factor) {
      this(factor, -1);
   }

   public MappedByteBufferPool(int factor, int maxBucketSize) {
      this(factor, maxBucketSize, (Function)null);
   }

   private MappedByteBufferPool(int factor, int maxBucketSize, Function newBucket) {
      this(factor, maxBucketSize, newBucket, 0L, 0L, 0L, 0L);
   }

   public MappedByteBufferPool(int factor, int maxBucketSize, long maxHeapMemory, long maxDirectMemory) {
      this(factor, maxBucketSize, (Function)null, maxHeapMemory, maxDirectMemory, maxHeapMemory, maxDirectMemory);
   }

   public MappedByteBufferPool(int factor, int maxBucketSize, long maxHeapMemory, long maxDirectMemory, long retainedHeapMemory, long retainedDirectMemory) {
      this(factor, maxBucketSize, (Function)null, maxHeapMemory, maxDirectMemory, retainedHeapMemory, retainedDirectMemory);
   }

   private MappedByteBufferPool(int factor, int maxBucketSize, Function newBucket, long maxHeapMemory, long maxDirectMemory, long retainedHeapMemory, long retainedDirectMemory) {
      super(factor, 0, maxBucketSize, maxHeapMemory, maxDirectMemory, retainedHeapMemory, retainedDirectMemory);
      this._directBuffers = new ConcurrentHashMap();
      this._heapBuffers = new ConcurrentHashMap();
      this._detailedDump = false;
      this._newBucket = newBucket;
   }

   protected RetainableByteBufferPool newRetainableByteBufferPool(int factor, int maxCapacity, int maxBucketSize, long retainedHeapMemory, long retainedDirectMemory) {
      return new Retained(factor, maxCapacity, maxBucketSize, retainedHeapMemory, retainedDirectMemory);
   }

   private AbstractByteBufferPool.Bucket newBucket(int key, boolean direct) {
      return this._newBucket != null ? (AbstractByteBufferPool.Bucket)this._newBucket.apply(key) : new AbstractByteBufferPool.Bucket(this.capacityFor(key), this.getMaxBucketSize(), this.updateMemory(direct));
   }

   public ByteBuffer acquire(int size, boolean direct) {
      int b = this.bucketFor(size);
      int capacity = this.capacityFor(b);
      ConcurrentMap<Integer, AbstractByteBufferPool.Bucket> buffers = this.bucketsFor(direct);
      AbstractByteBufferPool.Bucket bucket = (AbstractByteBufferPool.Bucket)buffers.get(b);
      if (bucket == null) {
         return this.newByteBuffer(capacity, direct);
      } else {
         ByteBuffer buffer = bucket.acquire();
         return buffer == null ? this.newByteBuffer(capacity, direct) : buffer;
      }
   }

   public void release(ByteBuffer buffer) {
      if (buffer != null) {
         int capacity = buffer.capacity();
         int b = this.bucketFor(capacity);
         if (capacity != this.capacityFor(b)) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("ByteBuffer {} does not belong to this pool, discarding it", BufferUtil.toDetailString(buffer));
            }

         } else {
            boolean direct = buffer.isDirect();
            ConcurrentMap<Integer, AbstractByteBufferPool.Bucket> buckets = this.bucketsFor(direct);
            AbstractByteBufferPool.Bucket bucket = (AbstractByteBufferPool.Bucket)buckets.computeIfAbsent(b, (i) -> this.newBucket(i, direct));
            bucket.release(buffer);
            this.releaseExcessMemory(direct, this::releaseMemory);
         }
      }
   }

   public void clear() {
      super.clear();
      this._directBuffers.values().forEach(AbstractByteBufferPool.Bucket::clear);
      this._directBuffers.clear();
      this._heapBuffers.values().forEach(AbstractByteBufferPool.Bucket::clear);
      this._heapBuffers.clear();
   }

   protected void releaseMemory(boolean direct) {
      long oldest = Long.MAX_VALUE;
      int index = -1;
      ConcurrentMap<Integer, AbstractByteBufferPool.Bucket> buckets = this.bucketsFor(direct);

      for(Map.Entry entry : buckets.entrySet()) {
         AbstractByteBufferPool.Bucket bucket = (AbstractByteBufferPool.Bucket)entry.getValue();
         if (!bucket.isEmpty()) {
            long lastUpdateNanoTime = bucket.getLastUpdate();
            if (oldest == Long.MAX_VALUE || NanoTime.isBefore(lastUpdateNanoTime, oldest)) {
               oldest = lastUpdateNanoTime;
               index = (Integer)entry.getKey();
            }
         }
      }

      if (index >= 0) {
         AbstractByteBufferPool.Bucket bucket = (AbstractByteBufferPool.Bucket)buckets.remove(index);
         if (bucket != null) {
            bucket.clear();
         }
      }

   }

   protected int bucketFor(int capacity) {
      return (int)Math.ceil((double)capacity / (double)this.getCapacityFactor());
   }

   protected int capacityFor(int bucket) {
      return bucket * this.getCapacityFactor();
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
      return this.bucketsFor(direct).values().stream().mapToLong(AbstractByteBufferPool.Bucket::size).sum();
   }

   ConcurrentMap bucketsFor(boolean direct) {
      return direct ? this._directBuffers : this._heapBuffers;
   }

   public boolean isDetailedDump() {
      return this._detailedDump;
   }

   public void setDetailedDump(boolean detailedDump) {
      this._detailedDump = detailedDump;
   }

   public void dump(Appendable out, String indent) throws IOException {
      List<Object> dump = new ArrayList();
      dump.add(String.format("HeapMemory: %d/%d", this.getHeapMemory(), this.getMaxHeapMemory()));
      dump.add(String.format("DirectMemory: %d/%d", this.getDirectMemory(), this.getMaxDirectMemory()));
      if (this.isDetailedDump()) {
         dump.add(new DumpableCollection("Indirect Buckets", this._heapBuffers.values()));
         dump.add(new DumpableCollection("Direct Buckets", this._directBuffers.values()));
      } else {
         dump.add("Indirect Buckets size=" + this._heapBuffers.size());
         dump.add("Direct Buckets size=" + this._directBuffers.size());
      }

      Dumpable.dumpObjects(out, indent, this, dump);
   }

   public String toString() {
      return String.format("%s@%x{maxQueueLength=%s, factor=%s}", this.getClass().getSimpleName(), this.hashCode(), this.getMaxBucketSize(), this.getCapacityFactor());
   }

   public static class Tagged extends MappedByteBufferPool {
      private final AtomicInteger tag = new AtomicInteger();

      public ByteBuffer newByteBuffer(int capacity, boolean direct) {
         ByteBuffer buffer = super.newByteBuffer(capacity + 4, direct);
         buffer.limit(buffer.capacity());
         buffer.putInt(this.tag.incrementAndGet());
         ByteBuffer slice = buffer.slice();
         BufferUtil.clear(slice);
         return slice;
      }
   }

   protected class Retained extends ArrayRetainableByteBufferPool {
      public Retained(int factor, int maxCapacity, int maxBucketSize, long retainedHeapMemory, long retainedDirectMemory) {
         super(0, factor, maxCapacity, maxBucketSize, retainedHeapMemory, retainedDirectMemory);
      }

      protected ByteBuffer allocate(int capacity) {
         return MappedByteBufferPool.this.acquire(capacity, false);
      }

      protected ByteBuffer allocateDirect(int capacity) {
         return MappedByteBufferPool.this.acquire(capacity, true);
      }

      protected void removed(RetainableByteBuffer retainedBuffer) {
         MappedByteBufferPool.this.release(retainedBuffer.getBuffer());
      }
   }
}
