package org.apache.arrow.memory.rounding;

import org.apache.arrow.memory.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRoundingPolicy implements RoundingPolicy {
   private static final Logger logger = LoggerFactory.getLogger(DefaultRoundingPolicy.class);
   public final long chunkSize;
   private static final long MIN_PAGE_SIZE = 4096L;
   private static final long MAX_CHUNK_SIZE = 1073741824L;
   private static final long DEFAULT_CHUNK_SIZE;
   public static final DefaultRoundingPolicy DEFAULT_ROUNDING_POLICY;

   private static long validateAndCalculatePageShifts(long pageSize) {
      if (pageSize < 4096L) {
         throw new IllegalArgumentException("pageSize: " + pageSize + " (expected: 4096)");
      } else if ((pageSize & pageSize - 1L) != 0L) {
         throw new IllegalArgumentException("pageSize: " + pageSize + " (expected: power of 2)");
      } else {
         return 63L - (long)Long.numberOfLeadingZeros(pageSize);
      }
   }

   private static long validateAndCalculateChunkSize(long pageSize, int maxOrder) {
      if (maxOrder > 14) {
         throw new IllegalArgumentException("maxOrder: " + maxOrder + " (expected: 0-14)");
      } else {
         long chunkSize = pageSize;

         for(long i = (long)maxOrder; i > 0L; --i) {
            if (chunkSize > 536870912L) {
               throw new IllegalArgumentException(String.format("pageSize (%d) << maxOrder (%d) must not exceed %d", pageSize, maxOrder, 1073741824L));
            }

            chunkSize <<= 1;
         }

         return chunkSize;
      }
   }

   private DefaultRoundingPolicy(long chunkSize) {
      this.chunkSize = chunkSize;
   }

   public long getRoundedSize(long requestSize) {
      return requestSize < this.chunkSize ? CommonUtil.nextPowerOfTwo(requestSize) : requestSize;
   }

   static {
      long defaultPageSize = Long.getLong("org.apache.memory.allocator.pageSize", 8192L);

      try {
         validateAndCalculatePageShifts(defaultPageSize);
      } catch (Throwable var5) {
         defaultPageSize = 8192L;
      }

      int defaultMaxOrder = Integer.getInteger("org.apache.memory.allocator.maxOrder", 11);

      try {
         validateAndCalculateChunkSize(defaultPageSize, defaultMaxOrder);
      } catch (Throwable var4) {
         defaultMaxOrder = 11;
      }

      DEFAULT_CHUNK_SIZE = validateAndCalculateChunkSize(defaultPageSize, defaultMaxOrder);
      if (logger.isDebugEnabled()) {
         logger.debug("-Dorg.apache.memory.allocator.pageSize: {}", defaultPageSize);
         logger.debug("-Dorg.apache.memory.allocator.maxOrder: {}", defaultMaxOrder);
      }

      DEFAULT_ROUNDING_POLICY = new DefaultRoundingPolicy(DEFAULT_CHUNK_SIZE);
   }
}
