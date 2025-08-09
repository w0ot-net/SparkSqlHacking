package org.apache.spark.network.shuffle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_NAME.;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.shuffledb.DB;
import org.apache.spark.network.shuffledb.DBBackend;
import org.apache.spark.network.shuffledb.DBIterator;
import org.apache.spark.network.shuffledb.StoreVersion;
import org.apache.spark.network.util.DBProvider;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import org.sparkproject.guava.collect.Maps;

public class ExternalShuffleBlockResolver {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ExternalShuffleBlockResolver.class);
   private static final ObjectMapper mapper = new ObjectMapper();
   private static final String APP_KEY_PREFIX = "AppExecShuffleInfo";
   private static final StoreVersion CURRENT_VERSION = new StoreVersion(1, 0);
   @VisibleForTesting
   final ConcurrentMap executors;
   private final LoadingCache shuffleIndexCache;
   private final Executor directoryCleaner;
   private final TransportConf conf;
   private final boolean rddFetchEnabled;
   @VisibleForTesting
   final File registeredExecutorFile;
   @VisibleForTesting
   final DB db;

   public ExternalShuffleBlockResolver(TransportConf conf, File registeredExecutorFile) throws IOException {
      this(conf, registeredExecutorFile, Executors.newSingleThreadExecutor(NettyUtils.createThreadFactory("spark-shuffle-directory-cleaner")));
   }

   @VisibleForTesting
   ExternalShuffleBlockResolver(TransportConf conf, File registeredExecutorFile, Executor directoryCleaner) throws IOException {
      this.conf = conf;
      this.rddFetchEnabled = Boolean.parseBoolean(conf.get("spark.shuffle.service.fetch.rdd.enabled", "false"));
      this.registeredExecutorFile = registeredExecutorFile;
      String indexCacheSize = conf.get("spark.shuffle.service.index.cache.size", "100m");
      CacheLoader<String, ShuffleIndexInformation> indexCacheLoader = new CacheLoader() {
         public ShuffleIndexInformation load(String filePath) throws IOException {
            return new ShuffleIndexInformation(filePath);
         }
      };
      this.shuffleIndexCache = CacheBuilder.newBuilder().maximumWeight(JavaUtils.byteStringAsBytes(indexCacheSize)).weigher((filePath, indexInfo) -> indexInfo.getRetainedMemorySize()).build(indexCacheLoader);
      String dbBackendName = conf.get("spark.shuffle.service.db.backend", DBBackend.ROCKSDB.name());
      DBBackend dbBackend = DBBackend.byName(dbBackendName);
      this.db = DBProvider.initDB(dbBackend, this.registeredExecutorFile, CURRENT_VERSION, mapper);
      if (this.db != null) {
         logger.info("Use {} as the implementation of {}", new MDC[]{MDC.of(.MODULE$, dbBackend), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_KEY..MODULE$, "spark.shuffle.service.db.backend")});
         this.executors = reloadRegisteredExecutors(this.db);
      } else {
         this.executors = Maps.newConcurrentMap();
      }

      this.directoryCleaner = directoryCleaner;
   }

   public int getRegisteredExecutorsSize() {
      return this.executors.size();
   }

   public void registerExecutor(String appId, String execId, ExecutorShuffleInfo executorInfo) {
      AppExecId fullId = new AppExecId(appId, execId);
      logger.info("Registered executor {} with {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_EXECUTOR_ID..MODULE$, fullId), MDC.of(org.apache.spark.internal.LogKeys.EXECUTOR_SHUFFLE_INFO..MODULE$, executorInfo)});

      try {
         if (this.db != null && AppsWithRecoveryDisabled.isRecoveryEnabledForApp(appId)) {
            byte[] key = dbAppExecKey(fullId);
            byte[] value = mapper.writeValueAsString(executorInfo).getBytes(StandardCharsets.UTF_8);
            this.db.put(key, value);
         }
      } catch (Exception e) {
         logger.error("Error saving registered executors", e);
      }

      this.executors.put(fullId, executorInfo);
   }

   public ManagedBuffer getBlockData(String appId, String execId, int shuffleId, long mapId, int reduceId) {
      return this.getContinuousBlocksData(appId, execId, shuffleId, mapId, reduceId, reduceId + 1);
   }

   public ManagedBuffer getContinuousBlocksData(String appId, String execId, int shuffleId, long mapId, int startReduceId, int endReduceId) {
      ExecutorShuffleInfo executor = (ExecutorShuffleInfo)this.executors.get(new AppExecId(appId, execId));
      if (executor == null) {
         throw new RuntimeException(String.format("Executor is not registered (appId=%s, execId=%s)", appId, execId));
      } else {
         return this.getSortBasedShuffleBlockData(executor, shuffleId, mapId, startReduceId, endReduceId);
      }
   }

   public ManagedBuffer getRddBlockData(String appId, String execId, int rddId, int splitIndex) {
      ExecutorShuffleInfo executor = (ExecutorShuffleInfo)this.executors.get(new AppExecId(appId, execId));
      if (executor == null) {
         throw new RuntimeException(String.format("Executor is not registered (appId=%s, execId=%s)", appId, execId));
      } else {
         return this.getDiskPersistedRddBlockData(executor, rddId, splitIndex);
      }
   }

   public void applicationRemoved(String appId, boolean cleanupLocalDirs) {
      logger.info("Application {} removed, cleanupLocalDirs = {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), MDC.of(org.apache.spark.internal.LogKeys.CLEANUP_LOCAL_DIRS..MODULE$, cleanupLocalDirs)});
      Iterator<Map.Entry<AppExecId, ExecutorShuffleInfo>> it = this.executors.entrySet().iterator();

      while(it.hasNext()) {
         Map.Entry<AppExecId, ExecutorShuffleInfo> entry = (Map.Entry)it.next();
         AppExecId fullId = (AppExecId)entry.getKey();
         ExecutorShuffleInfo executor = (ExecutorShuffleInfo)entry.getValue();
         if (appId.equals(fullId.appId)) {
            it.remove();
            if (this.db != null && AppsWithRecoveryDisabled.isRecoveryEnabledForApp(fullId.appId)) {
               try {
                  this.db.delete(dbAppExecKey(fullId));
               } catch (IOException e) {
                  logger.error("Error deleting {} from executor state db", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId)});
               }
            }

            if (cleanupLocalDirs) {
               logger.info("Cleaning up executor {}'s {} local dirs", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_EXECUTOR_ID..MODULE$, fullId), MDC.of(org.apache.spark.internal.LogKeys.NUM_LOCAL_DIRS..MODULE$, executor.localDirs.length)});
               this.directoryCleaner.execute(() -> this.deleteExecutorDirs(executor.localDirs));
            }
         }
      }

   }

   public void executorRemoved(String executorId, String appId) {
      logger.info("Clean up non-shuffle and non-RDD files associated with the finished executor {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)});
      AppExecId fullId = new AppExecId(appId, executorId);
      ExecutorShuffleInfo executor = (ExecutorShuffleInfo)this.executors.get(fullId);
      if (executor == null) {
         logger.info("Executor is not registered (appId={}, execId={})", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), MDC.of(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)});
      } else {
         logger.info("Cleaning up non-shuffle and non-RDD files in executor {}'s {} local dirs", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_EXECUTOR_ID..MODULE$, fullId), MDC.of(org.apache.spark.internal.LogKeys.NUM_LOCAL_DIRS..MODULE$, executor.localDirs.length)});
         this.directoryCleaner.execute(() -> this.deleteNonShuffleServiceServedFiles(executor.localDirs));
      }

   }

   private void deleteExecutorDirs(String[] dirs) {
      for(String localDir : dirs) {
         try {
            JavaUtils.deleteRecursively(new File(localDir));
            logger.debug("Successfully cleaned up directory: {}", localDir);
         } catch (Exception e) {
            logger.error("Failed to delete directory: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, localDir)});
         }
      }

   }

   private void deleteNonShuffleServiceServedFiles(String[] dirs) {
      FilenameFilter filter = (dir, name) -> !name.endsWith(".index") && !name.endsWith(".data") && (!this.rddFetchEnabled || !name.startsWith("rdd_"));

      for(String localDir : dirs) {
         try {
            JavaUtils.deleteRecursively(new File(localDir), filter);
            logger.debug("Successfully cleaned up files not served by shuffle service in directory: {}", localDir);
         } catch (Exception e) {
            logger.error("Failed to delete files not served by shuffle service in directory: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, localDir)});
         }
      }

   }

   private ManagedBuffer getSortBasedShuffleBlockData(ExecutorShuffleInfo executor, int shuffleId, long mapId, int startReduceId, int endReduceId) {
      String indexFilePath = ExecutorDiskUtils.getFilePath(executor.localDirs, executor.subDirsPerLocalDir, "shuffle_" + shuffleId + "_" + mapId + "_0.index");

      try {
         ShuffleIndexInformation shuffleIndexInformation = (ShuffleIndexInformation)this.shuffleIndexCache.get(indexFilePath);
         ShuffleIndexRecord shuffleIndexRecord = shuffleIndexInformation.getIndex(startReduceId, endReduceId);
         return new FileSegmentManagedBuffer(this.conf, new File(ExecutorDiskUtils.getFilePath(executor.localDirs, executor.subDirsPerLocalDir, "shuffle_" + shuffleId + "_" + mapId + "_0.data")), shuffleIndexRecord.offset(), shuffleIndexRecord.length());
      } catch (ExecutionException e) {
         throw new RuntimeException("Failed to open file: " + indexFilePath, e);
      }
   }

   public ManagedBuffer getDiskPersistedRddBlockData(ExecutorShuffleInfo executor, int rddId, int splitIndex) {
      File file = new File(ExecutorDiskUtils.getFilePath(executor.localDirs, executor.subDirsPerLocalDir, "rdd_" + rddId + "_" + splitIndex));
      long fileLength = file.length();
      ManagedBuffer res = null;
      if (file.exists()) {
         res = new FileSegmentManagedBuffer(this.conf, file, 0L, fileLength);
      }

      return res;
   }

   void close() {
      if (this.db != null) {
         try {
            this.db.close();
         } catch (IOException e) {
            logger.error("Exception closing RocksDB with registered executors", e);
         }
      }

   }

   public int removeBlocks(String appId, String execId, String[] blockIds) {
      ExecutorShuffleInfo executor = (ExecutorShuffleInfo)this.executors.get(new AppExecId(appId, execId));
      if (executor == null) {
         throw new RuntimeException(String.format("Executor is not registered (appId=%s, execId=%s)", appId, execId));
      } else {
         int numRemovedBlocks = 0;

         for(String blockId : blockIds) {
            File file = new File(ExecutorDiskUtils.getFilePath(executor.localDirs, executor.subDirsPerLocalDir, blockId));
            if (file.delete()) {
               ++numRemovedBlocks;
            } else {
               logger.warn("Failed to delete block: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PATH..MODULE$, file.getAbsolutePath())});
            }
         }

         return numRemovedBlocks;
      }
   }

   public Map getLocalDirs(String appId, Set execIds) {
      return (Map)execIds.stream().map((exec) -> {
         ExecutorShuffleInfo info = (ExecutorShuffleInfo)this.executors.get(new AppExecId(appId, exec));
         if (info == null) {
            throw new RuntimeException(String.format("Executor is not registered (appId=%s, execId=%s)", appId, exec));
         } else {
            return Pair.of(exec, info.localDirs);
         }
      }).collect(Collectors.toMap(Pair::getKey, Pair::getValue));
   }

   public Cause diagnoseShuffleBlockCorruption(String appId, String execId, int shuffleId, long mapId, int reduceId, long checksumByReader, String algorithm) {
      ExecutorShuffleInfo executor = (ExecutorShuffleInfo)this.executors.get(new AppExecId(appId, execId));
      String fileName = "shuffle_" + shuffleId + "_" + mapId + "_0.checksum." + algorithm;
      File checksumFile = new File(ExecutorDiskUtils.getFilePath(executor.localDirs, executor.subDirsPerLocalDir, fileName));
      ManagedBuffer data = this.getBlockData(appId, execId, shuffleId, mapId, reduceId);
      return ShuffleChecksumHelper.diagnoseCorruption(algorithm, checksumFile, reduceId, data, checksumByReader);
   }

   private static byte[] dbAppExecKey(AppExecId appExecId) throws IOException {
      String appExecJson = mapper.writeValueAsString(appExecId);
      String key = "AppExecShuffleInfo;" + appExecJson;
      return key.getBytes(StandardCharsets.UTF_8);
   }

   private static AppExecId parseDbAppExecKey(String s) throws IOException {
      if (!s.startsWith("AppExecShuffleInfo")) {
         throw new IllegalArgumentException("expected a string starting with AppExecShuffleInfo");
      } else {
         String json = s.substring("AppExecShuffleInfo".length() + 1);
         AppExecId parsed = (AppExecId)mapper.readValue(json, AppExecId.class);
         return parsed;
      }
   }

   @VisibleForTesting
   static ConcurrentMap reloadRegisteredExecutors(DB db) throws IOException {
      ConcurrentMap<AppExecId, ExecutorShuffleInfo> registeredExecutors = Maps.newConcurrentMap();
      if (db != null) {
         DBIterator itr = db.iterator();

         try {
            itr.seek("AppExecShuffleInfo".getBytes(StandardCharsets.UTF_8));

            while(itr.hasNext()) {
               Map.Entry<byte[], byte[]> e = (Map.Entry)itr.next();
               String key = new String((byte[])e.getKey(), StandardCharsets.UTF_8);
               if (!key.startsWith("AppExecShuffleInfo")) {
                  break;
               }

               AppExecId id = parseDbAppExecKey(key);
               logger.info("Reloading registered executors: {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.APP_EXECUTOR_ID..MODULE$, id)});
               ExecutorShuffleInfo shuffleInfo = (ExecutorShuffleInfo)mapper.readValue((byte[])e.getValue(), ExecutorShuffleInfo.class);
               registeredExecutors.put(id, shuffleInfo);
            }
         } catch (Throwable var8) {
            if (itr != null) {
               try {
                  itr.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (itr != null) {
            itr.close();
         }
      }

      return registeredExecutors;
   }

   public static class AppExecId {
      public final String appId;
      public final String execId;

      @JsonCreator
      public AppExecId(@JsonProperty("appId") String appId, @JsonProperty("execId") String execId) {
         this.appId = appId;
         this.execId = execId;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            AppExecId appExecId = (AppExecId)o;
            return Objects.equals(this.appId, appExecId.appId) && Objects.equals(this.execId, appExecId.execId);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.appId, this.execId});
      }

      public String toString() {
         return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("appId", this.appId).append("execId", this.execId).toString();
      }
   }
}
