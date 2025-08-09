package org.apache.datasketches.quantiles;

import java.util.Arrays;

final class DoublesArrayAccessor extends DoublesBufferAccessor {
   private int numItems_;
   private double[] buffer_;

   private DoublesArrayAccessor(double[] buffer) {
      this.numItems_ = buffer.length;
      this.buffer_ = buffer;
   }

   static DoublesArrayAccessor wrap(double[] buffer) {
      return new DoublesArrayAccessor(buffer);
   }

   static DoublesArrayAccessor initialize(int numItems) {
      return new DoublesArrayAccessor(new double[numItems]);
   }

   double get(int index) {
      assert index >= 0 && index < this.numItems_;

      return this.buffer_[index];
   }

   double set(int index, double quantile) {
      assert index >= 0 && index < this.numItems_;

      double retVal = this.buffer_[index];
      this.buffer_[index] = quantile;
      return retVal;
   }

   int numItems() {
      return this.numItems_;
   }

   double[] getArray(int fromIdx, int numItems) {
      return Arrays.copyOfRange(this.buffer_, fromIdx, fromIdx + numItems);
   }

   void putArray(double[] srcArray, int srcIndex, int dstIndex, int numItems) {
      System.arraycopy(srcArray, srcIndex, this.buffer_, dstIndex, numItems);
   }
}
