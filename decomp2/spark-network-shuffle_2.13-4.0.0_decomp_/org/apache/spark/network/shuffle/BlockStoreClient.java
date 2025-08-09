package org.apache.spark.network.shuffle;

import com.codahale.metrics.MetricSet;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.EXECUTOR_IDS.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientFactory;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.CorruptionCause;
import org.apache.spark.network.shuffle.protocol.DiagnoseCorruption;
import org.apache.spark.network.shuffle.protocol.GetLocalDirsForExecutors;
import org.apache.spark.network.shuffle.protocol.LocalDirsForExecutors;
import org.apache.spark.network.util.TransportConf;

public abstract class BlockStoreClient implements Closeable {
   protected final SparkLogger logger = SparkLoggerFactory.getLogger(this.getClass());
   protected volatile TransportClientFactory clientFactory;
   protected String appId;
   private String appAttemptId;
   protected TransportConf transportConf;

   public Cause diagnoseCorruption(String host, int port, String execId, int shuffleId, long mapId, int reduceId, long checksum, String algorithm) {
      try {
         TransportClient client = this.clientFactory.createClient(host, port);
         ByteBuffer response = client.sendRpcSync((new DiagnoseCorruption(this.appId, execId, shuffleId, mapId, reduceId, checksum, algorithm)).toByteBuffer(), (long)this.transportConf.connectionTimeoutMs());
         CorruptionCause cause = (CorruptionCause)BlockTransferMessage.Decoder.fromByteBuffer(response);
         return cause.cause;
      } catch (Exception var14) {
         this.logger.warn("Failed to get the corruption cause.");
         return Cause.UNKNOWN_ISSUE;
      }
   }

   public abstract void fetchBlocks(String var1, int var2, String var3, String[] var4, BlockFetchingListener var5, DownloadFileManager var6);

   public MetricSet shuffleMetrics() {
      return () -> Collections.emptyMap();
   }

   protected void checkInit() {
      assert this.appId != null : "Called before init()";

   }

   public void setAppAttemptId(String appAttemptId) {
      this.appAttemptId = appAttemptId;
   }

   public String getAppAttemptId() {
      return this.appAttemptId;
   }

   public void getHostLocalDirs(String host, int port, String[] execIds, final CompletableFuture hostLocalDirsCompletable) {
      this.checkInit();
      final GetLocalDirsForExecutors getLocalDirsMessage = new GetLocalDirsForExecutors(this.appId, execIds);

      try {
         TransportClient client = this.clientFactory.createClient(host, port);
         client.sendRpc(getLocalDirsMessage.toByteBuffer(), new RpcResponseCallback() {
            public void onSuccess(ByteBuffer response) {
               try {
                  BlockTransferMessage msgObj = BlockTransferMessage.Decoder.fromByteBuffer(response);
                  hostLocalDirsCompletable.complete(((LocalDirsForExecutors)msgObj).getLocalDirsByExec());
               } catch (Throwable t) {
                  BlockStoreClient.this.logger.warn("Error while trying to get the host local dirs for {}", t.getCause(), new MDC[]{MDC.of(.MODULE$, Arrays.toString(getLocalDirsMessage.execIds))});
                  hostLocalDirsCompletable.completeExceptionally(t);
               }

            }

            public void onFailure(Throwable t) {
               BlockStoreClient.this.logger.warn("Error while trying to get the host local dirs for {}", t.getCause(), new MDC[]{MDC.of(.MODULE$, Arrays.toString(getLocalDirsMessage.execIds))});
               hostLocalDirsCompletable.completeExceptionally(t);
            }
         });
      } catch (InterruptedException | IOException e) {
         hostLocalDirsCompletable.completeExceptionally(e);
      }

   }

   public void pushBlocks(String host, int port, String[] blockIds, ManagedBuffer[] buffers, BlockPushingListener listener) {
      throw new UnsupportedOperationException();
   }

   public void finalizeShuffleMerge(String host, int port, int shuffleId, int shuffleMergeId, MergeFinalizerListener listener) {
      throw new UnsupportedOperationException();
   }

   public void getMergedBlockMeta(String host, int port, int shuffleId, int shuffleMergeId, int reduceId, MergedBlocksMetaListener listener) {
      throw new UnsupportedOperationException();
   }

   public boolean removeShuffleMerge(String host, int port, int shuffleId, int shuffleMergeId) {
      throw new UnsupportedOperationException();
   }
}
