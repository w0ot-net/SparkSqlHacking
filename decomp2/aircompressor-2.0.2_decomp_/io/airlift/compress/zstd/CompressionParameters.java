package io.airlift.compress.zstd;

class CompressionParameters {
   private static final int MIN_HASH_LOG = 6;
   public static final int DEFAULT_COMPRESSION_LEVEL = 3;
   private static final int MAX_COMPRESSION_LEVEL = 22;
   private final int windowLog;
   private final int windowSize;
   private final int blockSize;
   private final int chainLog;
   private final int hashLog;
   private final int searchLog;
   private final int searchLength;
   private final int targetLength;
   private final Strategy strategy;
   private static final CompressionParameters[][] DEFAULT_COMPRESSION_PARAMETERS;

   public CompressionParameters(int windowLog, int chainLog, int hashLog, int searchLog, int searchLength, int targetLength, Strategy strategy) {
      this.windowLog = windowLog;
      this.windowSize = 1 << windowLog;
      this.blockSize = Math.min(131072, this.windowSize);
      this.chainLog = chainLog;
      this.hashLog = hashLog;
      this.searchLog = searchLog;
      this.searchLength = searchLength;
      this.targetLength = targetLength;
      this.strategy = strategy;
   }

   public int getWindowLog() {
      return this.windowLog;
   }

   public int getWindowSize() {
      return this.windowSize;
   }

   public int getBlockSize() {
      return this.blockSize;
   }

   public int getSearchLength() {
      return this.searchLength;
   }

   public int getChainLog() {
      return this.chainLog;
   }

   public int getHashLog() {
      return this.hashLog;
   }

   public int getSearchLog() {
      return this.searchLog;
   }

   public int getTargetLength() {
      return this.targetLength;
   }

   public Strategy getStrategy() {
      return this.strategy;
   }

   public static CompressionParameters compute(int compressionLevel, int estimatedInputSize) {
      CompressionParameters defaultParameters = getDefaultParameters(compressionLevel, (long)estimatedInputSize);
      if (estimatedInputSize < 0) {
         return defaultParameters;
      } else {
         int targetLength = defaultParameters.targetLength;
         int windowLog = defaultParameters.windowLog;
         int chainLog = defaultParameters.chainLog;
         int hashLog = defaultParameters.hashLog;
         int searchLog = defaultParameters.searchLog;
         int searchLength = defaultParameters.searchLength;
         Strategy strategy = defaultParameters.strategy;
         if (compressionLevel < 0) {
            targetLength = -compressionLevel;
         }

         long maxWindowResize = 1073741824L;
         if ((long)estimatedInputSize < maxWindowResize) {
            int hashSizeMin = 64;
            int inputSizeLog = estimatedInputSize < hashSizeMin ? 6 : Util.highestBit(estimatedInputSize - 1) + 1;
            if (windowLog > inputSizeLog) {
               windowLog = inputSizeLog;
            }
         }

         if (hashLog > windowLog + 1) {
            hashLog = windowLog + 1;
         }

         int cycleLog = Util.cycleLog(chainLog, strategy);
         if (cycleLog > windowLog) {
            chainLog -= cycleLog - windowLog;
         }

         if (windowLog < 10) {
            windowLog = 10;
         }

         return new CompressionParameters(windowLog, chainLog, hashLog, searchLog, searchLength, targetLength, strategy);
      }
   }

   private static CompressionParameters getDefaultParameters(int compressionLevel, long estimatedInputSize) {
      int table = 0;
      if (estimatedInputSize >= 0L) {
         if (estimatedInputSize <= 16384L) {
            table = 3;
         } else if (estimatedInputSize <= 131072L) {
            table = 2;
         } else if (estimatedInputSize <= 262144L) {
            table = 1;
         }
      }

      int row = 3;
      if (compressionLevel != 0) {
         row = Math.min(Math.max(0, compressionLevel), 22);
      }

      return DEFAULT_COMPRESSION_PARAMETERS[table][row];
   }

   static {
      DEFAULT_COMPRESSION_PARAMETERS = new CompressionParameters[][]{{new CompressionParameters(19, 12, 13, 1, 6, 1, CompressionParameters.Strategy.FAST), new CompressionParameters(19, 13, 14, 1, 7, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(19, 15, 16, 1, 6, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(20, 16, 17, 1, 5, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(20, 18, 18, 1, 5, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(20, 18, 18, 2, 5, 2, CompressionParameters.Strategy.GREEDY), new CompressionParameters(21, 18, 19, 2, 5, 4, CompressionParameters.Strategy.LAZY), new CompressionParameters(21, 18, 19, 3, 5, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(21, 19, 19, 3, 5, 16, CompressionParameters.Strategy.LAZY2), new CompressionParameters(21, 19, 20, 4, 5, 16, CompressionParameters.Strategy.LAZY2), new CompressionParameters(21, 20, 21, 4, 5, 16, CompressionParameters.Strategy.LAZY2), new CompressionParameters(21, 21, 22, 4, 5, 16, CompressionParameters.Strategy.LAZY2), new CompressionParameters(22, 20, 22, 5, 5, 16, CompressionParameters.Strategy.LAZY2), new CompressionParameters(22, 21, 22, 4, 5, 32, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(22, 21, 22, 5, 5, 32, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(22, 22, 22, 6, 5, 32, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(22, 21, 22, 4, 5, 48, CompressionParameters.Strategy.BTOPT), new CompressionParameters(23, 22, 22, 4, 4, 64, CompressionParameters.Strategy.BTOPT), new CompressionParameters(23, 23, 22, 6, 3, 256, CompressionParameters.Strategy.BTOPT), new CompressionParameters(23, 24, 22, 7, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(25, 25, 23, 7, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(26, 26, 24, 7, 3, 512, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(27, 27, 25, 9, 3, 999, CompressionParameters.Strategy.BTULTRA)}, {new CompressionParameters(18, 12, 13, 1, 5, 1, CompressionParameters.Strategy.FAST), new CompressionParameters(18, 13, 14, 1, 6, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(18, 14, 14, 1, 5, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(18, 16, 16, 1, 4, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(18, 16, 17, 2, 5, 2, CompressionParameters.Strategy.GREEDY), new CompressionParameters(18, 18, 18, 3, 5, 2, CompressionParameters.Strategy.GREEDY), new CompressionParameters(18, 18, 19, 3, 5, 4, CompressionParameters.Strategy.LAZY), new CompressionParameters(18, 18, 19, 4, 4, 4, CompressionParameters.Strategy.LAZY), new CompressionParameters(18, 18, 19, 4, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(18, 18, 19, 5, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(18, 18, 19, 6, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(18, 18, 19, 5, 4, 16, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(18, 19, 19, 6, 4, 16, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(18, 19, 19, 8, 4, 16, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(18, 18, 19, 4, 4, 24, CompressionParameters.Strategy.BTOPT), new CompressionParameters(18, 18, 19, 4, 3, 24, CompressionParameters.Strategy.BTOPT), new CompressionParameters(18, 19, 19, 6, 3, 64, CompressionParameters.Strategy.BTOPT), new CompressionParameters(18, 19, 19, 8, 3, 128, CompressionParameters.Strategy.BTOPT), new CompressionParameters(18, 19, 19, 10, 3, 256, CompressionParameters.Strategy.BTOPT), new CompressionParameters(18, 19, 19, 10, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(18, 19, 19, 11, 3, 512, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(18, 19, 19, 12, 3, 512, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(18, 19, 19, 13, 3, 999, CompressionParameters.Strategy.BTULTRA)}, {new CompressionParameters(17, 12, 12, 1, 5, 1, CompressionParameters.Strategy.FAST), new CompressionParameters(17, 12, 13, 1, 6, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(17, 13, 15, 1, 5, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(17, 15, 16, 2, 5, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(17, 17, 17, 2, 4, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(17, 16, 17, 3, 4, 2, CompressionParameters.Strategy.GREEDY), new CompressionParameters(17, 17, 17, 3, 4, 4, CompressionParameters.Strategy.LAZY), new CompressionParameters(17, 17, 17, 3, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(17, 17, 17, 4, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(17, 17, 17, 5, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(17, 17, 17, 6, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(17, 17, 17, 7, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(17, 18, 17, 6, 4, 16, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(17, 18, 17, 8, 4, 16, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(17, 18, 17, 4, 4, 32, CompressionParameters.Strategy.BTOPT), new CompressionParameters(17, 18, 17, 6, 3, 64, CompressionParameters.Strategy.BTOPT), new CompressionParameters(17, 18, 17, 7, 3, 128, CompressionParameters.Strategy.BTOPT), new CompressionParameters(17, 18, 17, 7, 3, 256, CompressionParameters.Strategy.BTOPT), new CompressionParameters(17, 18, 17, 8, 3, 256, CompressionParameters.Strategy.BTOPT), new CompressionParameters(17, 18, 17, 8, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(17, 18, 17, 9, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(17, 18, 17, 10, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(17, 18, 17, 11, 3, 512, CompressionParameters.Strategy.BTULTRA)}, {new CompressionParameters(14, 12, 13, 1, 5, 1, CompressionParameters.Strategy.FAST), new CompressionParameters(14, 14, 15, 1, 5, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(14, 14, 15, 1, 4, 0, CompressionParameters.Strategy.FAST), new CompressionParameters(14, 14, 14, 2, 4, 1, CompressionParameters.Strategy.DFAST), new CompressionParameters(14, 14, 14, 4, 4, 2, CompressionParameters.Strategy.GREEDY), new CompressionParameters(14, 14, 14, 3, 4, 4, CompressionParameters.Strategy.LAZY), new CompressionParameters(14, 14, 14, 4, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(14, 14, 14, 6, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(14, 14, 14, 8, 4, 8, CompressionParameters.Strategy.LAZY2), new CompressionParameters(14, 15, 14, 5, 4, 8, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(14, 15, 14, 9, 4, 8, CompressionParameters.Strategy.BTLAZY2), new CompressionParameters(14, 15, 14, 3, 4, 12, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 14, 6, 3, 16, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 14, 6, 3, 24, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 6, 3, 48, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 6, 3, 64, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 6, 3, 96, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 6, 3, 128, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 8, 3, 256, CompressionParameters.Strategy.BTOPT), new CompressionParameters(14, 15, 15, 6, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(14, 15, 15, 8, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(14, 15, 15, 9, 3, 256, CompressionParameters.Strategy.BTULTRA), new CompressionParameters(14, 15, 15, 10, 3, 512, CompressionParameters.Strategy.BTULTRA)}};
   }

   public static enum Strategy {
      FAST(BlockCompressor.UNSUPPORTED),
      DFAST(new DoubleFastBlockCompressor()),
      GREEDY(BlockCompressor.UNSUPPORTED),
      LAZY(BlockCompressor.UNSUPPORTED),
      LAZY2(BlockCompressor.UNSUPPORTED),
      BTLAZY2(BlockCompressor.UNSUPPORTED),
      BTOPT(BlockCompressor.UNSUPPORTED),
      BTULTRA(BlockCompressor.UNSUPPORTED);

      private final BlockCompressor compressor;

      private Strategy(BlockCompressor compressor) {
         this.compressor = compressor;
      }

      public BlockCompressor getCompressor() {
         return this.compressor;
      }
   }
}
