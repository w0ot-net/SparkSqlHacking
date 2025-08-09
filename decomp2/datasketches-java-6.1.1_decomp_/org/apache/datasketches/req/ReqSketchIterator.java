package org.apache.datasketches.req;

import java.util.List;
import org.apache.datasketches.quantilescommon.QuantilesFloatsSketchIterator;

public final class ReqSketchIterator implements QuantilesFloatsSketchIterator {
   private List compactors;
   private int cIndex;
   private int bIndex;
   private int numRetainedItems;
   private FloatBuffer currentBuf;

   ReqSketchIterator(ReqSketch sketch) {
      this.compactors = sketch.getCompactors();
      this.numRetainedItems = sketch.getNumRetained();
      this.currentBuf = ((ReqCompactor)this.compactors.get(0)).getBuffer();
      this.cIndex = 0;
      this.bIndex = -1;
   }

   public float getQuantile() {
      return this.currentBuf.getItem(this.bIndex);
   }

   public long getWeight() {
      return (long)(1 << this.cIndex);
   }

   public boolean next() {
      if (this.numRetainedItems != 0 && (this.cIndex != this.compactors.size() - 1 || this.bIndex != this.currentBuf.getCount() - 1)) {
         if (this.bIndex == this.currentBuf.getCount() - 1) {
            ++this.cIndex;
            this.currentBuf = ((ReqCompactor)this.compactors.get(this.cIndex)).getBuffer();
            this.bIndex = 0;
         } else {
            ++this.bIndex;
         }

         return true;
      } else {
         return false;
      }
   }
}
