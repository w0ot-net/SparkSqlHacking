package org.rocksdb;

public interface AdvancedMutableColumnFamilyOptionsInterface {
   AdvancedMutableColumnFamilyOptionsInterface setMaxWriteBufferNumber(int var1);

   int maxWriteBufferNumber();

   AdvancedMutableColumnFamilyOptionsInterface setInplaceUpdateNumLocks(long var1);

   long inplaceUpdateNumLocks();

   AdvancedMutableColumnFamilyOptionsInterface setMemtablePrefixBloomSizeRatio(double var1);

   double memtablePrefixBloomSizeRatio();

   AdvancedMutableColumnFamilyOptionsInterface setExperimentalMempurgeThreshold(double var1);

   double experimentalMempurgeThreshold();

   AdvancedMutableColumnFamilyOptionsInterface setMemtableWholeKeyFiltering(boolean var1);

   boolean memtableWholeKeyFiltering();

   AdvancedMutableColumnFamilyOptionsInterface setMemtableHugePageSize(long var1);

   long memtableHugePageSize();

   AdvancedMutableColumnFamilyOptionsInterface setArenaBlockSize(long var1);

   long arenaBlockSize();

   AdvancedMutableColumnFamilyOptionsInterface setLevel0SlowdownWritesTrigger(int var1);

   int level0SlowdownWritesTrigger();

   AdvancedMutableColumnFamilyOptionsInterface setLevel0StopWritesTrigger(int var1);

   int level0StopWritesTrigger();

   AdvancedMutableColumnFamilyOptionsInterface setTargetFileSizeBase(long var1);

   long targetFileSizeBase();

   AdvancedMutableColumnFamilyOptionsInterface setTargetFileSizeMultiplier(int var1);

   int targetFileSizeMultiplier();

   AdvancedMutableColumnFamilyOptionsInterface setMaxBytesForLevelMultiplier(double var1);

   double maxBytesForLevelMultiplier();

   AdvancedMutableColumnFamilyOptionsInterface setMaxBytesForLevelMultiplierAdditional(int[] var1);

   int[] maxBytesForLevelMultiplierAdditional();

   AdvancedMutableColumnFamilyOptionsInterface setSoftPendingCompactionBytesLimit(long var1);

   long softPendingCompactionBytesLimit();

   AdvancedMutableColumnFamilyOptionsInterface setHardPendingCompactionBytesLimit(long var1);

   long hardPendingCompactionBytesLimit();

   AdvancedMutableColumnFamilyOptionsInterface setMaxSequentialSkipInIterations(long var1);

   long maxSequentialSkipInIterations();

   AdvancedMutableColumnFamilyOptionsInterface setMaxSuccessiveMerges(long var1);

   long maxSuccessiveMerges();

   AdvancedMutableColumnFamilyOptionsInterface setParanoidFileChecks(boolean var1);

   boolean paranoidFileChecks();

   AdvancedMutableColumnFamilyOptionsInterface setReportBgIoStats(boolean var1);

   boolean reportBgIoStats();

   AdvancedMutableColumnFamilyOptionsInterface setTtl(long var1);

   long ttl();

   AdvancedMutableColumnFamilyOptionsInterface setPeriodicCompactionSeconds(long var1);

   long periodicCompactionSeconds();

   AdvancedMutableColumnFamilyOptionsInterface setEnableBlobFiles(boolean var1);

   boolean enableBlobFiles();

   AdvancedMutableColumnFamilyOptionsInterface setMinBlobSize(long var1);

   long minBlobSize();

   AdvancedMutableColumnFamilyOptionsInterface setBlobFileSize(long var1);

   long blobFileSize();

   AdvancedMutableColumnFamilyOptionsInterface setBlobCompressionType(CompressionType var1);

   CompressionType blobCompressionType();

   AdvancedMutableColumnFamilyOptionsInterface setEnableBlobGarbageCollection(boolean var1);

   boolean enableBlobGarbageCollection();

   AdvancedMutableColumnFamilyOptionsInterface setBlobGarbageCollectionAgeCutoff(double var1);

   double blobGarbageCollectionAgeCutoff();

   AdvancedMutableColumnFamilyOptionsInterface setBlobGarbageCollectionForceThreshold(double var1);

   double blobGarbageCollectionForceThreshold();

   AdvancedMutableColumnFamilyOptionsInterface setBlobCompactionReadaheadSize(long var1);

   long blobCompactionReadaheadSize();

   AdvancedMutableColumnFamilyOptionsInterface setBlobFileStartingLevel(int var1);

   int blobFileStartingLevel();

   AdvancedMutableColumnFamilyOptionsInterface setPrepopulateBlobCache(PrepopulateBlobCache var1);

   PrepopulateBlobCache prepopulateBlobCache();
}
