package org.apache.spark.network.shuffle;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.TRANSFER_TYPE.;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.sasl.SaslTimeoutException;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.Sets;
import org.sparkproject.guava.util.concurrent.Uninterruptibles;

public class RetryingBlockTransferor {
   private static final ExecutorService executorService = Executors.newCachedThreadPool(NettyUtils.createThreadFactory("Block Transfer Retry"));
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(RetryingBlockTransferor.class);
   private final BlockTransferStarter transferStarter;
   private final BlockTransferListener listener;
   private final int maxRetries;
   private final int retryWaitTime;
   private int retryCount;
   private int saslRetryCount;
   private final LinkedHashSet outstandingBlocksIds;
   private RetryingBlockTransferListener currentListener;
   private final boolean enableSaslRetries;
   private final ErrorHandler errorHandler;

   public RetryingBlockTransferor(TransportConf conf, BlockTransferStarter transferStarter, String[] blockIds, BlockTransferListener listener, ErrorHandler errorHandler) {
      this.retryCount = 0;
      this.saslRetryCount = 0;
      this.transferStarter = transferStarter;
      this.listener = listener;
      this.maxRetries = conf.maxIORetries();
      this.retryWaitTime = conf.ioRetryWaitTimeMs();
      this.outstandingBlocksIds = Sets.newLinkedHashSet();
      Collections.addAll(this.outstandingBlocksIds, blockIds);
      this.currentListener = new RetryingBlockTransferListener();
      this.errorHandler = errorHandler;
      this.enableSaslRetries = conf.enableSaslRetries();
      this.saslRetryCount = 0;
   }

   public RetryingBlockTransferor(TransportConf conf, BlockTransferStarter transferStarter, String[] blockIds, BlockFetchingListener listener) {
      this(conf, transferStarter, blockIds, listener, ErrorHandler.NOOP_ERROR_HANDLER);
   }

   @VisibleForTesting
   synchronized void setCurrentListener(RetryingBlockTransferListener listener) {
      this.currentListener = listener;
   }

   public void start() {
      this.transferAllOutstanding();
   }

   private void transferAllOutstanding() {
      String[] blockIdsToTransfer;
      int numRetries;
      RetryingBlockTransferListener myListener;
      synchronized(this) {
         blockIdsToTransfer = (String[])this.outstandingBlocksIds.toArray(new String[this.outstandingBlocksIds.size()]);
         numRetries = this.retryCount;
         myListener = this.currentListener;
      }

      try {
         this.transferStarter.createAndStart(blockIdsToTransfer, myListener);
      } catch (Exception var10) {
         Exception e = var10;
         if (numRetries > 0) {
            logger.error("Exception while beginning {} of {} outstanding blocks (after {} retries)", var10, new MDC[]{MDC.of(.MODULE$, this.listener.getTransferType()), MDC.of(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, blockIdsToTransfer.length), MDC.of(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, numRetries)});
         } else {
            logger.error("Exception while beginning {} of {} outstanding blocks", var10, new MDC[]{MDC.of(.MODULE$, this.listener.getTransferType()), MDC.of(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, blockIdsToTransfer.length)});
         }

         if (this.shouldRetry(var10) && this.initiateRetry(var10)) {
            return;
         }

         for(String bid : blockIdsToTransfer) {
            this.listener.onBlockTransferFailure(bid, e);
         }
      }

   }

   @VisibleForTesting
   synchronized boolean initiateRetry(Throwable e) {
      if (this.enableSaslRetries && e instanceof SaslTimeoutException) {
         ++this.saslRetryCount;
      }

      ++this.retryCount;
      this.currentListener = new RetryingBlockTransferListener();
      logger.info("Retrying {} ({}/{}) for {} outstanding blocks after {} ms", new MDC[]{MDC.of(.MODULE$, this.listener.getTransferType()), MDC.of(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, this.retryCount), MDC.of(org.apache.spark.internal.LogKeys.MAX_ATTEMPTS..MODULE$, this.maxRetries), MDC.of(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, this.outstandingBlocksIds.size()), MDC.of(org.apache.spark.internal.LogKeys.RETRY_WAIT_TIME..MODULE$, this.retryWaitTime)});

      try {
         executorService.execute(() -> {
            Uninterruptibles.sleepUninterruptibly((long)this.retryWaitTime, TimeUnit.MILLISECONDS);
            this.transferAllOutstanding();
         });
         return true;
      } catch (Throwable t) {
         logger.error("Exception while trying to initiate retry", t);
         return false;
      }
   }

   private synchronized boolean shouldRetry(Throwable e) {
      boolean isIOException = e instanceof IOException || e.getCause() instanceof IOException;
      boolean isSaslTimeout = this.enableSaslRetries && e instanceof SaslTimeoutException;
      if (!isSaslTimeout && this.saslRetryCount > 0) {
         Preconditions.checkState(this.retryCount >= this.saslRetryCount, "retryCount must be greater than or equal to saslRetryCount");
         this.retryCount -= this.saslRetryCount;
         this.saslRetryCount = 0;
      }

      boolean hasRemainingRetries = this.retryCount < this.maxRetries;
      boolean shouldRetry = (isSaslTimeout || isIOException) && hasRemainingRetries && this.errorHandler.shouldRetryError(e);
      return shouldRetry;
   }

   @VisibleForTesting
   public int getRetryCount() {
      return this.retryCount;
   }

   @VisibleForTesting
   class RetryingBlockTransferListener implements BlockFetchingListener, BlockPushingListener {
      private void handleBlockTransferSuccess(String blockId, ManagedBuffer data) {
         boolean shouldForwardSuccess = false;
         synchronized(RetryingBlockTransferor.this) {
            if (this == RetryingBlockTransferor.this.currentListener && RetryingBlockTransferor.this.outstandingBlocksIds.contains(blockId)) {
               RetryingBlockTransferor.this.outstandingBlocksIds.remove(blockId);
               shouldForwardSuccess = true;
               if (RetryingBlockTransferor.this.saslRetryCount > 0) {
                  Preconditions.checkState(RetryingBlockTransferor.this.retryCount >= RetryingBlockTransferor.this.saslRetryCount, "retryCount must be greater than or equal to saslRetryCount");
                  RetryingBlockTransferor var10000 = RetryingBlockTransferor.this;
                  var10000.retryCount -= RetryingBlockTransferor.this.saslRetryCount;
                  RetryingBlockTransferor.this.saslRetryCount = 0;
               }
            }
         }

         if (shouldForwardSuccess) {
            RetryingBlockTransferor.this.listener.onBlockTransferSuccess(blockId, data);
         }

      }

      private void handleBlockTransferFailure(String blockId, Throwable exception) {
         boolean shouldForwardFailure = false;
         synchronized(RetryingBlockTransferor.this) {
            if (this == RetryingBlockTransferor.this.currentListener && RetryingBlockTransferor.this.outstandingBlocksIds.contains(blockId)) {
               if (RetryingBlockTransferor.this.shouldRetry(exception)) {
                  if (!RetryingBlockTransferor.this.initiateRetry(exception)) {
                     RetryingBlockTransferor.this.outstandingBlocksIds.remove(blockId);
                     shouldForwardFailure = true;
                  }
               } else {
                  if (RetryingBlockTransferor.this.errorHandler.shouldLogError(exception)) {
                     RetryingBlockTransferor.logger.error("Failed to {} block {}, and will not retry ({} retries)", exception, new MDC[]{MDC.of(.MODULE$, RetryingBlockTransferor.this.listener.getTransferType()), MDC.of(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId), MDC.of(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, RetryingBlockTransferor.this.retryCount)});
                  } else {
                     RetryingBlockTransferor.logger.debug(String.format("Failed to %s block %s, and will not retry (%s retries)", RetryingBlockTransferor.this.listener.getTransferType(), blockId, RetryingBlockTransferor.this.retryCount), exception);
                  }

                  RetryingBlockTransferor.this.outstandingBlocksIds.remove(blockId);
                  shouldForwardFailure = true;
               }
            }
         }

         if (shouldForwardFailure) {
            RetryingBlockTransferor.this.listener.onBlockTransferFailure(blockId, exception);
         }

      }

      public void onBlockFetchSuccess(String blockId, ManagedBuffer data) {
         this.handleBlockTransferSuccess(blockId, data);
      }

      public void onBlockFetchFailure(String blockId, Throwable exception) {
         this.handleBlockTransferFailure(blockId, exception);
      }

      public void onBlockPushSuccess(String blockId, ManagedBuffer data) {
         this.handleBlockTransferSuccess(blockId, data);
      }

      public void onBlockPushFailure(String blockId, Throwable exception) {
         this.handleBlockTransferFailure(blockId, exception);
      }

      public void onBlockTransferSuccess(String blockId, ManagedBuffer data) {
         throw new RuntimeException("Invocation on RetryingBlockTransferListener.onBlockTransferSuccess is unexpected.");
      }

      public void onBlockTransferFailure(String blockId, Throwable exception) {
         throw new RuntimeException("Invocation on RetryingBlockTransferListener.onBlockTransferFailure is unexpected.");
      }

      public String getTransferType() {
         throw new RuntimeException("Invocation on RetryingBlockTransferListener.getTransferType is unexpected.");
      }
   }

   public interface BlockTransferStarter {
      void createAndStart(String[] var1, BlockTransferListener var2) throws IOException, InterruptedException;
   }
}
