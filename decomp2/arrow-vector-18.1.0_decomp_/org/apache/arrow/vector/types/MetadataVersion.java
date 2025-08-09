package org.apache.arrow.vector.types;

public enum MetadataVersion {
   V1((short)0),
   V2((short)1),
   V3((short)2),
   V4((short)3),
   V5((short)4);

   public static final MetadataVersion DEFAULT = V5;
   private static final MetadataVersion[] valuesByFlatbufId = new MetadataVersion[values().length];
   private final short flatbufID;

   private MetadataVersion(short flatbufID) {
      this.flatbufID = flatbufID;
   }

   public short toFlatbufID() {
      return this.flatbufID;
   }

   public static MetadataVersion fromFlatbufID(short id) {
      return valuesByFlatbufId[id];
   }

   // $FF: synthetic method
   private static MetadataVersion[] $values() {
      return new MetadataVersion[]{V1, V2, V3, V4, V5};
   }

   static {
      for(MetadataVersion v : values()) {
         valuesByFlatbufId[v.flatbufID] = v;
      }

   }
}
