package org.apache.spark.network.shuffle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.ChunkReceivedCallback;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallback;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.server.OneForOneStreamManager;
import org.apache.spark.network.shuffle.protocol.AbstractFetchShuffleBlocks;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.FetchShuffleBlockChunks;
import org.apache.spark.network.shuffle.protocol.FetchShuffleBlocks;
import org.apache.spark.network.shuffle.protocol.OpenBlocks;
import org.apache.spark.network.shuffle.protocol.StreamHandle;
import org.apache.spark.network.util.TransportConf;
import org.sparkproject.guava.primitives.Ints;
import org.sparkproject.guava.primitives.Longs;

public class OneForOneBlockFetcher {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(OneForOneBlockFetcher.class);
   private static final String SHUFFLE_BLOCK_PREFIX = "shuffle_";
   private static final String SHUFFLE_CHUNK_PREFIX = "shuffleChunk_";
   private static final String SHUFFLE_BLOCK_SPLIT = "shuffle";
   private static final String SHUFFLE_CHUNK_SPLIT = "shuffleChunk";
   private final TransportClient client;
   private final BlockTransferMessage message;
   private final String[] blockIds;
   private final BlockFetchingListener listener;
   private final ChunkReceivedCallback chunkCallback;
   private final TransportConf transportConf;
   private final DownloadFileManager downloadFileManager;
   private StreamHandle streamHandle;

   public OneForOneBlockFetcher(TransportClient client, String appId, String execId, String[] blockIds, BlockFetchingListener listener, TransportConf transportConf) {
      this(client, appId, execId, blockIds, listener, transportConf, (DownloadFileManager)null);
   }

   public OneForOneBlockFetcher(TransportClient client, String appId, String execId, String[] blockIds, BlockFetchingListener listener, TransportConf transportConf, DownloadFileManager downloadFileManager) {
      this.streamHandle = null;
      this.client = client;
      this.listener = listener;
      this.chunkCallback = new ChunkCallback();
      this.transportConf = transportConf;
      this.downloadFileManager = downloadFileManager;
      if (blockIds.length == 0) {
         throw new IllegalArgumentException("Zero-sized blockIds array");
      } else {
         if (!transportConf.useOldFetchProtocol() && this.areShuffleBlocksOrChunks(blockIds)) {
            this.blockIds = new String[blockIds.length];
            this.message = this.createFetchShuffleBlocksOrChunksMsg(appId, execId, blockIds);
         } else {
            this.blockIds = blockIds;
            this.message = new OpenBlocks(appId, execId, blockIds);
         }

      }
   }

   private boolean areShuffleBlocksOrChunks(String[] blockIds) {
      return isAnyBlockNotStartWithShuffleBlockPrefix(blockIds) ? isAllBlocksStartWithShuffleChunkPrefix(blockIds) : true;
   }

   private static boolean isAnyBlockNotStartWithShuffleBlockPrefix(String[] blockIds) {
      for(String blockId : blockIds) {
         if (!blockId.startsWith("shuffle_")) {
            return true;
         }
      }

      return false;
   }

   private static boolean isAllBlocksStartWithShuffleChunkPrefix(String[] blockIds) {
      for(String blockId : blockIds) {
         if (!blockId.startsWith("shuffleChunk_")) {
            return false;
         }
      }

      return true;
   }

   private AbstractFetchShuffleBlocks createFetchShuffleBlocksOrChunksMsg(String appId, String execId, String[] blockIds) {
      return blockIds[0].startsWith("shuffleChunk_") ? this.createFetchShuffleChunksMsg(appId, execId, blockIds) : this.createFetchShuffleBlocksMsg(appId, execId, blockIds);
   }

   private AbstractFetchShuffleBlocks createFetchShuffleBlocksMsg(String appId, String execId, String[] blockIds) {
      String[] firstBlock = this.splitBlockId(blockIds[0]);
      int shuffleId = Integer.parseInt(firstBlock[1]);
      boolean batchFetchEnabled = firstBlock.length == 5;
      Map<Long, BlocksInfo> mapIdToBlocksInfo = new LinkedHashMap();

      for(String blockId : blockIds) {
         String[] blockIdParts = this.splitBlockId(blockId);
         if (Integer.parseInt(blockIdParts[1]) != shuffleId) {
            throw new IllegalArgumentException("Expected shuffleId=" + shuffleId + ", got:" + blockId);
         }

         long mapId = Long.parseLong(blockIdParts[2]);
         BlocksInfo blocksInfoByMapId = (BlocksInfo)mapIdToBlocksInfo.computeIfAbsent(mapId, (id) -> new BlocksInfo());
         blocksInfoByMapId.blockIds.add(blockId);
         blocksInfoByMapId.ids.add(Integer.parseInt(blockIdParts[3]));
         if (batchFetchEnabled) {
            assert blockIdParts.length == 5;

            blocksInfoByMapId.ids.add(Integer.parseInt(blockIdParts[4]));
         }
      }

      int[][] reduceIdsArray = this.getSecondaryIds(mapIdToBlocksInfo);
      long[] mapIds = Longs.toArray(mapIdToBlocksInfo.keySet());
      return new FetchShuffleBlocks(appId, execId, shuffleId, mapIds, reduceIdsArray, batchFetchEnabled);
   }

   private AbstractFetchShuffleBlocks createFetchShuffleChunksMsg(String appId, String execId, String[] blockIds) {
      String[] firstBlock = this.splitBlockId(blockIds[0]);
      int shuffleId = Integer.parseInt(firstBlock[1]);
      int shuffleMergeId = Integer.parseInt(firstBlock[2]);
      Map<Integer, BlocksInfo> reduceIdToBlocksInfo = new LinkedHashMap();

      for(String blockId : blockIds) {
         String[] blockIdParts = this.splitBlockId(blockId);
         if (Integer.parseInt(blockIdParts[1]) != shuffleId || Integer.parseInt(blockIdParts[2]) != shuffleMergeId) {
            throw new IllegalArgumentException(String.format("Expected shuffleId = %s and shuffleMergeId = %s but got %s", shuffleId, shuffleMergeId, blockId));
         }

         int reduceId = Integer.parseInt(blockIdParts[3]);
         BlocksInfo blocksInfoByReduceId = (BlocksInfo)reduceIdToBlocksInfo.computeIfAbsent(reduceId, (id) -> new BlocksInfo());
         blocksInfoByReduceId.blockIds.add(blockId);
         blocksInfoByReduceId.ids.add(Integer.parseInt(blockIdParts[4]));
      }

      int[][] chunkIdsArray = this.getSecondaryIds(reduceIdToBlocksInfo);
      int[] reduceIds = Ints.toArray(reduceIdToBlocksInfo.keySet());
      return new FetchShuffleBlockChunks(appId, execId, shuffleId, shuffleMergeId, reduceIds, chunkIdsArray);
   }

   private int[][] getSecondaryIds(Map primaryIdsToBlockInfo) {
      int[][] secondaryIds = new int[primaryIdsToBlockInfo.size()][];
      int blockIdIndex = 0;
      int secIndex = 0;

      for(BlocksInfo blocksInfo : primaryIdsToBlockInfo.values()) {
         secondaryIds[secIndex++] = Ints.toArray(blocksInfo.ids);

         for(String blockId : blocksInfo.blockIds) {
            this.blockIds[blockIdIndex++] = blockId;
         }
      }

      assert blockIdIndex == this.blockIds.length;

      return secondaryIds;
   }

   private String[] splitBlockId(String blockId) {
      String[] blockIdParts = blockId.split("_");
      if (blockIdParts.length >= 4 && blockIdParts.length <= 5) {
         if (blockIdParts.length == 4 && !blockIdParts[0].equals("shuffle")) {
            throw new IllegalArgumentException("Unexpected shuffle block id format: " + blockId);
         } else if (blockIdParts.length == 5 && !blockIdParts[0].equals("shuffle") && !blockIdParts[0].equals("shuffleChunk")) {
            throw new IllegalArgumentException("Unexpected shuffle block id format: " + blockId);
         } else {
            return blockIdParts;
         }
      } else {
         throw new IllegalArgumentException("Unexpected shuffle block id format: " + blockId);
      }
   }

   public void start() {
      this.client.sendRpc(this.message.toByteBuffer(), new RpcResponseCallback() {
         public void onSuccess(ByteBuffer response) {
            try {
               OneForOneBlockFetcher.this.streamHandle = (StreamHandle)BlockTransferMessage.Decoder.fromByteBuffer(response);
               OneForOneBlockFetcher.logger.trace("Successfully opened blocks {}, preparing to fetch chunks.", OneForOneBlockFetcher.this.streamHandle);

               for(int i = 0; i < OneForOneBlockFetcher.this.streamHandle.numChunks; ++i) {
                  if (OneForOneBlockFetcher.this.downloadFileManager != null) {
                     OneForOneBlockFetcher.this.client.stream(OneForOneStreamManager.genStreamChunkId(OneForOneBlockFetcher.this.streamHandle.streamId, i), OneForOneBlockFetcher.this.new DownloadCallback(i));
                  } else {
                     OneForOneBlockFetcher.this.client.fetchChunk(OneForOneBlockFetcher.this.streamHandle.streamId, i, OneForOneBlockFetcher.this.chunkCallback);
                  }
               }
            } catch (Exception e) {
               OneForOneBlockFetcher.logger.error("Failed while starting block fetches after success", e);
               OneForOneBlockFetcher.this.failRemainingBlocks(OneForOneBlockFetcher.this.blockIds, e);
            }

         }

         public void onFailure(Throwable e) {
            OneForOneBlockFetcher.logger.error("Failed while starting block fetches", e);
            OneForOneBlockFetcher.this.failRemainingBlocks(OneForOneBlockFetcher.this.blockIds, e);
         }
      });
   }

   private void failRemainingBlocks(String[] failedBlockIds, Throwable e) {
      for(String blockId : failedBlockIds) {
         try {
            this.listener.onBlockFetchFailure(blockId, e);
         } catch (Exception e2) {
            logger.error("Error in block fetch failure callback", e2);
         }
      }

   }

   private static class BlocksInfo {
      final ArrayList ids = new ArrayList();
      final ArrayList blockIds = new ArrayList();

      BlocksInfo() {
      }
   }

   private class ChunkCallback implements ChunkReceivedCallback {
      public void onSuccess(int chunkIndex, ManagedBuffer buffer) {
         OneForOneBlockFetcher.this.listener.onBlockFetchSuccess(OneForOneBlockFetcher.this.blockIds[chunkIndex], buffer);
      }

      public void onFailure(int chunkIndex, Throwable e) {
         String[] remainingBlockIds = (String[])Arrays.copyOfRange(OneForOneBlockFetcher.this.blockIds, chunkIndex, OneForOneBlockFetcher.this.blockIds.length);
         OneForOneBlockFetcher.this.failRemainingBlocks(remainingBlockIds, e);
      }
   }

   private class DownloadCallback implements StreamCallback {
      private DownloadFileWritableChannel channel = null;
      private DownloadFile targetFile = null;
      private int chunkIndex;

      DownloadCallback(int chunkIndex) throws IOException {
         this.targetFile = OneForOneBlockFetcher.this.downloadFileManager.createTempFile(OneForOneBlockFetcher.this.transportConf);
         this.channel = this.targetFile.openForWriting();
         this.chunkIndex = chunkIndex;
      }

      public void onData(String streamId, ByteBuffer buf) throws IOException {
         while(buf.hasRemaining()) {
            this.channel.write(buf);
         }

      }

      public void onComplete(String streamId) throws IOException {
         OneForOneBlockFetcher.this.listener.onBlockFetchSuccess(OneForOneBlockFetcher.this.blockIds[this.chunkIndex], this.channel.closeAndRead());
         if (!OneForOneBlockFetcher.this.downloadFileManager.registerTempFileToClean(this.targetFile)) {
            this.targetFile.delete();
         }

      }

      public void onFailure(String streamId, Throwable cause) throws IOException {
         this.channel.close();
         String[] remainingBlockIds = (String[])Arrays.copyOfRange(OneForOneBlockFetcher.this.blockIds, this.chunkIndex, OneForOneBlockFetcher.this.blockIds.length);
         OneForOneBlockFetcher.this.failRemainingBlocks(remainingBlockIds, cause);
         this.targetFile.delete();
      }
   }
}
