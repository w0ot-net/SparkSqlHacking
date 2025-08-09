package io.airlift.compress.zstd;

class BitInputStream {
   private BitInputStream() {
   }

   public static boolean isEndOfStream(long startAddress, long currentAddress, int bitsConsumed) {
      return startAddress == currentAddress && bitsConsumed == 64;
   }

   static long readTail(Object inputBase, long inputAddress, int inputSize) {
      long bits = (long)(UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255);
      switch (inputSize) {
         case 7:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 6L) & 255L) << 48;
         case 6:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 5L) & 255L) << 40;
         case 5:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 4L) & 255L) << 32;
         case 4:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 3L) & 255L) << 24;
         case 3:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 2L) & 255L) << 16;
         case 2:
            bits |= ((long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 1L) & 255L) << 8;
         default:
            return bits;
      }
   }

   public static long peekBits(int bitsConsumed, long bitContainer, int numberOfBits) {
      return bitContainer << bitsConsumed >>> 1 >>> 63 - numberOfBits;
   }

   public static long peekBitsFast(int bitsConsumed, long bitContainer, int numberOfBits) {
      return bitContainer << bitsConsumed >>> 64 - numberOfBits;
   }

   static class Initializer {
      private final Object inputBase;
      private final long startAddress;
      private final long endAddress;
      private long bits;
      private long currentAddress;
      private int bitsConsumed;

      public Initializer(Object inputBase, long startAddress, long endAddress) {
         this.inputBase = inputBase;
         this.startAddress = startAddress;
         this.endAddress = endAddress;
      }

      public long getBits() {
         return this.bits;
      }

      public long getCurrentAddress() {
         return this.currentAddress;
      }

      public int getBitsConsumed() {
         return this.bitsConsumed;
      }

      public void initialize() {
         Util.verify(this.endAddress - this.startAddress >= 1L, this.startAddress, "Bitstream is empty");
         int lastByte = UnsafeUtil.UNSAFE.getByte(this.inputBase, this.endAddress - 1L) & 255;
         Util.verify(lastByte != 0, this.endAddress, "Bitstream end mark not present");
         this.bitsConsumed = 8 - Util.highestBit(lastByte);
         int inputSize = (int)(this.endAddress - this.startAddress);
         if (inputSize >= 8) {
            this.currentAddress = this.endAddress - 8L;
            this.bits = UnsafeUtil.UNSAFE.getLong(this.inputBase, this.currentAddress);
         } else {
            this.currentAddress = this.startAddress;
            this.bits = BitInputStream.readTail(this.inputBase, this.startAddress, inputSize);
            this.bitsConsumed += (8 - inputSize) * 8;
         }

      }
   }

   static final class Loader {
      private final Object inputBase;
      private final long startAddress;
      private long bits;
      private long currentAddress;
      private int bitsConsumed;
      private boolean overflow;

      public Loader(Object inputBase, long startAddress, long currentAddress, long bits, int bitsConsumed) {
         this.inputBase = inputBase;
         this.startAddress = startAddress;
         this.bits = bits;
         this.currentAddress = currentAddress;
         this.bitsConsumed = bitsConsumed;
      }

      public long getBits() {
         return this.bits;
      }

      public long getCurrentAddress() {
         return this.currentAddress;
      }

      public int getBitsConsumed() {
         return this.bitsConsumed;
      }

      public boolean isOverflow() {
         return this.overflow;
      }

      public boolean load() {
         if (this.bitsConsumed > 64) {
            this.overflow = true;
            return true;
         } else if (this.currentAddress == this.startAddress) {
            return true;
         } else {
            int bytes = this.bitsConsumed >>> 3;
            if (this.currentAddress >= this.startAddress + 8L) {
               if (bytes > 0) {
                  this.currentAddress -= (long)bytes;
                  this.bits = UnsafeUtil.UNSAFE.getLong(this.inputBase, this.currentAddress);
               }

               this.bitsConsumed &= 7;
            } else {
               if (this.currentAddress - (long)bytes < this.startAddress) {
                  bytes = (int)(this.currentAddress - this.startAddress);
                  this.currentAddress = this.startAddress;
                  this.bitsConsumed -= bytes * 8;
                  this.bits = UnsafeUtil.UNSAFE.getLong(this.inputBase, this.startAddress);
                  return true;
               }

               this.currentAddress -= (long)bytes;
               this.bitsConsumed -= bytes * 8;
               this.bits = UnsafeUtil.UNSAFE.getLong(this.inputBase, this.currentAddress);
            }

            return false;
         }
      }
   }
}
