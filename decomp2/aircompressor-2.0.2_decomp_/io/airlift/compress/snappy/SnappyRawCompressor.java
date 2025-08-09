package io.airlift.compress.snappy;

import java.util.Arrays;

public final class SnappyRawCompressor {
   private static final int BLOCK_LOG = 16;
   private static final int BLOCK_SIZE = 65536;
   private static final int INPUT_MARGIN_BYTES = 15;
   private static final int MAX_HASH_TABLE_BITS = 14;
   public static final int MAX_HASH_TABLE_SIZE = 16384;
   private static final int HIGH_BIT_MASK = 128;

   private SnappyRawCompressor() {
   }

   public static int maxCompressedLength(int sourceLength) {
      return 32 + sourceLength + sourceLength / 6;
   }

   public static int compress(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit, final short[] table) {
      int maxCompressedLength = maxCompressedLength((int)(inputLimit - inputAddress));
      if (outputLimit - outputAddress < (long)maxCompressedLength) {
         throw new IllegalArgumentException("Output buffer must be at least " + maxCompressedLength + " bytes");
      } else {
         long output = writeUncompressedLength(outputBase, outputAddress, (int)(inputLimit - inputAddress));

         for(long blockAddress = inputAddress; blockAddress < inputLimit; blockAddress += 65536L) {
            long blockLimit = Math.min(inputLimit, blockAddress + 65536L);
            long input = blockAddress;

            assert blockLimit - blockAddress <= 65536L;

            int blockHashTableSize = getHashTableSize((int)(blockLimit - blockAddress));
            Arrays.fill(table, 0, blockHashTableSize, (short)0);
            int shift = 32 - log2Floor(blockHashTableSize);

            assert (blockHashTableSize & blockHashTableSize - 1) == 0 : "table must be power of two";

            assert -1 >>> shift == blockHashTableSize - 1;

            long nextEmitAddress = blockAddress;

            label100:
            for(long fastInputLimit = blockLimit - 15L; input <= fastInputLimit; nextEmitAddress = input) {
               assert nextEmitAddress <= input;

               int skip = 32;
               long candidateIndex = 0L;
               ++input;

               while(input + (long)(skip >>> 5) <= fastInputLimit) {
                  int currentInt = UnsafeUtil.UNSAFE.getInt(inputBase, input);
                  int hash = hashBytes(currentInt, shift);
                  candidateIndex = blockAddress + (long)(table[hash] & '\uffff');

                  assert candidateIndex >= 0L;

                  assert candidateIndex < input;

                  table[hash] = (short)((int)(input - blockAddress));
                  if (currentInt == UnsafeUtil.UNSAFE.getInt(inputBase, candidateIndex)) {
                     break;
                  }

                  input += (long)(skip++ >>> 5);
               }

               if (input + (long)(skip >>> 5) > fastInputLimit) {
                  break;
               }

               assert nextEmitAddress + 16L <= blockLimit;

               int literalLength = (int)(input - nextEmitAddress);
               output = emitLiteralLength(outputBase, output, literalLength);
               output = fastCopy(inputBase, nextEmitAddress, outputBase, output, literalLength);

               while($assertionsDisabled || blockLimit >= input + 4L) {
                  int matched = count(inputBase, input + 4L, candidateIndex + 4L, blockLimit);
                  matched += 4;
                  output = emitCopy(outputBase, output, input, candidateIndex, matched);
                  input += (long)matched;
                  if (input >= fastInputLimit) {
                     continue label100;
                  }

                  long longValue = UnsafeUtil.UNSAFE.getLong(inputBase, input - 1L);
                  int prevInt = (int)longValue;
                  int inputBytes = (int)(longValue >>> 8);
                  int prevHash = hashBytes(prevInt, shift);
                  table[prevHash] = (short)((int)(input - blockAddress - 1L));
                  int curHash = hashBytes(inputBytes, shift);
                  candidateIndex = blockAddress + (long)(table[curHash] & '\uffff');
                  table[curHash] = (short)((int)(input - blockAddress));
                  if (inputBytes != UnsafeUtil.UNSAFE.getInt(inputBase, candidateIndex)) {
                     continue label100;
                  }
               }

               throw new AssertionError();
            }

            if (nextEmitAddress < blockLimit) {
               int literalLength = (int)(blockLimit - nextEmitAddress);
               output = emitLiteralLength(outputBase, output, literalLength);
               UnsafeUtil.UNSAFE.copyMemory(inputBase, nextEmitAddress, outputBase, output, (long)literalLength);
               output += (long)literalLength;
            }
         }

         return (int)(output - outputAddress);
      }
   }

   private static int count(Object inputBase, final long start, long matchStart, long matchLimit) {
      long current;
      for(current = start; current < matchLimit - 7L; matchStart += 8L) {
         long diff = UnsafeUtil.UNSAFE.getLong(inputBase, matchStart) ^ UnsafeUtil.UNSAFE.getLong(inputBase, current);
         if (diff != 0L) {
            current += (long)(Long.numberOfTrailingZeros(diff) >> 3);
            return (int)(current - start);
         }

         current += 8L;
      }

      if (current < matchLimit - 3L && UnsafeUtil.UNSAFE.getInt(inputBase, matchStart) == UnsafeUtil.UNSAFE.getInt(inputBase, current)) {
         current += 4L;
         matchStart += 4L;
      }

      if (current < matchLimit - 1L && UnsafeUtil.UNSAFE.getShort(inputBase, matchStart) == UnsafeUtil.UNSAFE.getShort(inputBase, current)) {
         current += 2L;
         matchStart += 2L;
      }

      if (current < matchLimit && UnsafeUtil.UNSAFE.getByte(inputBase, matchStart) == UnsafeUtil.UNSAFE.getByte(inputBase, current)) {
         ++current;
      }

      return (int)(current - start);
   }

   private static long emitLiteralLength(Object outputBase, long output, int literalLength) {
      int n = literalLength - 1;
      if (n < 60) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)(n << 2));
      } else {
         int bytes;
         if (n < 256) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-16);
            bytes = 1;
         } else if (n < 65536) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-12);
            bytes = 2;
         } else if (n < 16777216) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-8);
            bytes = 3;
         } else {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-4);
            bytes = 4;
         }

         UnsafeUtil.UNSAFE.putInt(outputBase, output, n);
         output += (long)bytes;
      }

      return output;
   }

   private static long fastCopy(final Object inputBase, long input, final Object outputBase, long output, final int literalLength) {
      long outputLimit = output + (long)literalLength;

      do {
         UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(inputBase, input));
         input += 8L;
         output += 8L;
      } while(output < outputLimit);

      return outputLimit;
   }

   private static long emitCopy(Object outputBase, long output, long input, long matchIndex, int matchLength) {
      long offset;
      for(offset = input - matchIndex; matchLength >= 68; matchLength -= 64) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-2);
         UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)((int)offset));
         output += 2L;
      }

      if (matchLength > 64) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-18);
         UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)((int)offset));
         output += 2L;
         matchLength -= 60;
      }

      if (matchLength < 12 && offset < 2048L) {
         int lenMinus4 = matchLength - 4;
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)((int)((long)(1 + (lenMinus4 << 2)) + (offset >>> 8 << 5))));
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)((int)offset));
      } else {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)(2 + (matchLength - 1 << 2)));
         UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)((int)offset));
         output += 2L;
      }

      return output;
   }

   private static int getHashTableSize(int inputSize) {
      int target = Integer.highestOneBit(inputSize - 1) << 1;
      return Math.max(Math.min(target, 16384), 256);
   }

   private static int hashBytes(int value, int shift) {
      return value * 506832829 >>> shift;
   }

   private static int log2Floor(int n) {
      return n == 0 ? -1 : 31 ^ Integer.numberOfLeadingZeros(n);
   }

   private static long writeUncompressedLength(Object outputBase, long outputAddress, int uncompressedLength) {
      if (uncompressedLength < 128 && uncompressedLength >= 0) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)uncompressedLength);
      } else if (uncompressedLength < 16384 && uncompressedLength > 0) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 7));
      } else if (uncompressedLength < 2097152 && uncompressedLength > 0) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 7 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 14));
      } else if (uncompressedLength < 268435456 && uncompressedLength > 0) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 7 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 14 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 21));
      } else {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 7 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 14 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 21 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress++, (byte)(uncompressedLength >>> 28));
      }

      return outputAddress;
   }
}
