package org.apache.datasketches.quantiles;

import java.util.Objects;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.quantilescommon.QuantilesGenericSketchIterator;

public final class ItemsSketchIterator implements QuantilesGenericSketchIterator {
   private Object[] combinedBuffer;
   private long bitPattern;
   private int level;
   private long weight;
   private int index;
   private int offset;
   private int num;
   private int k;

   ItemsSketchIterator(ItemsSketch sketch, long bitPattern) {
      Objects.requireNonNull(sketch, "sketch must not be null");
      this.combinedBuffer = sketch.combinedBuffer_;
      this.num = sketch.getBaseBufferCount();
      this.k = sketch.getK();
      this.bitPattern = bitPattern;
      this.level = -1;
      this.weight = 1L;
      this.index = -1;
      this.offset = 0;
   }

   public Object getQuantile() {
      if (this.index < 0) {
         throw new SketchesStateException("index < 0; getQuantile() was called before next()");
      } else {
         return this.combinedBuffer[this.offset + this.index];
      }
   }

   public long getWeight() {
      return this.weight;
   }

   public boolean next() {
      ++this.index;
      if (this.index < this.num) {
         return true;
      } else {
         do {
            ++this.level;
            if (this.level > 0) {
               this.bitPattern >>>= 1;
            }

            if (this.bitPattern == 0L) {
               return false;
            }

            this.weight *= 2L;
         } while((this.bitPattern & 1L) == 0L);

         this.index = 0;
         this.offset = (2 + this.level) * this.k;
         this.num = this.k;
         return true;
      }
   }
}
