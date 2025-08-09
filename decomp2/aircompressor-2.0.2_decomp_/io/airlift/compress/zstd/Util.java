package io.airlift.compress.zstd;

import io.airlift.compress.MalformedInputException;

final class Util {
   private Util() {
   }

   public static int highestBit(int value) {
      return 31 - Integer.numberOfLeadingZeros(value);
   }

   public static boolean isPowerOf2(int value) {
      return (value & value - 1) == 0;
   }

   public static int mask(int bits) {
      return (1 << bits) - 1;
   }

   public static void verify(boolean condition, long offset, String reason) {
      if (!condition) {
         throw new MalformedInputException(offset, reason);
      }
   }

   public static void checkArgument(boolean condition, String reason) {
      if (!condition) {
         throw new IllegalArgumentException(reason);
      }
   }

   static void checkPositionIndexes(int start, int end, int size) {
      if (start < 0 || end < start || end > size) {
         throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
      }
   }

   private static String badPositionIndexes(int start, int end, int size) {
      if (start >= 0 && start <= size) {
         return end >= 0 && end <= size ? String.format("end index (%s) must not be less than start index (%s)", end, start) : badPositionIndex(end, size, "end index");
      } else {
         return badPositionIndex(start, size, "start index");
      }
   }

   private static String badPositionIndex(int index, int size, String desc) {
      if (index < 0) {
         return String.format("%s (%s) must not be negative", desc, index);
      } else if (size < 0) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return String.format("%s (%s) must not be greater than size (%s)", desc, index, size);
      }
   }

   public static void checkState(boolean condition, String reason) {
      if (!condition) {
         throw new IllegalStateException(reason);
      }
   }

   public static MalformedInputException fail(long offset, String reason) {
      throw new MalformedInputException(offset, reason);
   }

   public static int cycleLog(int hashLog, CompressionParameters.Strategy strategy) {
      int cycleLog = hashLog;
      if (strategy == CompressionParameters.Strategy.BTLAZY2 || strategy == CompressionParameters.Strategy.BTOPT || strategy == CompressionParameters.Strategy.BTULTRA) {
         cycleLog = hashLog - 1;
      }

      return cycleLog;
   }

   public static int get24BitLittleEndian(Object inputBase, long inputAddress) {
      return UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress) & '\uffff' | (UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + 2L) & 255) << 16;
   }

   public static void put24BitLittleEndian(Object outputBase, long outputAddress, int value) {
      UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress, (short)value);
      UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress + 2L, (byte)(value >>> 16));
   }

   public static int minTableLog(int inputSize, int maxSymbolValue) {
      if (inputSize <= 1) {
         throw new IllegalArgumentException("Not supported. RLE should be used instead");
      } else {
         int minBitsSrc = highestBit(inputSize - 1) + 1;
         int minBitsSymbols = highestBit(maxSymbolValue) + 2;
         return Math.min(minBitsSrc, minBitsSymbols);
      }
   }
}
