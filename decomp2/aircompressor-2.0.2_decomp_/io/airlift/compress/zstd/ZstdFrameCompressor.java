package io.airlift.compress.zstd;

import sun.misc.Unsafe;

class ZstdFrameCompressor {
   static final int MAX_FRAME_HEADER_SIZE = 14;
   private static final int CHECKSUM_FLAG = 4;
   private static final int SINGLE_SEGMENT_FLAG = 32;
   private static final int MINIMUM_LITERALS_SIZE = 63;
   private static final int MAX_HUFFMAN_TABLE_LOG = 11;

   private ZstdFrameCompressor() {
   }

   static int writeMagic(final Object outputBase, final long outputAddress, final long outputLimit) {
      Util.checkArgument(outputLimit - outputAddress >= 4L, "Output buffer too small");
      UnsafeUtil.UNSAFE.putInt(outputBase, outputAddress, -47205080);
      return 4;
   }

   static int writeFrameHeader(final Object outputBase, final long outputAddress, final long outputLimit, int inputSize, int windowSize) {
      Util.checkArgument(outputLimit - outputAddress >= 14L, "Output buffer too small");
      int contentSizeDescriptor = 0;
      if (inputSize != -1) {
         contentSizeDescriptor = (inputSize >= 256 ? 1 : 0) + (inputSize >= 65792 ? 1 : 0);
      }

      int frameHeaderDescriptor = contentSizeDescriptor << 6 | 4;
      boolean singleSegment = inputSize != -1 && windowSize >= inputSize;
      if (singleSegment) {
         frameHeaderDescriptor |= 32;
      }

      UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)frameHeaderDescriptor);
      long output = outputAddress + 1L;
      if (!singleSegment) {
         int base = Integer.highestOneBit(windowSize);
         int exponent = 32 - Integer.numberOfLeadingZeros(base) - 1;
         if (exponent < 10) {
            throw new IllegalArgumentException("Minimum window size is 1024");
         }

         int remainder = windowSize - base;
         if (remainder % (base / 8) != 0) {
            throw new IllegalArgumentException("Window size of magnitude 2^" + exponent + " must be multiple of " + base / 8);
         }

         int mantissa = remainder / (base / 8);
         int encoded = exponent - 10 << 3 | mantissa;
         UnsafeUtil.UNSAFE.putByte(outputBase, output, (byte)encoded);
         ++output;
      }

      switch (contentSizeDescriptor) {
         case 0:
            if (singleSegment) {
               UnsafeUtil.UNSAFE.putByte(outputBase, output++, (byte)inputSize);
            }
            break;
         case 1:
            UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)(inputSize - 256));
            output += 2L;
            break;
         case 2:
            UnsafeUtil.UNSAFE.putInt(outputBase, output, inputSize);
            output += 4L;
            break;
         default:
            throw new AssertionError();
      }

      return (int)(output - outputAddress);
   }

   static int writeChecksum(Object outputBase, long outputAddress, long outputLimit, Object inputBase, long inputAddress, long inputLimit) {
      Util.checkArgument(outputLimit - outputAddress >= 4L, "Output buffer too small");
      int inputSize = (int)(inputLimit - inputAddress);
      long hash = XxHash64.hash(0L, inputBase, inputAddress, inputSize);
      UnsafeUtil.UNSAFE.putInt(outputBase, outputAddress, (int)hash);
      return 4;
   }

   public static int compress(Object inputBase, long inputAddress, long inputLimit, Object outputBase, long outputAddress, long outputLimit, int compressionLevel) {
      int inputSize = (int)(inputLimit - inputAddress);
      CompressionParameters parameters = CompressionParameters.compute(compressionLevel, inputSize);
      long output = outputAddress + (long)writeMagic(outputBase, outputAddress, outputLimit);
      output += (long)writeFrameHeader(outputBase, output, outputLimit, inputSize, parameters.getWindowSize());
      output += (long)compressFrame(inputBase, inputAddress, inputLimit, outputBase, output, outputLimit, parameters);
      output += (long)writeChecksum(outputBase, output, outputLimit, inputBase, inputAddress, inputLimit);
      return (int)(output - outputAddress);
   }

   private static int compressFrame(Object inputBase, long inputAddress, long inputLimit, Object outputBase, long outputAddress, long outputLimit, CompressionParameters parameters) {
      int blockSize = parameters.getBlockSize();
      int outputSize = (int)(outputLimit - outputAddress);
      int remaining = (int)(inputLimit - inputAddress);
      long output = outputAddress;
      long input = inputAddress;
      CompressionContext context = new CompressionContext(parameters, inputAddress, remaining);

      do {
         Util.checkArgument(outputSize >= 6, "Output buffer too small");
         boolean lastBlock = blockSize >= remaining;
         blockSize = Math.min(blockSize, remaining);
         int compressedSize = writeCompressedBlock(inputBase, input, blockSize, outputBase, output, outputSize, context, lastBlock);
         input += (long)blockSize;
         remaining -= blockSize;
         output += (long)compressedSize;
         outputSize -= compressedSize;
      } while(remaining > 0);

      return (int)(output - outputAddress);
   }

   static int writeCompressedBlock(Object inputBase, long input, int blockSize, Object outputBase, long output, int outputSize, CompressionContext context, boolean lastBlock) {
      Util.checkArgument(lastBlock || blockSize == context.parameters.getBlockSize(), "Only last block can be smaller than block size");
      int compressedSize = 0;
      if (blockSize > 0) {
         compressedSize = compressBlock(inputBase, input, blockSize, outputBase, output + 3L, outputSize - 3, context);
      }

      if (compressedSize == 0) {
         Util.checkArgument(blockSize + 3 <= outputSize, "Output size too small");
         int blockHeader = (lastBlock ? 1 : 0) | 0 | blockSize << 3;
         Util.put24BitLittleEndian(outputBase, output, blockHeader);
         UnsafeUtil.UNSAFE.copyMemory(inputBase, input, outputBase, output + 3L, (long)blockSize);
         compressedSize = 3 + blockSize;
      } else {
         int blockHeader = (lastBlock ? 1 : 0) | 4 | compressedSize << 3;
         Util.put24BitLittleEndian(outputBase, output, blockHeader);
         compressedSize += 3;
      }

      return compressedSize;
   }

   private static int compressBlock(Object inputBase, long inputAddress, int inputSize, Object outputBase, long outputAddress, int outputSize, CompressionContext context) {
      if (inputSize < 7) {
         return 0;
      } else {
         CompressionParameters parameters = context.parameters;
         context.blockCompressionState.enforceMaxDistance(inputAddress + (long)inputSize, parameters.getWindowSize());
         context.sequenceStore.reset();
         int lastLiteralsSize = parameters.getStrategy().getCompressor().compressBlock(inputBase, inputAddress, inputSize, context.sequenceStore, context.blockCompressionState, context.offsets, parameters);
         long lastLiteralsAddress = inputAddress + (long)inputSize - (long)lastLiteralsSize;
         context.sequenceStore.appendLiterals(inputBase, lastLiteralsAddress, lastLiteralsSize);
         context.sequenceStore.generateCodes();
         long outputLimit = outputAddress + (long)outputSize;
         int compressedLiteralsSize = encodeLiterals(context.huffmanContext, parameters, outputBase, outputAddress, (int)(outputLimit - outputAddress), context.sequenceStore.literalsBuffer, context.sequenceStore.literalsLength);
         long output = outputAddress + (long)compressedLiteralsSize;
         int compressedSequencesSize = SequenceEncoder.compressSequences(outputBase, output, (int)(outputLimit - output), context.sequenceStore, parameters.getStrategy(), context.sequenceEncodingContext);
         int compressedSize = compressedLiteralsSize + compressedSequencesSize;
         if (compressedSize == 0) {
            return compressedSize;
         } else {
            int maxCompressedSize = inputSize - calculateMinimumGain(inputSize, parameters.getStrategy());
            if (compressedSize > maxCompressedSize) {
               return 0;
            } else {
               context.commit();
               return compressedSize;
            }
         }
      }
   }

   private static int encodeLiterals(HuffmanCompressionContext context, CompressionParameters parameters, Object outputBase, long outputAddress, int outputSize, byte[] literals, int literalsSize) {
      boolean bypassCompression = parameters.getStrategy() == CompressionParameters.Strategy.FAST && parameters.getTargetLength() > 0;
      if (!bypassCompression && literalsSize > 63) {
         int headerSize = 3 + (literalsSize >= 1024 ? 1 : 0) + (literalsSize >= 16384 ? 1 : 0);
         Util.checkArgument(headerSize + 1 <= outputSize, "Output buffer too small");
         int[] counts = new int[256];
         Histogram.count(literals, literalsSize, counts);
         int maxSymbol = Histogram.findMaxSymbol(counts, 255);
         int largestCount = Histogram.findLargestCount(counts, maxSymbol);
         long literalsAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
         if (largestCount == literalsSize) {
            return rleLiterals(outputBase, outputAddress, outputSize, literals, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, literalsSize);
         } else if (largestCount <= (literalsSize >>> 7) + 4) {
            return rawLiterals(outputBase, outputAddress, outputSize, literals, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, literalsSize);
         } else {
            HuffmanCompressionTable previousTable = context.getPreviousTable();
            boolean canReuse = previousTable.isValid(counts, maxSymbol);
            boolean preferReuse = parameters.getStrategy().ordinal() < CompressionParameters.Strategy.LAZY.ordinal() && literalsSize <= 1024;
            HuffmanCompressionTable table;
            int serializedTableSize;
            boolean reuseTable;
            if (preferReuse && canReuse) {
               table = previousTable;
               reuseTable = true;
               serializedTableSize = 0;
            } else {
               HuffmanCompressionTable newTable = context.borrowTemporaryTable();
               newTable.initialize(counts, maxSymbol, HuffmanCompressionTable.optimalNumberOfBits(11, literalsSize, maxSymbol), context.getCompressionTableWorkspace());
               serializedTableSize = newTable.write(outputBase, outputAddress + (long)headerSize, outputSize - headerSize, context.getTableWriterWorkspace());
               if (canReuse && previousTable.estimateCompressedSize(counts, maxSymbol) <= serializedTableSize + newTable.estimateCompressedSize(counts, maxSymbol)) {
                  table = previousTable;
                  reuseTable = true;
                  serializedTableSize = 0;
                  context.discardTemporaryTable();
               } else {
                  table = newTable;
                  reuseTable = false;
               }
            }

            boolean singleStream = literalsSize < 256;
            int compressedSize;
            if (singleStream) {
               compressedSize = HuffmanCompressor.compressSingleStream(outputBase, outputAddress + (long)headerSize + (long)serializedTableSize, outputSize - headerSize - serializedTableSize, literals, literalsAddress, literalsSize, table);
            } else {
               compressedSize = HuffmanCompressor.compress4streams(outputBase, outputAddress + (long)headerSize + (long)serializedTableSize, outputSize - headerSize - serializedTableSize, literals, literalsAddress, literalsSize, table);
            }

            int totalSize = serializedTableSize + compressedSize;
            int minimumGain = calculateMinimumGain(literalsSize, parameters.getStrategy());
            if (compressedSize != 0 && totalSize < literalsSize - minimumGain) {
               int encodingType = reuseTable ? 3 : 2;
               switch (headerSize) {
                  case 3:
                     int header = encodingType | (singleStream ? 0 : 1) << 2 | literalsSize << 4 | totalSize << 14;
                     Util.put24BitLittleEndian(outputBase, outputAddress, header);
                     break;
                  case 4:
                     int header = encodingType | 8 | literalsSize << 4 | totalSize << 18;
                     UnsafeUtil.UNSAFE.putInt(outputBase, outputAddress, header);
                     break;
                  case 5:
                     int header = encodingType | 12 | literalsSize << 4 | totalSize << 22;
                     UnsafeUtil.UNSAFE.putInt(outputBase, outputAddress, header);
                     UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress + 4L, (byte)(totalSize >>> 10));
                     break;
                  default:
                     throw new IllegalStateException();
               }

               return headerSize + totalSize;
            } else {
               context.discardTemporaryTable();
               return rawLiterals(outputBase, outputAddress, outputSize, literals, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, literalsSize);
            }
         }
      } else {
         return rawLiterals(outputBase, outputAddress, outputSize, literals, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, literalsSize);
      }
   }

   private static int rleLiterals(Object outputBase, long outputAddress, int outputSize, Object inputBase, long inputAddress, int inputSize) {
      int headerSize = 1 + (inputSize > 31 ? 1 : 0) + (inputSize > 4095 ? 1 : 0);
      switch (headerSize) {
         case 1:
            UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)(1 | inputSize << 3));
            break;
         case 2:
            UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress, (short)(5 | inputSize << 4));
            break;
         case 3:
            UnsafeUtil.UNSAFE.putInt(outputBase, outputAddress, 13 | inputSize << 4);
            break;
         default:
            throw new IllegalStateException();
      }

      UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress + (long)headerSize, UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress));
      return headerSize + 1;
   }

   private static int calculateMinimumGain(int inputSize, CompressionParameters.Strategy strategy) {
      int minLog = strategy == CompressionParameters.Strategy.BTULTRA ? 7 : 6;
      return (inputSize >>> minLog) + 2;
   }

   private static int rawLiterals(Object outputBase, long outputAddress, int outputSize, Object inputBase, long inputAddress, int inputSize) {
      int headerSize = 1;
      if (inputSize >= 32) {
         ++headerSize;
      }

      if (inputSize >= 4096) {
         ++headerSize;
      }

      Util.checkArgument(inputSize + headerSize <= outputSize, "Output buffer too small");
      switch (headerSize) {
         case 1:
            UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)(0 | inputSize << 3));
            break;
         case 2:
            UnsafeUtil.UNSAFE.putShort(outputBase, outputAddress, (short)(4 | inputSize << 4));
            break;
         case 3:
            Util.put24BitLittleEndian(outputBase, outputAddress, 12 | inputSize << 4);
            break;
         default:
            throw new AssertionError();
      }

      Util.checkArgument(inputSize + 1 <= outputSize, "Output buffer too small");
      UnsafeUtil.UNSAFE.copyMemory(inputBase, inputAddress, outputBase, outputAddress + (long)headerSize, (long)inputSize);
      return headerSize + inputSize;
   }
}
