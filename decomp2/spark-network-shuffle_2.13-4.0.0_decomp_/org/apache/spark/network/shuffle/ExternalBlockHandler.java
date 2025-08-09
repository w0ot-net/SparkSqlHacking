package org.apache.spark.network.shuffle;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.RatioGauge;
import com.codahale.metrics.Timer;
import com.codahale.metrics.RatioGauge.Ratio;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.APP_ID.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.MergedBlockMetaResponseCallback;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.protocol.MergedBlockMetaRequest;
import org.apache.spark.network.server.OneForOneStreamManager;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.StreamManager;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.network.shuffle.protocol.AbstractFetchShuffleBlocks;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.BlocksRemoved;
import org.apache.spark.network.shuffle.protocol.CorruptionCause;
import org.apache.spark.network.shuffle.protocol.DiagnoseCorruption;
import org.apache.spark.network.shuffle.protocol.FetchShuffleBlockChunks;
import org.apache.spark.network.shuffle.protocol.FetchShuffleBlocks;
import org.apache.spark.network.shuffle.protocol.FinalizeShuffleMerge;
import org.apache.spark.network.shuffle.protocol.GetLocalDirsForExecutors;
import org.apache.spark.network.shuffle.protocol.LocalDirsForExecutors;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.shuffle.protocol.OpenBlocks;
import org.apache.spark.network.shuffle.protocol.PushBlockStream;
import org.apache.spark.network.shuffle.protocol.RegisterExecutor;
import org.apache.spark.network.shuffle.protocol.RemoveBlocks;
import org.apache.spark.network.shuffle.protocol.RemoveShuffleMerge;
import org.apache.spark.network.shuffle.protocol.StreamHandle;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TimerWithCustomTimeUnit;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.Sets;

public class ExternalBlockHandler extends RpcHandler implements RpcHandler.MergedBlockMetaReqHandler {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(ExternalBlockHandler.class);
   private static final String SHUFFLE_MERGER_IDENTIFIER = "shuffle-push-merger";
   private static final String SHUFFLE_BLOCK_ID = "shuffle";
   private static final String SHUFFLE_CHUNK_ID = "shuffleChunk";
   @VisibleForTesting
   final ExternalShuffleBlockResolver blockManager;
   private final OneForOneStreamManager streamManager;
   private final ShuffleMetrics metrics;
   private final MergedShuffleFileManager mergeManager;

   public ExternalBlockHandler(TransportConf conf, File registeredExecutorFile) throws IOException {
      this((OneForOneStreamManager)(new OneForOneStreamManager()), (ExternalShuffleBlockResolver)(new ExternalShuffleBlockResolver(conf, registeredExecutorFile)), new NoOpMergedShuffleFileManager(conf, (File)null));
   }

   public ExternalBlockHandler(TransportConf conf, File registeredExecutorFile, MergedShuffleFileManager mergeManager) throws IOException {
      this(new OneForOneStreamManager(), new ExternalShuffleBlockResolver(conf, registeredExecutorFile), mergeManager);
   }

   @VisibleForTesting
   public ExternalShuffleBlockResolver getBlockResolver() {
      return this.blockManager;
   }

   @VisibleForTesting
   public ExternalBlockHandler(OneForOneStreamManager streamManager, ExternalShuffleBlockResolver blockManager) {
      this((OneForOneStreamManager)streamManager, (ExternalShuffleBlockResolver)blockManager, new NoOpMergedShuffleFileManager((TransportConf)null, (File)null));
   }

   @VisibleForTesting
   public ExternalBlockHandler(OneForOneStreamManager streamManager, ExternalShuffleBlockResolver blockManager, MergedShuffleFileManager mergeManager) {
      this.metrics = new ShuffleMetrics();
      this.streamManager = streamManager;
      this.blockManager = blockManager;
      this.mergeManager = mergeManager;
   }

   public void receive(TransportClient client, ByteBuffer message, RpcResponseCallback callback) {
      BlockTransferMessage msgObj = BlockTransferMessage.Decoder.fromByteBuffer(message);
      this.handleMessage(msgObj, client, callback);
   }

   public StreamCallbackWithID receiveStream(TransportClient client, ByteBuffer messageHeader, RpcResponseCallback callback) {
      BlockTransferMessage msgObj = BlockTransferMessage.Decoder.fromByteBuffer(messageHeader);
      if (msgObj instanceof PushBlockStream message) {
         this.checkAuth(client, message.appId);
         return this.mergeManager.receiveBlockDataAsStream(message);
      } else {
         throw new UnsupportedOperationException("Unexpected message with #receiveStream: " + String.valueOf(msgObj));
      }
   }

   protected void handleMessage(BlockTransferMessage msgObj, TransportClient client, RpcResponseCallback callback) {
      if (!(msgObj instanceof AbstractFetchShuffleBlocks) && !(msgObj instanceof OpenBlocks)) {
         if (msgObj instanceof RegisterExecutor) {
            RegisterExecutor msg = (RegisterExecutor)msgObj;
            Timer.Context responseDelayContext = this.metrics.registerExecutorRequestLatencyMillis.time();

            try {
               this.checkAuth(client, msg.appId);
               this.blockManager.registerExecutor(msg.appId, msg.execId, msg.executorInfo);
               this.mergeManager.registerExecutor(msg.appId, msg.executorInfo);
               callback.onSuccess(ByteBuffer.wrap(new byte[0]));
            } finally {
               responseDelayContext.stop();
            }
         } else if (msgObj instanceof RemoveBlocks) {
            RemoveBlocks msg = (RemoveBlocks)msgObj;
            this.checkAuth(client, msg.appId);
            int numRemovedBlocks = this.blockManager.removeBlocks(msg.appId, msg.execId, msg.blockIds);
            callback.onSuccess((new BlocksRemoved(numRemovedBlocks)).toByteBuffer());
         } else if (msgObj instanceof GetLocalDirsForExecutors) {
            GetLocalDirsForExecutors msg = (GetLocalDirsForExecutors)msgObj;
            this.checkAuth(client, msg.appId);
            Set<String> execIdsForBlockResolver = Sets.newHashSet(msg.execIds);
            boolean fetchMergedBlockDirs = execIdsForBlockResolver.remove("shuffle-push-merger");
            Map<String, String[]> localDirs = this.blockManager.getLocalDirs(msg.appId, execIdsForBlockResolver);
            if (fetchMergedBlockDirs) {
               localDirs.put("shuffle-push-merger", this.mergeManager.getMergedBlockDirs(msg.appId));
            }

            callback.onSuccess((new LocalDirsForExecutors(localDirs)).toByteBuffer());
         } else if (msgObj instanceof FinalizeShuffleMerge) {
            FinalizeShuffleMerge msg = (FinalizeShuffleMerge)msgObj;
            Timer.Context responseDelayContext = this.metrics.finalizeShuffleMergeLatencyMillis.time();

            try {
               this.checkAuth(client, msg.appId);
               MergeStatuses statuses = this.mergeManager.finalizeShuffleMerge(msg);
               callback.onSuccess(statuses.toByteBuffer());
            } catch (IOException e) {
               throw new RuntimeException(String.format("Error while finalizing shuffle merge for application %s shuffle %d with shuffleMergeId %d", msg.appId, msg.shuffleId, msg.shuffleMergeId), e);
            } finally {
               responseDelayContext.stop();
            }
         } else if (msgObj instanceof RemoveShuffleMerge) {
            RemoveShuffleMerge msg = (RemoveShuffleMerge)msgObj;
            this.checkAuth(client, msg.appId);
            logger.info("Removing shuffle merge data for application {} shuffle {} shuffleMerge {}", new MDC[]{MDC.of(.MODULE$, msg.appId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, msg.shuffleId), MDC.of(org.apache.spark.internal.LogKeys.SHUFFLE_MERGE_ID..MODULE$, msg.shuffleMergeId)});
            this.mergeManager.removeShuffleMerge(msg);
         } else {
            if (!(msgObj instanceof DiagnoseCorruption)) {
               throw new UnsupportedOperationException("Unexpected message: " + String.valueOf(msgObj));
            }

            DiagnoseCorruption msg = (DiagnoseCorruption)msgObj;
            this.checkAuth(client, msg.appId);
            Cause cause = this.blockManager.diagnoseShuffleBlockCorruption(msg.appId, msg.execId, msg.shuffleId, msg.mapId, msg.reduceId, msg.checksum, msg.algorithm);
            callback.onSuccess((new CorruptionCause(cause)).toByteBuffer());
         }
      } else {
         Timer.Context responseDelayContext = this.metrics.openBlockRequestLatencyMillis.time();

         try {
            int numBlockIds;
            long streamId;
            if (msgObj instanceof AbstractFetchShuffleBlocks) {
               AbstractFetchShuffleBlocks msg = (AbstractFetchShuffleBlocks)msgObj;
               this.checkAuth(client, msg.appId);
               numBlockIds = msg.getNumBlocks();
               Iterator<ManagedBuffer> iterator;
               if (msgObj instanceof FetchShuffleBlocks) {
                  FetchShuffleBlocks blocks = (FetchShuffleBlocks)msgObj;
                  iterator = new ShuffleManagedBufferIterator(blocks);
               } else {
                  iterator = new ShuffleChunkManagedBufferIterator((FetchShuffleBlockChunks)msgObj);
               }

               streamId = this.streamManager.registerStream(client.getClientId(), iterator, client.getChannel(), true);
            } else {
               OpenBlocks msg = (OpenBlocks)msgObj;
               numBlockIds = msg.blockIds.length;
               this.checkAuth(client, msg.appId);
               streamId = this.streamManager.registerStream(client.getClientId(), new ManagedBufferIterator(msg), client.getChannel(), true);
            }

            if (logger.isTraceEnabled()) {
               logger.trace("Registered streamId {} with {} buffers for client {} from host {}", new Object[]{streamId, numBlockIds, client.getClientId(), NettyUtils.getRemoteAddress(client.getChannel())});
            }

            callback.onSuccess((new StreamHandle(streamId, numBlockIds)).toByteBuffer());
         } finally {
            responseDelayContext.stop();
         }
      }

   }

   public void receiveMergeBlockMetaReq(TransportClient client, MergedBlockMetaRequest metaRequest, MergedBlockMetaResponseCallback callback) {
      Timer.Context responseDelayContext = this.metrics.fetchMergedBlocksMetaLatencyMillis.time();

      try {
         this.checkAuth(client, metaRequest.appId);
         MergedBlockMeta mergedMeta = this.mergeManager.getMergedBlockMeta(metaRequest.appId, metaRequest.shuffleId, metaRequest.shuffleMergeId, metaRequest.reduceId);
         logger.debug("Merged block chunks appId {} shuffleId {} reduceId {} num-chunks : {} ", new Object[]{metaRequest.appId, metaRequest.shuffleId, metaRequest.reduceId, mergedMeta.getNumChunks()});
         callback.onSuccess(mergedMeta.getNumChunks(), mergedMeta.getChunksBitmapBuffer());
      } finally {
         responseDelayContext.stop();
      }

   }

   public RpcHandler.MergedBlockMetaReqHandler getMergedBlockMetaReqHandler() {
      return this;
   }

   public void exceptionCaught(Throwable cause, TransportClient client) {
      this.metrics.caughtExceptions.inc();
   }

   public MetricSet getAllMetrics() {
      return this.metrics;
   }

   public StreamManager getStreamManager() {
      return this.streamManager;
   }

   public void applicationRemoved(String appId, boolean cleanupLocalDirs) {
      this.blockManager.applicationRemoved(appId, cleanupLocalDirs);
      this.mergeManager.applicationRemoved(appId, cleanupLocalDirs);
   }

   public void executorRemoved(String executorId, String appId) {
      this.blockManager.executorRemoved(executorId, appId);
   }

   public void close() {
      this.blockManager.close();
      this.mergeManager.close();
   }

   private void checkAuth(TransportClient client, String appId) {
      if (client.getClientId() != null && !client.getClientId().equals(appId)) {
         throw new SecurityException(String.format("Client for %s not authorized for application %s.", client.getClientId(), appId));
      }
   }

   public void channelActive(TransportClient client) {
      this.metrics.activeConnections.inc();
      super.channelActive(client);
   }

   public void channelInactive(TransportClient client) {
      this.metrics.activeConnections.dec();
      super.channelInactive(client);
   }

   @VisibleForTesting
   public class ShuffleMetrics implements MetricSet {
      private final Map allMetrics;
      private final Timer openBlockRequestLatencyMillis;
      private final Timer registerExecutorRequestLatencyMillis;
      private final Timer fetchMergedBlocksMetaLatencyMillis;
      private final Timer finalizeShuffleMergeLatencyMillis;
      private final Meter blockTransferRate;
      private final Meter blockTransferMessageRate;
      private final Meter blockTransferRateBytes;
      private Counter activeConnections;
      private Counter caughtExceptions;

      public ShuffleMetrics() {
         this.openBlockRequestLatencyMillis = new TimerWithCustomTimeUnit(TimeUnit.MILLISECONDS);
         this.registerExecutorRequestLatencyMillis = new TimerWithCustomTimeUnit(TimeUnit.MILLISECONDS);
         this.fetchMergedBlocksMetaLatencyMillis = new TimerWithCustomTimeUnit(TimeUnit.MILLISECONDS);
         this.finalizeShuffleMergeLatencyMillis = new TimerWithCustomTimeUnit(TimeUnit.MILLISECONDS);
         this.blockTransferRate = new Meter();
         this.blockTransferMessageRate = new Meter();
         this.blockTransferRateBytes = new Meter();
         this.activeConnections = new Counter();
         this.caughtExceptions = new Counter();
         this.allMetrics = new HashMap();
         this.allMetrics.put("openBlockRequestLatencyMillis", this.openBlockRequestLatencyMillis);
         this.allMetrics.put("registerExecutorRequestLatencyMillis", this.registerExecutorRequestLatencyMillis);
         this.allMetrics.put("fetchMergedBlocksMetaLatencyMillis", this.fetchMergedBlocksMetaLatencyMillis);
         this.allMetrics.put("finalizeShuffleMergeLatencyMillis", this.finalizeShuffleMergeLatencyMillis);
         this.allMetrics.put("blockTransferRate", this.blockTransferRate);
         this.allMetrics.put("blockTransferMessageRate", this.blockTransferMessageRate);
         this.allMetrics.put("blockTransferRateBytes", this.blockTransferRateBytes);
         this.allMetrics.put("blockTransferAvgSize_1min", new RatioGauge() {
            protected RatioGauge.Ratio getRatio() {
               return Ratio.of(ShuffleMetrics.this.blockTransferRateBytes.getOneMinuteRate(), ShuffleMetrics.this.blockTransferMessageRate.getOneMinuteRate());
            }
         });
         this.allMetrics.put("registeredExecutorsSize", (Gauge)() -> ExternalBlockHandler.this.blockManager.getRegisteredExecutorsSize());
         this.allMetrics.put("numActiveConnections", this.activeConnections);
         this.allMetrics.put("numCaughtExceptions", this.caughtExceptions);
      }

      public Map getMetrics() {
         return this.allMetrics;
      }
   }

   private class ManagedBufferIterator implements Iterator {
      private int index = 0;
      private final Function blockDataForIndexFn;
      private final int size;

      ManagedBufferIterator(OpenBlocks msg) {
         String appId = msg.appId;
         String execId = msg.execId;
         String[] blockIds = msg.blockIds;
         String[] blockId0Parts = blockIds[0].split("_");
         if (blockId0Parts.length == 4 && blockId0Parts[0].equals("shuffle")) {
            int shuffleId = Integer.parseInt(blockId0Parts[1]);
            int[] mapIdAndReduceIds = this.shuffleMapIdAndReduceIds(blockIds, shuffleId);
            this.size = mapIdAndReduceIds.length;
            this.blockDataForIndexFn = (index) -> ExternalBlockHandler.this.blockManager.getBlockData(appId, execId, shuffleId, (long)mapIdAndReduceIds[index], mapIdAndReduceIds[index + 1]);
         } else if (blockId0Parts.length == 5 && blockId0Parts[0].equals("shuffleChunk")) {
            int shuffleId = Integer.parseInt(blockId0Parts[1]);
            int shuffleMergeId = Integer.parseInt(blockId0Parts[2]);
            int[] reduceIdAndChunkIds = this.shuffleReduceIdAndChunkIds(blockIds, shuffleId, shuffleMergeId);
            this.size = reduceIdAndChunkIds.length;
            this.blockDataForIndexFn = (index) -> ExternalBlockHandler.this.mergeManager.getMergedBlockData(msg.appId, shuffleId, shuffleMergeId, reduceIdAndChunkIds[index], reduceIdAndChunkIds[index + 1]);
         } else {
            if (blockId0Parts.length != 3 || !blockId0Parts[0].equals("rdd")) {
               throw new IllegalArgumentException("Unexpected block id format: " + blockIds[0]);
            }

            int[] rddAndSplitIds = this.rddAndSplitIds(blockIds);
            this.size = rddAndSplitIds.length;
            this.blockDataForIndexFn = (index) -> ExternalBlockHandler.this.blockManager.getRddBlockData(appId, execId, rddAndSplitIds[index], rddAndSplitIds[index + 1]);
         }

      }

      private int[] rddAndSplitIds(String[] blockIds) {
         int[] rddAndSplitIds = new int[2 * blockIds.length];

         for(int i = 0; i < blockIds.length; ++i) {
            String[] blockIdParts = blockIds[i].split("_");
            if (blockIdParts.length != 3 || !blockIdParts[0].equals("rdd")) {
               throw new IllegalArgumentException("Unexpected RDD block id format: " + blockIds[i]);
            }

            rddAndSplitIds[2 * i] = Integer.parseInt(blockIdParts[1]);
            rddAndSplitIds[2 * i + 1] = Integer.parseInt(blockIdParts[2]);
         }

         return rddAndSplitIds;
      }

      private int[] shuffleMapIdAndReduceIds(String[] blockIds, int shuffleId) {
         int[] mapIdAndReduceIds = new int[2 * blockIds.length];

         for(int i = 0; i < blockIds.length; ++i) {
            String[] blockIdParts = blockIds[i].split("_");
            if (blockIdParts.length != 4 || !blockIdParts[0].equals("shuffle")) {
               throw new IllegalArgumentException("Unexpected shuffle block id format: " + blockIds[i]);
            }

            if (Integer.parseInt(blockIdParts[1]) != shuffleId) {
               throw new IllegalArgumentException("Expected shuffleId=" + shuffleId + ", got:" + blockIds[i]);
            }

            mapIdAndReduceIds[2 * i] = Integer.parseInt(blockIdParts[2]);
            mapIdAndReduceIds[2 * i + 1] = Integer.parseInt(blockIdParts[3]);
         }

         return mapIdAndReduceIds;
      }

      private int[] shuffleReduceIdAndChunkIds(String[] blockIds, int shuffleId, int shuffleMergeId) {
         int[] reduceIdAndChunkIds = new int[2 * blockIds.length];
         int i = 0;

         while(i < blockIds.length) {
            String[] blockIdParts = blockIds[i].split("_");
            if (blockIdParts.length == 5 && blockIdParts[0].equals("shuffleChunk")) {
               if (Integer.parseInt(blockIdParts[1]) == shuffleId && Integer.parseInt(blockIdParts[2]) == shuffleMergeId) {
                  reduceIdAndChunkIds[2 * i] = Integer.parseInt(blockIdParts[3]);
                  reduceIdAndChunkIds[2 * i + 1] = Integer.parseInt(blockIdParts[4]);
                  ++i;
                  continue;
               }

               throw new IllegalArgumentException(String.format("Expected shuffleId = %s and shuffleMergeId = %s but got %s", shuffleId, shuffleMergeId, blockIds[i]));
            }

            throw new IllegalArgumentException("Unexpected shuffle chunk id format: " + blockIds[i]);
         }

         return reduceIdAndChunkIds;
      }

      public boolean hasNext() {
         return this.index < this.size;
      }

      public ManagedBuffer next() {
         ManagedBuffer block = (ManagedBuffer)this.blockDataForIndexFn.apply(this.index);
         this.index += 2;
         ExternalBlockHandler.this.metrics.blockTransferRate.mark();
         ExternalBlockHandler.this.metrics.blockTransferMessageRate.mark();
         ExternalBlockHandler.this.metrics.blockTransferRateBytes.mark(block != null ? block.size() : 0L);
         return block;
      }
   }

   private class ShuffleManagedBufferIterator implements Iterator {
      private int mapIdx = 0;
      private int reduceIdx = 0;
      private final String appId;
      private final String execId;
      private final int shuffleId;
      private final long[] mapIds;
      private final int[][] reduceIds;
      private final boolean batchFetchEnabled;

      ShuffleManagedBufferIterator(FetchShuffleBlocks msg) {
         this.appId = msg.appId;
         this.execId = msg.execId;
         this.shuffleId = msg.shuffleId;
         this.mapIds = msg.mapIds;
         this.reduceIds = msg.reduceIds;
         this.batchFetchEnabled = msg.batchFetchEnabled;

         assert this.mapIds.length != 0 && this.mapIds.length == this.reduceIds.length;
      }

      public boolean hasNext() {
         return this.mapIdx < this.mapIds.length && this.reduceIdx < this.reduceIds[this.mapIdx].length;
      }

      public ManagedBuffer next() {
         ManagedBuffer block;
         if (!this.batchFetchEnabled) {
            block = ExternalBlockHandler.this.blockManager.getBlockData(this.appId, this.execId, this.shuffleId, this.mapIds[this.mapIdx], this.reduceIds[this.mapIdx][this.reduceIdx]);
            if (this.reduceIdx < this.reduceIds[this.mapIdx].length - 1) {
               ++this.reduceIdx;
            } else {
               this.reduceIdx = 0;
               ++this.mapIdx;
            }

            ExternalBlockHandler.this.metrics.blockTransferRate.mark();
         } else {
            assert this.reduceIds[this.mapIdx].length == 2;

            int startReduceId = this.reduceIds[this.mapIdx][0];
            int endReduceId = this.reduceIds[this.mapIdx][1];
            block = ExternalBlockHandler.this.blockManager.getContinuousBlocksData(this.appId, this.execId, this.shuffleId, this.mapIds[this.mapIdx], startReduceId, endReduceId);
            ++this.mapIdx;
            ExternalBlockHandler.this.metrics.blockTransferRate.mark((long)(endReduceId - startReduceId));
         }

         ExternalBlockHandler.this.metrics.blockTransferMessageRate.mark();
         ExternalBlockHandler.this.metrics.blockTransferRateBytes.mark(block != null ? block.size() : 0L);
         return block;
      }
   }

   private class ShuffleChunkManagedBufferIterator implements Iterator {
      private int reduceIdx = 0;
      private int chunkIdx = 0;
      private final String appId;
      private final int shuffleId;
      private final int shuffleMergeId;
      private final int[] reduceIds;
      private final int[][] chunkIds;

      ShuffleChunkManagedBufferIterator(FetchShuffleBlockChunks msg) {
         this.appId = msg.appId;
         this.shuffleId = msg.shuffleId;
         this.shuffleMergeId = msg.shuffleMergeId;
         this.reduceIds = msg.reduceIds;
         this.chunkIds = msg.chunkIds;

         assert this.reduceIds.length != 0 && this.reduceIds.length == this.chunkIds.length;
      }

      public boolean hasNext() {
         return this.reduceIdx < this.reduceIds.length && this.chunkIdx < this.chunkIds[this.reduceIdx].length;
      }

      public ManagedBuffer next() {
         ManagedBuffer block = (ManagedBuffer)Preconditions.checkNotNull(ExternalBlockHandler.this.mergeManager.getMergedBlockData(this.appId, this.shuffleId, this.shuffleMergeId, this.reduceIds[this.reduceIdx], this.chunkIds[this.reduceIdx][this.chunkIdx]));
         if (this.chunkIdx < this.chunkIds[this.reduceIdx].length - 1) {
            ++this.chunkIdx;
         } else {
            this.chunkIdx = 0;
            ++this.reduceIdx;
         }

         ExternalBlockHandler.this.metrics.blockTransferRateBytes.mark(block.size());
         return block;
      }
   }
}
