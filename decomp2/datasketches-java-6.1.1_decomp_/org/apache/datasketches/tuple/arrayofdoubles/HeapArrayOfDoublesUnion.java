package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.SerializerDeserializer;

final class HeapArrayOfDoublesUnion extends ArrayOfDoublesUnion {
   HeapArrayOfDoublesUnion(int nomEntries, int numValues, long seed) {
      super(new HeapArrayOfDoublesQuickSelectSketch(nomEntries, 3, 1.0F, numValues, seed));
   }

   HeapArrayOfDoublesUnion(ArrayOfDoublesQuickSelectSketch gadget, long unionThetaLong) {
      super(gadget);
      this.unionThetaLong_ = unionThetaLong;
   }

   static ArrayOfDoublesUnion heapifyUnion(Memory mem, long seed) {
      byte version = mem.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
         SerializerDeserializer.validateType(mem.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesUnion);
         Memory sketchMem = mem.region(16L, mem.getCapacity() - 16L);
         ArrayOfDoublesQuickSelectSketch sketch = new HeapArrayOfDoublesQuickSelectSketch(sketchMem, seed);
         return new HeapArrayOfDoublesUnion(sketch, mem.getLong(8L));
      }
   }
}
