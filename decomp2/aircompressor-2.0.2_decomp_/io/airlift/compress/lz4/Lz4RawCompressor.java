package io.airlift.compress.lz4;

import java.util.Arrays;

public final class Lz4RawCompressor {
   private static final int MAX_INPUT_SIZE = 2113929216;
   private static final int HASH_LOG = 12;
   private static final int MIN_TABLE_SIZE = 16;
   public static final int MAX_TABLE_SIZE = 4096;
   private static final int COPY_LENGTH = 8;
   private static final int MATCH_FIND_LIMIT = 12;
   private static final int MIN_LENGTH = 13;
   private static final int ML_BITS = 4;
   private static final int ML_MASK = 15;
   private static final int RUN_BITS = 4;
   private static final int RUN_MASK = 15;
   private static final int MAX_DISTANCE = 65535;
   private static final int SKIP_TRIGGER = 6;

   private Lz4RawCompressor() {
   }

   private static int hash(long value, int mask) {
      return (int)(value * 889523592379L >>> 28 & (long)mask);
   }

   public static int maxCompressedLength(int sourceLength) {
      return sourceLength + sourceLength / 255 + 16;
   }

   public static int compress(final Object inputBase, final long inputAddress, final int inputLength, final Object outputBase, final long outputAddress, final long maxOutputLength, final int[] table) {
      int tableSize = computeTableSize(inputLength);
      Arrays.fill(table, 0, tableSize, 0);
      int mask = tableSize - 1;
      if (inputLength > 2113929216) {
         throw new IllegalArgumentException("Max input length exceeded");
      } else if (maxOutputLength < (long)maxCompressedLength(inputLength)) {
         throw new IllegalArgumentException("Max output length must be larger than " + maxCompressedLength(inputLength));
      } else {
         long output = outputAddress;
         long inputLimit = inputAddress + (long)inputLength;
         long matchFindLimit = inputLimit - 12L;
         long matchLimit = inputLimit - 5L;
         if (inputLength < 13) {
            output = emitLastLiteral(outputBase, outputAddress, inputBase, inputAddress, inputLimit - inputAddress);
            return (int)(output - outputAddress);
         } else {
            long anchor = inputAddress;
            table[hash(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), mask)] = (int)(inputAddress - inputAddress);
            long input = inputAddress + 1L;
            int nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, input), mask);
            boolean done = false;

            do {
               long nextInputIndex = input;
               int findMatchAttempts = 64;
               int step = 1;

               long matchIndex;
               do {
                  int hash = nextHash;
                  input = nextInputIndex;
                  nextInputIndex += (long)step;
                  step = findMatchAttempts++ >>> 6;
                  if (nextInputIndex > matchFindLimit) {
                     return (int)(emitLastLiteral(outputBase, output, inputBase, anchor, inputLimit - anchor) - outputAddress);
                  }

                  matchIndex = inputAddress + (long)table[nextHash];
                  nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, nextInputIndex), mask);
                  table[hash] = (int)(input - inputAddress);
               } while(UnsafeUtil.UNSAFE.getInt(inputBase, matchIndex) != UnsafeUtil.UNSAFE.getInt(inputBase, input) || matchIndex + 65535L < input);

               while(input > anchor && matchIndex > inputAddress && UnsafeUtil.UNSAFE.getByte(inputBase, input - 1L) == UnsafeUtil.UNSAFE.getByte(inputBase, matchIndex - 1L)) {
                  --input;
                  --matchIndex;
               }

               int literalLength = (int)(input - anchor);
               long tokenAddress = output;
               output = emitLiteral(inputBase, outputBase, anchor, literalLength, output);

               while(true) {
                  int matchLength = count(inputBase, input + 4L, matchLimit, matchIndex + 4L);
                  output = emitMatch(outputBase, output, tokenAddress, (short)((int)(input - matchIndex)), (long)matchLength);
                  input += (long)(matchLength + 4);
                  anchor = input;
                  if (input > matchFindLimit) {
                     done = true;
                     break;
                  }

                  long position = input - 2L;
                  table[hash(UnsafeUtil.UNSAFE.getLong(inputBase, position), mask)] = (int)(position - inputAddress);
                  int hash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, input), mask);
                  matchIndex = inputAddress + (long)table[hash];
                  table[hash] = (int)(input - inputAddress);
                  if (matchIndex + 65535L < input || UnsafeUtil.UNSAFE.getInt(inputBase, matchIndex) != UnsafeUtil.UNSAFE.getInt(inputBase, input)) {
                     ++input;
                     nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, input), mask);
                     break;
                  }

                  tokenAddress = output++;
                  UnsafeUtil.UNSAFE.putByte(outputBase, tokenAddress, (byte)0);
               }
            } while(!done);

            output = emitLastLiteral(outputBase, output, inputBase, anchor, inputLimit - anchor);
            return (int)(output - outputAddress);
         }
      }
   }

   private static long emitLiteral(Object inputBase, Object outputBase, long input, int literalLength, long output) {
      output = encodeRunLength(outputBase, output, (long)literalLength);
      long outputLimit = output + (long)literalLength;

      do {
         UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(inputBase, input));
         input += 8L;
         output += 8L;
      } while(output < outputLimit);

      return outputLimit;
   }

   private static long emitMatch(Object outputBase, long output, long tokenAddress, short offset, long matchLength) {
      UnsafeUtil.UNSAFE.putShort(outputBase, output, offset);
      output += 2L;
      if (matchLength >= 15L) {
         UnsafeUtil.UNSAFE.putByte(outputBase, tokenAddress, (byte)(UnsafeUtil.UNSAFE.getByte(outputBase, tokenAddress) | 15));

         long remaining;
         for(remaining = matchLength - 15L; remaining >= 510L; remaining -= 510L) {
            UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)-1);
            output += 2L;
         }

         if (remaining >= 255L) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)-1);
            remaining -= 255L;
         }

         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)((int)remaining));
      } else {
         UnsafeUtil.UNSAFE.putByte(outputBase, tokenAddress, (byte)((int)((long)UnsafeUtil.UNSAFE.getByte(outputBase, tokenAddress) | matchLength)));
      }

      return output;
   }

   static int count(Object inputBase, final long inputAddress, final long inputLimit, final long matchAddress) {
      long input = inputAddress;
      long match = matchAddress;
      int remaining = (int)(inputLimit - inputAddress);

      int count;
      for(count = 0; count < remaining - 7; match += 8L) {
         long diff = UnsafeUtil.UNSAFE.getLong(inputBase, match) ^ UnsafeUtil.UNSAFE.getLong(inputBase, input);
         if (diff != 0L) {
            return count + (Long.numberOfTrailingZeros(diff) >> 3);
         }

         count += 8;
         input += 8L;
      }

      while(count < remaining && UnsafeUtil.UNSAFE.getByte(inputBase, match) == UnsafeUtil.UNSAFE.getByte(inputBase, input)) {
         ++count;
         ++match;
         ++input;
      }

      return count;
   }

   private static long emitLastLiteral(final Object outputBase, final long outputAddress, final Object inputBase, final long inputAddress, final long length) {
      long output = encodeRunLength(outputBase, outputAddress, length);
      UnsafeUtil.UNSAFE.copyMemory(inputBase, inputAddress, outputBase, output, length);
      return output + length;
   }

   private static long encodeRunLength(final Object base, long output, final long length) {
      if (length >= 15L) {
         UnsafeUtil.UNSAFE.putByte(base, output++, (byte)-16);

         long remaining;
         for(remaining = length - 15L; remaining >= 255L; remaining -= 255L) {
            UnsafeUtil.UNSAFE.putByte(base, output++, (byte)-1);
         }

         UnsafeUtil.UNSAFE.putByte(base, output++, (byte)((int)remaining));
      } else {
         UnsafeUtil.UNSAFE.putByte(base, output++, (byte)((int)(length << 4)));
      }

      return output;
   }

   private static int computeTableSize(int inputSize) {
      int target = Integer.highestOneBit(inputSize - 1) << 1;
      return Math.max(Math.min(target, 4096), 16);
   }
}
