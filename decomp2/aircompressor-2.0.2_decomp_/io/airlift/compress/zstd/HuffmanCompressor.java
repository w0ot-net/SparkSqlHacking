package io.airlift.compress.zstd;

class HuffmanCompressor {
   private HuffmanCompressor() {
   }

   public static int compress4streams(Object outputBase, long outputAddress, int outputSize, Object inputBase, long inputAddress, int inputSize, HuffmanCompressionTable table) {
      long inputLimit = inputAddress + (long)inputSize;
      long outputLimit = outputAddress + (long)outputSize;
      int segmentSize = (inputSize + 3) / 4;
      if (outputSize < 17) {
         return 0;
      } else if (inputSize <= 9) {
         return 0;
      } else {
         long output = outputAddress + 6L;
         int compressedSize = compressSingleStream(outputBase, output, (int)(outputLimit - output), inputBase, inputAddress, segmentSize, table);
         if (compressedSize == 0) {
            return 0;
         } else {
            UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress, (short)compressedSize);
            output += (long)compressedSize;
            long input = inputAddress + (long)segmentSize;
            compressedSize = compressSingleStream(outputBase, output, (int)(outputLimit - output), inputBase, input, segmentSize, table);
            if (compressedSize == 0) {
               return 0;
            } else {
               UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress + 2L, (short)compressedSize);
               output += (long)compressedSize;
               input += (long)segmentSize;
               compressedSize = compressSingleStream(outputBase, output, (int)(outputLimit - output), inputBase, input, segmentSize, table);
               if (compressedSize == 0) {
                  return 0;
               } else {
                  UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress + 2L + 2L, (short)compressedSize);
                  output += (long)compressedSize;
                  input += (long)segmentSize;
                  compressedSize = compressSingleStream(outputBase, output, (int)(outputLimit - output), inputBase, input, (int)(inputLimit - input), table);
                  if (compressedSize == 0) {
                     return 0;
                  } else {
                     output += (long)compressedSize;
                     return (int)(output - outputAddress);
                  }
               }
            }
         }
      }
   }

   public static int compressSingleStream(Object outputBase, long outputAddress, int outputSize, Object inputBase, long inputAddress, int inputSize, HuffmanCompressionTable table) {
      if (outputSize < 8) {
         return 0;
      } else {
         BitOutputStream bitstream = new BitOutputStream(outputBase, outputAddress, outputSize);
         long input = inputAddress;
         int n = inputSize & -4;
         switch (inputSize & 3) {
            case 0:
            default:
               break;
            case 3:
               table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + (long)n + 2L) & 255);
            case 2:
               table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + (long)n + 1L) & 255);
            case 1:
               table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress + (long)n + 0L) & 255);
               bitstream.flush();
         }

         while(n > 0) {
            table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, input + (long)n - 1L) & 255);
            table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, input + (long)n - 2L) & 255);
            table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, input + (long)n - 3L) & 255);
            table.encodeSymbol(bitstream, UnsafeUtil.UNSAFE.getByte(inputBase, input + (long)n - 4L) & 255);
            bitstream.flush();
            n -= 4;
         }

         return bitstream.close();
      }
   }
}
