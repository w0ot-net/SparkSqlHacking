package io.airlift.compress.zstd;

import io.airlift.compress.MalformedInputException;
import java.util.Arrays;
import sun.misc.Unsafe;

class ZstdFrameDecompressor {
   private static final int[] DEC_32_TABLE = new int[]{4, 1, 2, 1, 4, 4, 4, 4};
   private static final int[] DEC_64_TABLE = new int[]{0, 0, 0, -1, 0, 1, 2, 3};
   private static final int V07_MAGIC_NUMBER = -47205081;
   static final int MAX_WINDOW_SIZE = 8388608;
   private static final int[] LITERALS_LENGTH_BASE = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 28, 32, 40, 48, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
   private static final int[] MATCH_LENGTH_BASE = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 37, 39, 41, 43, 47, 51, 59, 67, 83, 99, 131, 259, 515, 1027, 2051, 4099, 8195, 16387, 32771, 65539};
   private static final int[] OFFSET_CODES_BASE = new int[]{0, 1, 1, 5, 13, 29, 61, 125, 253, 509, 1021, 2045, 4093, 8189, 16381, 32765, 65533, 131069, 262141, 524285, 1048573, 2097149, 4194301, 8388605, 16777213, 33554429, 67108861, 134217725, 268435453};
   private static final FiniteStateEntropy.Table DEFAULT_LITERALS_LENGTH_TABLE = new FiniteStateEntropy.Table(6, new int[]{0, 16, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 32, 0, 0, 32, 0, 32, 0, 32, 0, 0, 32, 0, 32, 0, 32, 0, 0, 16, 32, 0, 0, 48, 16, 32, 32, 32, 32, 32, 32, 32, 32, 0, 32, 32, 32, 32, 32, 32, 0, 0, 0, 0}, new byte[]{0, 0, 1, 3, 4, 6, 7, 9, 10, 12, 14, 16, 18, 19, 21, 22, 24, 25, 26, 27, 29, 31, 0, 1, 2, 4, 5, 7, 8, 10, 11, 13, 16, 17, 19, 20, 22, 23, 25, 25, 26, 28, 30, 0, 1, 2, 3, 5, 6, 8, 9, 11, 12, 15, 17, 18, 20, 21, 23, 24, 35, 34, 33, 32}, new byte[]{4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 4, 4, 5, 6, 6, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6});
   private static final FiniteStateEntropy.Table DEFAULT_OFFSET_CODES_TABLE = new FiniteStateEntropy.Table(5, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 6, 9, 15, 21, 3, 7, 12, 18, 23, 5, 8, 14, 20, 2, 7, 11, 17, 22, 4, 8, 13, 19, 1, 6, 10, 16, 28, 27, 26, 25, 24}, new byte[]{5, 4, 5, 5, 5, 5, 4, 5, 5, 5, 5, 4, 5, 5, 5, 4, 5, 5, 5, 5, 4, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5});
   private static final FiniteStateEntropy.Table DEFAULT_MATCH_LENGTH_TABLE = new FiniteStateEntropy.Table(6, new int[]{0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 32, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 48, 16, 32, 32, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 1, 2, 3, 5, 6, 8, 10, 13, 16, 19, 22, 25, 28, 31, 33, 35, 37, 39, 41, 43, 45, 1, 2, 3, 4, 6, 7, 9, 12, 15, 18, 21, 24, 27, 30, 32, 34, 36, 38, 40, 42, 44, 1, 1, 2, 4, 5, 7, 8, 11, 14, 17, 20, 23, 26, 29, 52, 51, 50, 49, 48, 47, 46}, new byte[]{6, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6});
   private final byte[] literals = new byte[131080];
   private Object literalsBase;
   private long literalsAddress;
   private long literalsLimit;
   private final int[] previousOffsets = new int[3];
   private final FiniteStateEntropy.Table literalsLengthTable = new FiniteStateEntropy.Table(9);
   private final FiniteStateEntropy.Table offsetCodesTable = new FiniteStateEntropy.Table(8);
   private final FiniteStateEntropy.Table matchLengthTable = new FiniteStateEntropy.Table(9);
   private FiniteStateEntropy.Table currentLiteralsLengthTable;
   private FiniteStateEntropy.Table currentOffsetCodesTable;
   private FiniteStateEntropy.Table currentMatchLengthTable;
   private final Huffman huffman = new Huffman();
   private final FseTableReader fse = new FseTableReader();

   public int decompress(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      if (outputAddress == outputLimit) {
         return 0;
      } else {
         long input = inputAddress;
         long output = outputAddress;

         while(input < inputLimit) {
            this.reset();
            long outputStart = output;
            input += (long)verifyMagic(inputBase, input, inputLimit);
            FrameHeader frameHeader = readFrameHeader(inputBase, input, inputLimit);
            input += frameHeader.headerSize;

            boolean lastBlock;
            do {
               Util.verify(input + 3L <= inputLimit, input, "Not enough input bytes");
               int header = Util.get24BitLittleEndian(inputBase, input);
               input += 3L;
               lastBlock = (header & 1) != 0;
               int blockType = header >>> 1 & 3;
               int blockSize = header >>> 3 & 2097151;
               int decodedSize;
               switch (blockType) {
                  case 0:
                     Util.verify(input + (long)blockSize <= inputLimit, input, "Not enough input bytes");
                     decodedSize = decodeRawBlock(inputBase, input, blockSize, outputBase, output, outputLimit);
                     input += (long)blockSize;
                     break;
                  case 1:
                     Util.verify(input + 1L <= inputLimit, input, "Not enough input bytes");
                     decodedSize = decodeRleBlock(blockSize, inputBase, input, outputBase, output, outputLimit);
                     ++input;
                     break;
                  case 2:
                     Util.verify(input + (long)blockSize <= inputLimit, input, "Not enough input bytes");
                     decodedSize = this.decodeCompressedBlock(inputBase, input, blockSize, outputBase, output, outputLimit, frameHeader.windowSize, outputAddress);
                     input += (long)blockSize;
                     break;
                  default:
                     throw Util.fail(input, "Invalid block type");
               }

               output += (long)decodedSize;
            } while(!lastBlock);

            if (frameHeader.hasChecksum) {
               int decodedFrameSize = (int)(output - outputStart);
               long hash = XxHash64.hash(0L, outputBase, outputStart, decodedFrameSize);
               Util.verify(input + 4L <= inputLimit, input, "Not enough input bytes");
               int checksum = UnsafeUtil.UNSAFE.getInt(inputBase, input);
               if (checksum != (int)hash) {
                  throw new MalformedInputException(input, String.format("Bad checksum. Expected: %s, actual: %s", Integer.toHexString(checksum), Integer.toHexString((int)hash)));
               }

               input += 4L;
            }
         }

         return (int)(output - outputAddress);
      }
   }

   void reset() {
      this.previousOffsets[0] = 1;
      this.previousOffsets[1] = 4;
      this.previousOffsets[2] = 8;
      this.currentLiteralsLengthTable = null;
      this.currentOffsetCodesTable = null;
      this.currentMatchLengthTable = null;
   }

   static int decodeRawBlock(Object inputBase, long inputAddress, int blockSize, Object outputBase, long outputAddress, long outputLimit) {
      Util.verify(outputAddress + (long)blockSize <= outputLimit, inputAddress, "Output buffer too small");
      UnsafeUtil.UNSAFE.copyMemory(inputBase, inputAddress, outputBase, outputAddress, (long)blockSize);
      return blockSize;
   }

   static int decodeRleBlock(int size, Object inputBase, long inputAddress, Object outputBase, long outputAddress, long outputLimit) {
      Util.verify(outputAddress + (long)size <= outputLimit, inputAddress, "Output buffer too small");
      long output = outputAddress;
      long value = (long)UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255L;
      int remaining = size;
      if (size >= 8) {
         long packed = value | value << 8 | value << 16 | value << 24 | value << 32 | value << 40 | value << 48 | value << 56;

         do {
            UnsafeUtil.UNSAFE.putLong(outputBase, output, packed);
            output += 8L;
            remaining -= 8;
         } while(remaining >= 8);
      }

      for(int i = 0; i < remaining; ++i) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output, (byte)((int)value));
         ++output;
      }

      return size;
   }

   int decodeCompressedBlock(Object inputBase, final long inputAddress, int blockSize, Object outputBase, long outputAddress, long outputLimit, int windowSize, long outputAbsoluteBaseAddress) {
      long inputLimit = inputAddress + (long)blockSize;
      Util.verify(blockSize <= 131072, inputAddress, "Expected match length table to be present");
      Util.verify(blockSize >= 3, inputAddress, "Compressed block size too small");
      int literalsBlockType = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 3;
      long input;
      switch (literalsBlockType) {
         case 0:
            input = inputAddress + (long)this.decodeRawLiterals(inputBase, inputAddress, inputLimit);
            break;
         case 1:
            input = inputAddress + (long)this.decodeRleLiterals(inputBase, inputAddress, blockSize);
            break;
         case 3:
            Util.verify(this.huffman.isLoaded(), inputAddress, "Dictionary is corrupted");
         case 2:
            input = inputAddress + (long)this.decodeCompressedLiterals(inputBase, inputAddress, blockSize, literalsBlockType);
            break;
         default:
            throw Util.fail(inputAddress, "Invalid literals block encoding type");
      }

      Util.verify(windowSize <= 8388608, input, "Window size too large (not yet supported)");
      return this.decompressSequences(inputBase, input, inputAddress + (long)blockSize, outputBase, outputAddress, outputLimit, this.literalsBase, this.literalsAddress, this.literalsLimit, outputAbsoluteBaseAddress);
   }

   private int decompressSequences(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit, final Object literalsBase, final long literalsAddress, final long literalsLimit, long outputAbsoluteBaseAddress) {
      long fastOutputLimit = outputLimit - 8L;
      long fastMatchOutputLimit = fastOutputLimit - 8L;
      long output = outputAddress;
      long literalsInput = literalsAddress;
      int size = (int)(inputLimit - inputAddress);
      Util.verify(size >= 1, inputAddress, "Not enough input bytes");
      long input = inputAddress + 1L;
      int sequenceCount = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255;
      if (sequenceCount != 0) {
         if (sequenceCount == 255) {
            Util.verify(input + 2L <= inputLimit, input, "Not enough input bytes");
            sequenceCount = (UnsafeUtil.UNSAFE.getShort(inputBase, input) & '\uffff') + 32512;
            input += 2L;
         } else if (sequenceCount > 127) {
            Util.verify(input < inputLimit, input, "Not enough input bytes");
            sequenceCount = (sequenceCount - 128 << 8) + (UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255);
         }

         Util.verify(input + 4L <= inputLimit, input, "Not enough input bytes");
         byte type = UnsafeUtil.UNSAFE.getByte(inputBase, input++);
         int literalsLengthType = (type & 255) >>> 6;
         int offsetCodesType = type >>> 4 & 3;
         int matchLengthType = type >>> 2 & 3;
         long var77 = this.computeLiteralsTable(literalsLengthType, inputBase, input, inputLimit);
         long var78 = this.computeOffsetsTable(offsetCodesType, inputBase, var77, inputLimit);
         input = this.computeMatchLengthTable(matchLengthType, inputBase, var78, inputLimit);
         BitInputStream.Initializer initializer = new BitInputStream.Initializer(inputBase, input, inputLimit);
         initializer.initialize();
         int bitsConsumed = initializer.getBitsConsumed();
         long bits = initializer.getBits();
         long currentAddress = initializer.getCurrentAddress();
         FiniteStateEntropy.Table currentLiteralsLengthTable = this.currentLiteralsLengthTable;
         FiniteStateEntropy.Table currentOffsetCodesTable = this.currentOffsetCodesTable;
         FiniteStateEntropy.Table currentMatchLengthTable = this.currentMatchLengthTable;
         int literalsLengthState = (int)BitInputStream.peekBits(bitsConsumed, bits, currentLiteralsLengthTable.log2Size);
         bitsConsumed += currentLiteralsLengthTable.log2Size;
         int offsetCodesState = (int)BitInputStream.peekBits(bitsConsumed, bits, currentOffsetCodesTable.log2Size);
         bitsConsumed += currentOffsetCodesTable.log2Size;
         int matchLengthState = (int)BitInputStream.peekBits(bitsConsumed, bits, currentMatchLengthTable.log2Size);
         bitsConsumed += currentMatchLengthTable.log2Size;
         int[] previousOffsets = this.previousOffsets;
         byte[] literalsLengthNumbersOfBits = currentLiteralsLengthTable.numberOfBits;
         int[] literalsLengthNewStates = currentLiteralsLengthTable.newState;
         byte[] literalsLengthSymbols = currentLiteralsLengthTable.symbol;
         byte[] matchLengthNumbersOfBits = currentMatchLengthTable.numberOfBits;
         int[] matchLengthNewStates = currentMatchLengthTable.newState;
         byte[] matchLengthSymbols = currentMatchLengthTable.symbol;
         byte[] offsetCodesNumbersOfBits = currentOffsetCodesTable.numberOfBits;
         int[] offsetCodesNewStates = currentOffsetCodesTable.newState;

         long literalEnd;
         for(byte[] offsetCodesSymbols = currentOffsetCodesTable.symbol; sequenceCount > 0; literalsInput = literalEnd) {
            --sequenceCount;
            BitInputStream.Loader loader = new BitInputStream.Loader(inputBase, input, currentAddress, bits, bitsConsumed);
            loader.load();
            bitsConsumed = loader.getBitsConsumed();
            bits = loader.getBits();
            currentAddress = loader.getCurrentAddress();
            if (loader.isOverflow()) {
               Util.verify(sequenceCount == 0, input, "Not all sequences were consumed");
               break;
            }

            int literalsLengthCode = literalsLengthSymbols[literalsLengthState];
            int matchLengthCode = matchLengthSymbols[matchLengthState];
            int offsetCode = offsetCodesSymbols[offsetCodesState];
            int literalsLengthBits = Constants.LITERALS_LENGTH_BITS[literalsLengthCode];
            int matchLengthBits = Constants.MATCH_LENGTH_BITS[matchLengthCode];
            int offset = OFFSET_CODES_BASE[offsetCode];
            if (offsetCode > 0) {
               offset = (int)((long)offset + BitInputStream.peekBits(bitsConsumed, bits, offsetCode));
               bitsConsumed += offsetCode;
            }

            if (offsetCode <= 1) {
               if (literalsLengthCode == 0) {
                  ++offset;
               }

               if (offset != 0) {
                  int temp;
                  if (offset == 3) {
                     temp = previousOffsets[0] - 1;
                  } else {
                     temp = previousOffsets[offset];
                  }

                  if (temp == 0) {
                     temp = 1;
                  }

                  if (offset != 1) {
                     previousOffsets[2] = previousOffsets[1];
                  }

                  previousOffsets[1] = previousOffsets[0];
                  previousOffsets[0] = temp;
                  offset = temp;
               } else {
                  offset = previousOffsets[0];
               }
            } else {
               previousOffsets[2] = previousOffsets[1];
               previousOffsets[1] = previousOffsets[0];
               previousOffsets[0] = offset;
            }

            int matchLength = MATCH_LENGTH_BASE[matchLengthCode];
            if (matchLengthCode > 31) {
               matchLength = (int)((long)matchLength + BitInputStream.peekBits(bitsConsumed, bits, matchLengthBits));
               bitsConsumed += matchLengthBits;
            }

            int literalsLength = LITERALS_LENGTH_BASE[literalsLengthCode];
            if (literalsLengthCode > 15) {
               literalsLength = (int)((long)literalsLength + BitInputStream.peekBits(bitsConsumed, bits, literalsLengthBits));
               bitsConsumed += literalsLengthBits;
            }

            int totalBits = literalsLengthBits + matchLengthBits + offsetCode;
            if (totalBits > 31) {
               BitInputStream.Loader loader1 = new BitInputStream.Loader(inputBase, input, currentAddress, bits, bitsConsumed);
               loader1.load();
               bitsConsumed = loader1.getBitsConsumed();
               bits = loader1.getBits();
               currentAddress = loader1.getCurrentAddress();
            }

            int numberOfBits = literalsLengthNumbersOfBits[literalsLengthState];
            literalsLengthState = (int)((long)literalsLengthNewStates[literalsLengthState] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
            bitsConsumed += numberOfBits;
            numberOfBits = matchLengthNumbersOfBits[matchLengthState];
            matchLengthState = (int)((long)matchLengthNewStates[matchLengthState] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
            bitsConsumed += numberOfBits;
            numberOfBits = offsetCodesNumbersOfBits[offsetCodesState];
            offsetCodesState = (int)((long)offsetCodesNewStates[offsetCodesState] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
            bitsConsumed += numberOfBits;
            long literalOutputLimit = output + (long)literalsLength;
            long matchOutputLimit = literalOutputLimit + (long)matchLength;
            Util.verify(matchOutputLimit <= outputLimit, input, "Output buffer too small");
            literalEnd = literalsInput + (long)literalsLength;
            Util.verify(literalEnd <= literalsLimit, input, "Input is corrupted");
            long matchAddress = literalOutputLimit - (long)offset;
            Util.verify(matchAddress >= outputAbsoluteBaseAddress, input, "Input is corrupted");
            if (literalOutputLimit > fastOutputLimit) {
               this.executeLastSequence(outputBase, output, literalOutputLimit, matchOutputLimit, fastOutputLimit, literalsInput, matchAddress);
            } else {
               output = copyLiterals(outputBase, literalsBase, output, literalsInput, literalOutputLimit);
               copyMatch(outputBase, fastOutputLimit, output, offset, matchOutputLimit, matchAddress, matchLength, fastMatchOutputLimit);
            }

            output = matchOutputLimit;
         }
      }

      output = copyLastLiteral(input, literalsBase, literalsInput, literalsLimit, outputBase, output, outputLimit);
      return (int)(output - outputAddress);
   }

   private static long copyLastLiteral(long input, Object literalsBase, long literalsInput, long literalsLimit, Object outputBase, long output, long outputLimit) {
      long lastLiteralsSize = literalsLimit - literalsInput;
      Util.verify(output + lastLiteralsSize <= outputLimit, input, "Output buffer too small");
      UnsafeUtil.UNSAFE.copyMemory(literalsBase, literalsInput, outputBase, output, lastLiteralsSize);
      output += lastLiteralsSize;
      return output;
   }

   private static void copyMatch(Object outputBase, long fastOutputLimit, long output, int offset, long matchOutputLimit, long matchAddress, int matchLength, long fastMatchOutputLimit) {
      matchAddress = copyMatchHead(outputBase, output, offset, matchAddress);
      output += 8L;
      matchLength -= 8;
      copyMatchTail(outputBase, fastOutputLimit, output, matchOutputLimit, matchAddress, matchLength, fastMatchOutputLimit);
   }

   private static void copyMatchTail(Object outputBase, long fastOutputLimit, long output, long matchOutputLimit, long matchAddress, int matchLength, long fastMatchOutputLimit) {
      if (matchOutputLimit < fastMatchOutputLimit) {
         int copied = 0;

         do {
            UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
            output += 8L;
            matchAddress += 8L;
            copied += 8;
         } while(copied < matchLength);
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

   private static long copyMatchHead(Object outputBase, long output, int offset, long matchAddress) {
      if (offset < 8) {
         int increment32 = DEC_32_TABLE[offset];
         int decrement64 = DEC_64_TABLE[offset];
         UnsafeUtil.UNSAFE.putByte(outputBase, output, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress));
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 1L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 1L));
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 2L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 2L));
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 3L, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress + 3L));
         matchAddress += (long)increment32;
         UnsafeUtil.UNSAFE.putInt(outputBase, output + 4L, UnsafeUtil.UNSAFE.getInt(outputBase, matchAddress));
         matchAddress -= (long)decrement64;
      } else {
         UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(outputBase, matchAddress));
         matchAddress += 8L;
      }

      return matchAddress;
   }

   private static long copyLiterals(Object outputBase, Object literalsBase, long output, long literalsInput, long literalOutputLimit) {
      long literalInput = literalsInput;

      do {
         UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(literalsBase, literalInput));
         output += 8L;
         literalInput += 8L;
      } while(output < literalOutputLimit);

      return literalOutputLimit;
   }

   private long computeMatchLengthTable(int matchLengthType, Object inputBase, long input, long inputLimit) {
      switch (matchLengthType) {
         case 0:
            this.currentMatchLengthTable = DEFAULT_MATCH_LENGTH_TABLE;
            break;
         case 1:
            Util.verify(input < inputLimit, input, "Not enough input bytes");
            byte value = UnsafeUtil.UNSAFE.getByte(inputBase, input++);
            Util.verify(value <= 52, input, "Value exceeds expected maximum value");
            FseTableReader.initializeRleTable(this.matchLengthTable, value);
            this.currentMatchLengthTable = this.matchLengthTable;
            break;
         case 2:
            input += (long)this.fse.readFseTable(this.matchLengthTable, inputBase, input, inputLimit, 52, 9);
            this.currentMatchLengthTable = this.matchLengthTable;
            break;
         case 3:
            Util.verify(this.currentMatchLengthTable != null, input, "Expected match length table to be present");
            break;
         default:
            throw Util.fail(input, "Invalid match length encoding type");
      }

      return input;
   }

   private long computeOffsetsTable(int offsetCodesType, Object inputBase, long input, long inputLimit) {
      switch (offsetCodesType) {
         case 0:
            this.currentOffsetCodesTable = DEFAULT_OFFSET_CODES_TABLE;
            break;
         case 1:
            Util.verify(input < inputLimit, input, "Not enough input bytes");
            byte value = UnsafeUtil.UNSAFE.getByte(inputBase, input++);
            Util.verify(value <= 28, input, "Value exceeds expected maximum value");
            FseTableReader.initializeRleTable(this.offsetCodesTable, value);
            this.currentOffsetCodesTable = this.offsetCodesTable;
            break;
         case 2:
            input += (long)this.fse.readFseTable(this.offsetCodesTable, inputBase, input, inputLimit, 28, 8);
            this.currentOffsetCodesTable = this.offsetCodesTable;
            break;
         case 3:
            Util.verify(this.currentOffsetCodesTable != null, input, "Expected match length table to be present");
            break;
         default:
            throw Util.fail(input, "Invalid offset code encoding type");
      }

      return input;
   }

   private long computeLiteralsTable(int literalsLengthType, Object inputBase, long input, long inputLimit) {
      switch (literalsLengthType) {
         case 0:
            this.currentLiteralsLengthTable = DEFAULT_LITERALS_LENGTH_TABLE;
            break;
         case 1:
            Util.verify(input < inputLimit, input, "Not enough input bytes");
            byte value = UnsafeUtil.UNSAFE.getByte(inputBase, input++);
            Util.verify(value <= 35, input, "Value exceeds expected maximum value");
            FseTableReader.initializeRleTable(this.literalsLengthTable, value);
            this.currentLiteralsLengthTable = this.literalsLengthTable;
            break;
         case 2:
            input += (long)this.fse.readFseTable(this.literalsLengthTable, inputBase, input, inputLimit, 35, 9);
            this.currentLiteralsLengthTable = this.literalsLengthTable;
            break;
         case 3:
            Util.verify(this.currentLiteralsLengthTable != null, input, "Expected match length table to be present");
            break;
         default:
            throw Util.fail(input, "Invalid literals length encoding type");
      }

      return input;
   }

   private void executeLastSequence(Object outputBase, long output, long literalOutputLimit, long matchOutputLimit, long fastOutputLimit, long literalInput, long matchAddress) {
      if (output < fastOutputLimit) {
         do {
            UnsafeUtil.UNSAFE.putLong(outputBase, output, UnsafeUtil.UNSAFE.getLong(this.literalsBase, literalInput));
            output += 8L;
            literalInput += 8L;
         } while(output < fastOutputLimit);

         literalInput -= output - fastOutputLimit;
         output = fastOutputLimit;
      }

      while(output < literalOutputLimit) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output, UnsafeUtil.UNSAFE.getByte(this.literalsBase, literalInput));
         ++output;
         ++literalInput;
      }

      while(output < matchOutputLimit) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output, UnsafeUtil.UNSAFE.getByte(outputBase, matchAddress));
         ++output;
         ++matchAddress;
      }

   }

   private int decodeCompressedLiterals(Object inputBase, final long inputAddress, int blockSize, int literalsBlockType) {
      Util.verify(blockSize >= 5, inputAddress, "Not enough input bytes");
      boolean singleStream = false;
      int type = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) >> 2 & 3;
      int compressedSize;
      int uncompressedSize;
      int headerSize;
      switch (type) {
         case 0:
            singleStream = true;
         case 1:
            int header = UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress);
            headerSize = 3;
            uncompressedSize = header >>> 4 & Util.mask(10);
            compressedSize = header >>> 14 & Util.mask(10);
            break;
         case 2:
            int header = UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress);
            headerSize = 4;
            uncompressedSize = header >>> 4 & Util.mask(14);
            compressedSize = header >>> 18 & Util.mask(14);
            break;
         case 3:
            long header = (long)(UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255) | ((long)UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress + 1L) & 4294967295L) << 8;
            headerSize = 5;
            uncompressedSize = (int)(header >>> 4 & (long)Util.mask(18));
            compressedSize = (int)(header >>> 22 & (long)Util.mask(18));
            break;
         default:
            throw Util.fail(inputAddress, "Invalid literals header size type");
      }

      Util.verify(uncompressedSize <= 131072, inputAddress, "Block exceeds maximum size");
      Util.verify(headerSize + compressedSize <= blockSize, inputAddress, "Input is corrupted");
      long input = inputAddress + (long)headerSize;
      long inputLimit = input + (long)compressedSize;
      if (literalsBlockType != 3) {
         input += (long)this.huffman.readTable(inputBase, input, compressedSize);
      }

      this.literalsBase = this.literals;
      this.literalsAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      this.literalsLimit = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + uncompressedSize);
      if (singleStream) {
         this.huffman.decodeSingleStream(inputBase, input, inputLimit, this.literals, this.literalsAddress, this.literalsLimit);
      } else {
         this.huffman.decode4Streams(inputBase, input, inputLimit, this.literals, this.literalsAddress, this.literalsLimit);
      }

      return headerSize + compressedSize;
   }

   private int decodeRleLiterals(Object inputBase, final long inputAddress, int blockSize) {
      int type = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) >> 2 & 3;
      long input;
      int outputSize;
      switch (type) {
         case 0:
         case 2:
            outputSize = (UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255) >>> 3;
            input = inputAddress + 1L;
            break;
         case 1:
            outputSize = (UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress) & '\uffff') >>> 4;
            input = inputAddress + 2L;
            break;
         case 3:
            Util.verify(blockSize >= 4, inputAddress, "Not enough input bytes");
            outputSize = (UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress) & 16777215) >>> 4;
            input = inputAddress + 3L;
            break;
         default:
            throw Util.fail(inputAddress, "Invalid RLE literals header encoding type");
      }

      Util.verify(outputSize <= 131072, input, "Output exceeds maximum block size");
      byte value = UnsafeUtil.UNSAFE.getByte(inputBase, input++);
      Arrays.fill(this.literals, 0, outputSize + 8, value);
      this.literalsBase = this.literals;
      this.literalsAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      this.literalsLimit = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + outputSize);
      return (int)(input - inputAddress);
   }

   private int decodeRawLiterals(Object inputBase, final long inputAddress, long inputLimit) {
      int type = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) >> 2 & 3;
      long input;
      int literalSize;
      switch (type) {
         case 0:
         case 2:
            literalSize = (UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255) >>> 3;
            input = inputAddress + 1L;
            break;
         case 1:
            literalSize = (UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress) & '\uffff') >>> 4;
            input = inputAddress + 2L;
            break;
         case 3:
            int header = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255 | (UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress + 1L) & '\uffff') << 8;
            literalSize = header >>> 4;
            input = inputAddress + 3L;
            break;
         default:
            throw Util.fail(inputAddress, "Invalid raw literals header encoding type");
      }

      Util.verify(input + (long)literalSize <= inputLimit, input, "Not enough input bytes");
      if ((long)literalSize > inputLimit - input - 8L) {
         this.literalsBase = this.literals;
         this.literalsAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
         this.literalsLimit = (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + literalSize);
         UnsafeUtil.UNSAFE.copyMemory(inputBase, input, this.literals, this.literalsAddress, (long)literalSize);
         Arrays.fill(this.literals, literalSize, literalSize + 8, (byte)0);
      } else {
         this.literalsBase = inputBase;
         this.literalsAddress = input;
         this.literalsLimit = this.literalsAddress + (long)literalSize;
      }

      input += (long)literalSize;
      return (int)(input - inputAddress);
   }

   static FrameHeader readFrameHeader(final Object inputBase, final long inputAddress, final long inputLimit) {
      Util.verify(inputAddress < inputLimit, inputAddress, "Not enough input bytes");
      long input = inputAddress + 1L;
      int frameHeaderDescriptor = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255;
      boolean singleSegment = (frameHeaderDescriptor & 32) != 0;
      int dictionaryDescriptor = frameHeaderDescriptor & 3;
      int contentSizeDescriptor = frameHeaderDescriptor >>> 6;
      int headerSize = 1 + (singleSegment ? 0 : 1) + (dictionaryDescriptor == 0 ? 0 : 1 << dictionaryDescriptor - 1) + (contentSizeDescriptor == 0 ? (singleSegment ? 1 : 0) : 1 << contentSizeDescriptor);
      Util.verify((long)headerSize <= inputLimit - inputAddress, input, "Not enough input bytes");
      int windowSize = -1;
      if (!singleSegment) {
         int windowDescriptor = UnsafeUtil.UNSAFE.getByte(inputBase, input++) & 255;
         int exponent = windowDescriptor >>> 3;
         int mantissa = windowDescriptor & 7;
         int base = 1 << 10 + exponent;
         windowSize = base + base / 8 * mantissa;
      }

      long dictionaryId = -1L;
      switch (dictionaryDescriptor) {
         case 1:
            dictionaryId = (long)(UnsafeUtil.UNSAFE.getByte(inputBase, input) & 255);
            ++input;
            break;
         case 2:
            dictionaryId = (long)(UnsafeUtil.UNSAFE.getShort(inputBase, input) & '\uffff');
            input += 2L;
            break;
         case 3:
            dictionaryId = (long)UnsafeUtil.UNSAFE.getInt(inputBase, input) & 4294967295L;
            input += 4L;
      }

      Util.verify(dictionaryId == -1L, input, "Custom dictionaries not supported");
      long contentSize = -1L;
      switch (contentSizeDescriptor) {
         case 0:
            if (singleSegment) {
               contentSize = (long)(UnsafeUtil.UNSAFE.getByte(inputBase, input) & 255);
               ++input;
            }
            break;
         case 1:
            contentSize = (long)(UnsafeUtil.UNSAFE.getShort(inputBase, input) & '\uffff');
            contentSize += 256L;
            input += 2L;
            break;
         case 2:
            contentSize = (long)UnsafeUtil.UNSAFE.getInt(inputBase, input) & 4294967295L;
            input += 4L;
            break;
         case 3:
            contentSize = UnsafeUtil.UNSAFE.getLong(inputBase, input);
            input += 8L;
      }

      boolean hasChecksum = (frameHeaderDescriptor & 4) != 0;
      return new FrameHeader(input - inputAddress, windowSize, contentSize, dictionaryId, hasChecksum);
   }

   public static long getDecompressedSize(final Object inputBase, final long inputAddress, final long inputLimit) {
      long input = inputAddress + (long)verifyMagic(inputBase, inputAddress, inputLimit);
      return readFrameHeader(inputBase, input, inputLimit).contentSize;
   }

   static int verifyMagic(Object inputBase, long inputAddress, long inputLimit) {
      Util.verify(inputLimit - inputAddress >= 4L, inputAddress, "Not enough input bytes");
      int magic = UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress);
      if (magic != -47205080) {
         if (magic == -47205081) {
            throw new MalformedInputException(inputAddress, "Data encoded in unsupported ZSTD v0.7 format");
         } else {
            throw new MalformedInputException(inputAddress, "Invalid magic prefix: " + Integer.toHexString(magic));
         }
      } else {
         return 4;
      }
   }
}
