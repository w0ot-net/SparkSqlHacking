package org.roaringbitmap.longlong;

import org.roaringbitmap.RelativeRangeConsumer;

public class LongConsumerRelativeRangeAdapter implements RelativeRangeConsumer {
   final long start;
   final LongConsumer absolutePositionConsumer;

   public LongConsumerRelativeRangeAdapter(long start, LongConsumer lc) {
      this.start = start;
      this.absolutePositionConsumer = lc;
   }

   public void acceptPresent(int relativePos) {
      this.absolutePositionConsumer.accept(this.start + (long)relativePos);
   }

   public void acceptAbsent(int relativePos) {
   }

   public void acceptAllPresent(int relativeFrom, int relativeTo) {
      for(long pos = this.start + (long)relativeFrom; pos < this.start + (long)relativeTo; ++pos) {
         this.absolutePositionConsumer.accept(pos);
      }

   }

   public void acceptAllAbsent(int relativeFrom, int relativeTo) {
   }
}
