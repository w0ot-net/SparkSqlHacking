package org.roaringbitmap;

public class IntConsumerRelativeRangeAdapter implements RelativeRangeConsumer {
   final int start;
   final IntConsumer absolutePositionConsumer;

   public IntConsumerRelativeRangeAdapter(int start, IntConsumer lc) {
      this.start = start;
      this.absolutePositionConsumer = lc;
   }

   public void acceptPresent(int relativePos) {
      this.absolutePositionConsumer.accept(this.start + relativePos);
   }

   public void acceptAbsent(int relativePos) {
   }

   public void acceptAllPresent(int relativeFrom, int relativeTo) {
      for(int pos = this.start + relativeFrom; pos < this.start + relativeTo; ++pos) {
         this.absolutePositionConsumer.accept(pos);
      }

   }

   public void acceptAllAbsent(int relativeFrom, int relativeTo) {
   }
}
