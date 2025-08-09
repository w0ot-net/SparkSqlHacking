package org.apache.datasketches.kll;

import org.apache.datasketches.quantilescommon.QuantilesSketchIterator;

public class KllSketchIterator implements QuantilesSketchIterator {
   protected final int[] levelsArr;
   protected final int numLevels;
   protected int level;
   protected int index;
   protected long weight;
   protected boolean isInitialized_;

   KllSketchIterator(int[] levelsArr, int numLevels) {
      this.levelsArr = levelsArr;
      this.numLevels = numLevels;
      this.isInitialized_ = false;
   }

   public long getWeight() {
      return this.weight;
   }

   public boolean next() {
      if (!this.isInitialized_) {
         this.level = 0;
         this.index = this.levelsArr[this.level];
         this.weight = 1L;
         this.isInitialized_ = true;
      } else {
         ++this.index;
      }

      if (this.index < this.levelsArr[this.level + 1]) {
         return true;
      } else {
         do {
            ++this.level;
            if (this.level == this.numLevels) {
               return false;
            }

            this.weight *= 2L;
         } while(this.levelsArr[this.level] == this.levelsArr[this.level + 1]);

         this.index = this.levelsArr[this.level];
         return true;
      }
   }
}
