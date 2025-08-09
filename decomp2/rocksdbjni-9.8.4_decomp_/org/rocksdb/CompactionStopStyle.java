package org.rocksdb;

public enum CompactionStopStyle {
   CompactionStopStyleSimilarSize((byte)0),
   CompactionStopStyleTotalSize((byte)1);

   private final byte value;

   private CompactionStopStyle(byte var3) {
      this.value = var3;
   }

   public byte getValue() {
      return this.value;
   }

   public static CompactionStopStyle getCompactionStopStyle(byte var0) {
      for(CompactionStopStyle var4 : values()) {
         if (var4.getValue() == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Illegal value provided for CompactionStopStyle.");
   }
}
