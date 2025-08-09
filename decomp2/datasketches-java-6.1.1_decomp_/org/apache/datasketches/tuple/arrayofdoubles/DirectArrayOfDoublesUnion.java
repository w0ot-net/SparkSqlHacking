package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.SerializerDeserializer;

class DirectArrayOfDoublesUnion extends ArrayOfDoublesUnion {
   final WritableMemory mem_;

   DirectArrayOfDoublesUnion(int nomEntries, int numValues, long seed, WritableMemory dstMem) {
      super(new DirectArrayOfDoublesQuickSelectSketch(nomEntries, 3, 1.0F, numValues, seed, dstMem.writableRegion(16L, dstMem.getCapacity() - 16L)));
      this.mem_ = dstMem;
      this.mem_.putByte(0L, (byte)1);
      this.mem_.putByte(1L, (byte)1);
      this.mem_.putByte(2L, (byte)Family.TUPLE.getID());
      this.mem_.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesUnion.ordinal());
      this.mem_.putLong(8L, this.gadget_.getThetaLong());
   }

   DirectArrayOfDoublesUnion(ArrayOfDoublesQuickSelectSketch gadget, WritableMemory mem) {
      super(gadget);
      this.mem_ = mem;
      this.unionThetaLong_ = mem.getLong(8L);
   }

   void setUnionThetaLong(long thetaLong) {
      super.setUnionThetaLong(thetaLong);
      this.mem_.putLong(8L, thetaLong);
   }

   static ArrayOfDoublesUnion wrapUnion(WritableMemory mem, long seed, boolean isWritable) {
      byte version = mem.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
         SerializerDeserializer.validateType(mem.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesUnion);
         if (isWritable) {
            WritableMemory sketchMem = mem.writableRegion(16L, mem.getCapacity() - 16L);
            return new DirectArrayOfDoublesUnion(new DirectArrayOfDoublesQuickSelectSketch(sketchMem, seed), mem);
         } else {
            Memory sketchMem = mem.region(16L, mem.getCapacity() - 16L);
            return new DirectArrayOfDoublesUnionR(new DirectArrayOfDoublesQuickSelectSketchR(sketchMem, seed), mem);
         }
      }
   }
}
