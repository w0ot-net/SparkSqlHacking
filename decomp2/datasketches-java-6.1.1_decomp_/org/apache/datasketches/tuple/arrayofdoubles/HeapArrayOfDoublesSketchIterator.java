package org.apache.datasketches.tuple.arrayofdoubles;

import java.util.Arrays;

final class HeapArrayOfDoublesSketchIterator implements ArrayOfDoublesSketchIterator {
   private long[] keys_;
   private double[] values_;
   private int numValues_;
   private int i_;

   HeapArrayOfDoublesSketchIterator(long[] keys, double[] values, int numValues) {
      this.keys_ = keys;
      this.values_ = values;
      this.numValues_ = numValues;
      this.i_ = -1;
   }

   public boolean next() {
      if (this.keys_ == null) {
         return false;
      } else {
         ++this.i_;

         while(this.i_ < this.keys_.length) {
            if (this.keys_[this.i_] != 0L) {
               return true;
            }

            ++this.i_;
         }

         return false;
      }
   }

   public long getKey() {
      return this.keys_[this.i_];
   }

   public double[] getValues() {
      return this.numValues_ == 1 ? new double[]{this.values_[this.i_]} : Arrays.copyOfRange(this.values_, this.i_ * this.numValues_, (this.i_ + 1) * this.numValues_);
   }
}
