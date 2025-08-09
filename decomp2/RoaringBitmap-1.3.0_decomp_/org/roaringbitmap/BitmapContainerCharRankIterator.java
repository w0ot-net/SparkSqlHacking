package org.roaringbitmap;

final class BitmapContainerCharRankIterator extends BitmapContainerCharIterator implements PeekableCharRankIterator {
   private int nextRank = 1;

   BitmapContainerCharRankIterator(long[] p) {
      super(p);
   }

   public int peekNextRank() {
      return this.nextRank;
   }

   public char next() {
      ++this.nextRank;
      return super.next();
   }

   public void advanceIfNeeded(char minval) {
      if (this.hasNext()) {
         if (minval >= this.x * 64) {
            if (minval >= (this.x + 1) * 64) {
               int nextX = minval / 64;
               this.nextRank += Long.bitCount(this.w);
               ++this.x;

               while(this.x < nextX) {
                  this.w = this.bitmap[this.x];
                  this.nextRank += Long.bitCount(this.w);
                  ++this.x;
               }

               this.w = this.bitmap[nextX];
            }

            this.nextRank += Long.bitCount(this.w);
            this.w &= -1L << (minval & 63);

            for(this.nextRank -= Long.bitCount(this.w); this.w == 0L; this.w = this.bitmap[this.x]) {
               ++this.x;
               if (!this.hasNext()) {
                  return;
               }
            }
         }

      }
   }

   public PeekableCharRankIterator clone() {
      return (PeekableCharRankIterator)super.clone();
   }
}
