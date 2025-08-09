package org.apache.arrow.vector.types;

public enum FloatingPointPrecision {
   HALF((short)0),
   SINGLE((short)1),
   DOUBLE((short)2);

   private static final FloatingPointPrecision[] valuesByFlatbufId = new FloatingPointPrecision[values().length];
   private final short flatbufID;

   private FloatingPointPrecision(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short getFlatbufID() {
      return this.flatbufID;
   }

   public static FloatingPointPrecision fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static FloatingPointPrecision[] $values() {
      return new FloatingPointPrecision[]{HALF, SINGLE, DOUBLE};
   }

   static {
      for(FloatingPointPrecision v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
