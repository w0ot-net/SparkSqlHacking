package org.apache.spark.network.shuffle;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricSet;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_NAME.;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.server.BlockPushNonFatalFailure;
import org.apache.spark.network.server.BlockPushNonFatalFailure.ReturnCode;
import org.apache.spark.network.shuffle.protocol.BlockPushReturnCode;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.shuffle.protocol.FinalizeShuffleMerge;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.shuffle.protocol.PushBlockStream;
import org.apache.spark.network.shuffle.protocol.RemoveShuffleMerge;
import org.apache.spark.network.shuffledb.DB;
import org.apache.spark.network.shuffledb.DBBackend;
import org.apache.spark.network.shuffledb.DBIterator;
import org.apache.spark.network.shuffledb.StoreVersion;
import org.apache.spark.network.util.DBProvider;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.roaringbitmap.RoaringBitmap;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import org.sparkproject.guava.primitives.Ints;
import org.sparkproject.guava.primitives.Longs;

public class RemoteBlockPushResolver implements MergedShuffleFileManager {
   private static final Cleaner CLEANER = Cleaner.create();
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(RemoteBlockPushResolver.class);
   public static final String MERGED_SHUFFLE_FILE_NAME_PREFIX = "shuffleMerged";
   public static final String SHUFFLE_META_DELIMITER = ":";
   public static final String MERGE_DIR_KEY = "mergeDir";
   public static final String ATTEMPT_ID_KEY = "attemptId";
   private static final int UNDEFINED_ATTEMPT_ID = -1;
   public static final int DELETE_ALL_MERGED_SHUFFLE = -1;
   private static final String DB_KEY_DELIMITER = ";";
   private static final ErrorHandler.BlockPushErrorHandler ERROR_HANDLER = createErrorHandler();
   private static final ByteBuffer SUCCESS_RESPONSE;
   private static final ObjectMapper mapper;
   private static final String APP_ATTEMPT_SHUFFLE_FINALIZE_STATUS_KEY_PREFIX = "AppAttemptShuffleFinalized";
   private static final String APP_ATTEMPT_PATH_KEY_PREFIX = "AppAttemptPathInfo";
   private static final StoreVersion CURRENT_VERSION;
   @VisibleForTesting
   final ConcurrentMap appsShuffleInfo;
   private final ExecutorService mergedShuffleCleaner;
   private final TransportConf conf;
   private final long cleanerShutdownTimeout;
   private final int minChunkSize;
   private final int ioExceptionsThresholdDuringMerge;
   private final LoadingCache indexCache;
   private final PushMergeMetrics pushMergeMetrics;
   @VisibleForTesting
   final File recoveryFile;
   @VisibleForTesting
   final DB db;

   public RemoteBlockPushResolver(TransportConf conf, File recoveryFile) throws IOException {
      this.conf = conf;
      this.appsShuffleInfo = new ConcurrentHashMap();
      this.mergedShuffleCleaner = Executors.newSingleThreadExecutor(NettyUtils.createThreadFactory("spark-shuffle-merged-shuffle-directory-cleaner"));
      this.cleanerShutdownTimeout = conf.mergedShuffleCleanerShutdownTimeout();
      this.minChunkSize = conf.minChunkSizeInMergedShuffleFile();
      this.ioExceptionsThresholdDuringMerge = conf.ioExceptionsThresholdDuringMerge();
      CacheLoader<String, ShuffleIndexInformation> indexCacheLoader = new CacheLoader() {
         public ShuffleIndexInformation load(String filePath) throws IOException {
            return new ShuffleIndexInformation(filePath);
         }
      };
      this.indexCache = CacheBuilder.newBuilder().maximumWeight(conf.mergedIndexCacheSize()).weigher((filePath, indexInfo) -> indexInfo.getRetainedMemorySize()).build(indexCacheLoader);
      this.recoveryFile = recoveryFile;
      String dbBackendName = conf.get("spark.shuffle.service.db.backend", DBBackend.ROCKSDB.name());
      DBBackend dbBackend = DBBackend.byName(dbBackendName);
      this.db = DBProvider.initDB(dbBackend, this.recoveryFile, CURRENT_VERSION, mapper);
      if (this.db != null) {
         logger.info("Use {} as the implementation of {}", new MDC[]{MDC.of(.MODULE$, dbBackend), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_KEY..MODULE$, "spark.shuffle.service.db.backend")});
         this.reloadAndCleanUpAppShuffleInfo(this.db);
      }

      this.pushMergeMetrics = new PushMergeMetrics();
   }

   @VisibleForTesting
   protected static ErrorHandler.BlockPushErrorHandler createErrorHandler() {
      return new ErrorHandler.BlockPushErrorHandler() {
         public boolean shouldLogError(Throwable t) {
            return !(t instanceof BlockPushNonFatalFailure);
         }
      };
   }

   @VisibleForTesting
   protected AppShuffleInfo validateAndGetAppShuffleInfo(String appId) {
      AppShuffleInfo appShuffleInfo = (AppShuffleInfo)this.appsShuffleInfo.get(appId);
      Preconditions.checkArgument(appShuffleInfo != null, "application " + appId + " is not registered or NM was restarted.");
      return appShuffleInfo;
   }

   @VisibleForTesting
   AppShufflePartitionInfo getOrCreateAppShufflePartitionInfo(AppShuffleInfo appShuffleInfo, int shuffleId, int shuffleMergeId, int reduceId, String blockId) throws BlockPushNonFatalFailure {
      ConcurrentMap<Integer, AppShuffleMergePartitionsInfo> shuffles = appShuffleInfo.shuffles;
      AppShuffleMergePartitionsInfo shufflePartitionsWithMergeId = (AppShuffleMergePartitionsInfo)shuffles.compute(shuffleId, (id, mergePartitionsInfo) -> {
         if (mergePartitionsInfo == null) {
            logger.info("{} attempt {} shuffle {} shuffleMerge {}: creating a new shuffle merge metadata", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appShuffleInfo.appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, appShuffleInfo.attemptId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, shuffleMergeId)});
            return new AppShuffleMergePartitionsInfo(shuffleMergeId, false);
         } else {
            int latestShuffleMergeId = mergePartitionsInfo.shuffleMergeId;
            if (latestShuffleMergeId > shuffleMergeId) {
               throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.STALE_BLOCK_PUSH.id(), blockId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(blockId, ReturnCode.STALE_BLOCK_PUSH));
            } else if (latestShuffleMergeId < shuffleMergeId) {
               AppAttemptShuffleMergeId currentAppAttemptShuffleMergeId = new AppAttemptShuffleMergeId(appShuffleInfo.appId, appShuffleInfo.attemptId, shuffleId, latestShuffleMergeId);
               logger.info("{}: creating a new shuffle merge metadata since received shuffleMergeId {} is higher than latest shuffleMergeId {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, currentAppAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, shuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.LATEST_SHUFFLE_MERGE_ID..MODULE$, latestShuffleMergeId)});
               this.submitCleanupTask(() -> this.closeAndDeleteOutdatedPartitions(currentAppAttemptShuffleMergeId, mergePartitionsInfo.shuffleMergePartitions));
               return new AppShuffleMergePartitionsInfo(shuffleMergeId, false);
            } else if (mergePartitionsInfo.isFinalized()) {
               throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.TOO_LATE_BLOCK_PUSH.id(), blockId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(blockId, ReturnCode.TOO_LATE_BLOCK_PUSH));
            } else {
               return mergePartitionsInfo;
            }
         }
      });
      Map<Integer, AppShufflePartitionInfo> shuffleMergePartitions = shufflePartitionsWithMergeId.shuffleMergePartitions;
      return (AppShufflePartitionInfo)shuffleMergePartitions.computeIfAbsent(reduceId, (key) -> {
         File dataFile = appShuffleInfo.getMergedShuffleDataFile(shuffleId, shuffleMergeId, reduceId);
         File indexFile = new File(appShuffleInfo.getMergedShuffleIndexFilePath(shuffleId, shuffleMergeId, reduceId));
         File metaFile = appShuffleInfo.getMergedShuffleMetaFile(shuffleId, shuffleMergeId, reduceId);

         try {
            return this.newAppShufflePartitionInfo(appShuffleInfo, shuffleId, shuffleMergeId, reduceId, dataFile, indexFile, metaFile);
         } catch (IOException e) {
            logger.error("{} attempt {} shuffle {} shuffleMerge {}: cannot create merged shuffle partition with data file {}, index file {}, and meta file {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appShuffleInfo.appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, appShuffleInfo.attemptId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, shuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.DATA_FILE..MODULE$, dataFile.getAbsolutePath()), MDC.of(org.apache.spark.internal.LogKeys.INDEX_FILE..MODULE$, indexFile.getAbsolutePath()), MDC.of(org.apache.spark.internal.LogKeys.META_FILE..MODULE$, metaFile.getAbsolutePath())});
            throw new RuntimeException(String.format("Cannot initialize merged shuffle partition for appId %s shuffleId %s shuffleMergeId %s reduceId %s", appShuffleInfo.appId, shuffleId, shuffleMergeId, reduceId), e);
         }
      });
   }

   @VisibleForTesting
   AppShufflePartitionInfo newAppShufflePartitionInfo(AppShuffleInfo appShuffleInfo, int shuffleId, int shuffleMergeId, int reduceId, File dataFile, File indexFile, File metaFile) throws IOException {
      return new AppShufflePartitionInfo(new AppAttemptShuffleMergeId(appShuffleInfo.appId, appShuffleInfo.attemptId, shuffleId, shuffleMergeId), reduceId, dataFile, new MergeShuffleFile(indexFile), new MergeShuffleFile(metaFile));
   }

   public MergedBlockMeta getMergedBlockMeta(String appId, int shuffleId, int shuffleMergeId, int reduceId) {
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(appId);
      AppShuffleMergePartitionsInfo partitionsInfo = (AppShuffleMergePartitionsInfo)appShuffleInfo.shuffles.get(shuffleId);
      if (null != partitionsInfo && partitionsInfo.shuffleMergeId > shuffleMergeId) {
         throw new RuntimeException(String.format("MergedBlockMeta fetch for shuffle %s with shuffleMergeId %s reduceId %s is %s", shuffleId, shuffleMergeId, reduceId, "stale shuffle block fetch request as shuffle blocks of a higher shuffleMergeId for the shuffle is available"));
      } else {
         File indexFile = new File(appShuffleInfo.getMergedShuffleIndexFilePath(shuffleId, shuffleMergeId, reduceId));
         if (!indexFile.exists()) {
            throw new RuntimeException(String.format("Merged shuffle index file %s not found", indexFile.getPath()));
         } else {
            int size = (int)indexFile.length();
            int numChunks = size / 8 - 1;
            if (numChunks <= 0) {
               throw new RuntimeException(String.format("Merged shuffle index file %s is empty", indexFile.getPath()));
            } else {
               File metaFile = appShuffleInfo.getMergedShuffleMetaFile(shuffleId, shuffleMergeId, reduceId);
               if (!metaFile.exists()) {
                  throw new RuntimeException(String.format("Merged shuffle meta file %s not found", metaFile.getPath()));
               } else {
                  FileSegmentManagedBuffer chunkBitMaps = new FileSegmentManagedBuffer(this.conf, metaFile, 0L, metaFile.length());
                  logger.trace("{} shuffleId {} shuffleMergeId {} reduceId {} num chunks {}", new Object[]{appId, shuffleId, shuffleMergeId, reduceId, numChunks});
                  return new MergedBlockMeta(numChunks, chunkBitMaps);
               }
            }
         }
      }
   }

   public ManagedBuffer getMergedBlockData(String appId, int shuffleId, int shuffleMergeId, int reduceId, int chunkId) {
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(appId);
      AppShuffleMergePartitionsInfo partitionsInfo = (AppShuffleMergePartitionsInfo)appShuffleInfo.shuffles.get(shuffleId);
      if (null != partitionsInfo && partitionsInfo.shuffleMergeId > shuffleMergeId) {
         throw new RuntimeException(String.format("MergedBlockData fetch for shuffle %s with shuffleMergeId %s reduceId %s is %s", shuffleId, shuffleMergeId, reduceId, "stale shuffle block fetch request as shuffle blocks of a higher shuffleMergeId for the shuffle is available"));
      } else {
         File dataFile = appShuffleInfo.getMergedShuffleDataFile(shuffleId, shuffleMergeId, reduceId);
         if (!dataFile.exists()) {
            throw new RuntimeException(String.format("Merged shuffle data file %s not found", dataFile.getPath()));
         } else {
            String indexFilePath = appShuffleInfo.getMergedShuffleIndexFilePath(shuffleId, shuffleMergeId, reduceId);

            try {
               ShuffleIndexInformation shuffleIndexInformation = (ShuffleIndexInformation)this.indexCache.get(indexFilePath);
               ShuffleIndexRecord shuffleIndexRecord = shuffleIndexInformation.getIndex(chunkId);
               return new FileSegmentManagedBuffer(this.conf, dataFile, shuffleIndexRecord.offset(), shuffleIndexRecord.length());
            } catch (ExecutionException e) {
               throw new RuntimeException(String.format("Failed to open merged shuffle index file %s", indexFilePath), e);
            }
         }
      }
   }

   public String[] getMergedBlockDirs(String appId) {
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(appId);
      return appShuffleInfo.appPathsInfo.activeLocalDirs;
   }

   private void removeOldApplicationAttemptsFromDb(AppShuffleInfo info) {
      if (info.attemptId != -1) {
         for(int formerAttemptId = 0; formerAttemptId < info.attemptId; ++formerAttemptId) {
            this.removeAppAttemptPathInfoFromDB(info.appId, formerAttemptId);
         }
      }

   }

   public void applicationRemoved(String appId, boolean cleanupLocalDirs) {
      logger.info("Application {} removed, cleanupLocalDirs = {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), MDC.of(org.apache.spark.internal.LogKeys.CLEANUP_LOCAL_DIRS..MODULE$, cleanupLocalDirs)});
      AtomicReference<AppShuffleInfo> ref = new AtomicReference((Object)null);
      this.appsShuffleInfo.compute(appId, (id, info) -> {
         if (null != info) {
            this.removeAppAttemptPathInfoFromDB(info.appId, info.attemptId);
            this.removeOldApplicationAttemptsFromDb(info);
            ref.set(info);
         }

         return null;
      });
      AppShuffleInfo appShuffleInfo = (AppShuffleInfo)ref.get();
      if (null != appShuffleInfo) {
         this.submitCleanupTask(() -> this.closeAndDeletePartitionsIfNeeded(appShuffleInfo, cleanupLocalDirs));
      }

   }

   public void removeShuffleMerge(RemoveShuffleMerge msg) {
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(msg.appId);
      if (appShuffleInfo.attemptId != msg.appAttemptId) {
         throw new IllegalArgumentException(String.format("The attempt id %s in this RemoveShuffleMerge message does not match with the current attempt id %s stored in shuffle service for application %s", msg.appAttemptId, appShuffleInfo.attemptId, msg.appId));
      } else {
         appShuffleInfo.shuffles.compute(msg.shuffleId, (shuffleId, mergePartitionsInfo) -> {
            if (mergePartitionsInfo == null) {
               if (msg.shuffleMergeId == -1) {
                  return null;
               } else {
                  this.writeAppAttemptShuffleMergeInfoToDB(new AppAttemptShuffleMergeId(msg.appId, msg.appAttemptId, msg.shuffleId, msg.shuffleMergeId));
                  return new AppShuffleMergePartitionsInfo(msg.shuffleMergeId, true);
               }
            } else {
               boolean deleteCurrentMergedShuffle = msg.shuffleMergeId == -1 || msg.shuffleMergeId == mergePartitionsInfo.shuffleMergeId;
               int shuffleMergeIdToDelete = msg.shuffleMergeId != -1 ? msg.shuffleMergeId : mergePartitionsInfo.shuffleMergeId;
               if (!deleteCurrentMergedShuffle && shuffleMergeIdToDelete <= mergePartitionsInfo.shuffleMergeId) {
                  throw new RuntimeException(String.format("Asked to remove old shuffle merged data for application %s shuffleId %s shuffleMergeId %s, but current shuffleMergeId %s ", msg.appId, msg.shuffleId, shuffleMergeIdToDelete, mergePartitionsInfo.shuffleMergeId));
               } else {
                  AppAttemptShuffleMergeId currentAppAttemptShuffleMergeId = new AppAttemptShuffleMergeId(msg.appId, msg.appAttemptId, msg.shuffleId, mergePartitionsInfo.shuffleMergeId);
                  if (!mergePartitionsInfo.isFinalized()) {
                     this.submitCleanupTask(() -> this.closeAndDeleteOutdatedPartitions(currentAppAttemptShuffleMergeId, mergePartitionsInfo.shuffleMergePartitions));
                  } else {
                     this.submitCleanupTask(() -> this.deleteMergedFiles(currentAppAttemptShuffleMergeId, appShuffleInfo, mergePartitionsInfo.getReduceIds(), false));
                  }

                  this.writeAppAttemptShuffleMergeInfoToDB(new AppAttemptShuffleMergeId(msg.appId, msg.appAttemptId, msg.shuffleId, shuffleMergeIdToDelete));
                  return new AppShuffleMergePartitionsInfo(shuffleMergeIdToDelete, true);
               }
            }
         });
      }
   }

   @VisibleForTesting
   void closeAndDeletePartitionsIfNeeded(AppShuffleInfo appShuffleInfo, boolean cleanupLocalDirs) {
      appShuffleInfo.shuffles.forEach((shuffleId, shuffleInfo) -> shuffleInfo.shuffleMergePartitions.forEach((shuffleMergeId, partitionInfo) -> {
            synchronized(partitionInfo) {
               partitionInfo.cleanable.clean();
            }
         }));
      if (cleanupLocalDirs) {
         this.deleteExecutorDirs(appShuffleInfo);
      }

      this.removeAppShuffleInfoFromDB(appShuffleInfo);
   }

   @VisibleForTesting
   void removeAppAttemptPathInfoFromDB(String appId, int attemptId) {
      AppAttemptId appAttemptId = new AppAttemptId(appId, attemptId);
      if (this.db != null && AppsWithRecoveryDisabled.isRecoveryEnabledForApp(appId)) {
         try {
            byte[] key = this.getDbAppAttemptPathsKey(appAttemptId);
            this.db.delete(key);
         } catch (Exception e) {
            logger.error("Failed to remove the application attempt {} local path in DB", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, appAttemptId)});
         }
      }

   }

   @VisibleForTesting
   void removeAppShuffleInfoFromDB(AppShuffleInfo appShuffleInfo) {
      if (this.db != null) {
         appShuffleInfo.shuffles.forEach((shuffleId, shuffleInfo) -> this.removeAppShufflePartitionInfoFromDB(new AppAttemptShuffleMergeId(appShuffleInfo.appId, appShuffleInfo.attemptId, shuffleId, shuffleInfo.shuffleMergeId)));
      }

   }

   @VisibleForTesting
   void closeAndDeleteOutdatedPartitions(AppAttemptShuffleMergeId appAttemptShuffleMergeId, Map partitions) {
      this.removeAppShufflePartitionInfoFromDB(appAttemptShuffleMergeId);
      partitions.forEach((partitionId, partitionInfo) -> {
         synchronized(partitionInfo) {
            partitionInfo.cleanable.clean();
            partitionInfo.deleteAllFiles();
         }
      });
   }

   void deleteMergedFiles(AppAttemptShuffleMergeId appAttemptShuffleMergeId, AppShuffleInfo appShuffleInfo, int[] reduceIds, boolean deleteFromDB) {
      if (deleteFromDB) {
         this.removeAppShufflePartitionInfoFromDB(appAttemptShuffleMergeId);
      }

      int shuffleId = appAttemptShuffleMergeId.shuffleId;
      int shuffleMergeId = appAttemptShuffleMergeId.shuffleMergeId;
      int dataFilesDeleteCnt = 0;
      int indexFilesDeleteCnt = 0;
      int metaFilesDeleteCnt = 0;

      for(int reduceId : reduceIds) {
         File dataFile = appShuffleInfo.getMergedShuffleDataFile(shuffleId, shuffleMergeId, reduceId);
         if (dataFile.delete()) {
            ++dataFilesDeleteCnt;
         }

         File indexFile = new File(appShuffleInfo.getMergedShuffleIndexFilePath(shuffleId, shuffleMergeId, reduceId));
         if (indexFile.delete()) {
            ++indexFilesDeleteCnt;
         }

         File metaFile = appShuffleInfo.getMergedShuffleMetaFile(shuffleId, shuffleMergeId, reduceId);
         if (metaFile.delete()) {
            ++metaFilesDeleteCnt;
         }
      }

      logger.info("Delete {} data files, {} index files, {} meta files for {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.NUM_DATA_FILES..MODULE$, dataFilesDeleteCnt), MDC.of(org.apache.spark.internal.LogKeys.NUM_INDEX_FILES..MODULE$, indexFilesDeleteCnt), MDC.of(org.apache.spark.internal.LogKeys.NUM_META_FILES..MODULE$, metaFilesDeleteCnt), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId)});
   }

   void removeAppShufflePartitionInfoFromDB(AppAttemptShuffleMergeId appAttemptShuffleMergeId) {
      if (this.db != null) {
         try {
            this.db.delete(this.getDbAppAttemptShufflePartitionKey(appAttemptShuffleMergeId));
         } catch (Exception e) {
            logger.error("Error deleting {} from application shuffle merged partition info in DB", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId)});
         }
      }

   }

   @VisibleForTesting
   void deleteExecutorDirs(AppShuffleInfo appShuffleInfo) {
      Path[] dirs = (Path[])Arrays.stream(appShuffleInfo.appPathsInfo.activeLocalDirs).map((dir) -> Paths.get(dir)).toArray((x$0) -> new Path[x$0]);

      for(Path localDir : dirs) {
         try {
            if (Files.exists(localDir, new LinkOption[0])) {
               JavaUtils.deleteRecursively(localDir.toFile());
               logger.debug("Successfully cleaned up directory: {}", localDir);
            }
         } catch (Exception e) {
            logger.error("Failed to delete directory: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, localDir)});
         }
      }

   }

   public MetricSet getMetrics() {
      return this.pushMergeMetrics;
   }

   public StreamCallbackWithID receiveBlockDataAsStream(PushBlockStream msg) {
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(msg.appId);
      final String streamId = "shufflePush_" + msg.shuffleId + "_" + msg.shuffleMergeId + "_" + msg.mapIndex + "_" + msg.reduceId;
      if (appShuffleInfo.attemptId != msg.appAttemptId) {
         throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.TOO_OLD_ATTEMPT_PUSH.id(), streamId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(streamId, ReturnCode.TOO_OLD_ATTEMPT_PUSH));
      } else {
         final BlockPushNonFatalFailure failure = null;

         AppShufflePartitionInfo partitionInfoBeforeCheck;
         try {
            partitionInfoBeforeCheck = this.getOrCreateAppShufflePartitionInfo(appShuffleInfo, msg.shuffleId, msg.shuffleMergeId, msg.reduceId, streamId);
         } catch (BlockPushNonFatalFailure bpf) {
            partitionInfoBeforeCheck = null;
            failure = bpf;
         }

         AppShufflePartitionInfo partitionInfo = failure != null ? null : (partitionInfoBeforeCheck.mapTracker.contains(msg.mapIndex) ? null : partitionInfoBeforeCheck);
         if (partitionInfo != null) {
            return new PushBlockStreamCallback(this, appShuffleInfo, streamId, partitionInfo, msg.mapIndex);
         } else {
            this.pushMergeMetrics.lateBlockPushes.mark();
            return new StreamCallbackWithID() {
               public String getID() {
                  return streamId;
               }

               public void onData(String streamIdx, ByteBuffer buf) {
                  RemoteBlockPushResolver.this.pushMergeMetrics.ignoredBlockBytes.mark((long)buf.remaining());
               }

               public void onComplete(String streamIdx) {
                  if (failure != null) {
                     throw failure;
                  }
               }

               public void onFailure(String streamIdx, Throwable cause) {
               }

               public ByteBuffer getCompletionResponse() {
                  return RemoteBlockPushResolver.SUCCESS_RESPONSE.duplicate();
               }
            };
         }
      }
   }

   public MergeStatuses finalizeShuffleMerge(FinalizeShuffleMerge msg) {
      logger.info("{} attempt {} shuffle {} shuffleMerge {}: finalize shuffle merge", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, msg.appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, msg.appAttemptId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, msg.shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, msg.shuffleMergeId)});
      AppShuffleInfo appShuffleInfo = this.validateAndGetAppShuffleInfo(msg.appId);
      if (appShuffleInfo.attemptId != msg.appAttemptId) {
         throw new IllegalArgumentException(String.format("The attempt id %s in this FinalizeShuffleMerge message does not match with the current attempt id %s stored in shuffle service for application %s", msg.appAttemptId, appShuffleInfo.attemptId, msg.appId));
      } else {
         AppAttemptShuffleMergeId appAttemptShuffleMergeId = new AppAttemptShuffleMergeId(msg.appId, msg.appAttemptId, msg.shuffleId, msg.shuffleMergeId);
         AtomicReference<Map<Integer, AppShufflePartitionInfo>> shuffleMergePartitionsRef = new AtomicReference((Object)null);
         appShuffleInfo.shuffles.compute(msg.shuffleId, (shuffleId, mergePartitionsInfo) -> {
            if (null != mergePartitionsInfo) {
               if (msg.shuffleMergeId < mergePartitionsInfo.shuffleMergeId || mergePartitionsInfo.isFinalized()) {
                  throw new RuntimeException(String.format("Shuffle merge finalize request for shuffle %s with shuffleMergeId %s is %s", msg.shuffleId, msg.shuffleMergeId, "stale shuffle finalize request as shuffle blocks of a higher shuffleMergeId for the shuffle is already being pushed"));
               }

               if (msg.shuffleMergeId > mergePartitionsInfo.shuffleMergeId) {
                  AppAttemptShuffleMergeId currentAppAttemptShuffleMergeId = new AppAttemptShuffleMergeId(msg.appId, msg.appAttemptId, msg.shuffleId, mergePartitionsInfo.shuffleMergeId);
                  this.submitCleanupTask(() -> this.closeAndDeleteOutdatedPartitions(currentAppAttemptShuffleMergeId, mergePartitionsInfo.shuffleMergePartitions));
               } else {
                  shuffleMergePartitionsRef.set(mergePartitionsInfo.shuffleMergePartitions);
               }
            }

            this.writeAppAttemptShuffleMergeInfoToDB(appAttemptShuffleMergeId);
            return new AppShuffleMergePartitionsInfo(msg.shuffleMergeId, true);
         });
         Map<Integer, AppShufflePartitionInfo> shuffleMergePartitions = (Map)shuffleMergePartitionsRef.get();
         MergeStatuses mergeStatuses;
         if (null != shuffleMergePartitions && !shuffleMergePartitions.isEmpty()) {
            List<RoaringBitmap> bitmaps = new ArrayList(shuffleMergePartitions.size());
            List<Integer> reduceIds = new ArrayList(shuffleMergePartitions.size());
            List<Long> sizes = new ArrayList(shuffleMergePartitions.size());

            for(AppShufflePartitionInfo partition : shuffleMergePartitions.values()) {
               synchronized(partition) {
                  try {
                     logger.debug("{} attempt {} shuffle {} shuffleMerge {}: finalizing shuffle partition {} ", new Object[]{msg.appId, msg.appAttemptId, msg.shuffleId, msg.shuffleMergeId, partition.reduceId});
                     partition.finalizePartition();
                     if (!partition.mapTracker.isEmpty()) {
                        bitmaps.add(partition.mapTracker);
                        reduceIds.add(partition.reduceId);
                        sizes.add(partition.getLastChunkOffset());
                        logger.debug("{} attempt {} shuffle {} shuffleMerge {}: finalization results added for partition {} data size {} index size {} meta size {}", new Object[]{msg.appId, msg.appAttemptId, msg.shuffleId, msg.shuffleMergeId, partition.reduceId, partition.getLastChunkOffset(), partition.indexFile.getPos(), partition.metaFile.getPos()});
                     }
                  } catch (IOException ioe) {
                     logger.warn("{} attempt {} shuffle {} shuffleMerge {}: exception while finalizing shuffle partition {}. Exception message: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, msg.appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, msg.appAttemptId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, msg.shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, msg.shuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, partition.reduceId), MDC.of(org.apache.spark.internal.LogKeys.EXCEPTION..MODULE$, ioe.getMessage())});
                  } finally {
                     partition.cleanable.clean();
                  }
               }
            }

            mergeStatuses = new MergeStatuses(msg.shuffleId, msg.shuffleMergeId, (RoaringBitmap[])bitmaps.toArray(new RoaringBitmap[bitmaps.size()]), Ints.toArray(reduceIds), Longs.toArray(sizes));
            ((AppShuffleMergePartitionsInfo)appShuffleInfo.shuffles.get(msg.shuffleId)).setReduceIds(Ints.toArray(reduceIds));
         } else {
            mergeStatuses = new MergeStatuses(msg.shuffleId, msg.shuffleMergeId, new RoaringBitmap[0], new int[0], new long[0]);
         }

         logger.info("{} attempt {} shuffle {} shuffleMerge {}: finalization of shuffle merge completed", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, msg.appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, msg.appAttemptId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, msg.shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, msg.shuffleMergeId)});
         return mergeStatuses;
      }
   }

   public void registerExecutor(String appId, ExecutorShuffleInfo executorInfo) {
      if (logger.isDebugEnabled()) {
         logger.debug("register executor with RemoteBlockPushResolver {} local-dirs {} num sub-dirs {} shuffleManager {}", new Object[]{appId, Arrays.toString(executorInfo.localDirs), executorInfo.subDirsPerLocalDir, executorInfo.shuffleManager});
      }

      String shuffleManagerMeta = executorInfo.shuffleManager;
      if (shuffleManagerMeta.contains(":")) {
         String mergeDirInfo = shuffleManagerMeta.substring(shuffleManagerMeta.indexOf(":") + 1);

         try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Map<String, String>> typeRef = new TypeReference() {
            };
            Map<String, String> metaMap = (Map)mapper.readValue(mergeDirInfo, typeRef);
            String mergeDir = (String)metaMap.get("mergeDir");
            int attemptId = Integer.valueOf((String)metaMap.getOrDefault("attemptId", String.valueOf(-1)));
            if (mergeDir == null) {
               throw new IllegalArgumentException(String.format("Failed to get the merge directory information from the shuffleManagerMeta %s in executor registration message", shuffleManagerMeta));
            }

            if (attemptId == -1) {
               this.appsShuffleInfo.computeIfAbsent(appId, (id) -> {
                  AppPathsInfo appPathsInfo = new AppPathsInfo(appId, executorInfo.localDirs, mergeDir, executorInfo.subDirsPerLocalDir);
                  this.writeAppPathsInfoToDb(appId, -1, appPathsInfo);
                  return new AppShuffleInfo(appId, -1, appPathsInfo);
               });
            } else {
               AtomicReference<AppShuffleInfo> originalAppShuffleInfo = new AtomicReference();
               this.appsShuffleInfo.compute(appId, (id, appShuffleInfox) -> {
                  if (appShuffleInfox == null || attemptId > appShuffleInfox.attemptId) {
                     originalAppShuffleInfo.set(appShuffleInfox);
                     AppPathsInfo appPathsInfo = new AppPathsInfo(appId, executorInfo.localDirs, mergeDir, executorInfo.subDirsPerLocalDir);
                     if (appShuffleInfox != null) {
                        this.removeAppAttemptPathInfoFromDB(appId, appShuffleInfox.attemptId);
                     }

                     this.writeAppPathsInfoToDb(appId, attemptId, appPathsInfo);
                     appShuffleInfox = new AppShuffleInfo(appId, attemptId, new AppPathsInfo(appId, executorInfo.localDirs, mergeDir, executorInfo.subDirsPerLocalDir));
                  }

                  return appShuffleInfox;
               });
               if (originalAppShuffleInfo.get() != null) {
                  AppShuffleInfo appShuffleInfo = (AppShuffleInfo)originalAppShuffleInfo.get();
                  logger.warn("Cleanup shuffle info and merged shuffle files for {}_{} as new application attempt registered", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, appShuffleInfo.attemptId)});
                  this.submitCleanupTask(() -> this.closeAndDeletePartitionsIfNeeded(appShuffleInfo, true));
               }
            }
         } catch (JsonProcessingException e) {
            logger.warn("Failed to get the merge directory information from ExecutorShuffleInfo: ", e);
         }
      } else {
         logger.warn("ExecutorShuffleInfo does not have the expected merge directory information");
      }

   }

   public void close() {
      if (!this.mergedShuffleCleaner.isShutdown()) {
         try {
            this.mergedShuffleCleaner.shutdown();
            if (!this.mergedShuffleCleaner.awaitTermination(this.cleanerShutdownTimeout, TimeUnit.SECONDS)) {
               this.shutdownMergedShuffleCleanerNow();
            }
         } catch (InterruptedException e) {
            logger.info("mergedShuffleCleaner is interrupted in the process of graceful shutdown", e);
            this.shutdownMergedShuffleCleanerNow();
            Thread.currentThread().interrupt();
         }
      }

      if (this.db != null) {
         try {
            this.db.close();
         } catch (IOException e) {
            logger.error("Exception closing leveldb with registered app paths info and shuffle partition info", e);
         }
      }

   }

   private void shutdownMergedShuffleCleanerNow() {
      try {
         List<Runnable> unfinishedTasks = this.mergedShuffleCleaner.shutdownNow();
         logger.warn("There are still {} tasks not completed in mergedShuffleCleaner after {} ms.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.COUNT..MODULE$, unfinishedTasks.size()), MDC.of(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, this.cleanerShutdownTimeout * 1000L)});
         if (!this.mergedShuffleCleaner.awaitTermination(this.cleanerShutdownTimeout, TimeUnit.SECONDS)) {
            logger.warn("mergedShuffleCleaner did not terminate in {} ms.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, this.cleanerShutdownTimeout * 1000L)});
         }
      } catch (InterruptedException var2) {
         Thread.currentThread().interrupt();
      }

   }

   private void writeAppPathsInfoToDb(String appId, int attemptId, AppPathsInfo appPathsInfo) {
      if (this.db != null && AppsWithRecoveryDisabled.isRecoveryEnabledForApp(appId)) {
         AppAttemptId appAttemptId = new AppAttemptId(appId, attemptId);

         try {
            byte[] key = this.getDbAppAttemptPathsKey(appAttemptId);
            String valueStr = mapper.writeValueAsString(appPathsInfo);
            byte[] value = valueStr.getBytes(StandardCharsets.UTF_8);
            this.db.put(key, value);
         } catch (Exception e) {
            logger.error("Error saving registered app paths info for {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, appAttemptId)});
         }
      }

   }

   private void writeAppAttemptShuffleMergeInfoToDB(AppAttemptShuffleMergeId appAttemptShuffleMergeId) {
      if (this.db != null && AppsWithRecoveryDisabled.isRecoveryEnabledForApp(appAttemptShuffleMergeId.appId)) {
         try {
            byte[] dbKey = this.getDbAppAttemptShufflePartitionKey(appAttemptShuffleMergeId);
            this.db.put(dbKey, new byte[0]);
         } catch (Exception e) {
            logger.error("Error saving active app shuffle partition {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId)});
         }
      }

   }

   private Object parseDbKey(String key, String prefix, Class valueType) throws IOException {
      String json = key.substring(prefix.length() + 1);
      return mapper.readValue(json, valueType);
   }

   private AppAttemptId parseDbAppAttemptPathsKey(String key) throws IOException {
      return (AppAttemptId)this.parseDbKey(key, "AppAttemptPathInfo", AppAttemptId.class);
   }

   private AppAttemptShuffleMergeId parseDbAppAttemptShufflePartitionKey(String key) throws IOException {
      return (AppAttemptShuffleMergeId)this.parseDbKey(key, "AppAttemptShuffleFinalized", AppAttemptShuffleMergeId.class);
   }

   private byte[] getDbKey(Object key, String prefix) throws IOException {
      String keyJsonString = prefix + ";" + mapper.writeValueAsString(key);
      return keyJsonString.getBytes(StandardCharsets.UTF_8);
   }

   private byte[] getDbAppAttemptShufflePartitionKey(AppAttemptShuffleMergeId appAttemptShuffleMergeId) throws IOException {
      return this.getDbKey(appAttemptShuffleMergeId, "AppAttemptShuffleFinalized");
   }

   private byte[] getDbAppAttemptPathsKey(AppAttemptId appAttemptId) throws IOException {
      return this.getDbKey(appAttemptId, "AppAttemptPathInfo");
   }

   @VisibleForTesting
   void reloadAndCleanUpAppShuffleInfo(DB db) throws IOException {
      logger.info("Reload applications merged shuffle information from DB");
      List<byte[]> dbKeysToBeRemoved = new ArrayList();
      dbKeysToBeRemoved.addAll(this.reloadActiveAppAttemptsPathInfo(db));
      dbKeysToBeRemoved.addAll(this.reloadFinalizedAppAttemptsShuffleMergeInfo(db));
      this.removeOutdatedKeyValuesInDB(dbKeysToBeRemoved);
   }

   @VisibleForTesting
   List reloadActiveAppAttemptsPathInfo(DB db) throws IOException {
      List<byte[]> dbKeysToBeRemoved = new ArrayList();
      if (db != null) {
         DBIterator itr = db.iterator();

         try {
            itr.seek("AppAttemptPathInfo".getBytes(StandardCharsets.UTF_8));

            while(itr.hasNext()) {
               Map.Entry<byte[], byte[]> entry = (Map.Entry)itr.next();
               String key = new String((byte[])entry.getKey(), StandardCharsets.UTF_8);
               if (!key.startsWith("AppAttemptPathInfo")) {
                  break;
               }

               AppAttemptId appAttemptId = this.parseDbAppAttemptPathsKey(key);
               AppPathsInfo appPathsInfo = (AppPathsInfo)mapper.readValue((byte[])entry.getValue(), AppPathsInfo.class);
               logger.debug("Reloading Application paths info for application {}", appAttemptId);
               this.appsShuffleInfo.compute(appAttemptId.appId, (appId, existingAppShuffleInfo) -> {
                  if (existingAppShuffleInfo != null && existingAppShuffleInfo.attemptId >= appAttemptId.attemptId) {
                     dbKeysToBeRemoved.add((byte[])entry.getKey());
                     return existingAppShuffleInfo;
                  } else {
                     if (existingAppShuffleInfo != null) {
                        AppAttemptId existingAppAttemptId = new AppAttemptId(existingAppShuffleInfo.appId, existingAppShuffleInfo.attemptId);

                        try {
                           dbKeysToBeRemoved.add(this.getDbAppAttemptPathsKey(existingAppAttemptId));
                        } catch (IOException e) {
                           logger.error("Failed to get the DB key for {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, existingAppAttemptId)});
                        }
                     }

                     return new AppShuffleInfo(appAttemptId.appId, appAttemptId.attemptId, appPathsInfo);
                  }
               });
            }
         } catch (Throwable var9) {
            if (itr != null) {
               try {
                  itr.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (itr != null) {
            itr.close();
         }
      }

      return dbKeysToBeRemoved;
   }

   @VisibleForTesting
   List reloadFinalizedAppAttemptsShuffleMergeInfo(DB db) throws IOException {
      List<byte[]> dbKeysToBeRemoved = new ArrayList();
      if (db != null) {
         DBIterator itr = db.iterator();

         try {
            itr.seek("AppAttemptShuffleFinalized".getBytes(StandardCharsets.UTF_8));

            while(itr.hasNext()) {
               Map.Entry<byte[], byte[]> entry = (Map.Entry)itr.next();
               String key = new String((byte[])entry.getKey(), StandardCharsets.UTF_8);
               if (!key.startsWith("AppAttemptShuffleFinalized")) {
                  break;
               }

               AppAttemptShuffleMergeId partitionId = this.parseDbAppAttemptShufflePartitionKey(key);
               logger.debug("Reloading finalized shuffle info for partitionId {}", partitionId);
               AppShuffleInfo appShuffleInfo = (AppShuffleInfo)this.appsShuffleInfo.get(partitionId.appId);
               if (appShuffleInfo != null && appShuffleInfo.attemptId == partitionId.attemptId) {
                  appShuffleInfo.shuffles.compute(partitionId.shuffleId, (shuffleId, existingMergePartitionInfo) -> {
                     if (existingMergePartitionInfo != null && existingMergePartitionInfo.shuffleMergeId >= partitionId.shuffleMergeId) {
                        dbKeysToBeRemoved.add((byte[])entry.getKey());
                        return existingMergePartitionInfo;
                     } else {
                        if (existingMergePartitionInfo != null) {
                           AppAttemptShuffleMergeId appAttemptShuffleMergeId = new AppAttemptShuffleMergeId(appShuffleInfo.appId, appShuffleInfo.attemptId, shuffleId, existingMergePartitionInfo.shuffleMergeId);

                           try {
                              dbKeysToBeRemoved.add(this.getDbAppAttemptShufflePartitionKey(appAttemptShuffleMergeId));
                           } catch (Exception e) {
                              logger.error("Error getting the DB key for {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId)});
                           }
                        }

                        return new AppShuffleMergePartitionsInfo(partitionId.shuffleMergeId, true);
                     }
                  });
               } else {
                  dbKeysToBeRemoved.add((byte[])entry.getKey());
               }
            }
         } catch (Throwable var9) {
            if (itr != null) {
               try {
                  itr.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (itr != null) {
            itr.close();
         }
      }

      return dbKeysToBeRemoved;
   }

   @VisibleForTesting
   void removeOutdatedKeyValuesInDB(List dbKeysToBeRemoved) {
      dbKeysToBeRemoved.forEach((key) -> {
         try {
            this.db.delete(key);
         } catch (Exception e) {
            logger.error("Error deleting dangling key {} in DB", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.KEY..MODULE$, key)});
         }

      });
   }

   @VisibleForTesting
   void submitCleanupTask(Runnable task) {
      this.mergedShuffleCleaner.execute(task);
   }

   @VisibleForTesting
   boolean isCleanerShutdown() {
      return this.mergedShuffleCleaner.isShutdown();
   }

   static {
      SUCCESS_RESPONSE = (new BlockPushReturnCode(ReturnCode.SUCCESS.id(), "")).toByteBuffer().asReadOnlyBuffer();
      mapper = new ObjectMapper();
      CURRENT_VERSION = new StoreVersion(1, 0);
   }

   static class PushBlockStreamCallback implements StreamCallbackWithID {
      private final RemoteBlockPushResolver mergeManager;
      private final AppShuffleInfo appShuffleInfo;
      private final String streamId;
      private final int mapIndex;
      private final AppShufflePartitionInfo partitionInfo;
      private int length = 0;
      private boolean isWriting = false;
      private List deferredBufs;
      private long receivedBytes = 0L;

      private PushBlockStreamCallback(RemoteBlockPushResolver mergeManager, AppShuffleInfo appShuffleInfo, String streamId, AppShufflePartitionInfo partitionInfo, int mapIndex) {
         Preconditions.checkArgument(mergeManager != null);
         this.mergeManager = mergeManager;
         Preconditions.checkArgument(appShuffleInfo != null);
         this.appShuffleInfo = appShuffleInfo;
         this.streamId = streamId;
         Preconditions.checkArgument(partitionInfo != null);
         this.partitionInfo = partitionInfo;
         this.mapIndex = mapIndex;
         this.abortIfNecessary();
      }

      public String getID() {
         return this.streamId;
      }

      public ByteBuffer getCompletionResponse() {
         return RemoteBlockPushResolver.SUCCESS_RESPONSE.duplicate();
      }

      private void writeBuf(ByteBuffer buf) throws IOException {
         while(buf.hasRemaining()) {
            long updatedPos = this.partitionInfo.getDataFilePos() + (long)this.length;
            RemoteBlockPushResolver.logger.debug("{} current pos {} updated pos {}", new Object[]{this.partitionInfo, this.partitionInfo.getDataFilePos(), updatedPos});
            int bytesWritten = this.partitionInfo.dataChannel.write(buf, updatedPos);
            this.length += bytesWritten;
            this.mergeManager.pushMergeMetrics.blockBytesWritten.mark((long)bytesWritten);
         }

      }

      private boolean allowedToWrite() {
         return this.partitionInfo.getCurrentMapIndex() < 0 || this.partitionInfo.getCurrentMapIndex() == this.mapIndex;
      }

      private boolean isDuplicateBlock() {
         return this.partitionInfo.getCurrentMapIndex() == this.mapIndex && this.length == 0 || this.partitionInfo.mapTracker.contains(this.mapIndex);
      }

      private void writeDeferredBufs() throws IOException {
         long totalSize = 0L;

         for(ByteBuffer deferredBuf : this.deferredBufs) {
            totalSize += (long)deferredBuf.limit();
            this.writeBuf(deferredBuf);
            this.mergeManager.pushMergeMetrics.deferredBlocks.mark(-1L);
         }

         this.mergeManager.pushMergeMetrics.deferredBlockBytes.dec(totalSize);
         this.deferredBufs = null;
      }

      private void freeDeferredBufs() {
         if (this.deferredBufs != null && !this.deferredBufs.isEmpty()) {
            long totalSize = 0L;

            for(ByteBuffer deferredBuf : this.deferredBufs) {
               totalSize += (long)deferredBuf.limit();
               this.mergeManager.pushMergeMetrics.deferredBlocks.mark(-1L);
            }

            this.mergeManager.pushMergeMetrics.deferredBlockBytes.dec(totalSize);
         }

         this.deferredBufs = null;
      }

      private void abortIfNecessary() {
         if (this.partitionInfo.shouldAbort(this.mergeManager.ioExceptionsThresholdDuringMerge)) {
            this.freeDeferredBufs();
            throw new IllegalStateException(String.format("%s when merging %s", "IOExceptions exceeded the threshold", this.streamId));
         }
      }

      private void updateIgnoredBlockBytes() {
         if (this.receivedBytes > 0L) {
            this.mergeManager.pushMergeMetrics.ignoredBlockBytes.mark(this.receivedBytes);
            this.receivedBytes = 0L;
         }

      }

      private void incrementIOExceptionsAndAbortIfNecessary() {
         this.partitionInfo.incrementIOExceptions();
         this.abortIfNecessary();
      }

      private boolean isStale(AppShuffleMergePartitionsInfo appShuffleMergePartitionsInfo, int shuffleMergeId) {
         return null == appShuffleMergePartitionsInfo || appShuffleMergePartitionsInfo.shuffleMergeId > shuffleMergeId;
      }

      private boolean isTooLate(AppShuffleMergePartitionsInfo appShuffleMergePartitionsInfo, int reduceId) {
         return null == appShuffleMergePartitionsInfo || appShuffleMergePartitionsInfo.isFinalized() || !appShuffleMergePartitionsInfo.shuffleMergePartitions.containsKey(reduceId);
      }

      public void onData(String streamId, ByteBuffer buf) throws IOException {
         this.receivedBytes += (long)buf.remaining();
         synchronized(this.partitionInfo) {
            AppShuffleMergePartitionsInfo info = (AppShuffleMergePartitionsInfo)this.appShuffleInfo.shuffles.get(this.partitionInfo.appAttemptShuffleMergeId.shuffleId);
            boolean isStaleBlockPush = this.isStale(info, this.partitionInfo.appAttemptShuffleMergeId.shuffleMergeId);
            boolean isTooLateBlockPush = this.isTooLate(info, this.partitionInfo.reduceId);
            if (!isStaleBlockPush && !isTooLateBlockPush) {
               if (this.allowedToWrite()) {
                  if (this.isDuplicateBlock()) {
                     this.freeDeferredBufs();
                     return;
                  }

                  this.abortIfNecessary();
                  RemoteBlockPushResolver.logger.trace("{} onData writable", this.partitionInfo);
                  if (this.partitionInfo.getCurrentMapIndex() < 0) {
                     this.partitionInfo.setCurrentMapIndex(this.mapIndex);
                  }

                  this.isWriting = true;

                  try {
                     if (this.deferredBufs != null && !this.deferredBufs.isEmpty()) {
                        this.writeDeferredBufs();
                     }

                     this.writeBuf(buf);
                  } catch (IOException ioe) {
                     this.incrementIOExceptionsAndAbortIfNecessary();
                     throw ioe;
                  }
               } else {
                  RemoteBlockPushResolver.logger.trace("{} onData deferred", this.partitionInfo);
                  if (this.deferredBufs == null) {
                     this.deferredBufs = new ArrayList();
                  }

                  int deferredLen = buf.remaining();
                  ByteBuffer deferredBuf = ByteBuffer.allocate(deferredLen);
                  deferredBuf.put(buf);
                  deferredBuf.flip();
                  this.deferredBufs.add(deferredBuf);
                  this.mergeManager.pushMergeMetrics.deferredBlockBytes.inc((long)deferredLen);
                  this.mergeManager.pushMergeMetrics.deferredBlocks.mark();
               }

            } else {
               this.freeDeferredBufs();
               if (isTooLateBlockPush) {
                  this.mergeManager.pushMergeMetrics.lateBlockPushes.mark();
               } else {
                  this.mergeManager.pushMergeMetrics.staleBlockPushes.mark();
               }

            }
         }
      }

      public void onComplete(String streamId) throws IOException {
         synchronized(this.partitionInfo) {
            RemoteBlockPushResolver.logger.trace("{} onComplete invoked", this.partitionInfo);
            AppShuffleMergePartitionsInfo info = (AppShuffleMergePartitionsInfo)this.appShuffleInfo.shuffles.get(this.partitionInfo.appAttemptShuffleMergeId.shuffleId);
            if (this.isTooLate(info, this.partitionInfo.reduceId)) {
               this.freeDeferredBufs();
               this.mergeManager.pushMergeMetrics.lateBlockPushes.mark();
               throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.TOO_LATE_BLOCK_PUSH.id(), streamId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(streamId, ReturnCode.TOO_LATE_BLOCK_PUSH));
            }

            if (this.isStale(info, this.partitionInfo.appAttemptShuffleMergeId.shuffleMergeId)) {
               this.freeDeferredBufs();
               this.mergeManager.pushMergeMetrics.staleBlockPushes.mark();
               throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.STALE_BLOCK_PUSH.id(), streamId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(streamId, ReturnCode.STALE_BLOCK_PUSH));
            }

            if (!this.allowedToWrite()) {
               this.freeDeferredBufs();
               this.mergeManager.pushMergeMetrics.blockAppendCollisions.mark();
               throw new BlockPushNonFatalFailure((new BlockPushReturnCode(ReturnCode.BLOCK_APPEND_COLLISION_DETECTED.id(), streamId)).toByteBuffer(), BlockPushNonFatalFailure.getErrorMsg(streamId, ReturnCode.BLOCK_APPEND_COLLISION_DETECTED));
            }

            if (this.isDuplicateBlock()) {
               this.freeDeferredBufs();
               this.updateIgnoredBlockBytes();
               return;
            }

            if (this.partitionInfo.getCurrentMapIndex() < 0) {
               try {
                  if (this.deferredBufs != null && !this.deferredBufs.isEmpty()) {
                     this.abortIfNecessary();
                     this.isWriting = true;
                     this.writeDeferredBufs();
                  }
               } catch (IOException ioe) {
                  this.incrementIOExceptionsAndAbortIfNecessary();
                  throw ioe;
               }
            }

            long updatedPos = this.partitionInfo.getDataFilePos() + (long)this.length;
            boolean indexUpdated = false;
            if (updatedPos - this.partitionInfo.getLastChunkOffset() >= (long)this.mergeManager.minChunkSize) {
               try {
                  this.partitionInfo.updateChunkInfo(updatedPos, this.mapIndex);
                  indexUpdated = true;
               } catch (IOException var9) {
                  this.incrementIOExceptionsAndAbortIfNecessary();
               }
            }

            this.partitionInfo.setDataFilePos(updatedPos);
            this.partitionInfo.setCurrentMapIndex(-1);
            this.partitionInfo.blockMerged(this.mapIndex);
            if (indexUpdated) {
               this.partitionInfo.resetChunkTracker();
            }
         }

         this.isWriting = false;
      }

      public void onFailure(String streamId, Throwable throwable) throws IOException {
         if (RemoteBlockPushResolver.ERROR_HANDLER.shouldLogError(throwable)) {
            RemoteBlockPushResolver.logger.error("Encountered issue when merging {}", throwable, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, streamId)});
         } else {
            RemoteBlockPushResolver.logger.debug("Encountered issue when merging {}", streamId, throwable);
         }

         this.updateIgnoredBlockBytes();
         if (this.isWriting) {
            synchronized(this.partitionInfo) {
               AppShuffleMergePartitionsInfo info = (AppShuffleMergePartitionsInfo)this.appShuffleInfo.shuffles.get(this.partitionInfo.appAttemptShuffleMergeId.shuffleId);
               if (!this.isTooLate(info, this.partitionInfo.reduceId) && !this.isStale(info, this.partitionInfo.appAttemptShuffleMergeId.shuffleMergeId)) {
                  RemoteBlockPushResolver.logger.debug("{} encountered failure", this.partitionInfo);
                  this.partitionInfo.setCurrentMapIndex(-1);
               }
            }
         }

         this.isWriting = false;
      }

      @VisibleForTesting
      AppShufflePartitionInfo getPartitionInfo() {
         return this.partitionInfo;
      }
   }

   public static class AppAttemptId {
      public final String appId;
      public final int attemptId;

      @JsonCreator
      public AppAttemptId(@JsonProperty("appId") String appId, @JsonProperty("attemptId") int attemptId) {
         this.appId = appId;
         this.attemptId = attemptId;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AppAttemptId appAttemptId = (AppAttemptId)o;
            return this.attemptId == appAttemptId.attemptId && Objects.equals(this.appId, appAttemptId.appId);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.appId, this.attemptId});
      }

      public String toString() {
         return String.format("Application %s_%s", this.appId, this.attemptId);
      }
   }

   public static class AppShuffleMergePartitionsInfo {
      private static final Map SHUFFLE_FINALIZED_MARKER = Collections.emptyMap();
      private final int shuffleMergeId;
      private final Map shuffleMergePartitions;
      private final AtomicReference reduceIds = new AtomicReference(new int[0]);

      public AppShuffleMergePartitionsInfo(int shuffleMergeId, boolean shuffleFinalized) {
         this.shuffleMergeId = shuffleMergeId;
         this.shuffleMergePartitions = (Map)(shuffleFinalized ? SHUFFLE_FINALIZED_MARKER : new ConcurrentHashMap());
      }

      @VisibleForTesting
      public Map getShuffleMergePartitions() {
         return this.shuffleMergePartitions;
      }

      public boolean isFinalized() {
         return this.shuffleMergePartitions == SHUFFLE_FINALIZED_MARKER;
      }

      public void setReduceIds(int[] reduceIds) {
         this.reduceIds.set(reduceIds);
      }

      public int[] getReduceIds() {
         return (int[])this.reduceIds.get();
      }
   }

   public static class AppAttemptShuffleMergeId {
      public final String appId;
      public final int attemptId;
      public final int shuffleId;
      public final int shuffleMergeId;

      @JsonCreator
      public AppAttemptShuffleMergeId(@JsonProperty("appId") String appId, @JsonProperty("attemptId") int attemptId, @JsonProperty("shuffleId") int shuffleId, @JsonProperty("shuffleMergeId") int shuffleMergeId) {
         Preconditions.checkArgument(appId != null, "app id is null");
         this.appId = appId;
         this.attemptId = attemptId;
         this.shuffleId = shuffleId;
         this.shuffleMergeId = shuffleMergeId;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AppAttemptShuffleMergeId appAttemptShuffleMergeId = (AppAttemptShuffleMergeId)o;
            return this.attemptId == appAttemptShuffleMergeId.attemptId && this.shuffleId == appAttemptShuffleMergeId.shuffleId && this.shuffleMergeId == appAttemptShuffleMergeId.shuffleMergeId && Objects.equals(this.appId, appAttemptShuffleMergeId.appId);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.appId, this.attemptId, this.shuffleId, this.shuffleMergeId});
      }

      public String toString() {
         return String.format("Application %s_%s shuffleId %s shuffleMergeId %s", this.appId, this.attemptId, this.shuffleId, this.shuffleMergeId);
      }
   }

   public static class AppShufflePartitionInfo {
      private final AppAttemptShuffleMergeId appAttemptShuffleMergeId;
      private final int reduceId;
      private final File dataFile;
      public final FileChannel dataChannel;
      private final MergeShuffleFile indexFile;
      private final MergeShuffleFile metaFile;
      private final Cleaner.Cleanable cleanable;
      private long dataFilePos;
      private int currentMapIndex;
      private RoaringBitmap mapTracker;
      private long lastChunkOffset;
      private int lastMergedMapIndex = -1;
      private RoaringBitmap chunkTracker;
      private int numIOExceptions = 0;
      private boolean indexMetaUpdateFailed;

      AppShufflePartitionInfo(AppAttemptShuffleMergeId appAttemptShuffleMergeId, int reduceId, File dataFile, MergeShuffleFile indexFile, MergeShuffleFile metaFile) throws IOException {
         this.appAttemptShuffleMergeId = appAttemptShuffleMergeId;
         this.reduceId = reduceId;
         this.dataChannel = (new FileOutputStream(dataFile)).getChannel();
         this.dataFile = dataFile;
         this.indexFile = indexFile;
         this.metaFile = metaFile;
         this.currentMapIndex = -1;
         this.updateChunkInfo(0L, -1);
         this.dataFilePos = 0L;
         this.mapTracker = new RoaringBitmap();
         this.chunkTracker = new RoaringBitmap();
         this.cleanable = RemoteBlockPushResolver.CLEANER.register(this, new ResourceCleaner(this.dataChannel, indexFile, metaFile, appAttemptShuffleMergeId, reduceId));
      }

      public long getDataFilePos() {
         return this.dataFilePos;
      }

      public void setDataFilePos(long dataFilePos) {
         RemoteBlockPushResolver.logger.trace("{} current pos {} update pos {}", new Object[]{this, this.dataFilePos, dataFilePos});
         this.dataFilePos = dataFilePos;
      }

      int getCurrentMapIndex() {
         return this.currentMapIndex;
      }

      void setCurrentMapIndex(int mapIndex) {
         RemoteBlockPushResolver.logger.trace("{} mapIndex {} current mapIndex {}", new Object[]{this, this.currentMapIndex, mapIndex});
         this.currentMapIndex = mapIndex;
      }

      long getLastChunkOffset() {
         return this.lastChunkOffset;
      }

      void blockMerged(int mapIndex) {
         RemoteBlockPushResolver.logger.debug("{} updated merging mapIndex {}", this, mapIndex);
         this.mapTracker.add(mapIndex);
         this.chunkTracker.add(mapIndex);
         this.lastMergedMapIndex = mapIndex;
      }

      void resetChunkTracker() {
         this.chunkTracker.clear();
      }

      void updateChunkInfo(long chunkOffset, int mapIndex) throws IOException {
         try {
            RemoteBlockPushResolver.logger.trace("{} index current {} updated {}", new Object[]{this, this.lastChunkOffset, chunkOffset});
            if (this.indexMetaUpdateFailed) {
               this.indexFile.getChannel().position(this.indexFile.getPos());
            }

            this.indexFile.getDos().writeLong(chunkOffset);
            this.writeChunkTracker(mapIndex);
            this.indexFile.updatePos(8L);
            this.lastChunkOffset = chunkOffset;
            this.indexMetaUpdateFailed = false;
         } catch (IOException ioe) {
            RemoteBlockPushResolver.logger.warn("{} reduceId {} update to index/meta failed", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, this.appAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, this.reduceId)});
            this.indexMetaUpdateFailed = true;
            throw ioe;
         }
      }

      private void writeChunkTracker(int mapIndex) throws IOException {
         if (mapIndex != -1) {
            this.chunkTracker.add(mapIndex);
            RemoteBlockPushResolver.logger.trace("{} mapIndex {} write chunk to meta file", this, mapIndex);
            if (this.indexMetaUpdateFailed) {
               this.metaFile.getChannel().position(this.metaFile.getPos());
            }

            this.chunkTracker.serialize(this.metaFile.getDos());
            this.metaFile.updatePos(this.metaFile.getChannel().position() - this.metaFile.getPos());
         }
      }

      private void incrementIOExceptions() {
         ++this.numIOExceptions;
      }

      private boolean shouldAbort(int ioExceptionsThresholdDuringMerge) {
         return this.numIOExceptions > ioExceptionsThresholdDuringMerge;
      }

      private void finalizePartition() throws IOException {
         if (this.dataFilePos != this.lastChunkOffset) {
            try {
               this.updateChunkInfo(this.dataFilePos, this.lastMergedMapIndex);
            } catch (IOException var2) {
            }
         }

         RemoteBlockPushResolver.logger.trace("{} reduceId {} truncating files data {} index {} meta {}", new Object[]{this.appAttemptShuffleMergeId, this.reduceId, this.lastChunkOffset, this.indexFile.getPos(), this.metaFile.getPos()});
         this.dataChannel.truncate(this.lastChunkOffset);
         this.indexFile.getChannel().truncate(this.indexFile.getPos());
         this.metaFile.getChannel().truncate(this.metaFile.getPos());
      }

      private void deleteAllFiles() {
         if (!this.dataFile.delete()) {
            RemoteBlockPushResolver.logger.info("Error deleting data file for {} reduceId {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, this.appAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, this.reduceId)});
         }

         this.metaFile.delete();
         this.indexFile.delete();
      }

      public String toString() {
         return String.format("Application %s_%s shuffleId %s shuffleMergeId %s reduceId %s", this.appAttemptShuffleMergeId.appId, this.appAttemptShuffleMergeId.attemptId, this.appAttemptShuffleMergeId.shuffleId, this.appAttemptShuffleMergeId.shuffleMergeId, this.reduceId);
      }

      @VisibleForTesting
      MergeShuffleFile getIndexFile() {
         return this.indexFile;
      }

      @VisibleForTesting
      MergeShuffleFile getMetaFile() {
         return this.metaFile;
      }

      @VisibleForTesting
      FileChannel getDataChannel() {
         return this.dataChannel;
      }

      @VisibleForTesting
      public RoaringBitmap getMapTracker() {
         return this.mapTracker;
      }

      @VisibleForTesting
      int getNumIOExceptions() {
         return this.numIOExceptions;
      }

      @VisibleForTesting
      Cleaner.Cleanable getCleanable() {
         return this.cleanable;
      }

      private static record ResourceCleaner(FileChannel dataChannel, MergeShuffleFile indexFile, MergeShuffleFile metaFile, AppAttemptShuffleMergeId appAttemptShuffleMergeId, int reduceId) implements Runnable {
         public void run() {
            this.closeAllFiles(this.dataChannel, this.indexFile, this.metaFile, this.appAttemptShuffleMergeId, this.reduceId);
         }

         private void closeAllFiles(FileChannel dataChannel, MergeShuffleFile indexFile, MergeShuffleFile metaFile, AppAttemptShuffleMergeId appAttemptShuffleMergeId, int reduceId) {
            try {
               if (dataChannel.isOpen()) {
                  dataChannel.close();
               }
            } catch (IOException var9) {
               RemoteBlockPushResolver.logger.warn("Error closing data channel for {} reduceId {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, reduceId)});
            }

            try {
               metaFile.close();
            } catch (IOException var8) {
               RemoteBlockPushResolver.logger.warn("Error closing meta file for {} reduceId {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, reduceId)});
            }

            try {
               indexFile.close();
            } catch (IOException var7) {
               RemoteBlockPushResolver.logger.warn("Error closing index file for {} reduceId {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ATTEMPT_SHUFFLE_MERGE_ID..MODULE$, appAttemptShuffleMergeId), MDC.of(org.apache.spark.internal.LogKeys.REDUCE_ID..MODULE$, reduceId)});
            }

         }
      }
   }

   @VisibleForTesting
   public static class AppPathsInfo {
      @JsonFormat(
         shape = Shape.ARRAY
      )
      @JsonProperty("activeLocalDirs")
      private final String[] activeLocalDirs;
      @JsonProperty("subDirsPerLocalDir")
      private final int subDirsPerLocalDir;

      @JsonCreator
      public AppPathsInfo(@JsonFormat(shape = Shape.ARRAY) @JsonProperty("activeLocalDirs") String[] activeLocalDirs, @JsonProperty("subDirsPerLocalDir") int subDirsPerLocalDir) {
         this.activeLocalDirs = activeLocalDirs;
         this.subDirsPerLocalDir = subDirsPerLocalDir;
      }

      private AppPathsInfo(String appId, String[] localDirs, String mergeDirectory, int subDirsPerLocalDir) {
         this.activeLocalDirs = (String[])Arrays.stream(localDirs).map((localDir) -> Paths.get(localDir).getParent().resolve(mergeDirectory).toFile().getPath()).toArray((x$0) -> new String[x$0]);
         this.subDirsPerLocalDir = subDirsPerLocalDir;
         if (RemoteBlockPushResolver.logger.isInfoEnabled()) {
            RemoteBlockPushResolver.logger.info("Updated active local dirs {} and sub dirs {} for application {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATHS..MODULE$, Arrays.toString(this.activeLocalDirs)), MDC.of(org.apache.spark.internal.LogKeys.NUM_SUB_DIRS..MODULE$, subDirsPerLocalDir), MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)});
         }

      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AppPathsInfo appPathsInfo = (AppPathsInfo)o;
            return this.subDirsPerLocalDir == appPathsInfo.subDirsPerLocalDir && Arrays.equals(this.activeLocalDirs, appPathsInfo.activeLocalDirs);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.subDirsPerLocalDir}) * 41 + Arrays.hashCode(this.activeLocalDirs);
      }
   }

   public static class AppShuffleInfo {
      @VisibleForTesting
      final String appId;
      @VisibleForTesting
      final int attemptId;
      private final AppPathsInfo appPathsInfo;
      private final ConcurrentMap shuffles;

      AppShuffleInfo(String appId, int attemptId, AppPathsInfo appPathsInfo) {
         this.appId = appId;
         this.attemptId = attemptId;
         this.appPathsInfo = appPathsInfo;
         this.shuffles = new ConcurrentHashMap();
      }

      @VisibleForTesting
      public AppPathsInfo getAppPathsInfo() {
         return this.appPathsInfo;
      }

      @VisibleForTesting
      public ConcurrentMap getShuffles() {
         return this.shuffles;
      }

      @VisibleForTesting
      String getFilePath(String filename) {
         String targetFile = ExecutorDiskUtils.getFilePath(this.appPathsInfo.activeLocalDirs, this.appPathsInfo.subDirsPerLocalDir, filename);
         RemoteBlockPushResolver.logger.debug("Get merged file {}", targetFile);
         return targetFile;
      }

      private String generateFileName(String appId, int shuffleId, int shuffleMergeId, int reduceId) {
         return String.format("%s_%s_%d_%d_%d", "shuffleMerged", appId, shuffleId, shuffleMergeId, reduceId);
      }

      public File getMergedShuffleDataFile(int shuffleId, int shuffleMergeId, int reduceId) {
         String fileName = String.format("%s.data", this.generateFileName(this.appId, shuffleId, shuffleMergeId, reduceId));
         return new File(this.getFilePath(fileName));
      }

      public String getMergedShuffleIndexFilePath(int shuffleId, int shuffleMergeId, int reduceId) {
         String indexName = String.format("%s.index", this.generateFileName(this.appId, shuffleId, shuffleMergeId, reduceId));
         return this.getFilePath(indexName);
      }

      public File getMergedShuffleMetaFile(int shuffleId, int shuffleMergeId, int reduceId) {
         String metaName = String.format("%s.meta", this.generateFileName(this.appId, shuffleId, shuffleMergeId, reduceId));
         return new File(this.getFilePath(metaName));
      }
   }

   @VisibleForTesting
   public static class MergeShuffleFile {
      private final FileChannel channel;
      private final DataOutputStream dos;
      private long pos;
      private File file;

      @VisibleForTesting
      MergeShuffleFile(File file) throws IOException {
         FileOutputStream fos = new FileOutputStream(file);
         this.channel = fos.getChannel();
         this.dos = new DataOutputStream(fos);
         this.file = file;
      }

      private void updatePos(long numBytes) {
         this.pos += numBytes;
      }

      void close() throws IOException {
         if (this.channel.isOpen()) {
            this.dos.close();
         }

      }

      void delete() {
         try {
            if (null != this.file) {
               this.file.delete();
            }
         } finally {
            this.file = null;
         }

      }

      @VisibleForTesting
      public DataOutputStream getDos() {
         return this.dos;
      }

      @VisibleForTesting
      FileChannel getChannel() {
         return this.channel;
      }

      @VisibleForTesting
      long getPos() {
         return this.pos;
      }
   }

   static class PushMergeMetrics implements MetricSet {
      static final String BLOCK_APPEND_COLLISIONS_METRIC = "blockAppendCollisions";
      static final String LATE_BLOCK_PUSHES_METRIC = "lateBlockPushes";
      static final String BLOCK_BYTES_WRITTEN_METRIC = "blockBytesWritten";
      static final String DEFERRED_BLOCK_BYTES_METRIC = "deferredBlockBytes";
      static final String DEFERRED_BLOCKS_METRIC = "deferredBlocks";
      static final String STALE_BLOCK_PUSHES_METRIC = "staleBlockPushes";
      static final String IGNORED_BLOCK_BYTES_METRIC = "ignoredBlockBytes";
      private final Map allMetrics = new HashMap();
      private final Meter blockAppendCollisions = new Meter();
      private final Meter lateBlockPushes;
      private final Meter blockBytesWritten;
      private final Counter deferredBlockBytes;
      private final Meter deferredBlocks;
      private final Meter staleBlockPushes;
      private final Meter ignoredBlockBytes;

      private PushMergeMetrics() {
         this.allMetrics.put("blockAppendCollisions", this.blockAppendCollisions);
         this.lateBlockPushes = new Meter();
         this.allMetrics.put("lateBlockPushes", this.lateBlockPushes);
         this.blockBytesWritten = new Meter();
         this.allMetrics.put("blockBytesWritten", this.blockBytesWritten);
         this.deferredBlockBytes = new Counter();
         this.allMetrics.put("deferredBlockBytes", this.deferredBlockBytes);
         this.deferredBlocks = new Meter();
         this.allMetrics.put("deferredBlocks", this.deferredBlocks);
         this.staleBlockPushes = new Meter();
         this.allMetrics.put("staleBlockPushes", this.staleBlockPushes);
         this.ignoredBlockBytes = new Meter();
         this.allMetrics.put("ignoredBlockBytes", this.ignoredBlockBytes);
      }

      public Map getMetrics() {
         return this.allMetrics;
      }
   }
}
