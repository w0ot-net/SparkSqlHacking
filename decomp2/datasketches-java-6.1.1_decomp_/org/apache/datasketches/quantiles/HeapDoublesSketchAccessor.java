package org.apache.datasketches.quantiles;

import java.util.Arrays;

final class HeapDoublesSketchAccessor extends DoublesSketchAccessor {
   HeapDoublesSketchAccessor(DoublesSketch ds, boolean forceSize, int level) {
      super(ds, forceSize, level);

      assert !ds.hasMemory();

   }

   DoublesSketchAccessor copyAndSetLevel(int level) {
      return new HeapDoublesSketchAccessor(this.ds_, this.forceSize_, level);
   }

   double get(int index) {
      assert index >= 0 && index < this.numItems_;

      assert this.n_ == this.ds_.getN();

      return this.ds_.getCombinedBuffer()[this.offset_ + index];
   }

   double set(int index, double quantile) {
      assert index >= 0 && index < this.numItems_;

      assert this.n_ == this.ds_.getN();

      int idxOffset = this.offset_ + index;
      double oldVal = this.ds_.getCombinedBuffer()[idxOffset];
      this.ds_.getCombinedBuffer()[idxOffset] = quantile;
      return oldVal;
   }

   double[] getArray(int fromIdx, int numItems) {
      int stIdx = this.offset_ + fromIdx;
      return Arrays.copyOfRange(this.ds_.getCombinedBuffer(), stIdx, stIdx + numItems);
   }

   void putArray(double[] srcArray, int srcIndex, int dstIndex, int numItems) {
      int tgtIdx = this.offset_ + dstIndex;
      System.arraycopy(srcArray, srcIndex, this.ds_.getCombinedBuffer(), tgtIdx, numItems);
   }

   void sort() {
      assert this.currLvl_ == -1;

      if (!this.ds_.isCompact()) {
         Arrays.sort(this.ds_.getCombinedBuffer(), this.offset_, this.offset_ + this.numItems_);
      }

   }
}
