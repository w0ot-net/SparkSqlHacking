package org.apache.parquet.column.values.bitpacking;

import java.nio.ByteBuffer;

public abstract class BytePacker {
   private final int bitWidth;
   protected int unpackCount;

   BytePacker(int bitWidth) {
      this.bitWidth = bitWidth;
   }

   public final int getBitWidth() {
      return this.bitWidth;
   }

   public int getUnpackCount() {
      throw new RuntimeException("getUnpackCount must be implemented by subclass!");
   }

   public abstract void pack8Values(int[] var1, int var2, byte[] var3, int var4);

   public abstract void pack32Values(int[] var1, int var2, byte[] var3, int var4);

   public abstract void unpack8Values(ByteBuffer var1, int var2, int[] var3, int var4);

   /** @deprecated */
   @Deprecated
   public void unpack8Values(byte[] input, int inPos, int[] output, int outPos) {
      this.unpack8Values(ByteBuffer.wrap(input), inPos, output, outPos);
   }

   public abstract void unpack32Values(ByteBuffer var1, int var2, int[] var3, int var4);

   /** @deprecated */
   @Deprecated
   public void unpack32Values(byte[] input, int inPos, int[] output, int outPos) {
      this.unpack32Values(ByteBuffer.wrap(input), inPos, output, outPos);
   }

   public void unpackValuesUsingVector(byte[] input, int inPos, int[] output, int outPos) {
      throw new RuntimeException("unpackValuesUsingVector must be implemented by subclass!");
   }

   public void unpackValuesUsingVector(ByteBuffer input, int inPos, int[] output, int outPos) {
      throw new RuntimeException("unpackValuesUsingVector must be implemented by subclass!");
   }
}
