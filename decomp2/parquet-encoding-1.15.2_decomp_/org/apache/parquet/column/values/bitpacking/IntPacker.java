package org.apache.parquet.column.values.bitpacking;

public abstract class IntPacker {
   private final int bitWidth;

   IntPacker(int bitWidth) {
      this.bitWidth = bitWidth;
   }

   public final int getBitWidth() {
      return this.bitWidth;
   }

   public abstract void pack32Values(int[] var1, int var2, int[] var3, int var4);

   public abstract void unpack32Values(int[] var1, int var2, int[] var3, int var4);
}
