package org.apache.datasketches.quantiles;

import java.util.Objects;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.quantilescommon.QuantilesDoublesSketchIterator;

public final class DoublesSketchIterator implements QuantilesDoublesSketchIterator {
   private DoublesSketchAccessor sketchAccessor;
   private long bitPattern;
   private int level;
   private long weight;
   private int index;

   DoublesSketchIterator(DoublesSketch sketch, long bitPattern) {
      Objects.requireNonNull(sketch, "sketch must not be null");
      this.sketchAccessor = DoublesSketchAccessor.wrap(sketch);
      this.bitPattern = bitPattern;
      this.level = -1;
      this.weight = 1L;
      this.index = -1;
   }

   public double getQuantile() {
      if (this.index < 0) {
         throw new SketchesStateException("index < 0; getQuantile() was called before next()");
      } else {
         return this.sketchAccessor.get(this.index);
      }
   }

   public long getWeight() {
      return this.weight;
   }

   public boolean next() {
      ++this.index;
      if (this.index < this.sketchAccessor.numItems()) {
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
         this.sketchAccessor.setLevel(this.level);
         return true;
      }
   }
}
