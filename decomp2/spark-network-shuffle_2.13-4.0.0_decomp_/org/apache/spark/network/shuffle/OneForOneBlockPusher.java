package org.apache.spark.network.shuffle;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.server.BlockPushNonFatalFailure;
import org.apache.spark.network.server.BlockPushNonFatalFailure.ReturnCode;
import org.apache.spark.network.shuffle.protocol.BlockPushReturnCode;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.PushBlockStream;
import org.sparkproject.guava.base.Preconditions;

public class OneForOneBlockPusher {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(OneForOneBlockPusher.class);
   private static final ErrorHandler PUSH_ERROR_HANDLER = new ErrorHandler.BlockPushErrorHandler();
   public static final String SHUFFLE_PUSH_BLOCK_PREFIX = "shufflePush";
   private final TransportClient client;
   private final String appId;
   private final int appAttemptId;
   private final String[] blockIds;
   private final BlockPushingListener listener;
   private final Map buffers;
   // $FF: synthetic field
   static final boolean $assertionsDisabled = !OneForOneBlockPusher.class.desiredAssertionStatus();

   public OneForOneBlockPusher(TransportClient client, String appId, int appAttemptId, String[] blockIds, BlockPushingListener listener, Map buffers) {
      this.client = client;
      this.appId = appId;
      this.appAttemptId = appAttemptId;
      this.blockIds = blockIds;
      this.listener = listener;
      this.buffers = buffers;
   }

   private void checkAndFailRemainingBlocks(int index, Throwable e) {
      if (PUSH_ERROR_HANDLER.shouldRetryError(e)) {
         String[] targetBlockId = (String[])Arrays.copyOfRange(this.blockIds, index, index + 1);
         this.failRemainingBlocks(targetBlockId, e);
      } else {
         String[] targetBlockId = (String[])Arrays.copyOfRange(this.blockIds, index, this.blockIds.length);
         this.failRemainingBlocks(targetBlockId, e);
      }

   }

   private void failRemainingBlocks(String[] failedBlockIds, Throwable e) {
      for(String blockId : failedBlockIds) {
         try {
            this.listener.onBlockPushFailure(blockId, e);
         } catch (Exception e2) {
            logger.error("Error in block push failure callback", e2);
         }
      }

   }

   public void start() {
      logger.debug("Start pushing {} blocks", this.blockIds.length);

      for(int i = 0; i < this.blockIds.length; ++i) {
         if (!$assertionsDisabled && !this.buffers.containsKey(this.blockIds[i])) {
            String var4 = this.blockIds[i];
            throw new AssertionError("Could not find the block buffer for block " + var4);
         }

         String[] blockIdParts = this.blockIds[i].split("_");
         if (blockIdParts.length != 5 || !blockIdParts[0].equals("shufflePush")) {
            String var10002 = this.blockIds[i];
            throw new IllegalArgumentException("Unexpected shuffle push block id format: " + var10002);
         }

         ByteBuffer header = (new PushBlockStream(this.appId, this.appAttemptId, Integer.parseInt(blockIdParts[1]), Integer.parseInt(blockIdParts[2]), Integer.parseInt(blockIdParts[3]), Integer.parseInt(blockIdParts[4]), i)).toByteBuffer();
         this.client.uploadStream(new NioManagedBuffer(header), (ManagedBuffer)this.buffers.get(this.blockIds[i]), new BlockPushCallback(i, this.blockIds[i]));
      }

   }

   private class BlockPushCallback implements RpcResponseCallback {
      private int index;
      private String blockId;

      BlockPushCallback(int index, String blockId) {
         this.index = index;
         this.blockId = blockId;
      }

      public void onSuccess(ByteBuffer response) {
         BlockPushReturnCode pushResponse = (BlockPushReturnCode)BlockTransferMessage.Decoder.fromByteBuffer(response);
         BlockPushNonFatalFailure.ReturnCode returnCode = BlockPushNonFatalFailure.getReturnCode(pushResponse.returnCode);
         if (returnCode != ReturnCode.SUCCESS) {
            String blockId = pushResponse.failureBlockId;
            Preconditions.checkArgument(!blockId.isEmpty());
            OneForOneBlockPusher.this.checkAndFailRemainingBlocks(this.index, new BlockPushNonFatalFailure(returnCode, BlockPushNonFatalFailure.getErrorMsg(blockId, returnCode)));
         } else {
            OneForOneBlockPusher.this.listener.onBlockPushSuccess(this.blockId, new NioManagedBuffer(ByteBuffer.allocate(0)));
         }

      }

      public void onFailure(Throwable e) {
         OneForOneBlockPusher.this.checkAndFailRemainingBlocks(this.index, e);
      }
   }
}
