package org.apache.spark.network.shuffle.protocol;

import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.spark.network.protocol.Encoders.BitmapArrays;
import org.apache.spark.network.protocol.Encoders.IntArrays;
import org.apache.spark.network.protocol.Encoders.LongArrays;
import org.roaringbitmap.RoaringBitmap;
import org.sparkproject.guava.base.Objects;

public class MergeStatuses extends BlockTransferMessage {
   public final int shuffleId;
   public final int shuffleMergeId;
   public final RoaringBitmap[] bitmaps;
   public final int[] reduceIds;
   public final long[] sizes;

   public MergeStatuses(int shuffleId, int shuffleMergeId, RoaringBitmap[] bitmaps, int[] reduceIds, long[] sizes) {
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.bitmaps = bitmaps;
      this.reduceIds = reduceIds;
      this.sizes = sizes;
   }

   protected BlockTransferMessage.Type type() {
      return BlockTransferMessage.Type.MERGE_STATUSES;
   }

   public int hashCode() {
      int objectHashCode = Objects.hashCode(new Object[]{this.shuffleId}) * 41 + Objects.hashCode(new Object[]{this.shuffleMergeId});
      return objectHashCode * 41 + Arrays.hashCode(this.reduceIds) * 41 + Arrays.hashCode(this.bitmaps) * 41 + Arrays.hashCode(this.sizes);
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append("shuffleId", this.shuffleId).append("shuffleMergeId", this.shuffleMergeId).append("reduceId size", this.reduceIds.length).toString();
   }

   public boolean equals(Object other) {
      if (!(other instanceof MergeStatuses o)) {
         return false;
      } else {
         return Objects.equal(this.shuffleId, o.shuffleId) && Objects.equal(this.shuffleMergeId, o.shuffleMergeId) && Arrays.equals(this.bitmaps, o.bitmaps) && Arrays.equals(this.reduceIds, o.reduceIds) && Arrays.equals(this.sizes, o.sizes);
      }
   }

   public int encodedLength() {
      return 8 + BitmapArrays.encodedLength(this.bitmaps) + IntArrays.encodedLength(this.reduceIds) + LongArrays.encodedLength(this.sizes);
   }

   public void encode(ByteBuf buf) {
      buf.writeInt(this.shuffleId);
      buf.writeInt(this.shuffleMergeId);
      BitmapArrays.encode(buf, this.bitmaps);
      IntArrays.encode(buf, this.reduceIds);
      LongArrays.encode(buf, this.sizes);
   }

   public static MergeStatuses decode(ByteBuf buf) {
      int shuffleId = buf.readInt();
      int shuffleMergeId = buf.readInt();
      RoaringBitmap[] bitmaps = BitmapArrays.decode(buf);
      int[] reduceIds = IntArrays.decode(buf);
      long[] sizes = LongArrays.decode(buf);
      return new MergeStatuses(shuffleId, shuffleMergeId, bitmaps, reduceIds, sizes);
   }
}
