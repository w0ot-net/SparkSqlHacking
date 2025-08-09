package io.airlift.compress.lzo;

import io.airlift.compress.MalformedInputException;

public final class LzoRawDecompressor {
   private static final int[] DEC_32_TABLE = new int[]{4, 1, 2, 1, 4, 4, 4, 4};
   private static final int[] DEC_64_TABLE = new int[]{0, 0, 0, -1, 0, 1, 2, 3};

   private LzoRawDecompressor() {
   }

   public static int decompress(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) throws MalformedInputException {
      if (inputAddress == inputLimit) {
         return 0;
      } else {
         long fastOutputLimit = outputLimit - 8L;
         long input = inputAddress;
         long output = outputAddress;

         label211:
         while(input < inputLimit) {
            boolean firstCommand = true;

            int literalLength;
            for(int lastLiteralLength = 0; input < inputLimit; lastLiteralLength = literalLength) {
               int command = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
               int matchOffset;
               int matchLength;
               if ((command & 240) == 0) {
                  if (lastLiteralLength != 0) {
                     if (lastLiteralLength <= 3) {
                        matchLength = 2;
                        if (input >= inputLimit) {
                           throw new MalformedInputException(input - inputAddress);
                        }

                        matchOffset = (command & 12) >>> 2;
                        matchOffset |= (UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) << 2;
                        literalLength = command & 3;
                     } else {
                        matchLength = 3;
                        if (input >= inputLimit) {
                           throw new MalformedInputException(input - inputAddress);
                        }

                        matchOffset = (command & 12) >>> 2;
                        matchOffset |= (UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) << 2;
                        matchOffset |= 2048;
                        literalLength = command & 3;
                     }
                  } else {
                     matchOffset = 0;
                     matchLength = 0;
                     literalLength = command & 15;
                     if (literalLength == 0) {
                        literalLength = 15;

                        int nextByte;
                        for(nextByte = 0; input < inputLimit && (nextByte = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) == 0; literalLength += 255) {
                        }

                        literalLength += nextByte;
                     }

                     literalLength += 3;
                  }
               } else if (firstCommand) {
                  matchLength = 0;
                  matchOffset = 0;
                  literalLength = command - 17;
               } else if ((command & 240) == 16) {
                  matchLength = command & 7;
                  if (matchLength == 0) {
                     matchLength = 7;

                     int nextByte;
                     for(nextByte = 0; input < inputLimit && (nextByte = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) == 0; matchLength += 255) {
                     }

                     matchLength += nextByte;
                  }

                  matchLength += 2;
                  if (input + 2L > inputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  int trailer = UnsafeUtil.UNSAFE.getShort(inputBase, input) & '\uffff';
                  input += 2L;
                  matchOffset = (command & 8) << 11;
                  matchOffset += trailer >> 2;
                  if (matchOffset == 0) {
                     continue label211;
                  }

                  matchOffset += 16383;
                  literalLength = trailer & 3;
               } else if ((command & 224) != 32) {
                  if ((command & 192) == 0) {
                     String binary = toBinary(command);
                     throw new MalformedInputException(input - 1L, "Invalid LZO command " + binary);
                  }

                  matchLength = (command & 224) >>> 5;
                  ++matchLength;
                  if (input >= inputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  matchOffset = (command & 28) >>> 2;
                  matchOffset |= (UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) << 3;
                  literalLength = command & 3;
               } else {
                  matchLength = command & 31;
                  if (matchLength == 0) {
                     matchLength = 31;

                     int nextByte;
                     for(nextByte = 0; input < inputLimit && (nextByte = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255) == 0; matchLength += 255) {
                     }

                     matchLength += nextByte;
                  }

                  matchLength += 2;
                  if (input + 2L > inputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  int trailer = UnsafeUtil.UNSAFE.getShort(inputBase, input) & '\uffff';
                  input += 2L;
                  matchOffset = trailer >>> 2;
                  literalLength = trailer & 3;
               }

               firstCommand = false;
               if (matchLength < 0) {
                  throw new MalformedInputException(input - inputAddress);
               }

               if (matchLength != 0) {
                  ++matchOffset;
                  long matchAddress = output - (long)matchOffset;
                  if (matchAddress < outputAddress || output + (long)matchLength > outputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  long matchOutputLimit = output + (long)matchLength;
                  if (output > fastOutputLimit) {
                     while(output < matchOutputLimit) {
                        UnsafeUtil.UNSAFE.putByte(outputBase, output++, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress++));
                     }
                  } else {
                     if (matchOffset < 8) {
                        int increment32 = DEC_32_TABLE[matchOffset];
                        int decrement64 = DEC_64_TABLE[matchOffset];
                        UnsafeUtil.UNSAFE.putByte(outputBase, output, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress));
                        UnsafeUtil.UNSAFE.putByte(outputBase, output + 1L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 1L));
                        UnsafeUtil.UNSAFE.putByte(outputBase, output + 2L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 2L));
                        UnsafeUtil.UNSAFE.putByte(outputBase, output + 3L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 3L));
                        output += 4L;
                        matchAddress += (long)increment32;
                        UnsafeUtil.UNSAFE.putInt(outputBase, output, UnsafeUtil.UNSAFE.getInt(outputBase, matchAddress));
                        output += 4L;
                        matchAddress -= (long)decrement64;
                     } else {
                        UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                        matchAddress += 8L;
                        output += 8L;
                     }

                     if (matchOutputLimit < fastOutputLimit) {
                        while(output < matchOutputLimit) {
                           UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                           matchAddress += 8L;
                           output += 8L;
                        }
                     } else {
                        if (matchOutputLimit > outputLimit) {
                           throw new MalformedInputException(input - inputAddress);
                        }

                        while(output < fastOutputLimit) {
                           UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                           matchAddress += 8L;
                           output += 8L;
                        }

                        while(output < matchOutputLimit) {
                           UnsafeUtil.UNSAFE.putByte(outputBase, output++, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress++));
                        }
                     }
                  }

                  output = matchOutputLimit;
               }

               if (literalLength < 0) {
                  throw new MalformedInputException(input - inputAddress);
               }

               long literalOutputLimit = output + (long)literalLength;
               if (literalOutputLimit <= fastOutputLimit && input + (long)literalLength <= inputLimit - 8L) {
                  do {
                     UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(inputBase, input));
                     input += 8L;
                     output += 8L;
                  } while(output < literalOutputLimit);

                  input -= output - literalOutputLimit;
                  output = literalOutputLimit;
               } else {
                  if (literalOutputLimit > outputLimit || input + (long)literalLength > inputLimit) {
                     throw new MalformedInputException(input - inputAddress);
                  }

                  UnsafeUtil.UNSAFE.copyMemory(inputBase, input, outputBase, output, (long)literalLength);
                  input += (long)literalLength;
                  output += (long)literalLength;
               }
            }

            throw new MalformedInputException(input - inputAddress);
         }

         return (int)(output - outputAddress);
      }
   }

   private static String toBinary(int command) {
      String binaryString = String.format("%8s", Integer.toBinaryString(command)).replace(' ', '0');
      return "0b" + binaryString.substring(0, 4) + "_" + binaryString.substring(4);
   }
}
