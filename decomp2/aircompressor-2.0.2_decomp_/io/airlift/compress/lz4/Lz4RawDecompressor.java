package io.airlift.compress.lz4;

import io.airlift.compress.MalformedInputException;

public final class Lz4RawDecompressor {
   private static final int[] DEC_32_TABLE = new int[]{4, 1, 2, 1, 4, 4, 4, 4};
   private static final int[] DEC_64_TABLE = new int[]{0, 0, 0, -1, 0, 1, 2, 3};
   private static final int OFFSET_SIZE = 2;
   private static final int TOKEN_SIZE = 1;

   private Lz4RawDecompressor() {
   }

   public static int decompress(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      long fastOutputLimit = outputLimit - 8L;
      long input = inputAddress;
      long output = outputAddress;
      if (inputAddress == inputLimit) {
         throw new MalformedInputException(0L, "input is empty");
      } else if (outputAddress == outputLimit) {
         return inputLimit - inputAddress == 1L && UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) == 0 ? 0 : -1;
      } else {
         while(true) {
            if (input < inputLimit) {
               int token = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
               int literalLength = token >>> 4;
               if (literalLength == 15) {
                  if (input >= inputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  int value;
                  do {
                     value = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
                     literalLength += value;
                  } while(value == 255 && input < inputLimit - 15L);
               }

               if (literalLength < 0) {
                  throw new MalformedInputException(input - inputAddress);
               }

               long literalEnd = input + (long)literalLength;
               long literalOutputLimit = output + (long)literalLength;
               if (literalOutputLimit <= fastOutputLimit - 4L && literalEnd <= inputLimit - 8L) {
                  int index = 0;

                  do {
                     UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(inputBase, input));
                     output += 8L;
                     input += 8L;
                     index += 8;
                  } while(index < literalLength);

                  int offset = UnsafeUtil.UNSAFE.getShort(inputBase, literalEnd) & '\uffff';
                  input = literalEnd + 2L;
                  long matchAddress = literalOutputLimit - (long)offset;
                  if (matchAddress < outputAddress) {
                     throw new MalformedInputException(input - inputAddress, "offset outside destination buffer");
                  }

                  int matchLength = token & 15;
                  int value;
                  if (matchLength == 15) {
                     do {
                        if (input > inputLimit - 5L) {
                           throw new MalformedInputException(input - inputAddress);
                        }

                        value = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
                        matchLength += value;
                     } while(value == 255);
                  }

                  matchLength += 4;
                  if (matchLength < 0) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  long matchOutputLimit = literalOutputLimit + (long)matchLength;
                  if (offset < 8) {
                     int increment32 = DEC_32_TABLE[offset];
                     int decrement64 = DEC_64_TABLE[offset];
                     UnsafeUtil.UNSAFE.putByte(outputBase, literalOutputLimit, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress));
                     UnsafeUtil.UNSAFE.putByte(outputBase, literalOutputLimit + 1L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 1L));
                     UnsafeUtil.UNSAFE.putByte(outputBase, literalOutputLimit + 2L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 2L));
                     UnsafeUtil.UNSAFE.putByte(outputBase, literalOutputLimit + 3L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 3L));
                     output = literalOutputLimit + 4L;
                     matchAddress += (long)increment32;
                     UnsafeUtil.UNSAFE.putInt(outputBase, output, UnsafeUtil.UNSAFE.getInt(outputBase, matchAddress));
                     output += 4L;
                     matchAddress -= (long)decrement64;
                  } else {
                     UnsafeUtil.UNSAFE.putLong(outputBase, literalOutputLimit, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                     matchAddress += 8L;
                     output = literalOutputLimit + 8L;
                  }

                  if (matchOutputLimit > fastOutputLimit - 4L) {
                     if (matchOutputLimit > outputLimit - 5L) {
                        throw new MalformedInputException(input - inputAddress, String.format("last %s bytes must be literals", 5));
                     }

                     while(output < fastOutputLimit) {
                        UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                        matchAddress += 8L;
                        output += 8L;
                     }

                     while(output < matchOutputLimit) {
                        UnsafeUtil.UNSAFE.putByte(outputBase, output++, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress++));
                     }
                  } else {
                     int i = 0;

                     do {
                        UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                        output += 8L;
                        matchAddress += 8L;
                        i += 8;
                     } while(i < matchLength - 8);
                  }

                  output = matchOutputLimit;
                  continue;
               }

               if (literalOutputLimit > outputLimit) {
                  throw new MalformedInputException(input - inputAddress, "attempt to write last literal outside of destination buffer");
               }

               if (literalEnd != inputLimit) {
                  throw new MalformedInputException(input - inputAddress, "all input must be consumed");
               }

               UnsafeUtil.UNSAFE.copyMemory(inputBase, input, outputBase, output, (long)literalLength);
               output += (long)literalLength;
            }

            return (int)(output - outputAddress);
         }
      }
   }
}
