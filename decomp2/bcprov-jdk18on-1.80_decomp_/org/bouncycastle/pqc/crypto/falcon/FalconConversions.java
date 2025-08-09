package org.bouncycastle.pqc.crypto.falcon;

class FalconConversions {
   byte[] int_to_bytes(int var1) {
      byte[] var2 = new byte[]{(byte)(var1 >>> 0), (byte)(var1 >>> 8), (byte)(var1 >>> 16), (byte)(var1 >>> 24)};
      return var2;
   }

   int bytes_to_int(byte[] var1, int var2) {
      int var3 = 0;
      var3 = this.toUnsignedInt(var1[var2 + 0]) << 0 | this.toUnsignedInt(var1[var2 + 1]) << 8 | this.toUnsignedInt(var1[var2 + 2]) << 16 | this.toUnsignedInt(var1[var2 + 3]) << 24;
      return var3;
   }

   int[] bytes_to_int_array(byte[] var1, int var2, int var3) {
      int[] var4 = new int[var3];

      for(int var5 = 0; var5 < var3; ++var5) {
         var4[var5] = this.bytes_to_int(var1, var2 + 4 * var5);
      }

      return var4;
   }

   byte[] long_to_bytes(long var1) {
      byte[] var3 = new byte[]{(byte)((int)(var1 >>> 0)), (byte)((int)(var1 >>> 8)), (byte)((int)(var1 >>> 16)), (byte)((int)(var1 >>> 24)), (byte)((int)(var1 >>> 32)), (byte)((int)(var1 >>> 40)), (byte)((int)(var1 >>> 48)), (byte)((int)(var1 >>> 56))};
      return var3;
   }

   long bytes_to_long(byte[] var1, int var2) {
      long var3 = 0L;
      var3 = this.toUnsignedLong(var1[var2 + 0]) << 0 | this.toUnsignedLong(var1[var2 + 1]) << 8 | this.toUnsignedLong(var1[var2 + 2]) << 16 | this.toUnsignedLong(var1[var2 + 3]) << 24 | this.toUnsignedLong(var1[var2 + 4]) << 32 | this.toUnsignedLong(var1[var2 + 5]) << 40 | this.toUnsignedLong(var1[var2 + 6]) << 48 | this.toUnsignedLong(var1[var2 + 7]) << 56;
      return var3;
   }

   private int toUnsignedInt(byte var1) {
      return var1 & 255;
   }

   private long toUnsignedLong(byte var1) {
      return (long)var1 & 255L;
   }
}
