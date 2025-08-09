package org.apache.parquet.column.values.bitpacking;

import java.nio.ByteBuffer;

public abstract class BytePackerForLong {
   private final int bitWidth;

   BytePackerForLong(int bitWidth) {
      this.bitWidth = bitWidth;
   }

   public final int getBitWidth() {
      return this.bitWidth;
   }

   public abstract void pack8Values(long[] var1, int var2, byte[] var3, int var4);

   public abstract void pack32Values(long[] var1, int var2, byte[] var3, int var4);

   public abstract void unpack8Values(ByteBuffer var1, int var2, long[] var3, int var4);

   public abstract void unpack32Values(ByteBuffer var1, int var2, long[] var3, int var4);

   public abstract void unpack8Values(byte[] var1, int var2, long[] var3, int var4);

   public abstract void unpack32Values(byte[] var1, int var2, long[] var3, int var4);
}
