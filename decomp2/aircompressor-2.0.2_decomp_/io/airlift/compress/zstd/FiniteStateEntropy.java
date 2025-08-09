package io.airlift.compress.zstd;

import sun.misc.Unsafe;

class FiniteStateEntropy {
   public static final int MAX_SYMBOL = 255;
   public static final int MAX_TABLE_LOG = 12;
   public static final int MIN_TABLE_LOG = 5;
   private static final int[] REST_TO_BEAT = new int[]{0, 473195, 504333, 520860, 550000, 700000, 750000, 830000};
   private static final short UNASSIGNED = -2;

   private FiniteStateEntropy() {
   }

   public static int decompress(Table table, final Object inputBase, final long inputAddress, final long inputLimit, byte[] outputBuffer) {
      Object outputBase = outputBuffer;
      long outputAddress = (long)Unsafe.ARRAY_BYTE_BASE_OFFSET;
      long outputLimit = outputAddress + (long)outputBuffer.length;
      long input = inputAddress;
      long output = outputAddress;
      BitInputStream.Initializer initializer = new BitInputStream.Initializer(inputBase, inputAddress, inputLimit);
      initializer.initialize();
      int bitsConsumed = initializer.getBitsConsumed();
      long currentAddress = initializer.getCurrentAddress();
      long bits = initializer.getBits();
      int state1 = (int)BitInputStream.peekBits(bitsConsumed, bits, table.log2Size);
      bitsConsumed += table.log2Size;
      BitInputStream.Loader loader = new BitInputStream.Loader(inputBase, inputAddress, currentAddress, bits, bitsConsumed);
      loader.load();
      bits = loader.getBits();
      bitsConsumed = loader.getBitsConsumed();
      currentAddress = loader.getCurrentAddress();
      int state2 = (int)BitInputStream.peekBits(bitsConsumed, bits, table.log2Size);
      bitsConsumed += table.log2Size;
      loader = new BitInputStream.Loader(inputBase, inputAddress, currentAddress, bits, bitsConsumed);
      loader.load();
      bits = loader.getBits();
      bitsConsumed = loader.getBitsConsumed();
      currentAddress = loader.getCurrentAddress();
      byte[] symbols = table.symbol;
      byte[] numbersOfBits = table.numberOfBits;
      int[] newStates = table.newState;

      while(output <= outputLimit - 4L) {
         UnsafeUtil.UNSAFE.putByte(outputBase, output, symbols[state1]);
         int numberOfBits = numbersOfBits[state1];
         state1 = (int)((long)newStates[state1] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
         bitsConsumed += numberOfBits;
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 1L, symbols[state2]);
         numberOfBits = numbersOfBits[state2];
         state2 = (int)((long)newStates[state2] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
         bitsConsumed += numberOfBits;
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 2L, symbols[state1]);
         numberOfBits = numbersOfBits[state1];
         state1 = (int)((long)newStates[state1] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
         bitsConsumed += numberOfBits;
         UnsafeUtil.UNSAFE.putByte(outputBase, output + 3L, symbols[state2]);
         numberOfBits = numbersOfBits[state2];
         state2 = (int)((long)newStates[state2] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
         bitsConsumed += numberOfBits;
         output += 4L;
         loader = new BitInputStream.Loader(inputBase, input, currentAddress, bits, bitsConsumed);
         boolean done = loader.load();
         bitsConsumed = loader.getBitsConsumed();
         bits = loader.getBits();
         currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }
      }

      while(true) {
         Util.verify(output <= outputLimit - 2L, input, "Output buffer is too small");
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, symbols[state1]);
         int numberOfBits = numbersOfBits[state1];
         state1 = (int)((long)newStates[state1] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits));
         bitsConsumed += numberOfBits;
         loader = new BitInputStream.Loader(inputBase, input, currentAddress, bits, bitsConsumed);
         loader.load();
         bitsConsumed = loader.getBitsConsumed();
         bits = loader.getBits();
         currentAddress = loader.getCurrentAddress();
         if (loader.isOverflow()) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, symbols[state2]);
            break;
         }

         Util.verify(output <= outputLimit - 2L, input, "Output buffer is too small");
         UnsafeUtil.UNSAFE.putByte(outputBase, output++, symbols[state2]);
         int numberOfBits1 = numbersOfBits[state2];
         state2 = (int)((long)newStates[state2] + BitInputStream.peekBits(bitsConsumed, bits, numberOfBits1));
         bitsConsumed += numberOfBits1;
         loader = new BitInputStream.Loader(inputBase, input, currentAddress, bits, bitsConsumed);
         loader.load();
         bitsConsumed = loader.getBitsConsumed();
         bits = loader.getBits();
         currentAddress = loader.getCurrentAddress();
         if (loader.isOverflow()) {
            UnsafeUtil.UNSAFE.putByte(outputBase, output++, symbols[state1]);
            break;
         }
      }

      return (int)(output - outputAddress);
   }

   public static int compress(Object outputBase, long outputAddress, int outputSize, byte[] input, int inputSize, FseCompressionTable table) {
      return compress(outputBase, outputAddress, outputSize, input, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, inputSize, table);
   }

   public static int compress(Object outputBase, long outputAddress, int outputSize, Object inputBase, long inputAddress, int inputSize, FseCompressionTable table) {
      Util.checkArgument(outputSize >= 8, "Output buffer too small");
      long start = inputAddress;
      long inputLimit = inputAddress + (long)inputSize;
      if (inputSize <= 2) {
         return 0;
      } else {
         BitOutputStream stream = new BitOutputStream(outputBase, outputAddress, outputSize);
         long input;
         int state2;
         int state1;
         if ((inputSize & 1) != 0) {
            input = inputLimit - 1L;
            state1 = table.begin(UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state2 = table.begin(UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state1 = table.encode(stream, state1, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            stream.flush();
         } else {
            input = inputLimit - 1L;
            state2 = table.begin(UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state1 = table.begin(UnsafeUtil.UNSAFE.getByte(inputBase, input));
         }

         inputSize -= 2;
         if ((inputSize & 2) != 0) {
            --input;
            state2 = table.encode(stream, state2, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state1 = table.encode(stream, state1, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            stream.flush();
         }

         while(input > start) {
            --input;
            state2 = table.encode(stream, state2, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state1 = table.encode(stream, state1, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state2 = table.encode(stream, state2, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            --input;
            state1 = table.encode(stream, state1, UnsafeUtil.UNSAFE.getByte(inputBase, input));
            stream.flush();
         }

         table.finish(stream, state2);
         table.finish(stream, state1);
         return stream.close();
      }
   }

   public static int optimalTableLog(int maxTableLog, int inputSize, int maxSymbol) {
      if (inputSize <= 1) {
         throw new IllegalArgumentException();
      } else {
         int result = Math.min(maxTableLog, Util.highestBit(inputSize - 1) - 2);
         result = Math.max(result, Util.minTableLog(inputSize, maxSymbol));
         result = Math.max(result, 5);
         result = Math.min(result, 12);
         return result;
      }
   }

   public static int normalizeCounts(short[] normalizedCounts, int tableLog, int[] counts, int total, int maxSymbol) {
      Util.checkArgument(tableLog >= 5, "Unsupported FSE table size");
      Util.checkArgument(tableLog <= 12, "FSE table size too large");
      Util.checkArgument(tableLog >= Util.minTableLog(total, maxSymbol), "FSE table size too small");
      long scale = (long)(62 - tableLog);
      long step = 4611686018427387904L / (long)total;
      long vstep = 1L << (int)(scale - 20L);
      int stillToDistribute = 1 << tableLog;
      int largest = 0;
      short largestProbability = 0;
      int lowThreshold = total >>> tableLog;

      for(int symbol = 0; symbol <= maxSymbol; ++symbol) {
         if (counts[symbol] == total) {
            throw new IllegalArgumentException();
         }

         if (counts[symbol] == 0) {
            normalizedCounts[symbol] = 0;
         } else if (counts[symbol] <= lowThreshold) {
            normalizedCounts[symbol] = -1;
            --stillToDistribute;
         } else {
            short probability = (short)((int)((long)counts[symbol] * step >>> (int)scale));
            if (probability < 8) {
               long restToBeat = vstep * (long)REST_TO_BEAT[probability];
               long delta = (long)counts[symbol] * step - ((long)probability << (int)scale);
               if (delta > restToBeat) {
                  ++probability;
               }
            }

            if (probability > largestProbability) {
               largestProbability = probability;
               largest = symbol;
            }

            normalizedCounts[symbol] = probability;
            stillToDistribute -= probability;
         }
      }

      if (-stillToDistribute >= normalizedCounts[largest] >>> 1) {
         normalizeCounts2(normalizedCounts, tableLog, counts, total, maxSymbol);
      } else {
         normalizedCounts[largest] += (short)stillToDistribute;
      }

      return tableLog;
   }

   private static int normalizeCounts2(short[] normalizedCounts, int tableLog, int[] counts, int total, int maxSymbol) {
      int distributed = 0;
      int lowThreshold = total >>> tableLog;
      int lowOne = total * 3 >>> tableLog + 1;

      for(int i = 0; i <= maxSymbol; ++i) {
         if (counts[i] == 0) {
            normalizedCounts[i] = 0;
         } else if (counts[i] <= lowThreshold) {
            normalizedCounts[i] = -1;
            ++distributed;
            total -= counts[i];
         } else if (counts[i] <= lowOne) {
            normalizedCounts[i] = 1;
            ++distributed;
            total -= counts[i];
         } else {
            normalizedCounts[i] = -2;
         }
      }

      int normalizationFactor = 1 << tableLog;
      int toDistribute = normalizationFactor - distributed;
      if (total / toDistribute > lowOne) {
         lowOne = total * 3 / (toDistribute * 2);

         for(int i = 0; i <= maxSymbol; ++i) {
            if (normalizedCounts[i] == -2 && counts[i] <= lowOne) {
               normalizedCounts[i] = 1;
               ++distributed;
               total -= counts[i];
            }
         }

         toDistribute = normalizationFactor - distributed;
      }

      if (distributed == maxSymbol + 1) {
         int maxValue = 0;
         int maxCount = 0;

         for(int i = 0; i <= maxSymbol; ++i) {
            if (counts[i] > maxCount) {
               maxValue = i;
               maxCount = counts[i];
            }
         }

         normalizedCounts[maxValue] += (short)toDistribute;
         return 0;
      } else if (total == 0) {
         for(int i = 0; toDistribute > 0; i = (i + 1) % (maxSymbol + 1)) {
            if (normalizedCounts[i] > 0) {
               --toDistribute;
               ++normalizedCounts[i];
            }
         }

         return 0;
      } else {
         long vStepLog = (long)(62 - tableLog);
         long mid = (1L << (int)(vStepLog - 1L)) - 1L;
         long rStep = ((1L << (int)vStepLog) * (long)toDistribute + mid) / (long)total;
         long tmpTotal = mid;

         for(int i = 0; i <= maxSymbol; ++i) {
            if (normalizedCounts[i] == -2) {
               long end = tmpTotal + (long)counts[i] * rStep;
               int sStart = (int)(tmpTotal >>> (int)vStepLog);
               int sEnd = (int)(end >>> (int)vStepLog);
               int weight = sEnd - sStart;
               if (weight < 1) {
                  throw new AssertionError();
               }

               normalizedCounts[i] = (short)weight;
               tmpTotal = end;
            }
         }

         return 0;
      }
   }

   public static int writeNormalizedCounts(Object outputBase, long outputAddress, int outputSize, short[] normalizedCounts, int maxSymbol, int tableLog) {
      Util.checkArgument(tableLog <= 12, "FSE table too large");
      Util.checkArgument(tableLog >= 5, "FSE table too small");
      long output = outputAddress;
      long outputLimit = outputAddress + (long)outputSize;
      int tableSize = 1 << tableLog;
      int bitCount = 0;
      int bitStream = tableLog - 5;
      bitCount += 4;
      int remaining = tableSize + 1;
      int threshold = tableSize;
      int tableBitCount = tableLog + 1;
      int symbol = 0;
      boolean previousIs0 = false;

      while(remaining > 1) {
         if (previousIs0) {
            int start;
            for(start = symbol; normalizedCounts[symbol] == 0; ++symbol) {
            }

            while(symbol >= start + 24) {
               start += 24;
               bitStream |= '\uffff' << bitCount;
               Util.checkArgument(output + 2L <= outputLimit, "Output buffer too small");
               UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)bitStream);
               output += 2L;
               bitStream >>>= 16;
            }

            while(symbol >= start + 3) {
               start += 3;
               bitStream |= 3 << bitCount;
               bitCount += 2;
            }

            bitStream |= symbol - start << bitCount;
            bitCount += 2;
            if (bitCount > 16) {
               Util.checkArgument(output + 2L <= outputLimit, "Output buffer too small");
               UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)bitStream);
               output += 2L;
               bitStream >>>= 16;
               bitCount -= 16;
            }
         }

         int count = normalizedCounts[symbol++];
         int max = 2 * threshold - 1 - remaining;
         remaining -= count < 0 ? -count : count;
         ++count;
         if (count >= threshold) {
            count += max;
         }

         bitStream |= count << bitCount;
         bitCount += tableBitCount;
         bitCount -= count < max ? 1 : 0;
         previousIs0 = count == 1;
         if (remaining < 1) {
            throw new AssertionError();
         }

         while(remaining < threshold) {
            --tableBitCount;
            threshold >>= 1;
         }

         if (bitCount > 16) {
            Util.checkArgument(output + 2L <= outputLimit, "Output buffer too small");
            UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)bitStream);
            output += 2L;
            bitStream >>>= 16;
            bitCount -= 16;
         }
      }

      Util.checkArgument(output + 2L <= outputLimit, "Output buffer too small");
      UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)bitStream);
      output += (long)((bitCount + 7) / 8);
      Util.checkArgument(symbol <= maxSymbol + 1, "Error");
      return (int)(output - outputAddress);
   }

   public static final class Table {
      int log2Size;
      final int[] newState;
      final byte[] symbol;
      final byte[] numberOfBits;

      public Table(int log2Capacity) {
         int capacity = 1 << log2Capacity;
         this.newState = new int[capacity];
         this.symbol = new byte[capacity];
         this.numberOfBits = new byte[capacity];
      }

      public Table(int log2Size, int[] newState, byte[] symbol, byte[] numberOfBits) {
         int size = 1 << log2Size;
         if (newState.length == size && symbol.length == size && numberOfBits.length == size) {
            this.log2Size = log2Size;
            this.newState = newState;
            this.symbol = symbol;
            this.numberOfBits = numberOfBits;
         } else {
            throw new IllegalArgumentException("Expected arrays to match provided size");
         }
      }
   }
}
