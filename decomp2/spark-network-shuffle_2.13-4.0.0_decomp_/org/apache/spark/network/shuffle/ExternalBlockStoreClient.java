package org.apache.spark.network.shuffle;

import com.codahale.metrics.MetricSet;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID.;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.MergedBlockMetaResponseCallback;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.client.TransportClientBootstrap;
import org.apache.spark.network.crypto.AuthClientBootstrap;
import org.apache.spark.network.sasl.SecretKeyHolder;
import org.apache.spark.network.server.NoOpRpcHandler;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.BlocksRemoved;
import org.apache.spark.network.shuffle.protocol.ExecutorShuffleInfo;
import org.apache.spark.network.shuffle.protocol.FinalizeShuffleMerge;
import org.apache.spark.network.shuffle.protocol.MergeStatuses;
import org.apache.spark.network.shuffle.protocol.RegisterExecutor;
import org.apache.spark.network.shuffle.protocol.RemoveBlocks;
import org.apache.spark.network.shuffle.protocol.RemoveShuffleMerge;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.collect.Lists;

public class ExternalBlockStoreClient extends BlockStoreClient {
   private static final ErrorHandler PUSH_ERROR_HANDLER = new ErrorHandler.BlockPushErrorHandler();
   private final boolean authEnabled;
   private final SecretKeyHolder secretKeyHolder;
   private final long registrationTimeoutMs;
   private int comparableAppAttemptId = -1;

   public ExternalBlockStoreClient(TransportConf conf, SecretKeyHolder secretKeyHolder, boolean authEnabled, long registrationTimeoutMs) {
      this.transportConf = conf;
      this.secretKeyHolder = secretKeyHolder;
      this.authEnabled = authEnabled;
      this.registrationTimeoutMs = registrationTimeoutMs;
   }

   public void init(String appId) {
      this.appId = appId;
      TransportContext context = new TransportContext(this.transportConf, new NoOpRpcHandler(), true, true);
      List<TransportClientBootstrap> bootstraps = Lists.newArrayList();
      if (this.authEnabled) {
         bootstraps.add(new AuthClientBootstrap(this.transportConf, appId, this.secretKeyHolder));
      }

      this.clientFactory = context.createClientFactory(bootstraps);
   }

   public void setAppAttemptId(String appAttemptId) {
      super.setAppAttemptId(appAttemptId);
      this.setComparableAppAttemptId(appAttemptId);
   }

   private void setComparableAppAttemptId(String appAttemptId) {
      try {
         this.comparableAppAttemptId = Integer.parseInt(appAttemptId);
      } catch (NumberFormatException e) {
         this.logger.warn("Push based shuffle requires comparable application attemptId, but the appAttemptId {} cannot be parsed to Integer", e, new MDC[]{MDC.of(.MODULE$, appAttemptId)});
      }

   }

   public void fetchBlocks(String host, int port, String execId, String[] blockIds, BlockFetchingListener listener, DownloadFileManager downloadFileManager) {
      this.checkInit();
      this.logger.debug("External shuffle fetch from {}:{} (executor id {})", new Object[]{host, port, execId});

      try {
         int maxRetries = this.transportConf.maxIORetries();
         RetryingBlockTransferor.BlockTransferStarter blockFetchStarter = (inputBlockId, inputListener) -> {
            if (this.clientFactory != null) {
               assert inputListener instanceof BlockFetchingListener : "Expecting a BlockFetchingListener, but got " + String.valueOf(inputListener.getClass());

               TransportClient client = this.clientFactory.createClient(host, port, maxRetries > 0);
               (new OneForOneBlockFetcher(client, this.appId, execId, inputBlockId, (BlockFetchingListener)inputListener, this.transportConf, downloadFileManager)).start();
            } else {
               this.logger.info("This clientFactory was closed. Skipping further block fetch retries.");
            }

         };
         if (maxRetries > 0) {
            (new RetryingBlockTransferor(this.transportConf, blockFetchStarter, blockIds, listener)).start();
         } else {
            blockFetchStarter.createAndStart(blockIds, listener);
         }
      } catch (Exception var12) {
         Exception e = var12;
         this.logger.error("Exception while beginning fetchBlocks", var12);

         for(String blockId : blockIds) {
            listener.onBlockFetchFailure(blockId, e);
         }
      }

   }

   public void pushBlocks(String host, int port, String[] blockIds, ManagedBuffer[] buffers, BlockPushingListener listener) {
      this.checkInit();

      assert blockIds.length == buffers.length : "Number of block ids and buffers do not match.";

      Map<String, ManagedBuffer> buffersWithId = new HashMap();

      for(int i = 0; i < blockIds.length; ++i) {
         buffersWithId.put(blockIds[i], buffers[i]);
      }

      this.logger.debug("Push {} shuffle blocks to {}:{}", new Object[]{blockIds.length, host, port});

      try {
         RetryingBlockTransferor.BlockTransferStarter blockPushStarter = (inputBlockId, inputListener) -> {
            if (this.clientFactory != null) {
               assert inputListener instanceof BlockPushingListener : "Expecting a BlockPushingListener, but got " + String.valueOf(inputListener.getClass());

               TransportClient client = this.clientFactory.createClient(host, port);
               (new OneForOneBlockPusher(client, this.appId, this.comparableAppAttemptId, inputBlockId, (BlockPushingListener)inputListener, buffersWithId)).start();
            } else {
               this.logger.info("This clientFactory was closed. Skipping further block push retries.");
            }

         };
         int maxRetries = this.transportConf.maxIORetries();
         if (maxRetries > 0) {
            (new RetryingBlockTransferor(this.transportConf, blockPushStarter, blockIds, listener, PUSH_ERROR_HANDLER)).start();
         } else {
            blockPushStarter.createAndStart(blockIds, listener);
         }
      } catch (Exception var12) {
         Exception e = var12;
         this.logger.error("Exception while beginning pushBlocks", var12);

         for(String blockId : blockIds) {
            listener.onBlockPushFailure(blockId, e);
         }
      }

   }

   public void finalizeShuffleMerge(String host, int port, int shuffleId, int shuffleMergeId, final MergeFinalizerListener listener) {
      this.checkInit();

      try {
         TransportClient client = this.clientFactory.createClient(host, port);
         ByteBuffer finalizeShuffleMerge = (new FinalizeShuffleMerge(this.appId, this.comparableAppAttemptId, shuffleId, shuffleMergeId)).toByteBuffer();
         client.sendRpc(finalizeShuffleMerge, new RpcResponseCallback() {
            public void onSuccess(ByteBuffer response) {
               listener.onShuffleMergeSuccess((MergeStatuses)BlockTransferMessage.Decoder.fromByteBuffer(response));
            }

            public void onFailure(Throwable e) {
               listener.onShuffleMergeFailure(e);
            }
         });
      } catch (Exception e) {
         this.logger.error("Exception while sending finalizeShuffleMerge request to {}:{}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.HOST..MODULE$, host), MDC.of(org.apache.spark.internal.LogKeys.PORT..MODULE$, port)});
         listener.onShuffleMergeFailure(e);
      }

   }

   public void getMergedBlockMeta(String host, int port, final int shuffleId, final int shuffleMergeId, final int reduceId, final MergedBlocksMetaListener listener) {
      this.checkInit();
      this.logger.debug("Get merged blocks meta from {}:{} for shuffleId {} shuffleMergeId {} reduceId {}", new Object[]{host, port, shuffleId, shuffleMergeId, reduceId});

      try {
         TransportClient client = this.clientFactory.createClient(host, port);
         client.sendMergedBlockMetaReq(this.appId, shuffleId, shuffleMergeId, reduceId, new MergedBlockMetaResponseCallback() {
            public void onSuccess(int numChunks, ManagedBuffer buffer) {
               ExternalBlockStoreClient.this.logger.trace("Successfully got merged block meta for shuffleId {} shuffleMergeId {} reduceId {}", new Object[]{shuffleId, shuffleMergeId, reduceId});
               listener.onSuccess(shuffleId, shuffleMergeId, reduceId, new MergedBlockMeta(numChunks, buffer));
            }

            public void onFailure(Throwable e) {
               listener.onFailure(shuffleId, shuffleMergeId, reduceId, e);
            }
         });
      } catch (Exception e) {
         listener.onFailure(shuffleId, shuffleMergeId, reduceId, e);
      }

   }

   public boolean removeShuffleMerge(String host, int port, int shuffleId, int shuffleMergeId) {
      this.checkInit();

      try {
         TransportClient client = this.clientFactory.createClient(host, port);
         client.send((new RemoveShuffleMerge(this.appId, this.comparableAppAttemptId, shuffleId, shuffleMergeId)).toByteBuffer());
         return true;
      } catch (Exception e) {
         this.logger.debug("Exception while sending RemoveShuffleMerge request to {}:{}", new Object[]{host, port, e});
         return false;
      }
   }

   public MetricSet shuffleMetrics() {
      this.checkInit();
      return this.clientFactory.getAllMetrics();
   }

   public void registerWithShuffleServer(String host, int port, String execId, ExecutorShuffleInfo executorInfo) throws IOException, InterruptedException {
      this.checkInit();
      TransportClient client = this.clientFactory.createClient(host, port);

      try {
         ByteBuffer registerMessage = (new RegisterExecutor(this.appId, execId, executorInfo)).toByteBuffer();
         client.sendRpcSync(registerMessage, this.registrationTimeoutMs);
      } catch (Throwable var9) {
         if (client != null) {
            try {
               client.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }
         }

         throw var9;
      }

      if (client != null) {
         client.close();
      }

   }

   public Future removeBlocks(String host, int port, final String execId, final String[] blockIds) throws IOException, InterruptedException {
      this.checkInit();
      final CompletableFuture<Integer> numRemovedBlocksFuture = new CompletableFuture();
      ByteBuffer removeBlocksMessage = (new RemoveBlocks(this.appId, execId, blockIds)).toByteBuffer();
      TransportClient client = this.clientFactory.createClient(host, port);
      client.sendRpc(removeBlocksMessage, new RpcResponseCallback() {
         public void onSuccess(ByteBuffer response) {
            try {
               BlockTransferMessage msgObj = BlockTransferMessage.Decoder.fromByteBuffer(response);
               numRemovedBlocksFuture.complete(((BlocksRemoved)msgObj).numRemovedBlocks);
            } catch (Throwable t) {
               ExternalBlockStoreClient.this.logger.warn("Error trying to remove blocks {} via external shuffle service from executor: {}", t, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.BLOCK_IDS..MODULE$, Arrays.toString(blockIds)), MDC.of(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)});
               numRemovedBlocksFuture.complete(0);
            }

         }

         public void onFailure(Throwable e) {
            ExternalBlockStoreClient.this.logger.warn("Error trying to remove blocks {} via external shuffle service from executor: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.BLOCK_IDS..MODULE$, Arrays.toString(blockIds)), MDC.of(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)});
            numRemovedBlocksFuture.complete(0);
         }
      });
      return numRemovedBlocksFuture;
   }

   public void close() {
      this.checkInit();
      if (this.clientFactory != null) {
         this.clientFactory.close();
         this.clientFactory = null;
      }

   }
}
