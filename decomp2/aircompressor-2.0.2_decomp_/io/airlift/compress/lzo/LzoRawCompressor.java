package io.airlift.compress.lzo;

import java.util.Arrays;

public final class LzoRawCompressor {
   public static final int LAST_LITERAL_SIZE = 5;
   public static final int MIN_MATCH = 4;
   private static final int MAX_INPUT_SIZE = 2113929216;
   private static final int HASH_LOG = 12;
   private static final int MIN_TABLE_SIZE = 16;
   public static final int MAX_TABLE_SIZE = 4096;
   private static final int COPY_LENGTH = 8;
   private static final int MATCH_FIND_LIMIT = 12;
   private static final int MIN_LENGTH = 13;
   private static final int ML_BITS = 4;
   private static final int RUN_BITS = 4;
   private static final int RUN_MASK = 15;
   private static final int MAX_DISTANCE = 49151;
   private static final int SKIP_TRIGGER = 6;

   private LzoRawCompressor() {
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
      } else if (inputLength == 0) {
         return 0;
      } else {
         long output = outputAddress;
         long inputLimit = inputAddress + (long)inputLength;
         long matchFindLimit = inputLimit - 12L;
         long matchLimit = inputLimit - 5L;
         if (inputLength < 13) {
            output = emitLastLiteral(true, outputBase, outputAddress, inputBase, inputAddress, inputLimit - inputAddress);
            return (int)(output - outputAddress);
         } else {
            long anchor = inputAddress;
            table[hash(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), mask)] = (int)(inputAddress - inputAddress);
            long input = inputAddress + 1L;
            int nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, input), mask);
            boolean done = false;
            boolean firstLiteral = true;

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
                     output = emitLastLiteral(firstLiteral, outputBase, output, inputBase, anchor, inputLimit - anchor);
                     return (int)(output - outputAddress);
                  }

                  matchIndex = inputAddress + (long)table[nextHash];
                  nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, nextInputIndex), mask);
                  table[hash] = (int)(input - inputAddress);
               } while(UnsafeUtil.UNSAFE.getInt(inputBase, matchIndex) != UnsafeUtil.UNSAFE.getInt(inputBase, input) || matchIndex + 49151L < input);

               while(input > anchor && matchIndex > inputAddress && UnsafeUtil.UNSAFE.getByte(inputBase, input - 1L) == UnsafeUtil.UNSAFE.getByte(inputBase, matchIndex - 1L)) {
                  --input;
                  --matchIndex;
               }

               int literalLength = (int)(input - anchor);
               output = emitLiteral(firstLiteral, inputBase, anchor, outputBase, output, literalLength);
               firstLiteral = false;

               while(true) {
                  int offset = (int)(input - matchIndex);
                  long var39 = input + 4L;
                  int matchLength = count(inputBase, var39, matchIndex + 4L, matchLimit);
                  input = var39 + (long)matchLength;
                  output = emitCopy(outputBase, output, offset, matchLength + 4);
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
                  if (matchIndex + 49151L < input || UnsafeUtil.UNSAFE.getInt(inputBase, matchIndex) != UnsafeUtil.UNSAFE.getInt(inputBase, input)) {
                     ++input;
                     nextHash = hash(UnsafeUtil.UNSAFE.getLong(inputBase, input), mask);
                     break;
                  }
               }
            } while(!done);

            output = emitLastLiteral(false, outputBase, output, inputBase, anchor, inputLimit - anchor);
            return (int)(output - outputAddress);
         }
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

   private static long emitLastLiteral(boolean firstLiteral, final Object outputBase, long output, final Object inputBase, final long inputAddress, final long literalLength) {
      output = encodeLiteralLength(firstLiteral, outputBase, output, literalLength);
      UnsafeUtil.UNSAFE.copyMemory(inputBase, inputAddress, outputBase, output, literalLength);
      output += literalLength;
      UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)17);
      UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)0);
      output += 2L;
      return output;
   }

   private static long emitLiteral(boolean firstLiteral, Object inputBase, long input, Object outputBase, long output, int literalLength) {
      output = encodeLiteralLength(firstLiteral, outputBase, output, (long)literalLength);
      long outputLimit = output + (long)literalLength;

      do {
         UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(inputBase, input));
         input += 8L;
         output += 8L;
      } while(output < outputLimit);

      return outputLimit;
   }

   private static long encodeLiteralLength(boolean firstLiteral, final Object outBase, long output, long length) {
      if (firstLiteral && length < 238L) {
         UnsafeUtil.UNSAFE.putByte(outBase, output++, (byte)((int)(length + 17L)));
      } else if (length < 4L) {
         UnsafeUtil.UNSAFE.putByte(outBase, output - 2L, (byte)((int)((long)UnsafeUtil.UNSAFE.getByte(outBase, output - 2L) | length)));
      } else {
         length -= 3L;
         if (length > 15L) {
            UnsafeUtil.UNSAFE.putByte(outBase, output++, (byte)0);

            long remaining;
            for(remaining = length - 15L; remaining > 255L; remaining -= 255L) {
               UnsafeUtil.UNSAFE.putByte(outBase, output++, (byte)0);
            }

            UnsafeUtil.UNSAFE.putByte(outBase, output++, (byte)((int)remaining));
         } else {
            UnsafeUtil.UNSAFE.putByte(outBase, output++, (byte)((int)length));
         }
      }

      return output;
   }

   private static long emitCopy(Object outputBase, long output, int matchOffset, int matchLength) {
      if (matchOffset <= 49151 && matchOffset >= 1) {
         if (matchLength <= 8 && matchOffset <= 2048) {
            --matchLength;
            --matchOffset;
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)(matchLength << 5 | (matchOffset & 7) << 2));
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)(matchOffset >>> 3));
            return output;
         } else {
            matchLength -= 2;
            if (matchOffset >= 32768) {
               output = encodeMatchLength(outputBase, output, matchLength, 7, 24);
            } else if (matchOffset > 16384) {
               output = encodeMatchLength(outputBase, output, matchLength, 7, 16);
            } else {
               output = encodeMatchLength(outputBase, output, matchLength, 31, 32);
               --matchOffset;
            }

            output = encodeOffset(outputBase, output, matchOffset);
            return output;
         }
      } else {
         throw new IllegalArgumentException("Unsupported copy offset: " + matchOffset);
      }
   }

   private static long encodeOffset(final Object outputBase, final long outputAddress, final int offset) {
      UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress, (short)(offset << 2));
      return outputAddress + 2L;
   }

   private static long encodeMatchLength(Object outputBase, long output, int matchLength, int baseMatchLength, int command) {
      if (matchLength <= baseMatchLength) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)(command | matchLength));
      } else {
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)command);

         long remaining;
         for(remaining = (long)(matchLength - baseMatchLength); remaining > 510L; remaining -= 510L) {
            UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)0);
            output += 2L;
         }

         if (remaining > 255L) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)0);
            remaining -= 255L;
         }

         UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)((int)remaining));
      }

      return output;
   }

   private static int computeTableSize(int inputSize) {
      int target = Integer.highestOneBit(inputSize - 1) << 1;
      return Math.max(Math.min(target, 4096), 16);
   }
}
