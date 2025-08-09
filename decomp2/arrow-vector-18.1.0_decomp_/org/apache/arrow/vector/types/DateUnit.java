package org.apache.arrow.vector.types;

public enum DateUnit {
   DAY((short)0),
   MILLISECOND((short)1);

   private static final DateUnit[] valuesByFlatbufId = new DateUnit[values().length];
   private final short flatbufID;

   private DateUnit(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short getFlatbufID() {
      return this.flatbufID;
   }

   public static DateUnit fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static DateUnit[] $values() {
      return new DateUnit[]{DAY, MILLISECOND};
   }

   static {
      for(DateUnit v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
