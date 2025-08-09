package org.apache.datasketches.kll;

import org.apache.datasketches.quantilescommon.QuantilesLongsSketchIterator;

public final class KllLongsSketchIterator extends KllSketchIterator implements QuantilesLongsSketchIterator {
   private final long[] quantiles;

   KllLongsSketchIterator(long[] quantiles, int[] levelsArr, int numLevels) {
      super(levelsArr, numLevels);
      this.quantiles = quantiles;
   }

   public long getQuantile() {
      return this.quantiles[this.index];
   }
}
