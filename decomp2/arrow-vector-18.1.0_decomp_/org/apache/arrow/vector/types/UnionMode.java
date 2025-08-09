package org.apache.arrow.vector.types;

public enum UnionMode {
   Sparse((short)0),
   Dense((short)1);

   private static final UnionMode[] valuesByFlatbufId = new UnionMode[values().length];
   private final short flatbufID;

   private UnionMode(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short getFlatbufID() {
      return this.flatbufID;
   }

   public static UnionMode fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static UnionMode[] $values() {
      return new UnionMode[]{Sparse, Dense};
   }

   static {
      for(UnionMode v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
