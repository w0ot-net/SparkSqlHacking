package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;

public abstract class CompactDoublesSketch extends DoublesSketch {
   CompactDoublesSketch(int k) {
      super(k);
   }

   public static CompactDoublesSketch heapify(Memory srcMem) {
      return HeapCompactDoublesSketch.heapifyInstance(srcMem);
   }

   boolean isCompact() {
      return true;
   }

   public boolean isReadOnly() {
      return false;
   }

   public void reset() {
      throw new SketchesStateException("Cannot reset a compact sketch, which is read-only.");
   }

   public void update(double quantile) {
      throw new SketchesStateException("Cannot update a compact sketch, which is read-only.");
   }
}
