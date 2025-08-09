package io.airlift.compress.snappy;

import io.airlift.compress.MalformedInputException;

public final class SnappyRawDecompressor {
   private static final int[] DEC_32_TABLE = new int[]{4, 1, 2, 1, 4, 4, 4, 4};
   private static final int[] DEC_64_TABLE = new int[]{0, 0, 0, -1, 0, 1, 2, 3};
   private static final int[] wordmask = new int[]{0, 255, 65535, 16777215, -1};
   private static final short[] opLookupTable = new short[]{1, 2052, 4097, 8193, 2, 2053, 4098, 8194, 3, 2054, 4099, 8195, 4, 2055, 4100, 8196, 5, 2056, 4101, 8197, 6, 2057, 4102, 8198, 7, 2058, 4103, 8199, 8, 2059, 4104, 8200, 9, 2308, 4105, 8201, 10, 2309, 4106, 8202, 11, 2310, 4107, 8203, 12, 2311, 4108, 8204, 13, 2312, 4109, 8205, 14, 2313, 4110, 8206, 15, 2314, 4111, 8207, 16, 2315, 4112, 8208, 17, 2564, 4113, 8209, 18, 2565, 4114, 8210, 19, 2566, 4115, 8211, 20, 2567, 4116, 8212, 21, 2568, 4117, 8213, 22, 2569, 4118, 8214, 23, 2570, 4119, 8215, 24, 2571, 4120, 8216, 25, 2820, 4121, 8217, 26, 2821, 4122, 8218, 27, 2822, 4123, 8219, 28, 2823, 4124, 8220, 29, 2824, 4125, 8221, 30, 2825, 4126, 8222, 31, 2826, 4127, 8223, 32, 2827, 4128, 8224, 33, 3076, 4129, 8225, 34, 3077, 4130, 8226, 35, 3078, 4131, 8227, 36, 3079, 4132, 8228, 37, 3080, 4133, 8229, 38, 3081, 4134, 8230, 39, 3082, 4135, 8231, 40, 3083, 4136, 8232, 41, 3332, 4137, 8233, 42, 3333, 4138, 8234, 43, 3334, 4139, 8235, 44, 3335, 4140, 8236, 45, 3336, 4141, 8237, 46, 3337, 4142, 8238, 47, 3338, 4143, 8239, 48, 3339, 4144, 8240, 49, 3588, 4145, 8241, 50, 3589, 4146, 8242, 51, 3590, 4147, 8243, 52, 3591, 4148, 8244, 53, 3592, 4149, 8245, 54, 3593, 4150, 8246, 55, 3594, 4151, 8247, 56, 3595, 4152, 8248, 57, 3844, 4153, 8249, 58, 3845, 4154, 8250, 59, 3846, 4155, 8251, 60, 3847, 4156, 8252, 2049, 3848, 4157, 8253, 4097, 3849, 4158, 8254, 6145, 3850, 4159, 8255, 8193, 3851, 4160, 8256};

   private SnappyRawDecompressor() {
   }

   public static int getUncompressedLength(Object compressed, long compressedAddress, long compressedLimit) {
      return readUncompressedLength(compressed, compressedAddress, compressedLimit)[0];
   }

   public static int decompress(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      int[] varInt = readUncompressedLength(inputBase, inputAddress, inputLimit);
      int expectedLength = varInt[0];
      long input = inputAddress + (long)varInt[1];
      SnappyInternalUtils.checkArgument((long)expectedLength <= outputLimit - outputAddress, "Uncompressed length %s must be less than %s", expectedLength, outputLimit - outputAddress);
      int uncompressedSize = uncompressAll(inputBase, input, inputLimit, outputBase, outputAddress, outputLimit);
      if (expectedLength != uncompressedSize) {
         throw new MalformedInputException(0L, String.format("Recorded length is %s bytes but actual length after decompression is %s bytes ", expectedLength, uncompressedSize));
      } else {
         return expectedLength;
      }
   }

   private static int uncompressAll(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      long fastOutputLimit = outputLimit - 8L;
      long output = outputAddress;
      long input = inputAddress;

      while(input < inputLimit) {
         int opCode = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
         int entry = opLookupTable[opCode] & '\uffff';
         int trailerBytes = entry >>> 11;
         int trailer = 0;
         if (input + 4L < inputLimit) {
            trailer = UnsafeUtil.UNSAFE.getInt(inputBase, input) & wordmask[trailerBytes];
         } else {
            if (input + (long)trailerBytes > inputLimit) {
               throw new MalformedInputException(input - inputAddress);
            }

            switch (trailerBytes) {
               case 4:
                  trailer = (UnsafeUtil.UNSAFE.getByte(inputBase, input + 3L) & 255) << 24;
               case 3:
                  trailer |= (UnsafeUtil.UNSAFE.getByte(inputBase, input + 2L) & 255) << 16;
               case 2:
                  trailer |= (UnsafeUtil.UNSAFE.getByte(inputBase, input + 1L) & 255) << 8;
               case 1:
                  trailer |= UnsafeUtil.UNSAFE.getByte(inputBase, input) & 255;
            }
         }

         if (trailer < 0) {
            throw new MalformedInputException(input - inputAddress);
         }

         input += (long)trailerBytes;
         int length = entry & 255;
         if (length != 0) {
            if ((opCode & 3) == 0) {
               int literalLength = length + trailer;
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
            } else {
               int matchOffset = entry & 1792;
               matchOffset += trailer;
               if (matchOffset < 0) {
                  throw new MalformedInputException(input - inputAddress);
               }

               long matchAddress = output - (long)matchOffset;
               if (matchAddress < outputAddress || output + (long)length > outputLimit) {
                  throw new MalformedInputException(input - inputAddress);
               }

               long matchOutputLimit = output + (long)length;
               if (matchOutputLimit > outputLimit) {
                  throw new MalformedInputException(input - inputAddress);
               }

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

                  if (matchOutputLimit <= fastOutputLimit) {
                     while(output < matchOutputLimit) {
                        UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
                        matchAddress += 8L;
                        output += 8L;
                     }
                  } else {
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
         }
      }

      return (int)(output - outputAddress);
   }

   static int[] readUncompressedLength(Object compressed, long compressedAddress, long compressedLimit) {
      int bytesRead = 0;
      int b = getUnsignedByteSafe(compressed, compressedAddress + (long)bytesRead, compressedLimit);
      ++bytesRead;
      int result = b & 127;
      if ((b & 128) != 0) {
         b = getUnsignedByteSafe(compressed, compressedAddress + (long)bytesRead, compressedLimit);
         ++bytesRead;
         result |= (b & 127) << 7;
         if ((b & 128) != 0) {
            b = getUnsignedByteSafe(compressed, compressedAddress + (long)bytesRead, compressedLimit);
            ++bytesRead;
            result |= (b & 127) << 14;
            if ((b & 128) != 0) {
               b = getUnsignedByteSafe(compressed, compressedAddress + (long)bytesRead, compressedLimit);
               ++bytesRead;
               result |= (b & 127) << 21;
               if ((b & 128) != 0) {
                  b = getUnsignedByteSafe(compressed, compressedAddress + (long)bytesRead, compressedLimit);
                  ++bytesRead;
                  result |= (b & 127) << 28;
                  if ((b & 128) != 0) {
                     throw new MalformedInputException(compressedAddress + (long)bytesRead, "last byte of compressed length int has high bit set");
                  }
               }
            }
         }
      }

      if (result < 0) {
         throw new MalformedInputException(compressedAddress, "negative compressed length");
      } else {
         return new int[]{result, bytesRead};
      }
   }

   private static int getUnsignedByteSafe(Object base, long address, long limit) {
      if (address >= limit) {
         throw new MalformedInputException(limit - address, "Input is truncated");
      } else {
         return UnsafeUtil.UNSAFE.getByte(base, address) & 255;
      }
   }
}
