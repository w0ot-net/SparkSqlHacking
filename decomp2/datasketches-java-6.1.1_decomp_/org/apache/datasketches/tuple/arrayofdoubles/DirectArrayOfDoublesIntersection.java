package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.memory.WritableMemory;

final class DirectArrayOfDoublesIntersection extends ArrayOfDoublesIntersection {
   private WritableMemory mem_;

   DirectArrayOfDoublesIntersection(int numValues, long seed, WritableMemory dstMem) {
      super(numValues, seed);
      this.mem_ = dstMem;
   }

   protected ArrayOfDoublesQuickSelectSketch createSketch(int nomEntries, int numValues, long seed) {
      return new DirectArrayOfDoublesQuickSelectSketch(nomEntries, 0, 1.0F, numValues, seed, this.mem_);
   }
}
