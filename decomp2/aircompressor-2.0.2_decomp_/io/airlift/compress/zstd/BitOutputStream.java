package io.airlift.compress.zstd;

class BitOutputStream {
   private static final long[] BIT_MASK = new long[]{0L, 1L, 3L, 7L, 15L, 31L, 63L, 127L, 255L, 511L, 1023L, 2047L, 4095L, 8191L, 16383L, 32767L, 65535L, 131071L, 262143L, 524287L, 1048575L, 2097151L, 4194303L, 8388607L, 16777215L, 33554431L, 67108863L, 134217727L, 268435455L, 536870911L, 1073741823L, 2147483647L};
   private final Object outputBase;
   private final long outputAddress;
   private final long outputLimit;
   private long container;
   private int bitCount;
   private long currentAddress;

   public BitOutputStream(Object outputBase, long outputAddress, int outputSize) {
      Util.checkArgument(outputSize >= 8, "Output buffer too small");
      this.outputBase = outputBase;
      this.outputAddress = outputAddress;
      this.outputLimit = this.outputAddress + (long)outputSize - 8L;
      this.currentAddress = this.outputAddress;
   }

   public void addBits(int value, int bits) {
      this.container |= ((long)value & BIT_MASK[bits]) << this.bitCount;
      this.bitCount += bits;
   }

   public void addBitsFast(int value, int bits) {
      this.container |= (long)value << this.bitCount;
      this.bitCount += bits;
   }

   public void flush() {
      int bytes = this.bitCount >>> 3;
      UnsafeUtil.UNSAFE.putLong(this.outputBase, this.currentAddress, this.container);
      this.currentAddress += (long)bytes;
      if (this.currentAddress > this.outputLimit) {
         this.currentAddress = this.outputLimit;
      }

      this.bitCount &= 7;
      this.container >>>= bytes * 8;
   }

   public int close() {
      this.addBitsFast(1, 1);
      this.flush();
      return this.currentAddress >= this.outputLimit ? 0 : (int)(this.currentAddress - this.outputAddress + (long)(this.bitCount > 0 ? 1 : 0));
   }
}
