package org.rocksdb;

public interface MutableDBOptionsInterface {
   MutableDBOptionsInterface setMaxBackgroundJobs(int var1);

   int maxBackgroundJobs();

   /** @deprecated */
   @Deprecated
   MutableDBOptionsInterface setMaxBackgroundCompactions(int var1);

   /** @deprecated */
   @Deprecated
   int maxBackgroundCompactions();

   MutableDBOptionsInterface setAvoidFlushDuringShutdown(boolean var1);

   boolean avoidFlushDuringShutdown();

   MutableDBOptionsInterface setWritableFileMaxBufferSize(long var1);

   long writableFileMaxBufferSize();

   MutableDBOptionsInterface setDelayedWriteRate(long var1);

   long delayedWriteRate();

   MutableDBOptionsInterface setMaxTotalWalSize(long var1);

   long maxTotalWalSize();

   MutableDBOptionsInterface setDeleteObsoleteFilesPeriodMicros(long var1);

   long deleteObsoleteFilesPeriodMicros();

   MutableDBOptionsInterface setStatsDumpPeriodSec(int var1);

   int statsDumpPeriodSec();

   MutableDBOptionsInterface setStatsPersistPeriodSec(int var1);

   int statsPersistPeriodSec();

   MutableDBOptionsInterface setStatsHistoryBufferSize(long var1);

   long statsHistoryBufferSize();

   MutableDBOptionsInterface setMaxOpenFiles(int var1);

   int maxOpenFiles();

   MutableDBOptionsInterface setBytesPerSync(long var1);

   long bytesPerSync();

   MutableDBOptionsInterface setWalBytesPerSync(long var1);

   long walBytesPerSync();

   MutableDBOptionsInterface setStrictBytesPerSync(boolean var1);

   boolean strictBytesPerSync();

   MutableDBOptionsInterface setCompactionReadaheadSize(long var1);

   long compactionReadaheadSize();
}
