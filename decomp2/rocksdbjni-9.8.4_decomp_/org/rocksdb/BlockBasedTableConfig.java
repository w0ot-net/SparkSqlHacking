package org.rocksdb;

public class BlockBasedTableConfig extends TableFormatConfig {
   private boolean cacheIndexAndFilterBlocks;
   private boolean cacheIndexAndFilterBlocksWithHighPriority;
   private boolean pinL0FilterAndIndexBlocksInCache;
   private boolean pinTopLevelIndexAndFilter;
   private IndexType indexType;
   private DataBlockIndexType dataBlockIndexType;
   private double dataBlockHashTableUtilRatio;
   private ChecksumType checksumType;
   private boolean noBlockCache;
   private Cache blockCache;
   private PersistentCache persistentCache;
   private long blockSize;
   private int blockSizeDeviation;
   private int blockRestartInterval;
   private int indexBlockRestartInterval;
   private long metadataBlockSize;
   private boolean partitionFilters;
   private boolean optimizeFiltersForMemory;
   private boolean useDeltaEncoding;
   private Filter filterPolicy;
   private boolean wholeKeyFiltering;
   private boolean verifyCompression;
   private int readAmpBytesPerBit;
   private int formatVersion;
   private boolean enableIndexCompression;
   private boolean blockAlign;
   private IndexShorteningMode indexShortening;
   /** @deprecated */
   @Deprecated
   private long blockCacheSize;
   /** @deprecated */
   @Deprecated
   private int blockCacheNumShardBits;

   public BlockBasedTableConfig() {
      this.cacheIndexAndFilterBlocks = false;
      this.cacheIndexAndFilterBlocksWithHighPriority = true;
      this.pinL0FilterAndIndexBlocksInCache = false;
      this.pinTopLevelIndexAndFilter = true;
      this.indexType = IndexType.kBinarySearch;
      this.dataBlockIndexType = DataBlockIndexType.kDataBlockBinarySearch;
      this.dataBlockHashTableUtilRatio = (double)0.75F;
      this.checksumType = ChecksumType.kXXH3;
      this.noBlockCache = false;
      this.blockCache = null;
      this.persistentCache = null;
      this.blockSize = 4096L;
      this.blockSizeDeviation = 10;
      this.blockRestartInterval = 16;
      this.indexBlockRestartInterval = 1;
      this.metadataBlockSize = 4096L;
      this.partitionFilters = false;
      this.optimizeFiltersForMemory = true;
      this.useDeltaEncoding = true;
      this.filterPolicy = null;
      this.wholeKeyFiltering = true;
      this.verifyCompression = false;
      this.readAmpBytesPerBit = 0;
      this.formatVersion = 6;
      this.enableIndexCompression = true;
      this.blockAlign = false;
      this.indexShortening = IndexShorteningMode.kShortenSeparators;
      this.blockCacheSize = 8388608L;
      this.blockCacheNumShardBits = 0;
   }

   private BlockBasedTableConfig(boolean var1, boolean var2, boolean var3, boolean var4, byte var5, byte var6, double var7, byte var9, boolean var10, long var11, int var13, int var14, int var15, long var16, boolean var18, boolean var19, boolean var20, boolean var21, boolean var22, int var23, int var24, boolean var25, boolean var26, byte var27, byte var28, long var29, double var31) {
      this.cacheIndexAndFilterBlocks = var1;
      this.cacheIndexAndFilterBlocksWithHighPriority = var2;
      this.pinL0FilterAndIndexBlocksInCache = var3;
      this.pinTopLevelIndexAndFilter = var4;
      this.indexType = IndexType.values()[var5];
      this.dataBlockIndexType = DataBlockIndexType.values()[var6];
      this.dataBlockHashTableUtilRatio = var7;
      this.checksumType = ChecksumType.values()[var9];
      this.noBlockCache = var10;
      this.blockSize = var11;
      this.blockSizeDeviation = var13;
      this.blockRestartInterval = var14;
      this.indexBlockRestartInterval = var15;
      this.metadataBlockSize = var16;
      this.partitionFilters = var18;
      this.optimizeFiltersForMemory = var19;
      this.useDeltaEncoding = var20;
      this.wholeKeyFiltering = var21;
      this.verifyCompression = var22;
      this.readAmpBytesPerBit = var23;
      this.formatVersion = var24;
      this.enableIndexCompression = var25;
      this.blockAlign = var26;
      this.indexShortening = IndexShorteningMode.values()[var27];

      try (Filter var33 = FilterPolicyType.values()[var28].createFilter(var29, var31)) {
         if (var33 != null) {
            var33.disOwnNativeHandle();
            this.setFilterPolicy(var33);
         }
      }

   }

   public boolean cacheIndexAndFilterBlocks() {
      return this.cacheIndexAndFilterBlocks;
   }

   public BlockBasedTableConfig setCacheIndexAndFilterBlocks(boolean var1) {
      this.cacheIndexAndFilterBlocks = var1;
      return this;
   }

   public boolean cacheIndexAndFilterBlocksWithHighPriority() {
      return this.cacheIndexAndFilterBlocksWithHighPriority;
   }

   public BlockBasedTableConfig setCacheIndexAndFilterBlocksWithHighPriority(boolean var1) {
      this.cacheIndexAndFilterBlocksWithHighPriority = var1;
      return this;
   }

   public boolean pinL0FilterAndIndexBlocksInCache() {
      return this.pinL0FilterAndIndexBlocksInCache;
   }

   public BlockBasedTableConfig setPinL0FilterAndIndexBlocksInCache(boolean var1) {
      this.pinL0FilterAndIndexBlocksInCache = var1;
      return this;
   }

   public boolean pinTopLevelIndexAndFilter() {
      return this.pinTopLevelIndexAndFilter;
   }

   public BlockBasedTableConfig setPinTopLevelIndexAndFilter(boolean var1) {
      this.pinTopLevelIndexAndFilter = var1;
      return this;
   }

   public IndexType indexType() {
      return this.indexType;
   }

   public BlockBasedTableConfig setIndexType(IndexType var1) {
      this.indexType = var1;
      return this;
   }

   public DataBlockIndexType dataBlockIndexType() {
      return this.dataBlockIndexType;
   }

   public BlockBasedTableConfig setDataBlockIndexType(DataBlockIndexType var1) {
      this.dataBlockIndexType = var1;
      return this;
   }

   public double dataBlockHashTableUtilRatio() {
      return this.dataBlockHashTableUtilRatio;
   }

   public BlockBasedTableConfig setDataBlockHashTableUtilRatio(double var1) {
      this.dataBlockHashTableUtilRatio = var1;
      return this;
   }

   public ChecksumType checksumType() {
      return this.checksumType;
   }

   public BlockBasedTableConfig setChecksumType(ChecksumType var1) {
      this.checksumType = var1;
      return this;
   }

   public boolean noBlockCache() {
      return this.noBlockCache;
   }

   public BlockBasedTableConfig setNoBlockCache(boolean var1) {
      this.noBlockCache = var1;
      return this;
   }

   public BlockBasedTableConfig setBlockCache(Cache var1) {
      this.blockCache = var1;
      return this;
   }

   public BlockBasedTableConfig setPersistentCache(PersistentCache var1) {
      this.persistentCache = var1;
      return this;
   }

   public long blockSize() {
      return this.blockSize;
   }

   public BlockBasedTableConfig setBlockSize(long var1) {
      this.blockSize = var1;
      return this;
   }

   public int blockSizeDeviation() {
      return this.blockSizeDeviation;
   }

   public BlockBasedTableConfig setBlockSizeDeviation(int var1) {
      this.blockSizeDeviation = var1;
      return this;
   }

   public int blockRestartInterval() {
      return this.blockRestartInterval;
   }

   public BlockBasedTableConfig setBlockRestartInterval(int var1) {
      this.blockRestartInterval = var1;
      return this;
   }

   public int indexBlockRestartInterval() {
      return this.indexBlockRestartInterval;
   }

   public BlockBasedTableConfig setIndexBlockRestartInterval(int var1) {
      this.indexBlockRestartInterval = var1;
      return this;
   }

   public long metadataBlockSize() {
      return this.metadataBlockSize;
   }

   public BlockBasedTableConfig setMetadataBlockSize(long var1) {
      this.metadataBlockSize = var1;
      return this;
   }

   public boolean partitionFilters() {
      return this.partitionFilters;
   }

   public BlockBasedTableConfig setPartitionFilters(boolean var1) {
      this.partitionFilters = var1;
      return this;
   }

   public boolean optimizeFiltersForMemory() {
      return this.optimizeFiltersForMemory;
   }

   public BlockBasedTableConfig setOptimizeFiltersForMemory(boolean var1) {
      this.optimizeFiltersForMemory = var1;
      return this;
   }

   public boolean useDeltaEncoding() {
      return this.useDeltaEncoding;
   }

   public BlockBasedTableConfig setUseDeltaEncoding(boolean var1) {
      this.useDeltaEncoding = var1;
      return this;
   }

   public Filter filterPolicy() {
      return this.filterPolicy;
   }

   public BlockBasedTableConfig setFilterPolicy(Filter var1) {
      this.filterPolicy = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public BlockBasedTableConfig setFilter(Filter var1) {
      return this.setFilterPolicy(var1);
   }

   public boolean wholeKeyFiltering() {
      return this.wholeKeyFiltering;
   }

   public BlockBasedTableConfig setWholeKeyFiltering(boolean var1) {
      this.wholeKeyFiltering = var1;
      return this;
   }

   public boolean verifyCompression() {
      return this.verifyCompression;
   }

   public BlockBasedTableConfig setVerifyCompression(boolean var1) {
      this.verifyCompression = var1;
      return this;
   }

   public int readAmpBytesPerBit() {
      return this.readAmpBytesPerBit;
   }

   public BlockBasedTableConfig setReadAmpBytesPerBit(int var1) {
      this.readAmpBytesPerBit = var1;
      return this;
   }

   public int formatVersion() {
      return this.formatVersion;
   }

   public BlockBasedTableConfig setFormatVersion(int var1) {
      assert var1 >= 0;

      this.formatVersion = var1;
      return this;
   }

   public boolean enableIndexCompression() {
      return this.enableIndexCompression;
   }

   public BlockBasedTableConfig setEnableIndexCompression(boolean var1) {
      this.enableIndexCompression = var1;
      return this;
   }

   public boolean blockAlign() {
      return this.blockAlign;
   }

   public BlockBasedTableConfig setBlockAlign(boolean var1) {
      this.blockAlign = var1;
      return this;
   }

   public IndexShorteningMode indexShortening() {
      return this.indexShortening;
   }

   public BlockBasedTableConfig setIndexShortening(IndexShorteningMode var1) {
      this.indexShortening = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public long blockCacheSize() {
      return this.blockCacheSize;
   }

   /** @deprecated */
   @Deprecated
   public BlockBasedTableConfig setBlockCacheSize(long var1) {
      this.blockCacheSize = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public int cacheNumShardBits() {
      return this.blockCacheNumShardBits;
   }

   /** @deprecated */
   @Deprecated
   public BlockBasedTableConfig setCacheNumShardBits(int var1) {
      this.blockCacheNumShardBits = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public boolean hashIndexAllowCollision() {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public BlockBasedTableConfig setHashIndexAllowCollision(boolean var1) {
      return this;
   }

   protected long newTableFactoryHandle() {
      long var1;
      if (this.filterPolicy != null) {
         var1 = this.filterPolicy.nativeHandle_;
      } else {
         var1 = 0L;
      }

      long var3;
      if (this.blockCache != null) {
         var3 = this.blockCache.nativeHandle_;
      } else {
         var3 = 0L;
      }

      long var5;
      if (this.persistentCache != null) {
         var5 = this.persistentCache.nativeHandle_;
      } else {
         var5 = 0L;
      }

      return newTableFactoryHandle(this.cacheIndexAndFilterBlocks, this.cacheIndexAndFilterBlocksWithHighPriority, this.pinL0FilterAndIndexBlocksInCache, this.pinTopLevelIndexAndFilter, this.indexType.getValue(), this.dataBlockIndexType.getValue(), this.dataBlockHashTableUtilRatio, this.checksumType.getValue(), this.noBlockCache, var3, var5, this.blockSize, this.blockSizeDeviation, this.blockRestartInterval, this.indexBlockRestartInterval, this.metadataBlockSize, this.partitionFilters, this.optimizeFiltersForMemory, this.useDeltaEncoding, var1, this.wholeKeyFiltering, this.verifyCompression, this.readAmpBytesPerBit, this.formatVersion, this.enableIndexCompression, this.blockAlign, this.indexShortening.getValue(), this.blockCacheSize, this.blockCacheNumShardBits);
   }

   private static native long newTableFactoryHandle(boolean var0, boolean var1, boolean var2, boolean var3, byte var4, byte var5, double var6, byte var8, boolean var9, long var10, long var12, long var14, int var16, int var17, int var18, long var19, boolean var21, boolean var22, boolean var23, long var24, boolean var26, boolean var27, int var28, int var29, boolean var30, boolean var31, byte var32, @Deprecated long var33, @Deprecated int var35);
}
