package io.airlift.compress.zstd;

class FseCompressionTable {
   private final short[] nextState;
   private final int[] deltaNumberOfBits;
   private final int[] deltaFindState;
   private int log2Size;

   public FseCompressionTable(int maxTableLog, int maxSymbol) {
      this.nextState = new short[1 << maxTableLog];
      this.deltaNumberOfBits = new int[maxSymbol + 1];
      this.deltaFindState = new int[maxSymbol + 1];
   }

   public static FseCompressionTable newInstance(short[] normalizedCounts, int maxSymbol, int tableLog) {
      FseCompressionTable result = new FseCompressionTable(tableLog, maxSymbol);
      result.initialize(normalizedCounts, maxSymbol, tableLog);
      return result;
   }

   public void initializeRleTable(int symbol) {
      this.log2Size = 0;
      this.nextState[0] = 0;
      this.nextState[1] = 0;
      this.deltaFindState[symbol] = 0;
      this.deltaNumberOfBits[symbol] = 0;
   }

   public void initialize(short[] normalizedCounts, int maxSymbol, int tableLog) {
      int tableSize = 1 << tableLog;
      byte[] table = new byte[tableSize];
      int highThreshold = tableSize - 1;
      this.log2Size = tableLog;
      int[] cumulative = new int[257];
      cumulative[0] = 0;

      for(int i = 1; i <= maxSymbol + 1; ++i) {
         if (normalizedCounts[i - 1] == -1) {
            cumulative[i] = cumulative[i - 1] + 1;
            table[highThreshold--] = (byte)(i - 1);
         } else {
            cumulative[i] = cumulative[i - 1] + normalizedCounts[i - 1];
         }
      }

      cumulative[maxSymbol + 1] = tableSize + 1;
      int position = spreadSymbols(normalizedCounts, maxSymbol, tableSize, highThreshold, table);
      if (position != 0) {
         throw new AssertionError("Spread symbols failed");
      } else {
         for(int i = 0; i < tableSize; ++i) {
            byte symbol = table[i];
            this.nextState[cumulative[symbol]++] = (short)(tableSize + i);
         }

         int total = 0;

         for(int symbol = 0; symbol <= maxSymbol; ++symbol) {
            switch (normalizedCounts[symbol]) {
               case -1:
               case 1:
                  this.deltaNumberOfBits[symbol] = (tableLog << 16) - tableSize;
                  this.deltaFindState[symbol] = total - 1;
                  ++total;
                  break;
               case 0:
                  this.deltaNumberOfBits[symbol] = (tableLog + 1 << 16) - tableSize;
                  break;
               default:
                  int maxBitsOut = tableLog - Util.highestBit(normalizedCounts[symbol] - 1);
                  int minStatePlus = normalizedCounts[symbol] << maxBitsOut;
                  this.deltaNumberOfBits[symbol] = (maxBitsOut << 16) - minStatePlus;
                  this.deltaFindState[symbol] = total - normalizedCounts[symbol];
                  total += normalizedCounts[symbol];
            }
         }

      }
   }

   public int begin(byte symbol) {
      int outputBits = this.deltaNumberOfBits[symbol] + '耀' >>> 16;
      int base = (outputBits << 16) - this.deltaNumberOfBits[symbol] >>> outputBits;
      return this.nextState[base + this.deltaFindState[symbol]];
   }

   public int encode(BitOutputStream stream, int state, int symbol) {
      int outputBits = state + this.deltaNumberOfBits[symbol] >>> 16;
      stream.addBits(state, outputBits);
      return this.nextState[(state >>> outputBits) + this.deltaFindState[symbol]];
   }

   public void finish(BitOutputStream stream, int state) {
      stream.addBits(state, this.log2Size);
      stream.flush();
   }

   private static int calculateStep(int tableSize) {
      return (tableSize >>> 1) + (tableSize >>> 3) + 3;
   }

   public static int spreadSymbols(short[] param0, int param1, int param2, int param3, byte[] param4) {
      // $FF: Couldn't be decompiled
   }
}
