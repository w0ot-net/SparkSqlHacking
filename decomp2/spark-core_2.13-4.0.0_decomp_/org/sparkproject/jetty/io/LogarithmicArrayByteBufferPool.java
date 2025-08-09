package org.sparkproject.jetty.io;

import org.sparkproject.jetty.util.NanoTime;

public class LogarithmicArrayByteBufferPool extends ArrayByteBufferPool {
   public LogarithmicArrayByteBufferPool() {
      this(-1, -1, -1);
   }

   public LogarithmicArrayByteBufferPool(int minCapacity, int maxCapacity) {
      this(minCapacity, maxCapacity, -1, -1L, -1L);
   }

   public LogarithmicArrayByteBufferPool(int minCapacity, int maxCapacity, int maxQueueLength) {
      this(minCapacity, maxCapacity, maxQueueLength, -1L, -1L);
   }

   public LogarithmicArrayByteBufferPool(int minCapacity, int maxCapacity, int maxQueueLength, long maxHeapMemory, long maxDirectMemory) {
      this(minCapacity, maxCapacity, maxQueueLength, maxHeapMemory, maxDirectMemory, maxHeapMemory, maxDirectMemory);
   }

   public LogarithmicArrayByteBufferPool(int minCapacity, int maxCapacity, int maxQueueLength, long maxHeapMemory, long maxDirectMemory, long retainedHeapMemory, long retainedDirectMemory) {
      super(minCapacity, -1, maxCapacity, maxQueueLength, maxHeapMemory, maxDirectMemory, retainedHeapMemory, retainedDirectMemory);
   }

   protected RetainableByteBufferPool newRetainableByteBufferPool(int factor, int maxCapacity, int maxBucketSize, long retainedHeapMemory, long retainedDirectMemory) {
      return new LogarithmicRetainablePool(0, maxCapacity, maxBucketSize, retainedHeapMemory, retainedDirectMemory);
   }

   protected int bucketFor(int capacity) {
      return 32 - Integer.numberOfLeadingZeros(capacity - 1);
   }

   protected int capacityFor(int bucket) {
      return 1 << bucket;
   }

   protected void releaseMemory(boolean direct) {
      long oldest = Long.MAX_VALUE;
      int index = -1;
      AbstractByteBufferPool.Bucket[] buckets = this.bucketsFor(direct);

      for(int i = 0; i < buckets.length; ++i) {
         AbstractByteBufferPool.Bucket bucket = buckets[i];
         if (!bucket.isEmpty()) {
            long lastUpdateNanoTime = bucket.getLastUpdate();
            if (oldest == Long.MAX_VALUE || NanoTime.isBefore(lastUpdateNanoTime, oldest)) {
               oldest = lastUpdateNanoTime;
               index = i;
            }
         }
      }

      if (index >= 0) {
         AbstractByteBufferPool.Bucket bucket = buckets[index];
         bucket.acquire();
         bucket.resetUpdateTime();
      }

   }

   public static class LogarithmicRetainablePool extends ArrayRetainableByteBufferPool {
      public LogarithmicRetainablePool() {
         this(0, -1, Integer.MAX_VALUE);
      }

      public LogarithmicRetainablePool(int minCapacity, int maxCapacity, int maxBucketSize) {
         this(minCapacity, maxCapacity, maxBucketSize, -1L, -1L);
      }

      public LogarithmicRetainablePool(int minCapacity, int maxCapacity, int maxBucketSize, long maxHeapMemory, long maxDirectMemory) {
         super(minCapacity, -1, maxCapacity, maxBucketSize, (c) -> 32 - Integer.numberOfLeadingZeros(c - 1), (i) -> 1 << i, maxHeapMemory, maxDirectMemory);
      }
   }
}
