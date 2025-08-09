package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.memory.WritableMemory;

final class DirectArrayOfDoublesUnionR extends DirectArrayOfDoublesUnion {
   DirectArrayOfDoublesUnionR(ArrayOfDoublesQuickSelectSketch gadget, WritableMemory mem) {
      super(gadget, mem);
   }

   public void union(ArrayOfDoublesSketch tupleSketch) {
      throw new SketchesReadOnlyException();
   }

   public void reset() {
      throw new SketchesReadOnlyException();
   }
}
