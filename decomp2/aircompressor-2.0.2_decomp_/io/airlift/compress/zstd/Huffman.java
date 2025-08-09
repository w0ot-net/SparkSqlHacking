package io.airlift.compress.zstd;

import java.util.Arrays;

class Huffman {
   public static final int MAX_SYMBOL = 255;
   public static final int MAX_SYMBOL_COUNT = 256;
   public static final int MAX_TABLE_LOG = 12;
   public static final int MIN_TABLE_LOG = 5;
   public static final int MAX_FSE_TABLE_LOG = 6;
   private final byte[] weights = new byte[256];
   private final int[] ranks = new int[13];
   private int tableLog = -1;
   private final byte[] symbols = new byte[4096];
   private final byte[] numbersOfBits = new byte[4096];
   private final FseTableReader reader = new FseTableReader();
   private final FiniteStateEntropy.Table fseTable = new FiniteStateEntropy.Table(6);

   public boolean isLoaded() {
      return this.tableLog != -1;
   }

   public int readTable(final Object inputBase, final long inputAddress, final int size) {
      Arrays.fill(this.ranks, 0);
      Util.verify(size > 0, inputAddress, "Not enough input bytes");
      long input = inputAddress + 1L;
      int inputSize = UnsafeUtil.UNSAFE.getByte(inputBase, inputAddress) & 255;
      int outputSize;
      if (inputSize >= 128) {
         outputSize = inputSize - 127;
         inputSize = (outputSize + 1) / 2;
         Util.verify(inputSize + 1 <= size, input, "Not enough input bytes");
         Util.verify(outputSize <= 256, input, "Input is corrupted");

         for(int i = 0; i < outputSize; i += 2) {
            int value = UnsafeUtil.UNSAFE.getByte(inputBase, input + (long)(i / 2)) & 255;
            this.weights[i] = (byte)(value >>> 4);
            this.weights[i + 1] = (byte)(value & 15);
         }
      } else {
         Util.verify(inputSize + 1 <= size, input, "Not enough input bytes");
         long inputLimit = input + (long)inputSize;
         input += (long)this.reader.readFseTable(this.fseTable, inputBase, input, inputLimit, 255, 6);
         outputSize = FiniteStateEntropy.decompress(this.fseTable, inputBase, input, inputLimit, this.weights);
      }

      int totalWeight = 0;

      for(int i = 0; i < outputSize; ++i) {
         int var10002 = this.ranks[this.weights[i]]++;
         totalWeight += 1 << this.weights[i] >> 1;
      }

      Util.verify(totalWeight != 0, input, "Input is corrupted");
      this.tableLog = Util.highestBit(totalWeight) + 1;
      Util.verify(this.tableLog <= 12, input, "Input is corrupted");
      int total = 1 << this.tableLog;
      int rest = total - totalWeight;
      Util.verify(Util.isPowerOf2(rest), input, "Input is corrupted");
      int lastWeight = Util.highestBit(rest) + 1;
      this.weights[outputSize] = (byte)lastWeight;
      int var27 = this.ranks[lastWeight]++;
      int numberOfSymbols = outputSize + 1;
      int nextRankStart = 0;

      for(int i = 1; i < this.tableLog + 1; ++i) {
         int current = nextRankStart;
         nextRankStart += this.ranks[i] << i - 1;
         this.ranks[i] = current;
      }

      for(int n = 0; n < numberOfSymbols; ++n) {
         int weight = this.weights[n];
         int length = 1 << weight >> 1;
         byte symbol = (byte)n;
         byte numberOfBits = (byte)(this.tableLog + 1 - weight);

         for(int i = this.ranks[weight]; i < this.ranks[weight] + length; ++i) {
            this.symbols[i] = symbol;
            this.numbersOfBits[i] = numberOfBits;
         }

         int[] var10000 = this.ranks;
         var10000[weight] += length;
      }

      Util.verify(this.ranks[1] >= 2 && (this.ranks[1] & 1) == 0, input, "Input is corrupted");
      return inputSize + 1;
   }

   public void decodeSingleStream(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      BitInputStream.Initializer initializer = new BitInputStream.Initializer(inputBase, inputAddress, inputLimit);
      initializer.initialize();
      long bits = initializer.getBits();
      int bitsConsumed = initializer.getBitsConsumed();
      long currentAddress = initializer.getCurrentAddress();
      int tableLog = this.tableLog;
      byte[] numbersOfBits = this.numbersOfBits;
      byte[] symbols = this.symbols;
      long output = outputAddress;

      for(long fastOutputLimit = outputLimit - 4L; output < fastOutputLimit; output += 4L) {
         BitInputStream.Loader loader = new BitInputStream.Loader(inputBase, inputAddress, currentAddress, bits, bitsConsumed);
         boolean done = loader.load();
         bits = loader.getBits();
         bitsConsumed = loader.getBitsConsumed();
         currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }

         bitsConsumed = decodeSymbol(outputBase, output, bits, bitsConsumed, tableLog, numbersOfBits, symbols);
         bitsConsumed = decodeSymbol(outputBase, output + 1L, bits, bitsConsumed, tableLog, numbersOfBits, symbols);
         bitsConsumed = decodeSymbol(outputBase, output + 2L, bits, bitsConsumed, tableLog, numbersOfBits, symbols);
         bitsConsumed = decodeSymbol(outputBase, output + 3L, bits, bitsConsumed, tableLog, numbersOfBits, symbols);
      }

      this.decodeTail(inputBase, inputAddress, currentAddress, bitsConsumed, bits, outputBase, output, outputLimit);
   }

   public void decode4Streams(final Object inputBase, final long inputAddress, final long inputLimit, final Object outputBase, final long outputAddress, final long outputLimit) {
      Util.verify(inputLimit - inputAddress >= 10L, inputAddress, "Input is corrupted");
      long start1 = inputAddress + 6L;
      long start2 = start1 + (long)(UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress) & '\uffff');
      long start3 = start2 + (long)(UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress + 2L) & '\uffff');
      long start4 = start3 + (long)(UnsafeUtil.UNSAFE.getShort(inputBase, inputAddress + 4L) & '\uffff');
      Util.verify(start2 < start3 && start3 < start4 && start4 < inputLimit, inputAddress, "Input is corrupted");
      BitInputStream.Initializer initializer = new BitInputStream.Initializer(inputBase, start1, start2);
      initializer.initialize();
      int stream1bitsConsumed = initializer.getBitsConsumed();
      long stream1currentAddress = initializer.getCurrentAddress();
      long stream1bits = initializer.getBits();
      initializer = new BitInputStream.Initializer(inputBase, start2, start3);
      initializer.initialize();
      int stream2bitsConsumed = initializer.getBitsConsumed();
      long stream2currentAddress = initializer.getCurrentAddress();
      long stream2bits = initializer.getBits();
      initializer = new BitInputStream.Initializer(inputBase, start3, start4);
      initializer.initialize();
      int stream3bitsConsumed = initializer.getBitsConsumed();
      long stream3currentAddress = initializer.getCurrentAddress();
      long stream3bits = initializer.getBits();
      initializer = new BitInputStream.Initializer(inputBase, start4, inputLimit);
      initializer.initialize();
      int stream4bitsConsumed = initializer.getBitsConsumed();
      long stream4currentAddress = initializer.getCurrentAddress();
      long stream4bits = initializer.getBits();
      int segmentSize = (int)((outputLimit - outputAddress + 3L) / 4L);
      long outputStart2 = outputAddress + (long)segmentSize;
      long outputStart3 = outputStart2 + (long)segmentSize;
      long outputStart4 = outputStart3 + (long)segmentSize;
      long output1 = outputAddress;
      long output2 = outputStart2;
      long output3 = outputStart3;
      long output4 = outputStart4;
      long fastOutputLimit = outputLimit - 7L;
      int tableLog = this.tableLog;
      byte[] numbersOfBits = this.numbersOfBits;
      byte[] symbols = this.symbols;

      while(output4 < fastOutputLimit) {
         stream1bitsConsumed = decodeSymbol(outputBase, output1, stream1bits, stream1bitsConsumed, tableLog, numbersOfBits, symbols);
         stream2bitsConsumed = decodeSymbol(outputBase, output2, stream2bits, stream2bitsConsumed, tableLog, numbersOfBits, symbols);
         stream3bitsConsumed = decodeSymbol(outputBase, output3, stream3bits, stream3bitsConsumed, tableLog, numbersOfBits, symbols);
         stream4bitsConsumed = decodeSymbol(outputBase, output4, stream4bits, stream4bitsConsumed, tableLog, numbersOfBits, symbols);
         stream1bitsConsumed = decodeSymbol(outputBase, output1 + 1L, stream1bits, stream1bitsConsumed, tableLog, numbersOfBits, symbols);
         stream2bitsConsumed = decodeSymbol(outputBase, output2 + 1L, stream2bits, stream2bitsConsumed, tableLog, numbersOfBits, symbols);
         stream3bitsConsumed = decodeSymbol(outputBase, output3 + 1L, stream3bits, stream3bitsConsumed, tableLog, numbersOfBits, symbols);
         stream4bitsConsumed = decodeSymbol(outputBase, output4 + 1L, stream4bits, stream4bitsConsumed, tableLog, numbersOfBits, symbols);
         stream1bitsConsumed = decodeSymbol(outputBase, output1 + 2L, stream1bits, stream1bitsConsumed, tableLog, numbersOfBits, symbols);
         stream2bitsConsumed = decodeSymbol(outputBase, output2 + 2L, stream2bits, stream2bitsConsumed, tableLog, numbersOfBits, symbols);
         stream3bitsConsumed = decodeSymbol(outputBase, output3 + 2L, stream3bits, stream3bitsConsumed, tableLog, numbersOfBits, symbols);
         stream4bitsConsumed = decodeSymbol(outputBase, output4 + 2L, stream4bits, stream4bitsConsumed, tableLog, numbersOfBits, symbols);
         stream1bitsConsumed = decodeSymbol(outputBase, output1 + 3L, stream1bits, stream1bitsConsumed, tableLog, numbersOfBits, symbols);
         stream2bitsConsumed = decodeSymbol(outputBase, output2 + 3L, stream2bits, stream2bitsConsumed, tableLog, numbersOfBits, symbols);
         stream3bitsConsumed = decodeSymbol(outputBase, output3 + 3L, stream3bits, stream3bitsConsumed, tableLog, numbersOfBits, symbols);
         stream4bitsConsumed = decodeSymbol(outputBase, output4 + 3L, stream4bits, stream4bitsConsumed, tableLog, numbersOfBits, symbols);
         output1 += 4L;
         output2 += 4L;
         output3 += 4L;
         output4 += 4L;
         BitInputStream.Loader loader = new BitInputStream.Loader(inputBase, start1, stream1currentAddress, stream1bits, stream1bitsConsumed);
         boolean done = loader.load();
         stream1bitsConsumed = loader.getBitsConsumed();
         stream1bits = loader.getBits();
         stream1currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }

         loader = new BitInputStream.Loader(inputBase, start2, stream2currentAddress, stream2bits, stream2bitsConsumed);
         done = loader.load();
         stream2bitsConsumed = loader.getBitsConsumed();
         stream2bits = loader.getBits();
         stream2currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }

         loader = new BitInputStream.Loader(inputBase, start3, stream3currentAddress, stream3bits, stream3bitsConsumed);
         done = loader.load();
         stream3bitsConsumed = loader.getBitsConsumed();
         stream3bits = loader.getBits();
         stream3currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }

         loader = new BitInputStream.Loader(inputBase, start4, stream4currentAddress, stream4bits, stream4bitsConsumed);
         done = loader.load();
         stream4bitsConsumed = loader.getBitsConsumed();
         stream4bits = loader.getBits();
         stream4currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }
      }

      Util.verify(output1 <= outputStart2 && output2 <= outputStart3 && output3 <= outputStart4, inputAddress, "Input is corrupted");
      this.decodeTail(inputBase, start1, stream1currentAddress, stream1bitsConsumed, stream1bits, outputBase, output1, outputStart2);
      this.decodeTail(inputBase, start2, stream2currentAddress, stream2bitsConsumed, stream2bits, outputBase, output2, outputStart3);
      this.decodeTail(inputBase, start3, stream3currentAddress, stream3bitsConsumed, stream3bits, outputBase, output3, outputStart4);
      this.decodeTail(inputBase, start4, stream4currentAddress, stream4bitsConsumed, stream4bits, outputBase, output4, outputLimit);
   }

   private void decodeTail(final Object inputBase, final long startAddress, long currentAddress, int bitsConsumed, long bits, final Object outputBase, long outputAddress, final long outputLimit) {
      int tableLog = this.tableLog;
      byte[] numbersOfBits = this.numbersOfBits;

      byte[] symbols;
      for(symbols = this.symbols; outputAddress < outputLimit; bitsConsumed = decodeSymbol(outputBase, outputAddress++, bits, bitsConsumed, tableLog, numbersOfBits, symbols)) {
         BitInputStream.Loader loader = new BitInputStream.Loader(inputBase, startAddress, currentAddress, bits, bitsConsumed);
         boolean done = loader.load();
         bitsConsumed = loader.getBitsConsumed();
         bits = loader.getBits();
         currentAddress = loader.getCurrentAddress();
         if (done) {
            break;
         }
      }

      while(outputAddress < outputLimit) {
         bitsConsumed = decodeSymbol(outputBase, outputAddress++, bits, bitsConsumed, tableLog, numbersOfBits, symbols);
      }

      Util.verify(BitInputStream.isEndOfStream(startAddress, currentAddress, bitsConsumed), startAddress, "Bit stream is not fully consumed");
   }

   private static int decodeSymbol(Object outputBase, long outputAddress, long bitContainer, int bitsConsumed, int tableLog, byte[] numbersOfBits, byte[] symbols) {
      int value = (int)BitInputStream.peekBitsFast(bitsConsumed, bitContainer, tableLog);
      UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, symbols[value]);
      return bitsConsumed + numbersOfBits[value];
   }
}
