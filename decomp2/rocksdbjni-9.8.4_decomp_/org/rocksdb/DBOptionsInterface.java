package org.rocksdb;

import java.util.Collection;
import java.util.List;

public interface DBOptionsInterface {
   DBOptionsInterface optimizeForSmallDb();

   DBOptionsInterface setEnv(Env var1);

   Env getEnv();

   DBOptionsInterface setIncreaseParallelism(int var1);

   DBOptionsInterface setCreateIfMissing(boolean var1);

   boolean createIfMissing();

   DBOptionsInterface setCreateMissingColumnFamilies(boolean var1);

   boolean createMissingColumnFamilies();

   DBOptionsInterface setErrorIfExists(boolean var1);

   boolean errorIfExists();

   DBOptionsInterface setParanoidChecks(boolean var1);

   boolean paranoidChecks();

   DBOptionsInterface setRateLimiter(RateLimiter var1);

   DBOptionsInterface setSstFileManager(SstFileManager var1);

   DBOptionsInterface setLogger(LoggerInterface var1);

   DBOptionsInterface setInfoLogLevel(InfoLogLevel var1);

   InfoLogLevel infoLogLevel();

   DBOptionsInterface setMaxFileOpeningThreads(int var1);

   int maxFileOpeningThreads();

   DBOptionsInterface setStatistics(Statistics var1);

   Statistics statistics();

   DBOptionsInterface setUseFsync(boolean var1);

   boolean useFsync();

   DBOptionsInterface setDbPaths(Collection var1);

   List dbPaths();

   DBOptionsInterface setDbLogDir(String var1);

   String dbLogDir();

   DBOptionsInterface setWalDir(String var1);

   String walDir();

   DBOptionsInterface setDeleteObsoleteFilesPeriodMicros(long var1);

   long deleteObsoleteFilesPeriodMicros();

   DBOptionsInterface setMaxSubcompactions(int var1);

   int maxSubcompactions();

   /** @deprecated */
   @Deprecated
   DBOptionsInterface setMaxBackgroundFlushes(int var1);

   /** @deprecated */
   @Deprecated
   int maxBackgroundFlushes();

   DBOptionsInterface setMaxLogFileSize(long var1);

   long maxLogFileSize();

   DBOptionsInterface setLogFileTimeToRoll(long var1);

   long logFileTimeToRoll();

   DBOptionsInterface setKeepLogFileNum(long var1);

   long keepLogFileNum();

   DBOptionsInterface setRecycleLogFileNum(long var1);

   long recycleLogFileNum();

   DBOptionsInterface setMaxManifestFileSize(long var1);

   long maxManifestFileSize();

   DBOptionsInterface setTableCacheNumshardbits(int var1);

   int tableCacheNumshardbits();

   DBOptionsInterface setWalTtlSeconds(long var1);

   long walTtlSeconds();

   DBOptionsInterface setWalSizeLimitMB(long var1);

   long walSizeLimitMB();

   DBOptionsInterface setMaxWriteBatchGroupSizeBytes(long var1);

   long maxWriteBatchGroupSizeBytes();

   DBOptionsInterface setManifestPreallocationSize(long var1);

   long manifestPreallocationSize();

   DBOptionsInterface setUseDirectReads(boolean var1);

   boolean useDirectReads();

   DBOptionsInterface setUseDirectIoForFlushAndCompaction(boolean var1);

   boolean useDirectIoForFlushAndCompaction();

   DBOptionsInterface setAllowFAllocate(boolean var1);

   boolean allowFAllocate();

   DBOptionsInterface setAllowMmapReads(boolean var1);

   boolean allowMmapReads();

   DBOptionsInterface setAllowMmapWrites(boolean var1);

   boolean allowMmapWrites();

   DBOptionsInterface setIsFdCloseOnExec(boolean var1);

   boolean isFdCloseOnExec();

   DBOptionsInterface setAdviseRandomOnOpen(boolean var1);

   boolean adviseRandomOnOpen();

   DBOptionsInterface setDbWriteBufferSize(long var1);

   DBOptionsInterface setWriteBufferManager(WriteBufferManager var1);

   WriteBufferManager writeBufferManager();

   long dbWriteBufferSize();

   DBOptionsInterface setRandomAccessMaxBufferSize(long var1);

   long randomAccessMaxBufferSize();

   DBOptionsInterface setUseAdaptiveMutex(boolean var1);

   boolean useAdaptiveMutex();

   DBOptionsInterface setListeners(List var1);

   List listeners();

   DBOptionsInterface setEnableThreadTracking(boolean var1);

   boolean enableThreadTracking();

   DBOptionsInterface setEnablePipelinedWrite(boolean var1);

   boolean enablePipelinedWrite();

   DBOptionsInterface setUnorderedWrite(boolean var1);

   boolean unorderedWrite();

   DBOptionsInterface setAllowConcurrentMemtableWrite(boolean var1);

   boolean allowConcurrentMemtableWrite();

   DBOptionsInterface setEnableWriteThreadAdaptiveYield(boolean var1);

   boolean enableWriteThreadAdaptiveYield();

   DBOptionsInterface setWriteThreadMaxYieldUsec(long var1);

   long writeThreadMaxYieldUsec();

   DBOptionsInterface setWriteThreadSlowYieldUsec(long var1);

   long writeThreadSlowYieldUsec();

   DBOptionsInterface setSkipStatsUpdateOnDbOpen(boolean var1);

   boolean skipStatsUpdateOnDbOpen();

   DBOptionsInterface setSkipCheckingSstFileSizesOnDbOpen(boolean var1);

   boolean skipCheckingSstFileSizesOnDbOpen();

   DBOptionsInterface setWalRecoveryMode(WALRecoveryMode var1);

   WALRecoveryMode walRecoveryMode();

   DBOptionsInterface setAllow2pc(boolean var1);

   boolean allow2pc();

   DBOptionsInterface setRowCache(Cache var1);

   Cache rowCache();

   DBOptionsInterface setWalFilter(AbstractWalFilter var1);

   WalFilter walFilter();

   DBOptionsInterface setFailIfOptionsFileError(boolean var1);

   boolean failIfOptionsFileError();

   DBOptionsInterface setDumpMallocStats(boolean var1);

   boolean dumpMallocStats();

   DBOptionsInterface setAvoidFlushDuringRecovery(boolean var1);

   boolean avoidFlushDuringRecovery();

   DBOptionsInterface setAllowIngestBehind(boolean var1);

   boolean allowIngestBehind();

   DBOptionsInterface setTwoWriteQueues(boolean var1);

   boolean twoWriteQueues();

   DBOptionsInterface setManualWalFlush(boolean var1);

   boolean manualWalFlush();

   DBOptionsInterface setAtomicFlush(boolean var1);

   boolean atomicFlush();

   DBOptionsInterface setAvoidUnnecessaryBlockingIO(boolean var1);

   boolean avoidUnnecessaryBlockingIO();

   DBOptionsInterface setPersistStatsToDisk(boolean var1);

   boolean persistStatsToDisk();

   DBOptionsInterface setWriteDbidToManifest(boolean var1);

   boolean writeDbidToManifest();

   DBOptionsInterface setLogReadaheadSize(long var1);

   long logReadaheadSize();

   DBOptionsInterface setBestEffortsRecovery(boolean var1);

   boolean bestEffortsRecovery();

   DBOptionsInterface setMaxBgErrorResumeCount(int var1);

   int maxBgerrorResumeCount();

   DBOptionsInterface setBgerrorResumeRetryInterval(long var1);

   long bgerrorResumeRetryInterval();
}
