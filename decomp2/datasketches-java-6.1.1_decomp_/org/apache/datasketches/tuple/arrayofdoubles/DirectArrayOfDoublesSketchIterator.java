package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.memory.Memory;

final class DirectArrayOfDoublesSketchIterator implements ArrayOfDoublesSketchIterator {
   private Memory mem_;
   private int offset_;
   private int numEntries_;
   private int numValues_;
   private int i_;
   private static final int SIZE_OF_KEY_BYTES = 8;
   private static final int SIZE_OF_VALUE_BYTES = 8;

   DirectArrayOfDoublesSketchIterator(Memory mem, int offset, int numEntries, int numValues) {
      this.mem_ = mem;
      this.offset_ = offset;
      this.numEntries_ = numEntries;
      this.numValues_ = numValues;
      this.i_ = -1;
   }

   public boolean next() {
      ++this.i_;

      while(this.i_ < this.numEntries_) {
         if (this.mem_.getLong((long)this.offset_ + 8L * (long)this.i_) != 0L) {
            return true;
         }

         ++this.i_;
      }

      return false;
   }

   public long getKey() {
      return this.mem_.getLong((long)this.offset_ + 8L * (long)this.i_);
   }

   public double[] getValues() {
      if (this.numValues_ == 1) {
         return new double[]{this.mem_.getDouble((long)this.offset_ + 8L * (long)this.numEntries_ + 8L * (long)this.i_)};
      } else {
         double[] array = new double[this.numValues_];
         this.mem_.getDoubleArray((long)this.offset_ + 8L * (long)this.numEntries_ + 8L * (long)this.i_ * (long)this.numValues_, array, 0, this.numValues_);
         return array;
      }
   }
}
