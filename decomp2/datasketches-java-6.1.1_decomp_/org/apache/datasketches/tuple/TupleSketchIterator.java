package org.apache.datasketches.tuple;

public class TupleSketchIterator {
   private final long[] hashArrTbl_;
   private final Summary[] summaryArrTbl_;
   private int i_;

   TupleSketchIterator(long[] hashes, Summary[] summaries) {
      this.hashArrTbl_ = hashes;
      this.summaryArrTbl_ = summaries;
      this.i_ = -1;
   }

   public boolean next() {
      if (this.hashArrTbl_ == null) {
         return false;
      } else {
         ++this.i_;

         while(this.i_ < this.hashArrTbl_.length) {
            if (this.hashArrTbl_[this.i_] > 0L) {
               return true;
            }

            ++this.i_;
         }

         return false;
      }
   }

   public long getHash() {
      return this.hashArrTbl_[this.i_];
   }

   public Summary getSummary() {
      return this.summaryArrTbl_[this.i_];
   }
}
