package org.apache.datasketches.hllmap;

class CouponsIterator {
   private final int offset_;
   private final int maxEntries_;
   private final short[] couponsArr_;
   private int index_;

   CouponsIterator(short[] couponsArr, int offset, int maxEntries) {
      this.offset_ = offset;
      this.maxEntries_ = maxEntries;
      this.couponsArr_ = couponsArr;
      this.index_ = -1;
   }

   boolean next() {
      ++this.index_;

      while(this.index_ < this.maxEntries_) {
         if (this.couponsArr_[this.offset_ + this.index_] != 0) {
            return true;
         }

         ++this.index_;
      }

      return false;
   }

   short getValue() {
      return this.couponsArr_[this.offset_ + this.index_];
   }
}
