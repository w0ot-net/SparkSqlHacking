package org.apache.arrow.vector.types;

public enum TimeUnit {
   SECOND((short)0),
   MILLISECOND((short)1),
   MICROSECOND((short)2),
   NANOSECOND((short)3);

   private static final TimeUnit[] valuesByFlatbufId = new TimeUnit[values().length];
   private final short flatbufID;

   private TimeUnit(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short getFlatbufID() {
      return this.flatbufID;
   }

   public static TimeUnit fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static TimeUnit[] $values() {
      return new TimeUnit[]{SECOND, MILLISECOND, MICROSECOND, NANOSECOND};
   }

   static {
      for(TimeUnit v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
