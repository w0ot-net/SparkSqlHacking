package org.rocksdb;

public enum CompactionStyle {
   LEVEL((byte)0),
   UNIVERSAL((byte)1),
   FIFO((byte)2),
   NONE((byte)3);

   private final byte value;

   private CompactionStyle(byte var3) {
      this.value = var3;
   }

   public byte getValue() {
      return this.value;
   }

   static CompactionStyle fromValue(byte var0) throws IllegalArgumentException {
      for(CompactionStyle var4 : values()) {
         if (var4.value == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Unknown value for CompactionStyle: " + var0);
   }
}
