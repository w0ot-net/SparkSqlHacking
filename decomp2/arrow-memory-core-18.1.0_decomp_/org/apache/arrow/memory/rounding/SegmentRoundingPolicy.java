package org.apache.arrow.memory.rounding;

import com.google.errorprone.annotations.InlineMe;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;

public class SegmentRoundingPolicy implements RoundingPolicy {
   public static final long MIN_SEGMENT_SIZE = 1024L;
   private long segmentSize;

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   @InlineMe(
      replacement = "this((long) segmentSize)"
   )
   public SegmentRoundingPolicy(int segmentSize) {
      this((long)segmentSize);
   }

   public SegmentRoundingPolicy(long segmentSize) {
      Preconditions.checkArgument(segmentSize >= 1024L, "The segment size cannot be smaller than %s", 1024L);
      Preconditions.checkArgument((segmentSize & segmentSize - 1L) == 0L, "The segment size must be a power of 2");
      this.segmentSize = segmentSize;
   }

   public long getRoundedSize(long requestSize) {
      return (requestSize + (this.segmentSize - 1L)) / this.segmentSize * this.segmentSize;
   }

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   public int getSegmentSize() {
      return LargeMemoryUtil.checkedCastToInt(this.segmentSize);
   }

   public long getSegmentSizeAsLong() {
      return this.segmentSize;
   }
}
