package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DirectArrayOfDoublesQuickSelectSketchR extends DirectArrayOfDoublesQuickSelectSketch {
   DirectArrayOfDoublesQuickSelectSketchR(Memory mem, long seed) {
      super((WritableMemory)mem, seed);
   }

   void insertOrIgnore(long key, double[] values) {
      throw new SketchesReadOnlyException();
   }

   public void trim() {
      throw new SketchesReadOnlyException();
   }
}
