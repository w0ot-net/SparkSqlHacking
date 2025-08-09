package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import org.apache.spark.network.protocol.Encoders.IntArrays;
import org.apache.spark.network.protocol.Encoders.LongArrays;
import org.apache.spark.network.protocol.Encoders.Strings;

public class FetchShuffleBlocks extends AbstractFetchShuffleBlocks {
   public final long[] mapIds;
   public final int[][] reduceIds;
   public final boolean batchFetchEnabled;

   public FetchShuffleBlocks(String appId, String execId, int shuffleId, long[] mapIds, int[][] reduceIds, boolean batchFetchEnabled) {
      super(appId, execId, shuffleId);
      this.mapIds = mapIds;
      this.reduceIds = reduceIds;

      assert mapIds.length == reduceIds.length;

      this.batchFetchEnabled = batchFetchEnabled;
      if (batchFetchEnabled) {
         for(int[] ids : reduceIds) {
            assert ids.length == 2;
         }
      }

   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.FETCH_SHUFFLE_BLOCKS;
   }

   public String toString() {
      return this.toStringHelper().append("mapIds", Arrays.toString(this.mapIds)).append("reduceIds", Arrays.deepToString(this.reduceIds)).append("batchFetchEnabled", this.batchFetchEnabled).toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         FetchShuffleBlocks that = (FetchShuffleBlocks)o;
         if (!super.equals(that)) {
            return false;
         } else if (this.batchFetchEnabled != that.batchFetchEnabled) {
            return false;
         } else {
            return !Arrays.equals(this.mapIds, that.mapIds) ? false : Arrays.deepEquals(this.reduceIds, that.reduceIds);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + Arrays.hashCode(this.mapIds);
      result = 31 * result + Arrays.deepHashCode(this.reduceIds);
      result = 31 * result + (this.batchFetchEnabled ? 1 : 0);
      return result;
   }

   public int getNumBlocks() {
      if (this.batchFetchEnabled) {
         return this.mapIds.length;
      } else {
         int numBlocks = 0;

         for(int[] ids : this.reduceIds) {
            numBlocks += ids.length;
         }

         return numBlocks;
      }
   }

   public int encodedLength() {
      int encodedLengthOfReduceIds = 0;

      for(int[] ids : this.reduceIds) {
         encodedLengthOfReduceIds += IntArrays.encodedLength(ids);
      }

      return super.encodedLength() + LongArrays.encodedLength(this.mapIds) + 4 + encodedLengthOfReduceIds + 1;
   }

   public void encode(ByteBuf buf) {
      super.encode(buf);
      LongArrays.encode(buf, this.mapIds);
      buf.writeInt(this.reduceIds.length);

      for(int[] ids : this.reduceIds) {
         IntArrays.encode(buf, ids);
      }

      buf.writeBoolean(this.batchFetchEnabled);
   }

   public static FetchShuffleBlocks decode(ByteBuf buf) {
      String appId = Strings.decode(buf);
      String execId = Strings.decode(buf);
      int shuffleId = buf.readInt();
      long[] mapIds = LongArrays.decode(buf);
      int reduceIdsSize = buf.readInt();
      int[][] reduceIds = new int[reduceIdsSize][];

      for(int i = 0; i < reduceIdsSize; ++i) {
         reduceIds[i] = IntArrays.decode(buf);
      }

      boolean batchFetchEnabled = buf.readBoolean();
      return new FetchShuffleBlocks(appId, execId, shuffleId, mapIds, reduceIds, batchFetchEnabled);
   }
}
