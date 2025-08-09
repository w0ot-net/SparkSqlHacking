package org.apache.arrow.vector.types;

public enum IntervalUnit {
   YEAR_MONTH((short)0),
   DAY_TIME((short)1),
   MONTH_DAY_NANO((short)2);

   private static final IntervalUnit[] valuesByFlatbufId = new IntervalUnit[values().length];
   private final short flatbufID;

   private IntervalUnit(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short getFlatbufID() {
      return this.flatbufID;
   }

   public static IntervalUnit fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static IntervalUnit[] $values() {
      return new IntervalUnit[]{YEAR_MONTH, DAY_TIME, MONTH_DAY_NANO};
   }

   static {
      for(IntervalUnit v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
