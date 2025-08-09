package org.apache.datasketches.cpc;

final class CpcCompression {
   static final int NEXT_WORD_IDX = 0;
   static final int BIT_BUF = 1;
   static final int BUF_BITS = 2;

   static void writeUnary(int[] compressedWords, long[] ptrArr, int theValue) {
      // $FF: Couldn't be decompiled
   }

   static long readUnary(int[] compressedWords, long[] ptrArr) {
      // $FF: Couldn't be decompiled
   }

   static int lowLevelCompressBytes(byte[] byteArray, int numBytesToEncode, short[] encodingTable, int[] compressedWords) {
      int nextWordIndex = 0;
      long bitBuf = 0L;
      int bufBits = 0;

      for(int byteIndex = 0; byteIndex < numBytesToEncode; ++byteIndex) {
         int theByte = byteArray[byteIndex] & 255;
         long codeInfo = (long)encodingTable[theByte] & 65535L;
         long codeVal = codeInfo & 4095L;
         int codeWordLength = (int)(codeInfo >>> 12);
         bitBuf |= codeVal << bufBits;
         bufBits += codeWordLength;
         if (bufBits >= 32) {
            compressedWords[nextWordIndex++] = (int)bitBuf;
            bitBuf >>>= 32;
            bufBits -= 32;
         }
      }

      bufBits += 11;
      if (bufBits >= 32) {
         compressedWords[nextWordIndex++] = (int)bitBuf;
         bitBuf >>>= 32;
         bufBits -= 32;
      }

      if (bufBits > 0) {
         assert bufBits < 32;

         compressedWords[nextWordIndex++] = (int)bitBuf;
      }

      return nextWordIndex;
   }

   static void lowLevelUncompressBytes(byte[] byteArray, int numBytesToDecode, short[] decodingTable, int[] compressedWords, long numCompressedWords) {
      int byteIndex = 0;
      int nextWordIndex = 0;
      long bitBuf = 0L;
      int bufBits = 0;

      assert byteArray != null;

      assert decodingTable != null;

      assert compressedWords != null;

      for(int var15 = 0; var15 < numBytesToDecode; ++var15) {
         if (bufBits < 12) {
            bitBuf |= ((long)compressedWords[nextWordIndex++] & 4294967295L) << bufBits;
            bufBits += 32;
         }

         int peek12 = (int)(bitBuf & 4095L);
         int lookup = decodingTable[peek12] & '\uffff';
         int codeWordLength = lookup >>> 8;
         byte decodedByte = (byte)(lookup & 255);
         byteArray[var15] = decodedByte;
         bitBuf >>>= codeWordLength;
         bufBits -= codeWordLength;
      }

      assert (long)nextWordIndex <= numCompressedWords;

   }

   static long lowLevelCompressPairs(int[] pairArray, int numPairsToEncode, int numBaseBits, int[] compressedWords) {
      // $FF: Couldn't be decompiled
   }

   static void lowLevelUncompressPairs(int[] pairArray, int numPairsToDecode, int numBaseBits, int[] compressedWords, long numCompressedWords) {
      int pairIndex = 0;
      long[] ptrArr = new long[3];
      int nextWordIndex = 0;
      long bitBuf = 0L;
      int bufBits = 0;
      long golombLoMask = (1L << numBaseBits) - 1L;
      int predictedRowIndex = 0;
      int predictedColIndex = 0;

      for(int var29 = 0; var29 < numPairsToDecode; ++var29) {
         if (bufBits < 12) {
            bitBuf |= ((long)compressedWords[nextWordIndex++] & 4294967295L) << bufBits;
            bufBits += 32;
         }

         int peek12 = (int)(bitBuf & 4095L);
         int lookup = CompressionData.lengthLimitedUnaryDecodingTable65[peek12] & '\uffff';
         int codeWordLength = lookup >>> 8;
         int xDelta = lookup & 255;
         bitBuf >>>= codeWordLength;
         bufBits -= codeWordLength;
         ptrArr[0] = (long)nextWordIndex;
         ptrArr[1] = bitBuf;
         ptrArr[2] = (long)bufBits;

         assert (long)nextWordIndex == ptrArr[0];

         long golombHi = readUnary(compressedWords, ptrArr);
         nextWordIndex = (int)ptrArr[0];
         bitBuf = ptrArr[1];
         bufBits = (int)ptrArr[2];

         assert (long)nextWordIndex == ptrArr[0];

         if (bufBits < numBaseBits) {
            bitBuf |= ((long)compressedWords[nextWordIndex++] & 4294967295L) << bufBits;
            bufBits += 32;
         }

         long golombLo = bitBuf & golombLoMask;
         bitBuf >>>= numBaseBits;
         bufBits -= numBaseBits;
         long yDelta = golombHi << numBaseBits | golombLo;
         if (yDelta > 0L) {
            predictedColIndex = 0;
         }

         int rowIndex = predictedRowIndex + (int)yDelta;
         int colIndex = predictedColIndex + xDelta;
         int rowCol = rowIndex << 6 | colIndex;
         pairArray[var29] = rowCol;
         predictedRowIndex = rowIndex;
         predictedColIndex = colIndex + 1;
      }

      assert (long)nextWordIndex <= numCompressedWords : "nextWdIdx: " + nextWordIndex + ", #CompWds: " + numCompressedWords;

   }

   private static int safeLengthForCompressedPairBuf(long k, long numPairs, long numBaseBits) {
      assert numPairs > 0L;

      long ybits = numPairs * (1L + numBaseBits) + (k >>> (int)numBaseBits);
      long xbits = 12L * numPairs;
      long padding = 10L - numBaseBits;
      if (padding < 0L) {
         padding = 0L;
      }

      long bits = xbits + ybits + padding;
      long words = divideBy32RoundingUp(bits);

      assert words < 2147483648L;

      return (int)words;
   }

   private static int safeLengthForCompressedWindowBuf(long k) {
      long bits = 12L * k + 11L;
      return (int)divideBy32RoundingUp(bits);
   }

   private static int determinePseudoPhase(int lgK, long numCoupons) {
      long k = 1L << lgK;
      if (1000L * numCoupons < 2375L * k) {
         if (4L * numCoupons < 3L * k) {
            return 16;
         } else if (10L * numCoupons < 11L * k) {
            return 17;
         } else if (100L * numCoupons < 132L * k) {
            return 18;
         } else if (3L * numCoupons < 5L * k) {
            return 19;
         } else if (1000L * numCoupons < 1965L * k) {
            return 20;
         } else {
            return 1000L * numCoupons < 2275L * k ? 21 : 6;
         }
      } else {
         assert lgK >= 4;

         long tmp = numCoupons >>> lgK - 4;
         int phase = (int)(tmp & 15L);

         assert phase >= 0 && phase < 16;

         return phase;
      }
   }

   private static void compressTheWindow(CompressedState target, CpcSketch source) {
      int srcLgK = source.lgK;
      int srcK = 1 << srcLgK;
      int windowBufLen = safeLengthForCompressedWindowBuf((long)srcK);
      int[] windowBuf = new int[windowBufLen];
      int pseudoPhase = determinePseudoPhase(srcLgK, source.numCoupons);
      target.cwLengthInts = lowLevelCompressBytes(source.slidingWindow, srcK, CompressionData.encodingTablesForHighEntropyByte[pseudoPhase], windowBuf);
      target.cwStream = windowBuf;
   }

   private static void uncompressTheWindow(CpcSketch target, CompressedState source) {
      int srcLgK = source.lgK;
      int srcK = 1 << srcLgK;
      byte[] window = new byte[srcK];

      assert target.slidingWindow == null;

      target.slidingWindow = window;
      int pseudoPhase = determinePseudoPhase(srcLgK, source.numCoupons);

      assert source.cwStream != null;

      lowLevelUncompressBytes(target.slidingWindow, srcK, CompressionData.decodingTablesForHighEntropyByte[pseudoPhase], source.cwStream, (long)source.cwLengthInts);
   }

   private static void compressTheSurprisingValues(CompressedState target, CpcSketch source, int[] pairs, int numPairs) {
      assert numPairs > 0;

      target.numCsv = numPairs;
      int srcK = 1 << source.lgK;
      int numBaseBits = golombChooseNumberOfBaseBits(srcK + numPairs, (long)numPairs);
      int pairBufLen = safeLengthForCompressedPairBuf((long)srcK, (long)numPairs, (long)numBaseBits);
      int[] pairBuf = new int[pairBufLen];
      target.csvLengthInts = (int)lowLevelCompressPairs(pairs, numPairs, numBaseBits, pairBuf);
      target.csvStream = pairBuf;
   }

   private static int[] uncompressTheSurprisingValues(CompressedState source) {
      int srcK = 1 << source.lgK;
      int numPairs = source.numCsv;

      assert numPairs > 0;

      int[] pairs = new int[numPairs];
      int numBaseBits = golombChooseNumberOfBaseBits(srcK + numPairs, (long)numPairs);
      lowLevelUncompressPairs(pairs, numPairs, numBaseBits, source.csvStream, (long)source.csvLengthInts);
      return pairs;
   }

   private static void compressSparseFlavor(CompressedState target, CpcSketch source) {
      assert source.slidingWindow == null;

      PairTable srcPairTable = source.pairTable;
      int srcNumPairs = srcPairTable.getNumPairs();
      int[] srcPairArr = PairTable.unwrappingGetItems(srcPairTable, srcNumPairs);
      PairTable.introspectiveInsertionSort(srcPairArr, 0, srcNumPairs - 1);
      compressTheSurprisingValues(target, source, srcPairArr, srcNumPairs);
   }

   private static void uncompressSparseFlavor(CpcSketch target, CompressedState source) {
      assert source.cwStream == null;

      assert source.csvStream != null;

      int[] srcPairArr = uncompressTheSurprisingValues(source);
      int numPairs = source.numCsv;
      PairTable table = PairTable.newInstanceFromPairsArray(srcPairArr, numPairs, source.lgK);
      target.pairTable = table;
   }

   private static int[] trickyGetPairsFromWindow(byte[] window, int k, int numPairsToGet, int emptySpace) {
      int outputLength = emptySpace + numPairsToGet;
      int[] pairs = new int[outputLength];
      int rowIndex = 0;
      int pairIndex = emptySpace;

      int colIndex;
      for(int var10 = 0; var10 < k; ++var10) {
         for(int wByte = window[var10] & 255; wByte != 0; pairs[pairIndex++] = var10 << 6 | colIndex) {
            colIndex = Integer.numberOfTrailingZeros(wByte);
            wByte ^= 1 << colIndex;
         }
      }

      assert pairIndex == outputLength;

      return pairs;
   }

   private static void compressHybridFlavor(CompressedState target, CpcSketch source) {
      int srcK = 1 << source.lgK;
      PairTable srcPairTable = source.pairTable;
      int srcNumPairs = srcPairTable.getNumPairs();
      int[] srcPairArr = PairTable.unwrappingGetItems(srcPairTable, srcNumPairs);
      PairTable.introspectiveInsertionSort(srcPairArr, 0, srcNumPairs - 1);
      byte[] srcSlidingWindow = source.slidingWindow;
      int srcWindowOffset = source.windowOffset;
      long srcNumCoupons = source.numCoupons;

      assert srcSlidingWindow != null;

      assert srcWindowOffset == 0;

      long numPairs = srcNumCoupons - (long)srcNumPairs;

      assert numPairs < 2147483647L;

      int numPairsFromArray = (int)numPairs;

      assert (long)(numPairsFromArray + srcNumPairs) == srcNumCoupons;

      int[] allPairs = trickyGetPairsFromWindow(srcSlidingWindow, srcK, numPairsFromArray, srcNumPairs);
      PairTable.merge(srcPairArr, 0, srcNumPairs, allPairs, srcNumPairs, numPairsFromArray, allPairs, 0);
      compressTheSurprisingValues(target, source, allPairs, (int)srcNumCoupons);
   }

   private static void uncompressHybridFlavor(CpcSketch target, CompressedState source) {
      assert source.cwStream == null;

      assert source.csvStream != null;

      int[] pairs = uncompressTheSurprisingValues(source);
      int numPairs = source.numCsv;
      int srcLgK = source.lgK;
      int k = 1 << srcLgK;
      byte[] window = new byte[k];
      int nextTruePair = 0;

      for(int i = 0; i < numPairs; ++i) {
         int rowCol = pairs[i];

         assert rowCol != -1;

         int col = rowCol & 63;
         if (col < 8) {
            int row = rowCol >>> 6;
            window[row] = (byte)(window[row] | 1 << col);
         } else {
            pairs[nextTruePair++] = rowCol;
         }
      }

      assert source.getWindowOffset() == 0;

      target.windowOffset = 0;
      PairTable table = PairTable.newInstanceFromPairsArray(pairs, nextTruePair, srcLgK);
      target.pairTable = table;
      target.slidingWindow = window;
   }

   private static void compressPinnedFlavor(CompressedState target, CpcSketch source) {
      compressTheWindow(target, source);
      PairTable srcPairTable = source.pairTable;
      int numPairs = srcPairTable.getNumPairs();
      if (numPairs > 0) {
         int[] pairs = PairTable.unwrappingGetItems(srcPairTable, numPairs);

         for(int i = 0; i < numPairs; ++i) {
            assert (pairs[i] & 63) >= 8;

            pairs[i] -= 8;
         }

         PairTable.introspectiveInsertionSort(pairs, 0, numPairs - 1);
         compressTheSurprisingValues(target, source, pairs, numPairs);
      }

   }

   private static void uncompressPinnedFlavor(CpcSketch target, CompressedState source) {
      assert source.cwStream != null;

      uncompressTheWindow(target, source);
      int srcLgK = source.lgK;
      int numPairs = source.numCsv;
      if (numPairs == 0) {
         target.pairTable = new PairTable(2, 6 + srcLgK);
      } else {
         assert numPairs > 0;

         assert source.csvStream != null;

         int[] pairs = uncompressTheSurprisingValues(source);

         for(int i = 0; i < numPairs; ++i) {
            assert (pairs[i] & 63) < 56;

            pairs[i] += 8;
         }

         PairTable table = PairTable.newInstanceFromPairsArray(pairs, numPairs, srcLgK);
         target.pairTable = table;
      }

   }

   private static void compressSlidingFlavor(CompressedState target, CpcSketch source) {
      compressTheWindow(target, source);
      PairTable srcPairTable = source.pairTable;
      int numPairs = srcPairTable.getNumPairs();
      if (numPairs > 0) {
         int[] pairs = PairTable.unwrappingGetItems(srcPairTable, numPairs);
         int pseudoPhase = determinePseudoPhase(source.lgK, source.numCoupons);

         assert pseudoPhase < 16;

         byte[] permutation = CompressionData.columnPermutationsForEncoding[pseudoPhase];
         int offset = source.windowOffset;

         assert offset > 0 && offset <= 56;

         for(int i = 0; i < numPairs; ++i) {
            int rowCol = pairs[i];
            int row = rowCol >>> 6;
            int col = rowCol & 63;
            col = col + 56 - offset & 63;

            assert col >= 0 && col < 56;

            col = permutation[col];
            pairs[i] = row << 6 | col;
         }

         PairTable.introspectiveInsertionSort(pairs, 0, numPairs - 1);
         compressTheSurprisingValues(target, source, pairs, numPairs);
      }

   }

   private static void uncompressSlidingFlavor(CpcSketch target, CompressedState source) {
      assert source.cwStream != null;

      uncompressTheWindow(target, source);
      int srcLgK = source.lgK;
      int numPairs = source.numCsv;
      if (numPairs == 0) {
         target.pairTable = new PairTable(2, 6 + srcLgK);
      } else {
         assert numPairs > 0;

         assert source.csvStream != null;

         int[] pairs = uncompressTheSurprisingValues(source);
         int pseudoPhase = determinePseudoPhase(srcLgK, source.numCoupons);

         assert pseudoPhase < 16;

         byte[] permutation = CompressionData.columnPermutationsForDecoding[pseudoPhase];
         int offset = source.getWindowOffset();

         assert offset > 0 && offset <= 56;

         for(int i = 0; i < numPairs; ++i) {
            int rowCol = pairs[i];
            int row = rowCol >>> 6;
            int col = rowCol & 63;
            col = permutation[col];
            col = col + offset + 8 & 63;
            pairs[i] = row << 6 | col;
         }

         PairTable table = PairTable.newInstanceFromPairsArray(pairs, numPairs, srcLgK);
         target.pairTable = table;
      }

   }

   static CompressedState compress(CpcSketch source, CompressedState target) {
      Flavor srcFlavor = source.getFlavor();
      switch (srcFlavor) {
         case EMPTY:
         default:
            break;
         case SPARSE:
            compressSparseFlavor(target, source);

            assert target.cwStream == null;

            assert target.csvStream != null;
            break;
         case HYBRID:
            compressHybridFlavor(target, source);

            assert target.cwStream == null;

            assert target.csvStream != null;
            break;
         case PINNED:
            compressPinnedFlavor(target, source);

            assert target.cwStream != null;
            break;
         case SLIDING:
            compressSlidingFlavor(target, source);

            assert target.cwStream != null;
      }

      return target;
   }

   static CpcSketch uncompress(CompressedState source, CpcSketch target) {
      assert target != null;

      Flavor srcFlavor = source.getFlavor();
      switch (srcFlavor) {
         case EMPTY:
         default:
            break;
         case SPARSE:
            assert source.cwStream == null;

            uncompressSparseFlavor(target, source);
            break;
         case HYBRID:
            uncompressHybridFlavor(target, source);
            break;
         case PINNED:
            assert source.cwStream != null;

            uncompressPinnedFlavor(target, source);
            break;
         case SLIDING:
            uncompressSlidingFlavor(target, source);
      }

      return target;
   }

   private static int golombChooseNumberOfBaseBits(int k, long count) {
      assert (long)k >= 1L;

      assert count >= 1L;

      long quotient = ((long)k - count) / count;
      return quotient == 0L ? 0 : (int)floorLog2ofLong(quotient);
   }

   private static long floorLog2ofLong(long x) {
      assert x >= 1L;

      long p = 0L;

      for(long y = 1L; y != x; y <<= 1) {
         if (y > x) {
            return p - 1L;
         }

         ++p;
      }

      return p;
   }

   private static long divideBy32RoundingUp(long x) {
      long tmp = x >>> 5;
      return tmp << 5 == x ? tmp : tmp + 1L;
   }
}
