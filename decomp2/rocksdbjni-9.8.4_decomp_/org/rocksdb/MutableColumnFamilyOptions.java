package org.rocksdb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MutableColumnFamilyOptions extends AbstractMutableOptions {
   private MutableColumnFamilyOptions(String[] var1, String[] var2) {
      super(var1, var2);
   }

   public static MutableColumnFamilyOptionsBuilder builder() {
      return new MutableColumnFamilyOptionsBuilder();
   }

   public static MutableColumnFamilyOptionsBuilder parse(String var0, boolean var1) {
      Objects.requireNonNull(var0);
      List var2 = OptionString.Parser.parse(var0);
      return (MutableColumnFamilyOptionsBuilder)(new MutableColumnFamilyOptionsBuilder()).fromParsed(var2, var1);
   }

   public static MutableColumnFamilyOptionsBuilder parse(String var0) {
      return parse(var0, false);
   }

   public static enum MemtableOption implements MutableColumnFamilyOptionKey {
      write_buffer_size(MutableOptionKey.ValueType.LONG),
      arena_block_size(MutableOptionKey.ValueType.LONG),
      memtable_prefix_bloom_size_ratio(MutableOptionKey.ValueType.DOUBLE),
      memtable_whole_key_filtering(MutableOptionKey.ValueType.BOOLEAN),
      /** @deprecated */
      @Deprecated
      memtable_prefix_bloom_bits(MutableOptionKey.ValueType.INT),
      /** @deprecated */
      @Deprecated
      memtable_prefix_bloom_probes(MutableOptionKey.ValueType.INT),
      memtable_huge_page_size(MutableOptionKey.ValueType.LONG),
      max_successive_merges(MutableOptionKey.ValueType.LONG),
      /** @deprecated */
      @Deprecated
      filter_deletes(MutableOptionKey.ValueType.BOOLEAN),
      max_write_buffer_number(MutableOptionKey.ValueType.INT),
      inplace_update_num_locks(MutableOptionKey.ValueType.LONG),
      experimental_mempurge_threshold(MutableOptionKey.ValueType.DOUBLE);

      private final MutableOptionKey.ValueType valueType;

      private MemtableOption(MutableOptionKey.ValueType var3) {
         this.valueType = var3;
      }

      public MutableOptionKey.ValueType getValueType() {
         return this.valueType;
      }
   }

   public static enum CompactionOption implements MutableColumnFamilyOptionKey {
      disable_auto_compactions(MutableOptionKey.ValueType.BOOLEAN),
      soft_pending_compaction_bytes_limit(MutableOptionKey.ValueType.LONG),
      hard_pending_compaction_bytes_limit(MutableOptionKey.ValueType.LONG),
      level0_file_num_compaction_trigger(MutableOptionKey.ValueType.INT),
      level0_slowdown_writes_trigger(MutableOptionKey.ValueType.INT),
      level0_stop_writes_trigger(MutableOptionKey.ValueType.INT),
      max_compaction_bytes(MutableOptionKey.ValueType.LONG),
      target_file_size_base(MutableOptionKey.ValueType.LONG),
      target_file_size_multiplier(MutableOptionKey.ValueType.INT),
      max_bytes_for_level_base(MutableOptionKey.ValueType.LONG),
      max_bytes_for_level_multiplier(MutableOptionKey.ValueType.INT),
      max_bytes_for_level_multiplier_additional(MutableOptionKey.ValueType.INT_ARRAY),
      ttl(MutableOptionKey.ValueType.LONG),
      periodic_compaction_seconds(MutableOptionKey.ValueType.LONG);

      private final MutableOptionKey.ValueType valueType;

      private CompactionOption(MutableOptionKey.ValueType var3) {
         this.valueType = var3;
      }

      public MutableOptionKey.ValueType getValueType() {
         return this.valueType;
      }
   }

   public static enum BlobOption implements MutableColumnFamilyOptionKey {
      enable_blob_files(MutableOptionKey.ValueType.BOOLEAN),
      min_blob_size(MutableOptionKey.ValueType.LONG),
      blob_file_size(MutableOptionKey.ValueType.LONG),
      blob_compression_type(MutableOptionKey.ValueType.ENUM),
      enable_blob_garbage_collection(MutableOptionKey.ValueType.BOOLEAN),
      blob_garbage_collection_age_cutoff(MutableOptionKey.ValueType.DOUBLE),
      blob_garbage_collection_force_threshold(MutableOptionKey.ValueType.DOUBLE),
      blob_compaction_readahead_size(MutableOptionKey.ValueType.LONG),
      blob_file_starting_level(MutableOptionKey.ValueType.INT),
      prepopulate_blob_cache(MutableOptionKey.ValueType.ENUM);

      private final MutableOptionKey.ValueType valueType;

      private BlobOption(MutableOptionKey.ValueType var3) {
         this.valueType = var3;
      }

      public MutableOptionKey.ValueType getValueType() {
         return this.valueType;
      }
   }

   public static enum MiscOption implements MutableColumnFamilyOptionKey {
      max_sequential_skip_in_iterations(MutableOptionKey.ValueType.LONG),
      paranoid_file_checks(MutableOptionKey.ValueType.BOOLEAN),
      report_bg_io_stats(MutableOptionKey.ValueType.BOOLEAN),
      compression(MutableOptionKey.ValueType.ENUM);

      private final MutableOptionKey.ValueType valueType;

      private MiscOption(MutableOptionKey.ValueType var3) {
         this.valueType = var3;
      }

      public MutableOptionKey.ValueType getValueType() {
         return this.valueType;
      }
   }

   public static class MutableColumnFamilyOptionsBuilder extends AbstractMutableOptions.AbstractMutableOptionsBuilder implements MutableColumnFamilyOptionsInterface {
      private static final Map ALL_KEYS_LOOKUP = new HashMap();

      private MutableColumnFamilyOptionsBuilder() {
      }

      protected MutableColumnFamilyOptionsBuilder self() {
         return this;
      }

      protected Map allKeys() {
         return ALL_KEYS_LOOKUP;
      }

      protected MutableColumnFamilyOptions build(String[] var1, String[] var2) {
         return new MutableColumnFamilyOptions(var1, var2);
      }

      public MutableColumnFamilyOptionsBuilder setWriteBufferSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MemtableOption.write_buffer_size, var1);
      }

      public long writeBufferSize() {
         return this.getLong(MutableColumnFamilyOptions.MemtableOption.write_buffer_size);
      }

      public MutableColumnFamilyOptionsBuilder setArenaBlockSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MemtableOption.arena_block_size, var1);
      }

      public long arenaBlockSize() {
         return this.getLong(MutableColumnFamilyOptions.MemtableOption.arena_block_size);
      }

      public MutableColumnFamilyOptionsBuilder setMemtablePrefixBloomSizeRatio(double var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setDouble(MutableColumnFamilyOptions.MemtableOption.memtable_prefix_bloom_size_ratio, var1);
      }

      public double memtablePrefixBloomSizeRatio() {
         return this.getDouble(MutableColumnFamilyOptions.MemtableOption.memtable_prefix_bloom_size_ratio);
      }

      public MutableColumnFamilyOptionsBuilder setMemtableWholeKeyFiltering(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.MemtableOption.memtable_whole_key_filtering, var1);
      }

      public boolean memtableWholeKeyFiltering() {
         return this.getBoolean(MutableColumnFamilyOptions.MemtableOption.memtable_whole_key_filtering);
      }

      public MutableColumnFamilyOptionsBuilder setMemtableHugePageSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MemtableOption.memtable_huge_page_size, var1);
      }

      public long memtableHugePageSize() {
         return this.getLong(MutableColumnFamilyOptions.MemtableOption.memtable_huge_page_size);
      }

      public MutableColumnFamilyOptionsBuilder setMaxSuccessiveMerges(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MemtableOption.max_successive_merges, var1);
      }

      public long maxSuccessiveMerges() {
         return this.getLong(MutableColumnFamilyOptions.MemtableOption.max_successive_merges);
      }

      public MutableColumnFamilyOptionsBuilder setMaxWriteBufferNumber(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.MemtableOption.max_write_buffer_number, var1);
      }

      public int maxWriteBufferNumber() {
         return this.getInt(MutableColumnFamilyOptions.MemtableOption.max_write_buffer_number);
      }

      public MutableColumnFamilyOptionsBuilder setInplaceUpdateNumLocks(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MemtableOption.inplace_update_num_locks, var1);
      }

      public long inplaceUpdateNumLocks() {
         return this.getLong(MutableColumnFamilyOptions.MemtableOption.inplace_update_num_locks);
      }

      public MutableColumnFamilyOptionsBuilder setExperimentalMempurgeThreshold(double var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setDouble(MutableColumnFamilyOptions.MemtableOption.experimental_mempurge_threshold, var1);
      }

      public double experimentalMempurgeThreshold() {
         return this.getDouble(MutableColumnFamilyOptions.MemtableOption.experimental_mempurge_threshold);
      }

      public MutableColumnFamilyOptionsBuilder setDisableAutoCompactions(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.CompactionOption.disable_auto_compactions, var1);
      }

      public boolean disableAutoCompactions() {
         return this.getBoolean(MutableColumnFamilyOptions.CompactionOption.disable_auto_compactions);
      }

      public MutableColumnFamilyOptionsBuilder setSoftPendingCompactionBytesLimit(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.soft_pending_compaction_bytes_limit, var1);
      }

      public long softPendingCompactionBytesLimit() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.soft_pending_compaction_bytes_limit);
      }

      public MutableColumnFamilyOptionsBuilder setHardPendingCompactionBytesLimit(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.hard_pending_compaction_bytes_limit, var1);
      }

      public long hardPendingCompactionBytesLimit() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.hard_pending_compaction_bytes_limit);
      }

      public MutableColumnFamilyOptionsBuilder setLevel0FileNumCompactionTrigger(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.CompactionOption.level0_file_num_compaction_trigger, var1);
      }

      public int level0FileNumCompactionTrigger() {
         return this.getInt(MutableColumnFamilyOptions.CompactionOption.level0_file_num_compaction_trigger);
      }

      public MutableColumnFamilyOptionsBuilder setLevel0SlowdownWritesTrigger(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.CompactionOption.level0_slowdown_writes_trigger, var1);
      }

      public int level0SlowdownWritesTrigger() {
         return this.getInt(MutableColumnFamilyOptions.CompactionOption.level0_slowdown_writes_trigger);
      }

      public MutableColumnFamilyOptionsBuilder setLevel0StopWritesTrigger(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.CompactionOption.level0_stop_writes_trigger, var1);
      }

      public int level0StopWritesTrigger() {
         return this.getInt(MutableColumnFamilyOptions.CompactionOption.level0_stop_writes_trigger);
      }

      public MutableColumnFamilyOptionsBuilder setMaxCompactionBytes(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.max_compaction_bytes, var1);
      }

      public long maxCompactionBytes() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.max_compaction_bytes);
      }

      public MutableColumnFamilyOptionsBuilder setTargetFileSizeBase(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.target_file_size_base, var1);
      }

      public long targetFileSizeBase() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.target_file_size_base);
      }

      public MutableColumnFamilyOptionsBuilder setTargetFileSizeMultiplier(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.CompactionOption.target_file_size_multiplier, var1);
      }

      public int targetFileSizeMultiplier() {
         return this.getInt(MutableColumnFamilyOptions.CompactionOption.target_file_size_multiplier);
      }

      public MutableColumnFamilyOptionsBuilder setMaxBytesForLevelBase(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_base, var1);
      }

      public long maxBytesForLevelBase() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_base);
      }

      public MutableColumnFamilyOptionsBuilder setMaxBytesForLevelMultiplier(double var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setDouble(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_multiplier, var1);
      }

      public double maxBytesForLevelMultiplier() {
         return this.getDouble(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_multiplier);
      }

      public MutableColumnFamilyOptionsBuilder setMaxBytesForLevelMultiplierAdditional(int[] var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setIntArray(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_multiplier_additional, var1);
      }

      public int[] maxBytesForLevelMultiplierAdditional() {
         return this.getIntArray(MutableColumnFamilyOptions.CompactionOption.max_bytes_for_level_multiplier_additional);
      }

      public MutableColumnFamilyOptionsBuilder setMaxSequentialSkipInIterations(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.MiscOption.max_sequential_skip_in_iterations, var1);
      }

      public long maxSequentialSkipInIterations() {
         return this.getLong(MutableColumnFamilyOptions.MiscOption.max_sequential_skip_in_iterations);
      }

      public MutableColumnFamilyOptionsBuilder setParanoidFileChecks(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.MiscOption.paranoid_file_checks, var1);
      }

      public boolean paranoidFileChecks() {
         return this.getBoolean(MutableColumnFamilyOptions.MiscOption.paranoid_file_checks);
      }

      public MutableColumnFamilyOptionsBuilder setCompressionType(CompressionType var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setEnum(MutableColumnFamilyOptions.MiscOption.compression, var1);
      }

      public CompressionType compressionType() {
         return (CompressionType)this.getEnum(MutableColumnFamilyOptions.MiscOption.compression);
      }

      public MutableColumnFamilyOptionsBuilder setReportBgIoStats(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.MiscOption.report_bg_io_stats, var1);
      }

      public boolean reportBgIoStats() {
         return this.getBoolean(MutableColumnFamilyOptions.MiscOption.report_bg_io_stats);
      }

      public MutableColumnFamilyOptionsBuilder setTtl(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.ttl, var1);
      }

      public long ttl() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.ttl);
      }

      public MutableColumnFamilyOptionsBuilder setPeriodicCompactionSeconds(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.CompactionOption.periodic_compaction_seconds, var1);
      }

      public long periodicCompactionSeconds() {
         return this.getLong(MutableColumnFamilyOptions.CompactionOption.periodic_compaction_seconds);
      }

      public MutableColumnFamilyOptionsBuilder setEnableBlobFiles(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.BlobOption.enable_blob_files, var1);
      }

      public boolean enableBlobFiles() {
         return this.getBoolean(MutableColumnFamilyOptions.BlobOption.enable_blob_files);
      }

      public MutableColumnFamilyOptionsBuilder setMinBlobSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.BlobOption.min_blob_size, var1);
      }

      public long minBlobSize() {
         return this.getLong(MutableColumnFamilyOptions.BlobOption.min_blob_size);
      }

      public MutableColumnFamilyOptionsBuilder setBlobFileSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.BlobOption.blob_file_size, var1);
      }

      public long blobFileSize() {
         return this.getLong(MutableColumnFamilyOptions.BlobOption.blob_file_size);
      }

      public MutableColumnFamilyOptionsBuilder setBlobCompressionType(CompressionType var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setEnum(MutableColumnFamilyOptions.BlobOption.blob_compression_type, var1);
      }

      public CompressionType blobCompressionType() {
         return (CompressionType)this.getEnum(MutableColumnFamilyOptions.BlobOption.blob_compression_type);
      }

      public MutableColumnFamilyOptionsBuilder setEnableBlobGarbageCollection(boolean var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setBoolean(MutableColumnFamilyOptions.BlobOption.enable_blob_garbage_collection, var1);
      }

      public boolean enableBlobGarbageCollection() {
         return this.getBoolean(MutableColumnFamilyOptions.BlobOption.enable_blob_garbage_collection);
      }

      public MutableColumnFamilyOptionsBuilder setBlobGarbageCollectionAgeCutoff(double var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setDouble(MutableColumnFamilyOptions.BlobOption.blob_garbage_collection_age_cutoff, var1);
      }

      public double blobGarbageCollectionAgeCutoff() {
         return this.getDouble(MutableColumnFamilyOptions.BlobOption.blob_garbage_collection_age_cutoff);
      }

      public MutableColumnFamilyOptionsBuilder setBlobGarbageCollectionForceThreshold(double var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setDouble(MutableColumnFamilyOptions.BlobOption.blob_garbage_collection_force_threshold, var1);
      }

      public double blobGarbageCollectionForceThreshold() {
         return this.getDouble(MutableColumnFamilyOptions.BlobOption.blob_garbage_collection_force_threshold);
      }

      public MutableColumnFamilyOptionsBuilder setBlobCompactionReadaheadSize(long var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setLong(MutableColumnFamilyOptions.BlobOption.blob_compaction_readahead_size, var1);
      }

      public long blobCompactionReadaheadSize() {
         return this.getLong(MutableColumnFamilyOptions.BlobOption.blob_compaction_readahead_size);
      }

      public MutableColumnFamilyOptionsBuilder setBlobFileStartingLevel(int var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setInt(MutableColumnFamilyOptions.BlobOption.blob_file_starting_level, var1);
      }

      public int blobFileStartingLevel() {
         return this.getInt(MutableColumnFamilyOptions.BlobOption.blob_file_starting_level);
      }

      public MutableColumnFamilyOptionsBuilder setPrepopulateBlobCache(PrepopulateBlobCache var1) {
         return (MutableColumnFamilyOptionsBuilder)this.setEnum(MutableColumnFamilyOptions.BlobOption.prepopulate_blob_cache, var1);
      }

      public PrepopulateBlobCache prepopulateBlobCache() {
         return (PrepopulateBlobCache)this.getEnum(MutableColumnFamilyOptions.BlobOption.prepopulate_blob_cache);
      }

      static {
         for(MemtableOption var3 : MutableColumnFamilyOptions.MemtableOption.values()) {
            ALL_KEYS_LOOKUP.put(var3.name(), var3);
         }

         for(CompactionOption var13 : MutableColumnFamilyOptions.CompactionOption.values()) {
            ALL_KEYS_LOOKUP.put(var13.name(), var13);
         }

         for(MiscOption var14 : MutableColumnFamilyOptions.MiscOption.values()) {
            ALL_KEYS_LOOKUP.put(var14.name(), var14);
         }

         for(BlobOption var15 : MutableColumnFamilyOptions.BlobOption.values()) {
            ALL_KEYS_LOOKUP.put(var15.name(), var15);
         }

      }
   }

   private interface MutableColumnFamilyOptionKey extends MutableOptionKey {
   }
}
