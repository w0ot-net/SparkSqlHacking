package io.airlift.compress.zstd;

class SequenceEncoder {
   private static final int DEFAULT_LITERAL_LENGTH_NORMALIZED_COUNTS_LOG = 6;
   private static final short[] DEFAULT_LITERAL_LENGTH_NORMALIZED_COUNTS = new short[]{4, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 1, 1, 1, 1, 1, -1, -1, -1, -1};
   private static final int DEFAULT_MATCH_LENGTH_NORMALIZED_COUNTS_LOG = 6;
   private static final short[] DEFAULT_MATCH_LENGTH_NORMALIZED_COUNTS = new short[]{1, 4, 3, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1};
   private static final int DEFAULT_OFFSET_NORMALIZED_COUNTS_LOG = 5;
   private static final short[] DEFAULT_OFFSET_NORMALIZED_COUNTS = new short[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1};
   private static final FseCompressionTable DEFAULT_LITERAL_LENGTHS_TABLE;
   private static final FseCompressionTable DEFAULT_MATCH_LENGTHS_TABLE;
   private static final FseCompressionTable DEFAULT_OFFSETS_TABLE;

   private SequenceEncoder() {
   }

   public static int compressSequences(Object outputBase, final long outputAddress, int outputSize, SequenceStore sequences, CompressionParameters.Strategy strategy, SequenceEncodingContext workspace) {
      long outputLimit = outputAddress + (long)outputSize;
      Util.checkArgument(outputLimit - outputAddress > 4L, "Output buffer too small");
      int sequenceCount = sequences.sequenceCount;
      long output;
      if (sequenceCount < 127) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)sequenceCount);
         output = outputAddress + 1L;
      } else if (sequenceCount < 32512) {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)(sequenceCount >>> 8 | 128));
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress + 1L, (byte)sequenceCount);
         output = outputAddress + 2L;
      } else {
         UnsafeUtil.UNSAFE.putByte(outputBase, outputAddress, (byte)-1);
         output = outputAddress + 1L;
         UnsafeUtil.UNSAFE.putShort(outputBase, output, (short)(sequenceCount - 32512));
         output += 2L;
      }

      if (sequenceCount == 0) {
         return (int)(output - outputAddress);
      } else {
         long headerAddress = output++;
         int[] counts = workspace.counts;
         Histogram.count(sequences.literalLengthCodes, sequenceCount, workspace.counts);
         int maxSymbol = Histogram.findMaxSymbol(counts, 35);
         int largestCount = Histogram.findLargestCount(counts, maxSymbol);
         int literalsLengthEncodingType = selectEncodingType(largestCount, sequenceCount, 6, true, strategy);
         FseCompressionTable literalLengthTable;
         switch (literalsLengthEncodingType) {
            case 0:
               literalLengthTable = DEFAULT_LITERAL_LENGTHS_TABLE;
               break;
            case 1:
               UnsafeUtil.UNSAFE.putByte(outputBase, output, sequences.literalLengthCodes[0]);
               ++output;
               workspace.literalLengthTable.initializeRleTable(maxSymbol);
               literalLengthTable = workspace.literalLengthTable;
               break;
            case 2:
               output += (long)buildCompressionTable(workspace.literalLengthTable, outputBase, output, outputLimit, sequenceCount, 9, sequences.literalLengthCodes, workspace.counts, maxSymbol, workspace.normalizedCounts);
               literalLengthTable = workspace.literalLengthTable;
               break;
            default:
               throw new UnsupportedOperationException("not yet implemented");
         }

         Histogram.count(sequences.offsetCodes, sequenceCount, workspace.counts);
         maxSymbol = Histogram.findMaxSymbol(counts, 31);
         largestCount = Histogram.findLargestCount(counts, maxSymbol);
         boolean defaultAllowed = maxSymbol < 28;
         int offsetEncodingType = selectEncodingType(largestCount, sequenceCount, 5, defaultAllowed, strategy);
         FseCompressionTable offsetCodeTable;
         switch (offsetEncodingType) {
            case 0:
               offsetCodeTable = DEFAULT_OFFSETS_TABLE;
               break;
            case 1:
               UnsafeUtil.UNSAFE.putByte(outputBase, output, sequences.offsetCodes[0]);
               ++output;
               workspace.offsetCodeTable.initializeRleTable(maxSymbol);
               offsetCodeTable = workspace.offsetCodeTable;
               break;
            case 2:
               output += (long)buildCompressionTable(workspace.offsetCodeTable, outputBase, output, output + (long)outputSize, sequenceCount, 8, sequences.offsetCodes, workspace.counts, maxSymbol, workspace.normalizedCounts);
               offsetCodeTable = workspace.offsetCodeTable;
               break;
            default:
               throw new UnsupportedOperationException("not yet implemented");
         }

         Histogram.count(sequences.matchLengthCodes, sequenceCount, workspace.counts);
         maxSymbol = Histogram.findMaxSymbol(counts, 52);
         largestCount = Histogram.findLargestCount(counts, maxSymbol);
         int matchLengthEncodingType = selectEncodingType(largestCount, sequenceCount, 6, true, strategy);
         FseCompressionTable matchLengthTable;
         switch (matchLengthEncodingType) {
            case 0:
               matchLengthTable = DEFAULT_MATCH_LENGTHS_TABLE;
               break;
            case 1:
               UnsafeUtil.UNSAFE.putByte(outputBase, output, sequences.matchLengthCodes[0]);
               ++output;
               workspace.matchLengthTable.initializeRleTable(maxSymbol);
               matchLengthTable = workspace.matchLengthTable;
               break;
            case 2:
               output += (long)buildCompressionTable(workspace.matchLengthTable, outputBase, output, outputLimit, sequenceCount, 9, sequences.matchLengthCodes, workspace.counts, maxSymbol, workspace.normalizedCounts);
               matchLengthTable = workspace.matchLengthTable;
               break;
            default:
               throw new UnsupportedOperationException("not yet implemented");
         }

         UnsafeUtil.UNSAFE.putByte(outputBase, headerAddress, (byte)(literalsLengthEncodingType << 6 | offsetEncodingType << 4 | matchLengthEncodingType << 2));
         output += (long)encodeSequences(outputBase, output, outputLimit, matchLengthTable, offsetCodeTable, literalLengthTable, sequences);
         return (int)(output - outputAddress);
      }
   }

   private static int buildCompressionTable(FseCompressionTable table, Object outputBase, long output, long outputLimit, int sequenceCount, int maxTableLog, byte[] codes, int[] counts, int maxSymbol, short[] normalizedCounts) {
      int tableLog = FiniteStateEntropy.optimalTableLog(maxTableLog, sequenceCount, maxSymbol);
      if (counts[codes[sequenceCount - 1]] > 1) {
         --counts[codes[sequenceCount - 1]];
         --sequenceCount;
      }

      FiniteStateEntropy.normalizeCounts(normalizedCounts, tableLog, counts, sequenceCount, maxSymbol);
      table.initialize(normalizedCounts, maxSymbol, tableLog);
      return FiniteStateEntropy.writeNormalizedCounts(outputBase, output, (int)(outputLimit - output), normalizedCounts, maxSymbol, tableLog);
   }

   private static int encodeSequences(Object outputBase, long output, long outputLimit, FseCompressionTable matchLengthTable, FseCompressionTable offsetsTable, FseCompressionTable literalLengthTable, SequenceStore sequences) {
      byte[] matchLengthCodes = sequences.matchLengthCodes;
      byte[] offsetCodes = sequences.offsetCodes;
      byte[] literalLengthCodes = sequences.literalLengthCodes;
      BitOutputStream blockStream = new BitOutputStream(outputBase, output, (int)(outputLimit - output));
      int sequenceCount = sequences.sequenceCount;
      int matchLengthState = matchLengthTable.begin(matchLengthCodes[sequenceCount - 1]);
      int offsetState = offsetsTable.begin(offsetCodes[sequenceCount - 1]);
      int literalLengthState = literalLengthTable.begin(literalLengthCodes[sequenceCount - 1]);
      blockStream.addBits(sequences.literalLengths[sequenceCount - 1], Constants.LITERALS_LENGTH_BITS[literalLengthCodes[sequenceCount - 1]]);
      blockStream.addBits(sequences.matchLengths[sequenceCount - 1], Constants.MATCH_LENGTH_BITS[matchLengthCodes[sequenceCount - 1]]);
      blockStream.addBits(sequences.offsets[sequenceCount - 1], offsetCodes[sequenceCount - 1]);
      blockStream.flush();
      if (sequenceCount >= 2) {
         for(int n = sequenceCount - 2; n >= 0; --n) {
            byte literalLengthCode = literalLengthCodes[n];
            byte offsetCode = offsetCodes[n];
            byte matchLengthCode = matchLengthCodes[n];
            int literalLengthBits = Constants.LITERALS_LENGTH_BITS[literalLengthCode];
            int matchLengthBits = Constants.MATCH_LENGTH_BITS[matchLengthCode];
            offsetState = offsetsTable.encode(blockStream, offsetState, offsetCode);
            matchLengthState = matchLengthTable.encode(blockStream, matchLengthState, matchLengthCode);
            literalLengthState = literalLengthTable.encode(blockStream, literalLengthState, literalLengthCode);
            if (offsetCode + matchLengthBits + literalLengthBits >= 31) {
               blockStream.flush();
            }

            blockStream.addBits(sequences.literalLengths[n], literalLengthBits);
            if (literalLengthBits + matchLengthBits > 24) {
               blockStream.flush();
            }

            blockStream.addBits(sequences.matchLengths[n], matchLengthBits);
            if (offsetCode + matchLengthBits + literalLengthBits > 56) {
               blockStream.flush();
            }

            blockStream.addBits(sequences.offsets[n], offsetCode);
            blockStream.flush();
         }
      }

      matchLengthTable.finish(blockStream, matchLengthState);
      offsetsTable.finish(blockStream, offsetState);
      literalLengthTable.finish(blockStream, literalLengthState);
      int streamSize = blockStream.close();
      Util.checkArgument(streamSize > 0, "Output buffer too small");
      return streamSize;
   }

   private static int selectEncodingType(int largestCount, int sequenceCount, int defaultNormalizedCountsLog, boolean isDefaultTableAllowed, CompressionParameters.Strategy strategy) {
      if (largestCount == sequenceCount) {
         return isDefaultTableAllowed && sequenceCount <= 2 ? 0 : 1;
      } else if (strategy.ordinal() >= CompressionParameters.Strategy.LAZY.ordinal()) {
         throw new UnsupportedOperationException("not yet implemented");
      } else {
         if (isDefaultTableAllowed) {
            int factor = 10 - strategy.ordinal();
            int baseLog = 3;
            long minNumberOfSequences = (1L << defaultNormalizedCountsLog) * (long)factor >> baseLog;
            if ((long)sequenceCount < minNumberOfSequences || largestCount < sequenceCount >> defaultNormalizedCountsLog - 1) {
               return 0;
            }
         }

         return 2;
      }
   }

   static {
      DEFAULT_LITERAL_LENGTHS_TABLE = FseCompressionTable.newInstance(DEFAULT_LITERAL_LENGTH_NORMALIZED_COUNTS, 35, 6);
      DEFAULT_MATCH_LENGTHS_TABLE = FseCompressionTable.newInstance(DEFAULT_MATCH_LENGTH_NORMALIZED_COUNTS, 52, 6);
      DEFAULT_OFFSETS_TABLE = FseCompressionTable.newInstance(DEFAULT_OFFSET_NORMALIZED_COUNTS, 28, 5);
   }
}
