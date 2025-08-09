package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class DoublesUnionImplR extends DoublesUnion {
   int maxK_;
   UpdateDoublesSketch gadget_ = null;

   DoublesUnionImplR(int maxK) {
      this.maxK_ = maxK;
   }

   static DoublesUnionImplR wrapInstance(Memory mem) {
      DirectUpdateDoublesSketchR sketch = DirectUpdateDoublesSketchR.wrapInstance(mem);
      int k = sketch.getK();
      DoublesUnionImplR union = new DoublesUnionImplR(k);
      union.maxK_ = k;
      union.gadget_ = sketch;
      return union;
   }

   public void union(DoublesSketch sketchIn) {
      throw new SketchesReadOnlyException("Call to update() on read-only Union");
   }

   public void union(Memory mem) {
      throw new SketchesReadOnlyException("Call to update() on read-only Union");
   }

   public void update(double dataItem) {
      throw new SketchesReadOnlyException("Call to update() on read-only Union");
   }

   public byte[] toByteArray() {
      return this.gadget_ == null ? DoublesSketch.builder().setK(this.maxK_).build().toByteArray() : this.gadget_.toByteArray();
   }

   public UpdateDoublesSketch getResult() {
      return this.gadget_ == null ? HeapUpdateDoublesSketch.newInstance(this.maxK_) : DoublesUtil.copyToHeap(this.gadget_);
   }

   public UpdateDoublesSketch getResult(WritableMemory dstMem) {
      long memCapBytes = dstMem.getCapacity();
      if (this.gadget_ == null) {
         if (memCapBytes < (long)DoublesSketch.getUpdatableStorageBytes(0, 0L)) {
            throw new SketchesArgumentException("Insufficient capacity for result: " + memCapBytes);
         } else {
            return DirectUpdateDoublesSketch.newInstance(this.maxK_, dstMem);
         }
      } else {
         this.gadget_.putMemory(dstMem, false);
         return DirectUpdateDoublesSketch.wrapInstance(dstMem);
      }
   }

   public UpdateDoublesSketch getResultAndReset() {
      throw new SketchesReadOnlyException("Call to getResultAndReset() on read-only Union");
   }

   public void reset() {
      throw new SketchesReadOnlyException("Call to reset() on read-only Union");
   }

   public boolean hasMemory() {
      return this.gadget_ != null && this.gadget_.hasMemory();
   }

   public boolean isDirect() {
      return this.gadget_ != null && this.gadget_.isDirect();
   }

   public boolean isEmpty() {
      return this.gadget_ == null || this.gadget_.isEmpty();
   }

   public int getMaxK() {
      return this.maxK_;
   }

   public int getEffectiveK() {
      return this.gadget_ != null ? this.gadget_.getK() : this.maxK_;
   }

   public String toString() {
      return this.toString(true, false);
   }

   public String toString(boolean sketchSummary, boolean dataDetail) {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      int maxK = this.getMaxK();
      String kStr = String.format("%,d", maxK);
      sb.append(Util.LS).append("### Quantiles ").append(thisSimpleName).append(Util.LS);
      sb.append("   maxK                         : ").append(kStr);
      if (this.gadget_ == null) {
         sb.append(HeapUpdateDoublesSketch.newInstance(this.maxK_).toString());
         return sb.toString();
      } else {
         sb.append(this.gadget_.toString(sketchSummary, dataDetail));
         return sb.toString();
      }
   }

   public boolean isSameResource(Memory that) {
      return this.gadget_ == null ? false : this.gadget_.isSameResource(that);
   }
}
