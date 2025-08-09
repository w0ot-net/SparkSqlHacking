package io.airlift.compress.zstd;

class FseTableReader {
   private final short[] nextSymbol = new short[256];
   private final short[] normalizedCounters = new short[256];

   public int readFseTable(FiniteStateEntropy.Table table, Object inputBase, long inputAddress, long inputLimit, int maxSymbol, int maxTableLog) {
      long input = inputAddress;
      Util.verify(inputLimit - inputAddress >= 4L, inputAddress, "Not enough input bytes");
      int symbolNumber = 0;
      boolean previousIsZero = false;
      int bitStream = UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress);
      int tableLog = (bitStream & 15) + 5;
      int numberOfBits = tableLog + 1;
      bitStream >>>= 4;
      int bitCount = 4;
      Util.verify(tableLog <= maxTableLog, inputAddress, "FSE table size exceeds maximum allowed size");
      int remaining = (1 << tableLog) + 1;

      for(int threshold = 1 << tableLog; remaining > 1 && symbolNumber <= maxSymbol; bitStream = UnsafeUtil.UNSAFE.getInt(inputBase, input) >>> (bitCount & 31)) {
         if (previousIsZero) {
            int n0 = symbolNumber;

            while((bitStream & '\uffff') == 65535) {
               n0 += 24;
               if (input < inputLimit - 5L) {
                  input += 2L;
                  bitStream = UnsafeUtil.UNSAFE.getInt(inputBase, input) >>> bitCount;
               } else {
                  bitStream >>>= 16;
                  bitCount += 16;
               }
            }

            while((bitStream & 3) == 3) {
               n0 += 3;
               bitStream >>>= 2;
               bitCount += 2;
            }

            n0 += bitStream & 3;
            bitCount += 2;
            Util.verify(n0 <= maxSymbol, input, "Symbol larger than max value");

            while(symbolNumber < n0) {
               this.normalizedCounters[symbolNumber++] = 0;
            }

            if (input > inputLimit - 7L && input + (long)(bitCount >>> 3) > inputLimit - 4L) {
               bitStream >>>= 2;
            } else {
               input += (long)(bitCount >>> 3);
               bitCount &= 7;
               bitStream = UnsafeUtil.UNSAFE.getInt(inputBase, input) >>> bitCount;
            }
         }

         short max = (short)(2 * threshold - 1 - remaining);
         short count;
         if ((bitStream & threshold - 1) < max) {
            count = (short)(bitStream & threshold - 1);
            bitCount += numberOfBits - 1;
         } else {
            count = (short)(bitStream & 2 * threshold - 1);
            if (count >= threshold) {
               count -= max;
            }

            bitCount += numberOfBits;
         }

         --count;
         remaining -= Math.abs(count);
         this.normalizedCounters[symbolNumber++] = count;

         for(previousIsZero = count == 0; remaining < threshold; threshold >>>= 1) {
            --numberOfBits;
         }

         if (input > inputLimit - 7L && input + (long)(bitCount >> 3) > inputLimit - 4L) {
            bitCount -= (int)(8L * (inputLimit - 4L - input));
            input = inputLimit - 4L;
         } else {
            input += (long)(bitCount >>> 3);
            bitCount &= 7;
         }
      }

      Util.verify(remaining == 1 && bitCount <= 32, input, "Input is corrupted");
      maxSymbol = symbolNumber - 1;
      Util.verify(maxSymbol <= 255, input, "Max symbol value too large (too many symbols for FSE)");
      input += (long)(bitCount + 7 >> 3);
      int symbolCount = maxSymbol + 1;
      int tableSize = 1 << tableLog;
      int highThreshold = tableSize - 1;
      table.log2Size = tableLog;

      for(byte symbol = 0; symbol < symbolCount; ++symbol) {
         if (this.normalizedCounters[symbol] == -1) {
            table.symbol[highThreshold--] = symbol;
            this.nextSymbol[symbol] = 1;
         } else {
            this.nextSymbol[symbol] = this.normalizedCounters[symbol];
         }
      }

      int position = FseCompressionTable.spreadSymbols(this.normalizedCounters, maxSymbol, tableSize, highThreshold, table.symbol);
      Util.verify(position == 0, input, "Input is corrupted");

      for(int i = 0; i < tableSize; ++i) {
         byte symbol = table.symbol[i];
         short nextState = this.nextSymbol[symbol]++;
         table.numberOfBits[i] = (byte)(tableLog - Util.highestBit(nextState));
         table.newState[i] = (short)((nextState << table.numberOfBits[i]) - tableSize);
      }

      return (int)(input - inputAddress);
   }

   public static void initializeRleTable(FiniteStateEntropy.Table table, byte value) {
      table.log2Size = 0;
      table.symbol[0] = value;
      table.newState[0] = 0;
      table.numberOfBits[0] = 0;
   }
}
