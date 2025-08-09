package org.apache.datasketches.quantiles;

import org.apache.datasketches.memory.WritableMemory;

public class DoublesUnionBuilder {
   private int bMaxK = 128;

   public DoublesUnionBuilder setMaxK(int maxK) {
      ClassicUtil.checkK(maxK);
      this.bMaxK = maxK;
      return this;
   }

   public int getMaxK() {
      return this.bMaxK;
   }

   public DoublesUnion build() {
      return DoublesUnionImpl.heapInstance(this.bMaxK);
   }

   public DoublesUnion build(WritableMemory dstMem) {
      return DoublesUnionImpl.directInstance(this.bMaxK, dstMem);
   }
}
