package org.rocksdb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MutableDBOptions extends AbstractMutableOptions {
   private MutableDBOptions(String[] var1, String[] var2) {
      super(var1, var2);
   }

   public static MutableDBOptionsBuilder builder() {
      return new MutableDBOptionsBuilder();
   }

   public static MutableDBOptionsBuilder parse(String var0, boolean var1) {
      Objects.requireNonNull(var0);
      List var2 = OptionString.Parser.parse(var0);
      return (MutableDBOptionsBuilder)(new MutableDBOptionsBuilder()).fromParsed(var2, var1);
   }

   public static MutableDBOptionsBuilder parse(String var0) {
      return parse(var0, false);
   }

   public static enum DBOption implements MutableDBOptionKey {
      max_background_jobs(MutableOptionKey.ValueType.INT),
      max_background_compactions(MutableOptionKey.ValueType.INT),
      avoid_flush_during_shutdown(MutableOptionKey.ValueType.BOOLEAN),
      writable_file_max_buffer_size(MutableOptionKey.ValueType.LONG),
      delayed_write_rate(MutableOptionKey.ValueType.LONG),
      max_total_wal_size(MutableOptionKey.ValueType.LONG),
      delete_obsolete_files_period_micros(MutableOptionKey.ValueType.LONG),
      stats_dump_period_sec(MutableOptionKey.ValueType.INT),
      stats_persist_period_sec(MutableOptionKey.ValueType.INT),
      stats_history_buffer_size(MutableOptionKey.ValueType.LONG),
      max_open_files(MutableOptionKey.ValueType.INT),
      bytes_per_sync(MutableOptionKey.ValueType.LONG),
      wal_bytes_per_sync(MutableOptionKey.ValueType.LONG),
      strict_bytes_per_sync(MutableOptionKey.ValueType.BOOLEAN),
      compaction_readahead_size(MutableOptionKey.ValueType.LONG);

      private final MutableOptionKey.ValueType valueType;

      private DBOption(MutableOptionKey.ValueType var3) {
         this.valueType = var3;
      }

      public MutableOptionKey.ValueType getValueType() {
         return this.valueType;
      }
   }

   public static class MutableDBOptionsBuilder extends AbstractMutableOptions.AbstractMutableOptionsBuilder implements MutableDBOptionsInterface {
      private static final Map ALL_KEYS_LOOKUP = new HashMap();

      private MutableDBOptionsBuilder() {
      }

      protected MutableDBOptionsBuilder self() {
         return this;
      }

      protected Map allKeys() {
         return ALL_KEYS_LOOKUP;
      }

      protected MutableDBOptions build(String[] var1, String[] var2) {
         return new MutableDBOptions(var1, var2);
      }

      public MutableDBOptionsBuilder setMaxBackgroundJobs(int var1) {
         return (MutableDBOptionsBuilder)this.setInt(MutableDBOptions.DBOption.max_background_jobs, var1);
      }

      public int maxBackgroundJobs() {
         return this.getInt(MutableDBOptions.DBOption.max_background_jobs);
      }

      /** @deprecated */
      @Deprecated
      public MutableDBOptionsBuilder setMaxBackgroundCompactions(int var1) {
         return (MutableDBOptionsBuilder)this.setInt(MutableDBOptions.DBOption.max_background_compactions, var1);
      }

      /** @deprecated */
      @Deprecated
      public int maxBackgroundCompactions() {
         return this.getInt(MutableDBOptions.DBOption.max_background_compactions);
      }

      public MutableDBOptionsBuilder setAvoidFlushDuringShutdown(boolean var1) {
         return (MutableDBOptionsBuilder)this.setBoolean(MutableDBOptions.DBOption.avoid_flush_during_shutdown, var1);
      }

      public boolean avoidFlushDuringShutdown() {
         return this.getBoolean(MutableDBOptions.DBOption.avoid_flush_during_shutdown);
      }

      public MutableDBOptionsBuilder setWritableFileMaxBufferSize(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.writable_file_max_buffer_size, var1);
      }

      public long writableFileMaxBufferSize() {
         return this.getLong(MutableDBOptions.DBOption.writable_file_max_buffer_size);
      }

      public MutableDBOptionsBuilder setDelayedWriteRate(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.delayed_write_rate, var1);
      }

      public long delayedWriteRate() {
         return this.getLong(MutableDBOptions.DBOption.delayed_write_rate);
      }

      public MutableDBOptionsBuilder setMaxTotalWalSize(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.max_total_wal_size, var1);
      }

      public long maxTotalWalSize() {
         return this.getLong(MutableDBOptions.DBOption.max_total_wal_size);
      }

      public MutableDBOptionsBuilder setDeleteObsoleteFilesPeriodMicros(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.delete_obsolete_files_period_micros, var1);
      }

      public long deleteObsoleteFilesPeriodMicros() {
         return this.getLong(MutableDBOptions.DBOption.delete_obsolete_files_period_micros);
      }

      public MutableDBOptionsBuilder setStatsDumpPeriodSec(int var1) {
         return (MutableDBOptionsBuilder)this.setInt(MutableDBOptions.DBOption.stats_dump_period_sec, var1);
      }

      public int statsDumpPeriodSec() {
         return this.getInt(MutableDBOptions.DBOption.stats_dump_period_sec);
      }

      public MutableDBOptionsBuilder setStatsPersistPeriodSec(int var1) {
         return (MutableDBOptionsBuilder)this.setInt(MutableDBOptions.DBOption.stats_persist_period_sec, var1);
      }

      public int statsPersistPeriodSec() {
         return this.getInt(MutableDBOptions.DBOption.stats_persist_period_sec);
      }

      public MutableDBOptionsBuilder setStatsHistoryBufferSize(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.stats_history_buffer_size, var1);
      }

      public long statsHistoryBufferSize() {
         return this.getLong(MutableDBOptions.DBOption.stats_history_buffer_size);
      }

      public MutableDBOptionsBuilder setMaxOpenFiles(int var1) {
         return (MutableDBOptionsBuilder)this.setInt(MutableDBOptions.DBOption.max_open_files, var1);
      }

      public int maxOpenFiles() {
         return this.getInt(MutableDBOptions.DBOption.max_open_files);
      }

      public MutableDBOptionsBuilder setBytesPerSync(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.bytes_per_sync, var1);
      }

      public long bytesPerSync() {
         return this.getLong(MutableDBOptions.DBOption.bytes_per_sync);
      }

      public MutableDBOptionsBuilder setWalBytesPerSync(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.wal_bytes_per_sync, var1);
      }

      public long walBytesPerSync() {
         return this.getLong(MutableDBOptions.DBOption.wal_bytes_per_sync);
      }

      public MutableDBOptionsBuilder setStrictBytesPerSync(boolean var1) {
         return (MutableDBOptionsBuilder)this.setBoolean(MutableDBOptions.DBOption.strict_bytes_per_sync, var1);
      }

      public boolean strictBytesPerSync() {
         return this.getBoolean(MutableDBOptions.DBOption.strict_bytes_per_sync);
      }

      public MutableDBOptionsBuilder setCompactionReadaheadSize(long var1) {
         return (MutableDBOptionsBuilder)this.setLong(MutableDBOptions.DBOption.compaction_readahead_size, var1);
      }

      public long compactionReadaheadSize() {
         return this.getLong(MutableDBOptions.DBOption.compaction_readahead_size);
      }

      static {
         for(DBOption var3 : MutableDBOptions.DBOption.values()) {
            ALL_KEYS_LOOKUP.put(var3.name(), var3);
         }

      }
   }

   private interface MutableDBOptionKey extends MutableOptionKey {
   }
}
