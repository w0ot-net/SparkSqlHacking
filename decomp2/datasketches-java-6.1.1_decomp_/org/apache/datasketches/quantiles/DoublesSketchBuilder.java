package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;

public class DoublesSketchBuilder {
   private int bK = 128;

   public DoublesSketchBuilder setK(int k) {
      ClassicUtil.checkK(k);
      this.bK = k;
      return this;
   }

   public int getK() {
      return this.bK;
   }

   public UpdateDoublesSketch build() {
      return HeapUpdateDoublesSketch.newInstance(this.bK);
   }

   public UpdateDoublesSketch build(WritableMemory dstMem) {
      return DirectUpdateDoublesSketch.newInstance(this.bK, dstMem);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("QuantileSketchBuilder configuration:").append(Util.LS);
      sb.append("K     : ").append('\t').append(this.bK).append(Util.LS);
      return sb.toString();
   }
}
