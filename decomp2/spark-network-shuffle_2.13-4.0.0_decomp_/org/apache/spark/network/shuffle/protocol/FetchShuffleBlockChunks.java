package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import org.apache.spark.network.protocol.Encoders.IntArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class FetchShuffleBlockChunks extends AbstractFetchShuffleBlocks {
   public final int[] reduceIds;
   public final int[][] chunkIds;
   public final int shuffleMergeId;

   public FetchShuffleBlockChunks(String appId, String execId, int shuffleId, int shuffleMergeId, int[] reduceIds, int[][] chunkIds) {
      super(appId, execId, shuffleId);
      this.shuffleMergeId = shuffleMergeId;
      this.reduceIds = reduceIds;
      this.chunkIds = chunkIds;

      assert reduceIds.length == chunkIds.length;

   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.FETCH_SHUFFLE_BLOCK_CHUNKS;
   }

   public String toString() {
      return this.toStringHelper().append("shuffleMergeId", this.shuffleMergeId).append("reduceIds", Arrays.toString(this.reduceIds)).append("chunkIds", Arrays.deepToString(this.chunkIds)).toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         FetchShuffleBlockChunks that = (FetchShuffleBlockChunks)o;
         if (!super.equals(that)) {
            return false;
         } else {
            return this.shuffleMergeId == that.shuffleMergeId && Arrays.equals(this.reduceIds, that.reduceIds) ? Arrays.deepEquals(this.chunkIds, that.chunkIds) : false;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = super.hashCode() * 31 + this.shuffleMergeId;
      result = 31 * result + Arrays.hashCode(this.reduceIds);
      result = 31 * result + Arrays.deepHashCode(this.chunkIds);
      return result;
   }

   public int encodedLength() {
      int encodedLengthOfChunkIds = 0;

      for(int[] ids : this.chunkIds) {
         encodedLengthOfChunkIds += IntArrays.encodedLength(ids);
      }

      return super.encodedLength() + IntArrays.encodedLength(this.reduceIds) + 4 + 4 + encodedLengthOfChunkIds;
   }

   public void encode(ByteBuf buf) {
      super.encode(buf);
      buf.writeInt(this.shuffleMergeId);
      IntArrays.encode(buf, this.reduceIds);
      buf.writeInt(this.chunkIds.length);

      for(int[] ids : this.chunkIds) {
         IntArrays.encode(buf, ids);
      }

   }

   public int getNumBlocks() {
      int numBlocks = 0;

      for(int[] ids : this.chunkIds) {
         numBlocks += ids.length;
      }

      return numBlocks;
   }

   public static FetchShuffleBlockChunks decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      int shuffleId = buf.readInt();
      int shuffleMergeId = buf.readInt();
      int[] reduceIds = IntArrays.decode(buf);
      int chunkIdsLen = buf.readInt();
      int[][] chunkIds = new int[chunkIdsLen][];

      for(int i = 0; i < chunkIdsLen; ++i) {
         chunkIds[i] = IntArrays.decode(buf);
      }

      return new FetchShuffleBlockChunks(appId, execId, shuffleId, shuffleMergeId, reduceIds, chunkIds);
   }
}
