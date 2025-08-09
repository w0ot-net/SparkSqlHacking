package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.memory.WritableMemory;

final class DirectDoublesSketchAccessor extends DoublesSketchAccessor {
   DirectDoublesSketchAccessor(DoublesSketch ds, boolean forceSize, int level) {
      super(ds, forceSize, level);

      assert ds.hasMemory();

   }

   DoublesSketchAccessor copyAndSetLevel(int level) {
      return new DirectDoublesSketchAccessor(this.ds_, this.forceSize_, level);
   }

   double get(int index) {
      assert index >= 0 && index < this.numItems_;

      assert this.n_ == this.ds_.getN();

      int idxOffset = this.offset_ + (index << 3);
      return this.ds_.getMemory().getDouble((long)idxOffset);
   }

   double set(int index, double quantile) {
      assert index >= 0 && index < this.numItems_;

      assert this.n_ == this.ds_.getN();

      assert !this.ds_.isCompact();

      int idxOffset = this.offset_ + (index << 3);
      WritableMemory mem = this.ds_.getMemory();
      double oldVal = mem.getDouble((long)idxOffset);
      mem.putDouble((long)idxOffset, quantile);
      return oldVal;
   }

   double[] getArray(int fromIdx, int numItems) {
      double[] dstArray = new double[numItems];
      int offsetBytes = this.offset_ + (fromIdx << 3);
      this.ds_.getMemory().getDoubleArray((long)offsetBytes, dstArray, 0, numItems);
      return dstArray;
   }

   void putArray(double[] srcArray, int srcIndex, int dstIndex, int numItems) {
      assert !this.ds_.isCompact();

      int offsetBytes = this.offset_ + (dstIndex << 3);
      this.ds_.getMemory().putDoubleArray((long)offsetBytes, srcArray, srcIndex, numItems);
   }

   void sort() {
      assert this.currLvl_ == -1;

      double[] tmpBuffer = new double[this.numItems_];
      WritableMemory mem = this.ds_.getMemory();
      mem.getDoubleArray((long)this.offset_, tmpBuffer, 0, this.numItems_);
      Arrays.sort(tmpBuffer, 0, this.numItems_);
      mem.putDoubleArray((long)this.offset_, tmpBuffer, 0, this.numItems_);
   }
}
